package dc.servicos.dao.tributario;

import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.IpiConfiguracaoTributariaEntity;
import dc.model.dao.AbstractDAO;

public interface IIpiConfiguracaoTributariaDAO extends AbstractDAO<IpiConfiguracaoTributariaEntity>{

	public abstract IpiConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao);

}