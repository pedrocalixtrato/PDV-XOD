package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.RequisicaoInternaCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosEstoqueRequisicaoInternaCabecalhoDAO")
public class RequisicaoInternaCabecalhoDAO extends
		AbstractCrudDAO<RequisicaoInternaCabecalhoEntity> implements IRequisicaoInternaCabecalhoDAO {

	@Override
	public Class<RequisicaoInternaCabecalhoEntity> getEntityClass() {
		return RequisicaoInternaCabecalhoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "data" };
		// requisicao.getRequisicaoDetalhes().size();
	}

	// @Transactional
	// public List<RequisicaoInterna> findBySetor(){
	//
	// List<RequisicaoInterna> lista = new ArrayList<>();
	// Criteria c = createCriteria(RequisicaoInterna.class);
	// System.out.println(c);
	// return lista;
	// }

}