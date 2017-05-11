package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.NivelFormacaoEntity;
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
public class NivelFormacaoDAO extends AbstractCrudDAO<NivelFormacaoEntity> implements INivelFormacaoDAO{

	@Override
	public Class<NivelFormacaoEntity> getEntityClass() {
		return NivelFormacaoEntity.class;
	}
	
	@Transactional
	public List<NivelFormacaoEntity> listaTodos() {
		return getSession().createQuery("from NivelFormacao").list();
	}

	@Transactional
	public List<NivelFormacaoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from NivelFormacao where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome","descricao"};
	}
	
	@Transactional
	public List<NivelFormacaoEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from NivelFormacao where lower(nome) like :q").setParameter("q", q).list();
	}

}
