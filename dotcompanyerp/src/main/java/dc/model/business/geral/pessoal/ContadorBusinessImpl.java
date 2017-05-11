package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.model.dao.geral.pessoal.IContadorDAO;

@Service
@Transactional(readOnly = true)
public class ContadorBusinessImpl implements Serializable, ContadorBusiness<ContadorEntity> {
	
	
		private static final long serialVersionUID = 1L;

		@Autowired
		private IContadorDAO dao;

		/**
		 * **********************************************
		 */

		@Override
		public Class<ContadorEntity> getEntityClass() {
			return ContadorEntity.class;
		}

		@Transactional(readOnly = false)
		@Override
		public void delete(ContadorEntity t) throws Exception {
			
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
		public ContadorEntity find(Serializable id) throws Exception {
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				ContadorEntity ent = this.dao.find(id);

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
		public List<ContadorEntity> getAllForComboSelect(Class<ContadorEntity> type,
				int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
					idSelected);
		}

		@Override
		public List<ContadorEntity> getAllForCombo(Class<ContadorEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<ContadorEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			return dao.comboTextSearch(value, menu, getAll);
		}

		@Override
		public ContadorEntity find(ContadorEntity t) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContadorEntity> find(String s) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContadorEntity> findAll() throws Exception {
			return dao.getAll();
		}

		@Override
		public List<ContadorEntity> findAll(ContadorEntity t)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContadorEntity> fullTextSearch(String valor)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContadorEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContadorEntity> fullTextSearch(String valor,
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
		public void save(ContadorEntity t) throws Exception {
			// TODO Auto-generated method stub
			
		}

}
