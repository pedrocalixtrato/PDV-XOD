package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4313Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela4313DAO extends AbstractCrudDAO<EfdTabela4313Entity> implements IEfdTabela4313DAO {

	@Override
	public Class<EfdTabela4313Entity> getEntityClass() {
		return EfdTabela4313Entity.class;
	}

	@Transactional
	public List<EfdTabela4313Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela4313").list();
	}

	@Transactional
	public List<EfdTabela4313Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela4313 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

}