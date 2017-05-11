package dc.controller.contabilidade.planoconta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.visao.contabilidade.planoconta.PlanoContaFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller(value = "contabilidadePlanoContaFormController")
@Scope("prototype")
public class PlanoContaFormController extends
		CRUDFormController<PlanoContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoContaFormView subView;

	/** DAO'S */

	@Autowired
	private IPlanoContaDAO pDAO;

	/** ENTITIES */

	private PlanoContaEntity pEntity;

	/** CONSTRUTOR */

	public PlanoContaFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PlanoContaEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Plano conta";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String nome = this.subView.getTfNome().getValue();
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			String mascara = this.subView.getTfMascara().getValue();
			Integer niveis = Integer.parseInt(this.subView.getTfNiveis()
					.getValue());

			this.pEntity.setNome(nome);
			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setMascara(mascara);
			this.pEntity.setNiveis(niveis);

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
		this.subView = new PlanoContaFormView(this);
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

		String niveis = this.subView.getTfNiveis().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(niveis)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNiveis(), msg);

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
				this.pEntity = new PlanoContaEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getTfMascara().setValue(this.pEntity.getMascara());
			this.subView.getTfNiveis().setValue(
					this.pEntity.getNiveis().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PlanoContaEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}