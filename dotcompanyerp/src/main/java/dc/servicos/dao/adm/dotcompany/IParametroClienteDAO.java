package dc.servicos.dao.adm.dotcompany;

import java.util.List;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.model.dao.AbstractDAO;

public interface IParametroClienteDAO extends AbstractDAO<ParametroCliente>{

	public abstract List<ParametroCliente> buscarOsParametro(ParametroCliente cliente);

}