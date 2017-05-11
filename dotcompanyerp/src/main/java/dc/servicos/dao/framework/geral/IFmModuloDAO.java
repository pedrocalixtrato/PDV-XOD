package dc.servicos.dao.framework.geral;

import java.util.List;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmModulo;
import dc.model.dao.AbstractDAO;

public interface IFmModuloDAO extends AbstractDAO<FmModulo> {

	public abstract List<FmModulo> getAllOrderedByCaption();

	public abstract List<FmModulo> getAllByUserIdOrderedByCaption(int user_id);

	public abstract List<FmModulo> getModulosSelecionaveis();

	public abstract List<FmModulo> getModulosObrigatorios();

	public abstract List<FmModulo> getModuloLista(UsuarioEntity usuario);

	public abstract Integer getIdModuloAdmObrigatorio();

	public abstract void setIdModuloAdmObrigatorio(Integer idModuloAdmObrigatorio);

	public abstract Integer getIdModuloSistemaObrigatorio();

	public abstract void setIdModuloSistemaObrigatorio(Integer idModuloSistemaObrigatorio);

}