package dc.servicos.dao.ponto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoHorarioAutorizado;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoHorarioAutorizadoDAO extends AbstractCrudDAO<PontoHorarioAutorizado> {

	@Override
	public Class<PontoHorarioAutorizado> getEntityClass() {
		return PontoHorarioAutorizado.class;
	}

	@Transactional
	public List<PontoHorarioAutorizado> listaTodos() {
		return getSession().createQuery("from PontoHorarioAutorizado").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
