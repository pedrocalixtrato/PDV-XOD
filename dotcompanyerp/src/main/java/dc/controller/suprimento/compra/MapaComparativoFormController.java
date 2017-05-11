package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.CotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.servicos.dao.suprimentos.compra.CotacaoCompraBusiness;
import dc.servicos.dao.suprimentos.compra.IFornecedorCotacaoDAO;
import dc.servicos.dao.suprimentos.compra.IPedidoCompraDAO;
import dc.servicos.dao.suprimentos.compra.IRequisicaoDetalheDAO;
import dc.servicos.dao.suprimentos.compra.ITipoPedidoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.MapaComparativoFormView;

@Controller
@Scope("prototype")
public class MapaComparativoFormController extends
		CRUDFormController<CotacaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MapaComparativoFormView subView;

	@Autowired
	private IPedidoCompraDAO pedidoCompraDAO;

	@Autowired
	private IFornecedorCotacaoDAO fornecedorCotacaoDao;

	@Autowired
	private IRequisicaoDetalheDAO requisicaoDetalheDao;

	@Autowired
	private ITipoPedidoDAO tipoPedidoDAO;

	private CotacaoCompraEntity currentBean;
	
	@Autowired
	private CotacaoCompraBusiness<CotacaoCompraEntity> business;
	
	public CotacaoCompraBusiness<CotacaoCompraEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Mapa Comparativo";
	}

	@Override
	protected void actionSalvar() {
		
		try {
			business.saveOrUpdate(currentBean);
			
			List<FornecedorCotacaoEntity> fornecedores = currentBean
					.getCompraFornecedorCotacaos();

			for (FornecedorCotacaoEntity cotacao : fornecedores) {
				List<CotacaoDetalheEntity> cotacaoDetalhes = cotacao
						.getCotacaoDetalheList();

				PedidoEntity pedidoCompra = null;

				for (CotacaoDetalheEntity detalhe : cotacaoDetalhes) {
					boolean existePedido = pedidoCompraDAO
							.existsPedidoDetalheByCotacao(detalhe.getId());

					if (!existePedido) {
						if (pedidoCompra == null) {
							pedidoCompra = new PedidoEntity();
							pedidoCompra.setTipoPedido(tipoPedidoDAO.find(4));
							pedidoCompra.setFornecedor(cotacao.getFornecedor());
							pedidoCompra.setTaxaDesconto(cotacao
									.getTaxaDesconto());
							pedidoCompra
									.setValorTotalPedido(cotacao.getTotal());
							pedidoCompra.setValorDesconto(cotacao
									.getValorDesconto());
							pedidoCompra.setValorSubtotal(cotacao
									.getValorSubtotal());
							pedidoCompra.setFormaPagamento(cotacao
									.getVendaCondicaoPagamento());
						}

						PedidoDetalheEntity pedidoDetalhe = new PedidoDetalheEntity();
						pedidoDetalhe.setProduto(detalhe.getProduto());
						pedidoDetalhe.setQuantidade(detalhe
								.getQuantidadePedida());
						pedidoDetalhe.setValorUnitario(detalhe
								.getValorUnitario());
						pedidoDetalhe
								.setTaxaDesconto(detalhe.getTaxaDesconto());
						pedidoDetalhe.setValorDesconto(detalhe
								.getValorDesconto());
						pedidoDetalhe.setValorSubtotal(detalhe
								.getValorUnitario().multiply(
										detalhe.getQuantidadePedida()));
						pedidoDetalhe.setValorTotal(pedidoDetalhe
								.getValorSubtotal().subtract(
										pedidoDetalhe.getValorDesconto()));

						// pedidoCompra.addPedidoDetalhe(pedidoDetalhe);
					}
				}

				pedidoCompraDAO.saveOrUpdate(pedidoCompra);
			}

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = business.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		try {
			subView = new MapaComparativoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(CotacaoCompraEntity.class);
		
     	    fieldGroup.bind(this.subView.getCalDataCotacao(), "dataCotacao");
     	    fieldGroup.bind(this.subView.getTxtDescricao(), "descricao");
     	    
     	  /* this.subView.getCmbFornecedor().configuraCombo(
					"pessoa.nome", FornecedorListController.class, this.fornecedorCotacaoDao, this.getMainController());*/
     	   this.subView.getCmbFornecedor().configuraCombo(
					"fornecedor.pessoa.nome", FornecedorCotacaoListController.class, this.fornecedorCotacaoDao, this.getMainController());
     	   
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new CotacaoCompraEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			CotacaoCompraEntity cotacao = (CotacaoCompraEntity) id;

			try {
				business.delete(cotacao);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	public List<RequisicaoCompraDetalheEntity> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoCompraDetalheEntity.class);
	}
	
	public ReqCotacaoDetalheEntity novoRequisicaoCotacaoDetalhe() {
		ReqCotacaoDetalheEntity cotacaoDetalhe = new ReqCotacaoDetalheEntity();
		currentBean.addReqCotacaoDetalhe(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(List<ReqCotacaoDetalheEntity> values) {
		for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : values) {
			//currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
			currentBean.removeReqCotacaoDetalhe(requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}


	public List<FornecedorEntity> buscarFornecedores() {
		return fornecedorCotacaoDao.getAll(FornecedorEntity.class);
	}

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		//currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);
		currentBean.addFornecedorCotacao(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity fornecedorCotacao : values) {
			//currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
			currentBean.removeFornecedorCotacao(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}
	

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public CotacaoCompraEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}