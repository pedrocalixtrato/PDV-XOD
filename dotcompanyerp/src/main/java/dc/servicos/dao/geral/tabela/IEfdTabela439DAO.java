package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.EfdTabela439Entity;
import dc.model.dao.AbstractDAO;

public interface IEfdTabela439DAO extends AbstractDAO<EfdTabela439Entity> {

	EfdTabela439Entity procuraPorCodigo(String codigo);

}