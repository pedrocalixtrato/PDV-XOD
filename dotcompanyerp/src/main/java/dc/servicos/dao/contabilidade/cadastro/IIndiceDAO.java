package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.model.dao.AbstractDAO;

public interface IIndiceDAO extends AbstractDAO<IndiceEntity>{

	List<IndiceEntity> procuraNomeContendo(String valor);

}