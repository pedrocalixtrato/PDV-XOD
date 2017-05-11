package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
import dc.entidade.geral.pessoal.FornecedorEntity;

@Entity
@Table(name = "compra_fornecedor_cotacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FornecedorCotacaoEntity extends AbstractMultiEmpresaModel<Integer> {

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
	@Caption("Prazo de entrega")
	@Column(name = "prazo_entrega")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String prazoEntrega = "";

	@Field
	@Caption("Taxa de desconto")
	@Column(name = "taxa_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto = new BigDecimal(0);

	@Field
	@Caption("Total")
	@Column(name = "total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal total = new BigDecimal(0);

	@Field
	@Caption("Valor de desconto")
	@Column(name = "valor_desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Caption("Valor - Subtotal")
	@Column(name = "valor_subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal = new BigDecimal(0);

	@Field
	@Caption("Venda - Condições de pagamento")
	@Column(name = "venda_condicoes_pagamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String vendaCondicaoPagamento = "";

	/**
	 * REFERENCIA - FK
	 */

	
	@JoinColumn(name = "ID_COMPRA_COTACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	//@NotNull(message = "Compra Cotação é Obrigatório!")
    private CotacaoCompraEntity cotacao;
	
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Caption("Fornecedor")
    //@NotNull(message = "Fornecedor é Obrigatório!")
    private FornecedorEntity fornecedor;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "fornecedorCotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<CotacaoDetalheEntity> cotacaoDetalheList = new ArrayList<>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public FornecedorCotacaoEntity() {
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

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = (prazoEntrega == null ? "".trim() : prazoEntrega
				.toUpperCase().trim());
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = (taxaDesconto == null ? new BigDecimal(0)
				: taxaDesconto);
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = (total == null ? new BigDecimal(0) : total);
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

	public String getVendaCondicaoPagamento() {
		return vendaCondicaoPagamento;
	}

	public void setVendaCondicaoPagamento(String vendaCondicaoPagamento) {
		this.vendaCondicaoPagamento = (vendaCondicaoPagamento == null ? ""
				.trim() : vendaCondicaoPagamento.toUpperCase().trim());
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public CotacaoCompraEntity getCotacao() {
		return cotacao;
	}

	public void setCotacao(CotacaoCompraEntity cotacao) {
		this.cotacao = cotacao;
	}

	public List<CotacaoDetalheEntity> getCotacaoDetalheList() {
		return cotacaoDetalheList;
	}

	public void setCotacaoDetalheList(
			List<CotacaoDetalheEntity> cotacaoDetalheList) {
		this.cotacaoDetalheList = cotacaoDetalheList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}