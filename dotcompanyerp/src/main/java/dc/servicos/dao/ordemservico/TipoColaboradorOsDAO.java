package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.TipoColaboradorOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoColaboradorOsDAO extends AbstractCrudDAO<TipoColaboradorOsEntity> implements ITipoColaboradorOsDAO{

	@Override
	public Class<TipoColaboradorOsEntity> getEntityClass() {
		return TipoColaboradorOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<TipoColaboradorOsEntity> listaTodos() {
		return getSession().createQuery("from TipoColaboradorOsEntity").list();
	}
}


