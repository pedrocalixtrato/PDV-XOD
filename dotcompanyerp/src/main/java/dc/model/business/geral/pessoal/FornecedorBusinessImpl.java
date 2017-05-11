package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.geral.IFornecedorDAO;

@Service
@Transactional(readOnly = true)
public class FornecedorBusinessImpl implements Serializable, FornecedorBusiness<FornecedorEntity> {
	
	
		private static final long serialVersionUID = 1L;

		@Autowired
		private IFornecedorDAO dao;

		/**
		 * **********************************************
		 */

		@Override
		public Class<FornecedorEntity> getEntityClass() {
			return FornecedorEntity.class;
		}

		@Transactional(readOnly = false)
		@Override
		public void delete(FornecedorEntity t) throws Exception {
			
			dao.delete(t);
		}

		@Transactional(readOnly = false)
		@Override
		public void deleteAll(List<Serializable> list) throws Exception {
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
		public FornecedorEntity find(Serializable id) throws Exception {
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				FornecedorEntity ent = this.dao.find(id);
				
				Hibernate.initialize(ent.getEmpresa());
				Hibernate.initialize(ent.getBemList());
				Hibernate.initialize(ent.getNfeCabecalhoList());

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Transactional(readOnly = false)
		@Override
		public <E> void saveOrUpdate(E o) throws Exception {
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
		public List<FornecedorEntity> getAllForComboSelect(Class<FornecedorEntity> type,
				int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
					idSelected);
		}

		@Override
		public List<FornecedorEntity> getAllForCombo(Class<FornecedorEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<FornecedorEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			return dao.comboTextSearch(value, menu, getAll);
		}

		@Override
		public FornecedorEntity find(FornecedorEntity t) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FornecedorEntity> find(String s) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FornecedorEntity> findAll() throws Exception {
			return dao.getAll();
		}

		@Override
		public List<FornecedorEntity> findAll(FornecedorEntity t)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FornecedorEntity> fullTextSearch(String valor)
				throws Exception {
			// TODO Auto-generated method stub
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<FornecedorEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FornecedorEntity> fullTextSearch(String valor,
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
		public void save(FornecedorEntity t) throws Exception {
			// TODO Auto-generated method stub
			
		}

}
