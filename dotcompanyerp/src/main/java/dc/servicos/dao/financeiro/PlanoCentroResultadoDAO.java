package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.CentroResultado;
import dc.entidade.financeiro.PlanoCentroResultado;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o Método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/

@Repository
@SuppressWarnings("unchecked")
public class PlanoCentroResultadoDAO extends AbstractCrudDAO<PlanoCentroResultado> implements IPlanoCentroResultadoDAO {
	
	@Override
	public Class<PlanoCentroResultado> getEntityClass() {
		return PlanoCentroResultado.class;
	}
	
	
	@Transactional
	public List<CentroResultado> listCentros(PlanoCentroResultado planoCentro) {
		return getSession().createQuery("from CentroResultado where planoCentroResultado.id = :bid").setParameter("bid", planoCentro.getId()).list();
	}


	@Transactional
	public List<PlanoCentroResultado> listaTodos() {
		return getSession().createQuery("from PlanoCentroResultado").list();
	}

	@Transactional
	public List<PlanoCentroResultado> procuraNomeContendo(String query) {
		return getSession().createQuery("from PlanoCentroResultado where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome", "mascara","niveis","dataInclusao"};
	}

}
