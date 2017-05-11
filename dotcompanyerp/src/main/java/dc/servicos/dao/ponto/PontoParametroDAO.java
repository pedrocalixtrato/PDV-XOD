package dc.servicos.dao.ponto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoParametro;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoParametroDAO extends AbstractCrudDAO<PontoParametro> {

	@Override
	public Class<PontoParametro> getEntityClass() {
		return PontoParametro.class;
	}

	@Transactional
	public List<PontoParametro> listaTodos() {
		return getSession().createQuery("from PontoParametro").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

	@Transactional
	public PontoParametro getPontoParametroByMesAno(String mesAno) {
		Criteria criteria = getSession().createCriteria(PontoParametro.class);
		criteria.add(Restrictions.eq("mesAno", mesAno));
		return (PontoParametro) criteria.uniqueResult();
	}

}
