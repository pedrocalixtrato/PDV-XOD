package dc.entidade.suprimentos.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.produto.ProdutoEntity;

public class FormacaoPrecoEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProdutoEntity produto;
    private BigDecimal valorCompra;
    private BigDecimal encargoVenda;
    private BigDecimal markup;
    private BigDecimal valorVenda;
    
    @OneToMany(mappedBy = "formacaoPreco", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<FormacaoPrecoEntity> detalhes = new ArrayList<FormacaoPrecoEntity>();

    public FormacaoPrecoEntity() {
    }
    
    public List<FormacaoPrecoEntity> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<FormacaoPrecoEntity> detalhes) {
		this.detalhes = detalhes;
	}

    /**
     * @return the produto
     */
    public ProdutoEntity getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    /**
     * @return the valorCompra
     */
    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * @return the encargoVenda
     */
    public BigDecimal getEncargoVenda() {
        return encargoVenda;
    }

    /**
     * @param encargoVenda the encargoVenda to set
     */
    public void setEncargoVenda(BigDecimal encargoVenda) {
        this.encargoVenda = encargoVenda;
    }

    /**
     * @return the markup
     */
    public BigDecimal getMarkup() {
        return markup;
    }

    /**
     * @param markup the markup to set
     */
    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    /**
     * @return the valorVenda
     */
    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FormacaoPrecoEntity addDetalhe(FormacaoPrecoEntity detalhe) {
		//getDetalhes().add(detalhe);
		//detalhe.setReajuste(this);
		return detalhe;
	}

}
