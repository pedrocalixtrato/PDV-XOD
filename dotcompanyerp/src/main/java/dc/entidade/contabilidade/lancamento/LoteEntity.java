package dc.entidade.contabilidade.lancamento;

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
@Table(name = "CONTABIL_LOTE")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LoteEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lote_id_seq")
	@SequenceGenerator(name = "contabil_lote_id_seq", sequenceName = "contabil_lote_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao = "";

	@Field
	@Column(name = "liberado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Liberado")
	private String liberado = "";

	@Column(name = "data_inclusao")
	@Caption(value = "Data da inclusão")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataInclusao;

	@Column(name = "data_liberacao")
	@Caption(value = "Data da liberacao")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataLiberacao;

	@Field
	@Column(name = "programado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Programado")
	private String programado = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	private List<LancamentoCabecalhoEntity> lancamentoCabecalhoList;

	//@OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
	//private List<LancamentoProgramadoCabEntity> lancamentoProgramadoCabList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getDescricao();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public LoteEntity() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getLiberado() {
		return liberado;
	}

	public void setLiberado(String liberado) {
		this.liberado = (liberado == null ? "" : liberado.toUpperCase());
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public String getProgramado() {
		return programado;
	}

	public void setProgramado(String programado) {
		this.programado = (programado == null ? "" : programado.toUpperCase());
	}

	public List<LancamentoCabecalhoEntity> getLancamentoCabecalhoList() {
		return lancamentoCabecalhoList;
	}

	public void setLancamentoCabecalhoList(
			List<LancamentoCabecalhoEntity> lancamentoCabecalhoList) {
		this.lancamentoCabecalhoList = lancamentoCabecalhoList;
	}

	//public List<LancamentoProgramadoCabEntity> getLancamentoProgramadoCabList() {
	//	return lancamentoProgramadoCabList;
	//}

	//public void setLancamentoProgramadoCabList(
	//		List<LancamentoProgramadoCabEntity> lancamentoProgramadoCabList) {
	//	this.lancamentoProgramadoCabList = lancamentoProgramadoCabList;
	//}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}