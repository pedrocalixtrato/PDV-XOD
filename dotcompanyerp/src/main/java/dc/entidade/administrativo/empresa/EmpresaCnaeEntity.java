package dc.entidade.administrativo.empresa;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.SimNaoEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.CnaeEntity;

@Entity
@Table(name = "empresa_cnae")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EmpresaCnaeEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "empresa_cnae_id_seq")
	@SequenceGenerator(name = "empresa_cnae_id_seq", sequenceName = "empresa_cnae_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Principal")
	@Column(name = "principal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn principal;

	@Field
	@Caption("Ramo de atividade")
	@Column(name = "ramo_atividade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ramoAtividade;

	@Field
	@Caption("Objeto social")
	@Column(name = "objeto_social")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String objetoSocial;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("CNAE")
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "ID_CNAE", nullable = false)
	private CnaeEntity cnae;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getCnae().getNome();
	}

	/**
	 * CONSTRUTOR
	 */

	public EmpresaCnaeEntity() {
		// TODO Auto-generated constructor stub
	}

	public EmpresaCnaeEntity(Integer id) {
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

	public SimNaoEn getPrincipal() {
		return principal;
	}

	public void setPrincipal(SimNaoEn principal) {
		this.principal = principal;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getObjetoSocial() {
		return objetoSocial;
	}

	public void setObjetoSocial(String objetoSocial) {
		this.objetoSocial = objetoSocial;
	}

	public CnaeEntity getCnae() {
		return cnae;
	}

	public void setCnae(CnaeEntity cnae) {
		this.cnae = cnae;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}