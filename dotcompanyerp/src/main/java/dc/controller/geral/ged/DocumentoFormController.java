package dc.controller.geral.ged;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.TipoDocumento;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.business.ged.DocumentoBusiness;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.geral.ged.IDocumentoDAO;
import dc.servicos.dao.geral.ged.ITipoDocumentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.CompanyFileHandler;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.geral.ged.DocumentoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class DocumentoFormController extends CRUDFormController<Documento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoFormView subView;

	@Autowired
	private IDocumentoDAO documentoDAO;

	@Autowired
	private DocumentoBusiness documentoBusiness;

	@Autowired
	private MainController mainController;

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;

	@Autowired
	private IColaboradorDAO colaboradorDAO;

	private Documento currentBean;

	@Autowired
	private CompanyFileHandler companyFileHandler;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtPalavraChave().getValue())) {
			adicionarErroDeValidacao(subView.getTxtPalavraChave(), "Não pode ficar em branco");
			valido = false;
		}
		TipoDocumento tipoDocumento = (TipoDocumento) subView.getCmbTipoDocumento().getValue();
		if (!Validator.validateObject(tipoDocumento)) {
			adicionarErroDeValidacao(subView.getCmbTipoDocumento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtFimVigencia())) {
			adicionarErroDeValidacao(subView.getDtFimVigencia(), "Não pode ficar em branco");
			valido = false;
		}

		if (subView.getCkbAssinado().getValue()) {
			File arquivoCertificado = (File) subView.getUpAssinatura().getValue();
			if (!Validator.validateObject(arquivoCertificado) || !arquivoCertificado.exists()) {
				adicionarErroDeValidacao(subView.getUpArquivo(), "Selecione o arquivo de certificado");
			}
		}

		if (subView.getCkbTemplate().getValue()) {
			List<String> listArquivos = subView.getListArquivos();

			boolean algumDoc = false;

			for (String file : listArquivos) {
				if (file.contains(".doc")) {
					algumDoc = true;
					break;
				}
			}
			
			if (!algumDoc) {
				adicionarErroDeValidacao(subView.getUpArquivo(), "Os templates de contrato devem ser no formato WORD com a extensão .doc");
			}
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Documento();
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
		subView.setListArquivos(new ArrayList<String>());
	}

	public String getIDEmpresa() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		return empresa.getId().toString();

	}

	@Override
	protected void initSubView() {
		subView = new DocumentoFormView(this);
		companyFileHandler = new CompanyFileHandler();
		DefaultManyToOneComboModel<TipoDocumento> model = new DefaultManyToOneComboModel<TipoDocumento>(TipoDocumentoListController.class, tipoDocumentoDAO, mainController);
		subView.getCmbTipoDocumento().setModel(model);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = documentoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtPalavraChave().setValue(currentBean.getPalavraChave());
		subView.getDtFimVigencia().setValue(currentBean.getDataFimVigencia());
		subView.getCkbPodeAlterar().setValue(currentBean.getPodeAlterar());
		subView.getCkbPodeExcluir().setValue(currentBean.getPodeExcluir());
		subView.getCkbAssinado().setValue(currentBean.getAssinado());
		subView.getCkbTemplate().setValue(currentBean.getTemplateContrato());
		subView.setListArquivos(new ArrayList<String>());

		List<DocumentoArquivo> listArquivos = currentBean.getDocumentos();

		subView.limparMiniaturas();
		for (int i = 0; i < listArquivos.size(); i++) {
			String arquivo = listArquivos.get(i).getCaminho();
			subView.uploadArquivo(new File(arquivo), new File(arquivo).getName(), "A", (i + 1));
		}

		subView.getCmbTipoDocumento().setValue(currentBean.getTipoDocumento());

	}

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setPalavraChave(subView.getTxtPalavraChave().getValue());
		currentBean.setDataFimVigencia(subView.getDtFimVigencia().getValue());
		currentBean.setTipoDocumento((TipoDocumento) subView.getCmbTipoDocumento().getValue());
		currentBean.setPodeAlterar(subView.getCkbPodeAlterar().getValue());
		currentBean.setPodeExcluir(subView.getCkbPodeExcluir().getValue());
		currentBean.setTemplateContrato(subView.getCkbTemplate().getValue());
		currentBean.setAssinado(false);

		try {

			Integer id = currentBean.getId();
			if (id == null) {
				id = new Integer(0);
			}

			documentoDAO.saveOrUpdate(currentBean);
			File certificado = (File) subView.getUpAssinatura().getValue();
			String senhaCertificado = subView.getPwSenhaCertificado().getValue();
			List<String> listArquivos = subView.getListArquivos();
			List<String> listArquivosExcluidos = subView.getListArquivosExcluidos();

			documentoBusiness.gravarAnexo(currentBean, listArquivos, listArquivosExcluidos, certificado, senhaCertificado);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Documento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity colaborador = usuario.getColaborador();

		for (Serializable id : ids) {

			Documento documento = documentoDAO.find(id);
			documento.setDataExclusao(new Date());
			documentoDAO.saveOrUpdate(documento);

			VersaoDocumento versao = new VersaoDocumento();
			versao.setAcao("E");
			versao.setCaminho(documento.getDocumentos().get(0).getCaminho());
			versao.setDataHora(new Date());
			versao.setDocumento(documento);
			versao.setHashArquivo(documento.getDocumentos().get(0).getHash());
			versao.setVersao(documentoBusiness.getProximoNumeroVersao(documento));
			versao.setColaborador(colaborador);
			documentoDAO.saveOrUpdate(versao);
		}

		mensagemRemovidoOK();
	}

	public void removeArquivo(String caminho) throws Exception {
		File arquivo = new File(caminho);
		try {
			arquivo.delete();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "documentoForm";
	}

	@Override
	public Documento getModelBean() {
		return currentBean;
	}

	public File gravaArquivoTemporario(File arquivo, String nomeArquivo) {

		return documentoBusiness.gravaArquivoTemporario(arquivo, nomeArquivo, currentBean);
	}

	public boolean isArquivoTemporario(File arquivo) {
		return documentoBusiness.isArquivoTemporario(arquivo, currentBean);
	}

	public String getDiretorio() {
		return documentoBusiness.getDiretorio(currentBean);
	}
}