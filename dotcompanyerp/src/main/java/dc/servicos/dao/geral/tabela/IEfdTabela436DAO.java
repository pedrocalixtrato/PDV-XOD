package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.EfdTabela436Entity;
import dc.model.dao.AbstractDAO;

public interface IEfdTabela436DAO extends AbstractDAO<EfdTabela436Entity> {

	EfdTabela436Entity procuraPorCodigo(String codigo);

}