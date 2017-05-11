package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.servicos.dao.contabilidade.cadastro.IIndiceDAO;
import dc.servicos.dao.contabilidade.cadastro.IIndiceValorDAO;
import dc.visao.contabilidade.cadastro.IndiceValorFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class IndiceValorFormController extends
		CRUDFormController<IndiceValorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceValorFormView subView;

	/** DAO'S */

	@Autowired
	private IIndiceValorDAO pDAO;

	@Autowired
	private IIndiceDAO iDAO;

	/** ENTITIES */

	private IndiceValorEntity pEntity;

	/** CONSTRUTOR */

	public IndiceValorFormController() {
	}

	@Override
	protected String getNome() {
		return "Índice Valor";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			
			this.pEntity = this.pDAO.find(id);
			
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			        this.subView = new IndiceValorFormView(this);
			
			        // Cria o DCFieldGroup
			        this.fieldGroup = new DCFieldGroup<>(IndiceValorEntity.class);
			        // Mapeia os campos
			        fieldGroup.bind(this.subView.getTfValor(),"valor");
			        fieldGroup.bind(this.subView.getCbIndice(),"indice");
			        
			        this.subView.getCbIndice().configuraCombo(
			        		"periodicidade", IndiceListController.class, this.iDAO, this.getMainController());
			
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}

	@Override
	protected void criarNovoBean() {
		try {
			
			this.pEntity = new IndiceValorEntity();
			
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
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
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public IndiceValorEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}