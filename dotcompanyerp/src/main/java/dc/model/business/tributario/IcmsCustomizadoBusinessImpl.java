package dc.model.business.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.dao.tributario.IIcmsCustomizadoDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class IcmsCustomizadoBusinessImpl implements Serializable,
		IcmsCustomizadoBusiness<IcmsCustomizadoCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(IcmsCustomizadoBusinessImpl.class);

	@Autowired
	private IIcmsCustomizadoDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<IcmsCustomizadoCabecalhoEntity> getEntityClass() {
		return IcmsCustomizadoCabecalhoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(IcmsCustomizadoCabecalhoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] deleteAll");

			this.dao.deleteAllByIds(list);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public IcmsCustomizadoCabecalhoEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			IcmsCustomizadoCabecalhoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public IcmsCustomizadoCabecalhoEntity find(IcmsCustomizadoCabecalhoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<IcmsCustomizadoCabecalhoEntity> auxLista = this.dao
					.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> findAll(
			IcmsCustomizadoCabecalhoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> fullTextSearch(String valor,
			String[] sortingFields, boolean[] states, List<Filter> filters)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public void save(IcmsCustomizadoCabecalhoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			IcmsCustomizadoCabecalhoEntity ent = (IcmsCustomizadoCabecalhoEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> getAllForComboSelect(
			Class<IcmsCustomizadoCabecalhoEntity> type, int idEmpresa,
			FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> getAllForCombo(
			Class<IcmsCustomizadoCabecalhoEntity> type, int idEmpresa,
			FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Override
	public List<IcmsCustomizadoCabecalhoEntity> list() throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] list");

			List<IcmsCustomizadoCabecalhoEntity> auxLista = this.dao.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}