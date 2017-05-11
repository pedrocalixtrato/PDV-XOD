package dc.model.business.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.framework.FmMenu;
import dc.servicos.dao.financeiro.ILancamentoReceberDAO;


@Service
@Transactional(readOnly = true)
public class LancamentoReceberBusinessImpl implements Serializable, LancamentoReceberBusiness<LancamentoReceber> {
	
	
		private static final long serialVersionUID = 1L;
		
		@Autowired
		private ILancamentoReceberDAO dao;

		@Override
		public Class<LancamentoReceber> getEntityClass() {
			return LancamentoReceber.class;
		}

		@Override
		@Transactional(readOnly = false)
		public void delete(LancamentoReceber t) throws Exception {
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
		public LancamentoReceber find(Serializable id) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] find");

				LancamentoReceber ent = this.dao.find(id);

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public LancamentoReceber find(LancamentoReceber t) throws Exception {
			return null;
		}

		@Override
		public List<LancamentoReceber> find(String s) throws Exception {
			return null;
		}

		@Override
		public List<LancamentoReceber> findAll() throws Exception {
			
			try {
				List<LancamentoReceber> auxLista = this.dao.getAll();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		@Override
		public List<LancamentoReceber> findAll(LancamentoReceber t) throws Exception {
			return null;
		}

		@Override
		public List<LancamentoReceber> fullTextSearch(String valor) throws Exception {
			
			return dao.fullTextSearch(valor);
		}

		@Override
		public List<LancamentoReceber> fullTextSearch(String valor, int first,
				int pageSize, String[] sortingFields, boolean[] sortingStates,
				List<Filter> filters) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<LancamentoReceber> fullTextSearch(String valor,
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
		public void save(LancamentoReceber t) throws Exception {
			
		}

		@Override
		@Transactional(readOnly = false)
		public <E> void saveOrUpdate(E o) throws Exception {
			
			try {
				System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

				this.dao.saveOrUpdate(o);
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
			
		}

		@Override
		public List<LancamentoReceber> getAllForComboSelect(
				Class<LancamentoReceber> type, int idEmpresa, FmMenu menu,
				String typeSelected, Integer idSelected) {
			
			return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
		}

		@Override
		public List<LancamentoReceber> getAllForCombo(Class<LancamentoReceber> type,
				int idEmpresa, FmMenu menu, Boolean getAll) {
			
			return dao.getAllForCombo(type, idEmpresa, menu, getAll);
		}

		@Override
		public List<LancamentoReceber> comboTextSearch(String value, FmMenu menu,
				Boolean getAll) {
			
			return dao.comboTextSearch(value, menu, getAll);
		}

}
