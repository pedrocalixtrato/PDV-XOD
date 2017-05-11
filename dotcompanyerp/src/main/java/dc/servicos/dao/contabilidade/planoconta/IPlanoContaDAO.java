package dc.servicos.dao.contabilidade.planoconta;

import java.util.List;

import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.model.dao.AbstractDAO;

public interface IPlanoContaDAO extends AbstractDAO<PlanoContaEntity>{

	List<PlanoContaEntity> procuraNomeContendo(String valor);

}