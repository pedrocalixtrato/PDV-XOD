package dc.servicos.dao.tributario;

import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.model.dao.AbstractDAO;

public interface ICofinsConfiguracaoTributariaDAO extends AbstractDAO<CofinsConfiguracaoTributariaEntity>{

	public abstract CofinsConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao);

}