package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class BancoDAO extends AbstractCrudDAO<BancoEntity> implements IBancoDAO {

	@Override
	public Class<BancoEntity> getEntityClass() {
		return BancoEntity.class;
	}

	@Transactional
	public List<BancoEntity> listaTodos() {
		return getSession().createQuery("from BancoEntity").list();
	}

	@Transactional
	public List<BancoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from BancoEntity where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Transactional
	public List<BancoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from BancoEntity where lower(nome) like :q")
				.setParameter("q", q).list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "codigo", "url" };
	}

}