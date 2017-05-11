package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CstIcmsbDAO extends AbstractCrudDAO<CstIcmsbEntity> implements ICstIcmsbDAO {

	@Override
	public Class<CstIcmsbEntity> getEntityClass() {
		return CstIcmsbEntity.class;
	}

	@Transactional
	public List<CstIcmsbEntity> listaTodos() {
		return getSession().createQuery("from CstIcmsb").list();
	}

	@Transactional
	public List<CstIcmsbEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from CstIcmsb where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}