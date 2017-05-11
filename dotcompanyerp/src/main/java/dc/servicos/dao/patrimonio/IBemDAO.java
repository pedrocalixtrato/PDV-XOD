package dc.servicos.dao.patrimonio;

import java.util.List;

import dc.entidade.patrimonio.BemEntity;
import dc.model.dao.AbstractDAO;

public interface IBemDAO extends AbstractDAO<BemEntity>{

	public abstract List<BemEntity> bemLista();

}