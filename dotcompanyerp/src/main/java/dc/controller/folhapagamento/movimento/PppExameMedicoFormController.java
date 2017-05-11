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
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.entidade.folhapagamento.movimento.PppExameMedicoEntity;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.servicos.dao.folhapagamento.movimento.PppExameMedicoDAO;
import dc.visao.folhapagamento.movimento.PppExameMedicoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PppExameMedicoFormController extends
		CRUDFormController<PppExameMedicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppExameMedicoFormView subView;

	/** DAO'S */

	@Autowired
	private PppExameMedicoDAO pDAO;

	@Autowired
	private PppDAO pppDAO;

	/** ENTITIES */

	private PppExameMedicoEntity pEntity;

	/** CONSTRUTOR */

	public PppExameMedicoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PppExameMedicoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "PPP Exame Médico";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataUltimo = this.subView.getPdfDataUltimo().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String natureza = this.subView.getTfNatureza().getValue();
			String exame = this.subView.getTfExame().getValue();
			String indicacaoResultados = this.subView
					.getTfIndicacaoResultados().getValue();

			PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

			this.pEntity.setDataUltimo(dataUltimo);
			this.pEntity.setTipo(tipo);
			this.pEntity.setNatureza(natureza);
			this.pEntity.setExame(exame);
			this.pEntity.setIndicacaoResultados(indicacaoResultados);

			this.pEntity.setPpp(ppp);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new PppExameMedicoEntity();

			this.subView.getPdfDataUltimo().setValue(
					this.pEntity.getDataUltimo());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfExame().setValue(this.pEntity.getExame());
			this.subView.getTfIndicacaoResultados().setValue(
					this.pEntity.getIndicacaoResultados());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataUltimo().setValue(
					this.pEntity.getDataUltimo());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfExame().setValue(this.pEntity.getExame());
			this.subView.getTfIndicacaoResultados().setValue(
					this.pEntity.getIndicacaoResultados());

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
			this.pEntity = new PppExameMedicoEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataUltimo().setValue(
					this.pEntity.getDataUltimo());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfExame().setValue(this.pEntity.getExame());
			this.subView.getTfIndicacaoResultados().setValue(
					this.pEntity.getIndicacaoResultados());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PppExameMedicoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PppExameMedicoEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataUltimo().setValue(
					this.pEntity.getDataUltimo());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfExame().setValue(this.pEntity.getExame());
			this.subView.getTfIndicacaoResultados().setValue(
					this.pEntity.getIndicacaoResultados());

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
		Object dataUltimo = this.subView.getPdfDataUltimo().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataUltimo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataUltimo(), msg);

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
	public PppExameMedicoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}