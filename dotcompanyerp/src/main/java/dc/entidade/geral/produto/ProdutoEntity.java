package dc.entidade.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;

@Entity
@Table(name = "produto")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProdutoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("GTIN")
	@Column(name = "GTIN", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtin = "";

	@Field
	@Caption("Código interno")
	@Column(name = "CODIGO_INTERNO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoInterno = "";

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "Nome é obrigatório")
    //@Length(min = 4, message = "O tamanho deve ser no mínimo 4 caracteres")
	private String nome = "";

	// @Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao = "";

	@Field
	@Caption("Descrição do PDV")
	@Column(name = "DESCRICAO_PDV", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoPdv = "";

	@Field
	@Caption("Valor da compra")
	@Column(name = "VALOR_COMPRA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Valor de Compra é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorCompra;

	@Field
	@Caption("Valor da venda")
	@Column(name = "VALOR_VENDA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Valor de Venda é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorVenda;

	@Field
	@Caption("Preço de venda mínimo")
	@Column(name = "PRECO_VENDA_MINIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Preco de Venda Mínimo é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal precoVendaMinimo;

	@Field
	@Caption("Preço sugerido")
	@Column(name = "PRECO_SUGERIDO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Preço Sugerido é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal precoSugerido;

	@Field
	@Caption("Custo médio líquido")
	@Column(name = "CUSTO_MEDIO_LIQUIDO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Custo Médio Líquido é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal custoMedioLiquido;

	@Field
	@Caption("Preço de lucro zero")
	@Column(name = "PRECO_LUCRO_ZERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Preço Lucro Zero é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal precoLucroZero;

	@Field
	@Caption("Preço de lucro mínimo")
	@Column(name = "PRECO_LUCRO_MINIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Preço Lucro Mínimo é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal precoLucroMinimo;

	@Field
	@Caption("Preço de lucro máximo")
	@Column(name = "PRECO_LUCRO_MAXIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Preço Lucro Máximo é obrigatório")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal precoLucroMaximo;

	@Field
	@Caption("Markup")
	@Column(name = "MARKUP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal markup;

	@Field
	@Caption("Quantidade de estoque")
	@Column(name = "QUANTIDADE_ESTOQUE")
	// @Column(name = "QUANTIDADE_ESTOQUE", , precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoque;

	@Field
	@Caption("Quantidade de estoque anterior")
	@Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR")
	// @Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR", , precision = 11, scale =
	// 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoqueAnterior;

	@Field
	@Caption("Estoque mínimo")
	@Column(name = "ESTOQUE_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMinimo = new BigDecimal(0);

	@Field
	@Caption("Estoque máximo")
	@Column(name = "ESTOQUE_MAXIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMaximo;

	@Field
	@Caption("Estoque ideal")
	@Column(name = "ESTOQUE_IDEAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueIdeal;

	@Field
	@Caption("Código LST")
	@Column(name = "codigo_lst")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código Lst é obrigatório")
	private String codigoLst = "";

	@Field
	@Caption("Totalizador parcial")
	@Column(name = "TOTALIZADOR_PARCIAL", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String totalizadorParcial = "";

	@Field
	@Caption("Código da balança")
	@Column(name = "CODIGO_BALANCA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código Balança obrigatório")
	private Integer codigoBalanca = new Integer(0);

	@Field
	@Caption("Peso")
	@Column(name = "PESO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal peso = new BigDecimal(0);

	@Field
	@Caption("Porcentagem da comissão")
	@Column(name = "PORCENTO_COMISSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaComissao;

	@Field
	@Caption("Ponto do pedido")
	@Column(name = "PONTO_PEDIDO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pontoPedido;

	@Field
	@Caption("Lote econômico de compra")
	@Column(name = "LOTE_ECONOMICO_COMPRA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal loteEconomicoCompra;

	@Field
	@Caption("Alíquota ICMS")
	@Column(name = "aliquota_icms_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcms;

	@Field
	@Caption("Alíquota ISSQN")
	@Column(name = "aliquota_issqn_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIssqn;

	@Field
	@Caption("EXTIPI")
	@Column(name = "EX_TIPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "ExTipi é obrigatório")
	private String exTipi = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de venda")
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Tipo de Venda é obrigatório")
	private VendaTipoVendaEn tipoVenda;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Inativo")
	@Column(name = "INATIVO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Inativo é obrigatório")
	private SimNaoEn inativo;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Classe")
	@Column(name = "CLASSE_ABC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Classe é obrigatório")
	private ClasseEn classe;

	@Enumerated(EnumType.STRING)
	// @Type(type = "dc.control.enums.IatEn", parameters = @Parameter(name =
	// "type", value = "dc.control.enums.IatEn"))
	@Field
	@Caption("IAT")
	@Column(name = "iat")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "IAT é obrigatório")
	private IatEn iat;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("IPPT")
	@Column(name = "ippt")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "IPPT é obrigatório")
	private IpptEn ippt;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo do item SPED")
	@Column(name = "tipo_item_sped")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Tipo Item SPED é obrigatório")
	private TipoSpedEn tipoSped;

	/**
	 * REFERENCIA - FK
	 */
	
	@Caption("Subgrupo")
	@ManyToOne()
	@JoinColumn(name = "id_sub_grupo", referencedColumnName = "id", nullable = false)
	@NotNull(message = "SubGrupo é obrigatório")
	@IndexedEmbedded(depth=2, includePaths={"nome"})
	private SubGrupoEntity subGrupo;

	@Caption("Grupo")
	@ManyToOne()
	@JoinColumn(name = "id_produto_grupo", nullable = false)
	@NotNull(message = "Grupo é obrigatório")
	@IndexedEmbedded(includePaths={"nome"})
	private GrupoEntity grupo;

	@Caption("Unidade do produto")
	@ManyToOne()
	@JoinColumn(name = "id_unidade_produto",referencedColumnName = "id", nullable = false)
    @NotNull(message = "Unidade do produto é obrigatório")
	@IndexedEmbedded(includePaths={"descricao"})
	private UnidadeProdutoEntity unidadeProduto;

	@Caption("Marca do produto")
	@ManyToOne()
	@JoinColumn(name = "id_marca_produto",referencedColumnName = "id", nullable = false)
	@NotNull(message = "Marca do produto é obrigatório")
	@IndexedEmbedded(includePaths={"nome"})
	private MarcaEntity marca;

	@Caption("Almoxarifado")
	@ManyToOne()
	@JoinColumn(name = "id_almoxarifado", nullable = false)
	@NotNull(message = "Almoxarifado é obrigatório")
	@IndexedEmbedded(includePaths={"nome"})
	private AlmoxarifadoEntity almoxarifado;

	@Caption("NCM")
	@ManyToOne()
	@JoinColumn(name = "id_ncm", nullable = false)
	@NotNull(message = "Ncm é obrigatório")
	@IndexedEmbedded(includePaths={"descricao"})
	private NcmEntity ncm;

	@Caption("Grupo tributário")
	@ManyToOne()
	@JoinColumn(name = "id_grupo_tributario", nullable = true)
	//@NotNull(message = "Grupo Tributário é obrigatório")
	@IndexedEmbedded(includePaths={"nome"})
	private GrupoTributarioEntity grupoTributario;

	@Caption("ICMS customizado")
	@ManyToOne()
	@JoinColumn(name = "id_tribut_icms_custom_cab")
	private IcmsCustomizadoCabecalhoEntity icmsCustomizado;

	/**
	 * REFERENCIA - LIST
	 */
	
	/**
	 * CONSTRUTOR
	 */

	public ProdutoEntity() {

	}

	public ProdutoEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = (gtin == null ? "".trim() : gtin);
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = (codigoInterno == null ? "".trim() : codigoInterno
				.toUpperCase().trim());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public String getDescricaoPdv() {
		return descricaoPdv;
	}

	public void setDescricaoPdv(String descricaoPdv) {
		this.descricaoPdv = (descricaoPdv == null ? "".trim() : descricaoPdv
				.toUpperCase().trim());
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = (valorCompra == null ? new BigDecimal(0)
				: valorCompra);
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = (valorVenda == null ? new BigDecimal(0) : valorVenda);
	}

	public BigDecimal getPrecoVendaMinimo() {
		return precoVendaMinimo;
	}

	public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) {
		this.precoVendaMinimo = (precoVendaMinimo == null ? new BigDecimal(0)
				: precoVendaMinimo);
	}

	public BigDecimal getPrecoSugerido() {
		return precoSugerido;
	}

	public void setPrecoSugerido(BigDecimal precoSugerido) {
		this.precoSugerido = (precoSugerido == null ? new BigDecimal(0)
				: precoSugerido);
	}

	public BigDecimal getCustoMedioLiquido() {
		return custoMedioLiquido;
	}

	public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) {
		this.custoMedioLiquido = (custoMedioLiquido == null ? new BigDecimal(0)
				: custoMedioLiquido);
	}

	public BigDecimal getPrecoLucroZero() {
		return precoLucroZero;
	}

	public void setPrecoLucroZero(BigDecimal precoLucroZero) {
		this.precoLucroZero = (precoLucroZero == null ? new BigDecimal(0)
				: precoLucroZero);
	}

	public BigDecimal getPrecoLucroMinimo() {
		return precoLucroMinimo;
	}

	public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) {
		this.precoLucroMinimo = (precoLucroMinimo == null ? new BigDecimal(0)
				: precoLucroMinimo);
	}

	public BigDecimal getPrecoLucroMaximo() {
		return precoLucroMaximo;
	}

	public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) {
		this.precoLucroMaximo = (precoLucroMaximo == null ? new BigDecimal(0)
				: precoLucroMaximo);
	}

	public BigDecimal getMarkup() {
		return markup;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = (markup == null ? new BigDecimal(0) : markup);
	}

	public BigDecimal getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
		this.quantidadeEstoque = (quantidadeEstoque);
	}

	public BigDecimal getQuantidadeEstoqueAnterior() {
		return quantidadeEstoqueAnterior;
	}

	public void setQuantidadeEstoqueAnterior(
			BigDecimal quantidadeEstoqueAnterior) {
		this.quantidadeEstoqueAnterior = (quantidadeEstoqueAnterior);
	}

	public BigDecimal getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
		this.estoqueMinimo = (estoqueMinimo == null ? new BigDecimal(0)
				: estoqueMinimo);
	}

	public BigDecimal getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(BigDecimal estoqueMaximo) {
		this.estoqueMaximo = (estoqueMaximo == null ? new BigDecimal(0)
				: estoqueMaximo);
	}

	public BigDecimal getEstoqueIdeal() {
		return estoqueIdeal;
	}

	public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
		this.estoqueIdeal = (estoqueIdeal == null ? new BigDecimal(0)
				: estoqueIdeal);
	}

	public String getCodigoLst() {
		return codigoLst;
	}

	public void setCodigoLst(String codigoLst) {
		this.codigoLst = (codigoLst == null ? "".trim() : codigoLst
				.toUpperCase().trim());
	}

	public String getTotalizadorParcial() {
		return totalizadorParcial;
	}

	public void setTotalizadorParcial(String totalizadorParcial) {
		this.totalizadorParcial = (totalizadorParcial == null ? "".trim()
				: totalizadorParcial.toUpperCase().trim());
	}

	public Integer getCodigoBalanca() {
		return codigoBalanca;
	}

	public void setCodigoBalanca(Integer codigoBalanca) {
		this.codigoBalanca = (codigoBalanca == null ? new Integer(0)
				: codigoBalanca);
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = (peso == null ? new BigDecimal(0) : peso);
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = (taxaComissao == null ? new BigDecimal(0)
				: taxaComissao);
	}

	public BigDecimal getPontoPedido() {
		return pontoPedido;
	}

	public void setPontoPedido(BigDecimal pontoPedido) {
		this.pontoPedido = (pontoPedido == null ? new BigDecimal(0)
				: pontoPedido);
	}

	public BigDecimal getLoteEconomicoCompra() {
		return loteEconomicoCompra;
	}

	public void setLoteEconomicoCompra(BigDecimal loteEconomicoCompra) {
		this.loteEconomicoCompra = (loteEconomicoCompra == null ? new BigDecimal(
				0) : loteEconomicoCompra);
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = (aliquotaIcms == null ? new BigDecimal(0)
				: aliquotaIcms);
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = (aliquotaIssqn == null ? new BigDecimal(0)
				: aliquotaIssqn);
	}

	public String getExTipi() {
		return exTipi;
	}

	public void setExTipi(String exTipi) {
		this.exTipi = (exTipi == null ? "".trim() : exTipi.toUpperCase().trim());
	}

	public VendaTipoVendaEn getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(VendaTipoVendaEn tipoVenda) {
		this.tipoVenda = tipoVenda;
	}

	public SimNaoEn getInativo() {
		return inativo;
	}

	public void setInativo(SimNaoEn inativo) {
		this.inativo = inativo;
	}

	public ClasseEn getClasse() {
		return classe;
	}

	public void setClasse(ClasseEn classe) {
		this.classe = classe;
	}

	public IatEn getIat() {
		return iat;
	}

	public void setIat(IatEn iat) {
		this.iat = iat;
	}

	public IpptEn getIppt() {
		return ippt;
	}

	public void setIppt(IpptEn ippt) {
		this.ippt = ippt;
	}

	public TipoSpedEn getTipoSped() {
		return tipoSped;
	}

	public void setTipoSped(TipoSpedEn tipoSped) {
		this.tipoSped = tipoSped;
	}

	public SubGrupoEntity getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupoEntity subGrupo) {
		this.subGrupo = subGrupo;
	}

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
	}

	public UnidadeProdutoEntity getUnidadeProduto() {
		return unidadeProduto;
	}

	public void setUnidadeProduto(UnidadeProdutoEntity unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}

	public AlmoxarifadoEntity getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(AlmoxarifadoEntity almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public NcmEntity getNcm() {
		return ncm;
	}

	public void setNcm(NcmEntity ncm) {
		this.ncm = ncm;
	}

	public GrupoTributarioEntity getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributarioEntity grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public IcmsCustomizadoCabecalhoEntity getIcmsCustomizado() {
		return icmsCustomizado;
	}

	public void setIcmsCustomizado(IcmsCustomizadoCabecalhoEntity icmsCustomizado) {
		this.icmsCustomizado = icmsCustomizado;
	}
	
	/**
	 * TO STRING
	 */
	
	@Override
	public String toString() {
		return nome;
	}

//@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProdutoEntity)) {
            return false;
        }

        ProdutoEntity that = (ProdutoEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(getId(), that.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
        }
    }

}