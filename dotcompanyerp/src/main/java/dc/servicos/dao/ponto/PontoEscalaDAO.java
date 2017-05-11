package dc.servicos.dao.ponto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoEscala;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoEscalaDAO extends AbstractCrudDAO<PontoEscala> {

	@Override
	public Class<PontoEscala> getEntityClass() {
		return PontoEscala.class;
	}

	@Transactional
	public List<PontoEscala> listaTodos() {
		return getSession().createQuery("from PontoEscala").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
