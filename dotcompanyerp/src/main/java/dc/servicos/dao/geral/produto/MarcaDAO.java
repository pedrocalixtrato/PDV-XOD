package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.MarcaEntity;
import dc.model.dao.geral.produto.IMarcaDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("produtoMarcaDAO")
@SuppressWarnings("unchecked")
public class MarcaDAO extends AbstractCrudDAO<MarcaEntity> implements IMarcaDAO {

	@Override
	public Class<MarcaEntity> getEntityClass() {
		return MarcaEntity.class;
	}

	@Transactional
	public List<MarcaEntity> listaTodos() {
		return getSession().createQuery("from ProdutoMarca").list();
	}

	@Transactional
	public List<MarcaEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from ProdutoMarca where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<MarcaEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from ProdutoMarca where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}