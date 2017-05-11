package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Fpas;
import dc.servicos.dao.administrativo.empresa.IFpasDAO;

@Repository
public class FpasDAO extends AbstractCrudDAO<Fpas> implements IFpasDAO {

	@Override
	public Class<Fpas> getEntityClass() {
		return Fpas.class;
	}

	@Transactional
	public List<Fpas> listaTodos() {
		return getSession().createQuery("from Fpas").list();
	}

	@Transactional
	public List<Fpas> procuraNomeContendo(String query) {
		return getSession().createQuery("from Fpas where cnae like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "cnae", "descricao" };
	}

	@Transactional
	public List<Fpas> getFpasList() {
		try {
			String sql = "SELECT new Fpas(ent.id, ent.cnae) FROM Fpas ent";

			List<Fpas> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}