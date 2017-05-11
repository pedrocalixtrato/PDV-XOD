package dc.model.business.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.entidade.framework.FmMenu;
import dc.model.dao.geral.pessoal.INotaFiscalTipoDAO;

@Service
@Transactional(readOnly = true)
public class NotaFiscalTipoBusinessImpl implements NotaFiscalTipoBusiness<NotaFiscalTipo>, Serializable {
	
	
			private static final long serialVersionUID = 1L;

			@Autowired
			private INotaFiscalTipoDAO dao;

			/**
			 * **********************************************
			 */

			@Override
			public Class<NotaFiscalTipo> getEntityClass() {
				return NotaFiscalTipo.class;
			}

			@Transactional(readOnly = false)
			@Override
			public void delete(NotaFiscalTipo t) throws Exception {
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
			public NotaFiscalTipo find(Serializable id) throws Exception {
				try {
					System.out.println(":: [" + getClass().getSimpleName() + "] find");

					NotaFiscalTipo ent = this.dao.find(id);

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
			public List<NotaFiscalTipo> getAllForComboSelect(Class<NotaFiscalTipo> type,
					int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
				return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
						idSelected);
			}

			@Override
			public List<NotaFiscalTipo> getAllForCombo(Class<NotaFiscalTipo> type,
					int idEmpresa, FmMenu menu, Boolean getAll) {
				return dao.getAllForCombo(type, idEmpresa, menu, getAll);
			}

			@Override
			public List<NotaFiscalTipo> comboTextSearch(String value, FmMenu menu,
					Boolean getAll) {
				return dao.comboTextSearch(value, menu, getAll);
			}

			@Override
			public NotaFiscalTipo find(NotaFiscalTipo t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<NotaFiscalTipo> find(String s) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<NotaFiscalTipo> findAll() throws Exception {
				return dao.getAll();
			}

			@Override
			public List<NotaFiscalTipo> findAll(NotaFiscalTipo t)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<NotaFiscalTipo> fullTextSearch(String valor)
					throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<NotaFiscalTipo> fullTextSearch(String valor, int first,
					int pageSize, String[] sortingFields, boolean[] sortingStates,
					List<Filter> filters) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<NotaFiscalTipo> fullTextSearch(String valor,
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
			public void save(NotaFiscalTipo t) throws Exception {
				// TODO Auto-generated method stub
				
			}

}
