package dc.controller.tributario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.entidade.geral.tabela.CstIpiEntity;
import dc.entidade.geral.tabela.CstPisEntity;
import dc.entidade.geral.tabela.EfdTabela435Entity;
import dc.entidade.geral.tabela.TipoReceitaDipiEntity;
import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsConfiguracaoTributariaEntity;
import dc.entidade.tributario.IpiConfiguracaoTributariaEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.model.dao.tributario.IGrupoTributarioDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.tabela.ICfopDAO;
import dc.servicos.dao.geral.tabela.ICsosnbDAO;
import dc.servicos.dao.geral.tabela.ICstCofinsDAO;
import dc.servicos.dao.geral.tabela.ICstIcmsbDAO;
import dc.servicos.dao.geral.tabela.ICstIpiDAO;
import dc.servicos.dao.geral.tabela.ICstPisDAO;
import dc.servicos.dao.geral.tabela.IEfdTabela435DAO;
import dc.servicos.dao.geral.tabela.ITipoReceitaDipiDAO;
import dc.servicos.dao.tributario.ICofinsConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.IConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.IIcmsConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.IIpiConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.servicos.dao.tributario.IPisConfiguracaoTributariaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ConfiguracaoTributariaFormView;
import dc.visao.tributario.ConfiguracaoTributariaFormView.MODALIDADE_BC;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaFormController extends CRUDFormController<ConfiguracaoTributariaEntity> {

	private ConfiguracaoTributariaFormView subView;

	@Autowired
	private IConfiguracaoTributariaDAO dao;

	@Autowired
	private IGrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	private IOperacaoFiscalDAO operacaoFiscalDAO;

	@Autowired
	private IUfDAO ufDAO;

	private ConfiguracaoTributariaEntity currentBean;

	private String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Autowired
	private ICstPisDAO cstPisDAO;

	@Autowired
	private ICstCofinsDAO cstCofinsDAO;

	@Autowired
	private ICstIpiDAO cstIpiDAO;

	@Autowired
	private IEfdTabela435DAO efdDAO;

	@Autowired
	private ITipoReceitaDipiDAO dipiDAO;

	@Autowired
	private IPisConfiguracaoTributariaDAO pisDAO;

	@Autowired
	private ICofinsConfiguracaoTributariaDAO cofinsDAO;

	@Autowired
	private IIpiConfiguracaoTributariaDAO ipiDAO;

	@Autowired
	private ICsosnbDAO csosnbDAO;

	@Autowired
	private ICstIcmsbDAO cstbDAO;

	@Autowired
	private ICfopDAO cfopDAO;

	@Autowired
	MainController mainController;

	@Autowired
	private IIcmsConfiguracaoTributariaDAO icmsDAO;

	@Override
	public String getViewIdentifier() {

		return "ConfiguracaoTributariaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ConfiguracaoTributariaEntity();
	}

	@Override
	protected void initSubView() {
		
		subView = new ConfiguracaoTributariaFormView(this);
		
		DefaultManyToOneComboModel<GrupoTributarioEntity> model = new DefaultManyToOneComboModel<GrupoTributarioEntity>(GrupoTributarioListController.class, this.grupoTributarioDAO,
									super.getMainController()) {
				
								@Override
								public String getCaptionProperty() {
									return "descricao";
								}
				
							};
				
		this.subView.getCmbGrupoTributario().setModel(model);
	

		DefaultManyToOneComboModel<OperacaoFiscalEntity> comboOperacao = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(OperacaoFiscalListController.class,
				operacaoFiscalDAO, mainController);
		subView.getCmbOperacaoFiscal().setModel(comboOperacao);

	}

	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getCmbGrupoTributario().setValue(currentBean.getGrupoTributario());
		subView.getCmbOperacaoFiscal().setValue(currentBean.getOperacaoFiscal());

		List<IcmsConfiguracaoTributariaEntity> detalhes = currentBean.getListaIcms();

		for (IcmsConfiguracaoTributariaEntity d : detalhes) {
			Integer idCsosn = new Integer(d.getCsosnB().trim());
			d.setCsosn(csosnbDAO.find(idCsosn));

			Integer idCst = new Integer(d.getCstB().trim());
			d.setCst(cstbDAO.find(idCst));

		}

		subView.preencherIcmsSubForm(detalhes);

		carregarPis(currentBean);
		carregarCofins(currentBean);
		carregarIpi(currentBean);
	}

	public void carregarPis(ConfiguracaoTributariaEntity configuracao) {
		PisConfiguracaoTributariaEntity pis = pisDAO.buscarPorConfiguracao(configuracao);

		if (pis != null) {

			String cst = pis.getCst();
			if (cst != null && !(cst.isEmpty())) {
				subView.getTxtCstPis().setValue(pis.getCst());
				subView.getTxtDescricaoCstPis().setValue(consultarCstPis(cst).getDescricao());
			}

			String efd = pis.getCodigoApuracaoEfd();
			if (efd != null && !(efd.isEmpty())) {
				subView.getTxtEfdPis().setValue(efd);
				subView.getTxtDescricaoEfdPis().setValue(consultarEfd(efd).getDescricao());
			}

			String modalidadeBc = pis.getModalidadeBc();
			if (Validator.validateString(modalidadeBc)) {
				subView.getCmbModalidadeBcPis().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = pis.getPorcentoBc();
			if (porcentoBc != null) {
				subView.getTxtPorcentoBcPis().setValue(porcentoBc.toString());
			}

			BigDecimal aliquotaPorcento = pis.getAliquotaPorcento();
			if (aliquotaPorcento != null) {
				subView.getTxtAliquotaBcPis().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = pis.getPorcentoBc();
			if (aliquotaUnidade != null) {
				subView.getTxtAliquotaUnidadePis().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = pis.getValorPrecoMaximo();
			if (precoMaximo != null) {
				subView.getTxtValorPrecoMaximoPis().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = pis.getValorPautaFiscal();
			if (pautaFiscal != null) {
				subView.getTxtValorPautaFiscalPis().setValue(pautaFiscal.toString());
			}

		}

	}

	public void carregarCofins(ConfiguracaoTributariaEntity configuracao) {
		CofinsConfiguracaoTributariaEntity cofins = cofinsDAO.buscarPorConfiguracao(configuracao);

		if (cofins != null) {

			String cst = cofins.getCst();
			if (cst != null && !(cst.isEmpty())) {
				subView.getTxtCstCofins().setValue(cofins.getCst());
				subView.getTxtDescricaoCstCofins().setValue(consultarCstCofins(cst).getDescricao());
			}

			String efd = cofins.getCodigoApuracaoEfd();
			if (efd != null && !(efd.isEmpty())) {
				subView.getTxtEfdCofins().setValue(efd);
				subView.getTxtDescricaoEfdCofins().setValue(consultarEfd(efd).getDescricao());
			}

			String modalidadeBc = cofins.getModalidadeBc();
			if (Validator.validateString(modalidadeBc)) {
				subView.getCmbModalidadeBcCofins().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = cofins.getPorcentoBc();
			if (porcentoBc != null) {
				subView.getTxtPorcentoBcCofins().setValue(porcentoBc.toString());
			}

			BigDecimal aliquotaPorcento = cofins.getAliquotaPorcento();
			if (aliquotaPorcento != null) {
				subView.getTxtAliquotaBcCofins().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = cofins.getPorcentoBc();
			if (aliquotaUnidade != null) {
				subView.getTxtAliquotaUnidadeCofins().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = cofins.getValorPrecoMaximo();
			if (precoMaximo != null) {
				subView.getTxtValorPrecoMaximoCofins().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = cofins.getValorPautaFiscal();
			if (pautaFiscal != null) {
				subView.getTxtValorPautaFiscalCofins().setValue(pautaFiscal.toString());
			}

		}

	}

	public void carregarIpi(ConfiguracaoTributariaEntity configuracao) {
		IpiConfiguracaoTributariaEntity ipi = ipiDAO.buscarPorConfiguracao(configuracao);

		if (ipi != null) {

			String cst = ipi.getCst();
			if (cst != null && !(cst.isEmpty())) {
				subView.getTxtCstIPI().setValue(ipi.getCst());
				subView.getTxtDescricaoCstIPI().setValue(consultarCstIpi(cst).getDescricao());
			}

			String modalidadeBc = ipi.getModalidadeBc();
			if (Validator.validateString(modalidadeBc)) {
				subView.getCmbModalidadeBcIPI().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = ipi.getPorcentoBc();
			if (porcentoBc != null) {
				subView.getTxtPorcentoBcIPI().setValue(porcentoBc.toString());
			}

			BigDecimal aliquotaPorcento = ipi.getAliquotaPorcento();
			if (aliquotaPorcento != null) {
				subView.getTxtAliquotaBcIPI().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = ipi.getPorcentoBc();
			if (aliquotaUnidade != null) {
				subView.getTxtAliquotaUnidadeIPI().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = ipi.getValorPrecoMaximo();
			if (precoMaximo != null) {
				subView.getTxtValorPrecoMaximoIPI().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = ipi.getValorPautaFiscal();
			if (pautaFiscal != null) {
				subView.getTxtValorPautaFiscalIPI().setValue(pautaFiscal.toString());
			}

		}

	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setEmpresa(empresaAtual());
			String msgErro = "Problema ao realizar operação";
			GrupoTributarioEntity grupo = (GrupoTributarioEntity) subView.getCmbGrupoTributario().getValue();
			if (grupo == null) {
				msgErro = "Informe o Grupo Tributário";
				throw new ErroValidacaoException(msgErro);
			}

			OperacaoFiscalEntity operacao = (OperacaoFiscalEntity) subView.getCmbOperacaoFiscal().getValue();
			if (operacao == null) {
				msgErro = "Informe a Operação Fiscal";
				throw new ErroValidacaoException(msgErro);
			}

			currentBean.setGrupoTributario(grupo);
			currentBean.setOperacaoFiscal(operacao);
			dao.saveOrUpdate(currentBean);

			List<IcmsConfiguracaoTributariaEntity> detalhes = subView.getIcmsSubForm().getDados();

			if (detalhes != null) {
				for (IcmsConfiguracaoTributariaEntity detalhe : detalhes) {
					detalhe.setConfiguracaoTributaria(currentBean);
					detalhe.setCsosnB(detalhe.getCsosn().getId().toString());
					detalhe.setCstB(detalhe.getCst().getId().toString());

					// detalhe.setModalidadeBc(((MODALIDADE_BC)(detalhe.getModalidadeBc().getValue())).getCodigo());

					icmsDAO.saveOrUpdate(detalhe);
				}
			}

			salvarPis(currentBean);
			salvarCofins(currentBean);
			salvarIpi(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarPis(ConfiguracaoTributariaEntity configuracai) {
		PisConfiguracaoTributariaEntity pis = new PisConfiguracaoTributariaEntity();

		try {
			pis.setConfiguracaoTributaria(currentBean);
			// pis.setEmpresa(currentBean.getEmpresa());
			pis.setCst(subView.getTxtCstPis().getValue());
			pis.setCodigoApuracaoEfd(subView.getTxtEfdPis().getValue());
			pis.setModalidadeBc(((MODALIDADE_BC) (subView.getCmbModalidadeBcPis().getValue())).getCodigo());
			pis.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcPis().getValue()));
			pis.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcPis().getValue()));
			pis.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadePis().getValue()));
			pis.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalPis().getValue())));
			pis.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoPis().getValue())));
			pisDAO.saveOrUpdate(pis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarCofins(ConfiguracaoTributariaEntity configuracao) {
		CofinsConfiguracaoTributariaEntity cofins = new CofinsConfiguracaoTributariaEntity();

		try {
			cofins.setConfiguracaoTributaria(currentBean);
			// cofins.setEmpresa(currentBean.getEmpresa());
			cofins.setCst(subView.getTxtCstCofins().getValue());
			cofins.setCodigoApuracaoEfd(subView.getTxtEfdCofins().getValue());
			cofins.setModalidadeBc(((MODALIDADE_BC) (subView.getCmbModalidadeBcCofins().getValue())).getCodigo());
			cofins.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcCofins().getValue()));
			cofins.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcCofins().getValue()));
			cofins.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadeCofins().getValue()));
			cofins.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalCofins().getValue())));
			cofins.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoCofins().getValue())));
			cofinsDAO.saveOrUpdate(cofins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarIpi(ConfiguracaoTributariaEntity configuracai) {
		IpiConfiguracaoTributariaEntity ipi = new IpiConfiguracaoTributariaEntity();

		try {
			ipi.setConfiguracaoTributaria(currentBean);
			// ipi.setEmpresa(currentBean.getEmpresa());
			ipi.setCst(subView.getTxtCstIPI().getValue());
			ipi.setModalidadeBc(((MODALIDADE_BC) (subView.getCmbModalidadeBcIPI().getValue())).getCodigo());
			String dipi = subView.getTxtDipi().getValue();
			if (Validator.validateString(dipi))
				ipi.setTipoReceitaDipi(new Integer(dipi));
			ipi.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcIPI().getValue()));
			ipi.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcIPI().getValue()));
			ipi.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadeIPI().getValue()));
			ipi.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalIPI().getValue())));
			ipi.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoIPI().getValue())));
			ipiDAO.saveOrUpdate(ipi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Configuracao Tributaria";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		try {
			for (Serializable id : ids) {
				ConfiguracaoTributariaEntity configuracao = dao.find(id);

				PisConfiguracaoTributariaEntity pis = pisDAO.buscarPorConfiguracao(configuracao);
				if (pis != null)
					pisDAO.delete(pis);

				IpiConfiguracaoTributariaEntity ipi = ipiDAO.buscarPorConfiguracao(configuracao);
				if (ipi != null)
					ipiDAO.delete(ipi);

				CofinsConfiguracaoTributariaEntity cofins = cofinsDAO.buscarPorConfiguracao(configuracao);
				if (cofins != null)
					cofinsDAO.delete(cofins);

				List<IcmsConfiguracaoTributariaEntity> listaIcms = icmsDAO.buscarPorConfiguracao(configuracao);

				if (listaIcms != null) {
					for (IcmsConfiguracaoTributariaEntity obj : listaIcms) {
						icmsDAO.delete(obj);
					}
				}

			}
			dao.deleteAllByIds(ids);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<GrupoTributarioEntity> trazerGrupos() {
		return grupoTributarioDAO.getAll();
	}

	public List<OperacaoFiscalEntity> trazerOperacoes() {
		return operacaoFiscalDAO.getAll();
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.getAll();
	}

	public IcmsConfiguracaoTributariaEntity novoIcms() {
		IcmsConfiguracaoTributariaEntity objeto = new IcmsConfiguracaoTributariaEntity();
		currentBean.adicionarIcms(objeto);
		return objeto;
	}

	public CstPisEntity consultarCstPis(String codigo) {
		return cstPisDAO.procuraPorCodigo(codigo);
	}

	public CstCofinsEntity consultarCstCofins(String codigo) {
		return cstCofinsDAO.procuraPorCodigo(codigo);
	}

	public CstIpiEntity consultarCstIpi(String codigo) {
		return cstIpiDAO.procuraPorCodigo(codigo);
	}

	public EfdTabela435Entity consultarEfd(String codigo) {
		return efdDAO.procuraPorCodigo(codigo);
	}

	public TipoReceitaDipiEntity consultarDipi(String codigo) {
		return dipiDAO.procuraPorCodigo(codigo);
	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	@Override
	public ConfiguracaoTributariaEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

	public List<UfEntity> buscarUf() {
		return ufDAO.getAll(UfEntity.class);
	}
	
	public List<CfopEntity> buscarCfop() {
		return cfopDAO.getAll(CfopEntity.class);
	}
	
	public List<CsosnbEntity> buscarCsosn() {
		return csosnbDAO.getAll(CsosnbEntity.class);
	}
	
	public List<CstIcmsbEntity> buscarCst() {
		return cstbDAO.getAll(CstIcmsbEntity.class);
	}
}
