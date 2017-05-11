package dc.servicos.dao.contabilidade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PlanoContaDAO extends AbstractCrudDAO<PlanoContaEntity> {

	@Override
	public Class<PlanoContaEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return PlanoContaEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Transactional
	public List<PlanoContaEntity> listaTodos() {
		return getSession().createQuery("from PlanoConta").list();
	}
}