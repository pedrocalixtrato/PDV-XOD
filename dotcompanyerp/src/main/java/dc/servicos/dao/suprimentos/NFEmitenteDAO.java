package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NotaFiscalEmitente;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.estoque.INFEmitenteDAO;

@Repository
public class NFEmitenteDAO extends AbstractCrudDAO<NotaFiscalEmitente> implements INFEmitenteDAO {

	@Override
	public Class<NotaFiscalEmitente> getEntityClass() {
		return NotaFiscalEmitente.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public NotaFiscalEmitente findByNota(NotaFiscal nfe) {
		NotaFiscalEmitente emitente = null;

		try {
			Criteria criteria = super.getSession().createCriteria(
					NotaFiscalEmitente.class);
			criteria.add(Restrictions.eq("nota", nfe));
			emitente = (NotaFiscalEmitente) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return emitente;
	}

}