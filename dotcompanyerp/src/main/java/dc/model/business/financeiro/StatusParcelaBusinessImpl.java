package dc.model.business.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.StatusParcela;
import dc.entidade.framework.FmMenu;
import dc.servicos.dao.financeiro.StatusParcelaIDAO;

@Service
@Transactional(readOnly = true)
public class StatusParcelaBusinessImpl implements Serializable, StatusParcelaBusiness<StatusParcela> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(StatusParcelaBusinessImpl.class);

	@Autowired
	private StatusParcelaIDAO<StatusParcela> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(StatusParcela t) throws Exception {
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
	public StatusParcela find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			StatusParcela ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public StatusParcela find(StatusParcela t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusParcela> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusParcela> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<StatusParcela> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<StatusParcela> findAll(StatusParcela t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusParcela> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusParcela> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusParcela> fullTextSearch(String valor,
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
	public void save(StatusParcela t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			StatusParcela ent = (StatusParcela) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<StatusParcela> getAllForComboSelect(
			Class<StatusParcela> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<StatusParcela> getAllForCombo(
			Class<StatusParcela> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<StatusParcela> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}
