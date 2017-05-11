package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.FluxoCaixaPeriodoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
@Repository
@SuppressWarnings("unchecked")
public class FluxoCaixaPeriodoDAO extends AbstractCrudDAO<FluxoCaixaPeriodoEntity> implements IFluxoCaixaPeriodoDAO {
	
			@Override
			public Class<FluxoCaixaPeriodoEntity> getEntityClass() {
				return FluxoCaixaPeriodoEntity.class;
			}

			@Transactional
			public List<FluxoCaixaPeriodoEntity> listaTodos() {
				return getSession().createQuery("from FluxoCaixaPeriodo").list();
			}

			public String[] getDefaultSearchFields() {
				return new String[] {"contaCaixa","nome","periodo"};
			}
			
			@Transactional
			public List<FluxoCaixaPeriodoEntity> procuraNomeContendo(String query) {
				return getSession().createQuery("from FluxoCaixaPeriodo where nome like :q")
						.setParameter("q", "%" + query + "%").list();
			}

}
