package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ColaboradorOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ColaboradorOsDAO extends AbstractCrudDAO<ColaboradorOsEntity> implements IColaboradorOsDAO {


	@Override
	public Class<ColaboradorOsEntity> getEntityClass() {
		return ColaboradorOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<ColaboradorOsEntity> listaTodos() {
		return getSession().createQuery("from ColaboradorOsEntity").list();
	}
}
