package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.ServicoOsEntity;
import dc.model.business.ordemservico.ServicoOsBusiness;
import dc.model.dao.ordemservico.ISubGrupoOsDAO;
import dc.servicos.dao.ordemservico.IGrupoOsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ServicoOsFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class ServicoOsFormController extends CRUDFormController<ServicoOsEntity> {

	private static final long serialVersionUID = 1L;

	private ServicoOsFormView subView;

	private ServicoOsEntity currentBean;
	
	@Autowired
	private IGrupoOsDAO grupoOsDAO;
	
	@Autowired
	private ISubGrupoOsDAO subGrupoOsDAO;

	/**
	 * BUSINESS
	 */
	@Autowired
	private ServicoOsBusiness<ServicoOsEntity> business;

	//@Autowired
	//private GrupoOsBusiness<GrupoOsEntity> businessGrupoOs;

	//@Autowired
	//private SubGrupoOsBusiness<SubGrupoOsEntity> businessSubGrupoOs;

	/**
	 * CONSTRUTOR
	 */
	public ServicoOsFormController() {
	}

	public ServicoOsBusiness<ServicoOsEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Serviço OS";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public ServicoOsEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ServicoOsFormView(this);
			this.fieldGroup = new DCFieldGroup<>(ServicoOsEntity.class);

        fieldGroup.bind(this.subView.getTfNome(), "nome");
        fieldGroup.bind(this.subView.getOptTipoComissaoVendedor(), "tipoComissaoVendedor");
        fieldGroup.bind(this.subView.getOptTipoComissaoTecnico(), "tipoComissaoTecnico");
        fieldGroup.bind(this.subView.getCbGrupo(), "grupo");
        fieldGroup.bind(this.subView.getCbSubGrupo(), "subGrupo");

        // Configura os ManyToOneComboFields
        this.subView.getCbGrupo().configuraCombo(
                "nome", GrupoOsListController.class, this.grupoOsDAO, this.getMainController());

        this.subView.getCbSubGrupo().configuraCombo(
                "nome", SubGrupoListController.class, this.subGrupoOsDAO, this.getMainController());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
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
			this.currentBean = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
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
			currentBean = new ServicoOsEntity();
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/*private void preencheCombos() {
		
		DefaultManyToOneComboModel<GrupoOsEntity> modelNcm = new DefaultManyToOneComboModel<GrupoOsEntity>(
				GrupoOsListController.class, this.grupoOsDAO, super.getMainController()) {
		            @Override
		            public String getCaptionProperty() {
		                return "nome";
		            }
		        };
   	  this.subView.getCbGrupo().setModel(modelNcm);
	
	DefaultManyToOneComboModel<SubGrupoOsEntity> model = new DefaultManyToOneComboModel<SubGrupoOsEntity>(
			SubGrupoOsListController.class, this.subGrupoOsDAO, super.getMainController()) {
	            @Override
	            public String getCaptionProperty() {
	                return "nome";
	            }
	        };
	  this.subView.getCbSubGrupo().setModel(model);

}*/

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
			ServicoOsEntity servicoOs = (ServicoOsEntity) id;

			try {
				business.delete(servicoOs);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	/*public String formataMoeda(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).replaceAll(",", ".").trim();
		return format;
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(",", ".");
		return format;
	}*/
}
