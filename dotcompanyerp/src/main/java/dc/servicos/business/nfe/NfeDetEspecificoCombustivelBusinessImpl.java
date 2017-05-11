package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoCombustivelDAO;

/**
 * 
 * 
 */

@Service("nfeDetEspecificoCombustivelBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoCombustivelBusinessImpl implements Serializable,
		NfeDetEspecificoCombustivelBusiness<NfeDetEspecificoCombustivelEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoCombustivelDAO mainDao;

	@Override
	public void delete(NfeDetEspecificoCombustivelEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetEspecificoCombustivelEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetEspecificoCombustivelEntity find(
			NfeDetEspecificoCombustivelEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> findAll(
			NfeDetEspecificoCombustivelEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> fullTextSearch(String valor,
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
	public void save(NfeDetEspecificoCombustivelEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> find(String s)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> search(
			NfeDetEspecificoCombustivelEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetEspecificoCombustivelEntity> getEntityClass() {
		return NfeDetEspecificoCombustivelEntity.class;
	}

}