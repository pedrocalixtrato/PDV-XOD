package dc.servicos.dao.ponto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoRelogio;
import dc.entidade.ponto.PontoTurma;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoTurmaDAO extends AbstractCrudDAO<PontoRelogio> {

	@Override
	public Class<PontoRelogio> getEntityClass() {
		return PontoRelogio.class;
	}

	@Transactional
	public List<PontoRelogio> listaTodos() {
		return getSession().createQuery("from PontoTurma").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

	@Transactional
	public PontoTurma getPontoTurmaByCodigo(String codigo) {
		Criteria criteria = getSession().createCriteria(PontoTurma.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		PontoTurma pontoTurma = (PontoTurma) criteria.uniqueResult();

		return pontoTurma;
	}

}
