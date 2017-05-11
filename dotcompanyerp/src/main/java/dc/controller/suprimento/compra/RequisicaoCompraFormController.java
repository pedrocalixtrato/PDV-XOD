package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.suprimentos.compra.ITipoRequisicaoDAO;
import dc.servicos.dao.suprimentos.compra.RequisicaoCompraBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.RequisicaoCompraFormView;

@Controller
@Scope("prototype")
public class RequisicaoCompraFormController extends CRUDFormController<RequisicaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RequisicaoCompraFormView subView;

	//@Autowired
	//private IRequisicaoDAO requisicaoDAO;

	@Autowired
	private ITipoRequisicaoDAO tipoRequisicaoDAO;

	@Autowired
	private IColaboradorDAO colaboradorDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	private RequisicaoCompraEntity currentBean;
	
	/**
	 * BUSINESS
	 */
	@Autowired
	private RequisicaoCompraBusiness<RequisicaoCompraEntity> business;
	
	public RequisicaoCompraBusiness<RequisicaoCompraEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Requisição de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
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
			subView.fillRequisicaoDetalhesSubForm(currentBean.getRequisicaoDetalhes());
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			        this.subView = new RequisicaoCompraFormView(this);
			
			        // Cria o DCFieldGroup
			        this.fieldGroup = new DCFieldGroup<>(RequisicaoCompraEntity.class);
			
			        // Mapeia os campos
			        fieldGroup.bind(this.subView.getCmbColaborador(),"colaborador");
			        fieldGroup.bind(this.subView.getCalDataRequisicao(),"dataRequisicao");
			        fieldGroup.bind(this.subView.getCmbTipoRequisicao(), "tipoRequisicao");
			        
			        this.subView.getCmbColaborador().configuraCombo("pessoa.nome", 
			        		ColaboradorListController.class, this.colaboradorDAO, this.getMainController());
			        this.subView.getCmbTipoRequisicao().configuraCombo("nome",
			        		TipoRequisicaoListController.class, this.tipoRequisicaoDAO, this.getMainController());
			        
			        
			
			    } catch (Exception e) {
			       e.printStackTrace();
			    }
	}

	@Override
	protected void criarNovoBean() {
		
        try {
			
        	currentBean = new RequisicaoCompraEntity();
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
			RequisicaoCompraEntity requisicaoCompra = (RequisicaoCompraEntity) id;

			try {
				business.delete(requisicaoCompra);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	public RequisicaoCompraDetalheEntity novoRequisicaoDetalhe() {
		RequisicaoCompraDetalheEntity requisicaoDetalhe = new RequisicaoCompraDetalheEntity();
		currentBean.addRequisicaoDetalhe(requisicaoDetalhe);

		return requisicaoDetalhe;
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public void removerRequisicaoDetalhes(
			List<RequisicaoCompraDetalheEntity> requisicaoDetalhes) {
		for (RequisicaoCompraDetalheEntity requisicaoDetalhe : requisicaoDetalhes) {
			currentBean.removeRequisicaoDetalhe(requisicaoDetalhe);
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
	public RequisicaoCompraEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}