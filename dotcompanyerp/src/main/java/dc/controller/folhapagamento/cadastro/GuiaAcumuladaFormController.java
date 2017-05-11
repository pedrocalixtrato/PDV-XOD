package dc.controller.folhapagamento.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.cadastro.GuiaAcumuladaEntity;
import dc.servicos.dao.folhapagamento.cadastro.GuiaAcumuladaDAO;
import dc.visao.folhapagamento.cadastro.GuiaAcumuladaFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class GuiaAcumuladaFormController extends
		CRUDFormController<GuiaAcumuladaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GuiaAcumuladaFormView subView;

	/** DAO'S */

	@Autowired
	private GuiaAcumuladaDAO pDAO;

	/** ENTITIES */

	private GuiaAcumuladaEntity pEntity;

	/** CONSTRUTOR */

	public GuiaAcumuladaFormController() {
		if (this.pEntity == null) {
			this.pEntity = new GuiaAcumuladaEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Guia acumulada";
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

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			
			this.subView = new GuiaAcumuladaFormView(this);
			this.fieldGroup = new DCFieldGroup<>(GuiaAcumuladaEntity.class);
			
			fieldGroup.bind(this.subView.getTfGpsTipo(),"gpsTipo");
			fieldGroup.bind(this.subView.getTfGpsCompetencia(),"gpsCompetencia");
			fieldGroup.bind(this.subView.getTfGpsValorInss(),"gpsValorInss");
			fieldGroup.bind(this.subView.getTfGpsValorOutrasEnt(),"gpsValorOutrasEnt");
			fieldGroup.bind(this.subView.getPdfGpsDataPagamento(),"gpsDataPagamento");
			fieldGroup.bind(this.subView.getTfIrrfCompetencia(),"irrfCompetencia");
			fieldGroup.bind(this.subView.getTfIrrfCodigoRecolhimento(),"irrfCodigoRecolhimento");
			fieldGroup.bind(this.subView.getTfIrrfValorAcumulado(),"irrfValorAcumulado");
			fieldGroup.bind(this.subView.getPdfIrrfDataPagamento(),"irrfDataPagamento");
			fieldGroup.bind(this.subView.getTfPisCompetencia(),"pisCompetencia");
			fieldGroup.bind(this.subView.getTfPisValorAcumulado(),"pisValorAcumulado");
			fieldGroup.bind(this.subView.getPdfPisDataPagamento(),"pisDataPagamento");
			
		}catch(Exception e) {
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

			this.pEntity = new GuiaAcumuladaEntity();
			
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
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

	/** COMBOS */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	/*private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new GuiaAcumuladaEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfGpsTipo().setValue(this.pEntity.getGpsTipo());
			this.subView.getTfGpsCompetencia().setValue(
					this.pEntity.getGpsCompetencia());
			this.subView.getTfGpsValorInss().setValue(
					this.pEntity.getGpsValorInss().toString());
			this.subView.getTfGpsValorOutrasEnt().setValue(
					this.pEntity.getGpsValorOutrasEnt().toString());
			this.subView.getPdfGpsDataPagamento().setValue(
					this.pEntity.getGpsDataPagamento());
			this.subView.getTfIrrfCompetencia().setValue(
					this.pEntity.getIrrfCompetencia());
			this.subView.getTfIrrfCodigoRecolhimento().setValue(
					this.pEntity.getIrrfCodigoRecolhimento().toString());
			this.subView.getTfIrrfValorAcumulado().setValue(
					this.pEntity.getIrrfValorAcumulado().toString());
			this.subView.getPdfIrrfDataPagamento().setValue(
					this.pEntity.getIrrfDataPagamento());
			this.subView.getTfPisCompetencia().setValue(
					this.pEntity.getPisCompetencia());
			this.subView.getTfPisValorAcumulado().setValue(
					this.pEntity.getPisValorAcumulado().toString());
			this.subView.getPdfPisDataPagamento().setValue(
					this.pEntity.getPisDataPagamento());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	@Override
	public GuiaAcumuladaEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}