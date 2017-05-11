package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.lancamento.FechamentoEntity;
import dc.servicos.dao.contabilidade.lancamento.IFechamentoDAO;
import dc.visao.contabilidade.lancamento.FechamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller(value = "contabilidadeFechamentoFormController")
@Scope("prototype")
public class FechamentoFormController extends
		CRUDFormController<FechamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FechamentoFormView subView;

	/** DAO'S */

	@Autowired
	private IFechamentoDAO pDAO;

	/** ENTITIES */

	private FechamentoEntity pEntity;

	/** CONSTRUTOR */

	public FechamentoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FechamentoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Fechamento";
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
			String criterioLancamento = this.subView.getTfCriterioLancamento()
					.getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setCriterioLancamento(criterioLancamento);

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
		this.subView = new FechamentoFormView(this);
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
				this.pEntity = new FechamentoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getTfCriterioLancamento().setValue(
					this.pEntity.getCriterioLancamento());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FechamentoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}