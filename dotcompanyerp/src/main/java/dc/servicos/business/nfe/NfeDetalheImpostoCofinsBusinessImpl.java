package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.servicos.dao.nfe.INfeDetalheImpostoCofinsDAO;

/**
 * 
 * 
 */

@Service("nfeDetalheImpostoCofinsBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoCofinsBusinessImpl implements Serializable,
		NfeDetalheImpostoCofinsBusiness<NfeDetalheImpostoCofinsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetalheImpostoCofinsDAO mainDao;

	@Override
	public void delete(NfeDetalheImpostoCofinsEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetalheImpostoCofinsEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetalheImpostoCofinsEntity find(NfeDetalheImpostoCofinsEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> findAll(
			NfeDetalheImpostoCofinsEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> fullTextSearch(String valor,
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

	@Override
	public void save(NfeDetalheImpostoCofinsEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> search(
			NfeDetalheImpostoCofinsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetalheImpostoCofinsEntity> getEntityClass() {
		return NfeDetalheImpostoCofinsEntity.class;
	}

}