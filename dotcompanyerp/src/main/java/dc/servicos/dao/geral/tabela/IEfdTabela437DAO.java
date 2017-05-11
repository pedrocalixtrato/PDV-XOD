package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.EfdTabela437Entity;
import dc.model.dao.AbstractDAO;

public interface IEfdTabela437DAO extends AbstractDAO<EfdTabela437Entity> {

	EfdTabela437Entity procuraPorCodigo(String codigo);

}