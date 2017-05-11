package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.TipoRecebimento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoRecebimentoDAO extends AbstractCrudDAO<TipoRecebimento> implements ITipoRecebimentoDAO {

	@Override
	public Class<TipoRecebimento> getEntityClass() {
		return TipoRecebimento.class;
	}

	@Transactional
	public List<TipoRecebimento> listaTodos() {
		return getSession().createQuery("from TipoRecebimento").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

}
