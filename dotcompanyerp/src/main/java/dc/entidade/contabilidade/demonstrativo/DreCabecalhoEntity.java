package dc.entidade.contabilidade.demonstrativo;

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
import javax.persistence.Transient;
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
@Table(name = "CONTABIL_DRE_CABECALHO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class DreCabecalhoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_dre_cabecalho_id_seq")
	@SequenceGenerator(name = "contabil_dre_cabecalho_id_seq", sequenceName = "contabil_dre_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
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
	@Column(name = "padrao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Padrão")
	private String padrao = "";

	@Field
	@Column(name = "periodo_inicial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Período inicial")
	private String periodoInicial = "";

	@Field
	@Column(name = "periodo_final")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Período final")
	private String periodoFinal = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "dreCabecalho", fetch = FetchType.LAZY)
	private List<DreDetalheEntity> dreDetalheList;

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

	public DreCabecalhoEntity() {
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

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = (padrao == null ? "" : padrao.toUpperCase());
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = (periodoInicial == null ? "" : periodoInicial
				.toUpperCase());
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = (periodoFinal == null ? "" : periodoFinal
				.toUpperCase());
	}

	public List<DreDetalheEntity> getDreDetalheList() {
		return dreDetalheList;
	}

	public void setDreDetalheList(List<DreDetalheEntity> dreDetalheList) {
		this.dreDetalheList = dreDetalheList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}