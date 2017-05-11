package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ModeloOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ModeloOsDAO extends AbstractCrudDAO<ModeloOsEntity> {
	
	
		@Override
		public Class<ModeloOsEntity> getEntityClass() {
			return ModeloOsEntity.class;
		}

		public String[] getDefaultSearchFields() {
			return new String[] {"nome"};
		}
		
		@Transactional
		public List<ModeloOsEntity> listaTodos() {
			return getSession().createQuery("from ModeloOsEntity").list();
		}

}
