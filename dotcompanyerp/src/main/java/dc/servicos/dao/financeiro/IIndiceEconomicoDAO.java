package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.model.dao.AbstractDAO;

public interface IIndiceEconomicoDAO extends AbstractDAO<IndiceEconomicoEntity> {

	List<IndiceEconomicoEntity> procuraNomeContendo(String valor);

}