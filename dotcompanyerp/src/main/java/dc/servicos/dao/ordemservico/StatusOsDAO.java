package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.StatusOsEntity;
import dc.model.dao.ordemservico.IStatusOsDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class StatusOsDAO extends AbstractCrudDAO<StatusOsEntity> implements IStatusOsDAO {

	@Override
	public Class<StatusOsEntity> getEntityClass() {
		return StatusOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<StatusOsEntity> listaTodos() {
		return getSession().createQuery("from StatusOsEntity").list();
	}
}


