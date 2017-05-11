package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.entidade.geral.tabela.TipoReceitaDipiEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoReceitaDipiDAO extends AbstractCrudDAO<TipoReceitaDipiEntity> implements ITipoReceitaDipiDAO {

	@Override
	public Class<TipoReceitaDipiEntity> getEntityClass() {
		return TipoReceitaDipiEntity.class;
	}

	@Transactional
	public TipoReceitaDipiEntity procuraPorCodigo(String codigo) {
		TipoReceitaDipiEntity tipo = null;
		Criteria c = getSession().createCriteria(TipoReceitaDipiEntity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		tipo = (TipoReceitaDipiEntity) c.uniqueResult();

		return tipo;
	}

	@Transactional
	public List<CstCofinsEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from CstCofins where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}