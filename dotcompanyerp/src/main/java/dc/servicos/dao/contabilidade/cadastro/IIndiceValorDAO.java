package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.model.dao.AbstractDAO;

public interface IIndiceValorDAO extends AbstractDAO<IndiceValorEntity>{

	List<IndiceValorEntity> procuraNomeContendo(String valor);

}