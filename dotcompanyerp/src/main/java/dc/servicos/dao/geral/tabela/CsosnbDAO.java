package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CsosnbEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CsosnbDAO extends AbstractCrudDAO<CsosnbEntity> implements ICsosnbDAO {

	@Override
	public Class<CsosnbEntity> getEntityClass() {
		return CsosnbEntity.class;
	}

	@Transactional
	public List<CsosnbEntity> listaTodos() {
		return getSession().createQuery("from Csosnb").list();
	}

	@Transactional
	public List<CsosnbEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Csosnb where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}