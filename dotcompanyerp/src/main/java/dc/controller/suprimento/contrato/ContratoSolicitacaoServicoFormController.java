package dc.controller.suprimento.contrato;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.SetorListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.controller.geral.pessoal.FornecedorListController;
import dc.entidade.suprimentos.contrato.SolicitacaoServicoEntity;
import dc.model.dao.geral.diverso.ISetorDAO;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.contrato.ISolicitacaoServicoDAO;
import dc.servicos.dao.suprimentos.contrato.ITipoServicoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.contrato.ContratoSolicitacaoServicoFormView;

@Controller
@Scope("prototype")
public class ContratoSolicitacaoServicoFormController extends
		CRUDFormController<SolicitacaoServicoEntity> {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratoSolicitacaoServicoFormView subView;

	@Autowired
	private ISolicitacaoServicoDAO contratoSolicitacaoServicoDAO;

	@Autowired
	private IFornecedorDAO fornecedorDAO;
	@Autowired
	private ISetorDAO setorDAO;
	@Autowired
	private ITipoServicoDAO contratoTipoServicoDAO;
	@Autowired
	private IColaboradorDAO colaboradorDAO;
	@Autowired
	private IClienteDAO clienteDAO;

	private SolicitacaoServicoEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		
		try {
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
		    return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
		}
		/*if(subView.getGrpTipo().getValue().equals(ResponsavelEnum.FORNECEDOR)) {
			if (!Validator.validateObject(subView.getCbFornecedor().getValue())) {
				adicionarErroDeValidacao(subView.getCbFornecedor(),
						"Não pode ficar em branco");
				valido = false;
			}	
		}
		else{
			if (!Validator.validateObject(subView.getCbCliente().getValue())) {
				adicionarErroDeValidacao(subView.getCbCliente(),
						"Não pode ficar em branco");
				valido = false;
			}
		}*/
		
	}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new SolicitacaoServicoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
		    e.printStackTrace();
		    mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ContratoSolicitacaoServicoFormView(this);

			this.fieldGroup = new DCFieldGroup<>(SolicitacaoServicoEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getDtSolicitacao(),"dataSolicitacao");
			
			fieldGroup.bind(this.subView.getCbColaborador(),"colaborador");
			fieldGroup.bind(this.subView.getCbSetor(),"setor");
			fieldGroup.bind(this.subView.getCbTipoServico(),"contratoTipoServico");
			
			this.subView.getCbFornecedor().configuraCombo(
					"pessoa.nome", FornecedorListController.class, this.fornecedorDAO, this.getMainController());
			this.subView.getCbCliente().configuraCombo(
					"pessoa.nome", ClienteListController.class, this.clienteDAO, this.getMainController());
			this.subView.getCbTipoServico().configuraCombo(
					"nome", ContratoTipoServicoListController.class, this.contratoTipoServicoDAO, this.getMainController());
			this.subView.getCbColaborador().configuraCombo(
					"pessoa.nome", ColaboradorListController.class, this.colaboradorDAO, this.getMainController());
			this.subView.getCbSetor().configuraCombo(
					"nome", SetorListController.class, this.setorDAO, this.getMainController());
			
			subView.carregarStatusSituacao();
			subView.carregarUrgente();
			
		}catch (Exception e) {
	       e.printStackTrace();
	    }
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			this.currentBean = this.contratoSolicitacaoServicoDAO.find(id);
			subView.carregarView(currentBean);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		
		
		try {
			this.contratoSolicitacaoServicoDAO.saveOrUpdate(this.currentBean);

			subView.preencherObjeto(currentBean);
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
		return "Solicitação de serviço";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contratoSolicitacaoServicoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				SolicitacaoServicoEntity sol = (SolicitacaoServicoEntity) id;

				try {
					contratoSolicitacaoServicoDAO.delete(sol);
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
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public SolicitacaoServicoEntity getModelBean() {
		return currentBean;
	}

}