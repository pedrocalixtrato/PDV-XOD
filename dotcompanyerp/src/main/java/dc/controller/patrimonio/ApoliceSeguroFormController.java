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
import dc.entidade.patrimonio.ApoliceSeguroEntity;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.patrimonio.SeguradoraEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.servicos.dao.patrimonio.IApoliceSeguroDAO;
import dc.servicos.dao.patrimonio.IBemDAO;
import dc.servicos.dao.patrimonio.ISeguradoraDAO;
import dc.servicos.dao.patrimonio.SeguradoraDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.ApoliceSeguroFormView;

@Controller
@Scope("prototype")
public class ApoliceSeguroFormController extends CRUDFormController<ApoliceSeguroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ApoliceSeguroFormView subView;

	/** DAO'S */

	@Autowired
	private IApoliceSeguroDAO pDAO;

	@Autowired
	private IBemDAO bDAO;

	@Autowired
	private ISeguradoraDAO sDAO;

	/** ENTITIES */

	private ApoliceSeguroEntity pEntity;

	/** CONSTRUTOR */

	public ApoliceSeguroFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ApoliceSeguroEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Apólice do seguro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String numero = this.subView.getTfNumero().getValue();
			Date dataContratacao = this.subView.getPdfDataContratacao().getValue();
			Date dataVencimento = this.subView.getPdfDataVencimento().getValue();
			Double valorPremio = Double.parseDouble(this.subView.getTfValorPremio().getValue());
			Double valorSegurado = Double.parseDouble(this.subView.getTfValorSegurado().getValue());
			String observacao = this.subView.getTfObservacao().getValue();
			String imagem = this.subView.getTfImagem().getValue();

			BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();
			SeguradoraEntity seguradora = (SeguradoraEntity) this.subView.getCbSeguradora().getValue();

			this.pEntity.setNumero(numero);
			this.pEntity.setDataContratacao(dataContratacao);
			this.pEntity.setDataVencimento(dataVencimento);
			this.pEntity.setValorPremio(valorPremio);
			this.pEntity.setValorSegurado(valorSegurado);
			this.pEntity.setObservacao(observacao);
			this.pEntity.setImagem(imagem);

			this.pEntity.setBem(bem);
			this.pEntity.setSeguradora(seguradora);

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
			this.pEntity = new ApoliceSeguroEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new SeguradoraDAO();
			}

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ApoliceSeguroFormView(this);

		popularCombo();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new ApoliceSeguroEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new SeguradoraDAO();
			}

			novoObjeto(0);
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
		try {
			String numero = (String) this.subView.getTfNumero().getValue();

			if (!ObjectValidator.validateString(numero)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfNumero(), msg);

				return false;
			}

			Object dataContratacao = this.subView.getPdfDataContratacao().getValue();

			if (!ObjectValidator.validateNotRequiredDate(dataContratacao)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getPdfDataContratacao(), msg);

				return false;
			}

			Object dataVencimento = this.subView.getPdfDataVencimento().getValue();

			if (!ObjectValidator.validateNotRequiredDate(dataVencimento)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getPdfDataVencimento(), msg);

				return false;
			}

			String valorPremio = this.subView.getTfValorPremio().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(valorPremio)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfValorPremio(), msg);

				return false;
			}

			String valorSegurado = this.subView.getTfValorSegurado().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(valorSegurado)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfValorSegurado(), msg);

				return false;
			}

			/**
			 * 
			 */

			SeguradoraEntity seguradora = (SeguradoraEntity) this.subView.getCbSeguradora().getValue();

			if (!ObjectValidator.validateObject(seguradora)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getCbSeguradora(), msg);

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

	public List<SeguradoraEntity> seguradoraListarTodos() {
		List<SeguradoraEntity> auxLista = new ArrayList<SeguradoraEntity>();

		auxLista = this.sDAO.seguradoraLista();

		return auxLista;
	}

	/** COMBOS */

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<SeguradoraEntity> model1 = new DefaultManyToOneComboModel<SeguradoraEntity>(SeguradoraListController.class,
					this.sDAO, super.getMainController());

			DefaultManyToOneComboModel<BemEntity> model2 = new DefaultManyToOneComboModel<BemEntity>(BemListController.class, this.bDAO,
					super.getMainController());

			this.subView.getCbSeguradora().setModel(model1);
			this.subView.getCbBem().setModel(model2);
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
				this.pEntity = new ApoliceSeguroEntity();
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbBem().setValue(this.pEntity.getBem());
				this.subView.getCbSeguradora().setValue(this.pEntity.getSeguradora());
			}

			this.subView.getTfNumero().setValue(this.pEntity.getNumero());
			this.subView.getPdfDataContratacao().setValue(this.pEntity.getDataContratacao());
			this.subView.getPdfDataVencimento().setValue(this.pEntity.getDataVencimento());
			this.subView.getTfValorPremio().setValue(String.valueOf(this.pEntity.getValorPremio()));
			this.subView.getTfValorSegurado().setValue(String.valueOf(this.pEntity.getValorSegurado()));
			this.subView.getTfObservacao().setValue(this.pEntity.getObservacao());
			this.subView.getTfImagem().setValue(this.pEntity.getImagem());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ApoliceSeguroEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}