package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.servicos.dao.financeiro.IConfiguracaoBoletoDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.visao.financeiro.ConfiguracaoBoletoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ConfiguracaoBoletoFormController extends CRUDFormController<ConfiguracaoBoleto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConfiguracaoBoletoFormView subView;

	@Autowired
	private IConfiguracaoBoletoDAO configuracaoBoletoDAO;

	private ConfiguracaoBoleto currentBean;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;

	@Override
	protected String getNome() {
		return "Configuração Boleto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			subView.preencheBean(currentBean);
			configuracaoBoletoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			 mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = configuracaoBoletoDAO.find(id);
			
			// Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
			fieldGroup.setItemDataSource(this.currentBean);
			
		} catch (Exception e) {
		      e.printStackTrace();
		}
		
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ConfiguracaoBoletoFormView();
			
			this.fieldGroup = new DCFieldGroup<>(ConfiguracaoBoleto.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTxMensagem(),"mensagem");
			fieldGroup.bind(this.subView.getTxInstrucao01(),"instrucao01");
			fieldGroup.bind(this.subView.getCbContaCaixa(),"contaCaixa");
			
			this.subView.getCbContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
@Override
protected void criarNovoBean() {
		
	try {
        this.currentBean = new ConfiguracaoBoleto();

        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.currentBean);

    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
    }
}

@Override
protected void remover(List<Serializable> ids) {
		
	 try {
            this.configuracaoBoletoDAO.deleteAll(ids);

            mensagemRemovidoOK();
        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
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
	protected void removerEmCascata(List<Serializable> ids) {
		try {
        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "configuracaoBoletoForm";
	}

	@Override
	public ConfiguracaoBoleto getModelBean() {
		return currentBean;
	}
}
