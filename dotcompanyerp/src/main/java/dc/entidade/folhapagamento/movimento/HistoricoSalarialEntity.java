package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_historico_salarial")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class HistoricoSalarialEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_historico_salarial_id_seq")
	@SequenceGenerator(name = "folha_historico_salarial_id_seq", sequenceName = "folha_historico_salarial_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "competencia")
	@Field
	@Caption("Competência")
	private String competencia = "";

	@Column(name = "salario_atual")
	@Field
	@Caption("Salário atual")
	private Double salarioAtual = new Double(0.0);

	@Column(name = "percentual_aumento")
	@Field
	@Caption("Percentual de aumento")
	private Double percentualAumento = new Double(0.0);

	@Column(name = "salario_novo")
	@Field
	@Caption("Salário novo")
	private Double salarioNovo = new Double(0.0);

	@Column(name = "valido_a_partir")
	@Field
	@Caption("Válido a partir")
	private String validoAPartir = "";

	@Column(name = "motivo")
	@Field
	@Caption("Motivo")
	private String motivo = "";

	/**
	 * REFERENCIA - FK
	 */

	/* id_colaborador integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public HistoricoSalarialEntity() {
		// TODO Auto-generated constructor stub
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

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = (competencia == null ? "" : competencia
				.toUpperCase());
	}

	public Double getSalarioAtual() {
		return salarioAtual;
	}

	public void setSalarioAtual(Double salarioAtual) {
		this.salarioAtual = (salarioAtual == null ? new Double(0.0)
				: salarioAtual);
	}

	public Double getPercentualAumento() {
		return percentualAumento;
	}

	public void setPercentualAumento(Double percentualAumento) {
		this.percentualAumento = (percentualAumento == null ? new Double(0.0)
				: percentualAumento);
	}

	public Double getSalarioNovo() {
		return salarioNovo;
	}

	public void setSalarioNovo(Double salarioNovo) {
		this.salarioNovo = (salarioNovo == null ? new Double(0.0) : salarioNovo);
	}

	public String getValidoAPartir() {
		return validoAPartir;
	}

	public void setValidoAPartir(String validoAPartir) {
		this.validoAPartir = (validoAPartir == null ? "" : validoAPartir
				.toUpperCase());
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = (motivo == null ? "" : motivo.toUpperCase());
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}