package dc.servicos.dao.sistema;

import java.util.List;

import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.framework.PapelMenu;
import dc.model.dao.AbstractDAO;

public interface IPapelDAO extends AbstractDAO<PapelEntity>{

	public abstract dc.entidade.framework.PapelMenu getPapelMenuByPapelAndMenuID(Integer idPapel, Integer idMenu);

	public abstract dc.entidade.framework.PapelMenu getPapelMenuByPapelAndMenuControllerClass(Integer idPapel, String controllerClass);

	public abstract List<PapelMenu> getPapelMenusOrdered(PapelEntity p);
	

}