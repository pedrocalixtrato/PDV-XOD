package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela439Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela439DAO extends AbstractCrudDAO<EfdTabela439Entity> implements IEfdTabela439DAO {

	@Override
	public Class<EfdTabela439Entity> getEntityClass() {
		return EfdTabela439Entity.class;
	}

	@Transactional
	public List<EfdTabela439Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela439").list();
	}

	@Transactional
	public List<EfdTabela439Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela439 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

	@Transactional
	public EfdTabela439Entity procuraPorCodigo(String codigo) {
		EfdTabela439Entity cod = null;
		Criteria c = getSession().createCriteria(EfdTabela439Entity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cod = (EfdTabela439Entity) c.uniqueResult();

		return cod;
	}

}