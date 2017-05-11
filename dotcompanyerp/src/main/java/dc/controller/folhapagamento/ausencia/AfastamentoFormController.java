package dc.controller.folhapagamento.ausencia;

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
import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.folhapagamento.ausencia.IAfastamentoDAO;
import dc.servicos.dao.folhapagamento.ausencia.ITipoAfastamentoDAO;
import dc.visao.folhapagamento.ausencia.AfastamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AfastamentoFormController extends
		CRUDFormController<AfastamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AfastamentoFormView subView;

	/** DAO'S */

	@Autowired
	private IAfastamentoDAO pDAO;

	@Autowired
	private IColaboradorDAO cDAO;

	@Autowired
	private ITipoAfastamentoDAO taDAO;

	/** ENTITIES */

	private AfastamentoEntity pEntity;

	/** CONSTRUTOR */

	public AfastamentoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new AfastamentoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Afastamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Integer diasAfastado = Integer.parseInt(this.subView
					.getTfDiasAfastado().getValue());
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			Date dataFim = this.subView.getPdfDataFim().getValue();

			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
					.getCbColaborador().getValue();
			TipoAfastamentoEntity tipoAfastamento = (TipoAfastamentoEntity) this.subView
					.getCbTipoAfastamento().getValue();

			this.pEntity.setDiasAfastado(diasAfastado);
			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setColaborador(colaborador);
			this.pEntity.setTipoAfastamento(tipoAfastamento);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new AfastamentoEntity();

			this.subView.getTfDiasAfastado().setValue(
					String.valueOf(this.pEntity.getDiasAfastado()));
			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTipoAfastamento(this
					.tipoAfastamentoListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTipoAfastamento().setValue(
					this.pEntity.getTipoAfastamento());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfDiasAfastado().setValue(
					String.valueOf(this.pEntity.getDiasAfastado()));
			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTipoAfastamento(this
					.tipoAfastamentoListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTipoAfastamento().setValue(
					this.pEntity.getTipoAfastamento());
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
			this.pEntity = new AfastamentoEntity();

			this.subView.getTfDiasAfastado().setValue(
					String.valueOf(this.pEntity.getDiasAfastado()));
			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTipoAfastamento(this
					.tipoAfastamentoListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTipoAfastamento().setValue(
					this.pEntity.getTipoAfastamento());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new AfastamentoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new AfastamentoEntity();

			this.subView.getTfDiasAfastado().setValue(
					String.valueOf(this.pEntity.getDiasAfastado()));
			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTipoAfastamento(this
					.tipoAfastamentoListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTipoAfastamento().setValue(
					this.pEntity.getTipoAfastamento());
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

		Object dataFim = this.subView.getPdfDataFim().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataFim)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataFim(), msg);

			return false;
		}

		String diasAfastado = this.subView.getTfDiasAfastado().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(diasAfastado)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfDiasAfastado(), msg);

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

		TipoAfastamentoEntity tipoAfastamento = (TipoAfastamentoEntity) this.subView
				.getCbTipoAfastamento().getValue();

		if (!ObjectValidator.validateObject(tipoAfastamento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbTipoAfastamento(), msg);

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

	public List<TipoAfastamentoEntity> tipoAfastamentoListarTodos() {
		List<TipoAfastamentoEntity> auxLista = new ArrayList<TipoAfastamentoEntity>();

		auxLista = this.taDAO.tipoAfastamentoLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public AfastamentoEntity getModelBean() {
		return pEntity;
	}

}