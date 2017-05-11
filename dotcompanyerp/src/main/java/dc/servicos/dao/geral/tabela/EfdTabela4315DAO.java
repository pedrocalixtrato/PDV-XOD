package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4315Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela4315DAO extends AbstractCrudDAO<EfdTabela4315Entity> implements IEfdTabela4315DAO {

	@Override
	public Class<EfdTabela4315Entity> getEntityClass() {
		return EfdTabela4315Entity.class;
	}

	@Transactional
	public List<EfdTabela4315Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela4315").list();
	}

	@Transactional
	public List<EfdTabela4315Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela4315 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

}