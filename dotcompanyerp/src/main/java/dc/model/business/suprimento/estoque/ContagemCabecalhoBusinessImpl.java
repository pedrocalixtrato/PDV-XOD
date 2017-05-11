package dc.model.business.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.util.ListUtils;
import dc.entidade.framework.FmMenu;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.model.dao.suprimento.estoque.ContagemCabecalhoDAO;
import dc.model.dao.suprimento.estoque.ContagemDetalheDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class ContagemCabecalhoBusinessImpl implements Serializable,
		ContagemCabecalhoBusiness<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(ContagemCabecalhoBusinessImpl.class);

	@Autowired
	private ContagemCabecalhoDAO<ContagemCabecalhoEntity> dao;

	@Autowired
	private ContagemDetalheDAO<ContagemDetalheEntity> contagemDetalheDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(ContagemCabecalhoEntity t) throws Exception {
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
	public ContagemCabecalhoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			ContagemCabecalhoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ContagemCabecalhoEntity find(ContagemCabecalhoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> findAll(ContagemCabecalhoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> fullTextSearch(String valor,
			int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContagemCabecalhoEntity> fullTextSearch(String valor,
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
	public void save(ContagemCabecalhoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			ContagemCabecalhoEntity ent = (ContagemCabecalhoEntity) o;

			this.dao.saveOrUpdate(ent);

			if (ListUtils.isNotNullAndNotEmpty(ent.getContagemDetalheList())) {
				for (ContagemDetalheEntity ent1 : ent.getContagemDetalheList()) {
					ProdutoEntity produto = this.produtoDAO.find(ent1
							.getProduto().getId());
					ent1.setProduto(produto);

					this.contagemDetalheDAO.saveOrUpdate(ent1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<ContagemCabecalhoEntity> getAllForComboSelect(
			Class<ContagemCabecalhoEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<ContagemCabecalhoEntity> getAllForCombo(
			Class<ContagemCabecalhoEntity> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<ContagemCabecalhoEntity> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}