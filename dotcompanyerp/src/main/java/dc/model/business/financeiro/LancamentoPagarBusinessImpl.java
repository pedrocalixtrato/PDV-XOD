package dc.model.business.financeiro;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.framework.FmMenu;
import dc.servicos.dao.financeiro.ILancamentoPagarDAO;

@Service
@Transactional(readOnly = true)
public class LancamentoPagarBusinessImpl implements LancamentoPagarBusiness<LancamentoPagarEntity>, Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private static final int FIRST_ROW = 1;
	private static final int DEFAULT_PAGE_SIZE = 50;
	
	private static Logger logger = Logger.getLogger(LancamentoPagarBusinessImpl.class);
	
	@Autowired
	private ILancamentoPagarDAO dao;

	@Override
	public Class<LancamentoPagarEntity> getEntityClass() {
		return LancamentoPagarEntity.class;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(LancamentoPagarEntity t) throws Exception {
		dao.delete(t);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAll(List<Serializable> list) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName()+ "] deleteAll");

			this.dao.deleteAllByIds(list);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		
	}

	@Override
	public LancamentoPagarEntity find(Serializable id) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			LancamentoPagarEntity ent = this.dao.find(id);
			
			Hibernate.initialize(ent.getEmpresa());
			Hibernate.initialize(ent.getFornecedor());
			Hibernate.initialize(ent.getDocumentoOrigem());
			Hibernate.initialize(ent.getParcelasPagar());
			Hibernate.initialize(ent.getLctoPagarNtFinanceiras());

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public LancamentoPagarEntity find(LancamentoPagarEntity t) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagarEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagarEntity> findAll() throws Exception {
		
		try {
			List<LancamentoPagarEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<LancamentoPagarEntity> findAll(LancamentoPagarEntity t) throws Exception {
		return null;
	}
	
	@Override
	public List<LancamentoPagarEntity> fullTextSearch(String valor) throws Exception {
		return dao.fullTextSearch(valor);
	}

	@Override
	public List<LancamentoPagarEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LancamentoPagarEntity> fullTextSearch(String valor,
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
	@Transactional(readOnly = false)
	public void save(LancamentoPagarEntity t) throws Exception {
		
	}

	@Override
	@Transactional(readOnly = false)
	public <E> void saveOrUpdate(E o) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

			LancamentoPagarEntity ent = (LancamentoPagarEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		
	}

	@Override
	public List<LancamentoPagarEntity> getAllForComboSelect(
			Class<LancamentoPagarEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
	}

	@Override
	public List<LancamentoPagarEntity> getAllForCombo(Class<LancamentoPagarEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<LancamentoPagarEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		
		return dao.comboTextSearch(value, menu, getAll);
	}

}
