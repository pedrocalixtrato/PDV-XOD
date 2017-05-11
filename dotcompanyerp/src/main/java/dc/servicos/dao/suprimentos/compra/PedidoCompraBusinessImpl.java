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
import dc.entidade.suprimentos.compra.PedidoEntity;

@Service
@Transactional(readOnly = true)
public class PedidoCompraBusinessImpl implements PedidoCompraBusiness<PedidoEntity>, Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(PedidoCompraBusinessImpl.class);
		
		@Autowired
		private IPedidoCompraDAO dao;

		@Override
		public Class<PedidoEntity> getEntityClass() {
			return PedidoEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(PedidoEntity t) throws Exception {
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
		public PedidoEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				PedidoEntity ent = this.dao.find(id);
				
				Hibernate.initialize(ent.getEmpresa());
				Hibernate.initialize(ent.getFornecedor());
				Hibernate.initialize(ent.getTipoPedido());

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public PedidoEntity find(PedidoEntity t) throws Exception {
			return null;
		}

		@Override
		public List<PedidoEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<PedidoEntity> findAll() throws Exception {
			
			try {
				List<PedidoEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<PedidoEntity> findAll(PedidoEntity t) throws Exception {
			return null;
		}
		
		@Override
		public List<PedidoEntity> fullTextSearch(String valor) throws Exception {
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<PedidoEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<PedidoEntity> fullTextSearch(String valor,
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
		public void save(PedidoEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				PedidoEntity ent = (PedidoEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<PedidoEntity> getAllForComboSelect(
				Class<PedidoEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<PedidoEntity> getAllForCombo(Class<PedidoEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<PedidoEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
