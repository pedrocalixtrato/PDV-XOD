package dc.model.business.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.model.dao.tributario.IGrupoTributarioDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class GrupoTributarioBusinessImpl implements Serializable,
		GrupoTributarioBusiness<GrupoTributarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(GrupoTributarioBusinessImpl.class);

	@Autowired
	private IGrupoTributarioDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<GrupoTributarioEntity> getEntityClass() {
		return GrupoTributarioEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(GrupoTributarioEntity t) throws Exception {
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
	public GrupoTributarioEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			GrupoTributarioEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public GrupoTributarioEntity find(GrupoTributarioEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoTributarioEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoTributarioEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<GrupoTributarioEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<GrupoTributarioEntity> findAll(GrupoTributarioEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoTributarioEntity> fullTextSearch(String valor) throws Exception {
		
		return dao.fullTextSearch(valor);
	}

	@Override
	public List<GrupoTributarioEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoTributarioEntity> fullTextSearch(String valor,
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
	public void save(GrupoTributarioEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			GrupoTributarioEntity ent = (GrupoTributarioEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<GrupoTributarioEntity> getAllForComboSelect(
			Class<GrupoTributarioEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<GrupoTributarioEntity> getAllForCombo(
			Class<GrupoTributarioEntity> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<GrupoTributarioEntity> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}