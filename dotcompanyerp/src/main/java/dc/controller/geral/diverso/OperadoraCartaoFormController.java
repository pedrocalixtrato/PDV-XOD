package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.controller.financeiro.ContaCaixaListController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.model.business.geral.diverso.OperadoraCartaoBusiness;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraCartaoFormView;

@Controller
@Scope("prototype")
public class OperadoraCartaoFormController extends CRUDFormController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraCartaoFormView subView;

	/**
	 * ENTITY
	 */

	private OperadoraCartaoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private OperadoraCartaoBusiness<OperadoraCartaoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;

	/**
	 * CONSTRUTOR
	 */

	public OperadoraCartaoFormController() {
		// TODO Auto-generated constructor stub
	}

	public OperadoraCartaoBusiness<OperadoraCartaoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Operadora de cartão";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public OperadoraCartaoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new OperadoraCartaoFormView(this);

			this.fieldGroup = new DCFieldGroup<>(OperadoraCartaoEntity.class);
            fieldGroup.bind(this.subView.getMocContaCaixa(),"contaCaixa");
            fieldGroup.bind(this.subView.getTfBandeira(),"bandeira");
            fieldGroup.bind(this.subView.getTfNome(),"nome");
           // fieldGroup.bind(this.subView.getTfTaxaAdm(),"taxaAdm");
           // fieldGroup.bind(this.subView.getTfTaxaAdmDebito(),"taxaAdmDebito");
           // fieldGroup.bind(this.subView.getTfValorAluguelPosPin(),"valorAluguelPosPin");
            fieldGroup.bind(this.subView.getTfVencimentoAluguel(),"vencimentoAluguel");
            fieldGroup.bind(this.subView.getTfTelefone1(),"fone1");
            fieldGroup.bind(this.subView.getTfTelefone2(),"fone2");
            
            subView.getTfValorAluguelPosPin().addBlurListener(new BlurListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {
					subView.getTfValorAluguelPosPin().setConvertedValue(
							subView.getTfValorAluguelPosPin().getConvertedValue());

				}
			});

            
         // Configura os ManyToOneComboFields
            this.subView.getMocContaCaixa().configuraCombo(
                    "nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {

			fieldGroup.commit();
			
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			ContaCaixa contaCaixa = this.subView.getMocContaCaixa().getValue();
			if (ObjectUtils.isNotBlank(contaCaixa)) {
				this.entity.setContaCaixa((ContaCaixa) this.subView
						.getMocContaCaixa().getValue());
			} else {
				this.entity.setContaCaixa(null);
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new OperadoraCartaoEntity();
			
            fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				OperadoraCartaoEntity operadoraCartao = (OperadoraCartaoEntity) id;

				try {
					business.delete(operadoraCartao);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

}