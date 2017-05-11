package dc.entidade.contabilidade.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.nfe.NfeCabecalhoEntity;

/**
 * 
 * 
 */

// @Entity
// @Table(name = "CONTABIL_LANCA_PROGRAMADO_DET")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoProgramadoDetEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lanca_programado_det_id_seq")
	@SequenceGenerator(name = "contabil_lanca_programado_det_id_seq", sequenceName = "contabil_lanca_programado_det_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "historico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição do histórico")
	private String descricaoHistorico = "";

	@Field
	@Column(name = "valor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor")
	private BigDecimal valor = new BigDecimal(0);

	@Field
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Tipo")
	private String tipo = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_lanca_programado_cab", nullable = false)
	@Caption("Lançamento programado cab")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LancamentoProgramadoCabEntity lancamentoProgramadoCab;

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaEntity conta;

	@ManyToOne
	@JoinColumn(name = "id_contabil_historico", nullable = false)
	@Caption("Histórico")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private HistoricoEntity historico;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * Módulo: NFE
	 */

	@OneToMany(mappedBy = "contabilLancamentoProgramadoDet", fetch = FetchType.LAZY)
	private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * CONSTRUTOR
	 */

	public LancamentoProgramadoDetEntity() {
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

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = (descricaoHistorico == null ? ""
				: descricaoHistorico.toUpperCase());
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = (valor == null ? new BigDecimal(0) : valor);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public LancamentoProgramadoCabEntity getLancamentoProgramadoCab() {
		return lancamentoProgramadoCab;
	}

	public void setLancamentoProgramadoCab(
			LancamentoProgramadoCabEntity lancamentoProgramadoCab) {
		this.lancamentoProgramadoCab = lancamentoProgramadoCab;
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	public HistoricoEntity getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoEntity historico) {
		this.historico = historico;
	}

	public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
		return nfeCabecalhoList;
	}

	public void setNfeCabecalhoList(List<NfeCabecalhoEntity> nfeCabecalhoList) {
		this.nfeCabecalhoList = nfeCabecalhoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}