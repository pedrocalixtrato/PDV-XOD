package dc.servicos.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

//@Repository("pessoalColaboradorDAO")
@Repository()
public class ColaboradorDAO extends AbstractCrudDAO<ColaboradorEntity> implements IColaboradorDAO {

	@Override
	public Class<ColaboradorEntity> getEntityClass() {
		return ColaboradorEntity.class;
	}

	@Transactional
	public List<ColaboradorEntity> listaTodos() {
		try {
			String sql = "FROM Colaborador ent WHERE (1 = 1)";

			List auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ColaboradorEntity>();
		}
	}

	@Transactional
	public List<ColaboradorEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from Colaborador where observacao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "pessoa","tipoColaborador","cargo","setor","sindicato","nivelFormacao","matricula", "codigoTurmaPonto",
				"dataCadastro", "dataAdmissao", "vencimentoFerias",
				"dataTransferencia" };
	}

	@Transactional
	public ColaboradorEntity getColaboradorByPisNumero(String pisNumero) {
		Criteria criteria = getSession()
				.createCriteria(ColaboradorEntity.class);
		criteria.add(Restrictions.eq("pisNumero", pisNumero));
		ColaboradorEntity colaborador = (ColaboradorEntity) criteria
				.uniqueResult();

		return colaborador;
	}

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@Transactional
	public List<ColaboradorEntity> colaboradorLista() {
		try {
			String sql = "SELECT new Colaborador(ent.id, ent.matricula) FROM Colaborador ent"
					+ " WHERE (1 = 1)";

			List<ColaboradorEntity> auxLista = getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ColaboradorEntity>();
		}
	}

	@Override
	public List<ColaboradorEntity> listaVendedores() {
		try {
			String sql = "SELECT c FROM ColaboradorEntity c INNER JOIN c.tipoColaborador t WHERE (1 = 1) AND t.id = 40 ";

			Query query = super.getSession().createQuery(sql);

			List<ColaboradorEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<ColaboradorEntity> listaTecnicos() {
		try {
			String sql = "SELECT c FROM ColaboradorEntity c INNER JOIN c.tipoColaborador t WHERE (1 = 1) AND t.id = 41 ";

			Query query = super.getSession().createQuery(sql);

			List<ColaboradorEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}


	/**
	 * ********************************************************
	 */

}