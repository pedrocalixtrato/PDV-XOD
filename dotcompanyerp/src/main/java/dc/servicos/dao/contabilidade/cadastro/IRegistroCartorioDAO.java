package dc.servicos.dao.contabilidade.cadastro;

import java.util.List;

import dc.entidade.contabilidade.cadastro.RegistroCartorioEntity;
import dc.model.dao.AbstractDAO;

public interface IRegistroCartorioDAO extends AbstractDAO<RegistroCartorioEntity>{

	List<RegistroCartorioEntity> procuraNomeContendo(String valor);

}