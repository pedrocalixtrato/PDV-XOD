package dc.model.business.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.produto.NcmEntity;
import dc.model.dao.geral.produto.NcmDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class NcmBusinessImpl implements Serializable, NcmBusiness<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(NcmBusinessImpl.class);

	@Autowired
	private NcmDAO<NcmEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<NcmEntity> getEntityClass() {
		return NcmEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(NcmEntity t) throws Exception {
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
	public NcmEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			NcmEntity ent = this.dao.find(id);
			
			Hibernate.initialize(ent.getEmpresa());
			Hibernate.initialize(ent.getProdutoList());

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public NcmEntity find(NcmEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NcmEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NcmEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<NcmEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<NcmEntity> findAll(NcmEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NcmEntity> fullTextSearch(String valor) throws Exception {
		
		return dao.fullTextSearch(valor);
	}

	@Override
	public List<NcmEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NcmEntity> fullTextSearch(String valor, String[] sortingFields,
			boolean[] states, List<Filter> filters) throws Exception {
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
	public void save(NcmEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			NcmEntity ent = (NcmEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<NcmEntity> getAllForComboSelect(Class<NcmEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<NcmEntity> getAllForCombo(Class<NcmEntity> type, int idEmpresa,
			FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<NcmEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}