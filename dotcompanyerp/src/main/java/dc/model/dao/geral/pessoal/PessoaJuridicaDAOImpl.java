package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaJuridicaDAOImpl extends AbstractCrudDAO<PessoaJuridicaEntity> implements IPessoaJuridicaDAO {

	private static Logger logger = Logger
			.getLogger(PessoaJuridicaDAOImpl.class);

	@Override
	public Class<PessoaJuridicaEntity> getEntityClass() {
		return PessoaJuridicaEntity.class;
	}

	public List<PessoaJuridicaEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaJuridicaEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaJuridicaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaJuridicaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaJuridicaEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaJuridicaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}


	@Transactional
	public PessoaJuridicaEntity getPessoaJuridica(int idPessoa) {
		return (PessoaJuridicaEntity) getSession()
				.createCriteria(PessoaJuridicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	public String[] getDefaultSearchFields() {
		return new String[] {};
	}

}