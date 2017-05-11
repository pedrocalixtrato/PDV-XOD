package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.tabela.CboListController;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.business.geral.pessoal.CargoBusiness;
import dc.servicos.dao.geral.tabela.ICboDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.CargoFormView;

@Controller
@Scope("prototype")
public class CargoFormController extends CRUDFormController<CargoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CargoFormView subView;

	/**
	 * ENTITY
	 */

	private CargoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private CargoBusiness<CargoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ICboDAO cboDAO;

	/**
	 * CONSTRUTOR
	 */

	public CargoFormController() {
		// TODO Auto-generated constructor stub
	}

	public CargoBusiness<CargoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Cargo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public CargoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CargoFormView();
			
            this.fieldGroup = new DCFieldGroup<>(CargoEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			//fieldGroup.bind(this.subView.getTfSalario(),"salario");
			
			this.subView.getMocCbo1994().configuraCombo(
					"nome", CboListController.class, this.cboDAO, this.getMainController());
			this.subView.getMocCbo2002().configuraCombo(
					"nome", CboListController.class, this.cboDAO, this.getMainController());

		} catch (Exception e) {
			e.printStackTrace();
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
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new CargoEntity();
			fieldGroup.setItemDataSource(this.entity);
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
		}
	}

		@Override
		protected void removerEmCascata(List<Serializable> objetos) {
			for (Serializable id : objetos) {
				CargoEntity cargo = (CargoEntity) id;

				try {
					business.delete(cargo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
		}
}