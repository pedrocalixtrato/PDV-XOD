package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ContaCaixa;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class ContaCaixaDAO extends AbstractCrudDAO<ContaCaixa> implements IContaCaixaDAO{

	@Override
	public Class<ContaCaixa> getEntityClass() {
		return ContaCaixa.class;
	}
		
	@Transactional
	public List<ContaCaixa> listaTodos() {
		return getSession().createQuery("from ContaCaixa").list();
	}

	@Transactional
	public List<ContaCaixa> procuraNomeContendo(String query) {
		return getSession().createQuery("from ContaCaixa where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome", "digito","descricao"};
	}
	
	@Transactional
	public List<ContaCaixa> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from ContaCaixa where lower(nome) like :q").setParameter("q", q).list();
	}

}
