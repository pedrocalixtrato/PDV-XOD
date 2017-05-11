package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.Adiantamento;
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
public class AdiantamentoDAO extends AbstractCrudDAO<Adiantamento> implements IAdiantamentoDAO{

	@Override
	public Class<Adiantamento> getEntityClass() {
		return Adiantamento.class;
	}

	@Transactional
	public List<Adiantamento> listaTodos() {
		return getSession().createQuery("from Adiantamento").list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IAdiantamentoDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Adiantamento> procuraNomeContendo(String query) {
		return getSession().createQuery("from Adiantamento where valor like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"idLancamentoPagar","dataAdiantamento", "valor", "observacoes"};
	}
	
	@Transactional
	public List<Adiantamento> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Adiantamento where lower(valor) like :q").setParameter("q", q).list();
	}

}
