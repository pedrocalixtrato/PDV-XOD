package dc.servicos.dao.framework.geral;

import java.util.List;

import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.model.dao.AbstractDAO;

public interface IFmMenuDAO extends AbstractDAO<FmMenu>{

	public abstract Class<FmMenu> getEntityClass();

	public abstract String[] getDefaultSearchFields();

	public abstract List<FmModulo> getAllModulos();

	public abstract List<FmMenu> getAllMenusByModuleIdGrouped(Integer moduleID, Integer userID);

	public abstract List<FmMenu> getAllMenusByModuleIdGrouped(Integer moduleID);

	public abstract List<FmMenu> getAllMenusByModuleId(Integer moduleID);

	public abstract FmMenu getEntity(String controllerClass);

	public abstract List<FmMenu> getMenuLista(List lista, String nomeClasse);

}