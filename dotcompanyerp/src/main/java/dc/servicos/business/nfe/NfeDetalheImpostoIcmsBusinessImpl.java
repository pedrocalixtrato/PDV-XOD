package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.servicos.dao.nfe.INfeDetalheImpostoIcmsDAO;

/**
 * 
 * 
 */

@Service("nfeDetalheImpostoIcmsBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIcmsBusinessImpl implements Serializable,
		NfeDetalheImpostoIcmsBusiness<NfeDetalheImpostoIcmsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetalheImpostoIcmsDAO mainDao;

	@Override
	public void delete(NfeDetalheImpostoIcmsEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetalheImpostoIcmsEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetalheImpostoIcmsEntity find(NfeDetalheImpostoIcmsEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> findAll(
			NfeDetalheImpostoIcmsEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> fullTextSearch(String valor,
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
	public void save(NfeDetalheImpostoIcmsEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> search(
			NfeDetalheImpostoIcmsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetalheImpostoIcmsEntity> getEntityClass() {
		return NfeDetalheImpostoIcmsEntity.class;
	}

}