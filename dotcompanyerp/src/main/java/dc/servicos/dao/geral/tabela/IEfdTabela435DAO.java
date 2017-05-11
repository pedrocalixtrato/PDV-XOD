package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.EfdTabela435Entity;
import dc.model.dao.AbstractDAO;

public interface IEfdTabela435DAO extends AbstractDAO<EfdTabela435Entity> {

	EfdTabela435Entity procuraPorCodigo(String codigo);

}