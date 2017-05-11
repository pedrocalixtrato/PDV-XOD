
package dc.model.business.ged;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;

import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.TipoDocumento;

@org.springframework.stereotype.Component
@Scope("singleton")
public interface DocumentoBusiness {
	public void gravarAnexo(Documento documento, List<String> listArquivos, List<String> listArquivosExcluidos, File certificado, String senhaCertificado) throws IOException;

	public Integer getProximoNumeroVersao(Documento documento);
	public File gravaArquivoTemporario(File arquivo, String nomeArquivo, Documento currentBean);
	public String getDiretorio(Documento documento);
	public boolean isArquivoTemporario(File arquivo, Documento documento);

	public TipoDocumento findTipoDocumento(String string);
}
