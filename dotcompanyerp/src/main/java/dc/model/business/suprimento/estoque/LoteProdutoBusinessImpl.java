package dc.model.business.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.model.dao.suprimento.estoque.ILoteProdutoDAO;

@Service
@Transactional(readOnly = true)
public class LoteProdutoBusinessImpl implements Serializable, LoteProdutoBusiness<LoteProdutoEntity> {
		
			private static final long serialVersionUID = 1L;

			@Autowired
			private ILoteProdutoDAO dao;

			/**
			 * **********************************************
			 */

			@Override
			public Class<LoteProdutoEntity> getEntityClass() {
				return LoteProdutoEntity.class;
			}

			@Transactional(readOnly = false)
			@Override
			public void delete(LoteProdutoEntity t) throws Exception {
				
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
			public LoteProdutoEntity find(Serializable id) throws Exception {
				try {
					System.out.println(":: [" + getClass().getSimpleName() + "] find");

					LoteProdutoEntity ent = this.dao.find(id);

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
			public List<LoteProdutoEntity> getAllForComboSelect(Class<LoteProdutoEntity> type,
					int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
				return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
						idSelected);
			}

			@Override
			public List<LoteProdutoEntity> getAllForCombo(Class<LoteProdutoEntity> type,
					int idEmpresa, FmMenu menu, Boolean getAll) {
				return dao.getAllForCombo(type, idEmpresa, menu, getAll);
			}

			@Override
			public List<LoteProdutoEntity> comboTextSearch(String value, FmMenu menu,
					Boolean getAll) {
				return dao.comboTextSearch(value, menu, getAll);
			}

			@Override
			public LoteProdutoEntity find(LoteProdutoEntity t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<LoteProdutoEntity> find(String s) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<LoteProdutoEntity> findAll() throws Exception {
				return dao.getAll();
			}

			@Override
			public List<LoteProdutoEntity> findAll(LoteProdutoEntity t)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<LoteProdutoEntity> fullTextSearch(String valor)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<LoteProdutoEntity> fullTextSearch(String valor, int first,
					int pageSize, String[] sortingFields, boolean[] sortingStates,
					List<Filter> filters) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<LoteProdutoEntity> fullTextSearch(String valor,
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
			public void save(LoteProdutoEntity t) throws Exception {
				// TODO Auto-generated method stub
				
			}

}
