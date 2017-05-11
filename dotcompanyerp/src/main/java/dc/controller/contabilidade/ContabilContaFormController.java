package dc.controller.contabilidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaRefSpedDAO;
import dc.servicos.util.Validator;
import dc.visao.contabilidade.ContabilContaFormView;
import dc.visao.contabilidade.ContabilContaFormView.CodigoEFD;
import dc.visao.contabilidade.ContabilContaFormView.DemoFluxoCaixa;
import dc.visao.contabilidade.ContabilContaFormView.Natureza;
import dc.visao.contabilidade.ContabilContaFormView.PatrimonioResultado;
import dc.visao.contabilidade.ContabilContaFormView.SimNao;
import dc.visao.contabilidade.ContabilContaFormView.Situacao;
import dc.visao.contabilidade.ContabilContaFormView.Tipo;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ContabilContaFormController extends CRUDFormController<ContabilContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContabilContaFormView subView;

	@Autowired
	private IContabilContaDAO contabilContaDAO;

	@Autowired
	private IPlanoContaDAO planoContaDAO;

	@Autowired
	private IPlanoContaRefSpedDAO planoContaRefSpedDAO;

	private ContabilContaEntity currentBean;

	@Override
	protected String getNome() {
		return "Contabil Conta";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			contabilContaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contabilContaDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		
		subView = new ContabilContaFormView(this);

		carregarCombos();

		subView.getDtDataInclusao().setValue(new Date());

	}

	private void carregarCombos() {

		DefaultManyToOneComboModel<ContabilContaEntity> contabilContaModel = new DefaultManyToOneComboModel<ContabilContaEntity>(
				ContabilContaListController.class, this.contabilContaDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "codigoReduzido";
			}
		};

		DefaultManyToOneComboModel<PlanoContaEntity> planoContaModel = new DefaultManyToOneComboModel<PlanoContaEntity>(PlanoContaListController.class,
				this.planoContaDAO, super.getMainController());

		DefaultManyToOneComboModel<PlanoContaRefSped> planoContaRefSpedModel = new DefaultManyToOneComboModel<PlanoContaRefSped>(
				PlanoContaRefSpedListController.class, this.planoContaRefSpedDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		subView.getCbPlanoConta().setModel(planoContaModel);
		subView.getCbPlanoContaRefSped().setModel(planoContaRefSpedModel);
		subView.getCbContabilContaPai().setModel(contabilContaModel);

		for (Tipo value : Tipo.values()) {
			subView.getCbTipo().addItem(value);
		}

		for (DemoFluxoCaixa value : DemoFluxoCaixa.values()) {
			subView.getCbDemonstracaoFluxoCaixa().addItem(value);
		}

		for (PatrimonioResultado value : PatrimonioResultado.values()) {
			subView.getCbPatrimonio().addItem(value);
		}

		for (Natureza value : Natureza.values()) {
			subView.getCbNatureza().addItem(value);
		}

		for (Situacao value : Situacao.values()) {
			subView.getCbSituacao().addItem(value);
		}

		for (CodigoEFD value : CodigoEFD.values()) {
			subView.getCbCodigoEFB().addItem(value);
		}

		for (SimNao value : SimNao.values()) {
			subView.getCbLivroCaixa().addItem(value);
		}

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new ContabilContaEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contabilContaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;
		
		if (!Validator.validateObject(subView.getTxClassificacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxClassificacao(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getTxCodigoReduzido().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoReduzido(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getTxDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getTxOrdem().getValue())) {
			adicionarErroDeValidacao(subView.getTxOrdem(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getDtDataInclusao().getValue())) {
			adicionarErroDeValidacao(subView.getDtDataInclusao(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "contabilContaForm";
	}

	@Override
	public ContabilContaEntity getModelBean() {
		return currentBean;
	}
}
