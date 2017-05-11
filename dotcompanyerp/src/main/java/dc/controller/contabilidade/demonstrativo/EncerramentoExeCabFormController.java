package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IEncerramentoExeCabDAO;
import dc.visao.contabilidade.demonstrativo.EncerramentoExeCabFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class EncerramentoExeCabFormController extends
		CRUDFormController<EncerramentoExeCabEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EncerramentoExeCabFormView subView;

	/** DAO'S */

	@Autowired
	private IEncerramentoExeCabDAO pDAO;

	/** ENTITIES */

	private EncerramentoExeCabEntity pEntity;

	/** CONSTRUTOR */

	public EncerramentoExeCabFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EncerramentoExeCabEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Encerramento de exercício cabeçalho";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			Date dataFim = this.subView.getPdfDataFim().getValue();
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			String motivo = this.subView.getTfMotivo().getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setMotivo(motivo);

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
		this.subView = new EncerramentoExeCabFormView(this);
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
		Object dataInicio = this.subView.getPdfDataInicio().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInicio(), msg);

			return false;
		}

		Object dataFim = this.subView.getPdfDataFim().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataFim)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataFim(), msg);

			return false;
		}

		Object dataInclusao = this.subView.getPdfDataInclusao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataInclusao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInclusao(), msg);

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
				this.pEntity = new EncerramentoExeCabEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EncerramentoExeCabEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}