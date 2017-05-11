package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.dao.AbstractDAO;

public interface IClienteDAO extends AbstractDAO<ClienteEntity> {
	ClienteEntity findById(ClienteEntity t);
}