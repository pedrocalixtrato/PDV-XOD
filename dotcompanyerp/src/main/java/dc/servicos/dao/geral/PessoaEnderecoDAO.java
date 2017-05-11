package dc.servicos.dao.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.control.util.ListUtils;
import dc.control.util.ObjectUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaEnderecoDAO extends AbstractCrudDAO<PessoaEnderecoEntity> implements IPessoaEnderecoDAO {

	@Override
	public Class<PessoaEnderecoEntity> getEntityClass() {
		return PessoaEnderecoEntity.class;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#listaTodos()
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> listaTodos() {
		return getSession().createQuery("from PessoaEnderecoEntity").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#listaPorEmpresa(dc.entidade.administrativo.empresa.EmpresaEntity)
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> listaPorEmpresa(EmpresaEntity empresa) {
		return getSession()
				.createQuery(
						"from PessoaEnderecoEntity c where c.empresa = :emp")
				.setParameter("emp", empresa).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#listaPorPessoa(dc.entidade.geral.pessoal.PessoaEntity)
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> listaPorPessoa(PessoaEntity pessoa) {
		// TODO Verificar pq só funciona na segunda vez
		try {
			getSession()
					.createQuery(
							"from PessoaEnderecoEntity c where c.pessoa = :pessoa ")
					.setParameter("pessoa", pessoa).list();
		} catch (Exception e) {

		}

		return getSession()
				.createQuery(
						"from PessoaEnderecoEntity c where c.pessoa = :pessoa ")
				.setParameter("pessoa", pessoa).list();
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "email" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#deletePessoaEnderecoList(java.util.List)
	 */
	@Override
	@Transactional
	public void deletePessoaEnderecoList(List<Serializable> auxLista) {
		try {
			String sql = "DELETE FROM :entity WHERE empresa.id IN (:auxLista)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameterList("auxLista", auxLista);

			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#getPessoaEnderecoList(dc.entidade.administrativo.empresa.EmpresaEntity)
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> getPessoaEnderecoList(EmpresaEntity entity) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.empresa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", entity.getId());

			List<PessoaEnderecoEntity> auxLista = query.list();

			if (auxLista == null) {
				auxLista = new ArrayList<PessoaEnderecoEntity>();
			}

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#getPessoaEnderecoList(dc.entidade.geral.pessoal.PessoaEntity)
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> getPessoaEnderecoList(PessoaEntity entity) {
		try {
			String sql = "select ent FROM :entity ent WHERE (1 = 1) AND ent.pessoa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", entity.getId());

			List<PessoaEnderecoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#saveOrUpdatePessoaEnderecoList(java.util.List, dc.entidade.administrativo.empresa.EmpresaEntity)
	 */
	@Override
	@Transactional
	public void saveOrUpdatePessoaEnderecoList(
			List<PessoaEnderecoEntity> auxLista, EmpresaEntity empresa) {
		try {
			if (ListUtils.isNullOrEmpty(auxLista)) {
				return;
			}

			for (PessoaEnderecoEntity ent : auxLista) {
				if (ObjectUtils.isNotBlank(ent.getUf())) {
					ent.setIdUf(ent.getUf().getId());
					ent.setSiglaUf(ent.getUf().getSigla());
				}

				ent.setEmpresa(empresa);

				// String cep = ent.getCep();

				// if (StringUtils.isNotBlank(cep)) {
				// cep = StringUtils.removeSpecialCharacters(ent.getCep());
				// ent.setCep(cep);
				// }

				if (ent.getId() != null) {
					getSession().merge(ent);
				} else {
					super.saveOrUpdate(ent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.IPessoaEnderecoDAO#findByPessoaEndereco(dc.entidade.geral.pessoal.PessoaEntity)
	 */
	@Override
	@Transactional
	public List<PessoaEnderecoEntity> findByPessoaEndereco(PessoaEntity pessoa){

		List<PessoaEnderecoEntity> lista = new ArrayList<>();

		try{
			if(pessoa!=null){
				lista =  getSession()
						.createQuery("from PessoaEnderecoEntity i where i.pessoa = :pessoa")
						.setParameter("pessoa", pessoa).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}