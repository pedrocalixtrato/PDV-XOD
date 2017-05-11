package dc.model.business.eventos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.model.dao.eventos.IContratoEventosDAO;

@Service
@Transactional(readOnly = true)
public class ContratoEventosBusinessImpl implements ContratoEventosBusiness<ContratoEventosEntity>, Serializable {
	
	private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(ContratoEventosBusinessImpl.class);
		
		@Autowired
		private IContratoEventosDAO dao;

		@Override
		public Class<ContratoEventosEntity> getEntityClass() {
			return ContratoEventosEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(ContratoEventosEntity t) throws Exception {
			
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
		public ContratoEventosEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				ContratoEventosEntity ent = this.dao.find(id);

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public ContratoEventosEntity find(ContratoEventosEntity t) throws Exception {
			return null;
		}

		@Override
		public List<ContratoEventosEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<ContratoEventosEntity> findAll() throws Exception {
			
			try {
				List<ContratoEventosEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<ContratoEventosEntity> findAll(ContratoEventosEntity t) throws Exception {
			return null;
		}

		@Override
		public List<ContratoEventosEntity> fullTextSearch(String valor) throws Exception {
			return null;
		}

		@Override
		public List<ContratoEventosEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ContratoEventosEntity> fullTextSearch(String valor,
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
		public void save(ContratoEventosEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				ContratoEventosEntity ent = (ContratoEventosEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<ContratoEventosEntity> getAllForComboSelect(
				Class<ContratoEventosEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<ContratoEventosEntity> getAllForCombo(Class<ContratoEventosEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<ContratoEventosEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
