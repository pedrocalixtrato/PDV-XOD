package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4314Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela4314DAO extends AbstractCrudDAO<EfdTabela4314Entity> implements IEfdTabela4314DAO {

	@Override
	public Class<EfdTabela4314Entity> getEntityClass() {
		return EfdTabela4314Entity.class;
	}

	@Transactional
	public List<EfdTabela4314Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela4314").list();
	}

	@Transactional
	public List<EfdTabela4314Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela4314 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

}