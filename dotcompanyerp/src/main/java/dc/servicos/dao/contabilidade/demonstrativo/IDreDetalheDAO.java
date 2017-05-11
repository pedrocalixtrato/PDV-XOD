package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.model.dao.AbstractDAO;

public interface IDreDetalheDAO extends AbstractDAO<DreDetalheEntity>{

	public abstract List<DreDetalheEntity> procuraNomeContendo(String query);

}