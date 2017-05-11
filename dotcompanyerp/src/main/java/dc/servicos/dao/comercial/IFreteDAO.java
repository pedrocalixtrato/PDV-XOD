package dc.servicos.dao.comercial;

import java.util.List;

import dc.entidade.comercial.Frete;
import dc.model.dao.AbstractDAO;

public interface IFreteDAO extends AbstractDAO<Frete> {

	List<Frete> procuraNomeContendo(String valor);

}