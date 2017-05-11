package dc.servicos.dao.tributario;

import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.model.dao.AbstractDAO;

public interface IPisConfiguracaoTributariaDAO extends AbstractDAO<PisConfiguracaoTributariaEntity>{

	public abstract PisConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao);

}