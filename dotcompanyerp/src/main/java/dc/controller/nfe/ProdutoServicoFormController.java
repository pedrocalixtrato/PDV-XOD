package dc.controller.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.produto.ProdutoListController;
import dc.controller.nfe.dto.NfeDetalheImpIpiDTO;
import dc.controller.nfe.dto.NfeDetalheImpostoCofinsDTO;
import dc.controller.nfe.dto.NfeDetalheImpostoIcmsDTO;
import dc.controller.nfe.dto.NfeDetalheImpostoIiDTO;
import dc.controller.nfe.dto.NfeDetalheImpostoIssqnDTO;
import dc.controller.nfe.dto.NfeDetalheImpostoPisDTO;
import dc.controller.nfe.dto.ProdutoServicoViewDTO;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpIpiEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.business.nfe.NfeCabecalhoBusiness;
import dc.servicos.dao.nfe.INfeDeclaracaoImportacaoDAO;
import dc.servicos.dao.nfe.INfeDestinatarioDAO;
import dc.servicos.dao.nfe.INfeDetEspecificoArmamentoDAO;
import dc.servicos.dao.nfe.INfeDetEspecificoMedicamentoDAO;
import dc.servicos.dao.nfe.INfeDetalheDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoCofinsDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoIcmsDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoIiDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoIpiDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoIssqnDAO;
import dc.servicos.dao.nfe.INfeDetalheImpostoPisDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo.ItemValue;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.ProdutoServicoFormView;

