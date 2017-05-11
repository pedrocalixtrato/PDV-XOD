package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NfeLocalEntrega;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.estoque.INFeLocalEntregaDAO;

@Component
public class NFeLocalEntregaDAO extends AbstractCrudDAO<NfeLocalEntrega> implements INFeLocalEntregaDAO {

	@Override
	public Class<NfeLocalEntrega> getEntityClass() {
		return NfeLocalEntrega.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public NfeLocalEntrega buscaEntregaPorNota(NotaFiscal nota) {
		Criteria c = getSession().createCriteria(NfeLocalEntrega.class);
		c.add(Restrictions.eq("notaFiscal", nota));

		return (NfeLocalEntrega) c.uniqueResult();
	}

}