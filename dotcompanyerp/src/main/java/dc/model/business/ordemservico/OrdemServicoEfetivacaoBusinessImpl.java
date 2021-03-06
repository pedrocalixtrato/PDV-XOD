package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.OrdemServicoEfetivacaoDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class OrdemServicoEfetivacaoBusinessImpl implements Serializable,
OrdemServicoEfetivacaoBusiness<OrdemServicoEfetivacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(OrdemServicoEfetivacaoBusinessImpl.class);

	@Autowired
	private OrdemServicoEfetivacaoDAO<OrdemServicoEfetivacaoEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<OrdemServicoEfetivacaoEntity> getEntityClass() {
		return OrdemServicoEfetivacaoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(OrdemServicoEfetivacaoEntity t) throws Exception {
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
	public OrdemServicoEfetivacaoEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			OrdemServicoEfetivacaoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public OrdemServicoEfetivacaoEntity findByOrdemServico(OrdemServicoEntity t) {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			OrdemServicoEfetivacaoEntity ent = this.dao.findByOrdemServico(t);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<OrdemServicoEfetivacaoEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> findAll(OrdemServicoEfetivacaoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> fullTextSearch(String valor,
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
	public void save(OrdemServicoEfetivacaoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			OrdemServicoEfetivacaoEntity ent = (OrdemServicoEfetivacaoEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> getAllForComboSelect(Class<OrdemServicoEfetivacaoEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> getAllForCombo(Class<OrdemServicoEfetivacaoEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<OrdemServicoEfetivacaoEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Override
	public OrdemServicoEfetivacaoEntity find(OrdemServicoEfetivacaoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}