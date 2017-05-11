package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDeclaracaoImportacaoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDeclaracaoImportacaoDAO extends
		AbstractCrudDAO<NfeDeclaracaoImportacaoEntity> implements INfeDeclaracaoImportacaoDAO {

	@Override
	public Class<NfeDeclaracaoImportacaoEntity> getEntityClass() {
		return NfeDeclaracaoImportacaoEntity.class;
	}

	@Transactional
	public List<NfeDeclaracaoImportacaoEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDeclaracaoImportacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDeclaracaoImportacaoEntity>();
		}
	}

	@Transactional
	public List<NfeDeclaracaoImportacaoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDeclaracaoImportacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDeclaracaoImportacaoEntity>();
		}
	}

	@Transactional
	public List<NfeDeclaracaoImportacaoEntity> getLista(NfeDetalheEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeDetalhe = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			List<NfeDeclaracaoImportacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDeclaracaoImportacaoEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Número do documento" };
	}

}