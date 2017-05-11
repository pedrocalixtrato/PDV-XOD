package dc.entidade.ordemservico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "os_orcamento_item")
@SuppressWarnings("serial")
public class OrcamentoOsItemEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oos")
	@SequenceGenerator(name = "oos", sequenceName = "os_orcamento_item_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	private OrcamentoOsEntity orcamentoOs;

	@Field
	@Caption("TIPO")
	@Column(name = "tipo")
	private String tipo;

	@Field
	@Caption("DESCRICAO")
	@Column(name = "descricao")
	private String descricao;

	@Field
	@Caption("QUANTIDADE")
	@Column(name = "qtd_produto")
	private BigDecimal quantidade;

	@Column(name = "vlr_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "vlr_subtotal")
	private BigDecimal valorSubTotal;

	@Column(name = "taxa_desconto")
	private BigDecimal taxaDesconto;

	@Column(name = "vlr_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "vlr_total")
	private BigDecimal valorTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrcamentoOsEntity getOrcamentoOs() {
		return orcamentoOs;
	}

	public void setOrcamentoOs(OrcamentoOsEntity orcamentoOs) {
		this.orcamentoOs = orcamentoOs;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrcamentoOsItemEntity other = (OrcamentoOsItemEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
