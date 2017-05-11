package dc.servicos.dao.suprimentos.compra;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraFornecedorCotacaoDAO")
public class FornecedorCotacaoDAO extends AbstractCrudDAO<FornecedorCotacaoEntity> implements IFornecedorCotacaoDAO {

	@Override
	public Class<FornecedorCotacaoEntity> getEntityClass() {
		return FornecedorCotacaoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "fornecedor" };
	}
	
	@Transactional
	public List<FornecedorCotacaoEntity> listaTodos() {
		return getSession().createQuery("from CompraFornecedorCotacao").list();
	}
	
}