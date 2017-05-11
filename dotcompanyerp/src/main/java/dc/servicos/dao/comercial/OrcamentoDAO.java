package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Orcamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class OrcamentoDAO extends AbstractCrudDAO<Orcamento> implements IOrcamentoDAO {

	@Override
	public Class<Orcamento> getEntityClass() {
		return Orcamento.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "valorTotal","observacao" };
	}

	@Transactional
	public List<Orcamento> listaTodos() {
		String sql = "FROM Orcamento o";

		return getSession().createQuery(sql).list();
	}

}