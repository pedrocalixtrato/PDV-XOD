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
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "CENTRO_RESULTADO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CentroResultado extends AbstractMultiEmpresaModel<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centro_resultado_id_seq")
	@SequenceGenerator(name = "centro_resultado_id_seq", sequenceName = "centro_resultado_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "CLASSIFICACAO")
	@Caption(value = "Classificação")
	@NotNull(message = "Classificação é Obrigatório!")
	private String classificacao;
	
	@Column(name = "DESCRICAO")
	@Caption(value = "Descrição")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao;
	
	@Column(name = "SOFRE_RATEIO")
	@Caption(value = "Sofre Rateio")
	private Character sofreRateio;
	
	@JoinColumn(name = "ID_PLANO_CENTRO_RESULTADO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Plano Centro Resultado")
	@NotNull(message = "Plano Centro Resultado é Obrigatório!")
	private PlanoCentroResultado planoCentroResultado;

	public CentroResultado() {
	}

	public CentroResultado(Integer id) {
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

	public Character getSofreRateio() {
		return sofreRateio;
	}

	public void setSofreRateio(Character sofreRateio) {
		this.sofreRateio = sofreRateio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CentroResultado)) {
            return false;
        }

        CentroResultado that = (CentroResultado) obj;
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

	/**
	 * @return the planoCentroResultado
	 */
	public PlanoCentroResultado getPlanoCentroResultado() {
		return planoCentroResultado;
	}

	/**
	 * @param planoCentroResultado
	 *            the planoCentroResultado to set
	 */
	public void setPlanoCentroResultado(PlanoCentroResultado planoCentroResultado) {
		this.planoCentroResultado = planoCentroResultado;
	}
	
	@Override
	public String toString() {
		return descricao;
	}

}