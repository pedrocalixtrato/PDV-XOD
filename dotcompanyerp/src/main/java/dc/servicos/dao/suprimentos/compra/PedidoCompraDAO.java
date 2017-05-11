package dc.servicos.dao.suprimentos.compra;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraPedidoCompraDAO")
public class PedidoCompraDAO extends AbstractCrudDAO<PedidoEntity> implements IPedidoCompraDAO{

	@Override
	public Class<PedidoEntity> getEntityClass() {
		return PedidoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"fornecedor","tipoPedido", "baseCalculoIcms", "baseCalculoIcmsSt", "contato","dataPedido","dataPrevisaoPagamento" };
	}

	@Transactional(readOnly = true)
	public boolean existsPedidoDetalheByCotacao(Integer id) {
		Query query = getSession()
				.createQuery(
						"from CotacaoPedidoDetalhe cpd where cpd.compraCotacaoDetalhe.id = ?");
		query.setInteger(0, id);

		return query.list().size() > 0;
	}

}