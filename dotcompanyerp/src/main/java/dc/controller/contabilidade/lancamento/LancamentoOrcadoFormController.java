package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.planoconta.ContaListController;
import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoOrcadoDAO;
import dc.servicos.dao.contabilidade.planoconta.IContaDAO;
import dc.visao.contabilidade.lancamento.LancamentoOrcadoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class LancamentoOrcadoFormController extends
		CRUDFormController<LancamentoOrcadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoOrcadoFormView subView;

	/** DAO'S */

	@Autowired
	private ILancamentoOrcadoDAO pDAO;

	@Autowired
	private IContaDAO cDAO;

	/** ENTITIES */

	private LancamentoOrcadoEntity pEntity;

	/** CONSTRUTOR */

	public LancamentoOrcadoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoOrcadoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento orçado";
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
	
	/*public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(".", "").replace(",", ".");
		return format;
	}*/

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
			this.subView = new LancamentoOrcadoFormView(this);

			this.fieldGroup = new DCFieldGroup<>(LancamentoOrcadoEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTfAno(),"ano");
			fieldGroup.bind(this.subView.getTfJaneiro(),"janeiro");
			fieldGroup.bind(this.subView.getTfFevereiro(),"fevereiro");
			fieldGroup.bind(this.subView.getTfMarco(),"marco");
			fieldGroup.bind(this.subView.getTfAbril(),"abril");
			fieldGroup.bind(this.subView.getTfMaio(),"maio");
			fieldGroup.bind(this.subView.getTfJunho(),"junho");
			fieldGroup.bind(this.subView.getTfJulho(),"julho");
			fieldGroup.bind(this.subView.getTfAgosto(),"agosto");
			fieldGroup.bind(this.subView.getTfSetembro(),"setembro");
			fieldGroup.bind(this.subView.getTfOutubro(),"outubro");
			fieldGroup.bind(this.subView.getTfNovembro(),"novembro");
			fieldGroup.bind(this.subView.getTfDezembro(),"dezembro");
			
			this.subView.getCbConta().configuraCombo(
					"classificacao", ContaListController.class, this.cDAO, this.getMainController());
			
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
			this.pEntity = new LancamentoOrcadoEntity();
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

	/** ************************************** */

	/*private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new LancamentoOrcadoEntity();

				// this.subView.getCbConta().setValue(this.pEntity.getConta());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbConta().setValue(this.pEntity.getConta());
			}

		    //this.subView.getTfAno().setValue(this.pEntity.getAno());
			this.subView.getTfJaneiro().setValue(
					this.pEntity.getJaneiro().toString());
			this.subView.getTfFevereiro().setValue(
					this.pEntity.getFevereiro().toString());
			this.subView.getTfMarco().setValue(
					this.pEntity.getMarco().toString());
			this.subView.getTfAbril().setValue(
					this.pEntity.getAbril().toString());
			this.subView.getTfMaio()
					.setValue(this.pEntity.getMaio().toString());
			this.subView.getTfJunho().setValue(
					this.pEntity.getJunho().toString());
			this.subView.getTfJulho().setValue(
					this.pEntity.getJulho().toString());
			this.subView.getTfAgosto().setValue(
					this.pEntity.getAgosto().toString());
			this.subView.getTfSetembro().setValue(
					this.pEntity.getSetembro().toString());
			this.subView.getTfOutubro().setValue(
					this.pEntity.getOutubro().toString());
			this.subView.getTfNovembro().setValue(
					this.pEntity.getNovembro().toString());
			this.subView.getTfDezembro().setValue(
					this.pEntity.getDezembro().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public LancamentoOrcadoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}