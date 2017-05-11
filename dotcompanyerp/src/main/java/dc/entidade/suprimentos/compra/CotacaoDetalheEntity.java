package dc.entidade.suprimentos.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

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
import dc.entidade.geral.produto.ProdutoEntity;

@Entity
@Table(name = "compra_cotacao_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Quantidade")
	@Column(name = "quantidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidade = new BigDecimal(0);

	@Field
	@Caption("Quantidade pedida")
	@Column(name = "quantidade_pedida")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadePedida = new BigDecimal(0);

	@Field
	@Caption("Taxa de desconto")
	@Column(name = "taxa_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor de desconto")
	@Column(name = "valor_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor subtotal")
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

	@Caption(value = "Produto")
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;

	@Caption(value = "Fornecedor - Cotação")
	@ManyToOne
	@JoinColumn(name = "id_compra_fornecedor_cotacao")
	private FornecedorCotacaoEntity fornecedorCotacao;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "cotacaoDetalhe")
	@Fetch(FetchMode.SUBSELECT)
	private List<CotacaoPedidoDetalheEntity> cotacaoPedidoDetalheList;
	
	@Transient
    private RequisicaoCompraDetalheEntity compraRequisicaoDetalhe;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public CotacaoDetalheEntity() {

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

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = (quantidade == null ? new BigDecimal(0) : quantidade);
	}

	public BigDecimal getQuantidadePedida() {
		return quantidadePedida;
	}

	public void setQuantidadePedida(BigDecimal quantidadePedida) {
		this.quantidadePedida = (quantidadePedida == null ? new BigDecimal(0)
				: quantidadePedida);
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

	public FornecedorCotacaoEntity getFornecedorCotacao() {
		return fornecedorCotacao;
	}

	public void setFornecedorCotacao(FornecedorCotacaoEntity fornecedorCotacao) {
		this.fornecedorCotacao = fornecedorCotacao;
	}

	public List<CotacaoPedidoDetalheEntity> getCotacaoPedidoDetalheList() {
		return cotacaoPedidoDetalheList;
	}

	public void setCotacaoPedidoDetalheList(
			List<CotacaoPedidoDetalheEntity> cotacaoPedidoDetalheList) {
		this.cotacaoPedidoDetalheList = cotacaoPedidoDetalheList;
	}
	
	public RequisicaoCompraDetalheEntity getCompraRequisicaoDetalhe() {
		return compraRequisicaoDetalhe;
	}

	public void setCompraRequisicaoDetalhe(
			RequisicaoCompraDetalheEntity compraRequisicaoDetalhe) {
		this.compraRequisicaoDetalhe = compraRequisicaoDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}