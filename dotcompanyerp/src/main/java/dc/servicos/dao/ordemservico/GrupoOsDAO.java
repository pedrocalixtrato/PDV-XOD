package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.GrupoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GrupoOsDAO extends AbstractCrudDAO<GrupoOsEntity> implements IGrupoOsDAO{
 
	@Override
	public Class<GrupoOsEntity> getEntityClass() {
		return GrupoOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<GrupoOsEntity> listaTodos() {
		return getSession().createQuery("from GrupoOsEntity").list();
	}
}


