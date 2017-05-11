package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.model.dao.AbstractDAO;

public interface IEncerramentoExeCabDAO extends AbstractDAO<EncerramentoExeCabEntity> {

	List<EncerramentoExeCabEntity> procuraNomeContendo(String valor);

}