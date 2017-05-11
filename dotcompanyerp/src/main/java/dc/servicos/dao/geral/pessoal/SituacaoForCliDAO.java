package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalSituacaoForCliDAO")
public class SituacaoForCliDAO extends AbstractCrudDAO<SituacaoForCliEntity> implements ISituacaoForCliDAO {

	@Override
	public Class<SituacaoForCliEntity> getEntityClass() {
		return SituacaoForCliEntity.class;
	}

	@Transactional
	public List<SituacaoForCliEntity> listaTodos() {
		return getSession().createQuery("from SituacaoForCli").list();
	}

	@Transactional
	public List<SituacaoForCliEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SituacaoForCli where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<SituacaoForCliEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from SituacaoForCli where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}