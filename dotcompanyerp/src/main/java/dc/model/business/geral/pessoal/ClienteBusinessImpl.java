package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class ClienteBusinessImpl implements Serializable,
		ClienteBusiness<ClienteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ClienteBusinessImpl.class);

	@Autowired
	private IClienteDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<ClienteEntity> getEntityClass() {
		return ClienteEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(ClienteEntity t) throws Exception {
		
		dao.delete(t);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
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
	public ClienteEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			ClienteEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ClienteEntity findById(ClienteEntity t) {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			ClienteEntity ent = this.dao.findById(t);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ClienteEntity find(ClienteEntity t) throws Exception {
		return null;
	}

	@Override
	public List<ClienteEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<ClienteEntity> findAll() throws Exception {
		return null;
	}

	@Override
	public List<ClienteEntity> findAll(ClienteEntity t) throws Exception {
		return null;
	}

	@Override
	public List<ClienteEntity> fullTextSearch(String valor) throws Exception {
		
		return dao.fullTextSearch(valor);
	}

	@Override
	public List<ClienteEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		return null;
	}

	@Override
	public List<ClienteEntity> fullTextSearch(String valor,
			String[] sortingFields, boolean[] states, List<Filter> filters)
			throws Exception {
		return null;
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public void save(ClienteEntity t) throws Exception {
	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
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
	public List<ClienteEntity> getAllForComboSelect(Class<ClienteEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<ClienteEntity> getAllForCombo(Class<ClienteEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<ClienteEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}