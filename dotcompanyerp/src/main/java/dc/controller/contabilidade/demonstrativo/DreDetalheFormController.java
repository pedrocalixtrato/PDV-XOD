package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreCabecalhoDAO;
import dc.servicos.dao.contabilidade.demonstrativo.IDreDetalheDAO;
import dc.visao.contabilidade.demonstrativo.DreDetalheFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class DreDetalheFormController extends
		CRUDFormController<DreDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DreDetalheFormView subView;

	/** DAO'S */

	@Autowired
	private IDreDetalheDAO pDAO;

	@Autowired
	private IDreCabecalhoDAO dcDAO;

	/** ENTITIES */

	private DreDetalheEntity pEntity;

	/** CONSTRUTOR */

	public DreDetalheFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DreDetalheEntity();
		}
	}

	@Override
	protected String getNome() {
		return "DRE detalhe";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String classificacao = this.subView.getTfClassificacao().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			String formaCalculo = this.subView.getTfFormaCalculo().getValue();
			String sinal = this.subView.getTfSinal().getValue();
			String natureza = this.subView.getTfNatureza().getValue();
			//Double valor = Double.parseDouble(this.subView.getTfValor().getValue());

			DreCabecalhoEntity dreCabecalho = this.subView.getCbDreCabecalho()
					.getValue();
			
			pEntity.setValor((BigDecimal) this.subView.getTfValor().getConvertedValue());

			this.pEntity.setClassificacao(classificacao);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setFormaCalculo(formaCalculo);
			this.pEntity.setSinal(sinal);
			this.pEntity.setNatureza(natureza);
			//this.pEntity.setValor(valor);

			this.pEntity.setDreCabecalho(dreCabecalho);

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
		this.subView = new DreDetalheFormView(this);

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
		String valor = this.subView.getTfValor().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valor)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValor(), msg);

			return false;
		}

		/** REQUIRED */

		DreCabecalhoEntity dreCabecalho = this.subView.getCbDreCabecalho()
				.getValue();

		if (!ObjectValidator.validateObject(dreCabecalho)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbDreCabecalho(), msg);

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
			DefaultManyToOneComboModel<DreCabecalhoEntity> model = new DefaultManyToOneComboModel<DreCabecalhoEntity>(
					DreCabecalhoListController.class, this.dcDAO,
					super.getMainController());

			this.subView.getCbDreCabecalho().setModel(model);
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
				this.pEntity = new DreDetalheEntity();

				// this.subView.getCbDreCabecalho().setValue(
				// this.pEntity.getDreCabecalho());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbDreCabecalho().setValue(
						this.pEntity.getDreCabecalho());
			}

			this.subView.getTfClassificacao().setValue(
					this.pEntity.getClassificacao());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfFormaCalculo().setValue(
					this.pEntity.getFormaCalculo());
			this.subView.getTfSinal().setValue(this.pEntity.getSinal());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public DreDetalheEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}