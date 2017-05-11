package dc.model.dao.tributario;

import java.util.List;

import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.dao.AbstractDAO;

public interface IIcmsCustomizadoDAO extends AbstractDAO<IcmsCustomizadoCabecalhoEntity> {

	public List<IcmsCustomizadoCabecalhoEntity> list() throws Exception;

}