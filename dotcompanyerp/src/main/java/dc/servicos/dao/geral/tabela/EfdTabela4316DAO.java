package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4316Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela4316DAO extends AbstractCrudDAO<EfdTabela4316Entity> implements IEfdTabela4316DAO {

	@Override
	public Class<EfdTabela4316Entity> getEntityClass() {
		return EfdTabela4316Entity.class;
	}

	@Transactional
	public List<EfdTabela4316Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela4316").list();
	}

	@Transactional
	public List<EfdTabela4316Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela4316 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

}