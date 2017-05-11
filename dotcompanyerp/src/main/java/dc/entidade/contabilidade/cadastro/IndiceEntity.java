package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_INDICE")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contabil_indice_id_seq")
	@SequenceGenerator(name = "contabil_indice_id_seq", sequenceName = "contabil_indice_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "periodicidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Periodicidade")
	@NotNull(message = "Periodicidade é Obrigatório!")
	private String periodicidade = "";

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "diario_a_partir_de")
	@Caption(value = "Diário a partir de")
	@Temporal(TemporalType.DATE)
	private Date diarioPartirDe;

	@Field
	@Column(name = "mensal_mes_ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Mensal mês ano")
	private String mensalMesAno = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_indice_economico integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_indice_economico", nullable = false)
	@Caption("Índice econômico")
	@NotNull(message = "índice Econômico é Obrigatório!")
	private IndiceEconomicoEntity indiceEconomico;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "indice", fetch = FetchType.LAZY)
	private List<IndiceValorEntity> indiceValorList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getPeriodicidade();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public IndiceEntity() {
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

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = (periodicidade == null ? "" : periodicidade
				.toUpperCase());
	}

	public Date getDiarioPartirDe() {
		return diarioPartirDe;
	}

	public void setDiarioPartirDe(Date diarioPartirDe) {
		this.diarioPartirDe = diarioPartirDe;
	}

	public String getMensalMesAno() {
		return mensalMesAno;
	}

	public void setMensalMesAno(String mensalMesAno) {
		this.mensalMesAno = (mensalMesAno == null ? "" : mensalMesAno
				.toUpperCase());
	}

	public IndiceEconomicoEntity getIndiceEconomico() {
		return indiceEconomico;
	}

	public void setIndiceEconomico(IndiceEconomicoEntity indiceEconomico) {
		this.indiceEconomico = indiceEconomico;
	}

	public List<IndiceValorEntity> getIndiceValorList() {
		return indiceValorList;
	}

	public void setIndiceValorList(List<IndiceValorEntity> indiceValorList) {
		this.indiceValorList = indiceValorList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}