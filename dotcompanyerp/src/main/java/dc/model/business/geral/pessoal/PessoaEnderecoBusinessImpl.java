package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class PessoaEnderecoBusinessImpl implements Serializable,
		PessoaEnderecoBusiness<PessoaEnderecoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(PessoaEnderecoBusinessImpl.class);

	@Autowired
	private IPessoaEnderecoDAO dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<PessoaEnderecoEntity> getEntityClass() {
		return PessoaEnderecoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(PessoaEnderecoEntity t) throws Exception {
		// TODO Auto-generated method stub

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
	public PessoaEnderecoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			PessoaEnderecoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public PessoaEnderecoEntity find(PessoaEnderecoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> findAll(PessoaEnderecoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEnderecoEntity> fullTextSearch(String valor,
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
	public void save(PessoaEnderecoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
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
	public List<PessoaEnderecoEntity> getAllForComboSelect(
			Class<PessoaEnderecoEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<PessoaEnderecoEntity> getAllForCombo(
			Class<PessoaEnderecoEntity> type, int idEmpresa, FmMenu menu,
			Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<PessoaEnderecoEntity> comboTextSearch(String value,
			FmMenu menu, Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	/**
	 * 
	 */

	@Override
	public List<PessoaEnderecoEntity> list(PessoaEntity entity) {
		// TODO Auto-generated method stub
		try {
			List<PessoaEnderecoEntity> auxLista = this.dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/*@Override
	public List<PessoaEnderecoEntity> list(PessoaEventosEntity pessoa) {
		
		try {
			List<PessoaEnderecoEntity> auxLista = this.dao.list(pessoa);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}*/

}