package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.FormaDescontoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoFreteEn;
import dc.control.util.ClassUtils;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.business.geral.pessoal.ClienteBusiness;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoForCliDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.ClienteFormView;

@Controller
@Scope("prototype")
public class ClienteFormController extends CRUDFormController<ClienteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClienteFormView subView;

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private IPessoaDAO pessoaDAO;

	@Autowired
	private ISituacaoForCliDAO situacaoDAO;

	@Autowired
	private IAtividadeForCliDAO atividadeDAO;

	@Autowired
	private IOperacaoFiscalDAO operacaoDAO;

	private ClienteEntity currentBean;
	
	@Autowired
    private ClienteBusiness<ClienteEntity> business;
	
	public ClienteBusiness<ClienteEntity> getBusiness() {
        return business;
    }

	@Override
	protected boolean validaSalvar() {
		try {
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
		    return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new ClienteEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ClienteFormView(this);

			this.fieldGroup = new DCFieldGroup<>(ClienteEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getPdfDesde(),"desde");
			
			fieldGroup.bind(this.subView.getMocPessoa(),"pessoa");
			fieldGroup.bind(this.subView.getMocSituacao(),"situacaoForCli");
			fieldGroup.bind(this.subView.getMocAtividade(),"atividadeForCli");
			
			this.subView.getMocPessoa().configuraCombo(
					"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());
			this.subView.getMocSituacao().configuraCombo(
					"nome", SituacaoForCliListController.class, this.situacaoDAO, this.getMainController());
			this.subView.getMocAtividade().configuraCombo(
					"nome", AtividadeForCliListController.class, this.atividadeDAO, this.getMainController());
			this.subView.getMocOperacaoFiscal().configuraCombo(
					"descricao", OperacaoFiscalListController.class, this.operacaoDAO, this.getMainController());
			
			comboGerarFinanceiro();
			comboFormaDesconto();
			comboIndicadorPreco();
			comboTipoFrete();
			
		}catch (Exception e) {
	       e.printStackTrace();
	    }

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				ClienteEntity cli = (ClienteEntity) id;

				try {
					business.delete(cli);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	/**
	 * COMBOS
	 */

	public void comboGerarFinanceiro() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbGerarFinanceiro().addItem(en);
		}
	}

	public void comboIndicadorPreco() {
		for (IndicadorPrecoEn en : IndicadorPrecoEn.values()) {
			this.subView.getCbIndicadorPreco().addItem(en);
		}
	}

	public void comboTipoFrete() {
		for (TipoFreteEn en : TipoFreteEn.values()) {
			this.subView.getCbTipoFrete().addItem(en);
		}
	}

	public void comboFormaDesconto() {
		for (FormaDescontoEn en : FormaDescontoEn.values()) {
			this.subView.getCbFormaDesconto().addItem(en);
		}
	}

	@Override
	public ClienteEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}

}