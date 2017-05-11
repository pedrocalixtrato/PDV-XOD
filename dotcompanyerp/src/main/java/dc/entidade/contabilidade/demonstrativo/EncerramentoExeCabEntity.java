package dc.entidade.contabilidade.demonstrativo;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_ENCERRAMENTO_EXE_CAB")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EncerramentoExeCabEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_encerramento_exe_cab_id_seq")
	@SequenceGenerator(name = "contabil_encerramento_exe_cab_id_seq", sequenceName = "contabil_encerramento_exe_cab_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_inicio")
	@Caption(value = "Data do início")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_fim")
	@Caption(value = "Data do término")
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_inclusao")
	@Caption(value = "Data da inclusão")
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;

	@Field
	@Column(name = "motivo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Motivo")
	private String motivo = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "encerramentoExeCab", fetch = FetchType.LAZY)
	private List<EncerramentoExeDetEntity> encerramentoExeDetList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getMotivo();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public EncerramentoExeCabEntity() {
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = (motivo == null ? "" : motivo.toUpperCase());
	}

	public List<EncerramentoExeDetEntity> getEncerramentoExeDetList() {
		return encerramentoExeDetList;
	}

	public void setEncerramentoExeDetList(
			List<EncerramentoExeDetEntity> encerramentoExeDetList) {
		this.encerramentoExeDetList = encerramentoExeDetList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}