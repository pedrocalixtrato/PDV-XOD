package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.model.dao.AbstractDAO;

public interface IEmpresaCnaeDAO extends AbstractDAO<EmpresaCnaeEntity>{

	public abstract List<EmpresaCnaeEntity> getCnaePrincipalList();

}