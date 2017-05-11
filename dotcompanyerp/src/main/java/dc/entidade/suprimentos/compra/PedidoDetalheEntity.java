package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;

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

@Entity
@Table(name = "compra_pedido_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PedidoDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "compra_pedido_detalhe_id_seq")
	@SequenceGenerator(name = "compra_pedido_detalhe_id_seq", sequenceName = "compra_pedido_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Alíquota do ICMS")
	@Column(name = "aliquota_icms")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcms = new BigDecimal(0);

	@Field
	@Caption("Alíquota do IPI")
	@Column(name = "aliquota_ipi")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIpi = new BigDecimal(0);

	@Field
	@Caption("Base de cálculo do ICMS")
	@Column(name = "base_calculo_icms")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcms = new BigDecimal(0);

	@Field
	@Caption("CFOP")
	@Column(name = "cfop")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer cfop = new Integer(0);

	@Field
	@Caption("CST / CSOSN")
	@Column(name = "cst_csosn")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstCsosn = "";

	@Field
	@Caption("Qunatidade")
	@Column(name = "quantidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidade = new BigDecimal(0);

	@Field
	@Caption("Taxa de desconto")
	@Column(name = "taxa_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor do desconto")
	@Column(name = "valor_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor do ICMS")
	@Column(name = "valor_icms")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcms = new BigDecimal(0);

	@Field
	@Caption("Valor do IPI")
	@Column(name = "valor_ipi")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIpi = new BigDecimal(0);

	@Field
	@Caption("Valor - Subtotal")
	@Column(name = "valor_subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal = new BigDecimal(0);

	@Field
	@Caption("Valor total")
	@Column(name = "valor_total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Field
	@Caption("Valor unitário")
	@Column(name = "valor_unitario")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitario = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Produto")
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;

	@Caption("Pedido")
	@ManyToOne
	@JoinColumn(name = "id_compra_pedido")
	private PedidoEntity pedido;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PedidoDetalheEntity() {
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

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = (aliquotaIcms == null ? new BigDecimal(0)
				: aliquotaIcms);
	}

	public BigDecimal getAliquotaIpi() {
		return aliquotaIpi;
	}

	public void setAliquotaIpi(BigDecimal aliquotaIpi) {
		this.aliquotaIpi = (aliquotaIpi == null ? new BigDecimal(0)
				: aliquotaIpi);
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = (baseCalculoIcms == null ? new BigDecimal(0)
				: baseCalculoIcms);
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = (cfop == null ? new Integer(0) : cfop);
	}

	public String getCstCsosn() {
		return cstCsosn;
	}

	public void setCstCsosn(String cstCsosn) {
		this.cstCsosn = (cstCsosn == null ? "".trim() : cstCsosn.toUpperCase()
				.trim());
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = (quantidade == null ? new BigDecimal(0) : quantidade);
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = (taxaDesconto == null ? new BigDecimal(0)
				: taxaDesconto);
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = (valorDesconto == null ? new BigDecimal(0)
				: valorDesconto);
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = (valorIcms == null ? new BigDecimal(0) : valorIcms);
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = (valorIpi == null ? new BigDecimal(0) : valorIpi);
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

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = (valorUnitario == null ? new BigDecimal(0)
				: valorUnitario);
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}