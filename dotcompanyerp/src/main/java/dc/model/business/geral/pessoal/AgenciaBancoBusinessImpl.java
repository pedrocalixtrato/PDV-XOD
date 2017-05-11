package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.util.ObjectUtils;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.entidade.framework.FmMenu;
import dc.model.dao.geral.pessoal.IAgenciaBancoDAO;
import dc.servicos.dao.financeiro.IBancoDAO;

@Service
@Transactional(readOnly = true)
public class AgenciaBancoBusinessImpl implements AgenciaBancoBusiness<AgenciaBancoEntity>, Serializable  {	
	
	
private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(AgenciaBancoBusinessImpl.class);
	
	@Autowired
	private IAgenciaBancoDAO dao;
	
	@Autowired
	private IBancoDAO bancoDAO;

	@Override
	public Class<AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(AgenciaBancoEntity t) throws Exception {
		
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
	public AgenciaBancoEntity find(Serializable id) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			AgenciaBancoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public AgenciaBancoEntity find(AgenciaBancoEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AgenciaBancoEntity> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<AgenciaBancoEntity> findAll() throws Exception {
		
		try {
			List<AgenciaBancoEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<AgenciaBancoEntity> findAll(AgenciaBancoEntity t) throws Exception {
		return null;
	}

	@Override
	public List<AgenciaBancoEntity> fullTextSearch(String valor) throws Exception {
		
		return dao.fullTextSearch(valor);
	}

	@Override
	public List<AgenciaBancoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgenciaBancoEntity> fullTextSearch(String valor,
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
	public void save(AgenciaBancoEntity t) throws Exception {
		
	}

	@Override
	@Transactional(readOnly = false)
	public <E> void saveOrUpdate(E o) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

			AgenciaBancoEntity ent = (AgenciaBancoEntity) o;
			
			if (ObjectUtils.isNotBlank(ent.getBanco())) {
			BancoEntity banco = this.bancoDAO.find(ent.getBanco().getId());
			ent.setBanco(banco);
		    }

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		
	}

	@Override
	public List<AgenciaBancoEntity> getAllForComboSelect(
			Class<AgenciaBancoEntity> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
	}

	@Override
	public List<AgenciaBancoEntity> getAllForCombo(Class<AgenciaBancoEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<AgenciaBancoEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		
		return dao.comboTextSearch(value, menu, getAll);
	}

}
