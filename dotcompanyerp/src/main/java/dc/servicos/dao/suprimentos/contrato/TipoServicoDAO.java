package dc.servicos.dao.suprimentos.contrato;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.contrato.TipoServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosContratoTipoServicoDAO")
public class TipoServicoDAO extends AbstractCrudDAO<TipoServicoEntity> implements ITipoServicoDAO {

	@Override
	public Class<TipoServicoEntity> getEntityClass() {
		return TipoServicoEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}