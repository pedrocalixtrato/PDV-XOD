package dc.controller.contabilidade.livrocontabil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.ILivroDAO;
import dc.servicos.dao.contabilidade.livrocontabil.ITermoDAO;
import dc.visao.contabilidade.livrocontabil.TermoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TermoFormController extends CRUDFormController<TermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TermoFormView subView;

	/** DAO'S */

	@Autowired
	private ITermoDAO pDAO;

	@Autowired
	private ILivroDAO lDAO;

	/** ENTITIES */

	private TermoEntity pEntity;

	/** CONSTRUTOR */

	public TermoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new TermoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Termo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String aberturaEncerramento = this.subView
					.getTfAberturaEncerramento().getValue();
			String numero = this.subView.getTfNumero().getValue();
			String paginaInicial = this.subView.getTfPaginaInicial().getValue();
			String paginaFinal = this.subView.getTfPaginaFinal().getValue();
			String registrado = this.subView.getTfRegistrado().getValue();
			String numeroRegistro = this.subView.getTfNumeroRegistro()
					.getValue();
			Date dataDespacho = this.subView.getPdfDataDespacho().getValue();
			Date dataAbertura = this.subView.getPdfDataAbertura().getValue();
			Date dataEncerramento = this.subView.getPdfDataEncerramento()
					.getValue();
			Date escrituracaoInicio = this.subView.getPdfEscrituracaoInicio()
					.getValue();
			Date escrituracaoFim = this.subView.getPdfEscrituracaoFim()
					.getValue();
			String texto = this.subView.getTfTexto().getValue();

			LivroEntity livro = this.subView.getCbLivro().getValue();

			this.pEntity.setAberturaEncerramento(aberturaEncerramento);
			this.pEntity.setNumero(Integer.parseInt(numero));
			this.pEntity.setPaginaInicial(Integer.parseInt(paginaInicial));
			this.pEntity.setPaginaFinal(Integer.parseInt(paginaFinal));
			this.pEntity.setRegistrado(registrado);
			this.pEntity.setNumeroRegistro(numeroRegistro);
			this.pEntity.setDataDespacho(dataDespacho);
			this.pEntity.setDataAbertura(dataAbertura);
			this.pEntity.setDataEncerramento(dataEncerramento);
			this.pEntity.setEscrituracaoInicio(escrituracaoInicio);
			this.pEntity.setEscrituracaoFim(escrituracaoFim);
			this.pEntity.setTexto(texto);

			this.pEntity.setLivro(livro);

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
		this.subView = new TermoFormView(this);

		popularCombo();
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

		String paginaInicial = this.subView.getTfPaginaInicial().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(paginaInicial)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPaginaInicial(), msg);

			return false;
		}

		String paginaFinal = this.subView.getTfPaginaFinal().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(paginaFinal)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPaginaFinal(), msg);

			return false;
		}

		Object dataDespacho = this.subView.getPdfDataDespacho().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataDespacho)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataDespacho(), msg);

			return false;
		}

		Object dataAbertura = this.subView.getPdfDataAbertura().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAbertura)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAbertura(), msg);

			return false;
		}

		Object dataEncerramento = this.subView.getPdfDataEncerramento()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataEncerramento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataEncerramento(), msg);

			return false;
		}

		Object escrituracaoInicio = this.subView.getPdfEscrituracaoInicio()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(escrituracaoInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfEscrituracaoInicio(),
					msg);

			return false;
		}

		Object escrituracaoFim = this.subView.getPdfEscrituracaoFim()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(escrituracaoFim)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfEscrituracaoFim(), msg);

			return false;
		}

		/** REQUIRED */

		LivroEntity livro = this.subView.getCbLivro().getValue();

		if (!ObjectValidator.validateObject(livro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbLivro(), msg);

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

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<LivroEntity> model = new DefaultManyToOneComboModel<LivroEntity>(
					LivroListController.class, this.lDAO,
					super.getMainController());

			this.subView.getCbLivro().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new TermoEntity();

				// this.subView.getCbLivro().setValue(new LivroEntity());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbLivro().setValue(this.pEntity.getLivro());
			}

			this.subView.getTfAberturaEncerramento().setValue(
					this.pEntity.getAberturaEncerramento());
			this.subView.getTfNumero().setValue(
					this.pEntity.getNumero().toString());
			this.subView.getTfPaginaInicial().setValue(
					this.pEntity.getPaginaInicial().toString());
			this.subView.getTfPaginaFinal().setValue(
					this.pEntity.getPaginaFinal().toString());
			this.subView.getTfRegistrado().setValue(
					this.pEntity.getRegistrado());
			this.subView.getTfNumeroRegistro().setValue(
					this.pEntity.getNumeroRegistro());
			this.subView.getPdfDataDespacho().setValue(
					this.pEntity.getDataDespacho());
			this.subView.getPdfDataAbertura().setValue(
					this.pEntity.getDataAbertura());
			this.subView.getPdfDataEncerramento().setValue(
					this.pEntity.getDataEncerramento());
			this.subView.getPdfEscrituracaoInicio().setValue(
					this.pEntity.getEscrituracaoInicio());
			this.subView.getPdfEscrituracaoFim().setValue(
					this.pEntity.getEscrituracaoFim());
			this.subView.getTfTexto().setValue(this.pEntity.getTexto());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TermoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}