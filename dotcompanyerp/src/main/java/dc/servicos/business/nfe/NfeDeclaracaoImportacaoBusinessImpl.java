package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.nfe.NfeDeclaracaoImportacaoEntity;
import dc.servicos.dao.nfe.INfeDeclaracaoImportacaoDAO;

/**
 * 
 * 
 */

@Service("nfeDeclaracaoImportacaoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDeclaracaoImportacaoBusinessImpl implements Serializable,
		NfeDeclaracaoImportacaoBusiness<NfeDeclaracaoImportacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private INfeDeclaracaoImportacaoDAO mainDAO;

	@Override
	public void delete(NfeDeclaracaoImportacaoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public NfeDeclaracaoImportacaoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NfeDeclaracaoImportacaoEntity find(NfeDeclaracaoImportacaoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> findAll(
			NfeDeclaracaoImportacaoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> fullTextSearch(String valor,
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
	public void save(NfeDeclaracaoImportacaoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> search(
			NfeDeclaracaoImportacaoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<NfeDeclaracaoImportacaoEntity> getEntityClass() {
		return NfeDeclaracaoImportacaoEntity.class;
	}

}