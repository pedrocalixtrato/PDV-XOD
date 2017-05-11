package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.EquipamentoEntity;
import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ObservacaoDAO extends AbstractCrudDAO<ObservacaoEntity>{

	@Override
	public Class<ObservacaoEntity> getEntityClass() {
		return ObservacaoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"ordemServico.id"};
	} 
	
	@Transactional
	public List<EquipamentoEntity> listaTodos() {
		return getSession().createQuery("from ObservacaoEntity").list();
	}
	
	@Transactional  
	public ObservacaoEntity buscaObservacao(OrdemServicoEntity ordemServico){
		
		Criteria c = getSession().createCriteria(ObservacaoEntity.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (ObservacaoEntity)c.uniqueResult();
	}
}
