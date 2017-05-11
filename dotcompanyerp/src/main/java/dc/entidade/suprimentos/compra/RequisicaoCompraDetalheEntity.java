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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.produto.ProdutoEntity;

/**
 * The persistent class for the compra_requisicao_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_requisicao_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RequisicaoCompraDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

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
	
	@Column(name = "QUANTIDADE")
    private BigDecimal quantidade;
	
    @Column(name = "QUANTIDADE_COTADA")
    private BigDecimal quantidadeCotada;
    
    @Field
	@Caption("Item Cotado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "ITEM_COTADO")
    private String itemCotado;
    
    @JoinColumn(name = "ID_COMPRA_REQUISICAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RequisicaoCompraEntity requisicao;
    
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoEntity produto;
	

	public RequisicaoCompraDetalheEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public String getItemCotado() {
		return this.itemCotado;
	}

	public void setItemCotado(String itemCotado) {
		this.itemCotado = itemCotado;
	}

	public RequisicaoCompraEntity getRequisicao() {
		return this.requisicao;
	}

	public void setRequisicao(RequisicaoCompraEntity compraRequisicao) {
		this.requisicao = compraRequisicao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadeCotada() {
		return quantidadeCotada;
	}

	public void setQuantidadeCotada(BigDecimal quantidadeCotada) {
		this.quantidadeCotada = quantidadeCotada;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return itemCotado;
	}

}