package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;

@Service
@Transactional(readOnly = true)
public class ColaboradorBusinessImpl implements Serializable, ColaboradorBusiness<ColaboradorEntity> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private static Logger logger = Logger.getLogger(ColaboradorBusinessImpl.class);

		@Autowired
		private IColaboradorDAO dao;

		/**
		 * **********************************************
		 */

		@Override
		public Class<ColaboradorEntity> getEntityClass() {
			return ColaboradorEntity.class;
		}

		@Transactional(readOnly = false)
		@Override
		public void delete(ColaboradorEntity t) throws Exception {
		
			dao.delete(t);

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
		public ColaboradorEntity find(Serializable id) throws Exception {
			// TODO Auto-generated method stub
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				ColaboradorEntity ent = this.dao.find(id);
				
				Hibernate.initialize(ent.getEmpresa());
				Hibernate.initialize(ent.getCargo());
				Hibernate.initialize(ent.getContaCaixa());
				Hibernate.initialize(ent.getTipoColaborador());
				Hibernate.initialize(ent.getTipoAdmissao());
				Hibernate.initialize(ent.getNivelFormacao());
				Hibernate.initialize(ent.getPessoa());
				Hibernate.initialize(ent.getPlanoConta());
				Hibernate.initialize(ent.getSetor());
				Hibernate.initialize(ent.getSindicato());
				Hibernate.initialize(ent.getSituacaoColaborador());

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}
		
		public List<ColaboradorEntity> findVendedores() throws Exception {
			return dao.listaVendedores();
		}

		public List<ColaboradorEntity> findTecnicos() throws Exception {
			return dao.listaTecnicos();
		}


		@Override
		public ColaboradorEntity find(ColaboradorEntity t) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ColaboradorEntity> find(String s) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ColaboradorEntity> findAll() throws Exception {
			// TODO Auto-generated method stub
			try {
				List<ColaboradorEntity> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<ColaboradorEntity> findAll(ColaboradorEntity t) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ColaboradorEntity> fullTextSearch(String valor) throws Exception {
			
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<ColaboradorEntity> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ColaboradorEntity> fullTextSearch(String valor,
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
		public void save(ColaboradorEntity t) throws Exception {
			// TODO Auto-generated method stub

		}

		@Transactional(readOnly = false)
		@Override
		public <E> void saveOrUpdate(E o) throws Exception {
			// TODO Auto-generated method stub
			try {
				System.out.println(":: [" + getClass().getSimpleName()
						+ "] saveOrUpdate");

				ColaboradorEntity ent = (ColaboradorEntity) o;

				this.dao.saveOrUpdate(ent);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<ColaboradorEntity> getAllForComboSelect(Class<ColaboradorEntity> type,
				int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
					idSelected);
		}

		@Override
		public List<ColaboradorEntity> getAllForCombo(Class<ColaboradorEntity> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<ColaboradorEntity> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			return dao.comboTextSearch(value, menu, getAll);
		}

		/**
		 * 
		 */
}