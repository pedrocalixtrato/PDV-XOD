package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository(value = "contabilidadePlanoContaRefSpedDAO")
@SuppressWarnings("unchecked")
public class PlanoContaRefSpedDAO extends
		AbstractCrudDAO<PlanoContaRefSped> implements IPlanoContaRefSpedDAO {

	@Override
	public Class<PlanoContaRefSped> getEntityClass() {
		return PlanoContaRefSped.class;
	}

	@Transactional
	public List<PlanoContaRefSped> listarTodos() {
		try {
			String sql = "FROM PlanoContaRefSped ent WHERE (1 = 1)";

			List<PlanoContaRefSped> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaRefSped>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.contabilidade.planoconta.IPlanoContaRefSpedDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<PlanoContaRefSped> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PlanoContaRefSped ent WHERE (1 = 1) AND ent.descricao LIKE :q";

			List<PlanoContaRefSped> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaRefSped>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Descrição", "Orientações", "Início da validade",
				"Término da validade" };
	}

}