package dc.servicos.dao.suprimentos.compra;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.RequisicaoCompraEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDAO")
public class RequisicaoDAO extends AbstractCrudDAO<RequisicaoCompraEntity> implements IRequisicaoDAO {

	@Override
	public Class<RequisicaoCompraEntity> getEntityClass() {
		return RequisicaoCompraEntity.class;
	}

	@Override
	public RequisicaoCompraEntity find(Serializable id) {
		RequisicaoCompraEntity requisicao = super.find(id);
		// workaround para lazy initialization exception
		requisicao.getRequisicaoDetalhes().size();

		return requisicao;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataRequisicao", "observacao", "colaborador","tipoRequisicao" };
	}

}