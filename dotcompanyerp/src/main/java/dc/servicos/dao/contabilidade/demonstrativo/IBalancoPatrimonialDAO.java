package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.BalancoPatrimonialEntity;
import dc.model.dao.AbstractDAO;

public interface IBalancoPatrimonialDAO extends AbstractDAO<BalancoPatrimonialEntity> {

	List<BalancoPatrimonialEntity> procuraNomeContendo(String valor);

}