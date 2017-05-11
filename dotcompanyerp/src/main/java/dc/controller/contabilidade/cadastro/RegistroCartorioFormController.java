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
import dc.entidade.contabilidade.cadastro.RegistroCartorioEntity;
import dc.servicos.dao.contabilidade.cadastro.IRegistroCartorioDAO;
import dc.visao.contabilidade.cadastro.RegistroCartorioFormView;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class RegistroCartorioFormController extends
		CRUDFormController<RegistroCartorioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RegistroCartorioFormView subView;

	/** DAO'S */

	@Autowired
	private IRegistroCartorioDAO pDAO;

	/** ENTITIES */

	private RegistroCartorioEntity pEntity;

	/** CONSTRUTOR */

	public RegistroCartorioFormController() {
		if (this.pEntity == null) {
			this.pEntity = new RegistroCartorioEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Registro de cartório";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String nomeCartorio = this.subView.getTfNomeCartorio().getValue();
			Date dataRegistro = this.subView.getPdfDataRegistro().getValue();
			Integer numero = Integer.parseInt(this.subView.getTfNumero()
					.getValue());
			Integer folha = Integer.parseInt(this.subView.getTfFolha()
					.getValue());
			Integer livro = Integer.parseInt(this.subView.getTfLivro()
					.getValue());
			String nire = this.subView.getTfNire().getValue();

			this.pEntity.setNomeCartorio(nomeCartorio);
			this.pEntity.setDataRegistro(dataRegistro);
			this.pEntity.setNumero(numero);
			this.pEntity.setFolha(folha);
			this.pEntity.setLivro(livro);
			this.pEntity.setNire(nire);

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
		this.subView = new RegistroCartorioFormView(this);
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
		Object dataRegistro = this.subView.getPdfDataRegistro().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataRegistro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataRegistro(), msg);

			return false;
		}

		String numero = this.subView.getTfNumero().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(numero)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNumero(), msg);

			return false;
		}

		String folha = this.subView.getTfFolha().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(folha)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfFolha(), msg);

			return false;
		}

		String livro = this.subView.getTfLivro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(livro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfLivro(), msg);

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
				this.pEntity = new RegistroCartorioEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfNomeCartorio().setValue(
					this.pEntity.getNomeCartorio());
			this.subView.getPdfDataRegistro().setValue(
					this.pEntity.getDataRegistro());
			this.subView.getTfNumero().setValue(
					this.pEntity.getNumero().toString());
			this.subView.getTfFolha().setValue(
					this.pEntity.getFolha().toString());
			this.subView.getTfLivro().setValue(
					this.pEntity.getLivro().toString());
			this.subView.getTfNire().setValue(this.pEntity.getNire());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RegistroCartorioEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}