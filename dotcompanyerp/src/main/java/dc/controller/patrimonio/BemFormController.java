package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.entidade.patrimonio.GrupoBemEntity;
import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.geral.diverso.SetorDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.servicos.dao.patrimonio.EstadoConservacaoDAO;
import dc.servicos.dao.patrimonio.GrupoBemDAO;
import dc.servicos.dao.patrimonio.TipoAquisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.BemFormView;

@Controller
@Scope("prototype")
public class BemFormController extends CRUDFormController<BemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BemFormView subView;

	/** DAO'S */

	@Autowired
	private BemDAO pDAO;

	@Autowired
	private TipoAquisicaoDAO taDAO;

	@Autowired
	private EstadoConservacaoDAO ecDAO;

	@Autowired
	private GrupoBemDAO gbDAO;

	@Autowired
	private SetorDAO sDAO;

	@Autowired
	private FornecedorDAO fDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	/** ENTITIES */

	private BemEntity pEntity;

	/** CONSTRUTOR */

	public BemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new BemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Bem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String numeroNb = this.subView.getTfNumeroNb().getValue();
			String nome = this.subView.getTfNome().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			String numeroSerie = this.subView.getTfNumeroSerie().getValue();
			Date dataAquisicao = this.subView.getPdfDataAquisicao().getValue();
			Date dataAceite = this.subView.getPdfDataAceite().getValue();
			Date dataCadastro = this.subView.getPdfDataCadastro().getValue();
			Date dataContabilizado = this.subView.getPdfDataContabilizado().getValue();
			Date dataVistoria = this.subView.getPdfDataVistoria().getValue();
			Date dataMarcacao = this.subView.getPdfDataMarcacao().getValue();
			Date dataBaixa = this.subView.getPdfDataBaixa().getValue();
			Date vencimentoGarantia = this.subView.getPdfVencimentoGarantia().getValue();
			String numeroNotaFiscal = this.subView.getTfNumeroNotaFiscal().getValue();
			String chaveNfe = this.subView.getTfChaveNfe().getValue();
			Double valorOriginal = Double.parseDouble(this.subView.getTfValorOriginal().getValue());
			Double valorCompra = Double.parseDouble(this.subView.getTfValorCompra().getValue());
			Double valorAtualizado = Double.parseDouble(this.subView.getTfValorAtualizado().getValue());
			Double valorBaixa = Double.parseDouble(this.subView.getTfValorBaixa().getValue());
			String deprecia = this.subView.getTfDeprecia().getValue();
			String metodoDepreciacao = this.subView.getTfMetodoDepreciacao().getValue();
			Date inicioDepreciacao = this.subView.getPdfInicioDepreciacao().getValue();
			Date ultimaDepreciacao = this.subView.getPdfUltimaDepreciacao().getValue();
			String tipoDepreciacao = this.subView.getTfTipoDepreciacao().getValue();
			Double taxaAnualDepreciacao = Double.parseDouble(this.subView.getTfTaxaAnualDepreciacao().getValue());
			Double taxaMensalDepreciacao = Double.parseDouble(this.subView.getTfTaxaMensalDepreciacao().getValue());
			Double taxaDepreciacaoAcelerada = Double.parseDouble(this.subView.getTfTaxaDepreciacaoAcelerada().getValue());
			Double taxaDepreciacaoIncentivada = Double.parseDouble(this.subView.getTfTaxaDepreciacaoIncentivada().getValue());
			String funcao = this.subView.getTfFuncao().getValue();

			TipoAquisicaoEntity tipoAquisicao = (TipoAquisicaoEntity) this.subView.getCbTipoAquisicao().getValue();
			EstadoConservacaoEntity estadoConservacao = (EstadoConservacaoEntity) this.subView.getCbEstadoConservacao().getValue();
			GrupoBemEntity grupoBem = (GrupoBemEntity) this.subView.getCbGrupoBem().getValue();
			SetorEntity setor = (SetorEntity) this.subView.getCbSetor().getValue();
			FornecedorEntity fornecedor = (FornecedorEntity) this.subView.getCbFornecedor().getValue();
			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView.getCbColaborador().getValue();

			this.pEntity.setNumeroNb(numeroNb);
			this.pEntity.setNome(nome);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setNumeroSerie(numeroSerie);
			this.pEntity.setDataAquisicao(dataAquisicao);
			this.pEntity.setDataAceite(dataAceite);
			this.pEntity.setDataCadastro(dataCadastro);
			this.pEntity.setDataContabilizado(dataContabilizado);
			this.pEntity.setDataVistoria(dataVistoria);
			this.pEntity.setDataMarcacao(dataMarcacao);
			this.pEntity.setDataBaixa(dataBaixa);
			this.pEntity.setVencimentoGarantia(vencimentoGarantia);
			this.pEntity.setNumeroNotaFiscal(numeroNotaFiscal);
			this.pEntity.setChaveNfe(chaveNfe);
			this.pEntity.setValorOriginal(valorOriginal);
			this.pEntity.setValorCompra(valorCompra);
			this.pEntity.setValorAtualizado(valorAtualizado);
			this.pEntity.setValorBaixa(valorBaixa);
			this.pEntity.setDeprecia(deprecia);
			this.pEntity.setMetodoDepreciacao(metodoDepreciacao);
			this.pEntity.setInicioDepreciacao(inicioDepreciacao);
			this.pEntity.setUltimaDepreciacao(ultimaDepreciacao);
			this.pEntity.setTipoDepreciacao(tipoDepreciacao);
			this.pEntity.setTaxaAnualDepreciacao(taxaAnualDepreciacao);
			this.pEntity.setTaxaMensalDepreciacao(taxaMensalDepreciacao);
			this.pEntity.setTaxaDepreciacaoAcelerada(taxaDepreciacaoAcelerada);
			this.pEntity.setTaxaDepreciacaoIncentivada(taxaDepreciacaoIncentivada);
			this.pEntity.setFuncao(funcao);

			this.pEntity.setTipoAquisicao(tipoAquisicao);
			this.pEntity.setEstadoConservacao(estadoConservacao);
			this.pEntity.setGrupoBem(grupoBem);
			this.pEntity.setSetor(setor);
			this.pEntity.setFornecedor(fornecedor);
			this.pEntity.setColaborador(colaborador);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new BemEntity();

			this.subView.getTfNumeroNb().setValue(this.pEntity.getNumeroNb());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfNumeroSerie().setValue(this.pEntity.getNumeroSerie());
			this.subView.getPdfDataAquisicao().setValue(this.pEntity.getDataAquisicao());
			this.subView.getPdfDataAceite().setValue(this.pEntity.getDataAceite());
			this.subView.getPdfDataCadastro().setValue(this.pEntity.getDataCadastro());
			this.subView.getPdfDataContabilizado().setValue(this.pEntity.getDataContabilizado());
			this.subView.getPdfDataVistoria().setValue(this.pEntity.getDataVistoria());
			this.subView.getPdfDataMarcacao().setValue(this.pEntity.getDataMarcacao());
			this.subView.getPdfDataBaixa().setValue(this.pEntity.getDataBaixa());
			this.subView.getPdfVencimentoGarantia().setValue(this.pEntity.getVencimentoGarantia());
			this.subView.getTfNumeroNotaFiscal().setValue(this.pEntity.getNumeroNotaFiscal());
			this.subView.getTfChaveNfe().setValue(this.pEntity.getChaveNfe());
			this.subView.getTfValorOriginal().setValue(String.valueOf(this.pEntity.getValorOriginal()));
			this.subView.getTfValorCompra().setValue(String.valueOf(this.pEntity.getValorCompra()));
			this.subView.getTfValorAtualizado().setValue(String.valueOf(this.pEntity.getValorAtualizado()));
			this.subView.getTfValorBaixa().setValue(String.valueOf(this.pEntity.getValorBaixa()));
			this.subView.getTfDeprecia().setValue(this.pEntity.getDeprecia());
			this.subView.getTfMetodoDepreciacao().setValue(this.pEntity.getMetodoDepreciacao());
			this.subView.getPdfInicioDepreciacao().setValue(this.pEntity.getInicioDepreciacao());
			this.subView.getPdfUltimaDepreciacao().setValue(this.pEntity.getUltimaDepreciacao());
			this.subView.getTfTipoDepreciacao().setValue(this.pEntity.getTipoDepreciacao());
			this.subView.getTfTaxaAnualDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaAnualDepreciacao()));
			this.subView.getTfTaxaMensalDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaMensalDepreciacao()));
			this.subView.getTfTaxaDepreciacaoAcelerada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoAcelerada()));
			this.subView.getTfTaxaDepreciacaoIncentivada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoIncentivada()));
			this.subView.getTfFuncao().setValue(this.pEntity.getFuncao());

			this.subView.carregarCmbTipoAquisicao(this.tipoAquisicaoListarTodos());
			this.subView.carregarCmbEstadoConservacao(this.estadoConservacaoListarTodos());
			this.subView.carregarCmbGrupoBem(this.grupoBemListarTodos());
			this.subView.carregarCmbSetor(this.setorListarTodos());
			this.subView.carregarCmbFornecedor(this.fornecedorListarTodos());
			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbTipoAquisicao().setValue(this.pEntity.getTipoAquisicao());
			this.subView.getCbEstadoConservacao().setValue(this.pEntity.getEstadoConservacao());
			this.subView.getCbGrupoBem().setValue(this.pEntity.getGrupoBem());
			this.subView.getCbSetor().setValue(this.pEntity.getSetor());
			this.subView.getCbFornecedor().setValue(this.pEntity.getFornecedor());
			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfNumeroNb().setValue(this.pEntity.getNumeroNb());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfNumeroSerie().setValue(this.pEntity.getNumeroSerie());
			this.subView.getPdfDataAquisicao().setValue(this.pEntity.getDataAquisicao());
			this.subView.getPdfDataAceite().setValue(this.pEntity.getDataAceite());
			this.subView.getPdfDataCadastro().setValue(this.pEntity.getDataCadastro());
			this.subView.getPdfDataContabilizado().setValue(this.pEntity.getDataContabilizado());
			this.subView.getPdfDataVistoria().setValue(this.pEntity.getDataVistoria());
			this.subView.getPdfDataMarcacao().setValue(this.pEntity.getDataMarcacao());
			this.subView.getPdfDataBaixa().setValue(this.pEntity.getDataBaixa());
			this.subView.getPdfVencimentoGarantia().setValue(this.pEntity.getVencimentoGarantia());
			this.subView.getTfNumeroNotaFiscal().setValue(this.pEntity.getNumeroNotaFiscal());
			this.subView.getTfChaveNfe().setValue(this.pEntity.getChaveNfe());
			this.subView.getTfValorOriginal().setValue(String.valueOf(this.pEntity.getValorOriginal()));
			this.subView.getTfValorCompra().setValue(String.valueOf(this.pEntity.getValorCompra()));
			this.subView.getTfValorAtualizado().setValue(String.valueOf(this.pEntity.getValorAtualizado()));
			this.subView.getTfValorBaixa().setValue(String.valueOf(this.pEntity.getValorBaixa()));
			this.subView.getTfDeprecia().setValue(this.pEntity.getDeprecia());
			this.subView.getTfMetodoDepreciacao().setValue(this.pEntity.getMetodoDepreciacao());
			this.subView.getPdfInicioDepreciacao().setValue(this.pEntity.getInicioDepreciacao());
			this.subView.getPdfUltimaDepreciacao().setValue(this.pEntity.getUltimaDepreciacao());
			this.subView.getTfTipoDepreciacao().setValue(this.pEntity.getTipoDepreciacao());
			this.subView.getTfTaxaAnualDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaAnualDepreciacao()));
			this.subView.getTfTaxaMensalDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaMensalDepreciacao()));
			this.subView.getTfTaxaDepreciacaoAcelerada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoAcelerada()));
			this.subView.getTfTaxaDepreciacaoIncentivada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoIncentivada()));
			this.subView.getTfFuncao().setValue(this.pEntity.getFuncao());

			this.subView.carregarCmbTipoAquisicao(this.tipoAquisicaoListarTodos());
			this.subView.carregarCmbEstadoConservacao(this.estadoConservacaoListarTodos());
			this.subView.carregarCmbGrupoBem(this.grupoBemListarTodos());
			this.subView.carregarCmbSetor(this.setorListarTodos());
			this.subView.carregarCmbFornecedor(this.fornecedorListarTodos());
			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbTipoAquisicao().setValue(this.pEntity.getTipoAquisicao());
			this.subView.getCbEstadoConservacao().setValue(this.pEntity.getEstadoConservacao());
			this.subView.getCbGrupoBem().setValue(this.pEntity.getGrupoBem());
			this.subView.getCbSetor().setValue(this.pEntity.getSetor());
			this.subView.getCbFornecedor().setValue(this.pEntity.getFornecedor());
			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
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
			this.pEntity = new BemEntity();

			if (this.taDAO == null) {
				this.taDAO = new TipoAquisicaoDAO();
			}

			if (this.ecDAO == null) {
				this.ecDAO = new EstadoConservacaoDAO();
			}

			if (this.gbDAO == null) {
				this.gbDAO = new GrupoBemDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new SetorDAO();
			}

			if (this.fDAO == null) {
				this.fDAO = new FornecedorDAO();
			}

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getTfNumeroNb().setValue(this.pEntity.getNumeroNb());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfNumeroSerie().setValue(this.pEntity.getNumeroSerie());
			this.subView.getPdfDataAquisicao().setValue(this.pEntity.getDataAquisicao());
			this.subView.getPdfDataAceite().setValue(this.pEntity.getDataAceite());
			this.subView.getPdfDataCadastro().setValue(this.pEntity.getDataCadastro());
			this.subView.getPdfDataContabilizado().setValue(this.pEntity.getDataContabilizado());
			this.subView.getPdfDataVistoria().setValue(this.pEntity.getDataVistoria());
			this.subView.getPdfDataMarcacao().setValue(this.pEntity.getDataMarcacao());
			this.subView.getPdfDataBaixa().setValue(this.pEntity.getDataBaixa());
			this.subView.getPdfVencimentoGarantia().setValue(this.pEntity.getVencimentoGarantia());
			this.subView.getTfNumeroNotaFiscal().setValue(this.pEntity.getNumeroNotaFiscal());
			this.subView.getTfChaveNfe().setValue(this.pEntity.getChaveNfe());
			this.subView.getTfValorOriginal().setValue(String.valueOf(this.pEntity.getValorOriginal()));
			this.subView.getTfValorCompra().setValue(String.valueOf(this.pEntity.getValorCompra()));
			this.subView.getTfValorAtualizado().setValue(String.valueOf(this.pEntity.getValorAtualizado()));
			this.subView.getTfValorBaixa().setValue(String.valueOf(this.pEntity.getValorBaixa()));
			this.subView.getTfDeprecia().setValue(this.pEntity.getDeprecia());
			this.subView.getTfMetodoDepreciacao().setValue(this.pEntity.getMetodoDepreciacao());
			this.subView.getPdfInicioDepreciacao().setValue(this.pEntity.getInicioDepreciacao());
			this.subView.getPdfUltimaDepreciacao().setValue(this.pEntity.getUltimaDepreciacao());
			this.subView.getTfTipoDepreciacao().setValue(this.pEntity.getTipoDepreciacao());
			this.subView.getTfTaxaAnualDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaAnualDepreciacao()));
			this.subView.getTfTaxaMensalDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaMensalDepreciacao()));
			this.subView.getTfTaxaDepreciacaoAcelerada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoAcelerada()));
			this.subView.getTfTaxaDepreciacaoIncentivada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoIncentivada()));
			this.subView.getTfFuncao().setValue(this.pEntity.getFuncao());

			this.subView.carregarCmbTipoAquisicao(this.tipoAquisicaoListarTodos());
			this.subView.carregarCmbEstadoConservacao(this.estadoConservacaoListarTodos());
			this.subView.carregarCmbGrupoBem(this.grupoBemListarTodos());
			this.subView.carregarCmbSetor(this.setorListarTodos());
			this.subView.carregarCmbFornecedor(this.fornecedorListarTodos());
			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbTipoAquisicao().setValue(this.pEntity.getTipoAquisicao());
			this.subView.getCbEstadoConservacao().setValue(this.pEntity.getEstadoConservacao());
			this.subView.getCbGrupoBem().setValue(this.pEntity.getGrupoBem());
			this.subView.getCbSetor().setValue(this.pEntity.getSetor());
			this.subView.getCbFornecedor().setValue(this.pEntity.getFornecedor());
			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new BemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new BemEntity();

			if (this.taDAO == null) {
				this.taDAO = new TipoAquisicaoDAO();
			}

			if (this.ecDAO == null) {
				this.ecDAO = new EstadoConservacaoDAO();
			}

			if (this.gbDAO == null) {
				this.gbDAO = new GrupoBemDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new SetorDAO();
			}

			if (this.fDAO == null) {
				this.fDAO = new FornecedorDAO();
			}

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getTfNumeroNb().setValue(this.pEntity.getNumeroNb());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfNumeroSerie().setValue(this.pEntity.getNumeroSerie());
			this.subView.getPdfDataAquisicao().setValue(this.pEntity.getDataAquisicao());
			this.subView.getPdfDataAceite().setValue(this.pEntity.getDataAceite());
			this.subView.getPdfDataCadastro().setValue(this.pEntity.getDataCadastro());
			this.subView.getPdfDataContabilizado().setValue(this.pEntity.getDataContabilizado());
			this.subView.getPdfDataVistoria().setValue(this.pEntity.getDataVistoria());
			this.subView.getPdfDataMarcacao().setValue(this.pEntity.getDataMarcacao());
			this.subView.getPdfDataBaixa().setValue(this.pEntity.getDataBaixa());
			this.subView.getPdfVencimentoGarantia().setValue(this.pEntity.getVencimentoGarantia());
			this.subView.getTfNumeroNotaFiscal().setValue(this.pEntity.getNumeroNotaFiscal());
			this.subView.getTfChaveNfe().setValue(this.pEntity.getChaveNfe());
			this.subView.getTfValorOriginal().setValue(String.valueOf(this.pEntity.getValorOriginal()));
			this.subView.getTfValorCompra().setValue(String.valueOf(this.pEntity.getValorCompra()));
			this.subView.getTfValorAtualizado().setValue(String.valueOf(this.pEntity.getValorAtualizado()));
			this.subView.getTfValorBaixa().setValue(String.valueOf(this.pEntity.getValorBaixa()));
			this.subView.getTfDeprecia().setValue(this.pEntity.getDeprecia());
			this.subView.getTfMetodoDepreciacao().setValue(this.pEntity.getMetodoDepreciacao());
			this.subView.getPdfInicioDepreciacao().setValue(this.pEntity.getInicioDepreciacao());
			this.subView.getPdfUltimaDepreciacao().setValue(this.pEntity.getUltimaDepreciacao());
			this.subView.getTfTipoDepreciacao().setValue(this.pEntity.getTipoDepreciacao());
			this.subView.getTfTaxaAnualDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaAnualDepreciacao()));
			this.subView.getTfTaxaMensalDepreciacao().setValue(String.valueOf(this.pEntity.getTaxaMensalDepreciacao()));
			this.subView.getTfTaxaDepreciacaoAcelerada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoAcelerada()));
			this.subView.getTfTaxaDepreciacaoIncentivada().setValue(String.valueOf(this.pEntity.getTaxaDepreciacaoIncentivada()));
			this.subView.getTfFuncao().setValue(this.pEntity.getFuncao());

			this.subView.carregarCmbTipoAquisicao(this.tipoAquisicaoListarTodos());
			this.subView.carregarCmbEstadoConservacao(this.estadoConservacaoListarTodos());
			this.subView.carregarCmbGrupoBem(this.grupoBemListarTodos());
			this.subView.carregarCmbSetor(this.setorListarTodos());
			this.subView.carregarCmbFornecedor(this.fornecedorListarTodos());
			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbTipoAquisicao().setValue(this.pEntity.getTipoAquisicao());
			this.subView.getCbEstadoConservacao().setValue(this.pEntity.getEstadoConservacao());
			this.subView.getCbGrupoBem().setValue(this.pEntity.getGrupoBem());
			this.subView.getCbSetor().setValue(this.pEntity.getSetor());
			this.subView.getCbFornecedor().setValue(this.pEntity.getFornecedor());
			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		// String numeroNb = this.subView.getTfNumeroNb().getValue();

		// if (!Validator.validateString(numeroNb)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfNumeroNb(), msg);
		//
		// return false;
		// }

		// String nome = this.subView.getTfNome().getValue();

		// if (!Validator.validateString(nome)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfNome(), msg);
		//
		// return false;
		// }

		// String descricao = this.subView.getTfDescricao().getValue();

		// if (!Validator.validateString(descricao)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfDescricao(), msg);
		//
		// return false;
		// }

		// String numeroSerie = this.subView.getTfNumeroSerie().getValue();

		// if (!Validator.validateString(numeroSerie)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfNumeroSerie(), msg);
		//
		// return false;
		// }

		Object dataAquisicao = this.subView.getPdfDataAquisicao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAquisicao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAquisicao(), msg);

			return false;
		}

		Object dataAceite = this.subView.getPdfDataAceite().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAceite)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAceite(), msg);

			return false;
		}

		Object dataCadastro = this.subView.getPdfDataCadastro().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataCadastro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataCadastro(), msg);

			return false;
		}

		Object dataContabilizado = this.subView.getPdfDataContabilizado().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataContabilizado)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataContabilizado(), msg);

			return false;
		}

		Object dataVistoria = this.subView.getPdfDataVistoria().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataVistoria)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataVistoria(), msg);

			return false;
		}

		Object dataMarcacao = this.subView.getPdfDataMarcacao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataMarcacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataMarcacao(), msg);

			return false;
		}

		Object dataBaixa = this.subView.getPdfDataBaixa().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataBaixa)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataBaixa(), msg);

			return false;
		}

		Object vencimentoGarantia = this.subView.getPdfVencimentoGarantia().getValue();

		if (!ObjectValidator.validateNotRequiredDate(vencimentoGarantia)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfVencimentoGarantia(), msg);

			return false;
		}

		// String numeroNotaFiscal = this.subView.getTfNumeroNotaFiscal()
		// .getValue();

		// if (!Validator.validateString(numeroNotaFiscal)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfNumeroNotaFiscal(), msg);
		//
		// return false;
		// }

		// String chaveNfe = this.subView.getTfChaveNfe().getValue();

		// if (!Validator.validateString(chaveNfe)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfChaveNfe(), msg);
		//
		// return false;
		// }

		String valorOriginal = this.subView.getTfValorOriginal().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorOriginal)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorOriginal(), msg);

			return false;
		}

		String valorCompra = this.subView.getTfValorCompra().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorCompra)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorCompra(), msg);

			return false;
		}

		String valorAtualizado = this.subView.getTfValorAtualizado().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorAtualizado)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorAtualizado(), msg);

			return false;
		}

		String valorBaixa = this.subView.getTfValorBaixa().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorBaixa)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorBaixa(), msg);

			return false;
		}

		// String deprecia = this.subView.getTfDeprecia().getValue();

		// if (!Validator.validateString(deprecia)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfDeprecia(), msg);
		//
		// return false;
		// }

		// String metodoDepreciacao = this.subView.getTfMetodoDepreciacao()
		// .getValue();

		// if (!Validator.validateString(metodoDepreciacao)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfMetodoDepreciacao(), msg);
		//
		// return false;
		// }

		Object inicioDepreciacao = this.subView.getPdfInicioDepreciacao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(inicioDepreciacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfInicioDepreciacao(), msg);

			return false;
		}

		Object ultimaDepreciacao = this.subView.getPdfUltimaDepreciacao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(ultimaDepreciacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfUltimaDepreciacao(), msg);

			return false;
		}

		// String tipoDepreciacao =
		// this.subView.getTfTipoDepreciacao().getValue();

		// if (!Validator.validateString(tipoDepreciacao)) {
		// String msg = "Não pode ficar em branco.";
		//
		// adicionarErroDeValidacao(this.subView.getTfTipoDepreciacao(), msg);
		//
		// return false;
		// }

		String taxaAnualDepreciacao = this.subView.getTfTaxaAnualDepreciacao().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxaAnualDepreciacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxaAnualDepreciacao(), msg);

			return false;
		}

		String taxaMensalDepreciacao = this.subView.getTfTaxaMensalDepreciacao().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxaMensalDepreciacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxaMensalDepreciacao(), msg);

			return false;
		}

		String taxaDepreciacaoAcelerada = this.subView.getTfTaxaDepreciacaoAcelerada().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxaDepreciacaoAcelerada)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxaDepreciacaoAcelerada(), msg);

			return false;
		}

		String taxaDepreciacaoIncentivada = this.subView.getTfTaxaDepreciacaoIncentivada().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxaDepreciacaoIncentivada)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxaDepreciacaoIncentivada(), msg);

			return false;
		}

		TipoAquisicaoEntity tipoAquisicao = (TipoAquisicaoEntity) this.subView.getCbTipoAquisicao().getValue();

		if (!ObjectValidator.validateObject(tipoAquisicao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbTipoAquisicao(), msg);

			return false;
		}

		EstadoConservacaoEntity estadoConservacao = (EstadoConservacaoEntity) this.subView.getCbEstadoConservacao().getValue();

		if (!ObjectValidator.validateObject(estadoConservacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbEstadoConservacao(), msg);

			return false;
		}

		GrupoBemEntity grupoBem = (GrupoBemEntity) this.subView.getCbGrupoBem().getValue();

		if (!ObjectValidator.validateObject(grupoBem)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbGrupoBem(), msg);

			return false;
		}

		SetorEntity setor = (SetorEntity) this.subView.getCbSetor().getValue();

		if (!ObjectValidator.validateObject(setor)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbSetor(), msg);

			return false;
		}

		FornecedorEntity fornecedor = (FornecedorEntity) this.subView.getCbFornecedor().getValue();

		if (!ObjectValidator.validateObject(fornecedor)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbFornecedor(), msg);

			return false;
		}

		ColaboradorEntity colaborador = (ColaboradorEntity) this.subView.getCbColaborador().getValue();

		if (!ObjectValidator.validateObject(colaborador)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbColaborador(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_bem_fc";
	}

	/** COMBOS */

	public List<TipoAquisicaoEntity> tipoAquisicaoListarTodos() {
		List<TipoAquisicaoEntity> auxLista = new ArrayList<TipoAquisicaoEntity>();

		auxLista = this.taDAO.listarTodos();

		return auxLista;
	}

	public List<EstadoConservacaoEntity> estadoConservacaoListarTodos() {
		List<EstadoConservacaoEntity> auxLista = new ArrayList<EstadoConservacaoEntity>();

		auxLista = this.ecDAO.listarTodos();

		return auxLista;
	}

	public List<GrupoBemEntity> grupoBemListarTodos() {
		List<GrupoBemEntity> auxLista = new ArrayList<GrupoBemEntity>();

		auxLista = this.gbDAO.listarTodos();

		return auxLista;
	}

	public List<SetorEntity> setorListarTodos() {
		List<SetorEntity> auxLista = new ArrayList<SetorEntity>();

		auxLista = this.sDAO.listarTodos();

		return auxLista;
	}

	public List<FornecedorEntity> fornecedorListarTodos() {
		List<FornecedorEntity> auxLista = new ArrayList<FornecedorEntity>();

		auxLista = this.fDAO.getAll();

		return auxLista;
	}

	public List<ColaboradorEntity> colaboradorListarTodos() {
		List<ColaboradorEntity> auxLista = new ArrayList<ColaboradorEntity>();

		auxLista = this.cDAO.getAll();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public BemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}