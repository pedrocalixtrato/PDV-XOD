package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.StatusParcela;
import dc.model.business.financeiro.StatusParcelaBusiness;
import dc.visao.financeiro.StatusParcelaFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class StatusParcelaFormController extends CRUDFormController<StatusParcela> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StatusParcelaFormView subView;

	@Autowired
	private StatusParcelaBusiness<StatusParcela> business;

	private StatusParcela currentBean;
	
	public StatusParcelaFormController() {
		// TODO Auto-generated constructor stub
	}

	public StatusParcelaBusiness<StatusParcela> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Status Parcela";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			//statusParcelaDAO.saveOrUpdate(currentBean);
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
		      //this.currentBean = this.statusParcelaDAO.find(id);
		      this.currentBean = this.business.find(id);
			
		      // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
		      fieldGroup.setItemDataSource(this.currentBean);
			
		} catch (Exception e) {
		      e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}


	@Override
	protected void initSubView() {
        try {
			
			this.subView = new StatusParcelaFormView(this);
			
			this.fieldGroup = new DCFieldGroup<StatusParcela>(StatusParcela.class);
			
			fieldGroup.bind(this.subView.getTxtSituacao(),"situacao");
			fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
			//fieldGroup.bind(this.subView.getTxtProcedimento(),"procedimento");
			
		}catch(Exception e ) {
			e.printStackTrace();
		}

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			
			this.currentBean = new StatusParcela();
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch(Exception e ) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			this.business.deleteAll(ids);
			//this.statusParcelaDAO.deleteAll(ids);

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
		
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public StatusParcela getModelBean() {
		return currentBean;
	}
}
