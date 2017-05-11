package dc.entidade.geral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.search.annotations.Analyzer;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "fornecedor_produto")
public class FornecedorProdutoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_produto_id_seq")
	@SequenceGenerator(name = "fornecedor_produto_id_seq", sequenceName = "fornecedor_produto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "FORNECEDOR_ID", nullable = false)
	private int fornecedorId;

	@Basic(optional = false)
	@Column(name = "PRODUTO_ID", nullable = false)
	private int produtoId;

	@Column(name = "CODIGO_FORNECEDOR_PRODUTO")
	private String codigoFornecedorProduto;

	@Column(name = "DATA_ULTIMA_COMPRA")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaCompra;

	@Column(name = "PRECO_ULTIMA_COMPRA", precision = 14, scale = 0)
	private BigDecimal precoUltimaCompra;

	@Column(name = "LOTE_MINIMO_COMPRA", precision = 14, scale = 0)
	private BigDecimal loteMinimoCompra;

	@Column(name = "MENOR_EMBALAGEM_COMPRA", precision = 14, scale = 0)
	private BigDecimal menorEmbalagemCompra;

	@Column(name = "CUSTO_ULTIMA_COMPRA", precision = 14, scale = 0)
	private BigDecimal custoUltimaCompra;

	public FornecedorProdutoEntity() {

	}

	public FornecedorProdutoEntity(Integer id) {
		this.id = id;
	}

	public FornecedorProdutoEntity(Integer id, int fornecedorId, int produtoId) {
		this.id = id;
		this.fornecedorId = fornecedorId;
		this.produtoId = produtoId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public String getCodigoFornecedorProduto() {
		return codigoFornecedorProduto;
	}

	public void setCodigoFornecedorProduto(String codigoFornecedorProduto) {
		this.codigoFornecedorProduto = codigoFornecedorProduto;
	}

	public Date getDataUltimaCompra() {
		return dataUltimaCompra;
	}

	public void setDataUltimaCompra(Date dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public BigDecimal getPrecoUltimaCompra() {
		return precoUltimaCompra;
	}

	public void setPrecoUltimaCompra(BigDecimal precoUltimaCompra) {
		this.precoUltimaCompra = precoUltimaCompra;
	}

	public BigDecimal getLoteMinimoCompra() {
		return loteMinimoCompra;
	}

	public void setLoteMinimoCompra(BigDecimal loteMinimoCompra) {
		this.loteMinimoCompra = loteMinimoCompra;
	}

	public BigDecimal getMenorEmbalagemCompra() {
		return menorEmbalagemCompra;
	}

	public void setMenorEmbalagemCompra(BigDecimal menorEmbalagemCompra) {
		this.menorEmbalagemCompra = menorEmbalagemCompra;
	}

	public BigDecimal getCustoUltimaCompra() {
		return custoUltimaCompra;
	}

	public void setCustoUltimaCompra(BigDecimal custoUltimaCompra) {
		this.custoUltimaCompra = custoUltimaCompra;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}