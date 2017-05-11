package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
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
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;

@Entity
@Table(name = "nfe_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_id_seq", sequenceName = "nfe_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "numero_item")
	@Caption(value = "Número do item")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numeroItem = new Integer(0);

	@Field
	@Column(name = "codigo_produto")
	@Caption(value = "Código do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoProduto = "";

	@Field
	@Column(name = "gtin")
	@Caption(value = "GTIN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtin = "";

	@Field
	@Column(name = "nome_produto")
	@Caption(value = "Nome do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeProduto = "";

	@Field
	@Column(name = "ncm")
	@Caption(value = "NCM")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ncm = "";

	@Field
	@Column(name = "ex_tipi")
	@Caption(value = "EX TIPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer exTipi = new Integer(0);

	@Field
	@Column(name = "cfop")
	@Caption(value = "CFOP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer cfop = new Integer(0);

	@Field
	@Column(name = "unidade_comercial")
	@Caption(value = "Unidade comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String unidadeComercial = "";

	@Field
	@Column(name = "quantidade_comercial")
	@Caption(value = "Quantidade comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeComercial = new BigDecimal(0);

	@Field
	@Column(name = "valor_unitario_comercial")
	@Caption(value = "Valor unitário comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitarioComercial = new BigDecimal(0);

	@Field
	@Column(name = "valor_bruto_produto")
	@Caption(value = "Valor bruto do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBrutoProduto = new BigDecimal(0);

	@Field
	@Column(name = "gtin_unidade_tributavel")
	@Caption(value = "GTIN unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtinUnidadeTributavel = "";

	@Field
	@Column(name = "unidade_tributavel")
	@Caption(value = "Unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String unidadeTributavel = "";

	@Field
	@Column(name = "quantidade_tributavel")
	@Caption(value = "Quantidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeTributavel = new BigDecimal(0);

	@Field
	@Column(name = "valor_unitario_tributavel")
	@Caption(value = "Valor unitário tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitarioTributavel = new BigDecimal(0);

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
	@Column(name = "valor_outras_despesas")
	@Caption(value = "Valor de outras despesas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorOutrasDespesas = new BigDecimal(0);

	@Field
	@Column(name = "entra_total")
	@Caption(value = "Entra total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer entraTotal;

	@Field
	@Column(name = "valor_subtotal")
	@Caption(value = "Valor subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal = new BigDecimal(0);

	@Field
	@Column(name = "valor_total")
	@Caption(value = "Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Field
	@Column(name = "numero_pedido_compra")
	@Caption(value = "Número do pedido de compra")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroPedidoCompra = "";

	@Field
	@Column(name = "item_pedido_compra")
	@Caption(value = "Item do pedido de compra")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer itemPedidoCompra = new Integer(0);

	@Field
	@Column(name = "informacoes_adicionais")
	@Caption(value = "Informações adicionais")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAdicionais = "";
	
	@Field
	@Column(name = "nve")
	@Caption(value = "NVE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nve = "";
	
	@Field
	@Column(name = "numero_fci")
	@Caption(value = "Número FCI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroFci = "";
	
	@Field
	@Column(name = "numero_recopi")
	@Caption(value = "Número RECOPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroRecopi = "";
	
	@Field
	@Column(name = "valor_total_tributos")
	@Caption(value = "Valor total Tributos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalTributos = new BigDecimal(0);
	
	@Field
	@Column(name = "percentual_devolvido")
	@Caption(value = "Percentual Devolvido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualDevolvido = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_ipi_devolvido")
	@Caption(value = "Valor IPI Devolvido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpiDevolvido = new BigDecimal(0);
	
	@Column(name = "ORIGEM_MERCADORIA")
    private String origemMercadoria = "";
	
    @Column(name = "CST_ICMS_B")
    private String cstIcmsB = "";
    
    @Column(name = "CSOSN_B")
    private String csosnB = "";
    
    @Column(name = "MODALIDADE_BC_ICMS")
    private String modalidadeBcIcms = "";
    
    @Column(name = "TAXA_REDUCAO_BC_ICMS")
    private BigDecimal taxaReducaoBcIcms = new BigDecimal(0);
    
    @Column(name = "BASE_CALCULO_ICMS")
    private BigDecimal baseCalculoIcms = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_ICMS")
    private BigDecimal aliquotaIcms = new BigDecimal(0);
    
    @Column(name = "VALOR_ICMS")
    private BigDecimal valorIcms = new BigDecimal(0);
    
    @Column(name = "CST_PIS")
    private String cstPis = "";
    
    @Column(name = "VALOR_BASE_CALCULO_PIS")
    private BigDecimal valorBaseCalculoPis = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_PIS_PERCENTUAL")
    private BigDecimal aliquotaPisPercentual = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_PIS_REAIS")
    private BigDecimal aliquotaPisReais = new BigDecimal(0);
    
    @Column(name = "VALOR_PIS")
    private BigDecimal valorPis = new BigDecimal(0);
    
    @Column(name = "CST_COFINS")
    private String cstCofins = "";
    
    @Column(name = "BASE_CALCULO_COFINS")
    private BigDecimal baseCalculoCofins = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_COFINS_PERCENTUAL")
    private BigDecimal aliquotaCofinsPercentual = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_COFINS_REAIS")
    private BigDecimal aliquotaCofinsReais = new BigDecimal(0);
    
    @Column(name = "VALOR_COFINS")
    private BigDecimal valorCofins = new BigDecimal(0);
    
    @Column(name = "BASE_CALCULO_ISSQN")
    private BigDecimal baseCalculoIssqn = new BigDecimal(0);
    
    @Column(name = "ALIQUOTA_ISSQN")
    private BigDecimal aliquotaIssqn = new BigDecimal(0);
    
    @Column(name = "VALOR_ISSQN")
    private BigDecimal valorIssqn = new BigDecimal(0);


	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_nfe_cabecalho", nullable = false)
	@Caption(value = "NFE cabeçalho")
	private NfeCabecalhoEntity nfeCabecalho;

	@OneToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;
	
	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "nfeDetalhe", fetch = FetchType.LAZY)
	// private List<NfeDetalheImpostoCofinsEntity> nfeDetalheImpostoCofinsList;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoCofinsEntity nfeDetalheImpostoCofins;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIcmsEntity nfeDetalheImpostoIcms;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIiEntity nfeDetalheImpostoIi;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpIpiEntity nfeDetalheImpIpi;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIssqnEntity nfeDetalheImpostoIssqn;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoPisEntity nfeDetalheImpostoPis;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetEspecificoCombustivelEntity nfeDetEspecificoCombustivel;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetEspecificoVeiculoEntity nfeDetEspecificoVeiculo;

	@OneToMany(mappedBy = "nfeDetalhe", fetch = FetchType.LAZY)
	private List<NfeDetEspecificoMedicamentoEntity> ndeMedicamentoList;

	@OneToMany(mappedBy = "nfeDetalhe", fetch = FetchType.LAZY)
	private List<NfeDetEspecificoArmamentoEntity> ndeArmamentoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	private NfeDetEspecificoMedicamentoEntity ndeMedicamento;

	@Transient
	private NfeDetEspecificoArmamentoEntity ndeArmamento;
	
	private LoteProdutoEntity loteProduto;

	/**
	 * CONSTRUTOR
	 */

	public NfeDetalheEntity() {
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

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = (numeroItem == null ? new Integer(0) : numeroItem);
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = (codigoProduto == null ? "" : codigoProduto
				.toUpperCase().trim());
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = (gtin == null ? "" : gtin.toUpperCase().trim());
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = (nomeProduto == null ? "" : nomeProduto
				.toUpperCase().trim());
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = (ncm == null ? "" : ncm.toUpperCase().trim());
	}

	public Integer getExTipi() {
		return exTipi;
	}

	public void setExTipi(Integer exTipi) {
		this.exTipi = (exTipi == null ? new Integer(0) : exTipi);
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = (cfop == null ? new Integer(0) : cfop);
	}

	public String getUnidadeComercial() {
		return unidadeComercial;
	}

	public void setUnidadeComercial(String unidadeComercial) {
		this.unidadeComercial = (unidadeComercial == null ? ""
				: unidadeComercial.toUpperCase().trim());
	}

	public BigDecimal getQuantidadeComercial() {
		return quantidadeComercial;
	}

	public void setQuantidadeComercial(BigDecimal quantidadeComercial) {
		this.quantidadeComercial = (quantidadeComercial == null ? new BigDecimal(
				0) : quantidadeComercial);
	}

	public BigDecimal getValorUnitarioComercial() {
		return valorUnitarioComercial;
	}

	public void setValorUnitarioComercial(BigDecimal valorUnitarioComercial) {
		this.valorUnitarioComercial = (valorUnitarioComercial == null ? new BigDecimal(
				0) : valorUnitarioComercial);
	}

	public BigDecimal getValorBrutoProduto() {
		return valorBrutoProduto;
	}

	public void setValorBrutoProduto(BigDecimal valorBrutoProduto) {
		this.valorBrutoProduto = (valorBrutoProduto == null ? new BigDecimal(0)
				: valorBrutoProduto);
	}

	public String getGtinUnidadeTributavel() {
		return gtinUnidadeTributavel;
	}

	public void setGtinUnidadeTributavel(String gtinUnidadeTributavel) {
		this.gtinUnidadeTributavel = (gtinUnidadeTributavel == null ? ""
				: gtinUnidadeTributavel.toUpperCase().trim());
	}

	public String getUnidadeTributavel() {
		return unidadeTributavel;
	}

	public void setUnidadeTributavel(String unidadeTributavel) {
		this.unidadeTributavel = (unidadeTributavel == null ? ""
				: unidadeTributavel.toUpperCase().trim());
	}

	public BigDecimal getQuantidadeTributavel() {
		return quantidadeTributavel;
	}

	public void setQuantidadeTributavel(BigDecimal quantidadeTributavel) {
		this.quantidadeTributavel = (quantidadeTributavel == null ? new BigDecimal(
				0) : quantidadeTributavel);
	}

	public BigDecimal getValorUnitarioTributavel() {
		return valorUnitarioTributavel;
	}

	public void setValorUnitarioTributavel(BigDecimal valorUnitarioTributavel) {
		this.valorUnitarioTributavel = (valorUnitarioTributavel == null ? new BigDecimal(
				0) : valorUnitarioTributavel);
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

	public BigDecimal getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}

	public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
		this.valorOutrasDespesas = (valorOutrasDespesas == null ? new BigDecimal(
				0) : valorOutrasDespesas);
	}

	public Integer getEntraTotal() {
		return entraTotal;
	}

	public void setEntraTotal(Integer entraTotal) {
		this.entraTotal = entraTotal;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = (valorSubtotal == null ? new BigDecimal(0)
				: valorSubtotal);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = (valorTotal == null ? new BigDecimal(0) : valorTotal);
	}

	public String getNumeroPedidoCompra() {
		return numeroPedidoCompra;
	}

	public void setNumeroPedidoCompra(String numeroPedidoCompra) {
		this.numeroPedidoCompra = (numeroPedidoCompra == null ? ""
				: numeroPedidoCompra.toUpperCase().trim());
	}

	public Integer getItemPedidoCompra() {
		return itemPedidoCompra;
	}

	public void setItemPedidoCompra(Integer itemPedidoCompra) {
		this.itemPedidoCompra = (itemPedidoCompra == null ? new Integer(0)
				: itemPedidoCompra);
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = (informacoesAdicionais == null ? ""
				: informacoesAdicionais.toUpperCase().trim());
	}
	
	public String getNve() {
		return nve;
	}

	public void setNve(String nve) {
		this.nve = nve;
	}
	
	public String getNumeroFci() {
		return numeroFci;
	}

	public void setNumeroFci(String numeroFci) {
		this.numeroFci = numeroFci;
	}
	
	public String getNumeroRecopi() {
		return numeroRecopi;
	}

	public void setNumeroRecopi(String numeroRecopi) {
		this.numeroRecopi = numeroRecopi;
	}
	
	public BigDecimal getValorTotalTributos() {
		return valorTotalTributos;
	}

	public void setValorTotalTributos(BigDecimal valorTotalTributos) {
		this.valorTotalTributos = (valorTotalTributos == null ? new BigDecimal(0) : valorTotalTributos);
	}
	
	public BigDecimal getPercentualDevolvido() {
		return percentualDevolvido;
	}

	public void setPercentualDevolvido(BigDecimal percentualDevolvido) {
		this.percentualDevolvido = (percentualDevolvido == null ? new BigDecimal(0) : percentualDevolvido);
	}
	
	public BigDecimal getValorIpiDevolvido() {
		return valorIpiDevolvido;
	}

	public void setValorIpiDevolvido(BigDecimal valorIpiDevolvido) {
		this.valorIpiDevolvido = (valorIpiDevolvido == null ? new BigDecimal(0) : valorIpiDevolvido);
	}

	public NfeCabecalhoEntity getNfeCabecalho() {
		return nfeCabecalho;
	}

	public void setNfeCabecalho(NfeCabecalhoEntity nfeCabecalho) {
		this.nfeCabecalho = nfeCabecalho;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public NfeDetalheImpostoCofinsEntity getNfeDetalheImpostoCofins() {
		return nfeDetalheImpostoCofins;
	}

	public void setNfeDetalheImpostoCofins(
			NfeDetalheImpostoCofinsEntity nfeDetalheImpostoCofins) {
		this.nfeDetalheImpostoCofins = nfeDetalheImpostoCofins;
	}

	public NfeDetalheImpostoIcmsEntity getNfeDetalheImpostoIcms() {
		return nfeDetalheImpostoIcms;
	}

	public void setNfeDetalheImpostoIcms(
			NfeDetalheImpostoIcmsEntity nfeDetalheImpostoIcms) {
		this.nfeDetalheImpostoIcms = nfeDetalheImpostoIcms;
	}

	public NfeDetalheImpostoIiEntity getNfeDetalheImpostoIi() {
		return nfeDetalheImpostoIi;
	}

	public void setNfeDetalheImpostoIi(
			NfeDetalheImpostoIiEntity nfeDetalheImpostoIi) {
		this.nfeDetalheImpostoIi = nfeDetalheImpostoIi;
	}

	public NfeDetalheImpIpiEntity getNfeDetalheImpIpi() {
		return nfeDetalheImpIpi;
	}

	public void setNfeDetalheImpIpi(NfeDetalheImpIpiEntity nfeDetalheImpIpi) {
		this.nfeDetalheImpIpi = nfeDetalheImpIpi;
	}

	public NfeDetalheImpostoIssqnEntity getNfeDetalheImpostoIssqn() {
		return nfeDetalheImpostoIssqn;
	}

	public void setNfeDetalheImpostoIssqn(
			NfeDetalheImpostoIssqnEntity nfeDetalheImpostoIssqn) {
		this.nfeDetalheImpostoIssqn = nfeDetalheImpostoIssqn;
	}

	public NfeDetalheImpostoPisEntity getNfeDetalheImpostoPis() {
		return nfeDetalheImpostoPis;
	}

	public void setNfeDetalheImpostoPis(
			NfeDetalheImpostoPisEntity nfeDetalheImpostoPis) {
		this.nfeDetalheImpostoPis = nfeDetalheImpostoPis;
	}

	public NfeDetEspecificoCombustivelEntity getNfeDetEspecificoCombustivel() {
		return nfeDetEspecificoCombustivel;
	}

	public void setNfeDetEspecificoCombustivel(
			NfeDetEspecificoCombustivelEntity nfeDetEspecificoCombustivel) {
		this.nfeDetEspecificoCombustivel = nfeDetEspecificoCombustivel;
	}

	public NfeDetEspecificoVeiculoEntity getNfeDetEspecificoVeiculo() {
		return nfeDetEspecificoVeiculo;
	}

	public void setNfeDetEspecificoVeiculo(
			NfeDetEspecificoVeiculoEntity nfeDetEspecificoVeiculo) {
		this.nfeDetEspecificoVeiculo = nfeDetEspecificoVeiculo;
	}

	public List<NfeDetEspecificoMedicamentoEntity> getNdeMedicamentoList() {
		return ndeMedicamentoList;
	}

	public void setNdeMedicamentoList(
			List<NfeDetEspecificoMedicamentoEntity> ndeMedicamentoList) {
		this.ndeMedicamentoList = ndeMedicamentoList;
	}

	public List<NfeDetEspecificoArmamentoEntity> getNdeArmamentoList() {
		return ndeArmamentoList;
	}

	public void setNdeArmamentoList(
			List<NfeDetEspecificoArmamentoEntity> ndeArmamentoList) {
		this.ndeArmamentoList = ndeArmamentoList;
	}

	public NfeDetEspecificoMedicamentoEntity getNdeMedicamento() {
		return ndeMedicamento;
	}

	public void setNdeMedicamento(
			NfeDetEspecificoMedicamentoEntity ndeMedicamento) {
		this.ndeMedicamento = ndeMedicamento;
	}

	public NfeDetEspecificoArmamentoEntity getNdeArmamento() {
		return ndeArmamento;
	}

	public void setNdeArmamento(NfeDetEspecificoArmamentoEntity ndeArmamento) {
		this.ndeArmamento = ndeArmamento;
	}
	
	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public String getCstIcmsB() {
		return cstIcmsB;
	}

	public void setCstIcmsB(String cstIcmsB) {
		this.cstIcmsB = cstIcmsB;
	}

	public String getCsosnB() {
		return csosnB;
	}

	public void setCsosnB(String csosnB) {
		this.csosnB = csosnB;
	}

	public String getModalidadeBcIcms() {
		return modalidadeBcIcms;
	}

	public void setModalidadeBcIcms(String modalidadeBcIcms) {
		this.modalidadeBcIcms = modalidadeBcIcms;
	}

	public BigDecimal getTaxaReducaoBcIcms() {
		return taxaReducaoBcIcms;
	}

	public void setTaxaReducaoBcIcms(BigDecimal taxaReducaoBcIcms) {
		this.taxaReducaoBcIcms = taxaReducaoBcIcms;
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}
	
	public String getCstPis() {
		return cstPis;
	}

	public void setCstPis(String cstPis) {
		this.cstPis = cstPis;
	}

	public BigDecimal getValorBaseCalculoPis() {
		return valorBaseCalculoPis;
	}

	public void setValorBaseCalculoPis(BigDecimal valorBaseCalculoPis) {
		this.valorBaseCalculoPis = valorBaseCalculoPis;
	}

	public BigDecimal getAliquotaPisPercentual() {
		return aliquotaPisPercentual;
	}

	public void setAliquotaPisPercentual(BigDecimal aliquotaPisPercentual) {
		this.aliquotaPisPercentual = aliquotaPisPercentual;
	}

	public BigDecimal getAliquotaPisReais() {
		return aliquotaPisReais;
	}

	public void setAliquotaPisReais(BigDecimal aliquotaPisReais) {
		this.aliquotaPisReais = aliquotaPisReais;
	}

	public BigDecimal getValorPis() {
		return valorPis;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}

	public String getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(String cstCofins) {
		this.cstCofins = cstCofins;
	}

	public BigDecimal getBaseCalculoCofins() {
		return baseCalculoCofins;
	}

	public void setBaseCalculoCofins(BigDecimal baseCalculoCofins) {
		this.baseCalculoCofins = baseCalculoCofins;
	}

	public BigDecimal getAliquotaCofinsPercentual() {
		return aliquotaCofinsPercentual;
	}

	public void setAliquotaCofinsPercentual(BigDecimal aliquotaCofinsPercentual) {
		this.aliquotaCofinsPercentual = aliquotaCofinsPercentual;
	}

	public BigDecimal getAliquotaCofinsReais() {
		return aliquotaCofinsReais;
	}

	public void setAliquotaCofinsReais(BigDecimal aliquotaCofinsReais) {
		this.aliquotaCofinsReais = aliquotaCofinsReais;
	}

	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public BigDecimal getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
		this.baseCalculoIssqn = baseCalculoIssqn;
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = aliquotaIssqn;
	}

	public BigDecimal getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(BigDecimal valorIssqn) {
		this.valorIssqn = valorIssqn;
	}
	
	public LoteProdutoEntity getLoteProduto() {
		return loteProduto;
	}

	public void setLoteProduto(LoteProdutoEntity loteProduto) {
		this.loteProduto = loteProduto;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}