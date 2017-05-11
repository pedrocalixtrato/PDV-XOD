package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.AcessorioEntity;
import dc.model.dao.ordemservico.IAcessorioDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class AcessorioDAO extends AbstractCrudDAO<AcessorioEntity> implements IAcessorioDAO{

	@Override
	public Class<AcessorioEntity> getEntityClass() {
		return AcessorioEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<AcessorioEntity> listaTodos() {
		return getSession().createQuery("from AcessorioEntity").list();
	}
}
