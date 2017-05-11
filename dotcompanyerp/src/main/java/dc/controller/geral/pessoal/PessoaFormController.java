package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.CrtEn;
import dc.control.enums.FormaDescontoEn;
import dc.control.enums.FormaPagamentoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.LancamentoComissao;
import dc.control.enums.LocalizacaoEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoComissaoProdutoEn;
import dc.control.enums.TipoComissaoServicoEn;
import dc.control.enums.TipoFreteEn;
import dc.control.enums.TipoPessoaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.enums.TipoSanguineoEn;
import dc.control.enums.VendedorAtendente;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.contabilidade.PlanoContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.controller.geral.diverso.SetorListController;
import dc.controller.geral.outro.SindicatoListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.geral.pessoal.NivelFormacaoEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.pessoal.EstadoCivilBusiness;
import dc.model.business.geral.pessoal.PessoaBusiness;
import dc.model.business.geral.pessoal.PessoaContatoBusiness;
import dc.model.business.geral.pessoal.PessoaEnderecoBusiness;
import dc.model.dao.geral.diverso.ISetorDAO;
import dc.model.dao.geral.outro.ISindicatoDAO;
import dc.model.dao.geral.pessoal.ICargoDAO;
import dc.model.dao.geral.pessoal.IEstadoCivilDAO;
import dc.model.dao.geral.pessoal.IPessoaContatoDAO;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.geral.INivelFormacaoDAO;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoColaboradorDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoForCliDAO;
import dc.servicos.dao.geral.pessoal.ITipoColaboradorDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.PessoaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PessoaFormController extends CRUDFormController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PessoaFormView subView;

	/**
	 * ENTITY
	 */

	private PessoaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PessoaBusiness<PessoaEntity> business;

	@Autowired
	private PessoaContatoBusiness<PessoaContatoEntity> pessoaContatoBusiness;

	@Autowired
	private PessoaEnderecoBusiness<PessoaEnderecoEntity> pessoaEnderecoBusiness;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private EstadoCivilBusiness<EstadoCivilEntity> estadoCivilBusiness;
	
	@Autowired
	private IEstadoCivilDAO estadoCivilDAO;

	@Autowired
	private ISituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private IAtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private IOperacaoFiscalDAO operacaoFiscalDAO;

	@Autowired
	private ITipoColaboradorDAO tipoColaboradorDAO;

	@Autowired
	private ISituacaoColaboradorDAO situacaoColaboradorDAO;

	@Autowired
	private ISindicatoDAO sindicatoDAO;

	@Autowired
	private INivelFormacaoDAO nivelFormacaoDAO;

	@Autowired
	private ICargoDAO cargoDAO;

	@Autowired
	private ISetorDAO setorDAO;

	@Autowired
	private IUfDAO ufDAO;

	@Autowired
	private IPlanoContaDAO planoContaDAO;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;
	
	@Autowired
	private IContabilContaDAO contabilContaDAO;
	
	@Autowired
	private IPessoaEnderecoDAO pessoaEnderecoDAO;
	
	@Autowired
	private IPessoaContatoDAO pessoaContatoDAO;

	/**
	 * CONSTRUTOR
	 */

	public PessoaFormController() {
		// TODO Auto-generated constructor stub
	}

	public PessoaBusiness<PessoaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
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
	public PessoaEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PessoaEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			//fieldGroup.bind(this.subView.getCbTipoPessoa(),"tipo");
			fieldGroup.bind(this.subView.getTfEmail(),"email");
			fieldGroup.bind(this.subView.getTfSite(),"site");
			
			//fieldGroup.bind(this.subView.getMocEstadoCivil(), "pessoaFisica.estadoCivil");
			
			this.subView.getMocEstadoCivil().configuraCombo(
					"nome", EstadoCivilListController.class, this.estadoCivilDAO, this.getMainController());
			
			System.out.println(":: load cliente");
			this.subView.getMocClienteSituacao().configuraCombo(
					"nome", SituacaoForCliListController.class, this.situacaoForCliDAO, this.getMainController());
			this.subView.getMocClienteAtividade().configuraCombo(
					"nome", AtividadeForCliListController.class, this.atividadeForCliDAO, this.getMainController());
			this.subView.getMocClienteOperacaoFiscal().configuraCombo(
					"descricao", OperacaoFiscalListController.class, this.operacaoFiscalDAO, this.getMainController());
			
			System.out.println(":: load colaborador");
			this.subView.getMocColaboradorTipoColaborador().configuraCombo(
					"nome", TipoColaboradorListController.class, this.tipoColaboradorDAO, this.getMainController());
			this.subView.getMocColaboradorSituacaoColaborador().configuraCombo(
					"nome", SituacaoColaboradorListController.class, this.situacaoColaboradorDAO, this.getMainController());
			this.subView.getMocColaboradorSindicato().configuraCombo(
					"nome", SindicatoListController.class, this.sindicatoDAO, this.getMainController());
			this.subView.getMocColaboradorNivelFormacao().configuraCombo(
					"nome", NivelFormacaoListController.class, this.nivelFormacaoDAO, this.getMainController());
			this.subView.getMocColaboradorCargo().configuraCombo(
					"nome", CargoListController.class, this.cargoDAO, this.getMainController());
			this.subView.getMocColaboradorContaContabil().configuraCombo(
					"classificacao", ContabilContaListController.class, this.contabilContaDAO, this.getMainController());
			this.subView.getMocColaboradorSetor().configuraCombo(
					"nome", SetorListController.class, this.setorDAO, this.getMainController());
			this.subView.getMocColaboradorContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			this.subView.getMocColaboradorPlanoConta().configuraCombo(
					"nome", PlanoContaListController.class, this.planoContaDAO, this.getMainController());

			/*DefaultManyToOneComboModel<EstadoCivilEntity> modelEstadoCivil = new DefaultManyToOneComboModel<EstadoCivilEntity>(
					EstadoCivilListController.class, super.getMainController(),
					null, this.estadoCivilBusiness);
			this.subView.getMocEstadoCivil().setModel(modelEstadoCivil);//
			DefaultManyToOneComboModel<SituacaoForCliEntity> modelClienteSituacaoForCli = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
					SituacaoColaboradorListController.class,
					this.situacaoForCliDAO, super.getMainController());
			this.subView.getMocClienteSituacao().setModel(
					modelClienteSituacaoForCli);
			DefaultManyToOneComboModel<AtividadeForCliEntity> modelClienteAtividadeForCli = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
					AtividadeForCliListController.class,
					this.atividadeForCliDAO, super.getMainController());
			this.subView.getMocClienteAtividade().setModel(
					modelClienteAtividadeForCli);
			DefaultManyToOneComboModel<OperacaoFiscalEntity> modelClienteOperacaoFiscal = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(
					OperacaoFiscalListController.class, this.operacaoFiscalDAO,
					super.getMainController());
			this.subView.getMocClienteOperacaoFiscal().setModel(
					modelClienteOperacaoFiscal);
			DefaultManyToOneComboModel<TipoColaboradorEntity> modelColaboradorTipoColaborador = new DefaultManyToOneComboModel<TipoColaboradorEntity>(
					TipoColaboradorListController.class,
					this.tipoColaboradorDAO, super.getMainController());
			this.subView.getMocColaboradorTipoColaborador().setModel(
					modelColaboradorTipoColaborador);
			DefaultManyToOneComboModel<SituacaoColaboradorEntity> modelColaboradorSituacaoColaborador = new DefaultManyToOneComboModel<SituacaoColaboradorEntity>(
					SituacaoColaboradorListController.class,
					this.situacaoColaboradorDAO, super.getMainController());
			this.subView.getMocColaboradorSituacaoColaborador().setModel(
					modelColaboradorSituacaoColaborador);
			DefaultManyToOneComboModel<SindicatoEntity> modelColaboradorSindicato = new DefaultManyToOneComboModel<SindicatoEntity>(
					SindicatoListController.class, this.sindicatoDAO,
					super.getMainController());
			this.subView.getMocColaboradorSindicato().setModel(
					modelColaboradorSindicato);
			DefaultManyToOneComboModel<NivelFormacaoEntity> modelColaboradorNivelFormacao = new DefaultManyToOneComboModel<NivelFormacaoEntity>(
					NivelFormacaoListController.class, this.nivelFormacaoDAO,
					super.getMainController());
			this.subView.getMocColaboradorNivelFormacao().setModel(
					modelColaboradorNivelFormacao);
			DefaultManyToOneComboModel<CargoEntity> modelColaboradorCargo = new DefaultManyToOneComboModel<CargoEntity>(
					CargoListController.class, this.cargoDAO,
					super.getMainController());
			this.subView.getMocColaboradorCargo().setModel(
					modelColaboradorCargo);
			DefaultManyToOneComboModel<SetorEntity> modelSetor = new DefaultManyToOneComboModel<SetorEntity>(
					SetorListController.class, this.setorDAO,
					super.getMainController());
			this.subView.getMocColaboradorSetor().setModel(modelSetor);*/

			/*
			 * DefaultManyToOneComboModel<ContabilContaEntity>
			 * modelContabilConta3 = new
			 * DefaultManyToOneComboModel<ContabilContaEntity>(
			 * ContabilContaListController.class, this.contabilContaDAO,
			 * super.getMainController());
			 * 
			 * this.subView.getMocColaboradorContaContabil().setModel(
			 * modelContabilConta3);
			 * 
			 * DefaultManyToOneComboModel<PlanoConta> modelPlanoConta = new
			 * DefaultManyToOneComboModel<PlanoConta>(
			 * PlanoContaListController.class, this.planoContaDAO,
			 * super.getMainController());
			 * 
			 * this.subView.getMocColaboradorPlanoConta().setModel(
			 * modelPlanoConta);
			 * 
			 * DefaultManyToOneComboModel<ContaCaixa> modelContaCaixa = new
			 * DefaultManyToOneComboModel<ContaCaixa>(
			 * ContaCaixaListController.class, this.contaCaixaDAO,
			 * super.getMainController());
			 * 
			 * this.subView.getMocColaboradorContaCaixa().setModel(
			 * modelContaCaixa);
			 */

			System.out.println(":: load fornecedor");
			this.subView.getMocFornecedorSituacaoForCli().configuraCombo(
					"nome", SituacaoForCliListController.class, this.situacaoForCliDAO, this.getMainController());
			this.subView.getMocFornecedorAtividadeForCli().configuraCombo(
					"nome", AtividadeForCliListController.class, this.atividadeForCliDAO, this.getMainController());
			this.subView.getMocFornecedorContabilConta().configuraCombo(
					"classificacao", ContabilContaListController.class, this.contabilContaDAO, this.getMainController());

			/*DefaultManyToOneComboModel<SituacaoForCliEntity> modelFornecedorSituacaoForCli = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
					SituacaoColaboradorListController.class,
					this.situacaoForCliDAO, super.getMainController());
			this.subView.getMocFornecedorSituacaoForCli().setModel(
					modelFornecedorSituacaoForCli);
			DefaultManyToOneComboModel<AtividadeForCliEntity> modelFornecedorAtividadeForCli = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
					AtividadeForCliListController.class,
					this.atividadeForCliDAO, super.getMainController());
			this.subView.getMocFornecedorAtividadeForCli().setModel(
					modelFornecedorAtividadeForCli);*/

			System.out.println(":: load transportadora");
			this.subView.getMocTransportadoraContabilConta().configuraCombo(
					"classificacao", ContabilContaListController.class, this.contabilContaDAO, this.getMainController());

			//

			carregarTipoPessoa();
			carregarTipoRegime();
			carregarCnh();
			carregarRaca();
			carregarCategoriaReservista();
			carregarCrt();
			carregarTipoSanguineo();
			carregarSexo();

			carregarGerarFinanceiro();
			carregarIndicadorPreco();
			carregarTipoFrete();
			carregarFormaDesconto();

			carregarDescontoPlanoSaude();
			carregarFormaPagamento();
			carregarFgtsOptante();
			carregarSaiNaRais();
			carregarPriorizarComissao();
			carregarComissaoOver();
			carregarTipoComissaoProduto();
			carregarTipoComissaoServico();
			carregarVendedorAtendente();
			carregarLancamentoComissao();

			carregarSofreRetencao();
			carregarGerarFaturamento();
			carregarOptanteSimples();
			carregarLocalizacao();

			// Valores iniciais

			this.subView.getCbTipoPessoa().setValue(TipoPessoaEn.F);
			this.subView.getOgSexo().setValue(SexoEn.F);

			this.subView.getCbColaboradorPriorizarComissao().setValue(
					SimNaoEn.N);
			this.subView.getCbColaboradorComissaoOver().setValue(SimNaoEn.N);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			//PessoaUtils.validateRequiredFields(this.subView);
			// PessoaUtils.validateFieldValue(this.subView);
			
			fieldGroup.commit();

			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) this.subView
					.getCbTipoPessoa().getValue();

			this.entity.setTipo(tipoPessoaEn);

			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.entity.getTipo().equals(TipoPessoaEn.F)) {
				savePessoaFisica();
			} else if (this.entity.getTipo().equals(TipoPessoaEn.J)) {
				savePessoaJuridica();
			}

			this.entity.setTipoCliente(this.subView.getCkCliente().getValue()
					.equals(Boolean.TRUE) ? "1" : "0");

			if (this.entity.getTipoCliente().equals("1")) {
				saveCliente();
			}

			this.entity.setTipoColaborador(this.subView.getCkColaborador()
					.getValue());

			if (this.entity.getTipoColaborador()) {
				saveColaborador();
			}

			this.entity.setTipoFornecedor(this.subView.getCkFornecedor()
					.getValue());

			if (this.entity.getTipoFornecedor()) {
				saveFornecedor();
			}

			this.entity.setTipoTransportadora(this.subView
					.getCkTransportadora().getValue());

			if (this.entity.getTipoTransportadora()) {
				saveTransportadora();
			}

			// PessoaContato
			//List<PessoaContatoEntity> auxLista1 = this.subView.getSfPessoaContato().getDados();
			//this.entity.setPessoaContatoList(auxLista1);

			// PessoaEndereco
			//List<PessoaEnderecoEntity> auxLista2 = this.subView.getSfPessoaEndereco().getDados();
			//this.entity.setPessoaEnderecoList(auxLista2);

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	private void savePessoaFisica() throws Exception {
		PessoaFisicaEntity ent = (this.entity.getPessoaFisica() == null ? new PessoaFisicaEntity()
				: this.entity.getPessoaFisica());
		ent.setPessoa(this.entity);

		ent.setCpf(this.subView.getMtfCpf().getValue());
		ent.setDataNascimento(this.subView.getPdfDataNascimento().getValue());
		ent.setNaturalidade(this.subView.getTfNaturalidade().getValue());
		ent.setNacionalidade(this.subView.getTfNacionalidade().getValue());
		ent.setNomeMae(this.subView.getTfNomeMae().getValue());
		ent.setNomePai(this.subView.getTfNomePai().getValue());
		ent.setRg(this.subView.getTfNumeroRg().getValue());
		ent.setOrgaoRg(this.subView.getTfOrgaoEmissor().getValue());
		ent.setDataEmissaoRg(this.subView.getPdfDataEmissaoRg().getValue());
		ent.setCnhNumero(this.subView.getTfCnh().getValue());

		CnhEn cnhEn = (CnhEn) this.subView.getCbCategoriaCnh().getValue();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			ent.setCnh(cnhEn);
		}

		ent.setCnhVencimento(this.subView.getPdfDataCnhEmissao().getValue());

		EstadoCivilEntity estadoCivil = this.subView.getMocEstadoCivil()
				.getValue();

		if (ObjectUtils.isNotBlank(estadoCivil)) {
			ent.setEstadoCivil(estadoCivil);
		}

		RacaEn racaEn = (RacaEn) this.subView.getCbRaca().getValue();

		if (ObjectUtils.isNotBlank(racaEn)) {
			ent.setRaca(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = (TipoSanguineoEn) this.subView
				.getCbTipoSanguineo().getValue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			ent.setTipoSangue(tipoSanguineoEn);
		}

		ent.setReservistaNumero(this.subView.getTfNumeroReservista().getValue());

		CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
				.getCbCategoriaReservista().getValue();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			ent.setReservistaCategoria(categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.subView.getOgSexo().getValue();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			ent.setSexo(sexoEn);
		}

		ent.setTituloEleitoralNumero(this.subView.getTfTituloEleitor()
				.getValue());
		ent.setTituloEleitoralSecao(NumberUtils.toInt(this.subView
				.getTfTituloSecao().getValue()));
		ent.setTituloEleitoralZona(NumberUtils.toInt(this.subView
				.getTfTituloZona().getValue()));

		this.entity.setPessoaFisica(ent);
	}

	private void savePessoaJuridica() throws Exception {
		PessoaJuridicaEntity ent = (this.entity.getPessoaJuridica() == null ? new PessoaJuridicaEntity()
				: this.entity.getPessoaJuridica());
		ent.setPessoa(this.entity);

		ent.setFantasia(this.subView.getTfFantasia().getValue());
		ent.setCnpj(this.subView.getMtfCnpj().getValue());
		ent.setInscricaoEstadual(this.subView.getTfInscricaoEstadual()
				.getValue());
		ent.setInscricaoMunicipal(this.subView.getTfInscricaoMunicipal()
				.getValue());
		ent.setDataConstituicao(this.subView.getPdfDataConstituicao()
				.getValue());
		ent.setSuframa(this.subView.getTfSuframa().getValue());

		TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
				.getCbTipoRegime().getValue();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			ent.setTipoRegime(tipoRegimeEn);
		}

		CrtEn crtEn = (CrtEn) this.subView.getCbCrt().getValue();

		if (ObjectUtils.isNotBlank(crtEn)) {
			ent.setCrt(crtEn);
		}

		this.entity.setPessoaJuridica(ent);
	}

	private void saveCliente() throws Exception {
		ClienteEntity ent = (this.entity.getCliente() == null ? new ClienteEntity()
				: this.entity.getCliente());
		ent.setPessoa(this.entity);

		ent.setObservacao(this.subView.getTaClienteObservacao().getValue());

		String limiteCredito = this.subView.getTfClienteLimiteCredito()
				.getValue();

		if (NumberUtils.isNumber(limiteCredito)) {
			ent.setLimiteCredito(NumberUtils.createBigDecimal(limiteCredito));
		} else {
			ent.setLimiteCredito(new BigDecimal(0));
		}

		String porcentoDesconto = this.subView.getTfClienteTaxaDesconto()
				.getValue();

		if (NumberUtils.isNumber(porcentoDesconto)) {
			ent.setPorcentoDesconto(NumberUtils
					.createBigDecimal(porcentoDesconto));
		} else {
			ent.setPorcentoDesconto(new BigDecimal(0));
		}

		FormaDescontoEn formaDesconto = (FormaDescontoEn) this.subView
				.getCbClienteFormaDesconto().getValue();

		ent.setFormaDesconto(formaDesconto);

		TipoFreteEn tipoFrete = (TipoFreteEn) this.subView
				.getCbClienteTipoFrete().getValue();

		ent.setTipoFrete(tipoFrete);

		IndicadorPrecoEn indicadorPreco = (IndicadorPrecoEn) this.subView
				.getCbClienteIndicadorPreco().getValue();

		ent.setIndicadorPreco(indicadorPreco);

		SimNaoEn gerarFinanceiro = (SimNaoEn) this.subView
				.getCbClienteGerarFinanceiro().getValue();

		ent.setGerarFinanceiro(gerarFinanceiro);

		ent.setContaTomador(this.subView.getTfClienteContaTomador().getValue());
		ent.setDesde(this.subView.getPdfClienteDesde().getValue());
		ent.setSituacaoForCli(this.subView.getMocClienteSituacao().getValue());
		ent.setAtividadeForCli(this.subView.getMocClienteAtividade().getValue());
		ent.setOperacaoFiscal(this.subView.getMocClienteOperacaoFiscal()
				.getValue());

		this.entity.setCliente(ent);
	}

	private void saveColaborador() throws Exception {
		ColaboradorEntity ent = (this.entity.getColaborador() == null ? new ColaboradorEntity()
				: this.entity.getColaborador());
		ent.setPessoa(this.entity);

		ent.setCtpsDataExpedicao(this.subView
				.getPdfColaboradorCtpsDataExpedicao().getValue());
		ent.setCtpsSerie(this.subView.getTfColaboradorCtpsSerie().getValue());
		ent.setCtpsNumero(this.subView.getTfColaboradorCtpsNumero().getValue());
		ent.setPisAgenciaDigito(this.subView.getTfColaboradorPisAgenciaDigito()
				.getValue());
		ent.setPisAgencia(this.subView.getTfColaboradorPisAgencia().getValue());
		ent.setPisBanco(this.subView.getTfColaboradorPisBanco().getValue());
		ent.setPisNumero(this.subView.getTfColaboradorPisNumero().getValue());
		ent.setPisDataCadastro(this.subView.getPdfColaboradorPisDataCadastro()
				.getValue());
		ent.setPagamentoContaDigito(this.subView
				.getTfColaboradorPagamentoContaDigito().getValue());
		ent.setPagamentoConta(this.subView.getTfColaboradorPagamentoConta()
				.getValue());
		ent.setPagamentoAgenciaDigito(this.subView
				.getTfColaboradorPagamentoAgenciaDigito().getValue());
		ent.setPagamentoAgencia(this.subView.getTfColaboradorPagamentoAgencia()
				.getValue());
		ent.setPagamentoBanco(this.subView.getTfColaboradorPagamentoBanco()
				.getValue());

		FormaPagamentoEn formaPagamentoEn = (FormaPagamentoEn) this.subView
				.getCbColaboradorFormaPagamento().getValue();

		if (ObjectUtils.isNotBlank(formaPagamentoEn)) {
			ent.setPagamentoForma(formaPagamentoEn);
		}

		String codigoDemissaoCaged = this.subView
				.getTfColaboradorCodigoDemissaoCaged().getValue();

		if (NumberUtils.isNumber(codigoDemissaoCaged)) {
			ent.setCodigoDemissaoCaged(NumberUtils.toInt(codigoDemissaoCaged));
		}

		String codigoAdmissao = this.subView
				.getTfColaboradorCodigoAdmissaoCaged().getValue();

		if (NumberUtils.isNumber(codigoAdmissao)) {
			ent.setCodigoAdmissaoCaged(NumberUtils.toInt(codigoAdmissao));
		}

		ent.setFgtsDataOpcao(this.subView.getPdfColaboradorFgtsDataOpcao()
				.getValue());

		SimNaoEn optanteEn = (SimNaoEn) this.subView
				.getCbColaboradorFgtsOptante().getValue();

		if (ObjectUtils.isNotBlank(optanteEn)) {
			ent.setFgtsOptante(optanteEn);
		}

		String codigoDemissaoSefip = this.subView
				.getTfColaboradorCodigoDemissaoSefip().getValue();

		if (NumberUtils.isNumber(codigoDemissaoSefip)) {
			ent.setCodigoDemissaoSefip(NumberUtils.toInt(codigoDemissaoSefip));
		}

		String ocorrenciaSefip = this.subView.getTfColaboradorOcorrenciaSefip()
				.getValue();

		if (NumberUtils.isNumber(ocorrenciaSefip)) {
			ent.setOcorrenciaSefip(NumberUtils.toInt(ocorrenciaSefip));
		}

		ent.setCategoriaSefip(this.subView.getTfColaboradorCategoriaSefip()
				.getValue());

		String salarioFixo = this.subView.getTfColaboradorSalarioFixo()
				.getValue();

		if (NumberUtils.isNumber(salarioFixo)) {
			ent.setSalarioFixo(NumberUtils.createBigDecimal(salarioFixo));
		}

		String comissaoProduto = this.subView.getTfColaboradorComissaoProduto()
				.getValue();

		if (NumberUtils.isNumber(comissaoProduto)) {
			ent.setValorComissaoProduto(NumberUtils
					.createBigDecimal(comissaoProduto));
		}

		String comissaoServico = this.subView.getTfColaboradorComissaoServico()
				.getValue();

		if (NumberUtils.isNumber(comissaoServico)) {
			ent.setValorComissaoServico(NumberUtils
					.createBigDecimal(comissaoServico));
		}

		ent.setExameMedicoVencimento(this.subView
				.getPdfColaboradorExameMedicoVencimento().getValue());
		ent.setExameMedicoUltimo(this.subView
				.getPdfColaboradorExameMedicoUltimo().getValue());
		ent.setCodigoTurmaPonto(this.subView.getTfColaboradorCodigoTurmaPonto()
				.getValue());

		SimNaoEn saiRaisEn = (SimNaoEn) this.subView
				.getCbColaboradorSaiNaRais().getValue();

		if (ObjectUtils.isNotBlank(saiRaisEn)) {
			ent.setSaiNaRais(saiRaisEn);
		}

		SimNaoEn descontoPlanoSaudeEn = (SimNaoEn) this.subView
				.getCbColaboradorDescontoPlanoSaude().getValue();

		if (ObjectUtils.isNotBlank(descontoPlanoSaudeEn)) {
			ent.setDescontoPlanoSaude(descontoPlanoSaudeEn);
		}

		ent.setDataDemissao(this.subView.getPdfColaboradorDataDemissao()
				.getValue());
		ent.setDataTransferencia(this.subView
				.getPdfColaboradorDataTransferencia().getValue());
		ent.setVencimentoFerias(this.subView
				.getPdfColaboradorDataVencimentoFerias().getValue());
		ent.setDataAdmissao(this.subView.getPdfColaboradorDataAdmissao()
				.getValue());
		ent.setDataCadastro(this.subView.getPdfColaboradorDataCadastro()
				.getValue());
		ent.setMatricula(this.subView.getTfColaboradorMatricula().getValue());

		SimNaoEn priorizarComissaoEn = (SimNaoEn) this.subView
				.getCbColaboradorPriorizarComissao().getValue();

		ent.setPriorizarComissao(priorizarComissaoEn == SimNaoEn.N ? Boolean.FALSE
				: Boolean.TRUE);

		SimNaoEn comissaoOverEn = (SimNaoEn) this.subView
				.getCbColaboradorComissaoOver().getValue();

		ent.setComissaoOver(comissaoOverEn == SimNaoEn.N ? Boolean.FALSE
				: Boolean.TRUE);

		ent.setTipoComissaoServico((String) this.subView
				.getOgColaboradorTipoComissaoServico().getValue());
		ent.setTipoComissaoProduto((String) this.subView
				.getOgColaboradorTipoComissaoProduto().getValue());

		ent.setObservacao(this.subView.getTaColaboradorObservacao().getValue());

		TipoColaboradorEntity tipoColaborador = this.subView
				.getMocColaboradorTipoColaborador().getValue();

		if (ObjectUtils.isNotBlank(tipoColaborador)) {
			ent.setTipoColaborador(tipoColaborador);
		} else {
			ent.setTipoColaborador(null);
		}

		SituacaoColaboradorEntity situacaoColaborador = this.subView
				.getMocColaboradorSituacaoColaborador().getValue();

		if (ObjectUtils.isNotBlank(situacaoColaborador)) {
			ent.setSituacaoColaborador(situacaoColaborador);
		} else {
			ent.setSituacaoColaborador(null);
		}

		SindicatoEntity sindicato = this.subView.getMocColaboradorSindicato()
				.getValue();

		if (ObjectUtils.isNotBlank(sindicato)) {
			ent.setSindicato(sindicato);
		} else {
			ent.setSindicato(null);
		}

		NivelFormacaoEntity nivelFormacao = this.subView
				.getMocColaboradorNivelFormacao().getValue();

		if (ObjectUtils.isNotBlank(nivelFormacao)) {
			ent.setNivelFormacao(nivelFormacao);
		} else {
			ent.setNivelFormacao(null);
		}

		CargoEntity cargo = this.subView.getMocColaboradorCargo().getValue();

		if (ObjectUtils.isNotBlank(cargo)) {
			ent.setCargo(cargo);
		} else {
			ent.setCargo(null);
		}

		SetorEntity setor = this.subView.getMocColaboradorSetor().getValue();

		if (ObjectUtils.isNotBlank(setor)) {
			ent.setSetor(setor);
		} else {
			ent.setSetor(null);
		}

		PlanoContaEntity planoConta = this.subView.getMocColaboradorPlanoConta()
				.getValue();

		if (ObjectUtils.isNotBlank(planoConta)) {
			ent.setPlanoConta(planoConta);
		} else {
			ent.setPlanoConta(null);
		}

		ContaCaixa contaCaixa = this.subView.getMocColaboradorContaCaixa()
				.getValue();

		if (ObjectUtils.isNotBlank(contaCaixa)) {
			ent.setContaCaixa(contaCaixa);
		} else {
			ent.setContaCaixa(null);
		}

		this.entity.setColaborador(ent);
	}

	private void saveFornecedor() throws Exception {
		FornecedorEntity ent = (this.entity.getFornecedor() == null ? new FornecedorEntity()
				: this.entity.getFornecedor());
		ent.setPessoa(this.entity);

		ent.setSituacaoForCli(this.subView.getMocFornecedorSituacaoForCli()
				.getValue());
		ent.setAtividadeForCli(this.subView.getMocFornecedorAtividadeForCli()
				.getValue());

		ent.setDesde(this.subView.getPdfFornecedorDesde().getValue());
		ent.setContaRemetente(this.subView.getTfFornecedorContaRemetente()
				.getValue());

		SimNaoEn geraFaturamento = (SimNaoEn) this.subView
				.getCbFornecedorGeraFaturamento().getValue();

		ent.setGeraFaturamento(geraFaturamento);

		SimNaoEn optanteSimplesNacional = (SimNaoEn) this.subView
				.getCbFornecedorOptanteSimples().getValue();

		ent.setOptanteSimplesNacional(optanteSimplesNacional);

		LocalizacaoEn localizacao = (LocalizacaoEn) this.subView
				.getCbFornecedorLocalizacao().getValue();

		ent.setLocalizacao(localizacao);

		SimNaoEn sofreRetencao = (SimNaoEn) this.subView
				.getCbFornecedorSofreRetencao().getValue();

		ent.setSofreRetencao(sofreRetencao);

		String prazoMediaEntrega = this.subView
				.getTfFornecedorPrazoMedioEntrega().getValue();

		if (NumberUtils.isNumber(prazoMediaEntrega)) {
			ent.setPrazoMedioEntrega(NumberUtils
					.toInt(prazoMediaEntrega));
		} else {
			ent.setPrazoMedioEntrega(0);
		}

		String numDiasPrimeiroVencimento = this.subView
				.getTfFornecedorNumDiasPrimeiroVenc().getValue();

		if (NumberUtils.isNumber(numDiasPrimeiroVencimento)) {
			ent.setNumDiasPrimeiroVencimento(NumberUtils
					.toInt(numDiasPrimeiroVencimento));
		} else {
			ent.setNumDiasPrimeiroVencimento(0);
		}

		String numDiasIntervalo = this.subView
				.getTfFornecedorNumDiasIntervalo().getValue();

		if (NumberUtils.isNumber(numDiasIntervalo)) {
			ent.setNumDiasIntervalo(NumberUtils.toInt(numDiasIntervalo));
		} else {
			ent.setNumDiasIntervalo(0);
		}

		String quantidadeParcelas = this.subView
				.getTfFornecedorQuantidadeParcelas().getValue();

		if (NumberUtils.isNumber(quantidadeParcelas)) {
			ent.setQuantidadeParcelas(NumberUtils.toInt(quantidadeParcelas));
		} else {
			ent.setQuantidadeParcelas(0);
		}

		ent.setChequeNominalA(this.subView.getTfFornecedorChequeNominalA()
				.getValue());
		ent.setObservacao(this.subView.getTaFornecedorObservacao().getValue());

		this.entity.setFornecedor(ent);
	}

	private void saveTransportadora() throws Exception {
		TransportadoraEntity ent = (this.entity.getTransportadora() == null ? new TransportadoraEntity()
				: this.entity.getTransportadora());
		ent.setPessoa(this.entity);

		ent.setObservacao(this.subView.getTaTransportadoraObservacao()
				.getValue());

		this.entity.setTransportadora(ent);
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.entity);

			// PessoaContato
			//List<PessoaContatoEntity> auxLista1 = this.pessoaContatoBusiness.list(this.entity);
			//this.entity.setPessoaContatoList(auxLista1);
			List<PessoaContatoEntity> item = pessoaContatoDAO.findByPessoaContato(entity);
			subView.preencheSubFormContato(item);

			// PessoaEndereco
			//List<PessoaEnderecoEntity> auxLista2 = this.pessoaEnderecoBusiness.list(this.entity);
			//this.entity.setPessoaEnderecoList(auxLista2);			
			List<PessoaEnderecoEntity> itens = pessoaEnderecoDAO.findByPessoaEndereco(entity);
			subView.preencheSubFormEndereco(itens);

			this.subView.getTfNome().setValue(this.entity.getNome());

			this.subView.getCbTipoPessoa()
					.setValue(this.entity.getTipo());

			this.subView.getTfEmail().setValue(this.entity.getEmail());
			this.subView.getTfSite().setValue(this.entity.getSite());

			if (this.entity.getTipo().equals(TipoPessoaEn.F)) {
				this.entity.setPessoaFisica(loadPessoaFisica());
			} else if (this.entity.getTipo().equals(TipoPessoaEn.J)) {
				this.entity.setPessoaJuridica(loadPessoaJuridica());
			}

			this.subView.getCkCliente().setValue(
					this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
							: Boolean.TRUE);

			if (this.entity.getTipoCliente().equals("1")) {
				loadCliente();
			}

			this.subView.getCkColaborador().setValue(
					this.entity.getTipoColaborador());

			if (this.entity.getTipoColaborador()) {
				loadColaborador();
			}

			this.subView.getCkFornecedor().setValue(
					this.entity.getTipoFornecedor());

			if (this.entity.getTipoFornecedor()) {
				loadFornecedor();
			}

			this.subView.getCkTransportadora().setValue(
					this.entity.getTipoTransportadora());

			if (this.entity.getTipoTransportadora()) {
				loadTransportadora();
			}

			// PessoaContato

			this.subView.getSfPessoaContato().fillWith(
					this.entity.getPessoaContatoList());

			// PessoaEndereco

			for (PessoaEnderecoEntity ent : this.entity.getPessoaEnderecoList()) {
				if (StringUtils.isNotBlank(ent.getSiglaUf())) {
					UfEntity uf = this.ufBusiness.find(ent.getIdUf());

					ent.setUf(uf);
				}
			}

			this.subView.getSfPessoaEndereco().fillWith(
					this.entity.getPessoaEnderecoList());

			visibleTabSheet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaFisicaEntity loadPessoaFisica() {
		PessoaFisicaEntity pf = this.entity.getPessoaFisica();

		this.subView.getMtfCpf().setValue(pf.getCpf());
		this.subView.getPdfDataNascimento().setValue(pf.getDataNascimento());
		this.subView.getTfNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTfNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTfNomeMae().setValue(pf.getNomeMae());
		this.subView.getTfNomePai().setValue(pf.getNomePai());
		this.subView.getTfNumeroRg().setValue(pf.getRg());
		this.subView.getTfOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getPdfDataEmissaoRg().setValue(pf.getDataEmissaoRg());
		this.subView.getTfCnh().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			this.subView.getCbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getPdfDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getMocEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTfNumeroReservista().setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.entity.getPessoaFisica().getSexo();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			this.subView.getOgSexo().setValue(sexoEn);
		}

		this.subView.getTfTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTfTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTfTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	private PessoaJuridicaEntity loadPessoaJuridica() {
		PessoaJuridicaEntity pj = this.entity.getPessoaJuridica();

		this.subView.getTfFantasia().setValue(pj.getFantasia());
		this.subView.getMtfCnpj().setValue(pj.getCnpj());
		this.subView.getTfInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTfInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getPdfDataConstituicao()
				.setValue(pj.getDataConstituicao());
		this.subView.getTfSuframa().setValue(pj.getSuframa());

		TipoRegimeEn tipoRegimeEn = pj.getTipoRegime();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			this.subView.getCbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (ObjectUtils.isNotBlank(crtEn)) {
			this.subView.getCbCrt().setValue(crtEn);
		}

		return pj;
	}

	private void loadCliente() throws Exception {
		ClienteEntity ent = this.entity.getCliente();

		if (ObjectUtils.isBlank(ent)) {
			return;
		}

		this.subView.getTaClienteObservacao().setValue(ent.getObservacao());
		this.subView.getTfClienteLimiteCredito().setValue(
				ent.getLimiteCredito().toString());
		this.subView.getTfClienteTaxaDesconto().setValue(
				ent.getPorcentoDesconto().toString());
		this.subView.getCbClienteFormaDesconto().setValue(
				ent.getFormaDesconto());
		this.subView.getCbClienteTipoFrete().setValue(ent.getTipoFrete());
		this.subView.getCbClienteIndicadorPreco().setValue(
				ent.getIndicadorPreco());
		this.subView.getCbClienteGerarFinanceiro().setValue(
				ent.getGerarFinanceiro());
		this.subView.getTfClienteContaTomador().setValue(ent.getContaTomador());
		this.subView.getPdfClienteDesde().setValue(ent.getDesde());

		SituacaoForCliEntity situacaoForCli = ent.getSituacaoForCli();

		if (ObjectUtils.isNotBlank(situacaoForCli)) {
			this.subView.getMocClienteSituacao().setValue(situacaoForCli);
		}

		AtividadeForCliEntity atividadeForCli = ent.getAtividadeForCli();

		if (ObjectUtils.isNotBlank(atividadeForCli)) {
			this.subView.getMocClienteAtividade().setValue(atividadeForCli);
		}

		OperacaoFiscalEntity operacaoFiscal = ent.getOperacaoFiscal();

		if (ObjectUtils.isNotBlank(operacaoFiscal)) {
			this.subView.getMocClienteOperacaoFiscal().setValue(operacaoFiscal);
		}
	}

	private void loadColaborador() throws Exception {
		ColaboradorEntity ent = this.entity.getColaborador();

		if (ObjectUtils.isBlank(ent)) {
			return;
		}

		this.subView.getPdfColaboradorCtpsDataExpedicao().setValue(
				ent.getCtpsDataExpedicao());
		this.subView.getTfColaboradorCtpsSerie().setValue(ent.getCtpsSerie());
		this.subView.getTfColaboradorCtpsNumero().setValue(ent.getCtpsNumero());
		this.subView.getTfColaboradorPisAgenciaDigito().setValue(
				ent.getPisNumero());
		this.subView.getTfColaboradorPisAgencia().setValue(ent.getPisAgencia());
		this.subView.getTfColaboradorPisBanco().setValue(ent.getPisBanco());
		this.subView.getTfColaboradorPisNumero().setValue(ent.getPisNumero());
		this.subView.getPdfColaboradorPisDataCadastro().setValue(
				ent.getPisDataCadastro());
		this.subView.getTfColaboradorPagamentoContaDigito().setValue(
				ent.getPagamentoContaDigito());
		this.subView.getTfColaboradorPagamentoConta().setValue(
				ent.getPagamentoConta());
		this.subView.getTfColaboradorPagamentoAgenciaDigito().setValue(
				ent.getPagamentoAgenciaDigito());
		this.subView.getTfColaboradorPagamentoAgencia().setValue(
				ent.getPagamentoAgencia());
		this.subView.getTfColaboradorPagamentoBanco().setValue(
				ent.getPagamentoBanco());

		FormaPagamentoEn pagamentoFormaEn = ent.getPagamentoForma();

		if (ObjectUtils.isNotBlank(pagamentoFormaEn)) {
			this.subView.getCbColaboradorFormaPagamento().setValue(
					ent.getPagamentoForma());
		}

		Integer codigoDemissaoCaged = ent.getCodigoDemissaoCaged();

		if (NumberUtils.isNotBlank(codigoDemissaoCaged)) {
			this.subView.getTfColaboradorCodigoDemissaoCaged().setValue(
					ent.getCodigoDemissaoCaged().toString());
		}

		Integer codigoAdmissaoCaged = ent.getCodigoAdmissaoCaged();

		if (NumberUtils.isNotBlank(codigoAdmissaoCaged)) {
			this.subView.getTfColaboradorCodigoAdmissaoCaged().setValue(
					ent.getCodigoAdmissaoCaged().toString());
		}

		this.subView.getPdfColaboradorFgtsDataOpcao().setValue(
				ent.getFgtsDataOpcao());

		SimNaoEn fgtsOptanteEn = ent.getFgtsOptante();

		if (ObjectUtils.isNotBlank(fgtsOptanteEn)) {
			this.subView.getCbColaboradorFgtsOptante().setValue(
					ent.getFgtsOptante());
		}

		Integer codigoDemissaoSefip = ent.getCodigoDemissaoSefip();

		if (NumberUtils.isNotBlank(codigoDemissaoSefip)) {
			this.subView.getTfColaboradorCodigoDemissaoSefip().setValue(
					ent.getCodigoDemissaoSefip().toString());
		}

		Integer ocorrenciaSefip = ent.getOcorrenciaSefip();

		if (NumberUtils.isNotBlank(ocorrenciaSefip)) {
			this.subView.getTfColaboradorOcorrenciaSefip().setValue(
					ent.getOcorrenciaSefip().toString());
		}

		this.subView.getTfColaboradorCategoriaSefip().setValue(
				ent.getCategoriaSefip());

		BigDecimal salarioFixo = ent.getSalarioFixo();

		this.subView.getTfColaboradorSalarioFixo().setValue(
				salarioFixo.toString());

		BigDecimal comissaoProduto = ent.getValorComissaoProduto();

		this.subView.getTfColaboradorComissaoProduto().setValue(
				comissaoProduto.toString());

		BigDecimal comissaoServico = ent.getValorComissaoServico();

		this.subView.getTfColaboradorComissaoServico().setValue(
				comissaoServico.toString());

		this.subView.getPdfColaboradorExameMedicoVencimento().setValue(
				ent.getExameMedicoVencimento());
		this.subView.getPdfColaboradorExameMedicoUltimo().setValue(
				ent.getExameMedicoUltimo());
		this.subView.getTfColaboradorCodigoTurmaPonto().setValue(
				ent.getCodigoTurmaPonto());

		SimNaoEn saiNaRaisEn = ent.getSaiNaRais();

		if (ObjectUtils.isNotBlank(saiNaRaisEn)) {
			this.subView.getCbColaboradorSaiNaRais().setValue(
					ent.getSaiNaRais());
		}

		SimNaoEn descontoPlanoSaudeEn = ent.getDescontoPlanoSaude();

		if (ObjectUtils.isNotBlank(descontoPlanoSaudeEn)) {
			this.subView.getCbColaboradorDescontoPlanoSaude().setValue(
					ent.getDescontoPlanoSaude());
		}

		this.subView.getPdfColaboradorDataDemissao().setValue(
				ent.getDataDemissao());
		this.subView.getPdfColaboradorDataTransferencia().setValue(
				ent.getDataTransferencia());
		this.subView.getPdfColaboradorDataVencimentoFerias().setValue(
				ent.getVencimentoFerias());
		this.subView.getPdfColaboradorDataAdmissao().setValue(
				ent.getDataAdmissao());
		this.subView.getPdfColaboradorDataCadastro().setValue(
				ent.getDataCadastro());
		this.subView.getTfColaboradorMatricula().setValue(ent.getMatricula());

		SimNaoEn priorizarComissao = (ent.getPriorizarComissao() == Boolean.FALSE ? SimNaoEn.N
				: SimNaoEn.S);

		this.subView.getCbColaboradorPriorizarComissao().setValue(
				priorizarComissao);

		SimNaoEn comissaoOver = (ent.getComissaoOver() == Boolean.FALSE ? SimNaoEn.N
				: SimNaoEn.S);

		this.subView.getCbColaboradorComissaoOver().setValue(comissaoOver);

		this.subView.getCbColaboradorPgtoComissao().setValue(
				ent.getPgtoComissaoSera());
		this.subView.getCbColaboradorLctoComissao().setValue(
				ent.getLctoComissao());
		this.subView.getOgColaboradorTipoComissaoServico().setValue(
				ent.getTipoComissaoServico());
		this.subView.getOgColaboradorTipoComissaoProduto().setValue(
				ent.getTipoComissaoProduto());
		this.subView.getTaColaboradorObservacao().setValue(ent.getObservacao());

		TipoColaboradorEntity tipoColaborador = ent.getTipoColaborador();

		if (ObjectUtils.isNotBlank(tipoColaborador)) {
			this.subView.getMocColaboradorTipoColaborador().setValue(
					tipoColaborador);
		}

		SituacaoColaboradorEntity situacaoColaborador = ent
				.getSituacaoColaborador();

		if (ObjectUtils.isNotBlank(situacaoColaborador)) {
			this.subView.getMocColaboradorSituacaoColaborador().setValue(
					situacaoColaborador);
		}

		SindicatoEntity sindicato = ent.getSindicato();

		if (ObjectUtils.isNotBlank(sindicato)) {
			this.subView.getMocColaboradorSindicato().setValue(sindicato);
		}

		NivelFormacaoEntity nivelFormacao = ent.getNivelFormacao();

		if (ObjectUtils.isNotBlank(nivelFormacao)) {
			this.subView.getMocColaboradorNivelFormacao().setValue(
					nivelFormacao);
		}

		CargoEntity cargo = ent.getCargo();

		if (ObjectUtils.isNotBlank(cargo)) {
			this.subView.getMocColaboradorCargo().setValue(cargo);
		}

		SetorEntity setor = ent.getSetor();

		if (ObjectUtils.isNotBlank(setor)) {
			this.subView.getMocColaboradorSetor().setValue(setor);
		}

		/*
		 * ContabilContaEntity contabilConta = ent.getContaContabil();
		 * 
		 * if (ObjectUtils.isNotBlank(contabilConta)) {
		 * this.subView.getMocColaboradorContaContabil().setValue(
		 * contabilConta); }
		 * 
		 * PlanoConta planoConta = ent.getPlanoConta();
		 * 
		 * if (ObjectUtils.isNotBlank(planoConta)) {
		 * this.subView.getMocColaboradorPlanoConta().setValue(planoConta); }
		 * 
		 * ContaCaixa contaCaixa = ent.getContaCaixa();
		 * 
		 * if (ObjectUtils.isNotBlank(contaCaixa)) {
		 * this.subView.getMocColaboradorContaCaixa().setValue(contaCaixa); }
		 */
	}

	private void loadFornecedor() throws Exception {
		FornecedorEntity ent = this.entity.getFornecedor();

		if (ObjectUtils.isBlank(ent)) {
			return;
		}

		SituacaoForCliEntity situacaoForCli = ent.getSituacaoForCli();

		if (ObjectUtils.isNotBlank(situacaoForCli)) {
			this.subView.getMocFornecedorSituacaoForCli().setValue(
					situacaoForCli);
		}

		AtividadeForCliEntity atividadeForCli = ent.getAtividadeForCli();

		if (ObjectUtils.isNotBlank(atividadeForCli)) {
			this.subView.getMocFornecedorAtividadeForCli().setValue(
					atividadeForCli);
		}

		this.subView.getPdfFornecedorDesde().setValue(ent.getDesde());
		this.subView.getTfFornecedorContaRemetente().setValue(
				ent.getContaRemetente());
		this.subView.getCbFornecedorGeraFaturamento().setValue(
				ent.getGeraFaturamento());
		this.subView.getCbFornecedorOptanteSimples().setValue(
				ent.getOptanteSimplesNacional());
		this.subView.getCbFornecedorLocalizacao()
				.setValue(ent.getLocalizacao());
		this.subView.getCbFornecedorSofreRetencao().setValue(
				ent.getSofreRetencao());
		this.subView.getTfFornecedorPrazoMedioEntrega().setValue(
				ent.getPrazoMedioEntrega().toString());
		this.subView.getTfFornecedorNumDiasPrimeiroVenc().setValue(
				ent.getNumDiasPrimeiroVencimento().toString());
		this.subView.getTfFornecedorNumDiasIntervalo().setValue(
				ent.getNumDiasIntervalo().toString());
		this.subView.getTfFornecedorQuantidadeParcelas().setValue(
				ent.getQuantidadeParcelas().toString());
		this.subView.getTfFornecedorChequeNominalA().setValue(
				ent.getChequeNominalA());
		this.subView.getTaFornecedorObservacao().setValue(ent.getObservacao());
	}

	private void loadTransportadora() throws Exception {
		TransportadoraEntity ent = this.entity.getTransportadora();

		if (ObjectUtils.isBlank(ent)) {
			return;
		}

		this.subView.getTaTransportadoraObservacao().setValue(
				ent.getObservacao());

	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PessoaEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visibleTabSheet();
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
	protected void removerEmCascata(List<Serializable> objetos) {
		for (Serializable id : objetos) {
			PessoaEntity pessoaEntity = (PessoaEntity) id;

			try {
				business.delete(pessoaEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	/**
	 * 
	 */

	/*public PessoaContatoEntity adicionarPessoaContato() {
		try {
			PessoaContatoEntity ent = new PessoaContatoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaContatoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaContato(List<PessoaContatoEntity> values) {
		try {
			for (PessoaContatoEntity ent : values) {
				this.pessoaContatoBusiness.delete(ent);
				this.entity.getPessoaContatoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}*/

	/*public PessoaEnderecoEntity adicionarPessoaEndereco() {
		try {
			PessoaEnderecoEntity ent = new PessoaEnderecoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaEnderecoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}*/
	
	public PessoaContatoEntity adicionarPessoaContato() {
		PessoaContatoEntity item = new PessoaContatoEntity();
		entity.addPessoaContato(item);
		return item;
	}
	
	public void removerPessoaContato(List<PessoaContatoEntity> pessoaContato) {
		for (PessoaContatoEntity pessoaCon : pessoaContato) {
			entity.removePessoaContato(pessoaCon);
		}
		mensagemRemovidoOK();
	}
	
	public PessoaEnderecoEntity adicionarPessoaEndereco() {
		PessoaEnderecoEntity item = new PessoaEnderecoEntity();
		entity.addPessoaEndereco(item);
		return item;
	}
	
	public void removerPessoaEndereco(List<PessoaEnderecoEntity> pessoaEndereco) {
		for (PessoaEnderecoEntity pessoaEn : pessoaEndereco) {
			entity.removePessoaEndereco(pessoaEn);
		}
		mensagemRemovidoOK();
	}

	/*public void removerPessoaEndereco(List<PessoaEnderecoEntity> values) {
		try {
			for (PessoaEnderecoEntity ent : values) {
				this.pessoaEnderecoBusiness.delete(ent);
				this.entity.getPessoaEnderecoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}*/

	/**
	 * COMBOS
	 */

	public void carregarTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCbTipoRegime().addItem(en);
		}
	}

	public void carregarCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCbCategoriaCnh().addItem(en);
		}
	}

	public void carregarRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCbRaca().addItem(en);
		}
	}

	public void carregarCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCbCategoriaReservista().addItem(en);
		}
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCbCrt().addItem(en);
		}
	}

	public void carregarTipoSanguineo() {
		for (TipoSanguineoEn en : TipoSanguineoEn.values()) {
			this.subView.getCbTipoSanguineo().addItem(en);
		}
	}

	public void carregarTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCbTipoPessoa().addItem(en);
		}
	}

	public void carregarSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getOgSexo().addItem(en);
		}
	}

	//

	public void carregarGerarFinanceiro() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbClienteGerarFinanceiro().addItem(en);
		}
	}

	public void carregarIndicadorPreco() {
		for (IndicadorPrecoEn en : IndicadorPrecoEn.values()) {
			this.subView.getCbClienteIndicadorPreco().addItem(en);
		}
	}

	public void carregarTipoFrete() {
		for (TipoFreteEn en : TipoFreteEn.values()) {
			this.subView.getCbClienteTipoFrete().addItem(en);
		}
	}

	public void carregarFormaDesconto() {
		for (FormaDescontoEn en : FormaDescontoEn.values()) {
			this.subView.getCbClienteFormaDesconto().addItem(en);
		}
	}

	//

	public void carregarDescontoPlanoSaude() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbColaboradorDescontoPlanoSaude().addItem(en);
		}
	}

	public void carregarSaiNaRais() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbColaboradorSaiNaRais().addItem(en);
		}
	}

	public void carregarFgtsOptante() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbColaboradorFgtsOptante().addItem(en);
		}
	}

	public void carregarFormaPagamento() {
		for (FormaPagamentoEn en : FormaPagamentoEn.values()) {
			this.subView.getCbColaboradorFormaPagamento().addItem(en);
		}
	}

	public void carregarPriorizarComissao() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbColaboradorPriorizarComissao().addItem(en);
		}
	}

	public void carregarComissaoOver() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbColaboradorComissaoOver().addItem(en);
		}
	}

	public void carregarTipoComissaoProduto() {
		for (TipoComissaoProdutoEn en : TipoComissaoProdutoEn.values()) {
			this.subView.getOgColaboradorTipoComissaoProduto().addItem(en);
		}
	}

	public void carregarTipoComissaoServico() {
		for (TipoComissaoServicoEn en : TipoComissaoServicoEn.values()) {
			this.subView.getOgColaboradorTipoComissaoServico().addItem(en);
		}
	}
	
	public void carregarVendedorAtendente() {
		for (VendedorAtendente en : VendedorAtendente.values()) {
			this.subView.getCbColaboradorPgtoComissao().addItem(en);
		}
	}
	
	public void carregarLancamentoComissao() {
		for (LancamentoComissao en : LancamentoComissao.values()) {
			this.subView.getCbColaboradorLctoComissao().addItem(en);
		}
	}

	//

	public void carregarSofreRetencao() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorSofreRetencao().addItem(value);
		}
	}

	public void carregarGerarFaturamento() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorGeraFaturamento().addItem(value);
		}
	}

	public void carregarOptanteSimples() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorOptanteSimples().addItem(value);
		}
	}

	public void carregarLocalizacao() {
		for (LocalizacaoEn value : LocalizacaoEn.values()) {
			this.subView.getCbFornecedorLocalizacao().addItem(value);
		}
	}

	/**
	 * 
	 */

	public void visibleTabSheet() {
		this.subView
				.getTsGeral()
				.getTab(4)
				.setVisible(
						this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
								: Boolean.TRUE);

		this.subView.getTsGeral().getTab(5)
				.setVisible(this.entity.getTipoColaborador());
		this.subView.getTsGeral().getTab(6)
				.setVisible(this.entity.getTipoFornecedor());
		this.subView.getTsGeral().getTab(7)
				.setVisible(this.entity.getTipoTransportadora());
	}

	/**
	 * 
	 */

	public BeanItemContainer<UfEntity> getUfBic() {
		try {
			List<UfEntity> auxLista = this.ufBusiness.findAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			return bic;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 */

	public void vceVisibleTabSheet(ValueChangeEvent event, Integer indexTab) {
		Object obj = event.getProperty().getValue();

		if (obj instanceof TipoPessoaEn) {
			TipoPessoaEn en = (TipoPessoaEn) obj;

			this.subView
					.getTsGeral()
					.getTab(0)
					.setVisible(
							en.equals(TipoPessoaEn.F) ? Boolean.TRUE
									: Boolean.FALSE);
			this.subView
					.getTsGeral()
					.getTab(1)
					.setVisible(
							en.equals(TipoPessoaEn.J) ? Boolean.TRUE
									: Boolean.FALSE);

			this.subView.getTsGeral().setSelectedTab(
					en.equals(TipoPessoaEn.F) ? 0 : 1);
		} else if (obj instanceof Boolean) {
			Boolean b = (Boolean) obj;

			this.subView.getTsGeral().getTab(indexTab).setVisible(b);

			// this.subView.getCkColaborador().setEnabled(false);
			// this.subView.getTsGeral().getTab(5).setEnabled(false);
		} else {
			System.out.println(":: [instanceof] no type for " + obj.toString());
		}
	}
	
	public List<PessoaEnderecoEntity> getPessoaEndereco() {
		return pessoaEnderecoDAO.getAll();
	}

}
