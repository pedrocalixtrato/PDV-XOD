package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.servicos.dao.nfe.INfeDetEspecificoArmamentoDAO;

/**
 * 
 * 
 */

@Service("nfeDetEspecificoArmamentoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoArmamentoBusinessImpl implements Serializable,
		NfeDetEspecificoArmamentoBusiness<NfeDetEspecificoArmamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetEspecificoArmamentoDAO mainDao;

	@Override
	public void delete(NfeDetEspecificoArmamentoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetEspecificoArmamentoEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetEspecificoArmamentoEntity find(
			NfeDetEspecificoArmamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> findAll(
			NfeDetEspecificoArmamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> fullTextSearch(String valor,
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
	public void save(NfeDetEspecificoArmamentoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> find(String s)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> search(
			NfeDetEspecificoArmamentoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetEspecificoArmamentoEntity> getEntityClass() {
		return NfeDetEspecificoArmamentoEntity.class;
	}

}