package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.SubGrupoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("produtoSubGrupoDAO")
public class SubGrupoDAO extends AbstractCrudDAO<SubGrupoEntity> implements ISubGrupoDAO {

	@Override
	public Class<SubGrupoEntity> getEntityClass() {
		return SubGrupoEntity.class;
	}

	@Transactional
	public List<SubGrupoEntity> listaTodos() {
		return getSession().createQuery("from SubGrupoProduto").list();
	}

	@Transactional
	public List<SubGrupoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SubGrupoProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"grupo", "nome", "descricao" };
	}

	@Transactional
	public List<SubGrupoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from SubGrupoProduto where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}