package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.TipoRequisicaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraTipoRequisicaoDAO")
public class TipoRequisicaoDAO extends AbstractCrudDAO<TipoRequisicaoEntity> {

	@Override
	public Class<TipoRequisicaoEntity> getEntityClass() {
		return TipoRequisicaoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "nome" };
	}

}