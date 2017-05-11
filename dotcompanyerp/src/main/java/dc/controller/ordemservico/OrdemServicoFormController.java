package dc.controller.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dc.control.util.ClassUtils;
import dc.control.util.classes.ordemservico.OrdemServicoUtils;
import dc.control.validator.DotErpException;
import dc.controller.financeiro.TipoPagamentoListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.ordemservico.AcessorioEntity;
import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.CarroEntity;
import dc.entidade.ordemservico.EntradaServicoEntity;
import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.entidade.ordemservico.ParametroOsEntity;
import dc.entidade.ordemservico.ServicoOsEntity;
import dc.entidade.ordemservico.SituacaoServicoEntity;
import dc.entidade.ordemservico.StatusOsEntity;
import dc.entidade.ordemservico.TipoEfetivacaoOsEntity;
import dc.entidade.ordemservico.TipoServicoOsEntity;
import dc.entidade.ordemservico.VendaPecaEntity;
import dc.model.business.geral.pessoal.ClienteBusiness;
import dc.model.business.geral.pessoal.ColaboradorBusiness;
import dc.model.business.geral.pessoal.PessoaEnderecoBusiness;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.model.business.ordemservico.AcessorioBusiness;
import dc.model.business.ordemservico.AcessorioOsBusiness;
import dc.model.business.ordemservico.CarroBusiness;
import dc.model.business.ordemservico.EntradaServicoBusiness;
import dc.model.business.ordemservico.InformacaoGeralBusiness;
import dc.model.business.ordemservico.LaudoTecnicoBusiness;
import dc.model.business.ordemservico.MaterialServicoBusiness;
import dc.model.business.ordemservico.ObservacaoBusiness;
import dc.model.business.ordemservico.OrdemServicoBusiness;
import dc.model.business.ordemservico.OrdemServicoEfetivacaoBusiness;
import dc.model.business.ordemservico.ParametroOsBusiness;
import dc.model.business.ordemservico.ServicoOsBusiness;
import dc.model.business.ordemservico.SituacaoServicoBusiness;
import dc.model.business.ordemservico.StatusOsBusiness;
import dc.model.business.ordemservico.TipoEfetivacaoOsBusiness;
import dc.model.business.ordemservico.TipoServicoOsBusiness;
import dc.model.business.ordemservico.VendaPecaBusiness;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.ordemservico.ICarroDAO;
import dc.model.dao.ordemservico.ICorDAO;
import dc.model.dao.ordemservico.IEquipamentoDAO;
import dc.model.dao.ordemservico.IModeloOsDAO;
import dc.model.dao.ordemservico.IOrdemServicoDAO;
import dc.model.dao.ordemservico.ISituacaoServicoDAO;
import dc.model.dao.ordemservico.IStatusOsDAO;
import dc.model.dao.ordemservico.ITipoServicoOsDAO;
import dc.servicos.dao.financeiro.ITipoPagamentoDAO;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.ordemservico.OrdemServicoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class OrdemServicoFormController extends CRUDFormController<OrdemServicoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoPagamentoDAO tipoPagamentoDAO;
	
	@Autowired
	private IClienteDAO daoCliente;
	
	@Autowired
	private IColaboradorDAO daoColaborador;
	
	@Autowired
	private IStatusOsDAO daoStatus;
	
	@Autowired
	private ITipoServicoOsDAO daoTipoServico;
	
	@Autowired
	private ISituacaoServicoDAO daoSituacaoServico;
	
	@Autowired
	private ICarroDAO daoCarro;
	
	@Autowired
	private IEquipamentoDAO equipamentoDAO;
	
	@Autowired
	private IMarcaOsDAO marcaDAO;

	@Autowired
	private IModeloOsDAO modeloDAO;

	@Autowired
	private IOrdemServicoDAO ordemServicoDAO;
	
	@Autowired
	private ICorDAO corDAO;

	private OrdemServicoEntity currentBean;
	private ParametroOsEntity parametroOs;

	private OrdemServicoFormView subView;

	/**
	 * BUSINESS
	 */
	@Autowired
	private OrdemServicoBusiness<OrdemServicoEntity> business;

	@Autowired
	private CarroBusiness<CarroEntity> businessCarro;

	@Autowired
	private InformacaoGeralBusiness<InformacaoGeralEntity> businessInformacao;

	@Autowired
	private LaudoTecnicoBusiness<LaudoTecnicoEntity> businessLaudoTecnico;

	@Autowired
	private EntradaServicoBusiness<EntradaServicoEntity> businessEntradaServico;

	@Autowired
	private MaterialServicoBusiness<MaterialServicoEntity> businessMaterialServico;

	@Autowired
	private VendaPecaBusiness<VendaPecaEntity> businessVendaPeca;

	@Autowired
	private ObservacaoBusiness<ObservacaoEntity> businessObservacao;

	@Autowired
	private OrdemServicoEfetivacaoBusiness<OrdemServicoEfetivacaoEntity> businessOrdemServicoEfetivacao;

	@Autowired
	private ParametroOsBusiness<ParametroOsEntity> businessParametroOs;

	@Autowired
	private AcessorioOsBusiness<AcessorioOsEntity> businessAcessorioOs;

	@Autowired
	private AcessorioBusiness<AcessorioEntity> businessAcessorio;

	@Autowired
	private TipoEfetivacaoOsBusiness<TipoEfetivacaoOsEntity> businessTipoEfetivacaoOs;

	@Autowired
	private StatusOsBusiness<StatusOsEntity> businessStatusOs;

	@Autowired
	private TipoServicoOsBusiness<TipoServicoOsEntity> businessTipoServicoOs;

	@Autowired
	private SituacaoServicoBusiness<SituacaoServicoEntity> businessSituacaoServico;

	@Autowired
	private ClienteBusiness<ClienteEntity> businessCliente;

	@Autowired
	private ColaboradorBusiness<ColaboradorEntity> businessColaborador;

	@Autowired
	private PessoaEnderecoBusiness<PessoaEnderecoEntity> businessPessoaEndereco;

	@Autowired
	private ServicoOsBusiness<ServicoOsEntity> businessServicoOs;

	@Autowired
	private ProdutoBusiness<ProdutoEntity> businessProduto;

	/**
	 * CONSTRUTOR
	 */
	public OrdemServicoFormController() {
	}

	final List<OrdemServicoEfetivacaoEntity> parcelasChequeOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasCarneOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasCartaoOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasBoletoOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasDuplicataOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasValeOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaBancariaOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaCarteiraOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
	private ClienteEntity cliente = new ClienteEntity();
	Window subWindow;

	public OrdemServicoBusiness<OrdemServicoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Ordem de Serviço";
	} 

	@Override
	protected Component getSubView() {
		return subView;
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
	public OrdemServicoEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void actionSalvar() {
		try { 
			boolean valido = validaSalvar();
			if(valido){
				ClienteEntity cli = new ClienteEntity();
				if(subView.getCbCliente().getValue()!=null){
					cli = subView.getCbCliente().getValue();
					this.currentBean.setCliente(cli);
				} 
				if(subView.getTfTotalServico()!=null){
					String valorTotalSevico = subView.getTfTotalServico().getValue();
					if (Validator.validateString(valorTotalSevico)) {
						valorTotalSevico = formataBigDecimal(valorTotalSevico);
						this.currentBean.setValorServico(new BigDecimal(valorTotalSevico));
					}
				}
				if(subView.getTfTotalPeca()!=null){
					String valorTotalPeca = subView.getTfTotalPeca().getValue();
					if (Validator.validateString(valorTotalPeca)) {
						valorTotalPeca = formataBigDecimal(valorTotalPeca);
						this.currentBean.setValorPeca(new BigDecimal(valorTotalPeca));
					}
				}
				if(subView.getTfTotalProdutoGeral()!=null){
					String valorTotalProduto = subView.getTfTotalProdutoGeral().getValue();
					if (Validator.validateString(valorTotalProduto)) {
						valorTotalProduto = formataBigDecimal(valorTotalProduto);
						this.currentBean.setValorPeca(new BigDecimal(valorTotalProduto));
					}
				}
				if(subView.getTfTotais()!=null){
					String valorTotalOs = subView.getTfTotais().getValue();
					if (Validator.validateString(valorTotalOs)) {
						valorTotalOs = formataBigDecimal(valorTotalOs);
						this.currentBean.setValorTotalOs(new BigDecimal(valorTotalOs));
					}
				}
				if(subView.getTfDesconto()!=null){
					String valorTotalDesconto = subView.getTfDesconto().getValue();
					if (Validator.validateString(valorTotalDesconto)) {
						valorTotalDesconto = formataBigDecimal(valorTotalDesconto);
						this.currentBean.setValorDesconto(new BigDecimal(valorTotalDesconto));
					}
				}

				if(subView.getTfComissaoTecnico()!=null){
					String valorComissaoTecnico = subView.getTfComissaoTecnico().getValue();
					if (Validator.validateString(valorComissaoTecnico)) {
						valorComissaoTecnico = formataBigDecimal(valorComissaoTecnico);
						this.currentBean.setValorComissaoTecnico(new BigDecimal(valorComissaoTecnico));
					}
				}
				if(subView.getTfComissaoVendedor()!=null){
					String valorComissaoVendedor = subView.getTfComissaoVendedor().getValue();
					if (Validator.validateString(valorComissaoVendedor)) {
						valorComissaoVendedor = formataBigDecimal(valorComissaoVendedor);
						this.currentBean.setValorComissaoVendedor(new BigDecimal(valorComissaoVendedor));
					}
				}
				if(subView.getTfLucroParcialServico()!=null){
					String lucroParcialServico = subView.getTfLucroParcialServico().getValue();
					if (Validator.validateString(lucroParcialServico)) {
						lucroParcialServico = formataBigDecimal(lucroParcialServico);
						this.currentBean.setValorLucroParcial(new BigDecimal(lucroParcialServico));
					}
				}
				if(subView.getTfLucroServico()!=null){
					String lucroServico = subView.getTfLucroServico().getValue();
					if (Validator.validateString(lucroServico)) {
						lucroServico = formataBigDecimal(lucroServico);
						this.currentBean.setValorLucroServico(new BigDecimal(lucroServico));
					}
				}

				if(currentBean.getItensOrdemServicoEfetivacao().size() > 0){
					currentBean.setEfetivada(true);
				} 
				if(this.currentBean.getItensVendaPeca().size() > 0){
					BigDecimal valorTotalCompra = BigDecimal.ZERO;
					for(VendaPecaEntity vp : this.currentBean.getItensVendaPeca()){
						valorTotalCompra = valorTotalCompra.add(vp.getValorCompra().multiply(vp.getQuantidadeProduto()));
					}
					this.currentBean.setValorLucroPeca(this.currentBean.getValorPeca().subtract(valorTotalCompra));
				}
				
				this.business.saveOrUpdate(this.currentBean);

				salvarInformacaoGeral();
				salvarLaudoTecnico();  
				salvarObservacao();
				
				removeModal();
				notifiyFrameworkSaveOK(this.currentBean);
			
			}
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			OrdemServicoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());
			return false;
		}
	}

	
	public void salvarInformacaoGeral() {
		InformacaoGeralEntity informacaoGeral;
		try {
			informacaoGeral = this.businessInformacao.findByOrdemServico(currentBean);
			
			if(informacaoGeral == null){
				informacaoGeral = new InformacaoGeralEntity();
			}

			if (currentBean != null) {
				if (currentBean.getId() > 0) {
					informacaoGeral.setOrdemServico(currentBean);
				}
				if (subView.getPdfDataEntrada() != null) {
					informacaoGeral.setDataEntrada(subView.getPdfDataEntrada().getValue());
				}
				if (subView.getPdfDataEfetiv() != null) {
					informacaoGeral.setDataEfetivacao(subView.getPdfDataEfetiv().getValue());
				}
				if (!subView.getTfNumeroComanda().getValue().equals("")) {
					informacaoGeral.setNumeroComanda(Integer.parseInt(subView.getTfNumeroComanda().getValue()));
				}
				if (subView.getCbStatus() != null) {
					informacaoGeral.setStatusOs(subView.getCbStatus().getValue());
				}
				if (subView.getCbSituacaoServico() != null) {
					informacaoGeral.setSituacaoServico(subView.getCbSituacaoServico().getValue());
				}
				if (subView.getCbPlaca() != null) {
					informacaoGeral.setCarro(subView.getCbPlaca().getValue());
				}
				if (subView.getCbAtendente() != null) {
					informacaoGeral.setAtendente(subView.getCbAtendente().getValue());
				}
				if (subView.getTfFone() != null) {
					informacaoGeral.setTelefone(subView.getTfFone().getValue());
				}
				if (!subView.getTfkm().getValue().equals("")) {
					informacaoGeral.setKmHorRodado(Integer.parseInt(subView.getTfkm().getValue()));
				}
				if (subView.getPdfProximaRevisao() != null) {
					informacaoGeral.setDataProximaRevisao(subView.getPdfProximaRevisao().getValue());
				}
				if (subView.getCbTipoServico() != null) {
					informacaoGeral.setTipoServico(subView.getCbTipoServico().getValue());
				}
				if (subView.getTaObservacaoDefeito() != null && !subView.getTaObservacaoDefeito().equals("")) {
					informacaoGeral.setObservacao(subView.getTaObservacaoDefeito().getValue());
				}
				if (subView.getCbFormaPagamento() != null) {
					informacaoGeral.setTipoPagamento(subView.getCbFormaPagamento().getValue());
				}
				if (subView.getPdfEntrega() != null) {
					informacaoGeral.setDataEntrega(subView.getPdfEntrega().getValue());
				}
				informacaoGeral.setOrdemServico(currentBean);
				this.businessInformacao.saveOrUpdate(informacaoGeral);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarLaudoTecnico() {
		LaudoTecnicoEntity laudoTecnico = this.businessLaudoTecnico.findByOrdemServico(currentBean);

		if(laudoTecnico == null){
			laudoTecnico = new LaudoTecnicoEntity();
		}
		try {
			if (currentBean != null) {
				if (currentBean.getId() > 0) {
					laudoTecnico.setOrdemServico(currentBean);
				}
				if (subView.getTaObservacaoLaudoTecnico() != null) {
					laudoTecnico.setObservacaoLaudoTecnico((String) subView.getTaObservacaoLaudoTecnico().getValue());
				}
				if (subView.getTaObservacaoLaudoFerramentas() != null) {
					laudoTecnico.setObservacaoLaudoFerramentas((String) subView.getTaObservacaoLaudoFerramentas().getValue());
				}
				laudoTecnico.setOrdemServico(currentBean);
				this.businessLaudoTecnico.saveOrUpdate(laudoTecnico);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarObservacao() {
		ObservacaoEntity observacao = new ObservacaoEntity();
		observacao = this.businessObservacao.buscaObservacao(currentBean);

		try {
			if (currentBean != null) {
				if(!subView.getTaObservacaoOS().equals("") || subView.getTaObservacaoOS()!=null ) {
					observacao.setObservacaoOs(subView.getTaObservacaoOS().getValue());
				}
				if(!subView.getTaObservacaoLocal().equals("") || subView.getTaObservacaoLocal()!=null) {
					observacao.setFicandoLocal(subView.getTaObservacaoLocal().getValue());
				}
				observacao.setOrdemServico(currentBean);
				if(!subView.getTaObservacaoLocal().equals("") || !subView.getTaObservacaoOS().equals("")){
					this.businessObservacao.saveOrUpdate(observacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void actionSalvarEfetivacao() {
		boolean valido = true;
		if (((BigDecimal) subView.getValorTotalChequeOs()).compareTo(getTotalParcelaReceberCheque(parcelasChequeOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoChequeSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCarneOs()).compareTo(getTotalParcelaReceberCarne(parcelasCarneOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCarneSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCartaoOs()).compareTo(getTotalParcelaReceberCartao(parcelasCartaoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCartaoSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}
		if (((BigDecimal) subView.getValorTotalBoletoOs()).compareTo(getTotalParcelaReceberBoleto(parcelasBoletoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoBoletoSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (valido) {
			try {
				for (OrdemServicoEfetivacaoEntity p : parcelasChequeOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
					this.businessOrdemServicoEfetivacao.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacaoEntity p : parcelasCarneOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
					businessOrdemServicoEfetivacao.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacaoEntity p : parcelasCartaoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
					businessOrdemServicoEfetivacao.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacaoEntity p : parcelasBoletoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());

					businessOrdemServicoEfetivacao.saveOrUpdate(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private BigDecimal getTotalParcelaReceberCheque(List<OrdemServicoEfetivacaoEntity> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCarne(List<OrdemServicoEfetivacaoEntity> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCartao(List<OrdemServicoEfetivacaoEntity> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberBoleto(List<OrdemServicoEfetivacaoEntity> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private void preencheCombos() {
		
		this.subView.getCbCliente().configuraCombo(
				"pessoa.nome", ClienteListController.class, this.daoCliente, this.getMainController());
		
		this.subView.getCbAtendente().configuraCombo(
				"pessoa.nome", ColaboradorListController.class, this.daoColaborador, this.getMainController());
		
		this.subView.getCbStatus().configuraCombo(
				"descricao", StatusOsListController.class, this.daoStatus, this.getMainController());
		
		this.subView.getCbTipoServico().configuraCombo(
				"descricao", TipoServicoListController.class, this.daoTipoServico, this.getMainController());
		
		this.subView.getCbSituacaoServico().configuraCombo(
				"descricao", SituacaoServicoListController.class, this.daoSituacaoServico, this.getMainController());
		
		this.subView.getCbFormaPagamento().configuraCombo(
				"descricao", TipoPagamentoListController.class, this.tipoPagamentoDAO, this.getMainController());
		
		this.subView.getCbPlaca().configuraCombo(
				"placa", CarroListController.class, this.daoCarro, this.getMainController());
		
		this.subView.getCbEquipamentoGarantia().configuraCombo(
				"descricao", EquipamentoListController.class, this.equipamentoDAO, this.getMainController());
		
		this.subView.getCbMarcaGarantia().configuraCombo(
				"nome", MarcaListController.class, this.marcaDAO, this.getMainController());
		
		this.subView.getCbModeloGarantia().configuraCombo(
				"nome", ModeloListController.class, this.modeloDAO, this.getMainController());
		
		this.subView.getCbCorGarantia().configuraCombo(
				"nome", CorListController.class, this.corDAO, this.getMainController());
		


//		DefaultManyToOneComboModel<Equipamento> equipamento = new DefaultManyToOneComboModel<Equipamento>(EquipamentoListController.class,
//				this.equipamentoDAO, super.getMainController()) {
//			@Override
//			public String getCaptionProperty() {
//				return "descricao";
//			}
//		};
//
//		this.subView.getCbEquipamentoGarantia().setModel(equipamento);
//
//		DefaultManyToOneComboModel<Marca> marca = new DefaultManyToOneComboModel<Marca>(MarcaListController.class, this.marcaDAO,
//				super.getMainController());
//
//		this.subView.getCbMarcaGarantia().setModel(marca);
//
//		DefaultManyToOneComboModel<Modelo> modelo = new DefaultManyToOneComboModel<Modelo>(ModeloListController.class, this.modeloDAO,
//				super.getMainController());
//
//		this.subView.getCbModeloGarantia().setModel(modelo);
//
//		DefaultManyToOneComboModel<Cor> cor = new DefaultManyToOneComboModel<Cor>(CorListController.class, this.corDAO, super.getMainController());
//
//		this.subView.getCbCorGarantia().setModel(cor);

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = this.business.find((Integer) id);
			subView.preencheForm(currentBean);
			carregarInformacaoGeral();
			carregarLaudoTecnico();
			carregarEntradaServico();
			carregarVendaPeca();
			carregarMaterialServico();
			carregarAcessorioOs();
			carregarObservacao();
			carregarInformacaoFinanceira();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void carregarInformacaoGeral() {

		InformacaoGeralEntity info = new InformacaoGeralEntity();
		try {
			info = this.businessInformacao.findByOrdemServico(this.currentBean);
			
			if (info != null) {
				if(info.getAtendente()!=null){
					subView.getCbAtendente().setValue(info.getAtendente());
				}
				if(info.getDataEntrada()!=null){
					subView.getPdfDataEntrada().setValue(info.getDataEntrada());
				}
				if(info.getDataEntrada()!=null){
					subView.getPdfDataEfetiv().setValue(info.getDataEntrada());
				}
				if(info.getNumeroComanda()!=null){
					subView.getTfNumeroComanda().setValue(info.getNumeroComanda().toString());
				}
				if(info.getStatusOs()!=null){
					subView.getCbStatus().setValue(info.getStatusOs());
				}
				if(info.getSituacaoServico()!=null){
					subView.getCbSituacaoServico().setValue(info.getSituacaoServico());
				}
				if(info.getTelefone()!=null){
					subView.getTfFone().setValue(info.getTelefone());
				}
				if(info.getCarro()!=null){
					subView.getCbPlaca().setValue(info.getCarro());
				}
				if(info.getKmHorRodado()!=null){
					subView.getTfkm().setValue(info.getKmHorRodado().toString());
				}
				if(info.getDataProximaRevisao()!=null){
					subView.getPdfProximaRevisao().setValue(info.getDataProximaRevisao());
				}
				if(info.getTipoServico()!=null){
					subView.getCbTipoServico().setValue(info.getTipoServico());
				}
				if(info.getObservacao()!=null){
					subView.getTaObservacaoDefeito().setValue(info.getObservacao());
				}
				if(info.getTipoPagamento()!=null){
					subView.getCbFormaPagamento().setValue(info.getTipoPagamento());
				}
				if(info.getDataEntrega()!=null){
					subView.getPdfEntrega().setValue(info.getDataEntrega());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarLaudoTecnico() {
		LaudoTecnicoEntity laudo = this.businessLaudoTecnico.findByOrdemServico(currentBean);

		if (laudo != null) {
			subView.getTaObservacaoLaudoTecnico().setValue(laudo.getObservacaoLaudoTecnico());
			subView.getTaObservacaoLaudoFerramentas().setValue(laudo.getObservacaoLaudoFerramentas());
		}
	}

	public void carregarEntradaServico() {
		try {
			currentBean.setItensEntradaServico(this.businessEntradaServico.findByOrdemServico(currentBean));
			if(currentBean.getItensEntradaServico().size() > 0){
				subView.preencheEntradaServicoSubForm(currentBean.getItensEntradaServico());
			}
			if(currentBean.getItensEntradaServico().size() > 0){
				subView.preencheEntradaServicoFinanceiraSubForm(currentBean.getItensEntradaServico());
			}
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR ENTRADA DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarVendaPeca() {
		try {
			currentBean.setItensVendaPeca(this.businessVendaPeca.findByOrdemServico(currentBean));
			if(currentBean.getItensVendaPeca().size() > 0){
				subView.preencheVendaPecaSubForm(currentBean.getItensVendaPeca());
			}
			if(currentBean.getItensVendaPeca().size() > 0){
				subView.preencheVendaPecaFinanceiraSubForm(currentBean.getItensVendaPeca());
			}

		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR VENDA DE PEÇA");
			e.printStackTrace();
		}
	}

	public void carregarMaterialServico() {
		try {
			currentBean.setItensMaterialServico(this.businessMaterialServico.findByOrdemServico(currentBean));
			if(currentBean.getItensMaterialServico().size() > 0){
				subView.preencheMaterialServicoSubForm(currentBean.getItensMaterialServico());
			}
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR MATERIAL DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarAcessorioOs() {
		try {
			currentBean.setItensAcessorioOs(this.businessAcessorioOs.findByOrdemServico(currentBean));
			if(currentBean.getItensAcessorioOs().size() > 0){
				subView.preencheAcessorioOsSubForm(currentBean.getItensAcessorioOs());
			}
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR ACESSORIOS DE ORDEM DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarObservacao() {
		ObservacaoEntity observacao = this.businessObservacao.buscaObservacao(currentBean);

		if (observacao != null) {
			subView.getTaObservacaoOS().setValue(observacao.getObservacaoOs());
			subView.getTaObservacaoLocal().setValue(observacao.getFicandoLocal());
		}
	}

	public void carregarInformacaoFinanceira() {
		if(this.currentBean.getValorPeca()!=null){
			subView.getTfTotalPeca().setConvertedValue(this.currentBean.getValorPeca());
		}
		if(this.currentBean.getValorLucroPeca()!=null){
			subView.getTfLucroPeca().setConvertedValue(this.currentBean.getValorLucroPeca());
		}
		if(this.currentBean.getValorServico()!=null){
			subView.getTfTotalServico().setConvertedValue(this.currentBean.getValorServico());
		}
		if(this.currentBean.getValorLucroServico()!=null){
			subView.getTfLucroServico().setConvertedValue(this.currentBean.getValorLucroServico());
		}
		if(this.currentBean.getValorComissaoTecnico()!=null){
			subView.getTfComissaoTecnico().setConvertedValue(this.currentBean.getValorComissaoTecnico());
		}
		if(this.currentBean.getValorComissaoVendedor()!=null){
			subView.getTfComissaoVendedor().setConvertedValue(this.currentBean.getValorComissaoVendedor());
		}
		if(this.currentBean.getValorComissaoAtendente()!=null){
			subView.getTfComissaoAtendente().setConvertedValue(this.currentBean.getValorComissaoAtendente());
		}
		if(this.currentBean.getValorDesconto()!=null){
			subView.getTfDesconto().setConvertedValue(this.currentBean.getValorDesconto());
		}
		if(this.currentBean.getValorLucroParcial()!=null){
			subView.getTfLucroParcialServico().setConvertedValue(this.currentBean.getValorLucroParcial());
		}
		if(this.currentBean.getValorTotalOs()!=null){
			subView.getTfTotais().setConvertedValue(this.currentBean.getValorTotalOs());
		}
		if(this.currentBean.getEfetivada()!=null && this.currentBean.getEfetivada()){
			subView.getBtnEfetivacao().setCaption("Financeiro");
		}else{
			subView.getBtnEfetivacao().setCaption("Efetivar OS");
		}

	}
	
	@Override
	protected void initSubView() {
		subView = new OrdemServicoFormView(this);
		preencheCombos();
		
		subView.getBtnFinalizar().addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (subView.getCbCliente().getValue() != null) {
						cliente = (ClienteEntity) subView.getCbCliente().getValue();
						currentBean.setCliente(cliente);
					} else {
						mensagemErro("Favor selecionar um cliente.");
					}
					gerarParcelasOs();
				} catch (Exception e) {
					mensagemErro(e.getMessage());
				}
			}
		});

		subView.getBtnEfetivacao().addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				if(subView.getCbCliente().getValue() == null){
					mensagemErro("Antes de gravar, escolha o cliente, e grave novamente.");
					return;
				}
				if(subView.getCbPlaca().getValue() == null){
					mensagemErro("Antes de gravar, escolha primeiramente a placa do carro, e grave novamente.");
					return;
				}
				subWindow = new Window("Financeiro da Ordem de Serviço");
		        VerticalLayout subContent = new VerticalLayout();
		        subContent.setMargin(true);
		        
        		GridLayout gridLayout = null;
				
				if(currentBean.getEfetivada()!=null){
					if(!currentBean.getEfetivada()){
						ConfirmDialog.show(MainUI.getCurrent(), "Efetivar Ordem Serviço",
								"Tem certeza que deseja efetivar esta OS? depois de efetivado não poderá ser alterado.", "Sim", "Não",
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;
		
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelas(parcelasChequeOs);
											geraParcelasChequeOs(parcelasChequeOs);
										}
							}
						});
					}else{
						if(currentBean.getEfetivada()){
					        subWindow.setModal(true);
					        subWindow.setWidth("57%");
			        		gridLayout = subView.buildAbaEfetivacaoOsFinanceiro(currentBean);
			        		subView.preencheGeralFinanceiroSubForm(currentBean.getItensOrdemServicoEfetivacao());
			        		subView.preencheTituloFinanceiroSubForm(currentBean.getItensOrdemServicoEfetivacao());
			        		subView.preencheParcelasChequeSubForm(currentBean.getItensOrdemServicoEfetivacao());
						}else{
					        subWindow.setModal(false);
					        subWindow.setWidth("59%");
			        		gridLayout = subView.buildAbaEfetivacaoOs();
						}
					}
				}else{
			        subWindow.setModal(false);
			        subWindow.setWidth("59%");
	        		gridLayout = subView.buildAbaEfetivacaoOs();
				}
        		subContent.addComponent(gridLayout);
		        subWindow.setContent(subContent);
		        subWindow.setHeight("70%");
		        subWindow.center();
		        UI.getCurrent().addWindow(subWindow);
			 }
		});	
	}

	public void removeModal(){
        UI.getCurrent().removeWindow(subWindow);
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new OrdemServicoEntity();

	}

	public boolean validaCampos() {
		return true;
	}

	public void gerarParcelasOs() throws Exception {
		if (validaCampos()) {
			final List<OrdemServicoEfetivacaoEntity> parcelasChequeOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasCarneOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasCartaoOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasBoletoOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasDuplicataOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasValeOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaBancariaOs = new ArrayList<OrdemServicoEfetivacaoEntity>();
			final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaCarteiraOs = new ArrayList<OrdemServicoEfetivacaoEntity>();

			List<OrdemServicoEfetivacaoEntity> dadosCheque = subView.getParcelasChequeSubForm().getDados();
			if (dadosCheque != null) {
				parcelasChequeOs.addAll(subView.getParcelasChequeSubForm().getDados());
			}
			if(subView.getTfCheque().getValue()!=null){
				if (parcelasChequeOs != null && !parcelasChequeOs.isEmpty()) {
					ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
							"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;
	
								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										excluiParcelas(parcelasChequeOs);
										geraParcelasChequeOs(parcelasChequeOs);
									}
								}
							});
				} else {
					geraParcelasChequeOs(parcelasChequeOs);
				}
			}
			
			List<OrdemServicoEfetivacaoEntity> dadosCartao = subView.getParcelasCartaoSubForm().getDados();
			if (dadosCartao != null) {
				parcelasCartaoOs.addAll(subView.getParcelasCartaoSubForm().getDados());
			}
			if(subView.getTfCartao().getValue()!=null){
				if (parcelasCartaoOs.isEmpty()) {
					geraParcelasCartaoOs(parcelasCartaoOs);
				}else{
					if(parcelasCartaoOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasCartaoOs);
									geraParcelasCartaoOs(parcelasCartaoOs);
								}
							}
						});
					}
				}
			}
			List<OrdemServicoEfetivacaoEntity> dadosCarne = subView.getParcelasCarneSubForm().getDados();
			if (dadosCarne != null) {
				parcelasCarneOs.addAll(subView.getParcelasCarneSubForm().getDados());
			}
			if(subView.getTfCarne().getValue()!=null){
				if (parcelasCarneOs.isEmpty()) {
					geraParcelasCarneOs(parcelasCarneOs);
				}else{
					if(parcelasCarneOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasCarneOs);
									geraParcelasCarneOs(parcelasCarneOs);
								}
							}
						});
					}
				}
			}
			List<OrdemServicoEfetivacaoEntity> dadosBoleto = subView.getParcelasBoletoSubForm().getDados();
			if (dadosBoleto != null) {
				parcelasBoletoOs.addAll(subView.getParcelasBoletoSubForm().getDados());
			}
			if(subView.getTfBoleto().getValue()!=null){
				if (parcelasBoletoOs.isEmpty()) {
					geraParcelasBoletoOs(parcelasBoletoOs);
				}else{
					if(parcelasBoletoOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasBoletoOs);
									geraParcelasBoletoOs(parcelasBoletoOs);
								}
							}
						});
					}
				}
			}
			List<OrdemServicoEfetivacaoEntity> dadosDuplicata = subView.getParcelasDuplicataSubForm().getDados();
			if (dadosDuplicata != null) {
				parcelasDuplicataOs.addAll(subView.getParcelasDuplicataSubForm().getDados());
			}
			if(subView.getTfDuplicata().getValue()!=null){
				if (parcelasDuplicataOs.isEmpty()) {
					geraParcelasDuplicataOs(parcelasDuplicataOs);
				}else{
					if(parcelasDuplicataOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasDuplicataOs);
									geraParcelasDuplicataOs(parcelasDuplicataOs);
								}
							}
						});
					}
				}
			}

			List<OrdemServicoEfetivacaoEntity> dadosVale = subView.getParcelasValeSubForm().getDados();
			if (dadosVale != null) {
				parcelasValeOs.addAll(subView.getParcelasValeSubForm().getDados());
			}
			if(subView.getTfVale().getValue()!=null){
				if (parcelasValeOs.isEmpty()) {
					geraParcelasValeOs(parcelasValeOs);
				}else{
					if(parcelasValeOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasValeOs);
									geraParcelasValeOs(parcelasValeOs);
								}
							}
						});
					}
				}
			}
			List<OrdemServicoEfetivacaoEntity> dadosCobrancaBancaria = subView.getParcelasCobrancaBancariaSubForm().getDados();
			if (dadosCobrancaBancaria != null) {
				parcelasCobrancaBancariaOs.addAll(subView.getParcelasCobrancaBancariaSubForm().getDados());
			}
			if(subView.getTfCobrancaBancaria().getValue()!=null){
				if (parcelasCobrancaBancariaOs.isEmpty()) {
					geraParcelasCobrancaBancariaOs(parcelasCobrancaBancariaOs);
				}else{
					if(parcelasCobrancaBancariaOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasCobrancaBancariaOs);
									geraParcelasCobrancaBancariaOs(parcelasCobrancaBancariaOs);
								}
							}
						});
					}
				}
			}
			List<OrdemServicoEfetivacaoEntity> dadosCarteira = subView.getParcelasCobrancaCarteiraSubForm().getDados();
			if (dadosCarteira != null) {
				parcelasCobrancaCarteiraOs.addAll(subView.getParcelasCobrancaCarteiraSubForm().getDados());
			}
			if(subView.getTfCobrancaCarteira().getValue()!=null){
				if (parcelasCobrancaCarteiraOs.isEmpty()) {
					geraParcelasCobrancaCarteiraOs(parcelasCobrancaCarteiraOs);
				}else{
					if(parcelasCobrancaCarteiraOs !=null){
						ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasCobrancaCarteiraOs);
									geraParcelasCobrancaCarteiraOs(parcelasCobrancaCarteiraOs);
								}
							}
						});
					}
				}
			}
		}
	}

	private void geraParcelasChequeOs(final List<OrdemServicoEfetivacaoEntity> parcelasCheque) {
		try {
			subView.getParcelasChequeSubForm().removeAllItems();
			subView.preencheBean(currentBean);
			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaChequeOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(2);

			Date dataEmissão = new Date();
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());

			if (!subView.getTfCheque().getConvertedValue().toString().equals("")) {
				BigDecimal vlrTotalCheque = (BigDecimal) subView.getTfCheque().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalCheque);
			}

			if (!subView.getTfQtParcelaCheque().getConvertedValue().toString().equals("")) {
				currentBean.setQuantidadeParcelaCheque(Integer.valueOf(subView.getTfQtParcelaCheque().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoCheque(primeiroVencimento.getTime());

			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCheque()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;

			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaCheque(); i++) {
				parcelaChequeOs = new OrdemServicoEfetivacaoEntity();
				parcelaChequeOs.setOrdemParcela(i);
				parcelaChequeOs.setDataEfetivacao(dataEmissão);
				parcelaChequeOs.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaChequeOs.setDias(30 * (i + 1));
					parcelaChequeOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCheque());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaChequeOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);

				if (i == (currentBean.getQuantidadeParcelaCheque() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaChequeOs.setValorTotal(valorParcela);
				}
				parcelasCheque.add(parcelaChequeOs);
				novoParcelaChequeOs(parcelaChequeOs);
			}

			if(parcelasCheque.size() > 0) {
				subView.preencheParcelasChequeSubForm(parcelasCheque);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraParcelasCarneOs(final List<OrdemServicoEfetivacaoEntity> parcelasCarne) {
		try {
			subView.getParcelasCarneSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcela;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(6);
			Date dataEmissão = new Date();

			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
			if (!subView.getTfCarne().getConvertedValue().toString().equals("")) {
				BigDecimal vlrTotalCarne = (BigDecimal) subView.getTfCarne().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalCarne);
			}
			if (!subView.getTfQtParcelaCarne().getConvertedValue().toString().equals("")) {
				currentBean.setQuantidadeParcelaCarne(Integer.valueOf(subView.getTfQtParcelaCarne().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoCarne(primeiroVencimento.getTime());
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCarne()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaCarne(); i++) {
				parcela = new OrdemServicoEfetivacaoEntity();
				parcela.setOrdemParcela(i);
				parcela.setDataEfetivacao(dataEmissão);
				parcela.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcela.setDias(30 * (i + 1));
					parcela.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCarne());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcela.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);

				if (i == (currentBean.getQuantidadeParcelaCarne() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcela.setValorTotal(valorParcela);
				}

				parcelasChequeOs.add(parcela);
				novoParcelaCarneOs(parcela);
			}			
			if(parcelasChequeOs.size() > 0){
				subView.preencheParcelasCarneSubForm(parcelasChequeOs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraParcelasCartaoOs(final List<OrdemServicoEfetivacaoEntity> parcelasCartao) {
		try {
			subView.getParcelasCartaoSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaCartaoOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(3);
			Date dataEmissão = new Date();

			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());

			if (subView.getTfCartao().getConvertedValue() != null) {
				BigDecimal vlrTotalCartao = (BigDecimal) subView.getTfCartao().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalCartao);
			}

			if (subView.getTfQtParcelaCartao().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaCartao(Integer.valueOf(subView.getTfQtParcelaCartao().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoCartao(primeiroVencimento.getTime());

			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCartao()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaCartao(); i++) {
				parcelaCartaoOs = new OrdemServicoEfetivacaoEntity();
				parcelaCartaoOs.setOrdemParcela(i);
				parcelaCartaoOs.setDataEfetivacao(dataEmissão);
				parcelaCartaoOs.setTipoEfetivacao(tipoEfetivacao);

				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaCartaoOs.setDias(30 * (i + 1));
					parcelaCartaoOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCartao());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaCartaoOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);

				if (i == (currentBean.getQuantidadeParcelaCartao() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaCartaoOs.setValorTotal(valorParcela);
				}
				parcelasCartao.add(parcelaCartaoOs);
				novoParcelaCartaoOs(parcelaCartaoOs);
			}
			if(parcelasCartao.size() > 0){
				subView.preencheParcelasCartaoSubForm(parcelasCartao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraParcelasBoletoOs(final List<OrdemServicoEfetivacaoEntity> parcelasBoletoOs) {
		try {
			subView.getParcelasBoletoSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcela;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(4);
			Date dataEmissão = new Date();
	
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
	
			if (subView.getTfBoleto().getConvertedValue() != null) {
				BigDecimal vlrTotalBoleto = (BigDecimal) subView.getTfBoleto().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalBoleto);
			}
			if (subView.getTfQtParcelaBoleto().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaBoleto(Integer.valueOf(subView.getTfQtParcelaBoleto().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoBoleto(primeiroVencimento.getTime());
	
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaBoleto()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
	
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
	
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaBoleto(); i++) {
				parcela = new OrdemServicoEfetivacaoEntity();
				parcela.setOrdemParcela(i);
				parcela.setDataEfetivacao(dataEmissão);
				parcela.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcela.setDias(30 * (i + 1));
					parcela.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaBoleto());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcela.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);
	
				if (i == (currentBean.getQuantidadeParcelaBoleto() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcela.setValorTotal(valorParcela);
				}
				parcelasBoletoOs.add(parcela);
				novoParcelaBoletoOs(parcela);
			}
			if(parcelasBoletoOs.size() > 0){
				subView.preencheParcelasBoletoSubForm(parcelasBoletoOs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void geraParcelasDuplicataOs(final List<OrdemServicoEfetivacaoEntity> parcelasDuplicata) {
		try {
			subView.getParcelasDuplicataSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaDuplicataOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(5);
			Date dataEmissão = new Date();
	
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
	
			if (subView.getTfDuplicata().getConvertedValue() != null) {
				BigDecimal vlrTotalDuplicata = (BigDecimal) subView.getTfDuplicata().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalDuplicata);
			}
			if (subView.getTfQtParcelaDuplicata().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaDuplicata(Integer.valueOf(subView.getTfQtParcelaDuplicata().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoDuplicata(primeiroVencimento.getTime());
	
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaDuplicata()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
	
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
	
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaDuplicata(); i++) {
				parcelaDuplicataOs = new OrdemServicoEfetivacaoEntity();
				parcelaDuplicataOs.setOrdemParcela(i);
				parcelaDuplicataOs.setDataEfetivacao(dataEmissão);
				parcelaDuplicataOs.setTipoEfetivacao(tipoEfetivacao);
	
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaDuplicataOs.setDias(30 * (i + 1));
					parcelaDuplicataOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaDuplicata());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaDuplicataOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);
	
				if (i == (currentBean.getQuantidadeParcelaDuplicata() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaDuplicataOs.setValorTotal(valorParcela);
				}
				parcelasDuplicata.add(parcelaDuplicataOs);
				novoParcelaDuplicataOs(parcelaDuplicataOs);
			}
			if(parcelasDuplicata.size() > 0){
				subView.preencheParcelasDuplicataSubForm(parcelasDuplicata);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void geraParcelasValeOs(final List<OrdemServicoEfetivacaoEntity> parcelasVale) {
		try {
			subView.getParcelasValeSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaValeOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(7);
			Date dataEmissão = new Date();
	
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
	
			if (subView.getTfVale().getConvertedValue() != null) {
				BigDecimal vlrTotalVale = (BigDecimal) subView.getTfVale().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalVale);
			}
			if (subView.getTfQtParcelaVale().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaVale(Integer.valueOf(subView.getTfQtParcelaVale().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoVale(primeiroVencimento.getTime());
	
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaVale()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
	
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
	
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaVale(); i++) {
				parcelaValeOs = new OrdemServicoEfetivacaoEntity();
				parcelaValeOs.setOrdemParcela(i);
				parcelaValeOs.setDataEfetivacao(dataEmissão);
				parcelaValeOs.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaValeOs.setDias(30 * (i + 1));
					parcelaValeOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaVale());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaValeOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);
	
				if (i == (currentBean.getQuantidadeParcelaVale() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaValeOs.setValorTotal(valorParcela);
				}
				parcelasVale.add(parcelaValeOs);
				novoParcelaValeOs(parcelaValeOs);
			}
			if(parcelasVale.size() > 0){
				subView.preencheParcelasValeSubForm(parcelasVale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraParcelasCobrancaBancariaOs(final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaBancaria) {
		try {
			subView.getParcelasCobrancaBancariaSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaCobrancaBancariaOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(8);
			Date dataEmissão = new Date();
	
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
	
			if (subView.getTfCobrancaBancaria().getConvertedValue() != null) {
				BigDecimal vlrTotalCobrancaBancaria = (BigDecimal) subView.getTfCobrancaBancaria().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalCobrancaBancaria);
			}
			if (subView.getTfQtParcelaCobrancaBancaria().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaCobrancaBancaria(Integer.valueOf(subView.getTfQtParcelaCobrancaBancaria().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoCobrancaBancaria(primeiroVencimento.getTime());
	
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCobrancaBancaria()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
	
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
	
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaCobrancaBancaria(); i++) {
				parcelaCobrancaBancariaOs = new OrdemServicoEfetivacaoEntity();
				parcelaCobrancaBancariaOs.setOrdemParcela(i);
				parcelaCobrancaBancariaOs.setDataEfetivacao(dataEmissão);
				parcelaCobrancaBancariaOs.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaCobrancaBancariaOs.setDias(30 * (i + 1));
					parcelaCobrancaBancariaOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCobrancaBancaria());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaCobrancaBancariaOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);
	
				if (i == (currentBean.getQuantidadeParcelaCobrancaBancaria() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaCobrancaBancariaOs.setValorTotal(valorParcela);
				}
				parcelasCobrancaBancaria.add(parcelaCobrancaBancariaOs);
				novoParcelaCobrancaBancariaOs(parcelaCobrancaBancariaOs);
			}
			if(parcelasCobrancaBancaria.size() > 0){
				subView.preencheParcelasCobrancaBancariaSubForm(parcelasCobrancaBancaria);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void geraParcelasCobrancaCarteiraOs(final List<OrdemServicoEfetivacaoEntity> parcelasCobrancaCarteira) {
		try {
			subView.getParcelasCobrancaCarteiraSubForm().removeAllItems();
			subView.preencheBean(currentBean);

			OrdemServicoEntity ordemServico = currentBean;
			OrdemServicoEfetivacaoEntity parcelaCobrancaCarteiraOs;
			TipoEfetivacaoOsEntity tipoEfetivacao = new TipoEfetivacaoOsEntity();
			tipoEfetivacao = this.businessTipoEfetivacaoOs.findByCodigo(9);
			Date dataEmissão = new Date();
	
			Calendar primeiroVencimento = Calendar.getInstance();
			primeiroVencimento.setTime(primeiroVencimento.getTime());
	
			if (subView.getTfCobrancaCarteira().getConvertedValue() != null) {
				BigDecimal vlrTotalCobrancaCarteira = (BigDecimal) subView.getTfCobrancaCarteira().getConvertedValue();
				currentBean.setValorTotalOs(vlrTotalCobrancaCarteira);
			}
			if (subView.getTfQtParcelaCobrancaCarteira().getConvertedValue() != null) {
				currentBean.setQuantidadeParcelaCobrancaCarteira(Integer.valueOf(subView.getTfQtParcelaCobrancaCarteira().getConvertedValue().toString()));
			}
			currentBean.setPrimeiroVencimentoCobrancaCarteira(primeiroVencimento.getTime());
	
			BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCobrancaCarteira()),
					RoundingMode.HALF_DOWN);
			BigDecimal somaParcelas = BigDecimal.ZERO;
			BigDecimal residuo = BigDecimal.ZERO;
			String nossoNumero;
			DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
			DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
	
			SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
	
			Date dataAtual = new Date();
			for (int i = 0; i < currentBean.getQuantidadeParcelaCobrancaCarteira(); i++) {
				parcelaCobrancaCarteiraOs = new OrdemServicoEfetivacaoEntity();
				parcelaCobrancaCarteiraOs.setOrdemParcela(i);
				parcelaCobrancaCarteiraOs.setDataEfetivacao(dataEmissão);
				parcelaCobrancaCarteiraOs.setTipoEfetivacao(tipoEfetivacao);
				if (i >= 0) {
					primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
					parcelaCobrancaCarteiraOs.setDias(30 * (i + 1));
					parcelaCobrancaCarteiraOs.setDataVencimento(primeiroVencimento.getTime());
				}
				nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
				nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCobrancaCarteira());
				nossoNumero += formatoNossoNumero6.format(dataAtual);
				parcelaCobrancaCarteiraOs.setValorTotal(valorParcela);
				somaParcelas = somaParcelas.add(valorParcela);
	
				if (i == (currentBean.getQuantidadeParcelaCobrancaCarteira() - 1)) {
					residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
					valorParcela = valorParcela.add(residuo);
					parcelaCobrancaCarteiraOs.setValorTotal(valorParcela);
				}
				parcelasCobrancaCarteira.add(parcelaCobrancaCarteiraOs);
				novoParcelaCobrancaCarteiraOs(parcelaCobrancaCarteiraOs);
			}
			if(parcelasCobrancaCarteira.size() > 0){
				subView.preencheParcelasCobrancaCarteiraSubForm(parcelasCobrancaCarteira);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OrdemServicoEfetivacaoEntity novoParcelaChequeOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaCheque(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaCarneOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaCarne(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaCartaoOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaCartao(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaBoletoOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaBoleto(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaDuplicataOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaDuplicata(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaValeOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaVale(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaCobrancaBancariaOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaCobrancaBancaria(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacaoEntity novoParcelaCobrancaCarteiraOs(OrdemServicoEfetivacaoEntity parcela) {
		currentBean.addParcelaCobrancaCarteira(parcela);
		return parcela;
	}

	private void excluiParcelas(List<OrdemServicoEfetivacaoEntity> parcelasReceber) {
		List<OrdemServicoEfetivacaoEntity> persistentObjects = subView.getParcelasChequeSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
//			parcelaChequeDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}

	@Override
	protected void quandoNovo() {
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
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
			this.ordemServicoDAO.deleteAll(objetos);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	public void removerMaterialServico(List<MaterialServicoEntity> values) {
		for (MaterialServicoEntity value : values) {
			currentBean.removeMaterialServico(value);
		}
	}

	public void removerEntradaServico(List<EntradaServicoEntity> values) {
		for (EntradaServicoEntity value : values) {
			currentBean.removeEntradaServico(value);
		}
	}

	public void removerVendaPeca(List<VendaPecaEntity> values) {
		for (VendaPecaEntity value : values) {
			currentBean.removeVendaPeca(value);
		}
	}

	public void removerAcessorioOs(List<AcessorioOsEntity> values) {
		for (AcessorioOsEntity value : values) {
			currentBean.removeAcessorioOs(value);
		}
	}

	public List<ColaboradorEntity> getVendedores() {
		try {
			return businessColaborador.findVendedores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
 
	public List<ColaboradorEntity> getTecnicos() { 
		try {
			return businessColaborador.findTecnicos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ServicoOsEntity> buscarServicoOs() {
		try {
			return businessServicoOs.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<InformacaoGeralEntity> buscarInformacaoGeral() {
		try {
			return businessInformacao.getAll(InformacaoGeralEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void buscarOsAgrupada(ClienteEntity cliente) {
//		List<OrdemServico> osAgrupada = ordemServicoDAO.buscarOsPorCliente(cliente);
//		subView.preencheOsAgrupadaSubForm(osAgrupada);
	}

	public List<ProdutoEntity> buscarProdutos() {
		
		try {
			return businessProduto.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<AcessorioEntity> buscarAcessorio() {
		try {
			return this.businessAcessorio.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public InformacaoGeralEntity novaInformacaoGeral() {
		InformacaoGeralEntity d = new InformacaoGeralEntity();
		// currentBean.adicionarInformacaoGeral(d);
		return d;
	}

	public EntradaServicoEntity novoEntradaServico() {
		EntradaServicoEntity c = new EntradaServicoEntity();
		currentBean.adicionarEntradaServico(c);
		subView.preencheEntradaServicoFinanceiraSubForm(currentBean.getItensEntradaServico());
		return c;
	}

	public VendaPecaEntity novoVendaPeca() {
		VendaPecaEntity c = new VendaPecaEntity();
		currentBean.adicionarVendaPeca(c);
//		subView.preencheVendaPecaFinanceiraSubForm(currentBean.getItensVendaPeca());
		return c;
	}

	public MaterialServicoEntity novoMaterialServico() {
		MaterialServicoEntity c = new MaterialServicoEntity();
		currentBean.adicionarMaterialServico(c);
		return c;
	}

	public AcessorioOsEntity novoAcessorioOs() {
		AcessorioOsEntity c = new AcessorioOsEntity();
		currentBean.adicionarAcessorioOs(c);
		return c;
	}

	public void carregaTotais() {
		currentBean.setValorServico(BigDecimal.ZERO);
		if (subView.getEntradaServicoFinanceiraSubForm() != null) {
			if (subView.getEntradaServicoFinanceiraSubForm().getDados() != null) {
				for (EntradaServicoEntity es : subView.getEntradaServicoFinanceiraSubForm().getDados()) {
					currentBean.setValorServico(currentBean.getValorServico().add(es.getValorTotal()));
				}
			}
		}
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(".", "").replace(",", ".");
		format = format.replace("R$", "");
		return format;
	}

	public OrdemServicoEntity getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(OrdemServicoEntity currentBean) {
		this.currentBean = currentBean;
	}

	public List montaFinanceiro(List<OrdemServicoEfetivacaoEntity> listOrdemServicoEfetivacao) {
		List<OrdemServicoEfetivacaoEntity> listGeral = new ArrayList<OrdemServicoEfetivacaoEntity>();
		int idOs = listOrdemServicoEfetivacao.get(0).getTipoEfetivacao().getCodigo();
		BigDecimal valorTotal = BigDecimal.ZERO;
		String tipo = "";
		int quantidade = 0;
		int i = 1;
		
		for(OrdemServicoEfetivacaoEntity os : listOrdemServicoEfetivacao){
			os.setQuantidade(1);
			if(os.getTipoEfetivacao().getCodigo() != idOs){
				OrdemServicoEfetivacaoEntity totalizador = new OrdemServicoEfetivacaoEntity();	
				totalizador.setValorTotal(valorTotal);
				totalizador.setTipo(tipo);
				totalizador.setQuantidade(quantidade);
				quantidade = 0;
				listGeral.add(totalizador);
				valorTotal = new BigDecimal(0);
				idOs = os.getTipoEfetivacao().getCodigo();
			}
			if(os.getTipoEfetivacao().getCodigo() == 1){
				tipo = "Dinheiro";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 2){
				tipo = "Cheque";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 3){
				tipo = "Cartão";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 4){
				tipo = "Boleto";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 5){
				tipo = "Duplicata";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 6){
				tipo = "Carnê";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 7){
				tipo = "Vale";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 8){
				tipo = "Cobrança bancária";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(os.getTipoEfetivacao().getCodigo() == 9){
				tipo = "Cobrança carteira";
				if(os.getQuantidade()!=null && os.getQuantidade() > 0 ){
					quantidade += os.getQuantidade();
				}else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if(i == listOrdemServicoEfetivacao.size()){
				OrdemServicoEfetivacaoEntity totalizador = new OrdemServicoEfetivacaoEntity();	
				totalizador.setValorTotal(valorTotal);
				totalizador.setTipo(tipo);
				totalizador.setQuantidade(quantidade);
				listGeral.add(totalizador);
				quantidade = 0;
				valorTotal = new BigDecimal(0);
				idOs = os.getTipoEfetivacao().getCodigo();
			}
			i++;
		}
		return listGeral;
	}
	
	
	public ParametroOsEntity getParametroOs() {
		if(SecuritySessionProvider.getUsuario().getConta().getEmpresa()!=null){
			parametroOs = this.businessParametroOs.buscaParametroOs(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		}
		return parametroOs;
	}

	public void setParametroOs(ParametroOsEntity parametroOs) {
		this.parametroOs = parametroOs;
	}
	
	public ClienteEntity findById(ClienteEntity cliente){
		ClienteEntity cli = new ClienteEntity();
		cli = this.businessCliente.findById(cliente);
		
		return cli;
	}
	public List<PessoaEnderecoEntity> listEndereco(PessoaEntity pessoa){
		List<PessoaEnderecoEntity> listEndereco = new ArrayList<PessoaEnderecoEntity>();
		listEndereco = this.businessPessoaEndereco.list(pessoa);
		
		return listEndereco;
	}

}
