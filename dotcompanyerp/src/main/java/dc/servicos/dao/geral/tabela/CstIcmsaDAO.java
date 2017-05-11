package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstIcmsaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CstIcmsaDAO extends AbstractCrudDAO<CstIcmsaEntity> implements ICstIcmsaDAO {

	@Override
	public Class<CstIcmsaEntity> getEntityClass() {
		return CstIcmsaEntity.class;
	}

	@Transactional
	public List<CstIcmsaEntity> listaTodos() {
		return getSession().createQuery("from CstIcmsa").list();
	}

	@Transactional
	public List<CstIcmsaEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from CstIcmsa where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}