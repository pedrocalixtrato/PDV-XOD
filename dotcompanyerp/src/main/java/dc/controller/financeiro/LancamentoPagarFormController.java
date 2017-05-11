package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.enums.TipoVencimentoEn;
import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.FornecedorListController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.model.business.financeiro.LancamentoPagarBusiness;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.financeiro.IDocumentoOrigemDAO;
import dc.servicos.dao.financeiro.ILctoPagarNtFinanceiroDAO;
import dc.servicos.dao.financeiro.INaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.IParcelaPagarDAO;
import dc.servicos.dao.financeiro.IStatusParcelaDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.visao.financeiro.LancamentoPagarFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class LancamentoPagarFormController extends
		CRUDFormController<LancamentoPagarEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * BUSINESS
	 */
	@Autowired
	private LancamentoPagarBusiness<LancamentoPagarEntity> business;

	// @Autowired
	// private StatusParcelaBusiness<StatusParcela> businessStatus;
	
	//@Autowired
	//private FornecedorBusiness<FornecedorEntity> businessFornecedor;
	
	//@Autowired
	//private LctoPagarNtFinanceiraBusiness<LctoPagarNtFinanceira> naturezaFinanceiraBusiness;
	
	//@Autowired
	//private NaturezaFinanceiraBusiness<NaturezaFinanceira> naturezaFinBusiness;

	LancamentoPagarFormView subView;

	//@Autowired
	//private LancamentoPagarDAO lancamentoPagarDAO;

	@Autowired
	private IParcelaPagarDAO parcelaPagarDAO;

	private LancamentoPagarEntity currentBean;
	private ParcelaPagar parcelaPagar;
	
	@Autowired
	private IPessoaDAO pessoaDAO;

	@Autowired
	private IContabilContaDAO contabilcontaDAO;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO; 

	@Autowired
	private IFornecedorDAO fornecedorDAO;

	@Autowired
	private ILctoPagarNtFinanceiroDAO naturezaFinanceiraDAO;
	
	@Autowired
	private INaturezaFinanceiraDAO naturezaFinanDAO;

	@Autowired
	private IDocumentoOrigemDAO documentoOrigemDAO;

	@Autowired
	private IStatusParcelaDAO statusParcelaDAO;

	public LancamentoPagarBusiness<LancamentoPagarEntity> getBusiness() {
	 return business;
}

	@Override
	protected String getNome() {
		return "Lançamento à Pagar";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		subView.preencheBean(currentBean);

		boolean valido = true;

		List<ParcelaPagar> parcelasPagar = subView.getParcelasSubForm().getDados();
		List<LctoPagarNtFinanceira> naturezasFinanceiras = subView.getNaturezaFinanceiraSubForm().getDados();

		if (((BigDecimal) subView.getTxValorPagar().getConvertedValue()).compareTo(getTotalParcelaPagar(parcelasPagar)) != 0) {
			adicionarErroDeValidacao(subView.getParcelasSubForm(),
					"Os valores informados nas parcelas nao batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas nao batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getTxValorPagar().getConvertedValue()).compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
			adicionarErroDeValidacao(subView.getNaturezaFinanceiraSubForm(),
					"Os valores informados nas naturezas financeiras nÃ£o batem com o valor a pagar.");
			valido = false;

			mensagemErro("Os valores informados nas naturezas financeiras nao batem com o valor a pagar.");
		}

		if (valido) {

			setIntervaloParcelaByTipoVencimento();

			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("Outro");
			if (statusParcela == null) {
				mensagemErro("O status de parcela em aberto nao esta cadastrado.\nEntre em contato com a Software House.");
			} else {
				for (ParcelaPagar p : currentBean.getParcelasPagar()) {
					p.setStatusParcela(statusParcela);
				}

				try {
					currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
							.getConta().getEmpresa());
					business.saveOrUpdate(currentBean);
					notifiyFrameworkSaveOK(this.currentBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void salvarParcelasPagar() {
		StatusParcela statusParcela;
		try {

			statusParcela = this.statusParcelaDAO.findBySituacao("Outro");
			// statusParcela =
			// this.businessStatus.findByLancamento(this.currentBean);
			
			
			 //busca os dados do status de parcela padrao.
	        //String sql = "select STATUS_PARCELA from dc.entidade.financeiro.StatusParcela as STATUS_PARCELA where STATUS_PARCELA.id = 1";
	       // StatusParcela status = (StatusParcela) session.createQuery(sql).uniqueResult();
	        //salva as parcelas a pagar
           /* List<ParcelaPagar> parcelas = getParcelaPagar(parcelaPagar, currentBean.getQuantidadeParcela(), currentBean, currentBean);
            for (int i = 0; i < parcelas.size(); i++) {
                parcelas.get(i).setStatusParcela(status);
                session.save(parcelas.get(i));
            }*/


			if (statusParcela == null) {
				mensagemErro("O status de parcela em aberto não está cadastrado.\nEntre em contato com a Software House.");
			} else {
				for (ParcelaPagar p : currentBean.getParcelasPagar()) {
					p.setStatusParcela(statusParcela);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setIntervaloParcelaByTipoVencimento() {
		if (TipoVencimentoEn.M.equals(subView.getCbTipoVencimento().getValue())) {
			currentBean.setIntervaloEntreParcelas(30);
		}
	}

	@Override
	protected void carregar(Serializable id) {

		try {
			//currentBean = this.lancamentoPagarDAO.find(id);
			currentBean = this.business.find((Integer) id);
			subView.preencheForm(currentBean);
			
			List<LctoPagarNtFinanceira> itens = naturezaFinanceiraDAO.findByNatureza(currentBean);
			subView.preencheSubForm(itens);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new LancamentoPagarFormView(this);
			
			
			this.fieldGroup = new DCFieldGroup<>(LancamentoPagarEntity.class);
			
			// Mapeia os campos
			//fieldGroup.bind(this.subView.getTxValorTotal(),"valorTotal");
			fieldGroup.bind(this.subView.getTxQuantidadeParcela(),"quantidadeParcela");
			fieldGroup.bind(this.subView.getCbPagamentoCompartilhado(),"pagamentoCompartilhado");
			fieldGroup.bind(this.subView.getDtPrimeiroVencimento(),"primeiroVencimento");
			
			fieldGroup.bind(this.subView.getCbDocumentoOrigem(),"documentoOrigem");
			//fieldGroup.bind(this.subView.getCbPessoa(),"pessoa");
			fieldGroup.bind(this.subView.getCbFornecedor(),"fornecedor");
			
			
			this.subView.getCbContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			this.subView.getCbDocumentoOrigem().configuraCombo(
					"descricao", DocumentoOrigemListController.class, this.documentoOrigemDAO, this.getMainController());
			/*this.subView.getCbPessoa().configuraCombo(
					"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());*/
			this.subView.getCbFornecedor().configuraCombo(
					"pessoa.nome", FornecedorListController.class, this.fornecedorDAO, this.getMainController());
			
			subView.getBtnGerarParcelas().addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						gerarParcelas();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						mensagemErro(e.getMessage());
					}

				}
			});

			subView.getTxValorTotal().addBlurListener(new BlurListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {
					subView.getTxValorPagar().setConvertedValue(
							subView.getTxValorTotal().getConvertedValue());

				}
			});

			subView.getDtLancamento().setValue(new Date());
			subView.getTxIntervaloParcela().setEnabled(false);

			subView.getCbTipoVencimento().addValueChangeListener(
					new ValueChangeListener() {

						/**
				 * 
				 */
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(ValueChangeEvent event) {
							TipoVencimentoEn tipoVencimento = (TipoVencimentoEn) subView
									.getCbTipoVencimento().getValue();

							if (TipoVencimentoEn.M.equals(tipoVencimento)) {
								subView.getTxIntervaloParcela().setEnabled(false);
								subView.getTxIntervaloParcela().setValue("");
								currentBean.setIntervaloEntreParcelas(30);
							} else {
								subView.getTxIntervaloParcela().setEnabled(true);
								currentBean.setIntervaloEntreParcelas(null);
							}

						}
					});
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	protected void criarNovoBean() {
		
		
		try {
			currentBean = new LancamentoPagarEntity();
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
			//this.lancamentoPagarDAO.deleteAll(ids);

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
			//LancamentoPagarUtils.validateRequiredFields(this.subView);

			 fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {
			return false;
		}
	}

	private boolean verificaSeFoiParcelado() {
		return ((Integer) subView.getTxQuantidadeParcela().getConvertedValue()) > 1
				&& TipoVencimentoEn.D.equals(subView.getCbTipoVencimento()
						.getValue());
	}

	private BigDecimal getTotalParcelaPagar(List<ParcelaPagar> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(
			List<LctoPagarNtFinanceira> naturezasFinanceiras) {
		BigDecimal total = BigDecimal.ZERO;
		if (naturezasFinanceiras != null) {
			for (int i = 0; i < naturezasFinanceiras.size(); i++) {
				total = total.add(naturezasFinanceiras.get(i).getValor());
			}
		}
		return total;
	}

	/*@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			LancamentoPagarEntity lancamentoPagar = (LancamentoPagarEntity) id;
			List<LctoPagarNtFinanceira> lctoPagarNtFinanceiras = lancamentoPagar
					.getLctoPagarNtFinanceiras();

			for (LctoPagarNtFinanceira lctoPagarNtFinanceira : lctoPagarNtFinanceiras) {
				lctoPagarNtFinanceira.setLancamentoPagar(null);

			}

			List<ParcelaPagar> parcelasPagar = lancamentoPagar
					.getParcelasPagar();

			for (ParcelaPagar parcelaPagar : parcelasPagar) {

				parcelaPagar.setLancamentoPagar(null);
			}

			remover(ids);
			// lancamentoPagarDAO.delete(lancamentoPagar);

		}
		mensagemRemovidoOK();
	}*/
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			LancamentoPagarEntity lancamentoPagar = (LancamentoPagarEntity) id;

			try {
				business.delete(lancamentoPagar);
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

	public void gerarParcelas() throws Exception {

		if (validaSalvar()) {
			final ContaCaixa contaCaixa = (ContaCaixa) subView
					.getCbContaCaixa().getValue();
			if (contaCaixa == null || contaCaixa.getId() == null) {
				throw new Exception(
						"É necessário informar a conta caixa para previsão das parcelas.");
			}
			
			final List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
			List<ParcelaPagar> dados = subView.getParcelasSubForm().getDados();
			if (dados != null) {
				parcelasPagar.addAll(subView.getParcelasSubForm().getDados());
			}

			if (parcelasPagar != null && !parcelasPagar.isEmpty()) {
				ConfirmDialog
						.show(MainUI.getCurrent(),
								"Confirme a remoção",
								"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
								"Sim", "Não", new ConfirmDialog.Listener() {

									/**
							 * 
							 */
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelas(parcelasPagar);
											geraParcelas(contaCaixa, 
													parcelasPagar);
										}
									}
								});
			} else {
				geraParcelas(contaCaixa, parcelasPagar);
			}

		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}

	}
	
	/*private void gerarParcelaPagar(ContaCaixa contaCaixa, final List<ParcelaPagar> parcelasPagar) {

        //gera as parcelas
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);

		setIntervaloParcelaByTipoVencimento();
        ParcelaPagar p;
        LancamentoPagarEntity lancamentoPagar = currentBean;
       // BigDecimal numeroParcelas;
        BigDecimal valorParcela = lancamentoPagar.getValorAPagar().divide(BigDecimal.valueOf(lancamentoPagar.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
        BigDecimal somaParcelas = BigDecimal.ZERO;
        BigDecimal residuo = BigDecimal.ZERO;
        List<ParcelaPagar> parcelas = new ArrayList<ParcelaPagar>();
       

        Date dataAtual = new Date();
        Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoPagar.getPrimeiroVencimento());
        Calendar dataVencimentoParcela = Calendar.getInstance();

        for (int i = 0; i < lancamentoPagar.getQuantidadeParcela(); i++) {
            p = new ParcelaPagar();
            p.setDataEmissao(dataAtual);
            p.setContaCaixa(contaCaixa);
            p.setLancamentoPagar(lancamentoPagar);
            p.setNumeroParcela(i + 1);
            
            if (i > 0) {
            	primeiroVencimento.add(Calendar.DAY_OF_MONTH,lancamentoPagar.getIntervaloEntreParcelas());
            	
            }
            p.setDataVencimento(primeiroVencimento.getTime());
			somaParcelas = somaParcelas.add(valorParcela);
           // p.setSofreRetencao(((ParcelaPagar) parcelaPagar).getSofreRetencao());
           // p.setStatusParcela(((ParcelaPagar) parcelaPagar).getStatusParcela());
            //p.setTaxaDesconto(((ParcelaPagar) parcelaPagar).getTaxaDesconto());
           // p.setTaxaJuro(((ParcelaPagar) parcelaPagar).getTaxaJuro());
           // p.setTaxaMulta(((ParcelaPagar) parcelaPagar).getTaxaMulta());
            p.setValor(valorParcela);
           // p.setValorDesconto(((ParcelaPagar) parcelaPagar).getValorDesconto());
          //  p.setValorJuro(((ParcelaPagar) parcelaPagar).getValorJuro());
           // p.setValorMulta(((ParcelaPagar) parcelaPagar).getValorMulta());
            
            if (i == (lancamentoPagar.getQuantidadeParcela() - 1)) {
				residuo = lancamentoPagar.getValorAPagar().subtract(
						somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaPagar.setValor(valorParcela);
			}

            dataVencimentoParcela.setTime(calculaDataVencimento(primeiroVencimento, i + 1));
            p.setDataVencimento(calculaDataVencimento(primeiroVencimento, i + 1));
           // p.setDescontoAte(calculaDiaDesconto(dataVencimentoParcela, descontoAteDia));

            parcelas.add(p);
			novoParcelaPagar(p);
        }
        subView.getParcelasSubForm().fillWith(parcelasPagar);
    }*/
	
	 private Date calculaDataVencimento(Calendar dataPrimeiroVencimento, int numeroParcela) {
	        Calendar dataProximoVencimento = Calendar.getInstance();
	        dataProximoVencimento.setTime(dataPrimeiroVencimento.getTime());
	        dataProximoVencimento.set(Calendar.DAY_OF_MONTH, dataPrimeiroVencimento.get(Calendar.DAY_OF_MONTH));
	        dataProximoVencimento.add(Calendar.MONTH, numeroParcela);

	        return dataProximoVencimento.getTime();
	    }

	    private Date calculaDiaDesconto(Calendar dataVencimento, Integer diaDesconto) {
	        if (diaDesconto != null) {
	            dataVencimento.set(Calendar.DAY_OF_MONTH, diaDesconto);
	            return dataVencimento.getTime();
	        } else {
	            return null;
	        }
	    }



private void geraParcelas(ContaCaixa contaCaixa, final List<ParcelaPagar> parcelasPagar) {
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);

		setIntervaloParcelaByTipoVencimento();

		LancamentoPagarEntity lancamentoPagar = currentBean;
		ParcelaPagar parcelaPagar;
		Date dataEmissao = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoPagar.getPrimeiroVencimento());
		BigDecimal valorParcela = lancamentoPagar.getValorAPagar().divide(
				BigDecimal.valueOf(lancamentoPagar.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		for (int i = 0; i < lancamentoPagar.getQuantidadeParcela(); i++) {
			parcelaPagar = new ParcelaPagar();
			parcelaPagar.setContaCaixa(contaCaixa);
			//parcelaPagar.setStatusParcela(statusParcela);
			parcelaPagar.setNumeroParcela(i + 1);
			parcelaPagar.setDataEmissao(dataEmissao);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,
						lancamentoPagar.getIntervaloEntreParcelas());
			}
			parcelaPagar.setDataVencimento(primeiroVencimento.getTime());
			// parcelaPagar.setSofreRetencao(lancamentoPagar.getFornecedor()
			// .getSofreRetencao());
			parcelaPagar.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (lancamentoPagar.getQuantidadeParcela() - 1)) {
				residuo = lancamentoPagar.getValorAPagar().subtract(
						somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaPagar.setValor(valorParcela);
			}

			parcelasPagar.add(parcelaPagar);
			novoParcelaPagar(parcelaPagar);
		}

		subView.getParcelasSubForm().fillWith(parcelasPagar);
}

	private void excluiParcelas(List<ParcelaPagar> parcelasPagar) {
		List<ParcelaPagar> persistentObjects = subView.getParcelasSubForm()
				.getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			parcelaPagarDAO.delete(persistentObjects.get(i));
		}
		parcelasPagar.clear();
	}

	public ParcelaPagar novoParcelaPagar() {
		ParcelaPagar parcela = new ParcelaPagar();
		return novoParcelaPagar(parcela);
	}

	public ParcelaPagar novoParcelaPagar(ParcelaPagar parcela) {

		currentBean.addParcelaPagar(parcela);

		return parcela;
	}

	public void removerParcelaPagar(List<ParcelaPagar> values) {
		for (ParcelaPagar value : values) {
			currentBean.removeParcelaPagar(value);
		}

	}

	public LctoPagarNtFinanceira novoLctoPagarNtFinanceira() {
		
		LctoPagarNtFinanceira item = new LctoPagarNtFinanceira();
		currentBean.addLctoPagarNtFinanceira(item);
		return item;
	}

	public void removerLctoPagarNtFinanceira(List<LctoPagarNtFinanceira> values) {
		for (LctoPagarNtFinanceira value : values) {
			currentBean.removeLctoPagarNtFinanceira(value);
		}
		
		mensagemRemovidoOK();

	}
	
	@Override
	public LancamentoPagarEntity getModelBean() {
		return currentBean;
	}

	/*
	 * public LctoPagarNtFinanceira adicionarLctoPagarNtFinanceira() { try {
	 * LctoPagarNtFinanceira ent = new LctoPagarNtFinanceira();
	 * ent.setLancamentoPagar(this.currentBean);
	 * 
	 * this.currentBean.getLctoPagarNtFinanceiras().add(ent);
	 * 
	 * return ent; } catch (Exception e) { e.printStackTrace();
	 * 
	 * throw e; } }
	 * 
	 * public BeanItemContainer<NaturezaFinanceira> getNaturezaFinanceiraBic() {
	 * 
	 * try { List<NaturezaFinanceira> auxLista =
	 * this.naturezaFinanceiraDAO.findAll();
	 * 
	 * BeanItemContainer<NaturezaFinanceira> bic = new
	 * BeanItemContainer<NaturezaFinanceira>( NaturezaFinanceira.class,
	 * auxLista);
	 * 
	 * return bic; } catch (Exception e) { e.printStackTrace();
	 * 
	 * return null; } }
	 * 
	 * /*public List<NaturezaFinanceira> getNaturezasFinanceiras() { // TODO
	 * Auto-generated method stub try { List<NaturezaFinanceira> auxLista =
	 * this.naturezaFinanceiraDAO.findAll();
	 * //BeanItemContainer<NaturezaFinanceira> bic = new
	 * BeanItemContainer<NaturezaFinanceira>(NaturezaFinanceira.class,
	 * auxLista);
	 * 
	 * return auxLista; } catch (Exception e) { e.printStackTrace();
	 * 
	 * return null; } }
	 */
	
	
    public List<NaturezaFinanceira> getNaturezasFin() {
		
		try {
			
			DefaultManyToOneComboModel<NaturezaFinanceira> natureza = new DefaultManyToOneComboModel<NaturezaFinanceira>(
					NaturezaFinanceiraListController.class, this.naturezaFinanDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getCbNaturezaFinanceira().setModel(natureza);
			
			return naturezaFinanDAO.findByNatureza(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<LctoPagarNtFinanceira> getNaturezasFinanceiras() {
		
		try {
			return naturezaFinanceiraDAO.findByNatureza(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<NaturezaFinanceira> buscarNaturezas() {
		return naturezaFinanDAO.getAll(NaturezaFinanceira.class);
	}

}
