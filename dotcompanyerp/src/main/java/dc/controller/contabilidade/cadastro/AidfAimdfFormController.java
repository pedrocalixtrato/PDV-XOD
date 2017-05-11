package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.cadastro.AidfAimdfEntity;
import dc.servicos.dao.contabilidade.cadastro.IAidfAimdfDAO;
import dc.visao.contabilidade.cadastro.AidfAimdfFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AidfAimdfFormController extends
		CRUDFormController<AidfAimdfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AidfAimdfFormView subView;

	/** DAO'S */

	@Autowired
	private IAidfAimdfDAO pDAO;

	/** ENTITIES */

	private AidfAimdfEntity pEntity;

	/** CONSTRUTOR */

	public AidfAimdfFormController() {
		if (this.pEntity == null) {
			this.pEntity = new AidfAimdfEntity();
		}
	}

	@Override
	protected String getNome() {
		return "AIDF / AIMDF";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Integer numero = Integer.parseInt(this.subView.getTfNumero()
					.getValue());
			Date dataValidade = this.subView.getPdfDataValidade().getValue();
			Date dataAutorizacao = this.subView.getPdfDataAutorizacao()
					.getValue();
			String numeroAutorizacao = this.subView.getTfNumeroAutorizacao()
					.getValue();
			String formularioDisponivel = this.subView
					.getTfFormularioDisponivel().getValue();

			this.pEntity.setNumero(numero);
			this.pEntity.setDataValidade(dataValidade);
			this.pEntity.setDataAutorizacao(dataAutorizacao);
			this.pEntity.setNumeroAutorizacao(numeroAutorizacao);
			this.pEntity.setFormularioDisponivel(formularioDisponivel);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new AidfAimdfFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
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
		String numero = this.subView.getTfNumero().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(numero)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNumero(), msg);

			return false;
		}

		Object dataValidade = this.subView.getPdfDataValidade().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataValidade)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataValidade(), msg);

			return false;
		}

		Object dataAutorizacao = this.subView.getPdfDataAutorizacao()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAutorizacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAutorizacao(), msg);

			return false;
		}

		return true;
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

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new AidfAimdfEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfNumero().setValue(
					this.pEntity.getNumero().toString());
			this.subView.getPdfDataValidade().setValue(
					this.pEntity.getDataValidade());
			this.subView.getPdfDataAutorizacao().setValue(
					this.pEntity.getDataAutorizacao());
			this.subView.getTfNumeroAutorizacao().setValue(
					this.pEntity.getNumeroAutorizacao());
			this.subView.getTfFormularioDisponivel().setValue(
					this.pEntity.getFormularioDisponivel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AidfAimdfEntity getModelBean() {
		return pEntity;
	}

}