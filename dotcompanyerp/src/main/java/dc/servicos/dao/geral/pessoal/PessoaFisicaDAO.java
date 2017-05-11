package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.model.dao.geral.pessoal.IPessoaFisicaDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalPessoaFisicaDAO")
public class PessoaFisicaDAO extends AbstractCrudDAO<PessoaFisicaEntity> implements IPessoaFisicaDAO {

	@Override
	public Class<PessoaFisicaEntity> getEntityClass() {
		return PessoaFisicaEntity.class;
	}

	@Transactional
	public PessoaFisicaEntity getEntity(PessoaEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.pessoa.id = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			PessoaFisicaEntity entity = (PessoaFisicaEntity) query
					.uniqueResult();

			if (entity == null) {
				entity = new PessoaFisicaEntity();
			}

			return entity;
		} catch (Exception e) {
			return new PessoaFisicaEntity();
		}
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("FROM PessoaFisica").list();
	}

	@Transactional
	public PessoaFisicaEntity getPessoaFisica(int idPessoa) {
		return (PessoaFisicaEntity) getSession()
				.createCriteria(PessoaFisicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	public List<PessoaFisicaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaFisicaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	
	@Override
	public String[] getDefaultSearchFields() {
		return new String[] {};
	}

}