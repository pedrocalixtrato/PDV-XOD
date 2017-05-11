package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.PlanoNaturezaFinanceira;
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
public class PlanoNaturezaFinanceiraDAO extends AbstractCrudDAO<PlanoNaturezaFinanceira> implements IPlanoNaturezaFinanceiraDAO {
	
	@Override
	public Class<PlanoNaturezaFinanceira> getEntityClass() {
		return PlanoNaturezaFinanceira.class;
	}
	
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IPlanoNaturezaFinanceiraDAO#listCentros(dc.entidade.financeiro.PlanoNaturezaFinanceira)
	 */
	@Override
	@Transactional
	public List<NaturezaFinanceira> listCentros(PlanoNaturezaFinanceira planoNatureza) {
		return getSession().createQuery("from NaturezaFinanceira where planoNatureza.id = :bid").setParameter("bid", planoNatureza.getId()).list();
	}


	@Transactional
	public List<PlanoNaturezaFinanceira> listaTodos() {
		return getSession().createQuery("from PlanoNaturezaFinanceira").list();
	}

	@Transactional
	public List<PlanoNaturezaFinanceira> procuraNomeContendo(String query) {
		return getSession().createQuery("from PlanoNaturezaFinanceira where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome", "mascara","niveis","dataInclusao"};
	}
}
