package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalTipoRelacionamentoDAO")
public class TipoRelacionamentoDAO extends
		AbstractCrudDAO<TipoRelacionamentoEntity> implements ITipoRelacionamentoDAO {

	@Override
	public Class<TipoRelacionamentoEntity> getEntityClass() {
		return TipoRelacionamentoEntity.class;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.pessoal.ITipoRelacionamentoDAO#listaTodos()
	 */
	@Override
	@Transactional
	public List<TipoRelacionamentoEntity> listaTodos() {
		return getSession().createQuery("from TipoRelacionamento").list();
	}

	@Transactional
	public List<TipoRelacionamentoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from TipoRelacionamento where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "nome", "descricao" };
	}

}