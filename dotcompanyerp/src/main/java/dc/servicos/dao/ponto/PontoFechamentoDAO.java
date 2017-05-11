package dc.servicos.dao.ponto;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoRelogio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoFechamentoDAO extends AbstractCrudDAO<PontoFechamentoJornada> {

	@Override
	public Class<PontoFechamentoJornada> getEntityClass() {
		return PontoFechamentoJornada.class;
	}

	@Transactional
	public List<PontoRelogio> listaTodos() {
		return getSession().createQuery("from PontoFechamentoJornada").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}
	
	@Transactional
	public List<PontoFechamentoJornada> searchByInterval(Date dataInicial, Date dataFinal) {
		Criteria criteria = getSession().createCriteria(PontoFechamentoJornada.class);
		 criteria.add(Restrictions.between("dataFechamento", dataInicial, dataFinal));
         criteria.addOrder(Order.asc("colaborador"));
         criteria.addOrder(Order.asc("dataFechamento"));

		List<PontoFechamentoJornada> fechamentos = criteria.list();

		return fechamentos;
	}

}
