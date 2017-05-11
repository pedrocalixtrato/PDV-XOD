package dc.entidade.nfe;

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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_det_especifico_armamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetEspecificoArmamentoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_det_especifico_armamento_id_seq")
	@SequenceGenerator(name = "nfe_det_especifico_armamento_id_seq", sequenceName = "nfe_det_especifico_armamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "tipo_arma")
	@Caption(value = "Tipo de arma")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoArma = "";

	@Field
	@Column(name = "numero_serie_arma")
	@Caption(value = "Número de série da arma")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroSerieArma = "";

	@Field
	@Column(name = "numero_serie_cano")
	@Caption(value = "Número de série do cano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroSerieCano = "";

	@Field
	@Column(name = "descricao")
	@Caption(value = "Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_nfe_detalhe", nullable = false)
	@Caption(value = "NFE detalhe")
	private NfeDetalheEntity nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDetEspecificoArmamentoEntity() {
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

	public String getTipoArma() {
		return tipoArma;
	}

	public void setTipoArma(String tipoArma) {
		this.tipoArma = (tipoArma == null ? "" : tipoArma.toUpperCase().trim());
	}

	public String getNumeroSerieArma() {
		return numeroSerieArma;
	}

	public void setNumeroSerieArma(String numeroSerieArma) {
		this.numeroSerieArma = (numeroSerieArma == null ? "" : numeroSerieArma
				.toUpperCase().trim());
	}

	public String getNumeroSerieCano() {
		return numeroSerieCano;
	}

	public void setNumeroSerieCano(String numeroSerieCano) {
		this.numeroSerieCano = (numeroSerieCano == null ? "" : numeroSerieCano
				.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase()
				.trim());
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}