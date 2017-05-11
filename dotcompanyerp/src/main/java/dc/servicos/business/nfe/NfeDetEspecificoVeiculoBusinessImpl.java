package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoVeiculoDAO;

/**
 * 
 * 
 */

@Service("nfeDetEspecificoVeiculoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoVeiculoBusinessImpl implements Serializable,
		NfeDetEspecificoVeiculoBusiness<NfeDetEspecificoVeiculoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoVeiculoDAO mainDao;

	@Override
	public void delete(NfeDetEspecificoVeiculoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetEspecificoVeiculoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetEspecificoVeiculoEntity find(NfeDetEspecificoVeiculoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> findAll(
			NfeDetEspecificoVeiculoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> fullTextSearch(String valor,
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
	public void save(NfeDetEspecificoVeiculoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> search(
			NfeDetEspecificoVeiculoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetEspecificoVeiculoEntity> getEntityClass() {
		return NfeDetEspecificoVeiculoEntity.class;
	}

}