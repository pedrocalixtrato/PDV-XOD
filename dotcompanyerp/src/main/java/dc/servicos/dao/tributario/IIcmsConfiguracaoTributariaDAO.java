package dc.servicos.dao.tributario;

import java.util.List;

import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.IcmsConfiguracaoTributariaEntity;
import dc.model.dao.AbstractDAO;

public interface IIcmsConfiguracaoTributariaDAO extends AbstractDAO<IcmsConfiguracaoTributariaEntity>{

	public abstract List<IcmsConfiguracaoTributariaEntity> buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao);

}