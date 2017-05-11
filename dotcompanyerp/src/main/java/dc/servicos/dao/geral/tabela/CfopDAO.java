package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CfopEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CfopDAO extends AbstractCrudDAO<CfopEntity> implements ICfopDAO {

	@Override
	public Class<CfopEntity> getEntityClass() {
		return CfopEntity.class;
	}

	@Transactional
	public List<CfopEntity> listaTodos() {
		return getSession().createQuery("from Cfop").list();
	}

	@Transactional
	public List<CfopEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cfop where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao", "aplicacao" };
	}

}