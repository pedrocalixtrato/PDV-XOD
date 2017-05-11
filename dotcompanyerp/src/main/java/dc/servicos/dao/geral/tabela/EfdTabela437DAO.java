package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela437Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela437DAO extends AbstractCrudDAO<EfdTabela437Entity> implements IEfdTabela437DAO {

	@Override
	public Class<EfdTabela437Entity> getEntityClass() {
		return EfdTabela437Entity.class;
	}

	@Transactional
	public List<EfdTabela437Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela437").list();
	}

	@Transactional
	public List<EfdTabela437Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela437 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

	@Transactional
	public EfdTabela437Entity procuraPorCodigo(String codigo) {
		EfdTabela437Entity cod = null;
		Criteria c = getSession().createCriteria(EfdTabela437Entity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cod = (EfdTabela437Entity) c.uniqueResult();

		return cod;
	}

}