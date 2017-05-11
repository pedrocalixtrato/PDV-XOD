package dc.servicos.dao.ponto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoClassificacaoJornada;
import dc.entidade.ponto.PontoRelogio;
import dc.entidade.ponto.PontoTurma;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoClassificacaoJornadaDAO extends AbstractCrudDAO<PontoClassificacaoJornada> {

	@Override
	public Class<PontoClassificacaoJornada> getEntityClass() {
		return PontoClassificacaoJornada.class;
	}

	@Transactional
	public List<PontoRelogio> listaTodos() {
		return getSession().createQuery("from PontoClassificacaoJornada").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

	@Transactional
	public List<PontoClassificacaoJornada> getPontoClassificacaoJornadaByPadrao(String codigoPadrao) {
		Criteria criteria = getSession().createCriteria(PontoClassificacaoJornada.class);
		criteria.add(Restrictions.eq("padrao", codigoPadrao));
		List<PontoClassificacaoJornada> listaClassificacao = criteria.list();

		return listaClassificacao;
	}

}
