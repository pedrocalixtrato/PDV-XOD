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
import dc.entidade.folhapagamento.movimento.PppAtividadeEntity;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.servicos.dao.folhapagamento.movimento.PppAtividadeDAO;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.visao.folhapagamento.movimento.PppAtividadeFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PppAtividadeFormController extends
		CRUDFormController<PppAtividadeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppAtividadeFormView subView;

	/** DAO'S */

	@Autowired
	private PppAtividadeDAO pDAO;

	@Autowired
	private PppDAO pppDAO;

	/** ENTITIES */

	private PppAtividadeEntity pEntity;

	/** CONSTRUTOR */

	public PppAtividadeFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PppAtividadeEntity();
		}
	}

	@Override
	protected String getNome() {
		return "PPP Atividade";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			Date dataFim = this.subView.getPdfDataTermino().getValue();
			String descricao = this.subView.getTfDescricao().getValue();

			PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setDescricao(descricao);

			this.pEntity.setPpp(ppp);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new PppAtividadeEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataFim());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataFim());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());

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
			this.pEntity = new PppAtividadeEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataFim());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PppAtividadeFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PppAtividadeEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataFim());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());

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
		Object dataInicio = this.subView.getPdfDataInicio().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInicio(), msg);

			return false;
		}

		Object dataTermino = this.subView.getPdfDataTermino().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataTermino)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataTermino(), msg);

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
	public PppAtividadeEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}