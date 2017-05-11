package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.TipoDesligamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalTipoDesligamentoDAO")
public class TipoDesligamentoDAO extends AbstractCrudDAO<TipoDesligamentoEntity> implements ITipoDesligamentoDAO {

	@Override
	public Class<TipoDesligamentoEntity> getEntityClass() {
		return TipoDesligamentoEntity.class;
	}

	@Transactional
	public List<TipoDesligamentoEntity> listaTodos() {
		return getSession().createQuery("from TipoDesligamento").list();
	}

	@Transactional
	public List<TipoDesligamentoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from TipoDesligamento where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

}