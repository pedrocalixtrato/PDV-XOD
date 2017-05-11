package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.patrimonio.TaxaDepreciacaoEntity;
import dc.servicos.dao.patrimonio.TaxaDepreciacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.TaxaDepreciacaoFormView;

@Controller
@Scope("prototype")
public class TaxaDepreciacaoFormController extends CRUDFormController<TaxaDepreciacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaxaDepreciacaoFormView subView;

	/** DAO'S */

	@Autowired
	private TaxaDepreciacaoDAO pDAO;

	/** ENTITIES */

	private TaxaDepreciacaoEntity pEntity;

	/** CONSTRUTOR */

	public TaxaDepreciacaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new TaxaDepreciacaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Taxa de depreciação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String ncm = this.subView.getTfNcm().getValue();
			String bem = this.subView.getTfBem().getValue();
			Double vida = Double.parseDouble(this.subView.getTfVida().getValue());
			Double taxa = Double.parseDouble(this.subView.getTfTaxa().getValue());

			this.pEntity.setNcm(ncm);
			this.pEntity.setBem(bem);
			this.pEntity.setVida(vida);
			this.pEntity.setTaxa(taxa);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new TaxaDepreciacaoEntity();

			this.subView.getTfNcm().setValue(this.pEntity.getNcm());
			this.subView.getTfBem().setValue(this.pEntity.getBem());
			this.subView.getTfVida().setValue(String.valueOf(this.pEntity.getVida()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = pDAO.find(id);

			this.subView.getTfNcm().setValue(this.pEntity.getNcm());
			this.subView.getTfBem().setValue(this.pEntity.getBem());
			this.subView.getTfVida().setValue(String.valueOf(this.pEntity.getVida()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
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
			this.pEntity = new TaxaDepreciacaoEntity();

			this.subView.getTfNcm().setValue(this.pEntity.getNcm());
			this.subView.getTfBem().setValue(this.pEntity.getBem());
			this.subView.getTfVida().setValue(String.valueOf(this.pEntity.getVida()));
			this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new TaxaDepreciacaoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.pEntity = new TaxaDepreciacaoEntity();

		this.subView.getTfNcm().setValue(this.pEntity.getNcm());
		this.subView.getTfBem().setValue(this.pEntity.getBem());
		this.subView.getTfVida().setValue(String.valueOf(this.pEntity.getVida()));
		this.subView.getTfTaxa().setValue(String.valueOf(this.pEntity.getTaxa()));
	}

	@Override
	protected void remover(List<Serializable> ids) {
		this.pDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		String vida = (String) this.subView.getTfVida().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(vida)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfVida(), msg);

			return false;
		}

		String taxa = (String) this.subView.getTfTaxa().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxa)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxa(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_taxa_depreciacao_fc";
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public TaxaDepreciacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}