package dc.servicos.dao.framework.geral;

import java.util.List;

import dc.entidade.framework.PapelMenu;
import dc.entidade.framework.SeguimentoEntity;
import dc.model.dao.AbstractDAO;

public interface ISeguimentoDAO extends AbstractDAO<SeguimentoEntity> {

	public abstract List<PapelMenu> getSeguimentosMenusOrdered(SeguimentoEntity p);

}