package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.EquipamentoEntity;
import dc.model.dao.ordemservico.IEquipamentoDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class EquipamentoBusinessImpl implements Serializable,
		EquipamentoBusiness<EquipamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(EquipamentoBusinessImpl.class);

	@Autowired
	private IEquipamentoDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<EquipamentoEntity> getEntityClass() {
		return EquipamentoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(EquipamentoEntity t) throws Exception {
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
	public EquipamentoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			EquipamentoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public EquipamentoEntity find(EquipamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipamentoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipamentoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<EquipamentoEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<EquipamentoEntity> findAll(EquipamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipamentoEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipamentoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipamentoEntity> fullTextSearch(String valor,
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
	public void save(EquipamentoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			EquipamentoEntity ent = (EquipamentoEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<EquipamentoEntity> getAllForComboSelect(Class<EquipamentoEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<EquipamentoEntity> getAllForCombo(Class<EquipamentoEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<EquipamentoEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}