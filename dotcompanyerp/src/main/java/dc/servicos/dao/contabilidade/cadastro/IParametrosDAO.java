package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.model.dao.AbstractDAO;

public interface IParametrosDAO extends AbstractDAO<ParametrosEntity> {

	List<ParametrosEntity> procuraNomeContendo(String valor);

}