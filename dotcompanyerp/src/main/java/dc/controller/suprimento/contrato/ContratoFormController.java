package dc.controller.suprimento.contrato;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.hibernate.SessionFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupDateField;

import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.UfListController;
import dc.controller.geral.ged.DocumentoListController;
import dc.controller.geral.pessoal.PessoaListController;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.TipoDocumento;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.contrato.ContratoEntity;
import dc.entidade.suprimentos.contrato.ContratoProduto;
import dc.entidade.suprimentos.contrato.HistFaturamentoEntity;
import dc.entidade.suprimentos.contrato.HistoricoReajusteEntity;
import dc.entidade.suprimentos.contrato.PrevFaturamentoEntity;
import dc.entidade.suprimentos.contrato.SolicitacaoServicoEntity;
import dc.entidade.suprimentos.contrato.TipoContratoEntity;
import dc.model.business.ged.DocumentoBusiness;
import dc.model.business.ged.DocumentoBusinessImpl;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.administrativo.empresa.IEmpresaDAO;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.ged.IDocumentoDAO;
import dc.servicos.dao.suprimentos.contrato.IContratoDAO;
import dc.servicos.dao.suprimentos.contrato.ISolicitacaoServicoDAO;
import dc.servicos.dao.suprimentos.contrato.ITipoContratoDAO;
import dc.servicos.util.Util;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimento.contrato.ContratosFormView;

