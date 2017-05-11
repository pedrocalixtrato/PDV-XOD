package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.FornecedorEntity;

@Entity
@Table(name = "compra_pedido")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PedidoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "compra_pedido_id_seq")
	@SequenceGenerator(name = "compra_pedido_id_seq", sequenceName = "compra_pedido_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Base de cálculo ICMS")
	@Column(name = "base_calculo_icms")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcms = new BigDecimal(0);

	@Field
	@Caption("Base de cálculo ICMS ST")
	@Column(name = "base_calculo_icms_st")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcmsSt = new BigDecimal(0);

	@Field
	@Caption("Contato")
	@Column(name = "contato")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contato = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data do pedido")
	@Column(name = "data_pedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Data de Pedido é Obrigatório!")
	private Date dataPedido;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data da previsão do pagamento")
	@Column(name = "data_previsao_pagamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataPrevisaoPagamento;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data prevista da entrega")
	@Column(name = "data_prevista_entrega")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataPrevistaEntrega;

	@Field
	@Caption("Dias de intervalo")
	@Column(name = "dias_intervalo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer diasIntervalo = new Integer(0);

	@Field
	@Caption("Dias do primeiro vencimento")
	@Column(name = "dias_primeiro_vencimento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer diasPrimeiroVencimento = new Integer(0);

	@Field
	@Caption("Forma de pagamento")
	@Column(name = "forma_pagamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String formaPagamento = "";

	@Field
	@Caption("Local da cobrança")
	@Column(name = "local_cobranca")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String localCobranca = "";

	@Field
	@Caption("Local da entrega")
	@Column(name = "local_entrega")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String localEntrega = "";

	@Field
	@Caption("Quantidade de parcelas")
	@Column(name = "quantidade_parcelas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcela = new Integer(0);

	@Field
	@Caption("Taxa de desconto")
	@Column(name = "taxa_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto = new BigDecimal(0);

	@Field
	@Caption("Tipo de frete")
	@Column(name = "tipo_frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoFrete = "";

	@Field
	@Caption("Valor de desconto")
	@Column(name = "valor_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor do frete")
	@Column(name = "valor_frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorFrete = new BigDecimal(0);

	@Field
	@Caption("Valor do ICMS")
	@Column(name = "valor_icms")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcms = new BigDecimal(0);

	@Field
	@Caption("Valor do ICMS ST")
	@Column(name = "valor_icms_st")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsSt = new BigDecimal(0);

	@Field
	@Caption("Valor do IPI")
	@Column(name = "valor_ipi")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpi = new BigDecimal(0);

	@Field
	@Caption("Valor de outras despesas")
	@Column(name = "valor_outras_despesas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorOutraDespesa = new BigDecimal(0);

	@Field
	@Caption("Valor do seguro")
	@Column(name = "valor_seguro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSeguro = new BigDecimal(0);

	@Field
	@Caption("Valor - Subtotal")
	@Column(name = "valor_subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal = new BigDecimal(0);

	@Field
	@Caption("Valor total da NF")
	@Column(name = "valor_total_nf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalNf = new BigDecimal(0);

	@Field
	@Caption("Valor total do pedido")
	@Column(name = "valor_total_pedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalPedido = new BigDecimal(0);

	@Field
	@Caption("Valor total dos produtos")
	@Column(name = "valor_total_produtos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotalProduto = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Tipo do pedido")
	@ManyToOne
	@JoinColumn(name = "id_compra_tipo_pedido", referencedColumnName = "ID")
	@NotNull(message = "Tipo de Pedido é Obrigatório!")
	private TipoPedidoEntity tipoPedido;

	@Caption("Fornecedor")
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	@NotNull(message = "Fornecedor é Obrigatório!")
	private FornecedorEntity fornecedor;

	/**
	 * REFERENCIA - LIST
	 */

	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PedidoDetalheEntity> pedidoDetalhe = new ArrayList<>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PedidoEntity() {

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

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = (baseCalculoIcms == null ? new BigDecimal(0)
				: baseCalculoIcms);
	}

	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}

	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = (baseCalculoIcmsSt == null ? new BigDecimal(0)
				: baseCalculoIcmsSt);
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = (contato == null ? "".trim() : contato.toUpperCase()
				.trim());
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataPrevisaoPagamento() {
		return dataPrevisaoPagamento;
	}

	public void setDataPrevisaoPagamento(Date dataPrevisaoPagamento) {
		this.dataPrevisaoPagamento = dataPrevisaoPagamento;
	}

	public Date getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public Integer getDiasIntervalo() {
		return diasIntervalo;
	}

	public void setDiasIntervalo(Integer diasIntervalo) {
		this.diasIntervalo = diasIntervalo;
	}

	public Integer getDiasPrimeiroVencimento() {
		return diasPrimeiroVencimento;
	}

	public void setDiasPrimeiroVencimento(Integer diasPrimeiroVencimento) {
		this.diasPrimeiroVencimento = diasPrimeiroVencimento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = (formaPagamento == null ? "".trim()
				: formaPagamento.toUpperCase().trim());
	}

	public String getLocalCobranca() {
		return localCobranca;
	}

	public void setLocalCobranca(String localCobranca) {
		this.localCobranca = (localCobranca == null ? "".trim() : localCobranca
				.toUpperCase().trim());
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = (localEntrega == null ? "".trim() : localEntrega
				.toUpperCase().trim());
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = (taxaDesconto == null ? new BigDecimal(0)
				: taxaDesconto);
	}

	public String getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = (tipoFrete == null ? "".trim() : tipoFrete
				.toUpperCase().trim());
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = (valorDesconto == null ? new BigDecimal(0)
				: valorDesconto);
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = (valorFrete == null ? new BigDecimal(0) : valorFrete);
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = (valorIcms == null ? new BigDecimal(0) : valorIcms);
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = (valorIcmsSt == null ? new BigDecimal(0)
				: valorIcmsSt);
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = (valorIpi == null ? new BigDecimal(0) : valorIpi);
	}

	public BigDecimal getValorOutraDespesa() {
		return valorOutraDespesa;
	}

	public void setValorOutraDespesa(BigDecimal valorOutraDespesa) {
		this.valorOutraDespesa = (valorOutraDespesa == null ? new BigDecimal(0)
				: valorOutraDespesa);
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = (valorSeguro == null ? new BigDecimal(0)
				: valorSeguro);
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = (valorSubtotal == null ? new BigDecimal(0)
				: valorSubtotal);
	}

	public BigDecimal getValorTotalNf() {
		return valorTotalNf;
	}

	public void setValorTotalNf(BigDecimal valorTotalNf) {
		this.valorTotalNf = (valorTotalNf == null ? new BigDecimal(0)
				: valorTotalNf);
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = (valorTotalPedido == null ? new BigDecimal(0)
				: valorTotalPedido);
	}

	public BigDecimal getValorTotalProduto() {
		return valorTotalProduto;
	}

	public void setValorTotalProduto(BigDecimal valorTotalProduto) {
		this.valorTotalProduto = (valorTotalProduto == null ? new BigDecimal(0)
				: valorTotalProduto);
	}

	public TipoPedidoEntity getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(TipoPedidoEntity tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<PedidoDetalheEntity> getPedidoDetalhe() {
		return pedidoDetalhe;
	}

	public void setPedidoDetalhe(List<PedidoDetalheEntity> pedidoDetalhe) {
		this.pedidoDetalhe = pedidoDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PedidoEntity)) {
            return false;
        }

        PedidoEntity that = (PedidoEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(getId(), that.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
        }
    }

	public PedidoDetalheEntity addPedidoDetalhe(PedidoDetalheEntity pedidoDetalhe2) {
		getPedidoDetalhe().add(pedidoDetalhe2);
		pedidoDetalhe2.setPedido(this);

		return pedidoDetalhe2;
		
	}
	
	public PedidoDetalheEntity removePedidoDetalhe(PedidoDetalheEntity pedidoDetalhe2) {
		getPedidoDetalhe().remove(pedidoDetalhe2);
		pedidoDetalhe2.setPedido(null);

		return pedidoDetalhe2;
	}

}