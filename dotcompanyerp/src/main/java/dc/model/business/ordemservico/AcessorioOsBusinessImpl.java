package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.AcessorioOsDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class AcessorioOsBusinessImpl implements Serializable,
		AcessorioOsBusiness<AcessorioOsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AcessorioOsBusinessImpl.class);

	@Autowired
	private AcessorioOsDAO<AcessorioOsEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<AcessorioOsEntity> getEntityClass() {
		return AcessorioOsEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(AcessorioOsEntity t) throws Exception {
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
	public AcessorioOsEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			AcessorioOsEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public AcessorioOsEntity find(AcessorioOsEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioOsEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioOsEntity> findAll() throws Exception {
		try {
			List<AcessorioOsEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AcessorioOsEntity> findByOrdemServico(OrdemServicoEntity t) {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			List<AcessorioOsEntity> auxLista = this.dao.findByOrdemServico(t);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AcessorioOsEntity> findAll(AcessorioOsEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioOsEntity> fullTextSearch(String valor) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioOsEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		return null;
	}

	@Override
	public List<AcessorioOsEntity> fullTextSearch(String valor,
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
	public void save(AcessorioOsEntity t) throws Exception {
	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			AcessorioOsEntity ent = (AcessorioOsEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AcessorioOsEntity> getAllForComboSelect(Class<AcessorioOsEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<AcessorioOsEntity> getAllForCombo(Class<AcessorioOsEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<AcessorioOsEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}