package dc.servicos.dao.geral.diverso;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.CepEntity;
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
public class CepDAO extends AbstractCrudDAO<CepEntity>{

	@Override
	public Class<CepEntity> getEntityClass() {
		return CepEntity.class;
	}
	
	
	@Transactional
	public List<CepEntity> listaTodos() {
		return getSession().createQuery("from Cep").list();
	}

	@Transactional
	public List<CepEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cep where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"cep", "logradouro"};
	}

}
