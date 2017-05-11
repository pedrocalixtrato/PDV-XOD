package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import dc.entidade.framework.Fpas;
import dc.model.dao.AbstractDAO;

public interface IFpasDAO extends AbstractDAO<Fpas> {

	List<Fpas> getFpasList();

}
