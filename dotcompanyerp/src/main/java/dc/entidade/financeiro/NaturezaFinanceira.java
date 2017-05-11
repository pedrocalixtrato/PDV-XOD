package dc.entidade.financeiro;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */
@Entity
@Table(name = "natureza_financeira")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NaturezaFinanceira extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "natureza_financeira_id_seq")
	@SequenceGenerator(name = "natureza_financeira_id_seq", sequenceName = "natureza_financeira_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Classificação")
	@Column(name = "CLASSIFICACAO")
	@NotNull(message = "Classificação é Obrigatório!")
	private String classificacao;

	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao;

	@Column(name = "TIPO")
	@Caption(value = "Tipo")
	private String tipo;

	@Caption(value = "Aplicação")
	@Column(name = "APLICACAO", length = 255)
	@NotNull(message = "Aplicação é Obrigatório!")
	private String aplicacao;

	@Caption(value = "Aparece à Pagar")
	@Column(name = "APARECE_A_PAGAR")
	private String aparecePagar;

	@Caption(value = "Aparece à Receber")
	@Column(name = "APARECE_A_RECEBER")
	private String apareceReceber;

	@Caption(value = "Plano Natureza Financeira")
	@JoinColumn(name = "ID_PLANO_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@NotNull(message = "Plano Natureza Financeira é Obrigatório!")
	private PlanoNaturezaFinanceira planoNaturezaFinanceira;

	@Caption(value = "Contábil Conta")
	@JoinColumn(name = "id_contabil_conta", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@NotNull(message = "Contábil Conta é Obrigatório!")
	private ContabilContaEntity contabilconta;

	public NaturezaFinanceira() {
	}

	public NaturezaFinanceira(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getAparecePagar() {
		return aparecePagar;
	}

	public void setAparecePagar(String aparecePagar) {
		this.aparecePagar = aparecePagar;
	}

	public String getApareceReceber() {
		return apareceReceber;
	}

	public void setApareceReceber(String apareceReceber) {
		this.apareceReceber = apareceReceber;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NaturezaFinanceira)) {
            return false;
        }

        NaturezaFinanceira that = (NaturezaFinanceira) obj;
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

	@Override
	public String toString() {
		return descricao;
	}

	/**
	 * @return the planoNaturezaFinanceira
	 */
	public PlanoNaturezaFinanceira getPlanoNaturezaFinanceira() {
		return planoNaturezaFinanceira;
	}

	/**
	 * @param planoNaturezaFinanceira
	 *            the planoNaturezaFinanceira to set
	 */
	public void setPlanoNaturezaFinanceira(PlanoNaturezaFinanceira planoNaturezaFinanceira) {
		this.planoNaturezaFinanceira = planoNaturezaFinanceira;
	}

	public ContabilContaEntity getContabilconta() {
		return contabilconta;
	}

	public void setContabilconta(ContabilContaEntity contabilconta) {
		this.contabilconta = contabilconta;
	}

}
