package dc.servicos.dao.geral.diverso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.SetorEntity;
import dc.model.dao.geral.diverso.ISetorDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão a classe principal
 *         abstractCrudDao e dela herdamos alguns métodos, fazemos uma Conexão
 *         com o Banco, uma listagem E aqui herdamos também o Método do
 *         pesquisar, onde nela colocamos os campos que colocamos as anotações
 *         lá no TO (ENTIDADE), que vai ser pesquisado na Tela quando rodar o
 *         projeto.
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class SetorDAO extends AbstractCrudDAO<SetorEntity> implements ISetorDAO{

	@Override
	public Class<SetorEntity> getEntityClass() {
		return SetorEntity.class;
	}

	@Transactional
	public List<SetorEntity> listaTodos() {
		return getSession().createQuery("from Setor").list();
	}

	@Transactional
	public List<SetorEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Setor where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}
	

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<SetorEntity> listarTodos() {
		try {
			String sql = "FROM Setor ent WHERE (1 = 1)";

			List auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<SetorEntity>();
		}
	}

	@Transactional
	public List<SetorEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession().createQuery("from Setor where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}