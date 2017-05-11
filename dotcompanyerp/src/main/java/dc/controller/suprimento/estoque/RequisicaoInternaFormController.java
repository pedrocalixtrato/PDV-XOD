package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.RequisicaoInternaCabecalhoEntity;
import dc.entidade.suprimentos.estoque.RequisicaoInternaDetalheEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.suprimentos.estoque.IRequisicaoInternaCabecalhoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimento.estoque.RequisicaoInternaFormView;

@Controller
@Scope("prototype")
public class RequisicaoInternaFormController extends
		CRUDFormController<RequisicaoInternaCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RequisicaoInternaFormView subView;

	@Autowired
	private IRequisicaoInternaCabecalhoDAO dao;
	
	@Autowired
	private IColaboradorDAO colaboradorDAO;

	private RequisicaoInternaCabecalhoEntity currentBean;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Override
	protected String getNome() {
		return "Requisição Interna";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public ColaboradorEntity buscaColaborador() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity col = usuario.getColaborador();

		return col;
	}

	@Override
	protected void actionSalvar() {
		try {
			dao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = dao.find((Integer) id);
			fieldGroup.setItemDataSource(this.currentBean);
			subView.fillRequisicaoDetalhesSubForm(currentBean.getDetalhes());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new RequisicaoInternaFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(RequisicaoInternaCabecalhoEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getDataRequisicao(),"dataRequisicao");
			fieldGroup.bind(this.subView.getCmbColaborador(),"colaborador");
			
			this.subView.getCmbColaborador().configuraCombo(
					"pessoa.nome", ColaboradorListController.class, this.colaboradorDAO, this.getMainController());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		
		try {
			currentBean = new RequisicaoInternaCabecalhoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		
		}catch(Exception e ) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	public RequisicaoInternaDetalheEntity novoRequisicaoDetalhe() {
		RequisicaoInternaDetalheEntity detalhe = new RequisicaoInternaDetalheEntity();
		currentBean.addRequisicaoDetalhe(detalhe);

		return detalhe;
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public RequisicaoInternaCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}