package dc.entidade.ordemservico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "os_ordem_servico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OrdemServicoEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Caption(value = "Cliente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@IndexedEmbedded(depth=3, includePaths={"pessoa.nome"})
	private ClienteEntity cliente;

	@Field
	@Caption(value = "Valor Serviço")
	@Column(name = "valor_servico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorServico;

	@Field
	@Caption(value = "Valor Peça")
	@Column(name = "valor_peca")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorPeca;

	@Field
	@Caption(value = "Valor Frete")
	@Column(name = "valor_frete")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorFrete;	

	@Field
	@Caption(value = "Valor Outros")
	@Column(name = "valor_outros")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorOutros;	

	@Field
	@Caption(value = "Valor Desconto")
	@Column(name = "valor_desconto")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorDesconto;	

	@Field
	@Caption(value = "Valor Total")
	@Column(name = "valor_total_os")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorTotalOs;	

	@Field
	@Caption(value = "Valor Serviço")
	@Column(name = "valor_total_servico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorTotalServico;	

	@Field
	@Caption(value = "Valor Lucro Serviço")
	@Column(name = "valor_lucro_servico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorLucroServico;	

	@Field
	@Caption(value = "Valor Comissão Técnico")
	@Column(name = "valor_comissao_tecnico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissaoTecnico;	

	@Field
	@Caption(value = "Valor Comissão Vendedor")
	@Column(name = "valor_comissao_vendedor")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissaoVendedor;	

	@Field
	@Caption(value = "Valor Comissão Atendente")
	@Column(name = "valor_comissao_atendente")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissaoAtendente;	

	@Field
	@Caption(value = "Valor Lucro Parcial")
	@Column(name = "valor_lucro_parcial")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorLucroParcial;

	@Field
	@Caption(value = "Valor Lucro Peça")
	@Column(name = "valor_lucro_peca")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorLucroPeca;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cheque")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaCheque;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cheque")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCheque;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_carne")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaCarne;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_carne")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCarne;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cartao")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaCartao;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cartao")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCartao;
	
	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_boleto")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaBoleto;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_boleto")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoBoleto;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_duplicata")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaDuplicata;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_duplicata")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoDuplicata;
	
	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_vale")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaVale;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_vale")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoVale;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cobranca_bancaria")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaCobrancaBancaria;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cobranca_bancaria")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCobrancaBancaria;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cobranca_carteira")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCobrancaCarteira;

	@Field
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cobranca_carteira")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelaCobrancaCarteira;
	

	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;
	
	@Field
	@Caption(value = "Data Exclusão")
	@Column(name = "data_exclusao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataExclusao;

	@Field
	@Caption(value = "O.S Efetivada")
	@Column(name = "os_efetivada")
	private Boolean efetivada;
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<FinanceiroOsEntity> itensFinanceiroOs = new ArrayList<FinanceiroOsEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<InformacaoGeralEntity> itensInformacaoGeral = new ArrayList<InformacaoGeralEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<LaudoTecnicoEntity> itensLaudoTecnico = new ArrayList<LaudoTecnicoEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ObservacaoEntity> itensObservacao = new ArrayList<ObservacaoEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<EntradaServicoEntity> itensEntradaServico = new ArrayList<EntradaServicoEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<VendaPecaEntity> itensVendaPeca = new ArrayList<VendaPecaEntity>();

	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<MaterialServicoEntity> itensMaterialServico = new ArrayList<MaterialServicoEntity>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<AcessorioOsEntity> itensAcessorioOs = new ArrayList<AcessorioOsEntity>();

	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<OrdemServicoEfetivacaoEntity> itensOrdemServicoEfetivacao = new ArrayList<OrdemServicoEfetivacaoEntity>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public BigDecimal getValorPeca() {
		return valorPeca;
	}

	public void setValorPeca(BigDecimal valorPeca) {
		this.valorPeca = valorPeca;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorOutros() {
		return valorOutros;
	}

	public void setValorOutros(BigDecimal valorOutros) {
		this.valorOutros = valorOutros;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotalOs() {
		return valorTotalOs;
	}

	public void setValorTotalOs(BigDecimal valorTotalOs) {
		this.valorTotalOs = valorTotalOs;
	}

	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}

	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}

	public BigDecimal getValorLucroServico() {
		return valorLucroServico;
	}

	public void setValorLucroServico(BigDecimal valorLucroServico) {
		this.valorLucroServico = valorLucroServico;
	}

	public BigDecimal getValorComissaoTecnico() {
		return valorComissaoTecnico;
	}

	public void setValorComissaoTecnico(BigDecimal valorComissaoTecnico) {
		this.valorComissaoTecnico = valorComissaoTecnico;
	}

	public BigDecimal getValorComissaoVendedor() {
		return valorComissaoVendedor;
	}

	public void setValorComissaoVendedor(BigDecimal valorComissaoVendedor) {
		this.valorComissaoVendedor = valorComissaoVendedor;
	}

	public BigDecimal getValorComissaoAtendente() {
		return valorComissaoAtendente;
	}

	public void setValorComissaoAtendente(BigDecimal valorComissaoAtendente) {
		this.valorComissaoAtendente = valorComissaoAtendente;
	}

	public BigDecimal getValorLucroParcial() {
		return valorLucroParcial;
	}

	public void setValorLucroParcial(BigDecimal valorLucroParcial) {
		this.valorLucroParcial = valorLucroParcial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public List<EntradaServicoEntity> getItensEntradaServico() {
		return itensEntradaServico;
	}

	public void setItensEntradaServico(List<EntradaServicoEntity> itensEntradaServico) {
		this.itensEntradaServico = itensEntradaServico;
	}
	
	public List<VendaPecaEntity> getItensVendaPeca() {
		return itensVendaPeca;
	}

	public void setItensVendaPeca(List<VendaPecaEntity> itensVendaPeca) {
		this.itensVendaPeca = itensVendaPeca;
	}

	public List<MaterialServicoEntity> getItensMaterialServico() {
		return itensMaterialServico;
	}

	public void setItensMaterialServico(List<MaterialServicoEntity> itensMaterialServico) {
		this.itensMaterialServico = itensMaterialServico;
	}

	public List<AcessorioOsEntity> getItensAcessorioOs() {
		return itensAcessorioOs;
	}

	public void setItensAcessorioOs(List<AcessorioOsEntity> itensAcessorioOs) {
		this.itensAcessorioOs = itensAcessorioOs;
	}

	public Integer getQuantidadeParcelaCheque() {
		return quantidadeParcelaCheque;
	}

	public void setQuantidadeParcelaCheque(Integer quantidadeParcelaCheque) {
		this.quantidadeParcelaCheque = quantidadeParcelaCheque;
	}

	public List<OrdemServicoEfetivacaoEntity> getItensOrdemServicoEfetivacao() {
		return itensOrdemServicoEfetivacao;
	}

	public Date getPrimeiroVencimentoCheque() {
		return primeiroVencimentoCheque;
	}

	public void setPrimeiroVencimentoCheque(Date primeiroVencimentoCheque) {
		this.primeiroVencimentoCheque = primeiroVencimentoCheque;
	}

	public Integer getQuantidadeParcelaCarne() {
		return quantidadeParcelaCarne;
	}

	public void setQuantidadeParcelaCarne(Integer quantidadeParcelaCarne) {
		this.quantidadeParcelaCarne = quantidadeParcelaCarne;
	}

	public Date getPrimeiroVencimentoCarne() {
		return primeiroVencimentoCarne;
	}

	public void setPrimeiroVencimentoCarne(Date primeiroVencimentoCarne) {
		this.primeiroVencimentoCarne = primeiroVencimentoCarne;
	}

	public Integer getQuantidadeParcelaCartao() {
		return quantidadeParcelaCartao;
	}

	public void setQuantidadeParcelaCartao(Integer quantidadeParcelaCartao) {
		this.quantidadeParcelaCartao = quantidadeParcelaCartao;
	}

	public Date getPrimeiroVencimentoCartao() {
		return primeiroVencimentoCartao;
	}

	public void setPrimeiroVencimentoCartao(Date primeiroVencimentoCartao) {
		this.primeiroVencimentoCartao = primeiroVencimentoCartao;
	}

	public Integer getQuantidadeParcelaBoleto() {
		return quantidadeParcelaBoleto;
	}

	public void setQuantidadeParcelaBoleto(Integer quantidadeParcelaBoleto) {
		this.quantidadeParcelaBoleto = quantidadeParcelaBoleto;
	}

	public Date getPrimeiroVencimentoBoleto() {
		return primeiroVencimentoBoleto;
	}

	public void setPrimeiroVencimentoBoleto(Date primeiroVencimentoBoleto) {
		this.primeiroVencimentoBoleto = primeiroVencimentoBoleto;
	}

	public Integer getQuantidadeParcelaDuplicata() {
		return quantidadeParcelaDuplicata;
	}

	public void setQuantidadeParcelaDuplicata(Integer quantidadeParcelaDuplicata) {
		this.quantidadeParcelaDuplicata = quantidadeParcelaDuplicata;
	}

	public Date getPrimeiroVencimentoDuplicata() {
		return primeiroVencimentoDuplicata;
	}

	public void setPrimeiroVencimentoDuplicata(Date primeiroVencimentoDuplicata) {
		this.primeiroVencimentoDuplicata = primeiroVencimentoDuplicata;
	}

	public Integer getQuantidadeParcelaVale() {
		return quantidadeParcelaVale;
	}

	public void setQuantidadeParcelaVale(Integer quantidadeParcelaVale) {
		this.quantidadeParcelaVale = quantidadeParcelaVale;
	}

	public Date getPrimeiroVencimentoVale() {
		return primeiroVencimentoVale;
	}

	public void setPrimeiroVencimentoVale(Date primeiroVencimentoVale) {
		this.primeiroVencimentoVale = primeiroVencimentoVale;
	}

	public void setItensOrdemServicoEfetivacao(List<OrdemServicoEfetivacaoEntity> itensOrdemServicoEfetivacao) {
		this.itensOrdemServicoEfetivacao = itensOrdemServicoEfetivacao;
	}

	public Integer getQuantidadeParcelaCobrancaBancaria() {
		return quantidadeParcelaCobrancaBancaria;
	}

	public void setQuantidadeParcelaCobrancaBancaria(
			Integer quantidadeParcelaCobrancaBancaria) {
		this.quantidadeParcelaCobrancaBancaria = quantidadeParcelaCobrancaBancaria;
	}

	public Date getPrimeiroVencimentoCobrancaBancaria() {
		return primeiroVencimentoCobrancaBancaria;
	}

	public void setPrimeiroVencimentoCobrancaBancaria(
			Date primeiroVencimentoCobrancaBancaria) {
		this.primeiroVencimentoCobrancaBancaria = primeiroVencimentoCobrancaBancaria;
	}

	public Date getPrimeiroVencimentoCobrancaCarteira() {
		return primeiroVencimentoCobrancaCarteira;
	}

	public void setPrimeiroVencimentoCobrancaCarteira(
			Date primeiroVencimentoCobrancaCarteira) {
		this.primeiroVencimentoCobrancaCarteira = primeiroVencimentoCobrancaCarteira;
	}

	public Integer getQuantidadeParcelaCobrancaCarteira() {
		return quantidadeParcelaCobrancaCarteira;
	}

	public void setQuantidadeParcelaCobrancaCarteira(
			Integer quantidadeParcelaCobrancaCarteira) {
		this.quantidadeParcelaCobrancaCarteira = quantidadeParcelaCobrancaCarteira;
	}

	public BigDecimal getValorLucroPeca() {
		return valorLucroPeca;
	}

	public void setValorLucroPeca(BigDecimal valorLucroPeca) {
		this.valorLucroPeca = valorLucroPeca;
	}
	
	public Boolean getEfetivada() {
		return efetivada;
	}

	public void setEfetivada(Boolean efetivada) {
		this.efetivada = efetivada;
	}

	public EntradaServicoEntity adicionarEntradaServico(EntradaServicoEntity entradaServico){
		getItensEntradaServico().add(entradaServico);
		entradaServico.setOrdemServico(this);
		return entradaServico;
	}

	public VendaPecaEntity adicionarVendaPeca(VendaPecaEntity vendaPeca){
		getItensVendaPeca().add(vendaPeca);
		vendaPeca.setOrdemServico(this);
		return vendaPeca;
	}

	public MaterialServicoEntity adicionarMaterialServico(MaterialServicoEntity materialServico){
		getItensMaterialServico().add(materialServico);
		materialServico.setOrdemServico(this);
		return materialServico;
	}

	public AcessorioOsEntity adicionarAcessorioOs(AcessorioOsEntity acessorioOs){
		getItensAcessorioOs().add(acessorioOs);
		acessorioOs.setOrdemServico(this);
		return acessorioOs;
	}
	public void addParcelaCheque(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCarne(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCartao(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaBoleto(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaDuplicata(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaVale(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCobrancaBancaria(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCobrancaCarteira(OrdemServicoEfetivacaoEntity parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void removeMaterialServico(MaterialServicoEntity value) {
		this.itensMaterialServico.remove(value);
		value.setOrdemServico(null);
	}
	public void removeEntradaServico(EntradaServicoEntity value) {
		this.itensEntradaServico.remove(value);
		value.setOrdemServico(null);
	}
	public void removeVendaPeca(VendaPecaEntity value) {
		this.itensVendaPeca.remove(value);
		value.setOrdemServico(null);
	}
	public void removeAcessorioOs(AcessorioOsEntity value) {
		this.itensAcessorioOs.remove(value);
		value.setOrdemServico(null);
	}

	public List<FinanceiroOsEntity> getItensFinanceiroOs() {
		return itensFinanceiroOs;
	}

	public void setItensFinanceiroOs(List<FinanceiroOsEntity> itensFinanceiroOs) {
		this.itensFinanceiroOs = itensFinanceiroOs;
	}

	public List<InformacaoGeralEntity> getItensInformacaoGeral() {
		return itensInformacaoGeral;
	}

	public void setItensInformacaoGeral(
			List<InformacaoGeralEntity> itensInformacaoGeral) {
		this.itensInformacaoGeral = itensInformacaoGeral;
	}

	public List<LaudoTecnicoEntity> getItensLaudoTecnico() {
		return itensLaudoTecnico;
	}

	public void setItensLaudoTecnico(List<LaudoTecnicoEntity> itensLaudoTecnico) {
		this.itensLaudoTecnico = itensLaudoTecnico;
	}

	public List<ObservacaoEntity> getItensObservacao() {
		return itensObservacao;
	}

	public void setItensObservacao(List<ObservacaoEntity> itensObservacao) {
		this.itensObservacao = itensObservacao;
	}
	
}
