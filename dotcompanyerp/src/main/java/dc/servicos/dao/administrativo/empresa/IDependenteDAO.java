package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import dc.entidade.administrativo.empresa.DependenteEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.model.dao.AbstractDAO;

public interface IDependenteDAO extends AbstractDAO<DependenteEntity> {

	List<DependenteEntity> findBySocio(SocioEntity socio);

}
