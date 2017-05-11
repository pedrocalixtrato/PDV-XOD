package dc.model.business.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.model.dao.suprimento.estoque.ContagemDetalheDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class ContagemDetalheBusinessImpl implements Serializable,
		ContagemDetalheBusiness<ContagemDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(ContagemDetalheBusinessImpl.class);

	@Autowired
	private ContagemDetalheDAO<ContagemDetalheEntity> dao;

	@Autowired
	private IProdutoDAO produtoDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<ContagemDetalheEntity> getEntityClass() {
		return ContagemDetalheEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(ContagemDetalheEntity t) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out
					.println(":: [" + getClass().getSimpleName() + "] delete");

			ProdutoEntity produto = this.produtoDAO
					.find(t.getProduto().getId());

			t.setProduto(produto);

			this.dao.delete(t);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
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
	public ContagemDetalheEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			ContagemDetalheEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ContagemDetalheEntity find(ContagemDetalheEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> findAll(ContagemDetalheEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemDetalheEntity> fullTextSearch(String valor,
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
	public void save(ContagemDetalheEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			this.dao.saveOrUpdate(o);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<ContagemDetalheEntity> getAllForComboSelect(
			Class<ContagemDetalheEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<ContagemDetalheEntity> getAllForCombo(
			Class<ContagemDetalheEntity> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<ContagemDetalheEntity> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	/**
	 * 
	 */

	@Override
	public List<ContagemDetalheEntity> list(ContagemCabecalhoEntity entity) {
		// TODO Auto-generated method stub
		try {
			List<ContagemDetalheEntity> auxLista = this.dao.list(entity);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}