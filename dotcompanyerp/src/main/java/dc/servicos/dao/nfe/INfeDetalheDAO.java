package dc.servicos.dao.nfe;

import java.io.Serializable;
import java.util.List;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.model.dao.AbstractDAO;

public interface INfeDetalheDAO extends AbstractDAO<NfeDetalheEntity> {

	public abstract NfeDetalheEntity getEntidade(Serializable itemId);

	public abstract List<NfeDetalheEntity> getLista(NfeCabecalhoEntity ent);

	public abstract List<NfeDetalheEntity> findByNfeDetalhe(LoteProdutoEntity loteProduto);

	public abstract List<NfeDetalheEntity> listarTodos();

}