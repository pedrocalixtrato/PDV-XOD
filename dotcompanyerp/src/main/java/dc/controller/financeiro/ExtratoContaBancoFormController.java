package dc.controller.financeiro;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.mail.iap.Response;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.entidade.financeiro.ImportaOFX;
import dc.servicos.dao.financeiro.IExtratoContaBancoDAO;
import dc.visao.financeiro.ExtratoContaBancoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ExtratoContaBancoFormController extends CRUDFormController<ExtratoContaBancoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExtratoContaBancoEntity entity;
	
	private ExtratoContaBancoFormView subView;
	
	@Autowired
	private IExtratoContaBancoDAO extratoContaBancoDAO;
	
	private ContaCaixa contaCaixa;
    private String mes;
    private String ano;
	
	/**
	 * BUSINESS
	 *
	@Autowired
	private ExtratoContaBancoBusiness<ExtratoContaBancoEntity> business;
	
	public ExtratoContaBancoBusiness<ExtratoContaBancoEntity> getBusiness() {
		 return business;
	}*/

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			 List<ExtratoContaBancoEntity> extrato = subView.getExtratoContaBancoSubForm().getDados();
			 subView.preencheSubForm(extrato);
			 
			return true;
		} catch (Exception ce) {
			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			entity = new ExtratoContaBancoEntity();
			//fieldGroup.setItemDataSource(this.entity);
		}catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
	    }
		
	}

	@Override
	protected void initSubView() {
		try {
			
			subView = new ExtratoContaBancoFormView(this);
			
			subView.getBtnImportar().addListener(new ValueChangeListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void valueChange(ValueChangeEvent event) {
					importaOFX();
				}
				
				public void importaOFX() {
					InputStream is = subView.getBtnImportar().getContentAsStream();

				    if (is != null) {
				        ImportaOFX importa = new ImportaOFX();
				        List<ExtratoContaBancoEntity> listaExtrato = importa.importaArquivoOFX(is);
				        //subView.getSubForms().setSelectedTab(1);
				        //seta os dados do extrato
				        for (int i = 0; i < listaExtrato.size(); i++) {
				            listaExtrato.get(i).setAno(ano);
				            listaExtrato.get(i).setMes(mes);
				            listaExtrato.get(i).setContaCaixa(contaCaixa);
				        }
				        
				        atualizaSaldos(listaExtrato);
			            
//				        try {
//				        	ConfirmDialog
//							.show(MainUI.getCurrent(),
//									"Erro!", new ConfirmDialog.Listener() {
//
//										private static final long serialVersionUID = 1L;
//
//										public void onClose(ConfirmDialog dialog) {
//											
//											if (dados != null) {
//												extrato.addAll(subView.getExtratoContaBancoSubForm().getDados());
//											}
//				                             if (dialog.isConfirmed()) {
//				            	                   adicionarErroDeValidacao(subView.getExtratoContaBancoSubForm(),"Erro ao salvar os dados! ");
//				                                   mensagemErro("Erro ao salvar os dados! ");
//				                             } else {
//				            	                  subView.getExtratoContaBancoSubForm().getDados();
//				                            }
//				                             
//										}
//										
//							});
//				        	
//				        } catch (Exception e) {
//				            e.printStackTrace();
//				        }
				        
				    }
				}
			});
			
			subView.getBtnEfeutaConciliacaoCheque().addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						efetuaConciliacao("cheque");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						mensagemErro(e.getMessage());
					}

				}
			});
			
			subView.getBtnEfeutaConciliacaoLancamento().addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						efetuaConciliacao("lancamentos");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						mensagemErro(e.getMessage());
					}

				}
			});

		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			entity = this.extratoContaBancoDAO.find(id);
			//entity = this.business.find((Integer) id);
			
			//fieldGroup.setItemDataSource(this.entity);
			
			List<ExtratoContaBancoEntity> itens = extratoContaBancoDAO.findByExtratoContaBanco(entity);
			subView.preencheSubForm(itens);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

@Override
protected void actionSalvar() {
	try {
		//this.business.saveOrUpdate(this.entity);
		this.extratoContaBancoDAO.saveOrUpdate(this.entity);
		List<ExtratoContaBancoEntity> extrato = subView.getExtratoContaBancoSubForm().getDados();
		 subView.preencheSubForm(extrato);

		notifiyFrameworkSaveOK(this.entity);
    } catch (Exception e) {
	    e.printStackTrace();
    }
}

@Override
protected Component getSubView() {
	// TODO Auto-generated method stub
	return subView;
}

@Override
protected String getNome() {
	// TODO Auto-generated method stub
	return "Extrato Conta Banco";
}

@Override
protected void remover(List<Serializable> ids) {
	try {
		//this.business.deleteAll(ids);
		this.extratoContaBancoDAO.deleteAll(ids);

		mensagemRemovidoOK();
	} catch (Exception e) {
		e.printStackTrace();

		mensagemErro(e.getMessage());
	}
	
}

@Override
protected void removerEmCascata(List<Serializable> objetos) {
	try {
	} catch (Exception e) {
		e.printStackTrace();

		mensagemErro(e.getMessage());
	}
		
}

@Override
public ExtratoContaBancoEntity getModelBean() {
	// TODO Auto-generated method stub
	return entity;
}

/*public ExtratoContaBancoEntity novoExtratoContaBancoItem() {
	ExtratoContaBancoEntity item = new ExtratoContaBancoEntity();
	entity.addExtratoContaBancoItem(item);
	return item;
}

public void removerExtratoContaBancoItem(List<ExtratoContaBancoEntity> extratoItem) {
	for (ExtratoContaBancoEntity extratoContaItem : extratoItem) {
		entity.removeExtratoContaBancoItem(extratoContaItem);
	}
	mensagemRemovidoOK();
}*/

@Transactional
private void atualizaSaldos(List<ExtratoContaBancoEntity> extrato) {
	subView.getExtratoContaBancoSubForm().fillWith(extrato);
	
    BigDecimal creditos = BigDecimal.ZERO;
    BigDecimal debitos = BigDecimal.ZERO;
    BigDecimal saldo = BigDecimal.ZERO;
    
    for (int i = 0; i < extrato.size(); i++) {
        if (extrato.get(i).getValor().compareTo(BigDecimal.ZERO) == -1) {
            debitos = debitos.add(extrato.get(i).getValor());
        } else {
            creditos = creditos.add(extrato.get(i).getValor());
        }
    }
    saldo = creditos.add(debitos);

   
    subView.getCredito().setConvertedValue(creditos);
    subView.setCredito(subView.getCredito());
    subView.getDebito().setConvertedValue(debitos);
    subView.setDebito(subView.getDebito());
    subView.getSaldo().setConvertedValue(saldo);
    subView.setSaldo(subView.getSaldo());
}



public void efetuaConciliacao(String tipoConciliacao) throws Exception {
    //define os parametros da grid
    Map otherGridParams = new HashMap();
    otherGridParams.put("tipoConciliacao", tipoConciliacao);
    otherGridParams.put("contaCaixa", contaCaixa);
    otherGridParams.put("extrato", subView.getExtratoContaBancoSubForm().getDados());

    //seta os parametros da grid

    Response res = (Response) subView.getExtratoContaBancoSubForm().getDados();
    if (res != null) {
        adicionarErroDeValidacao(subView.getExtratoContaBancoSubForm(),"Erro ao salvar os dados! Aviso do Sistema");
        mensagemErro("Erro ao salvar os dados! Aviso do Sistema");
    } else {
        subView.getExtratoContaBancoSubForm().getDados();
    }
}


	

}
