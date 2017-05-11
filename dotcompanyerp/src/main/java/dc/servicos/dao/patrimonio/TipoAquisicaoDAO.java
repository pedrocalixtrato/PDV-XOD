package dc.servicos.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class TipoAquisicaoDAO extends AbstractCrudDAO<TipoAquisicaoEntity> {

	@Override
	public Class<TipoAquisicaoEntity> getEntityClass() {
		return TipoAquisicaoEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<TipoAquisicaoEntity> listarTodos() {
		try {
			String sql = "FROM TipoAquisicaoEntity ent WHERE (1 = 1)";

			List<TipoAquisicaoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoAquisicaoEntity>();
		}
	}

	@Transactional
	public List<TipoAquisicaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM TipoAquisicaoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<TipoAquisicaoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<TipoAquisicaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Tipo", "Nome", "Descrição" };
	}

}