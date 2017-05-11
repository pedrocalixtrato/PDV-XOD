package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.CidadeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CidadeDAO extends AbstractCrudDAO<CidadeEntity>{

	@Override
	public Class<CidadeEntity> getEntityClass() {
		return CidadeEntity.class;
	}
	
	@Transactional
	public List<CidadeEntity> listaTodos() {
		return getSession().createQuery("from Cidade").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[]{"nome"};
	}

}
