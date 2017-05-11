package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.servicos.dao.nfe.INfeDetEspecificoMedicamentoDAO;

/**
 * 
 * 
 */

@Service("nfeDetEspecificoMedicamentoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoMedicamentoBusinessImpl implements Serializable,
		NfeDetEspecificoMedicamentoBusiness<NfeDetEspecificoMedicamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDetEspecificoMedicamentoDAO mainDao;

	@Override
	public void delete(NfeDetEspecificoMedicamentoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDetEspecificoMedicamentoEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDetEspecificoMedicamentoEntity find(
			NfeDetEspecificoMedicamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> findAll(
			NfeDetEspecificoMedicamentoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> fullTextSearch(String valor,
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
	public void save(NfeDetEspecificoMedicamentoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> find(String s)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> search(
			NfeDetEspecificoMedicamentoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDetEspecificoMedicamentoEntity> getEntityClass() {
		return NfeDetEspecificoMedicamentoEntity.class;
	}

}