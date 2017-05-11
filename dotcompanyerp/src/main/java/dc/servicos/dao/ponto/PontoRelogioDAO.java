package dc.servicos.dao.ponto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoRelogio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoRelogioDAO extends AbstractCrudDAO<PontoRelogio> {

	@Override
	public Class<PontoRelogio> getEntityClass() {
		return PontoRelogio.class;
	}

	@Transactional
	public List<PontoRelogio> listaTodos() {
		return getSession().createQuery("from PontoRelogio").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

	@Transactional
	public PontoRelogio getPontoRelogioByNumeroSerie(String numeroSerie) {
		Criteria criteria = getSession().createCriteria(PontoRelogio.class);
		criteria.add(Restrictions.eq("numeroSerie", numeroSerie));
		PontoRelogio relogio = (PontoRelogio) criteria.uniqueResult();

		return relogio;
	}

}
