package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CsosnaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CsosnaDAO extends AbstractCrudDAO<CsosnaEntity> implements ICsosnaDAO {

	@Override
	public Class<CsosnaEntity> getEntityClass() {
		return CsosnaEntity.class;
	}

	@Transactional
	public List<CsosnaEntity> listaTodos() {
		return getSession().createQuery("from Csosna").list();
	}

	@Transactional
	public List<CsosnaEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Csosna where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

}