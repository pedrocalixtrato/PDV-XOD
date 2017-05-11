package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NFeTransporte;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.estoque.INFeTransporteDAO;

@Component
public class NFeTransporteDAO extends AbstractCrudDAO<NFeTransporte> implements INFeTransporteDAO {

	@Override
	public Class<NFeTransporte> getEntityClass() {
		return NFeTransporte.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public NFeTransporte buscaTransportePorNota(NotaFiscal nota) {
		Criteria c = getSession().createCriteria(NFeTransporte.class);
		c.add(Restrictions.eq("notaFiscal", nota));

		return (NFeTransporte) c.uniqueResult();
	}

}