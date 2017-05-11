package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository(/*"pessoalTransportadoraDAO"*/)
public class TransportadoraDAO extends AbstractCrudDAO<TransportadoraEntity> implements ITransportadoraDAO{

	@Override
	public Class<TransportadoraEntity> getEntityClass() {
		return TransportadoraEntity.class;
	}

	@Transactional
	public List<TransportadoraEntity> listaTodos() {
		return getSession().createQuery("from Transportadora").list();
	}

	@Transactional
	public List<TransportadoraEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from Transportadora where observacao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"pessoa", "observacao" };
	}

}