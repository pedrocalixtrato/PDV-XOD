package dc.entidade.comercial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;

@Entity
@Table(name = "venda_cabecalho")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Venda extends AbstractMultiEmpresaModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vnd")
	@SequenceGenerator(name = "vnd", sequenceName = "venda_cabecalho_id_seq", allocationSize = 1)
	@Caption("ID")
	private Integer id;

	@OneToMany(mappedBy = "venda", orphanRemoval = true, cascade = CascadeType.ALL)
	@Caption("Detalhe")
	private List<VendaDetalhe> detalhes;

	@Column(name = "data_venda")
	@Temporal(TemporalType.DATE)
	Date dataVenda;

	@Column(name = "data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;

	@Column(name = "hora_saida")
	String horaSaida;

	@Column(name = "numero_fatura")
	Integer numeroFatura;

	@Column(name = "local_entrega")
	String localEntrega;

	@Column(name = "local_cobranca")
	String localCobranca;

	@Column(name = "valor_subtotal")
	BigDecimal valorSubTotal;

	@Column(name = "valor_frete")
	BigDecimal valorFrete;

	@Column(name = "taxa_comissao")
	BigDecimal taxaComissao;

	@Column(name = "valor_comissao")
	BigDecimal valorComissao;

	@Column(name = "taxa_desconto")
	BigDecimal taxaDesconto;

	@Column(name = "valor_desconto")
	BigDecimal valorDesconto;

	@Column(name = "valor_total")
	BigDecimal valorTotal;

	String observacao;
	

    @JoinColumn(name = "ID_VENDA_ORCAMENTO_CABECALHO", referencedColumnName = "ID")
    @ManyToOne
    private Orcamento orcamentoVendaCabecalho;
    
    @JoinColumn(name = "ID_VENDA_CONDICOES_PAGAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Condição Pagamento é Obrigatório!")
    private CondicaoPagamento condicoesPagamento;
    
    @Caption("Cliente")
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Cliente é Obrigatório!")
    private ClienteEntity cliente;
    
    @Caption("Vendedor")
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VendedorEntity vendedor;
    
    @JoinColumn(name = "ID_NOTA_FISCAL_TIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Nota Fiscal Tipo é Obrigatório!")
    private NotaFiscalTipo tipoNotaFiscal;
    
    @Caption("Transportadora")
    @JoinColumn(name = "ID_TRANSPORTADORA", referencedColumnName = "ID")
    @ManyToOne
    private TransportadoraEntity transportadora;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * Módulo: NFE
	 */

	// @OneToMany(mappedBy = "vendaCabecalho", fetch = FetchType.LAZY)
	// @Fetch(FetchMode.SUBSELECT)
	// private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * 
	 */

	/**
	 * CONSTRUTOR
	 */

	public Venda() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<VendaDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<VendaDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	public VendaDetalhe adicionarDetalhe(VendaDetalhe detalhe) {
		if (detalhes == null)
			detalhes = new ArrayList();
		getDetalhes().add(detalhe);
		detalhe.setVenda(this);
		return detalhe;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Integer getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(Integer numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getLocalCobranca() {
		return localCobranca;
	}

	public void setLocalCobranca(String localCobranca) {
		this.localCobranca = localCobranca;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	/**
	 * Módulo: NFE
	 */

	// public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
	// return nfeCabecalhoList;
	// }

	// public void setNfeCabecalhoList(List<NfeCabecalhoEntity>
	// nfeCabecalhoList) {
	// this.nfeCabecalhoList = nfeCabecalhoList;
	// }

	/**
	 * 
	 */

	public Orcamento getOrcamentoVendaCabecalho() {
		return orcamentoVendaCabecalho;
	}

	public void setOrcamentoVendaCabecalho(Orcamento orcamentoVendaCabecalho) {
		this.orcamentoVendaCabecalho = orcamentoVendaCabecalho;
	}

	public CondicaoPagamento getCondicoesPagamento() {
		return condicoesPagamento;
	}

	public void setCondicoesPagamento(CondicaoPagamento condicoesPagamento) {
		this.condicoesPagamento = condicoesPagamento;
	}

	public NotaFiscalTipo getTipoNotaFiscal() {
		return tipoNotaFiscal;
	}

	public void setTipoNotaFiscal(NotaFiscalTipo tipoNotaFiscal) {
		this.tipoNotaFiscal = tipoNotaFiscal;
	}

	public TransportadoraEntity getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(TransportadoraEntity transportadora) {
		this.transportadora = transportadora;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}