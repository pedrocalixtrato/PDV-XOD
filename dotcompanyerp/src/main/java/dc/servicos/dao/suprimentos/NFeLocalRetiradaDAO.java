package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NfeLocalRetirada;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.estoque.INFeLocalRetiradaDAO;

@Component
public class NFeLocalRetiradaDAO extends AbstractCrudDAO<NfeLocalRetirada> implements INFeLocalRetiradaDAO {

	@Override
	public Class<NfeLocalRetirada> getEntityClass() {
		return NfeLocalRetirada.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public NfeLocalRetirada buscaRetiradaPorNota(NotaFiscal nota) {
		Criteria c = getSession().createCriteria(NfeLocalRetirada.class);
		c.add(Restrictions.eq("notaFiscal", nota));

		return (NfeLocalRetirada) c.uniqueResult();
	}

}