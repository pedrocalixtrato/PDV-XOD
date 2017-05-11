package dc.controller.folhapagamento.cadastro;

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
import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.folhapagamento.cadastro.PlanoSaudeDAO;
import dc.servicos.dao.geral.diverso.OperadoraPlanoSaudeDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.visao.folhapagamento.cadastro.PlanoSaudeFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PlanoSaudeFormController extends
		CRUDFormController<PlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoSaudeFormView subView;

	/** DAO'S */

	@Autowired
	private PlanoSaudeDAO pDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	@Autowired
	private OperadoraPlanoSaudeDAO opsDAO;

	/** ENTITIES */

	private PlanoSaudeEntity pEntity;

	/** CONSTRUTOR */

	public PlanoSaudeFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PlanoSaudeEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Plano de saúde";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			String beneficiario = this.subView.getTfBeneficiario().getValue();

			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
					.getCbColaborador().getValue();
			OperadoraPlanoSaudeEntity operadoraPlanoSaude = (OperadoraPlanoSaudeEntity) this.subView
					.getCbOperadoraPlanoSaude().getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setBeneficiario(beneficiario);

			this.pEntity.setColaborador(colaborador);
			this.pEntity.setOperadoraPlanoSaude(operadoraPlanoSaude);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new PlanoSaudeEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getTfBeneficiario().setValue(
					this.pEntity.getBeneficiario());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbOperadoraPlanoSaude(this
					.operadoraPlanoSaudeListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbOperadoraPlanoSaude().setValue(
					this.pEntity.getOperadoraPlanoSaude());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getTfBeneficiario().setValue(
					this.pEntity.getBeneficiario());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbOperadoraPlanoSaude(this
					.operadoraPlanoSaudeListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbOperadoraPlanoSaude().setValue(
					this.pEntity.getOperadoraPlanoSaude());
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
			this.pEntity = new PlanoSaudeEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getTfBeneficiario().setValue(
					this.pEntity.getBeneficiario());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbOperadoraPlanoSaude(this
					.operadoraPlanoSaudeListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbOperadoraPlanoSaude().setValue(
					this.pEntity.getOperadoraPlanoSaude());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PlanoSaudeFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PlanoSaudeEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getTfBeneficiario().setValue(
					this.pEntity.getBeneficiario());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbOperadoraPlanoSaude(this
					.operadoraPlanoSaudeListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbOperadoraPlanoSaude().setValue(
					this.pEntity.getOperadoraPlanoSaude());
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

		/** REQUIRED */

		ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
				.getCbColaborador().getValue();

		if (!ObjectValidator.validateObject(colaborador)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbColaborador(), msg);

			return false;
		}

		OperadoraPlanoSaudeEntity operadoraPlanoSaude = (OperadoraPlanoSaudeEntity) this.subView
				.getCbOperadoraPlanoSaude().getValue();

		if (!ObjectValidator.validateObject(operadoraPlanoSaude)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbOperadoraPlanoSaude(),
					msg);

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

	public List<ColaboradorEntity> colaboradorListarTodos() {
		List<ColaboradorEntity> auxLista = new ArrayList<ColaboradorEntity>();

		auxLista = this.cDAO.colaboradorLista();

		return auxLista;
	}

	public List<OperadoraPlanoSaudeEntity> operadoraPlanoSaudeListarTodos() {
		List<OperadoraPlanoSaudeEntity> auxLista = new ArrayList<OperadoraPlanoSaudeEntity>();

		auxLista = this.opsDAO.operadoraPlanoSaudeLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PlanoSaudeEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}