package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.model.dao.AbstractDAO;

public interface IDocumentoOrigemDAO extends AbstractDAO<DocumentoOrigem>{

	public abstract List<LancamentoPagarEntity> listLancamentos(DocumentoOrigem documento);

}