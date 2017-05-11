package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.VendaPecaEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.VendaPecaDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class VendaPecaBusinessImpl implements Serializable,
VendaPecaBusiness<VendaPecaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VendaPecaBusinessImpl.class);

	@Autowired
	private VendaPecaDAO<VendaPecaEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<VendaPecaEntity> getEntityClass() {
		return VendaPecaEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(VendaPecaEntity t) throws Exception {
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
	public VendaPecaEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			VendaPecaEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<VendaPecaEntity> findByOrdemServico(OrdemServicoEntity t) {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			List<VendaPecaEntity> auxLista = this.dao.findByOrdemServico(t);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<VendaPecaEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<VendaPecaEntity> findAll() throws Exception {
		try {
			List<VendaPecaEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<VendaPecaEntity> findAll(VendaPecaEntity t) throws Exception {
		return null;
	}

	@Override
	public List<VendaPecaEntity> fullTextSearch(String valor) throws Exception {
		return null;
	}

	@Override
	public List<VendaPecaEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		return null;
	}

	@Override
	public List<VendaPecaEntity> fullTextSearch(String valor,
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
	public void save(VendaPecaEntity t) throws Exception {
	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			VendaPecaEntity ent = (VendaPecaEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<VendaPecaEntity> getAllForComboSelect(Class<VendaPecaEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<VendaPecaEntity> getAllForCombo(Class<VendaPecaEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<VendaPecaEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Override
	public VendaPecaEntity find(VendaPecaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}