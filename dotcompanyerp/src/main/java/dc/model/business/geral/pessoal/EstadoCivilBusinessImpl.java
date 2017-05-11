package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.model.dao.geral.pessoal.IEstadoCivilDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class EstadoCivilBusinessImpl implements Serializable,
		EstadoCivilBusiness<EstadoCivilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(EstadoCivilBusinessImpl.class);

	@Autowired
	private IEstadoCivilDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<EstadoCivilEntity> getEntityClass() {
		return EstadoCivilEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(EstadoCivilEntity t) throws Exception {
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
	public EstadoCivilEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			EstadoCivilEntity ent = this.dao.find(id);
			
			Hibernate.initialize(ent.getEmpresa());
			Hibernate.initialize(ent.getPessoaFisicaList());

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public EstadoCivilEntity find(EstadoCivilEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> findAll(EstadoCivilEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstadoCivilEntity> fullTextSearch(String valor,
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
	public void save(EstadoCivilEntity t) throws Exception {
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
	public List<EstadoCivilEntity> getAllForComboSelect(
			Class<EstadoCivilEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<EstadoCivilEntity> getAllForCombo(
			Class<EstadoCivilEntity> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<EstadoCivilEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}