package dc.model.business.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.diverso.IPaisDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class UfBusinessImpl implements Serializable, UfBusiness<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UfBusinessImpl.class);

	@Autowired
	private IUfDAO dao;

	@Autowired
	private IPaisDAO paisDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<UfEntity> getEntityClass() {
		return UfEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(UfEntity t) throws Exception {
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
	public UfEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			UfEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public UfEntity find(UfEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<UfEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<UfEntity> findAll(UfEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor, int first, int pageSize,
			String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor, String[] sortingFields,
			boolean[] states, List<Filter> filters) throws Exception {
		return dao.fullTextSearch(valor, sortingFields, states, filters);
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		return dao.getAll(type);
	}

	@Transactional(readOnly = false)
	@Override
	public void save(UfEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			this.dao.saveOrUpdate(o);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<UfEntity> getAllForComboSelect(Class<UfEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<UfEntity> getAllForCombo(Class<UfEntity> type, int idEmpresa,
			FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<UfEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	/**
	 * 
	 */

	@Transactional
	public UfEntity getObject(String sigla) throws Exception {
		try {
			UfEntity entity = this.dao.getObject(sigla);

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

}