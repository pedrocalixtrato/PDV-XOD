package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.servicos.dao.nfe.INfeDetalheImpostoIssqnDAO;

/**
 * 
 * 
 */

@Service("nfeDetalheImpostoIssqnBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIssqnBusinessImpl implements Serializable,
		NfeDetalheImpostoIssqnBusiness<NfeDetalheImpostoIssqnEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetalheImpostoIssqnDAO mainDao;

	@Override
	public void delete(NfeDetalheImpostoIssqnEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetalheImpostoIssqnEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetalheImpostoIssqnEntity find(NfeDetalheImpostoIssqnEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> findAll(
			NfeDetalheImpostoIssqnEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> fullTextSearch(String valor,
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
	public void save(NfeDetalheImpostoIssqnEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> search(
			NfeDetalheImpostoIssqnEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetalheImpostoIssqnEntity> getEntityClass() {
		return NfeDetalheImpostoIssqnEntity.class;
	}

}