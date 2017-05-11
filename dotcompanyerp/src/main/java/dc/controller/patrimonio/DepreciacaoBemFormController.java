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
import dc.entidade.patrimonio.DepreciacaoBemEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.servicos.dao.patrimonio.DepreciacaoBemDAO;
import dc.servicos.dao.patrimonio.IBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.DepreciacaoBemFormView;

@Controller
@Scope("prototype")
public class DepreciacaoBemFormController extends CRUDFormController<DepreciacaoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DepreciacaoBemFormView subView;

	/** DAO'S */

	@Autowired
	private DepreciacaoBemDAO pDAO;

	@Autowired
	private IBemDAO bDAO;

	/** ENTITIES */

	private DepreciacaoBemEntity pEntity;

	/** CONSTRUTOR */

	public DepreciacaoBemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DepreciacaoBemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Depreciação do bem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataDepreciacao = this.subView.getPdfDataDepreciacao().getValue();
			Double depreciacaoAcumulada = Double.parseDouble(this.subView.getTfDepreciacaoAcumulada().getValue());
			Integer dias = Integer.parseInt(this.subView.getTfDias().getValue());
			Double indice = Double.parseDouble(this.subView.getTfIndice().getValue());
			Double taxa = Double.parseDouble(this.subView.getTfTaxa().getValue());
			Double valor = Double.parseDouble(this.subView.getTfValor().getValue());

			BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();

			this.pEntity.setDataDepreciacao(dataDepreciacao);
			this.pEntity.setDepreciacaoAcumulada(depreciacaoAcumulada);
			this.pEntity.setDias(dias);
			this.pEntity.setIndice(indice);
			this.pEntity.setTaxa(taxa);
			this.pEntity.setValor(valor);

			this.pEntity.setBem(bem);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new DepreciacaoBemEntity();

			this.subView.getPdfDataDepreciacao().setValue(null);
			this.subView.getTfDepreciacaoAcumulada().setValue(String.valueOf(this.pEntity.getDepreciacaoAcumulada()));
			this.subView.getTfDias().setValue(String.valueOf(this.pEntity.getDias()));
			this.subView.getTfIndice().setValue(String.valueOf(this.pEntity.getIndice()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getPdfDataDepreciacao().setValue(null);
			this.subView.getTfDepreciacaoAcumulada().setValue(String.valueOf(this.pEntity.getDepreciacaoAcumulada()));
			this.subView.getTfDias().setValue(String.valueOf(this.pEntity.getDias()));
			this.subView.getTfIndice().setValue(String.valueOf(this.pEntity.getIndice()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));

			this.subView.getCbBem().setValue(this.pEntity.getBem());
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
			this.pEntity = new DepreciacaoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			this.subView.getPdfDataDepreciacao().setValue(null);
			this.subView.getTfDepreciacaoAcumulada().setValue(String.valueOf(this.pEntity.getDepreciacaoAcumulada()));
			this.subView.getTfDias().setValue(String.valueOf(this.pEntity.getDias()));
			this.subView.getTfIndice().setValue(String.valueOf(this.pEntity.getIndice()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new DepreciacaoBemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new DepreciacaoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			this.subView.getPdfDataDepreciacao().setValue(null);
			this.subView.getTfDepreciacaoAcumulada().setValue(String.valueOf(this.pEntity.getDepreciacaoAcumulada()));
			this.subView.getTfDias().setValue(String.valueOf(this.pEntity.getDias()));
			this.subView.getTfIndice().setValue(String.valueOf(this.pEntity.getIndice()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		this.pDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		try {
			Object dataDepreciacao = this.subView.getPdfDataDepreciacao().getValue();

			if (!ObjectValidator.validateNotRequiredDate(dataDepreciacao)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getPdfDataDepreciacao(), msg);

				return false;
			}

			String dias = (String) this.subView.getTfDias().getValue();

			if (!ObjectValidator.validateNotRequiredInteger(dias)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfDias(), msg);

				return false;
			}

			String taxa = this.subView.getTfTaxa().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(taxa)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfTaxa(), msg);

				return false;
			}

			String indice = this.subView.getTfIndice().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(indice)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfIndice(), msg);

				return false;
			}

			String valor = this.subView.getTfValor().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(valor)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfValor(), msg);

				return false;
			}

			String depreciacaoAcumulada = this.subView.getTfDepreciacaoAcumulada().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(depreciacaoAcumulada)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfDepreciacaoAcumulada(), msg);

				return false;
			}

			BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();

			if (!ObjectValidator.validateObject(bem)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getCbBem(), msg);

				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_apolice_seguro_fc";
	}

	/** COMBOS */

	public List<BemEntity> bemListarTodos() {
		List<BemEntity> auxLista = new ArrayList<BemEntity>();

		auxLista = this.bDAO.bemLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public DepreciacaoBemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}