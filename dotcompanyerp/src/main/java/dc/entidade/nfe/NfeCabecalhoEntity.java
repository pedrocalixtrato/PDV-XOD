package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeCabecalhoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_cabecalho_id_seq")
	@SequenceGenerator(name = "nfe_cabecalho_id_seq", sequenceName = "nfe_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "codigo_numerico")
	@Caption(value = "Código numérico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoNumerico = "";

	@Field
	@Column(name = "natureza_operacao")
	@Caption(value = "Natureza da operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String naturezaOperacao = "VENDA DE MERCADORIA";

	@Field
	@Column(name = "indicador_forma_pagamento")
	@Caption(value = "Indicador da forma de pagamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer indicadorFormaPagamento;

	@Field
	@Column(name = "codigo_modelo")
	@Caption(value = "Código do modelo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoModelo = "55";

	@Field
	@Column(name = "serie")
	@Caption(value = "Série")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String serie = "888";

	@Field
	@Column(name = "numero")
	@Caption(value = "Número")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero;

	@Field
	@Column(name = "data__hora_emissao")
	@Caption(value = "Data Hora de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataHoraEmissao;

	@Field
	@Column(name = "data_hora_entrada_saida")
	@Caption(value = "Data Hora de entrada / saída")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataHoraEntradaSaida;

	@Field
	@Column(name = "local_destino")
	@Caption(value = "Local Destino")
	private Integer localDestino;

	@Field
	@Column(name = "tipo_operacao")
	@Caption(value = "Tipo de operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer tipoOperacao = 1;

	@Field
	@Column(name = "codigo_municipio")
	@Caption(value = "Código do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoMunicipio = 5300108;

	@Field
	@Column(name = "formato_impressao_danfe")
	@Caption(value = "Formato de impressão do DANFE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer formatoImpressaoDanfe;

	@Field
	@Column(name = "tipo_emissao")
	@Caption(value = "Tipo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer tipoEmissao = 1;

	@Field
	@Column(name = "chave_acesso")
	@Caption(value = "Chave de acesso")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String chaveAcesso = "";

	@Field
	@Column(name = "digito_chave_acesso")
	@Caption(value = "Digíto da chave de acesso")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String digitoChaveAcesso = "9";

	@Field
	@Column(name = "ambiente")
	@Caption(value = "Ambiente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ambiente = 2;

	@Field
	@Column(name = "finalidade_emissao")
	@Caption(value = "Finalidade de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer finalidadeEmissao = 1;

	@Field
	@Column(name = "processo_emissao")
	@Caption(value = "Processo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer processoEmissao;

	@Field
	@Column(name = "versao_processo_emissao")
	@Caption(value = "Versão do processo de emissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String versaoProcessoEmissao = "100";

	@Field
	@Column(name = "data_entrada_contingencia")
	@Caption(value = "Data da entrada da contingência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntradaContingencia;

	@Field
	@Column(name = "justificativa_contingencia")
	@Caption(value = "Justificativa da contingência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String justificativaContingencia = "";

	@Field
	@Column(name = "base_calculo_icms")
	@Caption(value = "Base de cálculo do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcms = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms")
	@Caption(value = "Valor do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcms = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_icms_st")
	@Caption(value = "Base de cálculo do ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms_st")
	@Caption(value = "Valor do ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_total_produtos")
	@Caption(value = "Valor total dos produtos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalProdutos = new BigDecimal(0);

	@Field
	@Column(name = "valor_frete")
	@Caption(value = "Valor do frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorFrete = new BigDecimal(0);

	@Field
	@Column(name = "valor_seguro")
	@Caption(value = "Valor do seguro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSeguro = new BigDecimal(0);

	@Field
	@Column(name = "valor_desconto")
	@Caption(value = "Valor do desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Column(name = "valor_imposto_importacao")
	@Caption(value = "Valor do imposto de importação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorImpostoImportacao = new BigDecimal(0);

	@Field
	@Column(name = "valor_ipi")
	@Caption(value = "Valor do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpi = new BigDecimal(0);

	@Field
	@Column(name = "valor_pis")
	@Caption(value = "Valor do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPis = new BigDecimal(0);

	@Field
	@Column(name = "valor_cofins")
	@Caption(value = "Valor do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCofins = new BigDecimal(0);

	@Field
	@Column(name = "valor_despesas_acessorias")
	@Caption(value = "Valor das despesas acessórias")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDespesasAcessorias = new BigDecimal(0);

	@Field
	@Column(name = "valor_total")
	@Caption(value = "Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Field
	@Column(name = "valor_servicos")
	@Caption(value = "Valor dos serviços")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorServicos = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_issqn")
	@Caption(value = "Base de cálculo do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_issqn")
	@Caption(value = "Valor do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_pis_issqn")
	@Caption(value = "Valor do PIS / ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPisIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_cofins_issqn")
	@Caption(value = "Valor do COFINS / ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCofinsIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_pis")
	@Caption(value = "Valor retido do PIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoPis = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_cofins")
	@Caption(value = "Valor retido do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoCofins = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_csll")
	@Caption(value = "Valor retido do CSLL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoCsll = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_irrf")
	@Caption(value = "Base de cálculo do IRRF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIrrf = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_irrf")
	@Caption(value = "Valor retido do IRRF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoIrrf = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_previdencia")
	@Caption(value = "Base de cálculo da previdência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoPrevidencia = new BigDecimal(0);

	@Field
	@Column(name = "valor_retido_previdencia")
	@Caption(value = "Valor retido da previdência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetidoPrevidencia = new BigDecimal(0);

	@Field
	@Column(name = "comex_uf_embarque")
	@Caption(value = "Comex UF de embarque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comexUfEmbarque = "";

	@Field
	@Column(name = "comex_local_embarque")
	@Caption(value = "Comex local de embarque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comexLocalEmbarque = "";

	@Field
	@Column(name = "compra_nota_empenho")
	@Caption(value = "Compra da nota de empenho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraNotaEmpenho = "";

	@Field
	@Column(name = "compra_pedido")
	@Caption(value = "Compra do pedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraPedido = "";

	@Field
	@Column(name = "compra_contrato")
	@Caption(value = "Compra do contrato")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String compraContrato = "";

	@Field
	@Column(name = "informacoes_add_fisco")
	@Caption(value = "Informações add fisco")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAddFisco = "";

	@Field
	@Column(name = "informacoes_add_contribuinte")
	@Caption(value = "Informações add contribuinte")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAddContribuinte = "";
	
	@Field
	@Column(name = "status_nota")
	@Caption(value = "Status da nota")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String statusNota = "0";

	@Field
	@Column(name = "uf_emitente")
	@Caption(value = "UF emitente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ufEmitente = new Integer(0);

	@Field
	@Column(name = "contato_email")
	@Caption(value = "E-mail do contato")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contatoEmail = "";

	@Field
	@Column(name = "consumidor_operacao")
	@Caption(value = "Consumidor Operação")
	private Integer consumidorOperacao;
	
	@Field
	@Column(name = "consumidor_presenca")
	@Caption(value = "Consumidor Presença")
	private Integer consumidorPresenca;
	
	@Field
	@Column(name = "valor_icms_desonerado")
	@Caption(value = "Valor do ICMS Desonerado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsDesonerado = new BigDecimal(0);
	
	@Field
	@Column(name = "data_prestacao_servico")
	@Caption(value = "Data Prestação Serviço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataPrestacaoServico;
	
	@Field
	@Column(name = "valor_deducao_issqn")
	@Caption(value = "Valor Dedução ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDeducaoIssqn = new BigDecimal(0);
	
	@Field
	@Column(name = "outras_retencoes_issqn")
	@Caption(value = "Outras Retenções ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal outrasRetencoesIssqn = new BigDecimal(0);
	
	@Field
	@Column(name = "desconto_incondicionado_issqn")
	@Caption(value = "Desconto Incondicionado ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal descontoIncondicionadoIssqn = new BigDecimal(0);
	
	@Field
	@Column(name = "desconto_condicionado_issqn")
	@Caption(value = "Desconto Condicionado ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal descontoCondicionadoIssqn = new BigDecimal(0);
	
	@Field
	@Column(name = "total_retencao_issqn")
	@Caption(value = "Total Retenção ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal totalRetencaoIssqn = new BigDecimal(0);
	
	@Field
	@Column(name = "regime_especial_tributacao")
	@Caption(value = "Regime Especial Tributação")
	private Integer regimeEspecialTributacao;
	
	@Field
	@Column(name = "comex_local_despacho")
	@Caption(value = "Comex Local de Despacho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comexLocalDespacho = "";
	
	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Column(name = "id_adiantamento")
	@Caption(value = "Adiantamento")
	private Integer adiantamento;

	@ManyToOne
	@JoinColumn(name = "id_tribut_operacao_fiscal")
	@Caption(value = "Tributário - Operação fiscal")
	private OperacaoFiscalEntity tributOperacaoFiscal;

	@Column(name = "id_venda_cabecalho")
	@Caption(value = "Venda - Cabeçalho")
	private Integer vendaCabecalho;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	@Caption(value = "Fornecedor")
	private FornecedorEntity fornecedor;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@Caption(value = "Cliente")
	private ClienteEntity cliente;

	@Field
	@Column(name = "id_contabil_lanca_programado_det")
	@Caption(value = "Contábil - Lança programado")
	private Integer contabilLancamentoProgramadoDet;

	@Field
	@Column(name = "id_contabil_dre_vinculo")
	@Caption(value = "Contábil - Dre vínculo")
	private Integer contabilDreVinculo;

	@Field
	@Column(name = "id_view_contrato_dados_contratante")
	@Caption(value = "View contrato dados contratante")
	private Integer viewContratoDadosContratante;

	@OneToOne(mappedBy = "nfeCabecalho", cascade = CascadeType.ALL)
	private NfeDestinatarioEntity nfeDestinatario;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "nfeCabecalho", fetch = FetchType.LAZY)
	private List<NfeDetalheEntity> nfeDetalheList;

	/**
	 * TRANSIENT
	 */

	@Transient
	private NfeDetalheEntity nfeDetalhe;

	/**
	 * CONSTRUTOR
	 */

	public NfeCabecalhoEntity() {
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

	public String getCodigoNumerico() {
		return codigoNumerico;
	}

	public void setCodigoNumerico(String codigoNumerico) {
		this.codigoNumerico = (codigoNumerico == null ? "".trim() : codigoNumerico.toUpperCase().trim());
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = (naturezaOperacao == null ? "VENDA DE MERCADORIA"
				.trim() : naturezaOperacao.toUpperCase().trim());
	}

	public Integer getIndicadorFormaPagamento() {
		return indicadorFormaPagamento;
	}

	public void setIndicadorFormaPagamento(Integer indicadorFormaPagamento) {
		this.indicadorFormaPagamento = indicadorFormaPagamento;
	}

	public String getCodigoModelo() {
		return codigoModelo;
	}

	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = (codigoModelo == null ? "55".trim() : codigoModelo
				.toUpperCase().trim());
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = (serie == null ? "888".trim() : serie.toUpperCase().trim());
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataHoraEmissao() {
		return dataHoraEmissao;
	}

	public void setDataHoraEmissao(Date dataHoraEmissao) {
		this.dataHoraEmissao = dataHoraEmissao;
	}

	public Date getDataHoraEntradaSaida() {
		return dataHoraEntradaSaida;
	}

	public void setDataHoraEntradaSaida(Date dataHoraEntradaSaida) {
		this.dataHoraEntradaSaida = dataHoraEntradaSaida;
	}

	public Integer getLocalDestino() {
		return localDestino;
	}

	public void setLocalDestino(Integer localDestino) {
		this.localDestino = localDestino;
	}

	public Integer getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = (tipoOperacao == null ? 1 : tipoOperacao);
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = (codigoMunicipio == null ? 5300108 : codigoMunicipio);
	}

	public Integer getFormatoImpressaoDanfe() {
		return formatoImpressaoDanfe;
	}

	public void setFormatoImpressaoDanfe(Integer formatoImpressaoDanfe) {
		this.formatoImpressaoDanfe = formatoImpressaoDanfe;
	}

	public Integer getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(Integer tipoEmissao) {
		this.tipoEmissao = (tipoEmissao == null ? 1 : tipoEmissao);
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = (chaveAcesso == null ? "".trim() : chaveAcesso
				.toUpperCase().trim());
	}

	public String getDigitoChaveAcesso() {
		return digitoChaveAcesso;
	}

	public void setDigitoChaveAcesso(String digitoChaveAcesso) {
		this.digitoChaveAcesso = (digitoChaveAcesso == null ? "9".trim() : digitoChaveAcesso.toUpperCase().trim());
	}

	public Integer getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Integer ambiente) {
		this.ambiente = (ambiente == null ? 2 : ambiente);
	}

	public Integer getFinalidadeEmissao() {
		return finalidadeEmissao;
	}

	public void setFinalidadeEmissao(Integer finalidadeEmissao) {
		this.finalidadeEmissao = (finalidadeEmissao == null ? 1	: finalidadeEmissao);
	}

	public Integer getProcessoEmissao() {
		return processoEmissao;
	}

	public void setProcessoEmissao(Integer processoEmissao) {
		this.processoEmissao = processoEmissao;
	}

	public String getVersaoProcessoEmissao() {
		return versaoProcessoEmissao;
	}

	public void setVersaoProcessoEmissao(String versaoProcessoEmissao) {
		this.versaoProcessoEmissao = (versaoProcessoEmissao == null ? "100"
				.trim() : versaoProcessoEmissao.toUpperCase().trim());
	}

	public Date getDataEntradaContingencia() {
		return dataEntradaContingencia;
	}

	public void setDataEntradaContingencia(Date dataEntradaContingencia) {
		this.dataEntradaContingencia = dataEntradaContingencia;
	}

	public String getJustificativaContingencia() {
		return justificativaContingencia;
	}

	public void setJustificativaContingencia(String justificativaContingencia) {
		this.justificativaContingencia = (justificativaContingencia == null ? ""
				.trim() : justificativaContingencia.toUpperCase().trim());
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = (baseCalculoIcms == null ? new BigDecimal(0)
				: baseCalculoIcms);
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = (valorIcms == null ? new BigDecimal(0) : valorIcms);
	}

	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}

	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = (baseCalculoIcmsSt == null ? new BigDecimal(0)
				: baseCalculoIcmsSt);
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = (valorIcmsSt == null ? new BigDecimal(0)
				: valorIcmsSt);
	}

	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;
	}

	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = (valorTotalProdutos == null ? new BigDecimal(
				0) : valorTotalProdutos);
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = (valorFrete == null ? new BigDecimal(0) : valorFrete);
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = (valorSeguro == null ? new BigDecimal(0)
				: valorSeguro);
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = (valorDesconto == null ? new BigDecimal(0)
				: valorDesconto);
	}

	public BigDecimal getValorImpostoImportacao() {
		return valorImpostoImportacao;
	}

	public void setValorImpostoImportacao(BigDecimal valorImpostoImportacao) {
		this.valorImpostoImportacao = (valorImpostoImportacao == null ? new BigDecimal(
				0) : valorImpostoImportacao);
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = (valorIpi == null ? new BigDecimal(0) : valorIpi);
	}

	public BigDecimal getValorPis() {
		return valorPis;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = (valorPis == null ? new BigDecimal(0) : valorPis);
	}

	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = (valorCofins == null ? new BigDecimal(0)
				: valorCofins);
	}

	public BigDecimal getValorDespesasAcessorias() {
		return valorDespesasAcessorias;
	}

	public void setValorDespesasAcessorias(BigDecimal valorDespesasAcessorias) {
		this.valorDespesasAcessorias = (valorDespesasAcessorias == null ? new BigDecimal(
				0) : valorDespesasAcessorias);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = (valorTotal == null ? new BigDecimal(0) : valorTotal);
	}

	public BigDecimal getValorServicos() {
		return valorServicos;
	}

	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = (valorServicos == null ? new BigDecimal(0)
				: valorServicos);
	}

	public BigDecimal getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
		this.baseCalculoIssqn = (baseCalculoIssqn == null ? new BigDecimal(0)
				: baseCalculoIssqn);
	}

	public BigDecimal getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(BigDecimal valorIssqn) {
		this.valorIssqn = (valorIssqn == null ? new BigDecimal(0) : valorIssqn);
	}

	public BigDecimal getValorPisIssqn() {
		return valorPisIssqn;
	}

	public void setValorPisIssqn(BigDecimal valorPisIssqn) {
		this.valorPisIssqn = (valorPisIssqn == null ? new BigDecimal(0)
				: valorPisIssqn);
	}

	public BigDecimal getValorCofinsIssqn() {
		return valorCofinsIssqn;
	}

	public void setValorCofinsIssqn(BigDecimal valorCofinsIssqn) {
		this.valorCofinsIssqn = (valorCofinsIssqn == null ? new BigDecimal(0)
				: valorCofinsIssqn);
	}

	public BigDecimal getValorRetidoPis() {
		return valorRetidoPis;
	}

	public void setValorRetidoPis(BigDecimal valorRetidoPis) {
		this.valorRetidoPis = (valorRetidoPis == null ? new BigDecimal(0)
				: valorRetidoPis);
	}

	public BigDecimal getValorRetidoCofins() {
		return valorRetidoCofins;
	}

	public void setValorRetidoCofins(BigDecimal valorRetidoCofins) {
		this.valorRetidoCofins = (valorRetidoCofins == null ? new BigDecimal(0)
				: valorRetidoCofins);
	}

	public BigDecimal getValorRetidoCsll() {
		return valorRetidoCsll;
	}

	public void setValorRetidoCsll(BigDecimal valorRetidoCsll) {
		this.valorRetidoCsll = (valorRetidoCsll == null ? new BigDecimal(0)
				: valorRetidoCsll);
	}

	public BigDecimal getBaseCalculoIrrf() {
		return baseCalculoIrrf;
	}

	public void setBaseCalculoIrrf(BigDecimal baseCalculoIrrf) {
		this.baseCalculoIrrf = (baseCalculoIrrf == null ? new BigDecimal(0)
				: baseCalculoIrrf);
	}

	public BigDecimal getValorRetidoIrrf() {
		return valorRetidoIrrf;
	}

	public void setValorRetidoIrrf(BigDecimal valorRetidoIrrf) {
		this.valorRetidoIrrf = (valorRetidoIrrf == null ? new BigDecimal(0)
				: valorRetidoIrrf);
	}

	public BigDecimal getBaseCalculoPrevidencia() {
		return baseCalculoPrevidencia;
	}

	public void setBaseCalculoPrevidencia(BigDecimal baseCalculoPrevidencia) {
		this.baseCalculoPrevidencia = (baseCalculoPrevidencia == null ? new BigDecimal(
				0) : baseCalculoPrevidencia);
	}

	public BigDecimal getValorRetidoPrevidencia() {
		return valorRetidoPrevidencia;
	}

	public void setValorRetidoPrevidencia(BigDecimal valorRetidoPrevidencia) {
		this.valorRetidoPrevidencia = (valorRetidoPrevidencia == null ? new BigDecimal(
				0) : valorRetidoPrevidencia);
	}

	public String getComexUfEmbarque() {
		return comexUfEmbarque;
	}

	public void setComexUfEmbarque(String comexUfEmbarque) {
		this.comexUfEmbarque = (comexUfEmbarque == null ? "".trim()
				: comexUfEmbarque.toUpperCase().trim());
	}

	public String getComexLocalEmbarque() {
		return comexLocalEmbarque;
	}

	public void setComexLocalEmbarque(String comexLocalEmbarque) {
		this.comexLocalEmbarque = (comexLocalEmbarque == null ? "".trim()
				: comexLocalEmbarque.toUpperCase().trim());
	}

	public String getCompraNotaEmpenho() {
		return compraNotaEmpenho;
	}

	public void setCompraNotaEmpenho(String compraNotaEmpenho) {
		this.compraNotaEmpenho = (compraNotaEmpenho == null ? "".trim()
				: compraNotaEmpenho.toUpperCase().trim());
	}

	public String getCompraPedido() {
		return compraPedido;
	}

	public void setCompraPedido(String compraPedido) {
		this.compraPedido = (compraPedido == null ? "".trim() : compraPedido
				.toUpperCase().trim());
	}

	public String getCompraContrato() {
		return compraContrato;
	}

	public void setCompraContrato(String compraContrato) {
		this.compraContrato = (compraContrato == null ? "".trim()
				: compraContrato.toUpperCase().trim());
	}

	public String getInformacoesAddFisco() {
		return informacoesAddFisco;
	}

	public void setInformacoesAddFisco(String informacoesAddFisco) {
		this.informacoesAddFisco = (informacoesAddFisco == null ? "".trim()
				: informacoesAddFisco.toUpperCase().trim());
	}

	public String getInformacoesAddContribuinte() {
		return informacoesAddContribuinte;
	}

	public void setInformacoesAddContribuinte(String informacoesAddContribuinte) {
		this.informacoesAddContribuinte = (informacoesAddContribuinte == null ? ""
				.trim() : informacoesAddContribuinte.toUpperCase().trim());
	}

	public String getStatusNota() {
		return statusNota;
	}

	public void setStatusNota(String statusNota) {
		this.statusNota = (statusNota == null ? "0".trim() : statusNota
				.toUpperCase().trim());
	}
	
	public Integer getConsumidorOperacao() {
		return consumidorOperacao;
	}

	public void setConsumidorOperacao(Integer consumidorOperacao) {
		this.consumidorOperacao = consumidorOperacao;
	}

	public Integer getConsumidorPresenca() {
		return consumidorPresenca;
	}

	public void setConsumidorPresenca(Integer consumidorPresenca) {
		this.consumidorPresenca = consumidorPresenca;
	}

	public Integer getUfEmitente() {
		return ufEmitente;
	}

	public void setUfEmitente(Integer ufEmitente) {
		this.ufEmitente = (ufEmitente == null ? new Integer(0) : ufEmitente);
	}

	public Integer getAdiantamento() {
		return adiantamento;
	}

	public void setAdiantamento(Integer adiantamento) {
		this.adiantamento = (adiantamento == null ? new Integer(0)
				: adiantamento);
	}
	
	public String getComexLocalDespacho() {
		return comexLocalDespacho;
	}

	public void setComexLocalDespacho(String comexLocalDespacho) {
		this.comexLocalDespacho = (comexLocalDespacho == null ? "".trim() : comexLocalDespacho.toUpperCase().trim());
	}

	public OperacaoFiscalEntity getTributOperacaoFiscal() {
		return tributOperacaoFiscal;
	}

	public void setTributOperacaoFiscal(OperacaoFiscalEntity tributOperacaoFiscal) {
		this.tributOperacaoFiscal = tributOperacaoFiscal;
	}

	public Integer getVendaCabecalho() {
		return vendaCabecalho;
	}

	public void setVendaCabecalho(Integer vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public Integer getContabilLancamentoProgramadoDet() {
		return contabilLancamentoProgramadoDet;
	}

	public void setContabilLancamentoProgramadoDet(
			Integer contabilLancamentoProgramadoDet) {
		this.contabilLancamentoProgramadoDet = (contabilLancamentoProgramadoDet == null ? new Integer(
				0) : contabilLancamentoProgramadoDet);
	}

	public Integer getContabilDreVinculo() {
		return contabilDreVinculo;
	}

	public void setContabilDreVinculo(Integer contabilDreVinculo) {
		this.contabilDreVinculo = (contabilDreVinculo == null ? new Integer(0)
				: contabilDreVinculo);
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = (contatoEmail == null ? "".trim() : contatoEmail
				.toUpperCase().trim());
	}

	public Integer getViewContratoDadosContratante() {
		return viewContratoDadosContratante;
	}

	public void setViewContratoDadosContratante(
			Integer viewContratoDadosContratante) {
		this.viewContratoDadosContratante = viewContratoDadosContratante;
	}

	public NfeDestinatarioEntity getNfeDestinatario() {
		return nfeDestinatario;
	}

	public void setNfeDestinatario(NfeDestinatarioEntity nfeDestinatario) {
		this.nfeDestinatario = nfeDestinatario;
	}

	public List<NfeDetalheEntity> getNfeDetalheList() {
		return nfeDetalheList;
	}

	public void setNfeDetalheList(List<NfeDetalheEntity> nfeDetalheList) {
		this.nfeDetalheList = nfeDetalheList;
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}
	
	public BigDecimal getValorIcmsDesonerado() {
		return valorIcmsDesonerado;
	}

	public void setValorIcmsDesonerado(BigDecimal valorIcmsDesonerado) {
		this.valorIcmsDesonerado = (valorIcmsDesonerado == null ? new BigDecimal(0) : valorIcmsDesonerado);
	}
	
	public BigDecimal getValorDeducaoIssqn() {
		return valorDeducaoIssqn;
	}

	public void setValorDeducaoIssqn(BigDecimal valorDeducaoIssqn) {
		this.valorDeducaoIssqn = (valorDeducaoIssqn == null ? new BigDecimal(0) : valorDeducaoIssqn);
	}
	
	public BigDecimal getOutrasRetencoesIssqn() {
		return outrasRetencoesIssqn;
	}

	public void setOutrasRetencoesIssqn(BigDecimal outrasRetencoesIssqn) {
		this.outrasRetencoesIssqn = (outrasRetencoesIssqn == null ? new BigDecimal(0) : outrasRetencoesIssqn);
	}
	
	public BigDecimal getDescontoIncondicionadoIssqn() {
		return descontoIncondicionadoIssqn;
	}

	public void setDescontoIncondicionadoIssqn(BigDecimal descontoIncondicionadoIssqn) {
		this.descontoIncondicionadoIssqn = (descontoIncondicionadoIssqn == null ? new BigDecimal(0) : descontoIncondicionadoIssqn);
	}
	
	public BigDecimal getDescontoCondicionadoIssqn() {
		return descontoCondicionadoIssqn;
	}

	public void setDescontoCondicionadoIssqn(BigDecimal descontoCondicionadoIssqn) {
		this.descontoCondicionadoIssqn = (descontoCondicionadoIssqn == null ? new BigDecimal(0) : descontoCondicionadoIssqn);
	}
	
	public BigDecimal getTotalRetencaoIssqn() {
		return totalRetencaoIssqn;
	}

	public void setTotalRetencaoIssqn(BigDecimal totalRetencaoIssqn) {
		this.totalRetencaoIssqn = (totalRetencaoIssqn == null ? new BigDecimal(0) : totalRetencaoIssqn);
	}
	
	public Date getDataPrestacaoServico() {
		return dataPrestacaoServico;
	}

	public void setDataPrestacaoServico(Date dataPrestacaoServico) {
		this.dataPrestacaoServico = dataPrestacaoServico;
	}

	public Integer getRegimeEspecialTributacao() {
		return regimeEspecialTributacao;
	}

	public void setRegimeEspecialTributacao(Integer regimeEspecialTributacao) {
		this.regimeEspecialTributacao = regimeEspecialTributacao;
	}

	/**
	 * Cálculos
	 */

	private BigDecimal calcularMercadoriaValor() {
		if (this.nfeDetalheList == null || this.nfeDetalheList.isEmpty()
				|| this.nfeDetalheList.size() == 0) {
			return new BigDecimal(0.0);
		}

		BigDecimal mercadoria = new BigDecimal(0.0);

		for (NfeDetalheEntity nfeDetalhe : this.nfeDetalheList) {
			mercadoria = mercadoria.add(nfeDetalhe.getValorBrutoProduto());
		}

		return mercadoria;
	}

	private BigDecimal calcularIpi() {
		Double d = calcularMercadoriaValor().doubleValue()
				+ this.valorDespesasAcessorias.doubleValue()
				+ this.valorSeguro.doubleValue();

		return new BigDecimal(d);
	}

	private BigDecimal calcularIcms() {
		Double d = calcularMercadoriaValor().doubleValue()
				+ this.valorDespesasAcessorias.doubleValue()
				+ this.valorSeguro.doubleValue()
				+ this.valorFrete.doubleValue()
				- this.valorDesconto.doubleValue()
				+ calcularIpi().doubleValue();

		return new BigDecimal(d);
	}

	private Double calcularIss() {
		if (this.nfeDetalheList == null || this.nfeDetalheList.isEmpty()
				|| this.nfeDetalheList.size() == 0) {
			return new Double(0.0);
		}

		return new Double(0.0);
	}

	private Double calcularPis() {
		if (this.nfeDetalheList == null || this.nfeDetalheList.isEmpty()
				|| this.nfeDetalheList.size() == 0) {
			return new Double(0.0);
		}

		return new Double(0.0);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}