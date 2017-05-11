package dc.servicos.dao.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.suprimentos.compra.RequisicaoCompraEntity;

@Service
@Transactional(readOnly = true)
public class RequisicaoCompraBusinessImpl implements RequisicaoCompraBusiness<RequisicaoCompraEntity>, Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(RequisicaoCompraBusinessImpl.class);
		
		@Autowired
		private IRequisicaoDAO dao;

		@Override
		public Class<RequisicaoCompraEntity> getEntityClass() {
			return RequisicaoCompraEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(RequisicaoCompraEntity t) throws Exception {
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
		public RequisicaoCompraEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				RequisicaoCompraEntity ent = this.dao.find(id);
				
				Hibernate.initialize(ent.getEmpresa());
				Hibernate.initialize(ent.getColaborador());
				Hibernate.initialize(ent.getTipoRequisicao());
				Hibernate.initialize(ent.getRequisicaoDetalhes());

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public RequisicaoCompraEntity find(RequisicaoCompraEntity t) throws Exception {
			return null;
		}

		@Override
		public List<RequisicaoCompraEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<RequisicaoCompraEntity> findAll() throws Exception {
			
			try {
				List<RequisicaoCompraEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<RequisicaoCompraEntity> findAll(RequisicaoCompraEntity t) throws Exception {
			return null;
		}
		
		@Override
		public List<RequisicaoCompraEntity> fullTextSearch(String valor) throws Exception {
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<RequisicaoCompraEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<RequisicaoCompraEntity> fullTextSearch(String valor,
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
		public void save(RequisicaoCompraEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				RequisicaoCompraEntity ent = (RequisicaoCompraEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<RequisicaoCompraEntity> getAllForComboSelect(
				Class<RequisicaoCompraEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<RequisicaoCompraEntity> getAllForCombo(Class<RequisicaoCompraEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<RequisicaoCompraEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
