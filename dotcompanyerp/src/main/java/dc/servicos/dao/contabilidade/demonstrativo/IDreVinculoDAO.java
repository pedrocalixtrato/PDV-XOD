package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.DreVinculoEntity;
import dc.model.dao.AbstractDAO;

public interface IDreVinculoDAO extends AbstractDAO<DreVinculoEntity>{

	List<DreVinculoEntity> procuraNomeContendo(String valor);

}