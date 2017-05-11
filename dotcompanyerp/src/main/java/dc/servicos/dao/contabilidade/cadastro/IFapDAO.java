package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.FapEntity;
import dc.model.dao.AbstractDAO;

public interface IFapDAO extends AbstractDAO<FapEntity>{

	List<FapEntity> procuraNomeContendo(String valor);

}