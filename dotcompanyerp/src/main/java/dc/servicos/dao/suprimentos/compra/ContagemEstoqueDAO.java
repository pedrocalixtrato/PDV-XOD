package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraContagemEstoqueDAO")
public class ContagemEstoqueDAO extends
		AbstractCrudDAO<ContagemCabecalhoEntity> {

	@Override
	public Class<ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	// @Override
	// public ContagemEstoque find(Serializable id) {
	// ContagemEstoque contagemEstoque = super.find(id);
	// // workaround para lazy initialization exception
	// //contagemEstoque.getContagemDetalhes().size();
	// return contagemEstoque;
	// }

	public String[] getDefaultSearchFields() {
		return new String[] { "data" };
	}

}