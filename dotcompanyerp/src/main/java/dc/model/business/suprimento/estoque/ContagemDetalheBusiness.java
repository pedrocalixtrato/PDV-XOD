package dc.model.business.suprimento.estoque;

import java.util.List;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface ContagemDetalheBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {

	public List<T> list(ContagemCabecalhoEntity entity);

}