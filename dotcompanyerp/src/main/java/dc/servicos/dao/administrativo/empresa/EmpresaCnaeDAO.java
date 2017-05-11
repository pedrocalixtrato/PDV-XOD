package dc.servicos.dao.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.entidade.geral.CnaeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EmpresaCnaeDAO extends AbstractCrudDAO<EmpresaCnaeEntity> implements IEmpresaCnaeDAO {

	@Override
	public Class<EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
	}

	@Transactional
	public List<EmpresaCnaeEntity> listarTodos() {
		try {
			List<EmpresaCnaeEntity> auxLista = new ArrayList<EmpresaCnaeEntity>();

			String sql = "FROM :entity";
			sql = sql.replace(":entity", getEntityClass().getName());

			auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaCnaeDAO#getCnaePrincipalList()
	 */
	@Override
	@Transactional
	public List<EmpresaCnaeEntity> getCnaePrincipalList() {
		try {
			List<EmpresaCnaeEntity> auxLista = new ArrayList<EmpresaCnaeEntity>();

			String sql = "FROM :entity e WHERE e.principal = 'S'";
			sql = sql.replace(":entity", getEntityClass().getName());

			auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataRegistro" };
	}

	@javax.transaction.Transactional
	public void saveOrUpdate(EmpresaCnaeEntity empresa) {
		empresa.setCnae((CnaeEntity) getSession().get(CnaeEntity.class, empresa.getCnae().getId()));
		super.saveOrUpdate(empresa);
	}

}