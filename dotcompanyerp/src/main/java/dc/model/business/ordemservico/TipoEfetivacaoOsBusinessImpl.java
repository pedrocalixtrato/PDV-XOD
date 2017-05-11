package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.TipoEfetivacaoOsEntity;
import dc.model.dao.ordemservico.TipoEfetivacaoOsDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class TipoEfetivacaoOsBusinessImpl implements Serializable,
TipoEfetivacaoOsBusiness<TipoEfetivacaoOsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(TipoEfetivacaoOsBusinessImpl.class);

	@Autowired
	private TipoEfetivacaoOsDAO<TipoEfetivacaoOsEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<TipoEfetivacaoOsEntity> getEntityClass() {
		return TipoEfetivacaoOsEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(TipoEfetivacaoOsEntity t) throws Exception {
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
	public TipoEfetivacaoOsEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			TipoEfetivacaoOsEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public TipoEfetivacaoOsEntity find(TipoEfetivacaoOsEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoEfetivacaoOsEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoEfetivacaoOsEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<TipoEfetivacaoOsEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<TipoEfetivacaoOsEntity> findAll(TipoEfetivacaoOsEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoEfetivacaoOsEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoEfetivacaoOsEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoEfetivacaoOsEntity> fullTextSearch(String valor,
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
	public void save(TipoEfetivacaoOsEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			TipoEfetivacaoOsEntity ent = (TipoEfetivacaoOsEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<TipoEfetivacaoOsEntity> getAllForComboSelect(Class<TipoEfetivacaoOsEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<TipoEfetivacaoOsEntity> getAllForCombo(Class<TipoEfetivacaoOsEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<TipoEfetivacaoOsEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Override
	public TipoEfetivacaoOsEntity findByCodigo(Integer codigo) throws Exception{
		try {
			
			TipoEfetivacaoOsEntity ent = this.dao.findByCodigo(codigo);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}