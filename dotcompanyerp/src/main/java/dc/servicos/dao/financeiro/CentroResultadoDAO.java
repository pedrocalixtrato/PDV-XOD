package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.CentroResultado;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class CentroResultadoDAO extends AbstractCrudDAO<CentroResultado> implements ICentroResultadoDAO{

	@Override
	public Class<CentroResultado> getEntityClass() {
		return CentroResultado.class;
	}
	
	
	@Transactional
	public List<CentroResultado> listCentroResultado(CentroResultado centroresultado) {
		return getSession().createQuery("from CentroResultado where centroresultado.id = :bid").setParameter("bid", centroresultado.getId()).list();
	}


	@Transactional
	public List<CentroResultado> listaTodos() {
		return getSession().createQuery("from CentroResultado").list();
	}

	@Transactional
	public List<CentroResultado> procuraNomeContendo(String query) {
		return getSession().createQuery("from CentroResultado where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"planoCentroResultado","descricao", "sofreRateio","classificacao"};
	}	
}
