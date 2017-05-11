package dc.servicos.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.GrupoTributarioEntity;
import dc.model.dao.tributario.IGrupoTributarioDAO;
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
public class GrupoTributarioDAO extends AbstractCrudDAO<GrupoTributarioEntity> implements IGrupoTributarioDAO {

	@Override
	public Class<GrupoTributarioEntity> getEntityClass() {
		return GrupoTributarioEntity.class;
	}
	
	@Transactional
	public List<GrupoTributarioEntity> listaTodos() {
		return getSession().createQuery("from GrupoTributario").list();
	}

	@Transactional
	public List<GrupoTributarioEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from GrupoTributario where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"descricao", "observacao", "origemMercadoria"};
	}
	
	@Transactional
	public List<GrupoTributarioEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from GrupoTributario where lower(descricao) like :q").setParameter("q", q).list();
	}

}
