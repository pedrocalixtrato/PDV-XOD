package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ModeloEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ModeloDAO extends AbstractCrudDAO<ModeloEntity> implements IModeloDAO{

	@Override
	public Class<ModeloEntity> getEntityClass() {
		return ModeloEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<ModeloEntity> listaTodos() {
		return getSession().createQuery("from ModeloEntity").list();
	}
}
