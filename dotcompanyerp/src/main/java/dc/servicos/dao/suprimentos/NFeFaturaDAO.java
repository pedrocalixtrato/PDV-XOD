package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NfeFatura;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.suprimentos.estoque.INFeFaturaDAO;

@Component
public class NFeFaturaDAO extends AbstractCrudDAO<NfeFatura> implements INFeFaturaDAO {

	@Override
	public Class<NfeFatura> getEntityClass() {
		return NfeFatura.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public NfeFatura buscaFaturaPorNota(NotaFiscal nota) {
		Criteria c = getSession().createCriteria(NfeFatura.class);
		c.add(Restrictions.eq("notaFiscal", nota));

		return (NfeFatura) c.uniqueResult();
	}

}