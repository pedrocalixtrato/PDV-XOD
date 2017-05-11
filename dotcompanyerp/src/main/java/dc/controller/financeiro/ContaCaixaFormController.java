package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ContaCaixa;
import dc.model.dao.geral.pessoal.IAgenciaBancoDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.visao.financeiro.ContaCaixaFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

/** DotCompany
 * 
 * @author Wesley Jr */
@Controller
@Scope("prototype")
public class ContaCaixaFormController extends CRUDFormController<ContaCaixa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContaCaixaFormView subView;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;

	@Autowired
	private IAgenciaBancoDAO agenciaDAO;

	private ContaCaixa currentBean;

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
			currentBean = new ContaCaixa();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
		
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ContaCaixaFormView();

			this.fieldGroup = new DCFieldGroup<>(ContaCaixa.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTxtCodigo(),"codigo");
			fieldGroup.bind(this.subView.getTxtDigito(),"digito");
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			fieldGroup.bind(this.subView.getCmbTipo(),"tipoConta");
			fieldGroup.bind(this.subView.getTxtDescricao(),"descricao");
			fieldGroup.bind(this.subView.getCmbAgenciaBanco(),"agenciaBanco");
			
			this.subView.getCmbAgenciaBanco().configuraCombo(
					"nome", AgenciaBancoListController.class, this.agenciaDAO, this.getMainController());
			
		}catch (Exception e) {
	       e.printStackTrace();
	    }
		

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = contaCaixaDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {

			/** Empresa vinda da conta do usuário logado */

			//EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa();

			//this.currentBean.setEmpresa(empresa);

			/** ************************************** */

			this.contaCaixaDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	protected String getNome() {
		return "Conta Caixa";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.contaCaixaDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			ContaCaixa cc = (ContaCaixa) id;

			try {
				contaCaixaDAO.delete(cc);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public ContaCaixa getModelBean() {
		return currentBean;
	}

}