package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.entidade.framework.FmMenu;
import dc.model.dao.geral.pessoal.IndiceEconomicoDAO;

@Service
@Transactional(readOnly = true)
public class IndiceEconomicoBusinessImpl implements IndiceEconomicoBusiness<IndiceEconomicoEntity>, Serializable {
	
	private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(IndiceEconomicoBusinessImpl.class);
		
		@Autowired
		private IndiceEconomicoDAO<IndiceEconomicoEntity> dao;

		@Override
		public Class<IndiceEconomicoEntity> getEntityClass() {
			return IndiceEconomicoEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(IndiceEconomicoEntity t) throws Exception {
			
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
		public IndiceEconomicoEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				IndiceEconomicoEntity ent = this.dao.find(id);

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public IndiceEconomicoEntity find(IndiceEconomicoEntity t) throws Exception {
			return null;
		}

		@Override
		public List<IndiceEconomicoEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<IndiceEconomicoEntity> findAll() throws Exception {
			
			try {
				List<IndiceEconomicoEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<IndiceEconomicoEntity> findAll(IndiceEconomicoEntity t) throws Exception {
			return null;
		}

		@Override
		public List<IndiceEconomicoEntity> fullTextSearch(String valor) throws Exception {
			return null;
		}

		@Override
		public List<IndiceEconomicoEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<IndiceEconomicoEntity> fullTextSearch(String valor,
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
		public void save(IndiceEconomicoEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				IndiceEconomicoEntity ent = (IndiceEconomicoEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<IndiceEconomicoEntity> getAllForComboSelect(
				Class<IndiceEconomicoEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<IndiceEconomicoEntity> getAllForCombo(Class<IndiceEconomicoEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<IndiceEconomicoEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
