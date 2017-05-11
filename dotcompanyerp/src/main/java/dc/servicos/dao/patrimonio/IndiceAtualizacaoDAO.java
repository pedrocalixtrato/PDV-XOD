package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.IndiceAtualizacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class IndiceAtualizacaoDAO extends
		AbstractCrudDAO<IndiceAtualizacaoEntity> {

	@Override
	public Class<IndiceAtualizacaoEntity> getEntityClass() {
		return IndiceAtualizacaoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<IndiceAtualizacaoEntity> listarTodos() {
		try {
			String sql = "FROM IndiceAtualizacaoEntity ent WHERE (1 = 1)";

			List<IndiceAtualizacaoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<IndiceAtualizacaoEntity>();
		}
	}

	@Transactional
	public List<IndiceAtualizacaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM IndiceAtualizacaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<IndiceAtualizacaoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<IndiceAtualizacaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

}