@Controller
@Scope("prototype")
public class ProdutoServicoFormController extends
		CRUDFormController<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormView subView;

	/** DAO'S */

	@Autowired
	private NfeCabecalhoBusiness<NfeCabecalhoEntity> nfeCabecalhoBusiness;

	// @Autowired
	// private NfeCabecalhoDAO nfeCabecalhoDAO;

	@Autowired
	private INfeDestinatarioDAO nfeDestinatarioDAO;

	@Autowired
	private INfeDetalheDAO nfeDetalheDAO;

	@Autowired
	private INfeDeclaracaoImportacaoDAO nfeDeclaracaoImportacaoDAO;

	@Autowired
	private INfeDetalheImpostoCofinsDAO nfeDetalheImpostoCofinsDAO;

	@Autowired
	private INfeDetalheImpostoIcmsDAO nfeDetalheImpostoIcmsDAO;

	@Autowired
	private INfeDetalheImpostoIiDAO nfeDetalheImpostoIiDAO;

	@Autowired
	private INfeDetalheImpostoIpiDAO nfeDetalheImpostoIpiDAO;

	@Autowired
	private INfeDetalheImpostoIssqnDAO nfeDetalheImpostoIssqnDAO;

	@Autowired
	private INfeDetalheImpostoPisDAO nfeDetalheImpostoPisDAO;

	@Autowired
	private INfeDetEspecificoMedicamentoDAO ndeMedicamentoDAO;

	@Autowired
	private INfeDetEspecificoArmamentoDAO ndeArmamentoDAO;

	/** ENTITIES */

	private NfeCabecalhoEntity nfeCabecalho;

	// private NfeDetalheEntity nfeDetalheSelecionado;

	private NfeDetEspecificoMedicamentoEntity ndeMedicamentoSelecionado;

	private NfeDetEspecificoArmamentoEntity ndeArmamentoSelecionado;

	/** CONSTRUTOR */

	public ProdutoServicoFormController() {
		if (this.nfeCabecalho == null) {
			this.nfeCabecalho = new NfeCabecalhoEntity();
		}

		// if (this.nfeDetalheSelecionado == null) {
		// this.nfeDetalheSelecionado = new NfeDetalheEntity();
		// }

		if (this.ndeMedicamentoSelecionado == null) {
			this.ndeMedicamentoSelecionado = new NfeDetEspecificoMedicamentoEntity();
		}

		if (this.ndeArmamentoSelecionado == null) {
			this.ndeArmamentoSelecionado = new NfeDetEspecificoArmamentoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Produto / serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			/*
			 * this.nfeCabecalhoDAO.saveOrUpdate(this.nfeCabecalho);
			 * 
			 * List<NfeDetalheEntity> auxLista = this.subView.getSfNfeDetalhe()
			 * .getDados();
			 * 
			 * if (auxLista != null && !auxLista.isEmpty()) { for
			 * (NfeDetalheEntity ent : auxLista) { // NfeDetalheEntity ent =
			 * (NfeDetalheEntity) obj; ent.setNfeCabecalho(this.nfeCabecalho);
			 * 
			 * this.nfeDetalheDAO.saveOrUpdate(ent);
			 * 
			 * for (NfeDetEspecificoMedicamentoEntity ent1 : ent
			 * .getNdeMedicamentoList()) {
			 * this.ndeMedicamentoDAO.saveOrUpdate(ent1); } } }
			 */

			// this.nfeCabecalho.saveOrUpdateNfeCabecalho(this.nfeCabecalho);
			this.nfeCabecalhoBusiness.saveOrUpdate(this.nfeCabecalho);

			notifiyFrameworkSaveOK(this.nfeCabecalho);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ProdutoServicoFormView(this);

		// popularCombo();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public void criarNovo() {
		try {
			super.criarNovo();

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			// this.nfeCabecalhoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		String msg = "";

		if (this.nfeCabecalho == null) {
			msg = ":: Cabeçalho está nulo.";

			// adicionarErroDeValidacao(null, msg);

			mensagemErro(msg);

			return false;
		}

		if (this.nfeCabecalho.getNfeDetalheList() == null) {
			msg = ":: Lista de detalhes está nula.";

			// adicionarErroDeValidacao(this.subView.getSfNfeDetalhe(), msg);

			mensagemErro(msg);

			return false;
		}

		if (this.nfeCabecalho.getNfeDetalheList().isEmpty()) {
			msg = ":: Lista de detalhes está vazia.";

			// adicionarErroDeValidacao(this.subView.getSfNfeDetalhe(), msg);

			mensagemErro(msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {
			// this.pDAO.deleteAllByIds(ids);
			// this.nfeCabecalhoDAO.listarTodos(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return "";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.nfeCabecalho = new NfeCabecalhoEntity();

				// this.nfeCabecalho
				// .setNfeDestinatario(new NfeDestinatarioEntity());

				/*
				 * nfeDetalheLimpar(); ndiCofinsLimpar(); ndiIcmsLimpar();
				 * ndiIiLimpar(); ndiIpiLimpar(); ndiIssqnLimpar();
				 * ndiPisLimpar(); ndeCombustivelLimpar(); ndeVeiculoLimpar();
				 * ndeMedicamentoLimpar();
				 */

				ProdutoServicoViewDTO.subViewClean(this.subView);
			} else {
				// this.nfeCabecalho = this.nfeCabecalhoDAO.find(id);
				this.nfeCabecalho = this.nfeCabecalhoBusiness.find(id);
			}

			// this.nfeCabecalho.setNfeDetalhe(new NfeDetalheEntity());

			nfeCabecalhoCarregar();
			nfeDestinatarioCarregar();
			nfeDetalheCarregar();

			popularCombo();

			ProdutoServicoViewDTO.tabEnabled(this.nfeCabecalho.getNfeDetalhe(),
					this.subView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	// CARREGAR

	private void nfeCabecalhoCarregar() throws Exception {
		ProdutoServicoViewDTO.setNfeCabecalhoSubView(this.nfeCabecalho,
				this.subView);
	}

	private void nfeDestinatarioCarregar() throws Exception {
		if (this.nfeCabecalho.getId() != null
				&& !this.nfeCabecalho.getId().equals(0)) {
			this.nfeCabecalho.setNfeDestinatario(this.nfeDestinatarioDAO
					.getEntidade(this.nfeCabecalho));
		} else {
			this.nfeCabecalho.setNfeDestinatario(new NfeDestinatarioEntity());
		}

		ProdutoServicoViewDTO.setNfeDestinatarioSubView(this.nfeCabecalho,
				this.subView);
	}

	private void nfeDetalheCarregar() throws Exception {
		if (this.nfeCabecalho.getId() != null
				&& !this.nfeCabecalho.getId().equals(0)) {
			this.nfeCabecalho.setNfeDetalheList(this.nfeDetalheDAO
					.getLista(this.nfeCabecalho));

			efetuaCalculosDetalhe(false);
		} else {
			this.nfeCabecalho
					.setNfeDetalheList(new ArrayList<NfeDetalheEntity>());
		}

		ProdutoServicoViewDTO.setNfeDetalheListSubView(this.nfeCabecalho,
				this.subView);
	}

	private void ndeMedicamentoCarregar() throws Exception {
		List<NfeDetEspecificoMedicamentoEntity> auxLista = this.nfeCabecalho
				.getNfeDetalhe().getNdeMedicamentoList();

		if (auxLista == null || auxLista.isEmpty()) {
			auxLista = this.ndeMedicamentoDAO.getLista(this.nfeCabecalho
					.getNfeDetalhe());
		}

		this.nfeCabecalho.getNfeDetalhe().setNdeMedicamentoList(auxLista);

		// this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
		// .getNdeMedicamentoList());
	}

	private void ndeArmamentoCarregar() throws Exception {
		List<NfeDetEspecificoArmamentoEntity> auxLista = this.nfeCabecalho
				.getNfeDetalhe().getNdeArmamentoList();

		if (auxLista == null || auxLista.isEmpty()) {
			auxLista = this.ndeArmamentoDAO.getLista(this.nfeCabecalho
					.getNfeDetalhe());
		}

		this.nfeCabecalho.getNfeDetalhe().setNdeArmamentoList(auxLista);

		// this.subView.carregarSfNdeArmamento(this.nfeCabecalho.getNfeDetalhe()
		// .getNdeArmamentoList());
	}

	/** NFEDETALHE - ADICIONAR */

	public NfeDetalheEntity nfeDetalheAdicionar() {
		try {
			NfeDetalheEntity ent = new NfeDetalheEntity();
			ent.setNfeCabecalho(this.nfeCabecalho);

			/** COFINS */

			NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();
			ndiCofins.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoCofins(ndiCofins);

			/** ICMS */

			NfeDetalheImpostoIcmsEntity ndiIcms = new NfeDetalheImpostoIcmsEntity();
			ndiIcms.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIcms(ndiIcms);

			/** IMPOSTO IMPORTAÇÃO */

			NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();
			ndiIi.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIi(ndiIi);

			/** IPI */

			NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();
			ndiIpi.setNfeDetalhe(ent);

			ent.setNfeDetalheImpIpi(ndiIpi);

			/** ISSQN */

			NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();
			ndiIssqn.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIssqn(ndiIssqn);

			/** PIS */

			NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();
			ndiPis.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoPis(ndiPis);

			/** COMBUSTÍVEL */

			NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();
			ndeCombustivel.setNfeDetalhe(ent);

			ent.setNfeDetEspecificoCombustivel(ndeCombustivel);

			/** VEÍCULO */

			NfeDetEspecificoVeiculoEntity ndeVeiculo = new NfeDetEspecificoVeiculoEntity();
			ndeVeiculo.setNfeDetalhe(ent);

			ent.setNfeDetEspecificoVeiculo(ndeVeiculo);

			/** MEDICAMENTO */

			ent.setNdeMedicamentoList(new ArrayList<NfeDetEspecificoMedicamentoEntity>());

			/** ARMAMENTO */

			ent.setNdeArmamentoList(new ArrayList<NfeDetEspecificoArmamentoEntity>());

			/**
			 * 
			 */

			this.nfeCabecalho.getNfeDetalheList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/** NFEDETESPECIFICOMEDICAMENTO - ADICIONAR */

	public NfeDetEspecificoMedicamentoEntity ndeMedicamentoAdicionar() {
		try {
			NfeDetEspecificoMedicamentoEntity ent = new NfeDetEspecificoMedicamentoEntity();
			ent.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

			// this.nfeDetalheSelecionado.getNdeMedicamentoList().add(ent);
			this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/** NFEDETESPECIFICOARMAMENTO - ADICIONAR */

	public NfeDetEspecificoArmamentoEntity ndeArmamentoAdicionar() {
		try {
			NfeDetEspecificoArmamentoEntity ent = new NfeDetEspecificoArmamentoEntity();
			ent.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

			// this.nfeDetalheSelecionado.getNdeMedicamentoList().add(ent);
			this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/** NFEDETALHE - SELECIONAR */

	public void nfeDetalheSelecionar(NfeDetalheEntity item) {
		try {
			this.nfeCabecalho.setNfeDetalhe(item);

			/** NFEDETALHE */

			ProdutoServicoViewDTO.setNfeDetalheSubView(item, this.subView);

			/** COFINS */

			if (item.getNfeDetalheImpostoCofins() == null) {
				item.setNfeDetalheImpostoCofins(new NfeDetalheImpostoCofinsEntity());
			}

			ProdutoServicoViewDTO.setNdiCofinsSubView(item, this.subView);

			/** ICMS */

			if (item.getNfeDetalheImpostoIcms() == null) {
				item.setNfeDetalheImpostoIcms(new NfeDetalheImpostoIcmsEntity());
			}

			ProdutoServicoViewDTO.setNdiIcmsSubView(item, this.subView);

			/** IMPOSTO IMPORTAÇÃO */

			if (item.getNfeDetalheImpostoIi() == null) {
				item.setNfeDetalheImpostoIi(new NfeDetalheImpostoIiEntity());
			}

			ProdutoServicoViewDTO.setNdiIiSubView(item, this.subView);

			/** IPI */

			if (item.getNfeDetalheImpIpi() == null) {
				item.setNfeDetalheImpIpi(new NfeDetalheImpIpiEntity());
			}

			ProdutoServicoViewDTO.setNdiIpiSubView(item, this.subView);

			/** ISSQN */

			if (item.getNfeDetalheImpostoIssqn() == null) {
				item.setNfeDetalheImpostoIssqn(new NfeDetalheImpostoIssqnEntity());
			}

			ProdutoServicoViewDTO.setNdiIssqnSubView(item, this.subView);

			/** PIS */

			if (item.getNfeDetalheImpostoPis() == null) {
				item.setNfeDetalheImpostoPis(new NfeDetalheImpostoPisEntity());
			}

			ProdutoServicoViewDTO.setNdiPisSubView(item, this.subView);

			/** COMBUSTÍVEL */

			if (item.getNfeDetEspecificoCombustivel() == null) {
				item.setNfeDetEspecificoCombustivel(new NfeDetEspecificoCombustivelEntity());
			}

			ProdutoServicoViewDTO.setNdeCombustivelSubView(item, this.subView);

			/** VEÍCULO */

			if (item.getNfeDetEspecificoVeiculo() == null) {
				item.setNfeDetEspecificoVeiculo(new NfeDetEspecificoVeiculoEntity());
			}

			ProdutoServicoViewDTO.setNdeVeiculoSubView(item, this.subView);

			/** MEDICAMENTO */

			// ndeMedicamentoCarregar();

			/** ARMAMENTO */

			// ndeArmamentoCarregar();

			ProdutoServicoViewDTO.tabEnabled(this.nfeCabecalho.getNfeDetalhe(),
					this.subView);

			/*
			 * efetuaCalculosDetalhe(false);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** NFEDETESPECIFICOMEDICAMENTO - SELECIONAR */

	public void ndeMedicamentoSelecionar(NfeDetEspecificoMedicamentoEntity item) {
		try {
			/*
			 * this.subView.getGlNdeMedicamento().setEnabled(true);
			 * 
			 * this.nfeCabecalho.getNfeDetalhe().setNdeMedicamento(item);
			 * 
			 * this.subView.getTfNumeroLoteMedicamento().setValue(
			 * item.getNumeroLote());
			 * this.subView.getTfQuantidadeLoteMedicamento().setValue(
			 * item.getQuantidadeLote().toString());
			 * this.subView.getPdfDataFabricacaoMedicamento().setValue(
			 * item.getDataFabricacao());
			 * this.subView.getPdfDataValidadeMedicamento().setValue(
			 * item.getDataValidade());
			 * this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
			 * item.getPrecoMaximoConsumidor().toString());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** NFEDETESPECIFICOARMAMENTO - SELECIONAR */

	public void ndeArmamentoSelecionar(NfeDetEspecificoArmamentoEntity item) {
		try {
			/*
			 * this.subView.getGlNdeArmamento().setEnabled(true);
			 * 
			 * this.nfeCabecalho.getNfeDetalhe().setNdeArmamento(item);
			 * 
			 * this.subView.getTfTipoArmaArmamento().setValue(item.getTipoArma())
			 * ; this.subView.getTfNumeroSerieArmaArmamento().setValue(
			 * item.getNumeroSerieArma());
			 * this.subView.getTfNumeroSerieCanoArmamento().setValue(
			 * item.getNumeroSerieCano());
			 * this.subView.getTfDescricaoArmamento()
			 * .setValue(item.getDescricao());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * SETAR VALORES
	 * 
	 * @param id
	 * @param event
	 */

	public void nfeCabecalhoSetarValor(String id, Object obj) {
		// TODO nfeCabecalhoSetarValor

		if (this.nfeCabecalho == null) {
			return;
		}

		switch (id) {
		// case "mtoOperacaoFiscal":
		// this.nfeCabecalho.set

		// break;
		case "mtoOperacaoFiscal":
			ItemValue m = (ItemValue) obj;
			OperacaoFiscalEntity operacaoFiscal = (OperacaoFiscalEntity) m.getBean();

			this.nfeCabecalho.setTributOperacaoFiscal(operacaoFiscal);
			// this.nfeCabecalho
			// .setTipoOperacao(operacaoFiscal.getDescricaoNaNF());

			break;
		case "tfVenda":
			// this.nfeCabecalho.set

			break;
		case "tfModeloNotaFiscal":
			this.nfeCabecalho.setCodigoModelo((String) obj);

			break;
		case "tfNaturezaOperacao":
			this.nfeCabecalho.setNaturezaOperacao((String) obj);

			break;
		case "tfChaveAcesso":
			this.nfeCabecalho.setChaveAcesso((String) obj);

			break;
		case "tfDigitoChaveAcesso":
			//this.nfeCabecalho.setDigitoChaveAcesso((String) obj);

			break;
		case "tfCodigoNumerico":
			//this.nfeCabecalho.setCodigoNumerico((String) obj);

			break;
		case "tfSerie":
			//this.nfeCabecalho.setSerie((String) obj);

			break;
		case "tfNumero":
			//this.nfeCabecalho.setNumero((String) obj);

			break;
		case "pdfDataEmissao":
			this.nfeCabecalho.setDataHoraEmissao((Date) obj);

			break;
		case "pdfDataEntradaSaida":
			this.nfeCabecalho.setDataHoraEntradaSaida((Date) obj);

			break;
		case "tfTipoOperacao":
			//this.nfeCabecalho.setTipoOperacao((String) obj);

			break;
		case "tfTipoEmissao":
			//this.nfeCabecalho.setTipoEmissao((String) obj);

			break;
		case "tfFinalidadeEmissao":
			//this.nfeCabecalho.setFinalidadeEmissao((String) obj);

			break;
		case "tfFormatoImpressaoDanfe":
			//this.nfeCabecalho.setFormatoImpressaoDanfe((String) obj);

			break;
		case "tfFormaPagamento":
			//this.nfeCabecalho.setIndicadorFormaPagamento((String) obj);

			break;
		}
	}

	public void nfeDestinatarioSetarValor(String id, Object obj) {
		// TODO nfeDestinatarioSetarValor

		NfeDestinatarioEntity nfeDestinatario = this.nfeCabecalho
				.getNfeDestinatario();

		if (nfeDestinatario == null) {
			return;
		}

		switch (id) {
		case "tfEmailDestinatario":
			nfeDestinatario.setEmail((String) obj);

			break;
		case "tfSuframaDestinatario":
			//nfeDestinatario.setSuframa((String) obj);

			break;
		case "tfTelefoneDestinatario":
			nfeDestinatario.setTelefone((String) obj);

			break;
		case "tfInscricaoEstadualDestinatario":
			nfeDestinatario.setInscricaoEstadual((String) obj);

			break;
		case "tfUfDestinatario":
			nfeDestinatario.setUf((String) obj);

			break;
		case "tfCidadeDestinatario":
			// nfeDestinatario.set

			break;
		case "tfCodigoIbgeDestinatario":
			// nfeDestinatario.set

			break;
		case "tfBairroLogradouroDestinatario":
			nfeDestinatario.setBairro((String) obj);

			break;
		case "tfLogradouroComplementoDestinatario":
			nfeDestinatario.setComplemento((String) obj);

			break;
		case "tfLogradouroNumeroDestinatario":
			nfeDestinatario.setNumero((String) obj);

			break;
		case "tfLogradouroDestinatario":
			nfeDestinatario.setLogradouro((String) obj);

			break;
		case "tfCepDestinatario":
			nfeDestinatario.setCep((String) obj);

			break;
		case "tfNomeDestinatario":
			nfeDestinatario.setNome((String) obj);

			break;
		case "tfCpfCnpjDestinatario":
			nfeDestinatario.setCpfCnpj((String) obj);

			break;
		case "mtoCliente":
			ItemValue m = (ItemValue) obj;
			ClienteEntity cliente = (ClienteEntity) m.getBean();

			this.nfeCabecalho.setCliente(cliente);

			break;
		}
	}

	public void nfeDetalheSetarValor(String id, Object obj) {
		// TODO nfeDetalheSetarValor

		boolean b = this.subView.getGlNfeDetalhe().isEnabled();

		if (!b) {
			return;
		}

		switch (id) {
		case "tfNumeroItem":
			this.nfeCabecalho.getNfeDetalhe().setNumeroItem(
					ObjectConverter.stringToInteger((String) obj));

			break;
		// case "tfCodigoProduto":
		// this.nfeDetalheSelecionado.setCodigoProduto(s.toString().trim());

		// break;
		case "tfGtin":
			this.nfeCabecalho.getNfeDetalhe().setGtin((String) obj);

			break;
		case "mtoProduto":
			ItemValue m = (ItemValue) obj;
			ProdutoEntity produto = (ProdutoEntity) m.getBean();

			this.nfeCabecalho.getNfeDetalhe().setProduto(produto);
			this.nfeCabecalho.getNfeDetalhe().setCodigoProduto(
					produto.getCodigoInterno());

			break;
		case "tfNcm":
			this.nfeCabecalho.getNfeDetalhe().setNcm((String) obj);

			break;
		case "tfExTipi":
			this.nfeCabecalho.getNfeDetalhe().setExTipi(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfCfop":
			this.nfeCabecalho.getNfeDetalhe().setCfop(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfUnidadeComercial":
			this.nfeCabecalho.getNfeDetalhe().setUnidadeComercial((String) obj);

			break;
		case "tfQuantidadeComercial":
			this.nfeCabecalho.getNfeDetalhe().setQuantidadeComercial(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorUnitarioComercial":
			this.nfeCabecalho.getNfeDetalhe().setValorUnitarioComercial(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorBrutoProduto":
			this.nfeCabecalho.getNfeDetalhe().setValorBrutoProduto(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfGtinUnidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe().setGtinUnidadeTributavel(
					(String) obj);

			break;
		case "tfUnidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe()
					.setUnidadeTributavel((String) obj);

			break;
		case "tfQuantidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe().setQuantidadeTributavel(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorUnitarioTributavel":
			this.nfeCabecalho.getNfeDetalhe().setValorUnitarioTributavel(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorFrete":
			this.nfeCabecalho.getNfeDetalhe().setValorFrete(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorSeguro":
			this.nfeCabecalho.getNfeDetalhe().setValorSeguro(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorDesconto":
			this.nfeCabecalho.getNfeDetalhe().setValorDesconto(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorOutrasDespesas":
			this.nfeCabecalho.getNfeDetalhe().setValorOutrasDespesas(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfEntraTotal":
			//this.nfeCabecalho.getNfeDetalhe().setEntraTotal((String) obj);

			break;
		case "tfValorSubtotal":
			this.nfeCabecalho.getNfeDetalhe().setValorSubtotal(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorTotal":
			this.nfeCabecalho.getNfeDetalhe().setValorTotal(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfNumeroPedidoCompra":
			this.nfeCabecalho.getNfeDetalhe().setNumeroPedidoCompra(
					(String) obj);

			break;
		case "tfItemPedidoCompra":
			this.nfeCabecalho.getNfeDetalhe().setItemPedidoCompra(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfInformacoesAdicionais":
			this.nfeCabecalho.getNfeDetalhe().setInformacoesAdicionais(
					(String) obj);

			break;
		}

		efetuaCalculosDetalhe(false);
	}

	/**
	 * 
	 * 
	 */

	public void ndiCofinsSetarValor(String id, Object obj) {
		// TODO ndiCofinsSetarValor

		NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetalheImpostoCofins();
		NfeDetalheImpostoCofinsDTO.setarValor(ndiCofins, id, obj);

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(ndiCofins);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIcmsSetarValor(String id, Object obj) {
		// TODO ndiIcmsSetarValor

		NfeDetalheImpostoIcmsEntity ndiIcms = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoIcms();
		NfeDetalheImpostoIcmsDTO.setarValor(ndiIcms, id, obj);

		// Integer index = this.subView.getSfNfeDetalhe().getDados()
		// .indexOf(this.nfeCabecalho.getNfeDetalhe());

		// this.subView.getSfNfeDetalhe().getDados()
		// .remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIcms(ndiIcms);

		// this.subView.getSfNfeDetalhe().getDados()
		// .add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIiSetarValor(String id, Object obj) {
		// TODO ndiIiSetarValor

		NfeDetalheImpostoIiEntity ndiIi = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoIi();
		ndiIi = NfeDetalheImpostoIiDTO.setarValor(ndiIi, id, obj);

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIi(ndiIi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIpiSetarValor(String id, Object obj) {
		// TODO ndiIpiSetarValor

		// NfeDetalheImpostoIpiEntity ndiIpi = new NfeDetalheImpostoIpiEntity();

		NfeDetalheImpIpiEntity ndiIpi = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpIpi();
		NfeDetalheImpIpiDTO.setarValor(ndiIpi, id, obj);

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpIpi(ndiIpi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIssqnSetarValor(String id, Object obj) {
		// TODO ndiIssqnSetarValor

		NfeDetalheImpostoIssqnEntity ndiIssqn = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetalheImpostoIssqn();
		ndiIssqn = NfeDetalheImpostoIssqnDTO.setarValor(ndiIssqn, id, obj);

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIssqn(ndiIssqn);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiPisSetarValor(String id, Object obj) {
		// TODO ndiPisSetarValor

		NfeDetalheImpostoPisEntity ndiPis = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoPis();
		ndiPis = NfeDetalheImpostoPisDTO.setarValor(ndiPis, id, obj);

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoPis(ndiPis);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	/**
	 * 
	 * 
	 */

	public void ndeCombustivelSetarValor(String id, Object obj) {
		// TODO ndeCombustivelSetarValor

		NfeDetEspecificoCombustivelEntity ndiCombustivel = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetEspecificoCombustivel();

		if (ndiCombustivel == null) {
			return;
		}

		switch (id) {
		case "tfCodigoAnpCombustivel":
			ndiCombustivel.setCodigoAnp(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfCodifCombustivel":
			ndiCombustivel.setCodif((String) obj);

			break;
		case "tfQtdeTempAmbienteCombustivel":
			ndiCombustivel.setQuantidadeTempAmbiente(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfUfConsumoCombustivel":
			ndiCombustivel.setUfConsumo((String) obj);

			break;
		case "tfBcCideCombustivel":
			ndiCombustivel.setBaseCalculoCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaCideCombustivel":
			ndiCombustivel.setAliquotaCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCideCombustivel":
			ndiCombustivel.setValorCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetEspecificoCombustivel(
				ndiCombustivel);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndeVeiculoSetarValor(String id, Object obj) {
		// TODO ndeVeiculoSetarValor

		NfeDetEspecificoVeiculoEntity ndiVeiculo = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetEspecificoVeiculo();

		if (ndiVeiculo == null) {
			return;
		}

		switch (id) {
		case "tfTipoOperacaoVeiculo":
			ndiVeiculo.setTipoOperacao((String) obj);

			break;
		case "tfChassiVeiculo":
			ndiVeiculo.setChassi((String) obj);

			break;
		case "tfCodigoCorVeiculo":
			ndiVeiculo.setCodigoCor((String) obj);

			break;
		case "tfDescricaoCorVeiculo":
			ndiVeiculo.setDescricaoCor((String) obj);

			break;
		case "tfPotenciaMotorVeiculo":
			ndiVeiculo.setPotenciaMotor((String) obj);

			break;
		case "tfCilindradasVeiculo":
			ndiVeiculo.setCilindradas((String) obj);

			break;
		case "tfPesoLiquidoVeiculo":
			ndiVeiculo.setPesoLiquido((String) obj);

			break;
		case "tfPesoBrutoVeiculo":
			ndiVeiculo.setPesoBruto((String) obj);

			break;
		case "tfNumeroSerieVeiculo":
			ndiVeiculo.setNumeroSerie((String) obj);

			break;
		case "tfCombustivelVeiculo":
			ndiVeiculo.setTipoCombustivel((String) obj);

			break;
		case "tfNumeroMotorVeiculo":
			ndiVeiculo.setNumeroMotor((String) obj);

			break;
		case "tfCapacidadeTracaoVeiculo":
			ndiVeiculo.setCapacidadeMaximaTracao((String) obj);

			break;
		case "tfDistanciaEixosVeiculo":
			ndiVeiculo.setDistanciaEixos((String) obj);

			break;
		case "tfAnoModeloVeiculo":
			ndiVeiculo.setAnoModelo((String) obj);

			break;
		case "tfAnoFabricacaoVeiculo":
			ndiVeiculo.setAnoFabricacao((String) obj);

			break;
		case "tfTipoPinturaVeiculo":
			ndiVeiculo.setTipoPintura((String) obj);

			break;
		case "tfTipoVeiculo":
			ndiVeiculo.setTipoVeiculo((String) obj);

			break;
		case "tfEspecieVeiculo":
			ndiVeiculo.setEspecieVeiculo((String) obj);

			break;
		case "tfCondicaoVinVeiculo":
			ndiVeiculo.setCondicaoVin((String) obj);

			break;
		case "tfCondicaoVeiculo":
			ndiVeiculo.setCondicaoVeiculo((String) obj);

			break;
		case "tfCodigoMarcaModeloVeiculo":
			ndiVeiculo.setCodigoMarcaModelo((String) obj);

			break;
		case "tfCodigoCorDenatranVeiculo":
			ndiVeiculo.setCor((String) obj);

			break;
		case "tfLotacaoVeiculo":
			ndiVeiculo
					.setLotacao(ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfRestricaoVeiculo":
			ndiVeiculo.setRestricao((String) obj);

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe()
				.setNfeDetEspecificoVeiculo(ndiVeiculo);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndeMedicamentoSetarValor(String id, Object obj) {
		// TODO ndeMedicamentoSetarValor

		this.ndeMedicamentoSelecionado = this.nfeCabecalho.getNfeDetalhe()
				.getNdeMedicamento();

		if (this.ndeMedicamentoSelecionado == null) {
			return;
		}

		switch (id) {
		case "tfNumeroLoteMedicamento":
			this.ndeMedicamentoSelecionado.setNumeroLote((String) obj);

			break;
		case "tfQuantidadeLoteMedicamento":
			this.ndeMedicamentoSelecionado.setQuantidadeLote(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "pdfDataFabricacaoMedicamento":
			this.ndeMedicamentoSelecionado.setDataFabricacao((Date) obj);

			break;
		case "pdfDataValidadeMedicamento":
			this.ndeMedicamentoSelecionado.setDataValidade((Date) obj);

			break;
		case "tfPrecoMaximoConsumidorMedicamento":
			this.ndeMedicamentoSelecionado
					.setPrecoMaximoConsumidor(ObjectConverter
							.stringToValue((String) obj));

			break;
		}

		/*
		 * Integer index = this.subView.getSfNdeMedicamento().getDados()
		 * .indexOf(this.ndeMedicamentoSelecionado);
		 * 
		 * this.subView.getSfNdeMedicamento().getDados()
		 * .remove(this.ndeMedicamentoSelecionado);
		 * 
		 * this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList()
		 * .remove(this.ndeMedicamentoSelecionado);
		 * 
		 * this.ndeMedicamentoSelecionado.setNfeDetalhe(this.nfeCabecalho
		 * .getNfeDetalhe());
		 * 
		 * this.subView.getSfNdeMedicamento().getDados() .add(index,
		 * this.ndeMedicamentoSelecionado);
		 * 
		 * this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList()
		 * .add(this.ndeMedicamentoSelecionado);
		 */
	}

	public void ndeArmamentoSetarValor(String id, Object obj) {
		// TODO ndeArmamentoSetarValor

		this.ndeArmamentoSelecionado = this.nfeCabecalho.getNfeDetalhe()
				.getNdeArmamento();

		if (this.ndeArmamentoSelecionado == null) {
			return;
		}

		switch (id) {
		case "tfTipoArmaArmamento":
			this.ndeArmamentoSelecionado.setTipoArma((String) obj);

			break;
		case "tfNumeroSerieArmaArmamento":
			this.ndeArmamentoSelecionado.setNumeroSerieArma((String) obj);

			break;
		case "tfNumeroSerieCanoArmamento":
			this.ndeArmamentoSelecionado.setNumeroSerieCano((String) obj);

			break;
		case "tfDescricaoArmamento":
			this.ndeArmamentoSelecionado.setDescricao((String) obj);

			break;
		}

		/*
		 * Integer index = this.subView.getSfNdeArmamento().getDados()
		 * .indexOf(this.ndeArmamentoSelecionado);
		 * 
		 * this.subView.getSfNdeArmamento().getDados()
		 * .remove(this.ndeArmamentoSelecionado);
		 * 
		 * this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList()
		 * .remove(this.ndeArmamentoSelecionado);
		 * 
		 * this.ndeArmamentoSelecionado.setNfeDetalhe(this.nfeCabecalho
		 * .getNfeDetalhe());
		 * 
		 * this.subView.getSfNdeArmamento().getDados() .add(index,
		 * this.ndeArmamentoSelecionado);
		 * 
		 * this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList()
		 * .add(this.ndeArmamentoSelecionado);
		 */
	}

	/**
	 * LIMPAR
	 * 
	 * @param item
	 */

	public void ndeMedicamentoLimpar() {
		// this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
		// .getNdeMedicamentoList());

		this.ndeMedicamentoSelecionado = new NfeDetEspecificoMedicamentoEntity();

		/*
		 * this.subView.getTfNumeroLoteMedicamento().setValue(
		 * this.ndeMedicamentoSelecionado.getNumeroLote());
		 * this.subView.getTfQuantidadeLoteMedicamento().setValue(
		 * this.ndeMedicamentoSelecionado.getQuantidadeLote().toString());
		 * this.subView.getPdfDataFabricacaoMedicamento().setValue(
		 * this.ndeMedicamentoSelecionado.getDataFabricacao());
		 * this.subView.getPdfDataValidadeMedicamento().setValue(
		 * this.ndeMedicamentoSelecionado.getDataValidade());
		 * this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
		 * this.ndeMedicamentoSelecionado.getPrecoMaximoConsumidor()
		 * .toString());
		 * 
		 * this.subView.getGlNdeMedicamento().setEnabled(false);
		 */
	}

	/** COMBOS */

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IOperacaoFiscalDAO operacaoFiscalDAO;

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<ClienteEntity> model1 = new DefaultManyToOneComboModel<ClienteEntity>(
					ClienteListController.class, this.clienteDAO,
					super.getMainController());

			// this.subView.getMtoCliente().setModel(model1);

			DefaultManyToOneComboModel<ProdutoEntity> model2 = new DefaultManyToOneComboModel<ProdutoEntity>(
					ProdutoListController.class, this.produtoDAO,
					super.getMainController());

			this.subView.getMtoProduto().setModel(model2);

			DefaultManyToOneComboModel<OperacaoFiscalEntity> model3 = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(
					OperacaoFiscalListController.class, this.operacaoFiscalDAO,
					super.getMainController());

			this.subView.getMtoOperacaoFiscal().setModel(model3);

			/**
			 * 
			 */

			/*
			 * List<CrtEn> auxList = new ArrayList<CrtEn>();
			 * 
			 * for (CrtEn en : CrtEn.values()) { auxList.add(en); }
			 * 
			 * this.subView.getCbCstIcms().setData(auxList);
			 */

			// this.subView.carregarComboBox();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void efetuaCalculosDetalhe(boolean cupomVinculado) {
		// String csosn;
		// String cstIcms;
		BigDecimal valorTotalIcms = BigDecimal.ZERO;

		Integer crt = this.nfeCabecalho.getEmpresa().getCrt().ordinal();

		for (NfeDetalheEntity nfeDetalhe : this.nfeCabecalho
				.getNfeDetalheList()) {
			// TODO : Como calcular o frete do item?
			nfeDetalhe.setValorFrete(BigDecimal.ZERO);
			// TODO : Como calcular o seguro do item?
			nfeDetalhe.setValorSeguro(BigDecimal.ZERO);
			// TODO : Como calcular o desconto do item?
			nfeDetalhe.setValorDesconto(BigDecimal.ZERO);
			// TODO : Como calcular o valor de outras depesas do item?
			nfeDetalhe.setValorOutrasDespesas(BigDecimal.ZERO);
			// TODO : Deve-se preencher com zero ou um? Porque?
			//nfeDetalhe.setEntraTotal("0");
			// nfeDetalhe.setOrigemMercadoria("0");
			// TODO : De onde devemos pegar essa informação
			// (nfeDetalhe.setModalidadeBcIcms)?
			// nfeDetalhe.setModalidadeBcIcms("3");

			CsosnEn csosn = nfeDetalhe.getNfeDetalheImpostoIcms().getCsosnEn();

			CstIcmsEn cstIcms = nfeDetalhe.getNfeDetalheImpostoIcms()
					.getCstIcmsEn();

			if (crt == 1) { // 1 = Simples Nacional
				if (cupomVinculado == false) {
					if (csosn.equals(CsosnEn._101)) { // Tributada pelo Simples
						// Nacional com permissão de
						// crédito
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._102)) { // Tributada pelo Simples
						// Nacional sem permissão de
						// crédito
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._103)) { // Isenção do ICMS no
														// Simples
						// Nacional para faixa de
						// receita bruta
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._201)) { // Tributada pelo Simples
						// Nacional com permissão de
						// crédito e com cobrança do
						// ICMS por substituição
						// tributária
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._202)) { // Tributada pelo Simples
						// Nacional sem permissão de
						// crédito e com cobrança do
						// ICMS por substituição
						// tributária
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._300)) { // 300 - Imune -
														// Classificam-se
						// neste código as operações
						// praticadas por optantes pelo
						// Simples Nacional contempladas
						// com imunidade do ICMS.
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._400)) { // 400 - Não tributada
														// pelo
						// Simples Nacional -
						// Classificam-se neste código
						// as operações praticadas por
						// optantes pelo Simples
						// Nacional não sujeitas à
						// tributação pelo ICMS dentro
						// do Simples Nacional.
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._500)) { // 500 - ICMS cobrado
						// anteriormente por
						// substituição tributária
						// (substituído) ou por
						// antecipação - Classificam-se
						// neste código as operações
						// sujeitas exclusivamente ao
						// regime de substituição
						// tributária na condição de
						// substituído tributário ou no
						// caso de antecipações.
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}

					if (csosn.equals(CsosnEn._900)) { // 900 - Outros -
														// Classificam-se
						// neste código as demais
						// operações que não se
						// enquadrem nos códigos 101,
						// 102, 103, 201, 202, 203, 300,
						// 400 e 500.
						nfeDetalhe.getNfeDetalheImpostoIcms()
								.setBaseCalculoIcms(BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
								BigDecimal.ZERO);
						nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
								BigDecimal.ZERO);
					}
				} else {
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
				}

				nfeDetalhe.getNfeDetalheImpIpi().setCstIpi("99");
				nfeDetalhe.getNfeDetalheImpIpi().setValorBaseCalculoIpi(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi()
						.setAliquotaIpi(BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi().setValorIpi(BigDecimal.ZERO);

				nfeDetalhe.getNfeDetalheImpostoCofins().setCstCofins("99");
				nfeDetalhe.getNfeDetalheImpostoCofins().setBaseCalculoCofins(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoCofins().setValorCofins(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoCofins().setAliquotaCofinsReais(
						BigDecimal.ZERO);

				nfeDetalhe.getNfeDetalheImpostoPis().setCstPis("99");
				nfeDetalhe.getNfeDetalheImpostoPis().setValorBaseCalculoPis(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoPis().setAliquotaPisReais(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoPis().setValorPis(
						BigDecimal.ZERO);

			}

			if (crt == 2 || crt == 3) { // 2 = Simples Nacional = excesso de
				// sublimite da receita bruta | 3 -
				// Reginme Normal
				if (cstIcms.equals(CstIcmsEn._00)) { // Tributada integralmente
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							nfeDetalhe.getValorUnitarioComercial());
					// nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(nfeDetalhe.getProduto()
					// .getTributacaoEstadual().getTaxaIcms());
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							nfeDetalhe
									.getNfeDetalheImpostoIcms()
									.getAliquotaIcms()
									.divide(BigDecimal.valueOf(100))
									.multiply(
											nfeDetalhe
													.getNfeDetalheImpostoIcms()
													.getBaseCalculoIcms()));
					valorTotalIcms = valorTotalIcms.add(nfeDetalhe
							.getNfeDetalheImpostoIcms().getValorIcms());
				}

				if (cstIcms.equals(CstIcmsEn._10)) { // Tributada e com
														// cobrança
														// do ICMS
					// por substituição tributária
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._20)) { // Com redução de base de
														// cálculo
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._30)) { // Isenta ou não tributada
														// e com
					// cobrança do ICMS por substituição
					// tributária
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._40)) { // 40 - Isenta;
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._41)) { // 41 - Nao tributada;
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._50)) { // 50 Suspensao;
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._51)) { // Diferimento
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._60)) { // ICMS cobrado
														// anteriormente por
					// substituição tributária
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._70)) { // Com redução da base de
														// cálculo e
					// cobrança do ICMS por substituição
					// tributária
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}

				if (cstIcms.equals(CstIcmsEn._90)) { // Outras
					nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
							BigDecimal.ZERO);
					nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
							BigDecimal.ZERO);
				}
			} else { // if (crt == 2) || crt == 3) {
				nfeDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpostoIcms().setValorIcms(
						BigDecimal.ZERO);
			}

			nfeDetalhe.getNfeDetalheImpIpi().setCstIpi("99");

			if (nfeDetalhe.getNfeDetalheImpIpi().getCstIpi().equals("01")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("02")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("03")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("04")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("05")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("51")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("52")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("53")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("54")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("55")) {

				nfeDetalhe.getNfeDetalheImpIpi().setValorBaseCalculoIpi(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi()
						.setAliquotaIpi(BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi().setValorIpi(BigDecimal.ZERO);
			}

			if (nfeDetalhe.getNfeDetalheImpIpi().getCstIpi().equals("00")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("49")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("60")
					|| nfeDetalhe.getNfeDetalheImpIpi().getCstIpi()
							.equals("99")) {

				nfeDetalhe.getNfeDetalheImpIpi().setValorBaseCalculoIpi(
						BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi()
						.setAliquotaIpi(BigDecimal.ZERO);
				nfeDetalhe.getNfeDetalheImpIpi().setValorIpi(BigDecimal.ZERO);
			}

			nfeDetalhe.getNfeDetalheImpostoCofins().setCstCofins("99");
			nfeDetalhe.getNfeDetalheImpostoCofins().setBaseCalculoCofins(
					BigDecimal.ZERO);
			nfeDetalhe.getNfeDetalheImpostoCofins().setValorCofins(
					BigDecimal.ZERO);
			nfeDetalhe.getNfeDetalheImpostoCofins().setAliquotaCofinsReais(
					BigDecimal.ZERO);

			nfeDetalhe.getNfeDetalheImpostoPis().setCstPis("99");
			nfeDetalhe.getNfeDetalheImpostoPis().setValorBaseCalculoPis(
					BigDecimal.ZERO);
			nfeDetalhe.getNfeDetalheImpostoPis().setAliquotaPisReais(
					BigDecimal.ZERO);
			nfeDetalhe.getNfeDetalheImpostoPis().setValorPis(BigDecimal.ZERO);
		}

		this.nfeCabecalho.setValorIcms(valorTotalIcms);
	}

	@Override
	public NfeCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return nfeCabecalho;
	}

}