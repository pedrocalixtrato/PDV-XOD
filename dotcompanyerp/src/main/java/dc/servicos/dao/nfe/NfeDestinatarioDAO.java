package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDestinatarioDAO extends AbstractCrudDAO<NfeDestinatarioEntity> implements INfeDestinatarioDAO {

	@Override
	public Class<NfeDestinatarioEntity> getEntityClass() {
		return NfeDestinatarioEntity.class;
	}

	@Transactional
	public List<NfeDestinatarioEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDestinatarioEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDestinatarioEntity>();
		}
	}

	@Transactional
	public List<NfeDestinatarioEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDestinatarioEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDestinatarioEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.nfe.I#getEntidade(dc.entidade.nfe.NfeCabecalhoEntity)
	 */
	@Override
	@Transactional
	public NfeDestinatarioEntity getEntidade(NfeCabecalhoEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.nfeCabecalho = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent);

			NfeDestinatarioEntity entidade = (NfeDestinatarioEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeDestinatarioEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeDestinatarioEntity();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "INSS", "Serviço", "Valor mensal", "Valor 13" };
	}

}