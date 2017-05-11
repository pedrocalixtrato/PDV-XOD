package dc.servicos.dao.ponto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ponto.PontoAbono;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoAbonoDAO extends AbstractCrudDAO<PontoAbono> implements IPontoAbonoDAO {

	@Override
	public Class<PontoAbono> getEntityClass() {
		return PontoAbono.class;
	}

	@Transactional
	public List<PontoAbono> listaTodos() {
		return getSession().createQuery("from PontoAbono").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
