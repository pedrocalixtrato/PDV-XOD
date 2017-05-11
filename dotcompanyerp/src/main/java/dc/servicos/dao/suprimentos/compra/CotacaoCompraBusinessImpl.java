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
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;

@Service
@Transactional(readOnly = true)
public class CotacaoCompraBusinessImpl implements CotacaoCompraBusiness<CotacaoCompraEntity>, Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private static Logger logger = Logger.getLogger(CotacaoCompraBusinessImpl.class);
		
		@Autowired
		private ICotacaoDAO dao;

		@Override
		public Class<CotacaoCompraEntity> getEntityClass() {
			return CotacaoCompraEntity.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(CotacaoCompraEntity t) throws Exception {
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
		public CotacaoCompraEntity find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				CotacaoCompraEntity ent = this.dao.find(id);
				
				Hibernate.initialize(ent.getEmpresa());
				Hibernate.initialize(ent.getCompraFornecedorCotacaos());
				Hibernate.initialize(ent.getCompraReqCotacaoDetalhes());

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public CotacaoCompraEntity find(CotacaoCompraEntity t) throws Exception {
			return null;
		}

		@Override
		public List<CotacaoCompraEntity> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<CotacaoCompraEntity> findAll() throws Exception {
			
			try {
				List<CotacaoCompraEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<CotacaoCompraEntity> findAll(CotacaoCompraEntity t) throws Exception {
			return null;
		}
		
		@Override
		public List<CotacaoCompraEntity> fullTextSearch(String valor) throws Exception {
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<CotacaoCompraEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<CotacaoCompraEntity> fullTextSearch(String valor,
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
		public void save(CotacaoCompraEntity t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				CotacaoCompraEntity ent = (CotacaoCompraEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<CotacaoCompraEntity> getAllForComboSelect(
				Class<CotacaoCompraEntity> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<CotacaoCompraEntity> getAllForCombo(Class<CotacaoCompraEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<CotacaoCompraEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
