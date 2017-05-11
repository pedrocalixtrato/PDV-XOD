package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Frete;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FreteDAO extends AbstractCrudDAO<Frete> implements IFreteDAO {

	@Override
	public Class<Frete> getEntityClass() {
		return Frete.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"responsavel","placa","transportadora"};
	}
	
	@Transactional
	public List<Frete> listaTodos() {
		return getSession().createQuery("from Frete").list();
	}
	
	@Transactional
	public List<Frete> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from Frete where responsavel like :q")
				.setParameter("q", "%" + query + "%").list();
	}
	
}
