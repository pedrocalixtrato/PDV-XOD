package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.servicos.dao.nfe.INfeDetalheImpostoPisDAO;

/**
 * 
 * 
 */

@Service("nfeDetalheImpostoPisBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoPisBusinessImpl implements Serializable,
		NfeDetalheImpostoPisBusiness<NfeDetalheImpostoPisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetalheImpostoPisDAO mainDao;

	@Override
	public void delete(NfeDetalheImpostoPisEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetalheImpostoPisEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetalheImpostoPisEntity find(NfeDetalheImpostoPisEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> findAll(NfeDetalheImpostoPisEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> fullTextSearch(String valor,
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
	public void save(NfeDetalheImpostoPisEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetalheImpostoPisEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> search(
			NfeDetalheImpostoPisEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetalheImpostoPisEntity> getEntityClass() {
		return NfeDetalheImpostoPisEntity.class;
	}

}