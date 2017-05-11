package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.Cheque;
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
public class ChequeDAO extends AbstractCrudDAO<Cheque> implements IChequeDAO{

	@Override
	public Class<Cheque> getEntityClass() {
		return Cheque.class;
	}
	
	@Transactional
	public List<Cheque> listaTodos() {
		return getSession().createQuery("from Cheque").list();
	}

	@Transactional
	public List<Cheque> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cheque where statusCheque like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"idTalonarioCheque","statusCheque", "numero", "dataStatus"};
	}

}
