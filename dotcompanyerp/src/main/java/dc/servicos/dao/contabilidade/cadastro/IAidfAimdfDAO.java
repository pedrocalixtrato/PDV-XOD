package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.AidfAimdfEntity;
import dc.model.dao.AbstractDAO;

public interface IAidfAimdfDAO extends AbstractDAO<AidfAimdfEntity> {

	List<AidfAimdfEntity> procuraNomeContendo(String valor);

}