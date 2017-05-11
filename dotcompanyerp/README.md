dotcompanyerp
=============

Alteração Importante.

Descrição: Acréscimo da camada BUSINESS. Esta camada ficará responsável por centralizar todas as regras de negócio de 
determinado módulo como também pelo desacomplamento das regras de negócio com a camada de controle (CONTROLLER). O seu 
funcionamento fica independente do tipo de controle que poderá ser implementado (VAADIN, JSF, STRUTS, ...). 

A camada de controle (CONTROLLER) passa a ficar responsável pela ligação da visão (VIEW) com o mesmo (CONTROLLER) como 
também pela manipulação da iteração visual. A camada DAO passa a ficar responsável pela comunicação, pela iteração e 
pela manipulação com o banco de dados apenas. As camadas CONTROLLER e DAO não devem conter regras de negócio 
implementado nelas, apenas suas funções respectivamente.


Mapeamento de dados entre formulários e o modelo de dados
---------------------------------------------------------

No projeto há duas maneiras de se fazer a transferência dos dados da camada modelo para um formulário. O método
"manual" envolve, para cada campo do formulário, usar os métodos getValue/setValue do campo e os getter/setters da
entidade para transferir os valores entre as duas camadas. Estes métodos são chamados ao processar os eventos de
"validar", "salvar", "novo" e "carregar" do CRUD. Além da transferência de valores, o desenvolvedor também deve manualmente
fazer a validação em caso de erro. Um exemplo é a classe GrupoFormController:

```java
@Override
protected boolean validaSalvar() {
    try {
        GrupoProdutoUtils.validateRequiredFields(this.subView);

        return true;
    } catch (DotErpException dee) {
        adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

        return false;
    }
}

@Override
protected void actionSalvar() {
    try {
        this.entity.setNome(this.subView.getTfNome().getValue());
        this.entity.setDescricao(this.subView.getTfDescricao().getValue());

        this.business.saveOrUpdate(this.entity);

        notifiyFrameworkSaveOK(this.entity);
    } catch (Exception e) {
        e.printStackTrace();

        mensagemErro(e.getMessage());
    }
}

@Override
protected void carregar(Serializable id) {
    try {
        this.entity = this.business.find(id);

        this.subView.getTfNome().setValue(this.entity.getNome());
        this.subView.getTfDescricao().setValue(this.entity.getDescricao());
    } catch (Exception e) {
        e.printStackTrace();

        mensagemErro(e.getMessage());
    }
}

/**
 * Parte de GrupoProdutoUtils.java
 **/

public static void validateRequiredFields(GrupoFormView subView) throws DotErpException {
    String nome = subView.getTfNome().getValue();

    if (StringUtils.isBlank(nome)) {
        throw new DotErpException(subView.getTfNome(),
                "::DotERP - Não pode ficar em branco");
    }

    String descricao = subView.getTfDescricao().getValue();

    if (StringUtils.isBlank(descricao)) {
        throw new DotErpException(subView.getTfDescricao(),
                "::DotERP - Não pode ficar em branco");
    }
}

```

Este método, apesar de ser flexível e dar total controle ao programador, exige muito código para implantar um único
campo e rapidamente fica tedioso ao ser utilizado em dezenas de campos, em vário de formulários. Como alternativa
o Vaadin 7 tem a funcionalidade "FieldGroup", que com algumas alterações específicas ao projeto, permite cuidar
do mapeamento de dados ao carregar, salvar e da validação em um único comando e utilizando as anotações do Hibernate
Validator. O método utilizando "FieldGroup" pode ser utilizado ao lado do método manual e o código existente pode ser
convertido gradualmente.

Abaixo um passo-a-passo de como implantar mapeamento e validação utilizando "FieldGroup". O código completo já está
implantado e comentado nas classes MarcaFormController e MarcaEntity. O primeiro passo é incluir as anotações que
definem as restrições na entidade. A mais comum é a anotação `@NotNull` que significa que o atributo é obrigatório.
Outra comum é a `@Length` que determina opcionalmente um tamanho mínimo e máximo para o campo. Abaixo o código
da classe `MarcaEntity` (as linhas não relevantes foram removidas):

