package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.EncerramentoExeDetEntity;
import dc.model.dao.AbstractDAO;

public interface IEncerramentoExeDetDAO extends AbstractDAO<EncerramentoExeDetEntity> {

	List<EncerramentoExeDetEntity> procuraNomeContendo(String valor);

}