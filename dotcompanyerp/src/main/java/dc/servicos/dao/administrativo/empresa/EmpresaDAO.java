package dc.servicos.dao.administrativo.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.framework.geral.IEmpresaSeguimentoDAO;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;

@Repository
public class EmpresaDAO extends AbstractCrudDAO<EmpresaEntity> implements IEmpresaDAO {

	private static String MATRIZ = "1";

	@Autowired
	private IPessoaEnderecoDAO pessoaEnderecoDAO;

	@Autowired
	private IEmpresaSeguimentoDAO empresaSeguimentoDAO;

	@Override
	public Class<EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#listaTodos()
	 */
	@Override
	@Transactional
	public List<EmpresaEntity> listaTodos() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<EmpresaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE nomeFantasia LIKE :q";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Transactional
	public List<EmpresaEntity> query(String q) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE lower(nomeFantasia) LIKE :q";

			q = "%" + q.toLowerCase() + "%";

			return getSession().createQuery(sql).setParameter("q", q).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#getListEmpresa()
	 */

	@Override
	@Transactional
	public List<EmpresaEntity> getListEmpresa() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#findEmpresa(java.lang.Integer)
	 */
	@Override
	@Transactional
	public EmpresaEntity findEmpresa(Integer id) {
		try {
			String sql = "SELECT ent.empresa FROM TipoAquisicaoEntity ent"
					+ " WHERE (1 = 1) AND ent.id = :id";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("id", id).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#findByCNPJ(java.lang.String)
	 */
	@Override
	@Transactional
	public EmpresaEntity findByCNPJ(String cnpj) {
		try {
			List<EmpresaEntity> auxLista = getSession()
					.createCriteria(EmpresaEntity.class)
					.add(Restrictions.eq("cnpj", cnpj)).list();

			if (!auxLista.isEmpty()) {
				return auxLista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#getEmpresaMatrizList()
	 */
	@Override
	@Transactional
	public List<EmpresaEntity> getEmpresaMatrizList() {
		try {
			List<EmpresaEntity> auxLista = new ArrayList<EmpresaEntity>();

			String sql = "SELECT new EmpresaEntity(ent.id, ent.razaoSocial, ent.nomeFantasia)"
					+ " FROM EmpresaEntity ent WHERE tipo = :tipo";

			auxLista = getSession().createQuery(sql)
					.setParameter("tipo", MATRIZ).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#findEmpresaByContaEmpresa(java.lang.Integer)
	 */
	@Override
	@Transactional
	public EmpresaEntity findEmpresaByContaEmpresa(Integer contaEmpresaId) {
		try {
			String sql = "SELECT ent "
					+ " FROM EmpresaEntity ent "
					+ " INNER JOIN ent.contaEmpresa conta WHERE conta.id = :c";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("c", contaEmpresaId).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	@Override
	public void saveOrUpdateEmpresa(EmpresaEntity empresa) throws Exception {
		try {
			
			Hibernate.initialize(empresa.getContaEmpresa());
			Hibernate.initialize(empresa.getContaEmpresa().getUsuarioCriador());
			Hibernate.initialize(empresa.getContaEmpresa().getUsuarioCriador().getPapel());
			empresa = (EmpresaEntity) getSession().merge(empresa);

			List<PessoaEnderecoEntity> auxLista = empresa
					.getPessoaEnderecoList();

			if (auxLista != null && !auxLista.isEmpty()) {
				this.pessoaEnderecoDAO.saveOrUpdatePessoaEnderecoList(auxLista, empresa);
			}

			List<EmpresaSeguimento> auxLista1 = empresa
					.getEmpresaSeguimentoList();

			if (auxLista1 != null && !auxLista1.isEmpty()) {
				this.empresaSeguimentoDAO
						.saveOrUpdateEmpresaSeguimentoList(auxLista1);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IEmpresaDAO#deleteEmpresaList(java.util.List)
	 */
	@Override
	@Transactional
	public void deleteEmpresaList(List<Serializable> auxLista) {
		try {
			this.pessoaEnderecoDAO.deletePessoaEnderecoList(auxLista);

			super.deleteAllByIds(auxLista);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}