```java
public class MarcaEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_marca_id_seq")
    @SequenceGenerator(name = "produto_marca_id_seq", sequenceName = "produto_marca_id_seq", allocationSize = 1, initialValue = 0)
    @Basic(optional = false)
    private Integer id;

    @Column(name = "NOME")
    @NotNull(message = "Nome é obrigatório")            // Anotação de validação
    private String nome;

    @Lob
    @Type(type = "text")
    @Basic(fetch = javax.persistence.FetchType.LAZY)
    @Column(name = "DESCRICAO")
    @NotNull(message = "Descrição é obrigatório")       // Anotação de validação
    private String descricao;

}
```

Para configurar o mapeamento, o programador cria um novo `FieldGroup` usando a classe `DCFieldGroup` no método
 `initSubView` do Controller passando a classe do modelo de dados como argumento. Para mapear um campo, basta
 chamar o método `bind` passando o campo e uma String com o nome da propriedade. A classe herdada `CRUDFormController`
 já tem um campo `fieldGroup` para essa finalidade.

Exemplo da classe `MarcaFormController`:

```java
@Override
protected void initSubView() {
    try {
        this.subView = new MarcaFormView(this);

        // Cria o DCFieldGroup
        this.fieldGroup = new DCFieldGroup<>(MarcaEntity.class);

        // Mapeia os campos
        fieldGroup.bind(this.subView.getTfNome(),"nome");
        fieldGroup.bind(this.subView.getTfDescricao(),"descricao");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

Ao carregar ou ao criar novo registro, deve-ser passar a entidade como a fonte de dados do `FieldGroup`.

```java
@Override
protected void carregar(Serializable id) {
    try {
        this.entity = this.business.find(id);

        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.entity);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
protected void criarNovoBean() {
    try {
        this.entity = new MarcaEntity();

        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.entity);

    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
    }
}

```

Para a ação validar/salvar, basta chamar o método `commit` do `FieldGroup`, que lançará um erro se houver algum erro
de validação. O próprio `FieldGroup` se encarrega de decorar os campos com problemas. No método `actionSalvar` não
é necessário fazer nada uma vez que, se não houver erros no `commit`, os dados são transferidos automaticamente
dos campos para a entidade.

```java
@Override
protected boolean validaSalvar() {
    try {
        // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
        fieldGroup.commit();
        return true;
    } catch (FieldGroup.CommitException ce) {
        return false;
    }
}

@Override
protected void actionSalvar() {
    try {
        // Note que não é necessário fazer mais nada. Se o commit for bem sucedido, os dados já estão na entidade.
        this.business.saveOrUpdate(this.entity);
        notifiyFrameworkSaveOK(this.entity);
    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
    }
}
```

As conversões são feitas automaticamente. Se não for possível a conversão automática, por exemplo, dum campo de
CNPJ formatado `00.000.000/0000-00` para somente números, o programador deve instalar um conversor utilizando o
método `setConverter` do campo. O `FieldGroup` também cuida de definir o método `setRequired` automaticamente com base
na existência ou não da anotação `@NotNull`.

O método `FieldGroup` também aceita o componente customizado `ManyToOneCombo`. O programador, porém, deve utilizar
a classe `ManyToOneComboField`, que se comporta adequadamente como um `Field` nos moldes do Vaadin. A configuração
é feita do mesmo modo ou utilizando o método simplificado `ManyToOneComboField#configuraCombo`.

As classes `ProdutoFormController` e `ProdutoEntity`, atualmente, implementam parcialmente o novo método de mapeamente,
para os atributos `nome`, `unidadeProduto`, `subGrupo` e `ippt`. Os atributos `unidadeProduto` e `subGrupo` são
relacionamentos many-to-one e demonstram a funcionalidade com o `ManyToOneComboField`.