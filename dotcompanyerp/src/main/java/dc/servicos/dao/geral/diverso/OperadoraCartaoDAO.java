package dc.servicos.dao.geral.diverso;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.OperadoraCartaoEntity;
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
public class OperadoraCartaoDAO extends AbstractCrudDAO<OperadoraCartaoEntity>{

	@Override
	public Class<OperadoraCartaoEntity> getEntityClass() {
		return OperadoraCartaoEntity.class;
	}

	@Transactional
	public List<OperadoraCartaoEntity> listaTodos() {
		return getSession().createQuery("from OperadoraCartao").list();
	}

	@Transactional
	public List<OperadoraCartaoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from OperadoraCartao where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome", "bandeira"};
	}
}
