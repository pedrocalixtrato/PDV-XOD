package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.AcessorioEntity;
import dc.model.dao.ordemservico.AcessorioDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class AcessorioBusinessImpl implements Serializable,
		AcessorioBusiness<AcessorioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AcessorioBusinessImpl.class);

	@Autowired
	private AcessorioDAO<AcessorioEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<AcessorioEntity> getEntityClass() {
		return AcessorioEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(AcessorioEntity t) throws Exception {
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
	public AcessorioEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			AcessorioEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public AcessorioEntity find(AcessorioEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioEntity> findAll() throws Exception {
		try {
			List<AcessorioEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AcessorioEntity> findAll(AcessorioEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioEntity> fullTextSearch(String valor) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioEntity> fullTextSearch(String valor,
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
	public void save(AcessorioEntity t) throws Exception {
	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			AcessorioEntity ent = (AcessorioEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AcessorioEntity> getAllForComboSelect(Class<AcessorioEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<AcessorioEntity> getAllForCombo(Class<AcessorioEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<AcessorioEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}