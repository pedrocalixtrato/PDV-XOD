package dc.model.business.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.entidade.framework.FmMenu;
import dc.model.dao.geral.pessoal.IFluxoCaixaDAO;

@Service
@Transactional(readOnly = true)
public class FluxoCaixaBusinessImpl implements FluxoCaixaBusiness<FluxoCaixaEntity>, Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(FluxoCaixaBusinessImpl.class);
		
		@Autowired
		private IFluxoCaixaDAO dao;

		@Override
		public Class<FluxoCaixaEntity> getEntityClass() {
			return FluxoCaixaEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(FluxoCaixaEntity t) throws Exception {
			
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
		public FluxoCaixaEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				FluxoCaixaEntity ent = this.dao.find(id);

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public FluxoCaixaEntity find(FluxoCaixaEntity t) throws Exception {
			return null;
		}

		@Override
		public List<FluxoCaixaEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<FluxoCaixaEntity> findAll() throws Exception {
			
			try {
				List<FluxoCaixaEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<FluxoCaixaEntity> findAll(FluxoCaixaEntity t) throws Exception {
			return null;
		}

		@Override
		public List<FluxoCaixaEntity> fullTextSearch(String valor) throws Exception {
			return null;
		}

		@Override
		public List<FluxoCaixaEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<FluxoCaixaEntity> fullTextSearch(String valor,
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
		public void save(FluxoCaixaEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				FluxoCaixaEntity ent = (FluxoCaixaEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<FluxoCaixaEntity> getAllForComboSelect(
				Class<FluxoCaixaEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<FluxoCaixaEntity> getAllForCombo(Class<FluxoCaixaEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<FluxoCaixaEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
