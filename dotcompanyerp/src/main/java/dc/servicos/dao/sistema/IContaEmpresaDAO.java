package dc.servicos.dao.sistema;

import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.entidade.sistema.ContaEmpresa;
import dc.model.dao.AbstractDAO;

public interface IContaEmpresaDAO extends AbstractDAO<ContaEmpresa>{

	public abstract ConfiguracaoContaEmpresa findConfiguracaoByIdConta(Integer contaId);

	public abstract ContaEmpresa findByEmail(String email);

	public abstract ConfiguracaoContaEmpresa findConfiguracaoByIdContaWithModules(Integer contaId);

}