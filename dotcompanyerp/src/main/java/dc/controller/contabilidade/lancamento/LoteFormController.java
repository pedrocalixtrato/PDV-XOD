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
import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.contabilidade.lancamento.ILoteDAO;
import dc.visao.contabilidade.lancamento.LoteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class LoteFormController extends CRUDFormController<LoteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoteFormView subView;

	/** DAO'S */

	@Autowired
	private ILoteDAO pDAO;

	/** ENTITIES */

	private LoteEntity pEntity;

	/** CONSTRUTOR */

	public LoteFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LoteEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lote";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricao = this.subView.getTfDescricao().getValue();
			String liberado = this.subView.getTfLiberado().getValue();
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			Date dataLiberacao = this.subView.getPdfDataLiberacao().getValue();
			String programado = this.subView.getTfProgramado().getValue();

			this.pEntity.setDescricao(descricao);
			this.pEntity.setLiberado(liberado);
			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setDataLiberacao(dataLiberacao);
			this.pEntity.setProgramado(programado);

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
		this.subView = new LoteFormView(this);
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
		Object dataInclusao = this.subView.getPdfDataInclusao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataInclusao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInclusao(), msg);

			return false;
		}

		Object dataLiberacao = this.subView.getPdfDataLiberacao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataLiberacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataLiberacao(), msg);

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
				this.pEntity = new LoteEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfLiberado().setValue(this.pEntity.getLiberado());
			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getPdfDataLiberacao().setValue(
					this.pEntity.getDataLiberacao());
			this.subView.getTfProgramado().setValue(
					this.pEntity.getProgramado());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LoteEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}