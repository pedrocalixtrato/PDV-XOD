package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Teste;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class testeDAO extends AbstractCrudDAO<Teste>{

	@Override
	public Class<Teste> getEntityClass() {
		return Teste.class;
	}

	@Transactional
	public List<Teste> listaTodos() {
		return getSession().createQuery("from Teste").list();
	}

	@Transactional
	public List<Teste> procuraNomeContendo(String query) {
		return getSession().createQuery("from Teste where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}

}
