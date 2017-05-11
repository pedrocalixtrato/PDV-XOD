package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalAtividadeForCliDAO")
public class AtividadeForCliDAO extends AbstractCrudDAO<AtividadeForCliEntity> implements IAtividadeForCliDAO {

	@Override
	public Class<AtividadeForCliEntity> getEntityClass() {
		return AtividadeForCliEntity.class;
	}

	@Transactional
	public List<AtividadeForCliEntity> listaTodos() {
		return getSession().createQuery("from AtividadeForCli").list();
	}

	@Transactional
	public List<AtividadeForCliEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from AtividadeForCli where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<AtividadeForCliEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from AtividadeForCli where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}