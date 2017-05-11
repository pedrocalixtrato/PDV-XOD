package dc.servicos.dao.geral.diverso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PaisDAO extends AbstractCrudDAO<PaisEntity> implements IPaisDAO {

	@Override
	public Class<PaisEntity> getEntityClass() {
		return PaisEntity.class;
	}

	@Transactional
	public List<PaisEntity> listaTodos() {
		try {
			String sql = "SELECT - FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			sql = sql.replace("-", "new PaisEntity(ent.id, ent.nomePtbr)");

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	@Transactional
	public List<PaisEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	@Transactional
	public List<PaisEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nomeIngles", "nomePtbr", "sigla2", "sigla3" };
	}

}