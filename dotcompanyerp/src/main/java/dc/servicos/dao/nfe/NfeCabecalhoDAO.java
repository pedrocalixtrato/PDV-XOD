package dc.servicos.dao.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeCabecalhoDAO extends AbstractCrudDAO<NfeCabecalhoEntity> implements INfeCabecalhoDAO {

	/**
	 * DAOS
	 */

	@Autowired
	private NfeDetalheDAO nfeDetalheDAO;

	@Autowired
	private NfeDetEspecificoMedicamentoDAO ndeMedicamentoDAO;

	@Autowired
	private NfeDetEspecificoArmamentoDAO ndeArmamentoDAO;

	/**
	 * 
	 */

	@Override
	public Class<NfeCabecalhoEntity> getEntityClass() {
		return NfeCabecalhoEntity.class;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.nfe.INfeCabecalhoDAO#find(java.lang.String)
	 */
	@Override
	@Transactional
	public List<NfeCabecalhoEntity> find(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", "%" + s + "%");

			List<NfeCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	@Transactional
	public NfeCabecalhoEntity getEntity(Serializable id) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", id);

			NfeCabecalhoEntity entidade = (NfeCabecalhoEntity) query
					.uniqueResult();

			if (entidade == null) {
				entidade = new NfeCabecalhoEntity();
			}

			return entidade;
		} catch (Exception e) {
			return new NfeCabecalhoEntity();
		}
	}

	@Transactional
	public List<NfeCabecalhoEntity> listAll() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<NfeCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	@Transactional
	public List<NfeCabecalhoEntity> listAll(List<Serializable> ids) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.id IN (:lista)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("lista", ids);

			List<NfeCabecalhoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.nfe.INfeCabecalhoDAO#saveOrUpdateNfeCabecalho(dc.entidade.nfe.NfeCabecalhoEntity)
	 */
	@Override
	@Transactional
	public void saveOrUpdateNfeCabecalho(NfeCabecalhoEntity nfeCabecalho)
			throws Exception {
		super.saveOrUpdate(nfeCabecalho);

		List<NfeDetalheEntity> auxLista = nfeCabecalho.getNfeDetalheList();

		if (auxLista != null && !auxLista.isEmpty()) {
			for (NfeDetalheEntity ent : auxLista) {
				// NfeDetalheEntity ent = (NfeDetalheEntity) obj;
				ent.setNfeCabecalho(nfeCabecalho);

				this.nfeDetalheDAO.saveOrUpdate(ent);

				if (ent.getNdeMedicamentoList() != null
						&& !ent.getNdeMedicamentoList().isEmpty()) {
					for (NfeDetEspecificoMedicamentoEntity ent1 : ent
							.getNdeMedicamentoList()) {
						this.ndeMedicamentoDAO.saveOrUpdate(ent1);
					}
				}

				if (ent.getNdeArmamentoList() != null
						&& !ent.getNdeArmamentoList().isEmpty()) {
					for (NfeDetEspecificoArmamentoEntity ent1 : ent
							.getNdeArmamentoList()) {
						this.ndeArmamentoDAO.saveOrUpdate(ent1);
					}
				}
			}
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Código numérico", "Natureza da operação",
				"Indicador da forma de pagamento" };
	}

}