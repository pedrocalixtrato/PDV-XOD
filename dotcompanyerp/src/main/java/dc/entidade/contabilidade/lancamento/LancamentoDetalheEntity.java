package dc.entidade.contabilidade.lancamento;

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
import javax.persistence.Transient;
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

/**
 * 
 * 
 */

@Entity(name = "contabilidadeLancamentoDetalheEntity")
@Table(name = "CONTABIL_LANCAMENTO_DETALHE")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lancamento_detalhe_id_seq")
	@SequenceGenerator(name = "contabil_lancamento_detalhe_id_seq", sequenceName = "contabil_lancamento_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "historico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Histórico")
	private String descricaoHistorico = "";

	@Field
	@Column(name = "valor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor")
	private Double valor = new Double(0.0);

	// Nao é esse tipo q foi importado o certo, é porque não sei qual as informações do Tipo de Lancmaneot Cabeçalho é o correto,
		// pois preciso dos cds do Financeiro para revisar.
		// Só coloquei esse Tipo para conseguir passar para o proximo validate no banco de Dados.
	/*@Enumerated(EnumType.STRING)
	@Field
	@Caption()
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoType tipo;*/

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	// id_contabil_conta integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaEntity conta;

	// id_contabil_historico integer,

	@ManyToOne
	@JoinColumn(name = "id_contabil_historico", nullable = false)
	@Caption("Histórico")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private HistoricoEntity historico;

	// id_contabil_lancamento_cab integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_lancamento_cab", nullable = false)
	@Caption("Lançamento cabeçalho")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LancamentoCabecalhoEntity lancamentoCabecalho;

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
		return getDescricaoHistorico();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public LancamentoDetalheEntity() {
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = (valor == null ? new Double(0.0) : valor);
	}

	/*public TipoType getTipo() {
		return tipo;
	}

	public void setTipo(TipoType tipo) {
		this.tipo = (tipo);
	}*/

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

	public LancamentoCabecalhoEntity getLancamentoCabecalho() {
		return lancamentoCabecalho;
	}

	public void setLancamentoCabecalho(
			LancamentoCabecalhoEntity lancamentoCabecalho) {
		this.lancamentoCabecalho = lancamentoCabecalho;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}