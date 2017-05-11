package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraTipoPedidoDAO")
public class TipoPedidoDAO extends AbstractCrudDAO<TipoPedidoEntity> {

	@Override
	public Class<TipoPedidoEntity> getEntityClass() {
		return TipoPedidoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "nome" };
	}

}