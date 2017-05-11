package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.model.dao.AbstractDAO;

public interface IHistoricoDAO extends AbstractDAO<HistoricoEntity>{

	List<HistoricoEntity> procuraNomeContendo(String valor);

}