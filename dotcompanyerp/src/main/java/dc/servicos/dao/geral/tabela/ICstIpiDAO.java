package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.CstIpiEntity;
import dc.model.dao.AbstractDAO;

public interface ICstIpiDAO extends AbstractDAO<CstIpiEntity> {

	public abstract CstIpiEntity procuraPorCodigo(String codigo);

}