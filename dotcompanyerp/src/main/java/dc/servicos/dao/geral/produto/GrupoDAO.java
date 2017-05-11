package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.GrupoEntity;
import dc.model.dao.geral.produto.IGrupoDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("produtoGrupoDAO")
@SuppressWarnings("unchecked")
public class GrupoDAO extends AbstractCrudDAO<GrupoEntity> implements IGrupoDAO {

	@Override
	public Class<GrupoEntity> getEntityClass() {
		return GrupoEntity.class;
	}

	@Transactional
	public List<GrupoEntity> listaTodos() {
		return getSession().createQuery("from GrupoEntity").list();
	}

	@Transactional
	public List<GrupoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from ProdutoGrupo where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}
	
	@Transactional
	public List<GrupoEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from ProdutoGrupo where lower(nome) like :q").setParameter("q", q).list();
	}

}