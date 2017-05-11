package dc.model.dao.suprimento.estoque;

import java.util.List;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.model.dao.AbstractDAO;

public interface ContagemDetalheDAO<T> extends AbstractDAO<T> {

	public List<T> list(ContagemCabecalhoEntity entity);

}