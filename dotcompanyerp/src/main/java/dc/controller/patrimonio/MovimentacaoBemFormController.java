package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.patrimonio.MovimentacaoBemEntity;
import dc.entidade.patrimonio.TipoMovimentacaoEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.servicos.dao.patrimonio.IBemDAO;
import dc.servicos.dao.patrimonio.MovimentacaoBemDAO;
import dc.servicos.dao.patrimonio.TipoMovimentacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.MovimentacaoBemFormView;

@Controller
@Scope("prototype")
public class MovimentacaoBemFormController extends CRUDFormController<MovimentacaoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MovimentacaoBemFormView subView;

	/** DAO'S */

	@Autowired
	private MovimentacaoBemDAO pDAO;

	@Autowired
	private IBemDAO bDAO;

	@Autowired
	private TipoMovimentacaoDAO tmDAO;

	/** ENTITIES */

	private MovimentacaoBemEntity pEntity;

	public MovimentacaoBemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new MovimentacaoBemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Movimentação do bem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataMovimentacao = this.subView.getPdfDataMovimentacao().getValue();
			String responsavel = this.subView.getTfResponsavel().getValue();

			BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();
			TipoMovimentacaoEntity tipoMovimentacao = (TipoMovimentacaoEntity) this.subView.getCbTipoMovimentacao().getValue();

			this.pEntity.setDataMovimentacao(dataMovimentacao);
			this.pEntity.setResponsavel(responsavel);

			this.pEntity.setBem(bem);
			this.pEntity.setTipoMovimentacao(tipoMovimentacao);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new MovimentacaoBemEntity();

			this.subView.getPdfDataMovimentacao().setValue(this.pEntity.getDataMovimentacao());
			this.subView.getTfResponsavel().setValue(this.pEntity.getResponsavel());

			this.subView.carregarCmbBem(this.bemListarTodos());
			this.subView.carregarCmbTipoMovimentacao(this.tipoMovimentacaoListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
			this.subView.getCbTipoMovimentacao().setValue(this.pEntity.getTipoMovimentacao());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.carregarCmbBem(this.bemListarTodos());
			this.subView.carregarCmbTipoMovimentacao(this.tipoMovimentacaoListarTodos());

			this.subView.getPdfDataMovimentacao().setValue(this.pEntity.getDataMovimentacao());
			this.subView.getTfResponsavel().setValue(this.pEntity.getResponsavel());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
			this.subView.getCbTipoMovimentacao().setValue(this.pEntity.getTipoMovimentacao());
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
			this.pEntity = new MovimentacaoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			if (this.tmDAO == null) {
				this.tmDAO = new TipoMovimentacaoDAO();
			}

			this.subView.getPdfDataMovimentacao().setValue(this.pEntity.getDataMovimentacao());
			this.subView.getTfResponsavel().setValue(this.pEntity.getResponsavel());

			this.subView.carregarCmbBem(this.bemListarTodos());
			this.subView.carregarCmbTipoMovimentacao(this.tipoMovimentacaoListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
			this.subView.getCbTipoMovimentacao().setValue(this.pEntity.getTipoMovimentacao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new MovimentacaoBemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new MovimentacaoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			if (this.tmDAO == null) {
				this.tmDAO = new TipoMovimentacaoDAO();
			}

			this.subView.getPdfDataMovimentacao().setValue(this.pEntity.getDataMovimentacao());
			this.subView.getTfResponsavel().setValue(this.pEntity.getResponsavel());

			this.subView.carregarCmbBem(this.bemListarTodos());
			this.subView.carregarCmbTipoMovimentacao(this.tipoMovimentacaoListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
			this.subView.getCbTipoMovimentacao().setValue(this.pEntity.getTipoMovimentacao());
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
		Object dataMovimentacao = this.subView.getPdfDataMovimentacao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataMovimentacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataMovimentacao(), msg);

			return false;
		}

		// String responsavel = this.subView.getTfResponsavel().getValue();

		BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();

		if (!ObjectValidator.validateObject(bem)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbBem(), msg);

			return false;
		}

		TipoMovimentacaoEntity tipoMovimentacao = (TipoMovimentacaoEntity) this.subView.getCbTipoMovimentacao().getValue();

		if (!ObjectValidator.validateObject(tipoMovimentacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbTipoMovimentacao(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_movimentacao_bem_fc";
	}

	/** COMBOS */

	public List<BemEntity> bemListarTodos() {
		List<BemEntity> auxLista = new ArrayList<BemEntity>();

		auxLista = this.bDAO.bemLista();

		return auxLista;
	}

	public List<TipoMovimentacaoEntity> tipoMovimentacaoListarTodos() {
		List<TipoMovimentacaoEntity> auxLista = new ArrayList<TipoMovimentacaoEntity>();

		auxLista = this.tmDAO.tipoMovimnentacaoLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public MovimentacaoBemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}