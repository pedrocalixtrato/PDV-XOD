package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_PARAMETROS")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParametrosEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_parametros_id_seq")
	@SequenceGenerator(name = "contabil_parametros_id_seq", sequenceName = "contabil_parametros_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "mascara")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Máscara")
	private String mascara = "";

	@Field
	@Column(name = "niveis")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Níveis")
	private Integer niveis = new Integer(0);

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "informar_conta_por")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Informar conta por")
	private SimNaoEn informarContaPor;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "compartilha_plano_conta")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Compartilha plano de conta")
	private SimNaoEn compartilhaPlanoConta;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "compartilha_historicos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Compartilha históricos")
	private SimNaoEn compartilhaHistoricos;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "altera_lancamento_outro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Altera lançamento outro")
	private SimNaoEn alteraLancamentoOutro;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "historico_obrigatorio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Histórico obrigatório")
	private SimNaoEn historicoObrigatorio;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "permite_lancamento_zerado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Permite lançamento zerado")
	private SimNaoEn permiteLancamentoZerado;

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "gera_informativo_sped")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Gera informativo sped")
	private SimNaoEn geraInformativoSped;

	@Field
	@Column(name = "sped_forma_escrit_diario")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Sped forma escrit diário")
	private String spedFormaEscritDiario = "";

	@Field
	@Column(name = "sped_nome_livro_diario")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Sped nome livro diário")
	private String spedNomeLivroDiario = "";

	@Field
	@Column(name = "assinatura_direita")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Assinatura direita")
	private String assinaturaDireita = "";

	@Field
	@Column(name = "assinatura_esquerda")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Assinatura esquerda")
	private String assinaturaEsquerda = "";

	@Field
	@Column(name = "conta_ativo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta ativo")
	private String contaAtivo = "";

	@Field
	@Column(name = "conta_passivo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta passivo")
	private String contaPassivo = "";

	@Field
	@Column(name = "conta_patrimonio_liquido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta patrimônio líquido")
	private String contaPatrimonioLiquido = "";

	@Field
	@Column(name = "conta_depreciacao_acumulada")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta depreciação acumulada")
	private String contaDepreciacaoAcumulada = "";

	@Field
	@Column(name = "conta_capital_social")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta capital social")
	private String contaCapitalSocial = "";

	@Field
	@Column(name = "conta_resultado_exercicio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta resultado exercício")
	private String contaResultadoExercicio = "";

	@Field
	@Column(name = "conta_prejuizo_acumulado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta prejuízo acumulado")
	private String contaPrejuizoAcumulado = "";

	@Field
	@Column(name = "conta_lucro_acumulado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta lucro acumulado")
	private String contaLucroAcumulado = "";

	@Field
	@Column(name = "conta_titulo_pagar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta título a pagar")
	private String contaTituloPagar = "";

	@Field
	@Column(name = "conta_titulo_receber")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta título a receber")
	private String contaTituloReceber = "";

	@Field
	@Column(name = "conta_juros_passivo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta juros passivo")
	private String contaJurosPassivo = "";

	@Field
	@Column(name = "conta_juros_ativo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta juros ativo")
	private String contaJurosAtivo = "";

	@Field
	@Column(name = "conta_desconto_obtido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta desconto obtido")
	private String contaDescontoObtido = "";

	@Field
	@Column(name = "conta_desconto_concedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta desconto concedido")
	private String contaDescontoConcedido = "";

	@Field
	@Column(name = "conta_cmv")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta cmv")
	private String contaCmv = "";

	@Field
	@Column(name = "conta_venda")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta venda")
	private String contaVenda = "";

	@Field
	@Column(name = "conta_venda_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta venda serviço")
	private String contaVendaServico = "";

	@Field
	@Column(name = "conta_estoque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta estoque")
	private String contaEstoque = "";

	@Field
	@Column(name = "conta_apura_resultado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta apura resultado")
	private String contaApuraResultado = "";

	@Field
	@Column(name = "conta_juros_apropriar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Conta juros apropriar")
	private String contaJurosApropriar = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_hist_padrao_resultado integer,

	@Field
	@Column(name = "id_hist_padrao_resultado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Hist padrão resultado")
	private Integer histPadraoResultado = new Integer(0);

	// id_hist_padrao_lucro integer,

	@Field
	@Column(name = "id_hist_padrao_lucro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Hist padrão lucro")
	private Integer histPadraoLucro = new Integer(0);

	// id_hist_padrao_prejuizo integer,

	@Field
	@Column(name = "id_hist_padrao_prejuizo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Hist padrão prejuízo")
	private Integer histPadraoPrejuizo = new Integer(0);

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
		return getMascara();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public ParametrosEntity() {
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

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = (mascara == null ? "" : mascara.toUpperCase());
	}

	public Integer getNiveis() {
		return niveis;
	}

	public void setNiveis(Integer niveis) {
		this.niveis = (niveis == null ? new Integer(0) : niveis);
	}

	

	public SimNaoEn getInformarContaPor() {
		return informarContaPor;
	}

	public void setInformarContaPor(SimNaoEn informarContaPor) {
		this.informarContaPor = informarContaPor;
	}

	public SimNaoEn getCompartilhaPlanoConta() {
		return compartilhaPlanoConta;
	}

	public void setCompartilhaPlanoConta(SimNaoEn compartilhaPlanoConta) {
		this.compartilhaPlanoConta = compartilhaPlanoConta;
	}

	public SimNaoEn getCompartilhaHistoricos() {
		return compartilhaHistoricos;
	}

	public void setCompartilhaHistoricos(SimNaoEn compartilhaHistoricos) {
		this.compartilhaHistoricos = compartilhaHistoricos;
	}

	public SimNaoEn getAlteraLancamentoOutro() {
		return alteraLancamentoOutro;
	}

	public void setAlteraLancamentoOutro(SimNaoEn alteraLancamentoOutro) {
		this.alteraLancamentoOutro = alteraLancamentoOutro;
	}

	public SimNaoEn getHistoricoObrigatorio() {
		return historicoObrigatorio;
	}

	public void setHistoricoObrigatorio(SimNaoEn historicoObrigatorio) {
		this.historicoObrigatorio = historicoObrigatorio;
	}

	public SimNaoEn getPermiteLancamentoZerado() {
		return permiteLancamentoZerado;
	}

	public void setPermiteLancamentoZerado(SimNaoEn permiteLancamentoZerado) {
		this.permiteLancamentoZerado = permiteLancamentoZerado;
	}

	public SimNaoEn getGeraInformativoSped() {
		return geraInformativoSped;
	}

	public void setGeraInformativoSped(SimNaoEn geraInformativoSped) {
		this.geraInformativoSped = geraInformativoSped;
	}

	public String getSpedFormaEscritDiario() {
		return spedFormaEscritDiario;
	}

	public void setSpedFormaEscritDiario(String spedFormaEscritDiario) {
		this.spedFormaEscritDiario = (spedFormaEscritDiario == null ? ""
				: spedFormaEscritDiario.toUpperCase());
	}

	public String getSpedNomeLivroDiario() {
		return spedNomeLivroDiario;
	}

	public void setSpedNomeLivroDiario(String spedNomeLivroDiario) {
		this.spedNomeLivroDiario = (spedNomeLivroDiario == null ? ""
				: spedNomeLivroDiario.toUpperCase());
	}

	public String getAssinaturaDireita() {
		return assinaturaDireita;
	}

	public void setAssinaturaDireita(String assinaturaDireita) {
		this.assinaturaDireita = (assinaturaDireita == null ? ""
				: assinaturaDireita.toUpperCase());
	}

	public String getAssinaturaEsquerda() {
		return assinaturaEsquerda;
	}

	public void setAssinaturaEsquerda(String assinaturaEsquerda) {
		this.assinaturaEsquerda = (assinaturaEsquerda == null ? ""
				: assinaturaEsquerda.toUpperCase());
	}

	public String getContaAtivo() {
		return contaAtivo;
	}

	public void setContaAtivo(String contaAtivo) {
		this.contaAtivo = (contaAtivo == null ? "" : contaAtivo.toUpperCase());
	}

	public String getContaPassivo() {
		return contaPassivo;
	}

	public void setContaPassivo(String contaPassivo) {
		this.contaPassivo = (contaPassivo == null ? "" : contaPassivo
				.toUpperCase());
	}

	public String getContaPatrimonioLiquido() {
		return contaPatrimonioLiquido;
	}

	public void setContaPatrimonioLiquido(String contaPatrimonioLiquido) {
		this.contaPatrimonioLiquido = (contaPatrimonioLiquido == null ? ""
				: contaPatrimonioLiquido.toUpperCase());
	}

	public String getContaDepreciacaoAcumulada() {
		return contaDepreciacaoAcumulada;
	}

	public void setContaDepreciacaoAcumulada(String contaDepreciacaoAcumulada) {
		this.contaDepreciacaoAcumulada = (contaDepreciacaoAcumulada == null ? ""
				: contaDepreciacaoAcumulada.toUpperCase());
	}

	public String getContaCapitalSocial() {
		return contaCapitalSocial;
	}

	public void setContaCapitalSocial(String contaCapitalSocial) {
		this.contaCapitalSocial = (contaCapitalSocial == null ? ""
				: contaCapitalSocial.toUpperCase());
	}

	public String getContaResultadoExercicio() {
		return contaResultadoExercicio;
	}

	public void setContaResultadoExercicio(String contaResultadoExercicio) {
		this.contaResultadoExercicio = (contaResultadoExercicio == null ? ""
				: contaResultadoExercicio.toUpperCase());
	}

	public String getContaPrejuizoAcumulado() {
		return contaPrejuizoAcumulado;
	}

	public void setContaPrejuizoAcumulado(String contaPrejuizoAcumulado) {
		this.contaPrejuizoAcumulado = (contaPrejuizoAcumulado == null ? ""
				: contaPrejuizoAcumulado.toUpperCase());
	}

	public String getContaLucroAcumulado() {
		return contaLucroAcumulado;
	}

	public void setContaLucroAcumulado(String contaLucroAcumulado) {
		this.contaLucroAcumulado = (contaLucroAcumulado == null ? ""
				: contaLucroAcumulado.toUpperCase());
	}

	public String getContaTituloPagar() {
		return contaTituloPagar;
	}

	public void setContaTituloPagar(String contaTituloPagar) {
		this.contaTituloPagar = (contaTituloPagar == null ? ""
				: contaTituloPagar.toUpperCase());
	}

	public String getContaTituloReceber() {
		return contaTituloReceber;
	}

	public void setContaTituloReceber(String contaTituloReceber) {
		this.contaTituloReceber = (contaTituloReceber == null ? ""
				: contaTituloReceber.toUpperCase());
	}

	public String getContaJurosPassivo() {
		return contaJurosPassivo;
	}

	public void setContaJurosPassivo(String contaJurosPassivo) {
		this.contaJurosPassivo = (contaJurosPassivo == null ? ""
				: contaJurosPassivo.toUpperCase());
	}

	public String getContaJurosAtivo() {
		return contaJurosAtivo;
	}

	public void setContaJurosAtivo(String contaJurosAtivo) {
		this.contaJurosAtivo = (contaJurosAtivo == null ? "" : contaJurosAtivo
				.toUpperCase());
	}

	public String getContaDescontoObtido() {
		return contaDescontoObtido;
	}

	public void setContaDescontoObtido(String contaDescontoObtido) {
		this.contaDescontoObtido = (contaDescontoObtido == null ? ""
				: contaDescontoObtido.toUpperCase());
	}

	public String getContaDescontoConcedido() {
		return contaDescontoConcedido;
	}

	public void setContaDescontoConcedido(String contaDescontoConcedido) {
		this.contaDescontoConcedido = (contaDescontoConcedido == null ? ""
				: contaDescontoConcedido.toUpperCase());
	}

	public String getContaCmv() {
		return contaCmv;
	}

	public void setContaCmv(String contaCmv) {
		this.contaCmv = (contaCmv == null ? "" : contaCmv.toUpperCase());
	}

	public String getContaVenda() {
		return contaVenda;
	}

	public void setContaVenda(String contaVenda) {
		this.contaVenda = (contaVenda == null ? "" : contaVenda.toUpperCase());
	}

	public String getContaVendaServico() {
		return contaVendaServico;
	}

	public void setContaVendaServico(String contaVendaServico) {
		this.contaVendaServico = (contaVendaServico == null ? ""
				: contaVendaServico.toUpperCase());
	}

	public String getContaEstoque() {
		return contaEstoque;
	}

	public void setContaEstoque(String contaEstoque) {
		this.contaEstoque = (contaEstoque == null ? "" : contaEstoque
				.toUpperCase());
	}

	public String getContaApuraResultado() {
		return contaApuraResultado;
	}

	public void setContaApuraResultado(String contaApuraResultado) {
		this.contaApuraResultado = (contaApuraResultado == null ? ""
				: contaApuraResultado.toUpperCase());
	}

	public String getContaJurosApropriar() {
		return contaJurosApropriar;
	}

	public void setContaJurosApropriar(String contaJurosApropriar) {
		this.contaJurosApropriar = (contaJurosApropriar == null ? ""
				: contaJurosApropriar.toUpperCase());
	}

	public Integer getHistPadraoResultado() {
		return histPadraoResultado;
	}

	public void setHistPadraoResultado(Integer histPadraoResultado) {
		this.histPadraoResultado = (histPadraoResultado == null ? new Integer(0)
				: histPadraoResultado);
	}

	public Integer getHistPadraoLucro() {
		return histPadraoLucro;
	}

	public void setHistPadraoLucro(Integer histPadraoLucro) {
		this.histPadraoLucro = (histPadraoLucro == null ? new Integer(0)
				: histPadraoLucro);
	}

	public Integer getHistPadraoPrejuizo() {
		return histPadraoPrejuizo;
	}

	public void setHistPadraoPrejuizo(Integer histPadraoPrejuizo) {
		this.histPadraoPrejuizo = (histPadraoPrejuizo == null ? new Integer(0)
				: histPadraoPrejuizo);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}