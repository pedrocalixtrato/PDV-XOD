package dc.entidade.folhapagamento.inss;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_inss")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class InssEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_inss_id_seq")
	@SequenceGenerator(name = "folha_inss_id_seq", sequenceName = "folha_inss_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Caption("Competência")
	@Column(name = "competencia")
	private String competencia = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_empresa", nullable = false)
	 * 
	 * @Caption("Empresa")
	 * 
	 * @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	 * private Empresa empresa;
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "inss", fetch = FetchType.LAZY)
	private List<RetencaoEntity> retencaoList;

	/**
	 * CONSTRUTOR
	 */

	public InssEntity() {
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

	/*
	 * public Empresa getEmpresa() { return empresa; }
	 * 
	 * public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	 */

	public List<RetencaoEntity> getRetencaoList() {
		return retencaoList;
	}

	public void setRetencaoList(List<RetencaoEntity> retencaoList) {
		this.retencaoList = retencaoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}