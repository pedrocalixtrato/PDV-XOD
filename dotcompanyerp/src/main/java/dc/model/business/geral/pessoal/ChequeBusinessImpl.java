package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.Cheque;
import dc.entidade.framework.FmMenu;
import dc.model.dao.geral.pessoal.ChequeDAO;

@Service
@Transactional(readOnly = true)
public class ChequeBusinessImpl implements Serializable, ChequeBusiness<Cheque>  {
	
			private static final long serialVersionUID = 1L;

			@Autowired
			private ChequeDAO<Cheque> dao;

			/**
			 * **********************************************
			 */

			@Override
			public Class<Cheque> getEntityClass() {
				return Cheque.class;
			}

			@Transactional(readOnly = false)
			@Override
			public void delete(Cheque t) throws Exception {
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
			public Cheque find(Serializable id) throws Exception {
				try {
					System.out.println(":: [" + getClass().getSimpleName() + "] find");

					Cheque ent = this.dao.find(id);

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
			public List<Cheque> getAllForComboSelect(Class<Cheque> type,
					int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
				return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
						idSelected);
			}

			@Override
			public List<Cheque> getAllForCombo(Class<Cheque> type,
					int idEmpresa, FmMenu menu, Boolean getAll) {
				return dao.getAllForCombo(type, idEmpresa, menu, getAll);
			}

			@Override
			public List<Cheque> comboTextSearch(String value, FmMenu menu,
					Boolean getAll) {
				return dao.comboTextSearch(value, menu, getAll);
			}

			@Override
			public Cheque find(Cheque t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Cheque> find(String s) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Cheque> findAll() throws Exception {
				return dao.getAll();
			}

			@Override
			public List<Cheque> findAll(Cheque t)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Cheque> fullTextSearch(String valor)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Cheque> fullTextSearch(String valor, int first,
					int pageSize, String[] sortingFields, boolean[] sortingStates,
					List<Filter> filters) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Cheque> fullTextSearch(String valor,
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
			public void save(Cheque t) throws Exception {
				// TODO Auto-generated method stub
				
			}

}
