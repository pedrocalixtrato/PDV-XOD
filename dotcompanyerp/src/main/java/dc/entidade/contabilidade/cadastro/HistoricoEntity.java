package dc.entidade.contabilidade.cadastro;

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
import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_HISTORICO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class HistoricoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_historico_id_seq")
	@SequenceGenerator(name = "contabil_historico_id_seq", sequenceName = "contabil_historico_id_seq", allocationSize = 1, initialValue = 0)
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
	@Column(name = "historico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Histórico")
	private String historico = "";

	@Field
	@Column(name = "pede_complemento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Pede complemento")
	private String pedeComplemento = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "historico", fetch = FetchType.LAZY)
	private List<LancamentoDetalheEntity> lancamentoDetalheList;

	//@OneToMany(mappedBy = "historico", fetch = FetchType.LAZY)
	//private List<LancamentoProgramadoDetEntity> lancamentoProgramadoDetEntityList;

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

	public HistoricoEntity() {
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

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = (historico == null ? "" : historico.toUpperCase());
	}

	public String getPedeComplemento() {
		return pedeComplemento;
	}

	public void setPedeComplemento(String pedeComplemento) {
		this.pedeComplemento = (pedeComplemento == null ? "" : pedeComplemento
				.toUpperCase());
	}

	public List<LancamentoDetalheEntity> getLancamentoDetalheList() {
		return lancamentoDetalheList;
	}

	public void setLancamentoDetalheList(
			List<LancamentoDetalheEntity> lancamentoDetalheList) {
		this.lancamentoDetalheList = lancamentoDetalheList;
	}

	//public List<LancamentoProgramadoDetEntity> getLancamentoProgramadoDetEntityList() {
	//	return lancamentoProgramadoDetEntityList;
	//}

	//public void setLancamentoProgramadoDetEntityList(
	//		List<LancamentoProgramadoDetEntity> lancamentoProgramadoDetEntityList) {
	//	this.lancamentoProgramadoDetEntityList = lancamentoProgramadoDetEntityList;
	//}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}