@Controller
@Scope("prototype")
public class ContratoFormController extends CRUDFormController<ContratoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratosFormView subView;

	@Autowired
	private IContratoDAO contratoDAO;

	@Autowired
	private DocumentoBusiness documentoBusiness;

	// @Autowired
	// private MainController mainController;

	@Autowired
	private ITipoContratoDAO tipoContratoDAO;

	@Autowired
	private IDocumentoDAO documentoDAO;

	@Autowired
	private IPessoaDAO pessoaDAO;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private ISolicitacaoServicoDAO solicitacaoServicoDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IUfDAO ufDAO;

	@Autowired
	private IEmpresaDAO empresaDAO;

	@Autowired
	private IPessoaEnderecoDAO pessoaEnderecoDAO;

	private ContratoEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = validaCampos();

		List<PrevFaturamentoEntity> contratoPrevFaturamento = subView.getPrevisaoFaturamentoSubForm().getDados();

		if (Validator.validateString(subView.getTxtValor().getValue())) {
			BigDecimal valorTela = (BigDecimal) subView.getTxtValor().getConvertedValue();
			valorTela = valorTela.setScale(2, RoundingMode.HALF_EVEN);
			if (valorTela.compareTo(getTotal(contratoPrevFaturamento)) != 0) {
				adicionarErroDeValidacao(subView.getPrevisaoFaturamentoSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
				valido = false;
				mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
			}
		}

		return valido;
	}

	private boolean validaCampos() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNumero(), "Número inválido");
			valido = false;
		}

		PessoaEntity pessoa = (PessoaEntity) subView.getCbmPessoa().getValue();

		if (!Validator.validateObject(pessoa)) {
			adicionarErroDeValidacao(subView.getCbmPessoa(), "Não pode ficar em branco");
			valido = false;
		}
		
		SolicitacaoServicoEntity solicitacao = (SolicitacaoServicoEntity) subView.getCmbSolicitacaoServico().getValue();

		if (!Validator.validateObject(solicitacao)) {
			adicionarErroDeValidacao(subView.getCmbSolicitacaoServico(), "Não pode ficar em branco");
			valido = false;
		}

		TipoContratoEntity tipoContrato = (TipoContratoEntity) subView.getCbmTipoContrato().getValue();
		if (!Validator.validateObject(tipoContrato)) {
			adicionarErroDeValidacao(subView.getCbmTipoContrato(), "Não pode ficar em branco");
			valido = false;
		}

		Date dataVigencia = (Date) subView.getDtVigencia().getValue();

		if (!Validator.validateObject(dataVigencia)) {
			adicionarErroDeValidacao(subView.getDtVigencia(), "Não pode ficar em branco");
			valido = false;
		}

		Date dataCadastro = (Date) subView.getDtCadastro().getValue();

		if (!Validator.validateObject(dataCadastro)) {
			adicionarErroDeValidacao(subView.getDtCadastro(), "Não pode ficar em branco");
			valido = false;
		}

		Date dataFimVigencia = (Date) subView.getDtFimVigencia().getValue();

		if (!Validator.validateObject(dataFimVigencia)) {
			adicionarErroDeValidacao(subView.getDtFimVigencia(), "Não pode ficar em branco");
			valido = false;
		}

		/*if (!Validator.validateNumber(subView.getTxtValor().getConvertedValue().toString())) {
			adicionarErroDeValidacao(subView.getTxtValor(), "Número inválido");
			valido = false;
		}*/

		if (!Validator.validateNumber(subView.getTxtQuantidadeParcelas().getValue())) {
			adicionarErroDeValidacao(subView.getTxtQuantidadeParcelas(), "Número inválido");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtIntervaloParcelas().getValue())) {
			adicionarErroDeValidacao(subView.getTxtIntervaloParcelas(), "Número inválido");
			valido = false;
		}

		/*if (!Validator.validateString(subView.getTxaDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaObservacoes().getValue())) {
			adicionarErroDeValidacao(subView.getTxaObservacoes(), "Não pode ficar em branco");
			valido = false;
		}*/

		/*
		 * if (!Validator.validateString(subView.getTxaTemplate().getValue())) {
		 * adicionarErroDeValidacao(subView.getTxaTemplate(),
		 * "Não pode ficar em branco"); valido = false; }
		 */
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ContratoEntity();
	}

	/*
	 * private void carregarCombos() { /*
	 * subView.carregaComboContabilConta(contabilContaDAO.getAll(ContabilConta
	 * .class));
	 * subView.carregaComboTipoContrato(tipoContratoDAO.getAll(TipoContrato
	 * .class)); subView.carregaComboSolicitacaoServico(solicitacaoServicoDAO
	 * .getAll(ContratoSolicitacaoServico.class));
	 */

	/*
	 * DefaultManyToOneComboModel<ContabilConta> contabilContaModel = new
	 * DefaultManyToOneComboModel<ContabilConta>(
	 * ContabilContaListController.class, this.contabilContaDAO,
	 * super.getMainController()) {
	 * 
	 * @Override public String getCaptionProperty() { return "descricao"; } };
	 * 
	 * DefaultManyToOneComboModel<TipoContrato> tipoContratoModel = new
	 * DefaultManyToOneComboModel
	 * <TipoContrato>(TipoContratoListController.class, this.tipoContratoDAO,
	 * super.getMainController());
	 * 
	 * /**
	 * 
	 * Ajustes para receber os dados de Solicitação de Serviço pegando os
	 * StatusSolicitação para aparecer no ComboBox
	 */

	/*
	 * DefaultManyToOneComboModel<ContratoSolicitacaoServico>
	 * contratoSolicitacaoServicoModel = new
	 * DefaultManyToOneComboModel<ContratoSolicitacaoServico>(
	 * ContratoSolicitacaoServicoListController.class,
	 * this.solicitacaoServicoDAO, super.getMainController());
	 * 
	 * @Override public String getCaptionProperty() { return "descricao"; } };
	 * 
	 * subView.getCbmContabilConta().setModel(contabilContaModel);
	 * subView.getCbmTipoContrato().setModel(tipoContratoModel);
	 */
	// this.subView.getCmbSolicitacaoServico().setModel(contratoSolicitacaoServicoModel);
	// subView.getCmbSolicitacaoServico().setModel(contratoSolicitacaoServicoModel);

	/*
	 * List<Template> documentos = documentoDAO.getAll(Template.class);
	 * 
	 * Iterator<Template> iterator = documentos.iterator(); while
	 * (iterator.hasNext()) { Template doc = iterator.next(); if
	 * (doc.getDocumentos().size() > 0 &&
	 * !doc.getDocumentos().get(0).getCaminho().endsWith("docx")) {
	 * iterator.remove(); }
	 * 
	 * }
	 * 
	 * 
	 * // subView.carregaComboDocumento(documentos); }
	 */

	@Override
	protected void initSubView() {
		subView = new ContratosFormView(this);

		DefaultManyToOneComboModel<UfEntity> modelUf = new DefaultManyToOneComboModel<UfEntity>(UfListController.class, this.ufDAO, super.getMainController());

		this.subView.getMocUf().setModel(modelUf);

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

		subView.getDtCadastro().setValue(new Date());

		DefaultManyToOneComboModel<PessoaEntity> pessoaModel = new DefaultManyToOneComboModel<PessoaEntity>(PessoaListController.class, this.pessoaDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

		};

		DefaultManyToOneComboModel<TipoContratoEntity> tipoContratoModel = new DefaultManyToOneComboModel<TipoContratoEntity>(TipoContratoListController.class, this.tipoContratoDAO,
				super.getMainController());

		/*
		 * DefaultManyToOneComboModel<ProdutoEntity> modelProduto = new
		 * DefaultManyToOneComboModel<ProdutoEntity>(
		 * ProdutoListController.class, this.produtoDAO,
		 * super.getMainController());
		 * 
		 * subView.getCmbProduto().setModel(modelProduto);
		 */

		// DefaultManyToOneComboModel<UfEntity> templateUF = new
		// DefaultManyToOneComboModel<UfEntity>(
		// UfListController.class, this.ufDAO, super.getMainController());

		List<Filter> filters = new ArrayList<Filter>();
		Compare compareFilter = new Compare.Equal("templateContrato", true);
		filters.add(compareFilter);

		DefaultManyToOneComboModel<Documento> templateModel 
		= new DefaultManyToOneComboModel<Documento>(DocumentoListController.class, this.documentoDAO, super.getMainController(), filters);

		/**
		 * Ajustes para receber os dados de Solicitação de Serviço pegando os
		 * StatusSolicitação para aparecer no ComboBox
		 */

		this.subView.getCmbSolicitacaoServico().configuraCombo(
				"contratoTipoServico.nome", ContratoSolicitacaoServicoListController.class, this.solicitacaoServicoDAO, this.getMainController());

		subView.getCbmPessoa().setModel(pessoaModel);
		subView.getCbmTipoContrato().setModel(tipoContratoModel);
		subView.getCbmDocumento().setModel(templateModel);

		/*
		 * List<UfEntity> ufs = templateUF.getAll();
		 * 
		 * for (UfEntity uf : ufs) { subView.getCmbEstadoObjeto().addItem(uf); }
		 */

		subView.getTxtValor().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {
				subView.getTxtValor().setConvertedValue(subView.getTxtValor().getConvertedValue());

			}

		});

		// carregarCombos();
		StreamResource myResource = createResource();

		if (myResource != null) {
			FileDownloader fileDownloader = new FileDownloader(myResource);
			fileDownloader.extend(subView.getBtnGerarContrato());
		}
		subView.getBtnGerarContrato().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!podeGerarContrato()) {
					mensagemErro("Erro ao gerar contrato, preencha os dados corretamente.");
				}
			}
		});
	}

	public StreamResource createResource() {

		return new StreamResource(new StreamSource() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private MaskFormatter formatoCnpj;
			private MaskFormatter formatoCpf;

			@Override
			public InputStream getStream() {

				if (podeGerarContrato()) {
					try {
						formatoCnpj = new MaskFormatter("##.###.###/####-##");
						formatoCpf = new MaskFormatter("###.###.###-##");
						formatoCnpj.setValueContainsLiteralCharacters(false);
						formatoCpf.setValueContainsLiteralCharacters(false);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					Documento documento = (Documento) subView.getCbmDocumento().getValue();
					PessoaEnderecoEntity enderecoEmpresaContratada = new PessoaEnderecoEntity();
					PessoaEntity dadosContratante = subView.getCbmPessoa().getValue();
					PessoaEnderecoEntity enderecoEmpresaContratante = new PessoaEnderecoEntity();
					// SolicitacaoServicoEntity solicitacaoServico =
					// subView.getCmbSolicitacaoServico().getValue();

					if (documento != null) {
						EmpresaEntity empresa = documento.getEmpresa();
						empresa = empresaDAO.find(empresa.getId());

						List<PessoaEnderecoEntity> pessoaEnderecoList = pessoaEnderecoDAO.getPessoaEnderecoList(empresa);

						if (pessoaEnderecoList != null && !pessoaEnderecoList.isEmpty()) {
							enderecoEmpresaContratada = pessoaEnderecoList.get(0);
						}

						pessoaEnderecoList = pessoaEnderecoDAO.getPessoaEnderecoList(dadosContratante);

						if (pessoaEnderecoList != null && !pessoaEnderecoList.isEmpty()) {
							enderecoEmpresaContratante = pessoaEnderecoList.get(0);
						}

						ByteArrayOutputStream bos = new ByteArrayOutputStream();

						try {
							File origem = getFileFromDocumento(documento);
							if (origem != null) {
								// define os termos a serem substituidos
								String termos[] = new String[] {
										// contratada
										"#CONTRATADA#", "#CNPJ_CONTRATADA#", "#ENDERECO_CONTRATADA#", "#CIDADE_CONTRATADA#", "#UF_CONTRATADA#", "#BAIRRO_CONTRATADA#", "#CEP_CONTRATADA#",
										// contratante
										"#CONTRATANTE#", "#CNPJ_CONTRATANTE#", "CPF_CONTRATANTE", "#ENDERECO_CONTRATANTE#", "#CIDADE_CONTRATANTE#", "#UF_CONTRATANTE#", "#BAIRRO_CONTRATANTE#",
										"#CEP_CONTRATANTE#",
										// outros
										"#VALOR_MENSAL#", "#DATA_EXTENSO#" };

								MaskFormatter formatoCnpj = new MaskFormatter("##.###.###/####-##");
								formatoCnpj.setValueContainsLiteralCharacters(false);
								MaskFormatter formatoCpf = new MaskFormatter("###.###.###-##");
								formatoCpf.setValueContainsLiteralCharacters(false);
								SimpleDateFormat formatoDataExtenso = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");

								// busca os dados para substituicoes dos termos
								String substituicoes[] = new String[] {
										// contratada
										empresa.getRazaoSocial()/* "#CONTRATADA#" */,
										formatoCnpj.valueToString(empresa.getCnpj())/* "#CNPJ_CONTRATADA#" */,
										enderecoEmpresaContratada.getLogradouro() + " " + enderecoEmpresaContratada.getNumero() + " "
												+ (enderecoEmpresaContratada.getComplemento() == null ? "" : enderecoEmpresaContratada.getComplemento())/* "#ENDERECO_CONTRATADA#" */,

										enderecoEmpresaContratada.getCidade()/* "#CIDADE_CONTRATADA#" */,
										enderecoEmpresaContratada.getUf() != null ? enderecoEmpresaContratada.getUf().getNome() : ""/* "#UF_CONTRATADA#" */,
										enderecoEmpresaContratada.getBairro()/* "#BAIRRO_CONTRATADA#" */,
										enderecoEmpresaContratada.getCep()/* "#CEP_CONTRATADA#" */,
										// contratante
										dadosContratante.getNome()/* "#CONTRATANTE#" */,
										getCNPJ(dadosContratante)/* "#CNPJ_CONTRATANTE#" */,
										getCPF(dadosContratante),// "#CPF_CONTRATANTE#"
										enderecoEmpresaContratante.getLogradouro() + " " + enderecoEmpresaContratante.getNumero() + " "
												+ (enderecoEmpresaContratante.getComplemento() == null ? "" : enderecoEmpresaContratante.getComplemento())/* "#ENDERECO_CONTRATANTE#" */,
										enderecoEmpresaContratante.getCidade() /* "#CIDADE_CONTRATANTE#" */,
										enderecoEmpresaContratante.getUf() != null ? enderecoEmpresaContratante.getUf().getNome() : "" /* "#UF_CONTRATANTE#" */, // dadosContratante.getUf(),
										enderecoEmpresaContratante.getBairro() /* "#BAIRRO_CONTRATANTE#" */, enderecoEmpresaContratante.getCep() /* "#CEP_CONTRATANTE#" */,
										// outros
										subView.getTxtValor().getValue()/* "#VALOR_MENSAL#" */, formatoDataExtenso.format(subView.getDtVigencia().getValue()) /* "#DATA_EXTENSO#" */
								};

								XWPFDocument doc = new XWPFDocument(new FileInputStream(origem));

								List<XWPFParagraph> paragrafos = new ArrayList<XWPFParagraph>(doc.getParagraphs());

								for (XWPFParagraph p : paragrafos) {
									for (XWPFRun r : p.getRuns()) {
										for (CTText ctText : r.getCTR().getTList()) {
											for (int i = 0; i < termos.length; i++) {
												String termo = termos[i];
												String substituir = substituicoes[i];

												if (substituir == null) {
													substituir = "";
												}

												String str = null;
												if (ctText.getStringValue().contains(termo)) {
													str = ctText.getStringValue().replace(termo, substituir);
													ctText.setStringValue(str);
												}
											}
										}

									}
								}

								doc.write(bos);

								String nomeArquivo = getDiretorio() + "contrato.docx";
								File arquivoContratoGerado = Util.gravarArquivo(nomeArquivo, bos.toByteArray());
								subView.uploadArquivo(arquivoContratoGerado, nomeArquivo, "", bos.toByteArray().length);

								return new ByteArrayInputStream(bos.toByteArray());
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								bos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					return null;
				}
				return null;
			}

			private String getCPF(PessoaEntity dadosContratante) {

				try {
					return formatoCpf.valueToString(dadosContratante.getPessoaFisica().getCpf());
				} catch (Exception e) {
				}
				return "";
			}

			private String getCNPJ(PessoaEntity dadosContratante) {
				try {
					return formatoCnpj.valueToString(dadosContratante.getPessoaJuridica().getCnpj());
				} catch (Exception e) {
				}
				return "";
			}
		}, "contrato.docx");
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contratoDAO.find(id);

		subView.preencheContratoForm(currentBean);

	}

	@Override
	protected void actionSalvar() {

		try {
			subView.preencheContrato(currentBean);

			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());

			// boolean valido = true;

			// currentBean.setContabilConta(subView.getCbmContabilConta().getValue());
			List<String> listArquivos = subView.getListArquivos();
			List<String> listArquivosExcluidos = subView.getListArquivosExcluidos();

			
			
			if (currentBean.getDocumento() != null) {
				Documento documento = currentBean.getDocumento();
				TipoDocumento tipoDocumento = documentoBusiness.findTipoDocumento("Contrato");
				if(tipoDocumento == null){
					mensagemAtencao("Crie um tipo de documento chamado \"Contrato\"");
					return;
				}
				documento.setTipoDocumento(tipoDocumento);
				documento.setTemplateContrato(false);
				documento.setDescricao(currentBean.getDescricao());
				documentoBusiness.gravarAnexo(documento, listArquivos, listArquivosExcluidos, null, "");
			}
			contratoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		// carregarCombos();
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Contrato";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contratoDAO.deleteAllByIds(ids);
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	public ContratoEntity getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(ContratoEntity currentBean) {
		this.currentBean = currentBean;
	}

	public HistFaturamentoEntity novoContratoHistFaturamento() {
		HistFaturamentoEntity contratoHistFaturamento = new HistFaturamentoEntity();
		this.currentBean.addContratoHistFaturamento(contratoHistFaturamento);

		return contratoHistFaturamento;
	}

	public void removerContratoHistFaturamento(List<HistFaturamentoEntity> values) {
		for (HistFaturamentoEntity contratoHistFaturamento : values) {
			this.currentBean.removeContratoHistFaturamento(contratoHistFaturamento);
		}

		mensagemRemovidoOK();

	}

	public ContratoProduto novoContratoProduto() {
		ContratoProduto contratoProduto = new ContratoProduto();
		this.currentBean.addContratoHistFaturamento(contratoProduto);

		return contratoProduto;
	}

	public void removerContratoProduto(List<ContratoProduto> values) {
		for (ContratoProduto contrProduto : values) {
			this.currentBean.removeContratoProduto(contrProduto);
		}

		mensagemRemovidoOK();

	}

	public PrevFaturamentoEntity novoContratoPrevFaturamento(PrevFaturamentoEntity contratoPreFaturamento) {
		PrevFaturamentoEntity contratoPrevFaturamento = new PrevFaturamentoEntity();
		this.currentBean.addContratoPrevFaturamento(contratoPrevFaturamento);

		return contratoPrevFaturamento;
	}

	public void removerContratoPrevFaturamento(List<PrevFaturamentoEntity> values) {
		for (PrevFaturamentoEntity contratoPrevFaturamento : values) {
			this.currentBean.removeContratoPrevFaturamento(contratoPrevFaturamento);
		}

		mensagemRemovidoOK();
	}

	public HistoricoReajusteEntity novoContratoHistoricoReajuste() {
		HistoricoReajusteEntity contratoHistoricoReajuste = new HistoricoReajusteEntity();
		this.currentBean.addContratoHistoricoReajuste(contratoHistoricoReajuste);

		return contratoHistoricoReajuste;
	}

	public void removerContratoHistoricoReajuste(List<HistoricoReajusteEntity> values) {
		for (HistoricoReajusteEntity contratoHistoricoReajuste : values) {
			this.currentBean.removeContratoHistoricoReajuste(contratoHistoricoReajuste);
		}

		mensagemRemovidoOK();
	}

	public void gerarParcelas() throws Exception {
		if (validaCampos()) {
			final PessoaEntity pessoa = (PessoaEntity) subView.getCbmPessoa().getValue();

			if (pessoa == null || pessoa.getId() == null) {
				throw new Exception("É necessário informar a pessoa para previsão das parcelas.");
			}

			final List<PrevFaturamentoEntity> contratoprevFaturamento = new ArrayList<PrevFaturamentoEntity>();

			List<PrevFaturamentoEntity> dados = subView.getPrevisaoFaturamentoSubForm().getDados();
			// Integer i = (Integer)
			// (!subView.getTxtIntervaloParcelas().getValue().equals("") ? new
			// Integer(0) : subView.getTxtIntervaloParcelas().getValue());

			if (dados != null) {
				contratoprevFaturamento.addAll(subView.getPrevisaoFaturamentoSubForm().getDados());
			}

			if (contratoprevFaturamento != null && !contratoprevFaturamento.isEmpty()) {
				ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção", "As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(contratoprevFaturamento);
									// geraParcelas(contabilConta,
									// parcelasPagar);
									geraParcelas(pessoa, contratoprevFaturamento);
								}
							}

						});
			} else {
				// geraParcelas(contabilConta, parcelasPagar);
				geraParcelas(pessoa, contratoprevFaturamento);
			}
		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}
	}

	/** Wesley Jr gera as Parcelas */

	private void geraParcelas(PessoaEntity pessoa, final List<PrevFaturamentoEntity> contratopreFaturamento) {
		subView.getPrevisaoFaturamentoSubForm().removeAllItems();
		subView.preencheContrato(currentBean);

		ContratoEntity contrato = currentBean;
		PrevFaturamentoEntity contratoPrevFaturamento;
		// List<ContratoPrevFaturamento> dados =
		// subView.buildPrevisaoFaturamentoSubForm().getDados();
		Date dataPrevista = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(contrato.getDataInicioVigencia());
		BigDecimal valorParcela = contrato.getValor().divide(BigDecimal.valueOf(contrato.getQuantidadeParcelas()), RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		// String nossoNumero = null;
		// DecimalFormat formatoNossoNumero = new DecimalFormat("0000");
		// DecimalFormat formatoNossoNumero1 = new DecimalFormat("00000");
		// SimpleDateFormat formatoNossoNumero2 = new SimpleDateFormat("D");
		// Date dataAtual = new Date();

		for (int i = 0; i < contrato.getQuantidadeParcelas(); i++) {
			contratoPrevFaturamento = new PrevFaturamentoEntity();
			contratoPrevFaturamento.setPessoa(pessoa);
			// contrato.setContabilConta(contabilConta);
			// contrato.setQuantidadeParcelas(i + 1);
			contratoPrevFaturamento.setNumeroParcela(i + 1);
			contratoPrevFaturamento.setDataPrevista(dataPrevista);

			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, contrato.getIntervaloEntreParcelas());
			}
			// contratoPrevFaturamento.setDataVencimento(primeiroVencimento.getTime());
			contratoPrevFaturamento.setDataPrevista(primeiroVencimento.getTime());

			// nossoNumero +=
			// formatoNossoNumero1.format(contratoPrevFaturamento.getPessoa().getId());
			// nossoNumero +=
			// formatoNossoNumero.format(contratoPrevFaturamento.getNumeroParcela());
			// nossoNumero += formatoNossoNumero2.format(dataAtual);

			contratoPrevFaturamento.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);

			contratopreFaturamento.add(contratoPrevFaturamento);
			novoContrato(contratoPrevFaturamento);
			// novoContratoPrevFaturamento(contratoPrevFaturamento);
		}

		residuo = contrato.getValor().subtract(somaParcelas);
		valorParcela = valorParcela.add(residuo);
		if (residuo.compareTo(BigDecimal.ZERO) != 0) {
			contratopreFaturamento.get(contratopreFaturamento.size() - 1).setValor(valorParcela);
		}
		// subView.getPrevisaoFaturamentoSubForm().fillWith(parcelasPagar);
		subView.getPrevisaoFaturamentoSubForm().fillWith(contratopreFaturamento);
	}

	private void excluiParcelas(List<PrevFaturamentoEntity> contratoPrevFaturamento) {
		List<PrevFaturamentoEntity> persistentObjects = subView.getPrevisaoFaturamentoSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			delete(persistentObjects.get(i));
		}

		contratoPrevFaturamento.clear();
	}

	public void delete(PrevFaturamentoEntity contratoPrevFaturamento) {
		contratoDAO.delete(contratoPrevFaturamento);
	}

	public PrevFaturamentoEntity novoContratoPrevFaturamento() {
		PrevFaturamentoEntity contratoPreFaturamento = new PrevFaturamentoEntity();

		return novoContratoPrevFaturamento(contratoPreFaturamento);
	}

	public PrevFaturamentoEntity novoContrato(PrevFaturamentoEntity contratoPreFaturamento) {
		currentBean.addParcela(contratoPreFaturamento);

		return contratoPreFaturamento;
	}

	public void removerParcelaPagar(List<PrevFaturamentoEntity> values) {
		for (PrevFaturamentoEntity value : values) {
			currentBean.removeParcela(value);
		}
	}

	private BigDecimal getTotal(List<PrevFaturamentoEntity> contratoPrevFaturamento) {
		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < contratoPrevFaturamento.size(); i++) {
			total = total.add(contratoPrevFaturamento.get(i).getValor());
		}

		return total.setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	private static File getFileFromDocumento(Documento documento) {
		File arquivo = null;
		if (documento != null && documento.getDocumentos() != null) {
			for (DocumentoArquivo documentoArquivo : documento.getDocumentos()) {
				if (DocumentoBusinessImpl.getExtensao(documentoArquivo.getFile().getName()).contains("doc")) {
					return documentoArquivo.getFile();
				}
			}
		}
		return arquivo;
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	public List<ProdutoEntity> getProdutos() {
		return produtoDAO.getAll();
	}

	@Override
	public ContratoEntity getModelBean() {
		return currentBean;
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	private boolean podeGerarContrato() {

		Documento documento = (Documento) subView.getCbmDocumento().getValue();
		PessoaEnderecoEntity enderecoEmpresaContratada = new PessoaEnderecoEntity();
		PessoaEntity dadosContratante = subView.getCbmPessoa().getValue();
		SolicitacaoServicoEntity solicitacaoServico = subView.getCmbSolicitacaoServico().getValue();
		String valor = subView.getTxtValor().getValue();
		PopupDateField dtVigencia = subView.getDtVigencia();

		return documento != null && enderecoEmpresaContratada != null && dadosContratante != null && solicitacaoServico != null && valor != null && dtVigencia != null;
	}

	public File gravaArquivoTemporario(File arquivo, String nomeArquivo) {

		return documentoBusiness.gravaArquivoTemporario(arquivo, nomeArquivo, currentBean.getDocumento());
	}

	public boolean isArquivoTemporario(File arquivo) {
		return documentoBusiness.isArquivoTemporario(arquivo, currentBean.getDocumento());
	}

	public String getDiretorio() {
		return documentoBusiness.getDiretorio(currentBean.getDocumento());
	}

}