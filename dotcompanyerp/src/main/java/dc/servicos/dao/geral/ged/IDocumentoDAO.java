package dc.servicos.dao.geral.ged;

import java.util.List;

import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.model.dao.AbstractDAO;

public interface IDocumentoDAO extends AbstractDAO<Documento>{

	public abstract void saveOrUpdate(VersaoDocumento versao);

	public abstract Integer nextVersionNumber(Documento documento);

	public abstract void deleteDocumentoArquivo(DocumentoArquivo documentoArquivo);

	public abstract <Documento> List<Documento> getAll(Class<Documento> type);

}