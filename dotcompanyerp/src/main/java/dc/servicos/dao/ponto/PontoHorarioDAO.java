package dc.servicos.dao.ponto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoHorario;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoHorarioDAO extends AbstractCrudDAO<PontoHorario> {

	@Override
	public Class<PontoHorario> getEntityClass() {
		return PontoHorario.class;
	}

	@Transactional
	public List<PontoHorario> listaTodos() {
		return getSession().createQuery("from PontoHorario").list();
	}

	@Override
	public String[] getDefaultSearchFields() {
 
		return new String[] { "" };
	}
	
	@Transactional
	public PontoHorario getPontoHorarioByCodigo(String codigoHorario)
	{
		Criteria criteria = getSession().createCriteria(PontoHorario.class);
		criteria.add(Restrictions.eq("codigo", codigoHorario));
		
		return (PontoHorario) criteria.uniqueResult();
	}

}
