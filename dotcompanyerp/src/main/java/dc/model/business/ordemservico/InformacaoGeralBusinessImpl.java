package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.IInformacaoGeralDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Service
@Transactional(readOnly = true)
public class InformacaoGeralBusinessImpl implements Serializable,
InformacaoGeralBusiness<InformacaoGeralEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(InformacaoGeralBusinessImpl.class);

	@Autowired
	private IInformacaoGeralDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<InformacaoGeralEntity> getEntityClass() {
		return InformacaoGeralEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(InformacaoGeralEntity t) throws Exception {
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
	public InformacaoGeralEntity find(Serializable id) throws Exception {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			InformacaoGeralEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity t) {
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			InformacaoGeralEntity ent = this.dao.findByOrdemServico(t);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<InformacaoGeralEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InformacaoGeralEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<InformacaoGeralEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<InformacaoGeralEntity> findAll(InformacaoGeralEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InformacaoGeralEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InformacaoGeralEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InformacaoGeralEntity> fullTextSearch(String valor,
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
	public void save(InformacaoGeralEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			InformacaoGeralEntity ent = (InformacaoGeralEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<InformacaoGeralEntity> getAllForComboSelect(Class<InformacaoGeralEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<InformacaoGeralEntity> getAllForCombo(Class<InformacaoGeralEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<InformacaoGeralEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	@Override
	public InformacaoGeralEntity find(InformacaoGeralEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}