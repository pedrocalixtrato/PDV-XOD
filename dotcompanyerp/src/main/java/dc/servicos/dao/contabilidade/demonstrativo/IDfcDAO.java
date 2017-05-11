package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.DfcEntity;
import dc.model.dao.AbstractDAO;

public interface IDfcDAO extends AbstractDAO<DfcEntity> {

	List<DfcEntity> procuraNomeContendo(String valor);


}