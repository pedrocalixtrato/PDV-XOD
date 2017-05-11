package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.FluxoCaixaDetalheEntity;
import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.model.business.financeiro.FluxoCaixaBusiness;
import dc.servicos.dao.financeiro.IFluxoCaixaPeriodoDAO;
import dc.servicos.dao.financeiro.INaturezaFinanceiraDAO;
import dc.visao.financeiro.FluxoCaixaFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FluxoCaixaFormController extends CRUDFormController<FluxoCaixaEntity> {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * BUSINESS
		 */
		@Autowired
		private FluxoCaixaBusiness<FluxoCaixaEntity> business;

		private FluxoCaixaFormView subView;

		private FluxoCaixaEntity currentBean;
		
		@Autowired
		private INaturezaFinanceiraDAO naturezaFinanceiraDAO;
		
		@Autowired
		private IFluxoCaixaPeriodoDAO fluxoCaixaPeriodoDAO;

		public FluxoCaixaBusiness<FluxoCaixaEntity> getBusiness() {
		 return business;
	    }

		@Override
		protected String getNome() {
			return "Fluxo de Caixa";
		}

		@Override
		protected Component getSubView() {
			return subView;
		}

		@Override
		protected void actionSalvar() {

			try {
				
				this.business.saveOrUpdate(this.currentBean);
				notifiyFrameworkSaveOK(this.currentBean);
				subView.preencheBean(currentBean);
				
				
			}catch(Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}

		}

		@Override
		protected void carregar(Serializable id) {

			try {
			
				currentBean = this.business.find((Integer) id);
				subView.preencheForm(currentBean);
				
				List<FluxoCaixaDetalheEntity> itens = naturezaFinanceiraDAO.findByNatureza(currentBean);
				subView.preencheSubForm(itens);
				
				fieldGroup.setItemDataSource(this.currentBean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void initSubView() {
			
			try {
				subView = new FluxoCaixaFormView(this);
				
				
				this.fieldGroup = new DCFieldGroup<>(FluxoCaixaEntity.class);
				
				// Mapeia os campos
				fieldGroup.bind(this.subView.getTxNomeFluxo(),"nome");
				/*fieldGroup.bind(this.subView.getTxQuantidadeParcela(),"quantidadeParcela");
				fieldGroup.bind(this.subView.getCbPagamentoCompartilhado(),"pagamentoCompartilhado");
				fieldGroup.bind(this.subView.getDtPrimeiroVencimento(),"primeiroVencimento");*/
				
				fieldGroup.bind(this.subView.getCbPeriodo(),"fluxoCaixaPeriodo");

				
				subView.getBtnCalculaVariacao().addClickListener(new ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							calculaVariacao();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							mensagemErro(e.getMessage());
						}

					}
				});
				
				
				this.subView.getCbPeriodo().configuraCombo(
						"nome", FluxoCaixaPeriodoListController.class, this.fluxoCaixaPeriodoDAO, this.getMainController());
				
			}catch (Exception e) {
			    e.printStackTrace();
			}
			
		}

		@Override
		protected void criarNovoBean() {
			
			
			try {
				currentBean = new FluxoCaixaEntity();
				fieldGroup.setItemDataSource(this.currentBean);
			}catch (Exception e) {
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

		public FluxoCaixaDetalheEntity novoFluxo() {
			
			FluxoCaixaDetalheEntity item = new FluxoCaixaDetalheEntity();
			currentBean.addFluxo(item);
			return item;
		}

		public void removerFluxo(List<FluxoCaixaDetalheEntity> values) {
			for (FluxoCaixaDetalheEntity value : values) {
				currentBean.removeFluxo(value);
			}
			
			mensagemRemovidoOK();

		}
		
		@Override
		public FluxoCaixaEntity getModelBean() {
			return currentBean;
		}

		public List<FluxoCaixaDetalheEntity> getNaturezasFinanceiras() {
			
			try {
				return naturezaFinanceiraDAO.findByNatureza(currentBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public List<NaturezaFinanceira> buscarNaturezas() {
			return naturezaFinanceiraDAO.getAll(NaturezaFinanceira.class);
		}
		
		 public void calculaVariacao() throws Exception {
		        List<FluxoCaixaDetalheEntity> listaFluxoCaixa = subView.getFluxoCaixaDetalheSubForm().getDados();
		        if (listaFluxoCaixa.isEmpty()) {
		        	throw new Exception(
							"Não existe  valores para o cálculo");
		            
		        } else {
		            BigDecimal taxaVariacao;
		            BigDecimal valorVariacao;
		            FluxoCaixaDetalheEntity fluxoCaixa;
		            
		            for (int i = 0; i < listaFluxoCaixa.size(); i++) {
		                fluxoCaixa = listaFluxoCaixa.get(i);
		                if (fluxoCaixa.getValorOrcado() != null && fluxoCaixa.getValorRealizado() != null) {
		                    taxaVariacao = fluxoCaixa.getValorRealizado().divide(fluxoCaixa.getValorOrcado(), BigDecimal.ROUND_HALF_EVEN);
		                    valorVariacao = fluxoCaixa.getValorRealizado().subtract(fluxoCaixa.getValorOrcado());
		                    listaFluxoCaixa.get(i).setTaxaVariacao(taxaVariacao.subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
		                    listaFluxoCaixa.get(i).setValorVariacao(valorVariacao);
		                    subView.getFluxoCaixaDetalheSubForm().getDados();
		                }
		            }
		            
		            subView.getFluxoCaixaDetalheSubForm().fillWith(listaFluxoCaixa);
		        }
		    }


}
