package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.folhapagamento.movimento.PppCatEntity;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.servicos.dao.folhapagamento.movimento.PppCatDAO;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.visao.folhapagamento.movimento.PppCatFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PppCatFormController extends CRUDFormController<PppCatEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppCatFormView subView;

	/** DAO'S */

	@Autowired
	private PppCatDAO pDAO;

	@Autowired
	private PppDAO pppDAO;

	/** ENTITIES */

	private PppCatEntity pEntity;

	/** CONSTRUTOR */

	public PppCatFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PppCatEntity();
		}
	}

	@Override
	protected String getNome() {
		return "PPP Cat";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Integer numeroCat = Integer.parseInt(this.subView.getTfNumeroCat()
					.getValue());
			Date dataAfastamento = this.subView.getPdfDataAfastamento()
					.getValue();
			Date dataRegistro = this.subView.getPdfDataRegistro().getValue();

			PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

			this.pEntity.setNumeroCat(numeroCat);
			this.pEntity.setDataAfastamento(dataAfastamento);
			this.pEntity.setDataRegistro(dataRegistro);

			this.pEntity.setPpp(ppp);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			try {
				this.pEntity = new PppCatEntity();

				this.subView.getTfNumeroCat().setValue(
						String.valueOf(this.pEntity.getNumeroCat()));
				this.subView.getPdfDataAfastamento().setValue(
						this.pEntity.getDataAfastamento());
				this.subView.getPdfDataRegistro().setValue(
						this.pEntity.getDataRegistro());

				this.subView.carregarCmbPpp(this.pppListarTodos());

				this.subView.getCbPpp().setValue(this.pEntity.getPpp());
			} catch (Exception e) {
				e.printStackTrace();

				mensagemErro(e.getMessage());
			}
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfNumeroCat().setValue(
					String.valueOf(this.pEntity.getNumeroCat()));
			this.subView.getPdfDataAfastamento().setValue(
					this.pEntity.getDataAfastamento());
			this.subView.getPdfDataRegistro().setValue(
					this.pEntity.getDataRegistro());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new PppCatEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getTfNumeroCat().setValue(
					String.valueOf(this.pEntity.getNumeroCat()));
			this.subView.getPdfDataAfastamento().setValue(
					this.pEntity.getDataAfastamento());
			this.subView.getPdfDataRegistro().setValue(
					this.pEntity.getDataRegistro());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PppCatFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PppCatEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getTfNumeroCat().setValue(
					String.valueOf(this.pEntity.getNumeroCat()));
			this.subView.getPdfDataAfastamento().setValue(
					this.pEntity.getDataAfastamento());
			this.subView.getPdfDataRegistro().setValue(
					this.pEntity.getDataRegistro());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
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
		String numeroCat = this.subView.getTfNumeroCat().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(numeroCat)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNumeroCat(), msg);

			return false;
		}

		Object dataAfastamento = this.subView.getPdfDataAfastamento()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAfastamento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAfastamento(), msg);

			return false;
		}

		Object dataRegistro = this.subView.getPdfDataRegistro().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataRegistro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataRegistro(), msg);

			return false;
		}

		PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

		if (!ObjectValidator.validateObject(ppp)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbPpp(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	public List<PppEntity> pppListarTodos() {
		List<PppEntity> auxLista = new ArrayList<PppEntity>();

		auxLista = this.pppDAO.listarTodos();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PppCatEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}