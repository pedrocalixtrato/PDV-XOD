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
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.compra.IPedidoDetalheDAO;
import dc.servicos.dao.suprimentos.compra.ITipoPedidoDAO;
import dc.servicos.dao.suprimentos.compra.PedidoCompraBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.PedidoCompraFormView;

@Controller
@Scope("prototype")
public class PedidoCompraFormController extends
		CRUDFormController<PedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PedidoCompraFormView subView;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IFornecedorDAO fornecedorDAO;

	@Autowired
	private ITipoPedidoDAO tipoPedidoDAO;
	
	@Autowired
	private IPedidoDetalheDAO pedidoDetalheDAO;

	private PedidoEntity currentBean;
	
	/**
	 * BUSINESS
	 */
	@Autowired
	private PedidoCompraBusiness<PedidoEntity> business;
	
	public PedidoCompraBusiness<PedidoEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Pedido de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			subView.preencheBean(currentBean);
			business.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
        try {
			
			currentBean = business.find((Integer) id);
			fieldGroup.setItemDataSource(this.currentBean);
			subView.preencheForm(currentBean);
			//List<PedidoDetalheEntity> pedido = pedidoDetalheDAO.findByPedido(currentBean);
			subView.preencheSubForm(currentBean.getPedidoDetalhe());
			subView.fillPedidoDetalhesSubForm(currentBean.getPedidoDetalhe());
			
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		
		 try {
		        this.subView = new PedidoCompraFormView(this);
		
		        // Cria o DCFieldGroup
		        this.fieldGroup = new DCFieldGroup<>(PedidoEntity.class);
		
		        // Mapeia os campos
		        fieldGroup.bind(this.subView.getCalDataPedido(),"dataPedido");
		        fieldGroup.bind(this.subView.getCmbTipoPedido(), "tipoPedido");
		        fieldGroup.bind(this.subView.getCmbFornecedor(), "fornecedor");
		        
		        subView.fillCmbFornecedor(fornecedorDAO.getAll(FornecedorEntity.class));
		        
		        this.subView.getCmbTipoPedido().configuraCombo("nome",
		        		TipoPedidoListController.class, this.tipoPedidoDAO, this.getMainController());
		        
		        
		
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
	}

	@Override
	protected void criarNovoBean() {
        try {
			
        	currentBean = new PedidoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		
		} catch (Exception e) {
			
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
	        // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
	        fieldGroup.commit();
	        return true;
	    } catch (FieldGroup.CommitException ce) {
	        return false;
	    }
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			PedidoEntity pedido = (PedidoEntity) id;

			try {
				business.delete(pedido);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public PedidoDetalheEntity novoPedidoDetalhe() {
		PedidoDetalheEntity pedidoDetalhe = new PedidoDetalheEntity();
		currentBean.addPedidoDetalhe(pedidoDetalhe);

		return pedidoDetalhe;
	}

	public void removerPedidoDetalhe(List<PedidoDetalheEntity> pedidoDetalhe) {
		for (PedidoDetalheEntity ent : pedidoDetalhe) {
			currentBean.removePedidoDetalhe(ent);
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
	public PedidoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}