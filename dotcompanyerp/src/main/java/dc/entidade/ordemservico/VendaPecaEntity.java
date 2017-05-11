package dc.entidade.ordemservico;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.produto.ProdutoEntity;

@Entity
@Table(name = "os_venda_peca")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class VendaPecaEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServicoEntity ordemServico;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vendedor", referencedColumnName = "id", nullable = true)
	private ColaboradorEntity vendedor;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tecnico", referencedColumnName = "id", nullable = true)
	private ColaboradorEntity tecnico;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id")
	private ProdutoEntity produto;
	
	@Field
	@Caption("TIPO PECA")
	@Column(name = "tipo_peca")
	private String tipoPeca;

	@Field
	@Caption("QUANTIDADE")
	@Column(name = "quantidade_produto")
	private BigDecimal quantidadeProduto;

	@Column(name = "valor_unitario_pago")
	private BigDecimal valorUnitarioPago;

	@Column(name = "valor_unitario_prod")
	private BigDecimal valorUnitario;

	@Column(name="valor_subtotal")
	private BigDecimal valorSubtotal;
	
	@Column(name = "perc_desconto")
	private BigDecimal percentualDesconto;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_compra_prod")
	private BigDecimal valorCompra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServicoEntity getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServicoEntity ordemServico) {
		this.ordemServico = ordemServico;
	}

	public ColaboradorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(ColaboradorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public String getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(String tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public BigDecimal getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public BigDecimal getValorUnitarioPago() {
		return valorUnitarioPago;
	}

	public void setValorUnitarioPago(BigDecimal valorUnitarioPago) {
		this.valorUnitarioPago = valorUnitarioPago;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public ColaboradorEntity getTecnico() {
		return tecnico;
	}

	public void setTecnico(ColaboradorEntity tecnico) {
		this.tecnico = tecnico;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}
	
}
