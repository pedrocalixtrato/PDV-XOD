package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.CstPisEntity;
import dc.model.dao.AbstractDAO;

public interface ICstPisDAO extends AbstractDAO<CstPisEntity> {

	public abstract CstPisEntity procuraPorCodigo(String codigo);

}