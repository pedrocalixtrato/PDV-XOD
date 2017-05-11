package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.FluxoCaixaPeriodoEntity;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.financeiro.IFluxoCaixaPeriodoDAO;
import dc.visao.financeiro.FluxoCaixaPeriodoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FluxoCaixaPeriodoFormController extends CRUDFormController<FluxoCaixaPeriodoEntity> {
	
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			FluxoCaixaPeriodoFormView subView;

			private FluxoCaixaPeriodoEntity currentBean;
			
			@Autowired
			private IFluxoCaixaPeriodoDAO dao;
			
			@Autowired
			private IContaCaixaDAO contaCaixaDAO;
			
			@Override
			protected String getNome() {
				return "Fluxo Caixa de Período";
			}

			@Override
			protected Component getSubView() {
				return subView;
			}

			@Override
			protected void actionSalvar() {

				try {
					
					this.dao.saveOrUpdate(this.currentBean);
					notifiyFrameworkSaveOK(this.currentBean);
					
				}catch(Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}

			@Override
			protected void carregar(Serializable id) {

				try {
				
					currentBean = this.dao.find((Integer) id);
					
					fieldGroup.setItemDataSource(this.currentBean);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void initSubView() {
				
				try {
					subView = new FluxoCaixaPeriodoFormView(this);
					
					
					this.fieldGroup = new DCFieldGroup<>(FluxoCaixaPeriodoEntity.class);
					
					// Mapeia os campos
					fieldGroup.bind(this.subView.getTxNome(),"nome");
					fieldGroup.bind(this.subView.getCbContaCaixa(),"contaCaixa");
					
					
					this.subView.getCbContaCaixa().configuraCombo(
							"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
					
				}catch (Exception e) {
				    e.printStackTrace();
				}
				
			}

			@Override
			protected void criarNovoBean() {
				
				
				try {
					currentBean = new FluxoCaixaPeriodoEntity();
					fieldGroup.setItemDataSource(this.currentBean);
				}catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
			    }
			}

			@Override
			protected void remover(List<Serializable> ids) {
				try {
					this.dao.deleteAll(ids);

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

					 fieldGroup.commit();
					return true;
				} catch (FieldGroup.CommitException ce) {
					return false;
				}
			}

			@Override
			protected void removerEmCascata(List<Serializable> ids) {
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
			public FluxoCaixaPeriodoEntity getModelBean() {
				return currentBean;
			}

}
