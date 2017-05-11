package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.entidade.ordemservico.OrcamentoOsItemEntity;
import dc.model.dao.ordemservico.OrcamentoItemOsDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class OrcamentoItemOsBusinessImpl implements Serializable,
		OrcamentoItemOsBusiness<OrcamentoOsItemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(OrcamentoItemOsBusinessImpl.class);

	@Autowired
	private OrcamentoItemOsDAO<OrcamentoOsItemEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<OrcamentoOsItemEntity> getEntityClass() {
		return OrcamentoOsItemEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(OrcamentoOsItemEntity t) throws Exception {
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
	public OrcamentoOsItemEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			OrcamentoOsItemEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public OrcamentoOsItemEntity find(OrcamentoOsItemEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrcamentoOsItemEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrcamentoOsItemEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<OrcamentoOsItemEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<OrcamentoOsItemEntity> findAll(OrcamentoOsItemEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrcamentoOsItemEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrcamentoOsItemEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrcamentoOsItemEntity> fullTextSearch(String valor,
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
	public void save(OrcamentoOsItemEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			OrcamentoOsItemEntity ent = (OrcamentoOsItemEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<OrcamentoOsItemEntity> getAllForComboSelect(Class<OrcamentoOsItemEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<OrcamentoOsItemEntity> getAllForCombo(Class<OrcamentoOsItemEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<OrcamentoOsItemEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Transactional
	public List<OrcamentoOsItemEntity> findByOrcamentoOs(OrcamentoOsEntity orcamentoOs){

		try{
			List<OrcamentoOsItemEntity> auxLista = this.dao.findByOrcamentoOs(orcamentoOs);
			return auxLista;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}