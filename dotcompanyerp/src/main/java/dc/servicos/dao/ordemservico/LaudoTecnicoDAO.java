package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LaudoTecnicoDAO extends AbstractCrudDAO<LaudoTecnicoEntity>{

	@Override
	public Class<LaudoTecnicoEntity> getEntityClass() {
		return LaudoTecnicoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<LaudoTecnicoEntity> listaTodos() {
		return getSession().createQuery("from LaudoTecnicoEntity").list();
	}
	
	@Transactional  
	public LaudoTecnicoEntity buscaLaudoTecnico(OrdemServicoEntity ordemServico){
		
		Criteria c = getSession().createCriteria(LaudoTecnicoEntity.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (LaudoTecnicoEntity)c.uniqueResult();
	}
}
