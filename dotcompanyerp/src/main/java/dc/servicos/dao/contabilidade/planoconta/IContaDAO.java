package dc.servicos.dao.contabilidade.planoconta;

import java.util.List;

import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.model.dao.AbstractDAO;

public interface IContaDAO extends AbstractDAO<ContaEntity> {

	List<ContaEntity> procuraNomeContendo(String valor);

}