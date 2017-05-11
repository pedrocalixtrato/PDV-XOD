package dc.entidade.suprimentos.estoque;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.RequisicaoInternaCabecalhoEntity;

@Entity
@Table(name = "requisicao_interna_detalhe")
public class RequisicaoInternaDetalheEntity extends
		AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req")
	@SequenceGenerator(name = "req", sequenceName = "requisicao_interna_detalhe_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;

	@ManyToOne
	@JoinColumn(name = "id_req_interna_cabecalho")
	RequisicaoInternaCabecalhoEntity requisicao;

	Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RequisicaoInternaCabecalhoEntity getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(RequisicaoInternaCabecalhoEntity requisicao) {
		this.requisicao = requisicao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

}