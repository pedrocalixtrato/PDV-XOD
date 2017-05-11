package dc.model.business.ged;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.TipoDocumento;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.geral.ged.IDocumentoDAO;
import dc.servicos.dao.geral.ged.ITipoDocumentoDAO;
import dc.servicos.util.Util;
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("singleton")
public class DocumentoBusinessImpl implements DocumentoBusiness {

	@Autowired
	private IDocumentoDAO documentoDAO;
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	private String homePath = System.getProperty("user.home");
	private String customCompanyBaseFolder = "dc-erp";

	@Override
	public void gravarAnexo(Documento documento, List<String> listArquivos, List<String> listArquivosExcluidos, File certificado, String senhaCertificado) throws IOException {

		for (int i = 0; i < listArquivos.size(); i++) {
			String file = listArquivos.get(i);
			File tmpFile = new File(file);

			String hash = null;

			try {
				hash = Util.md5Arquivo(tmpFile.getAbsolutePath());
			} catch (Exception e) {
				hash = "0";
			}

			if (file.indexOf(hash) == -1 && !hash.equals("0")) {
				File arquivo = null;
				// if (isArquivoTemporario(tmpFile, documento)) {
				arquivo = gravaArquivoEmDisco(tmpFile, documento, hash);
				try {
					tmpFile.delete();
				} catch (Exception e) {

				}
				if (arquivo != null && arquivo.exists()) {
					if (documento.getAssinado() != null && documento.getAssinado()) {
						byte[] assinatura = Util.geraAssinaturaArquivo(Util.lerBytesArquivo(arquivo), certificado, senhaCertificado.toCharArray());
						// usa o array de bytes e salva
						Util.gravarArquivo(hash, assinatura);
					}
					// }
				} else {
					arquivo = tmpFile;
				}

				DocumentoArquivo doc = new DocumentoArquivo();
				doc.setCaminho(arquivo.getAbsolutePath());
				doc.setHash(hash);
				doc.setExtensaoArquivo(getExtensao(arquivo.getAbsolutePath()));

				doc.setDocumento(documento);
				documento.getDocumentos().add(doc);

				VersaoDocumento versao = verificaVersao(documento, doc);

				if (versao != null) {
					documentoDAO.saveOrUpdate(versao);
				}
			}
		}

		if (listArquivosExcluidos != null) {

			for (String arquivo : listArquivosExcluidos) {
				for (Iterator<DocumentoArquivo> iteratorDocumentos = documento.getDocumentos().iterator(); iteratorDocumentos.hasNext();) {
					DocumentoArquivo documentoArquivo = (DocumentoArquivo) iteratorDocumentos.next();
					if (documentoArquivo.getCaminho().equals(arquivo)) {
						iteratorDocumentos.remove();
						break;
					}
				}
				removeArquivo(arquivo);
			}
		}

		documentoDAO.saveOrUpdate(documento);
	}

	private File gravaArquivoEmDisco(File tmpFile, Documento documento, String hash) throws IOException {
		String caminho = getDiretorio(documento) + hash + getExtensao(tmpFile.getName());

		File arquivo = null;
		try {
			arquivo = gravarArquivo(caminho, Util.lerBytesArquivo(tmpFile));
		} catch (IOException e) {
		}
		return arquivo;

	}

	public String getDiretorio(Documento documento) {
		if (documento == null || documento.getId() == null) {
			return homePath + File.separator + customCompanyBaseFolder + File.separator + getIDEmpresa() + File.separator;
		} else {
			return homePath + File.separator + customCompanyBaseFolder + File.separator + getIDEmpresa() + File.separator + documento.getId() + File.separator;
		}
	}

	public VersaoDocumento verificaVersao(Documento documento, DocumentoArquivo doc) {
		VersaoDocumento versao = null;
		String acao = null;
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity colaborador = usuario.getColaborador();

		if (colaborador == null) {
			return null;
		}
		Documento original = documentoDAO.find(documento.getId());

		if (original.getDocumentos().size() > 0) {
			acao = "A";

			DocumentoArquivo arquivoOriginal = original.getDocumentos().iterator().next();

			if (!arquivoOriginal.getHash().equals(doc.getHash())) {

				versao = new VersaoDocumento();
				versao.setAcao(acao);
				versao.setCaminho(doc.getCaminho());
				versao.setDataHora(new Date());
				versao.setDocumento(documento);
				versao.setHashArquivo(doc.getHash());
				versao.setVersao(getProximoNumeroVersao(original));
				versao.setColaborador(colaborador);
			}
		} else {
			acao = "I";
			versao = new VersaoDocumento();
			versao.setAcao(acao);
			versao.setCaminho(doc.getCaminho());
			versao.setDataHora(new Date());
			versao.setDocumento(documento);
			versao.setHashArquivo(doc.getHash());
			versao.setColaborador(colaborador);
			versao.setVersao(1);
		}

		return versao;
	}

	public Integer getProximoNumeroVersao(Documento documento) {
		return documentoDAO.nextVersionNumber(documento);
	}

	public static String getExtensao(String caminho) {
		if (caminho != null && !caminho.isEmpty()) {
			int indiceExtensao = caminho.lastIndexOf(".");

			if (indiceExtensao > -1) {
				return caminho.substring(indiceExtensao, caminho.length());
			}
		}

		return "";
	}

	public void removeArquivo(String caminho) {
		File arquivo = new File(caminho);
		try {
			arquivo.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getIDEmpresa() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		return empresa.getId().toString();

	}

	@Override
	public File gravaArquivoTemporario(File arquivo, String nomeArquivo, Documento documento) {
		String diretorio = getDiretorio(documento);

		String nomeArquivoVisualizacao = diretorio + "/" + nomeArquivo;

		/*
		 * nomeArquivoVisualizacao = nomeArquivo;
		 * 
		 * if(!this.idDocumento.equals("")){ nomeArquivoVisualizacao = homePath
		 * + "/"+ customCompanyBaseFolder + "/" + this.idEmpresa + "/" +
		 * this.idDocumento+"/"+ nomeArquivoVisualizacao; } else{
		 * nomeArquivoVisualizacao = homePath + "/"+ customCompanyBaseFolder +
		 * "/" + this.idEmpresa + "/" + nomeArquivoVisualizacao; }
		 */

		File tmp = null;
		try {
			tmp = gravarArquivo(nomeArquivoVisualizacao, Util.lerBytesArquivo(arquivo));
		} catch (IOException e) {
		}
		return tmp;
	}

	private File gravarArquivo(String caminho, byte[] dados) throws IOException {
		File arquivo = new File(caminho);
		FileOutputStream fos = null;
		try {
			if (!arquivo.exists()) {
				File pastaPai = arquivo.getParentFile();
				if (pastaPai != null) {
					pastaPai.mkdirs();
					arquivo.createNewFile();
				}
			}
			fos = new FileOutputStream(arquivo);
			fos.write(dados);
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arquivo;

	}

	public boolean isArquivoTemporario(File arquivo, Documento documento) {
		if (documento.getId() == null) {
			return !arquivo.getAbsolutePath().contains(customCompanyBaseFolder);
		} else {
			return !arquivo.getAbsolutePath().contains(getDiretorio(documento));
		}
	}

	@Override
	public TipoDocumento findTipoDocumento(String valor) {
		List<TipoDocumento> tipoDocumento = tipoDocumentoDAO.fullTextSearch(valor);
		if (tipoDocumento.size() > 0) {
			return tipoDocumento.get(0);
		}
		return null;
	}

}
