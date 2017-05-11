package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class FluxoCaixaDAO extends AbstractCrudDAO<FluxoCaixaEntity> {
	
		@Override
		public Class<FluxoCaixaEntity> getEntityClass() {
			return FluxoCaixaEntity.class;
		}

		@Transactional
		public List<FluxoCaixaEntity> listaTodos() {
			return getSession().createQuery("from FluxoCaixa").list();
		}

		public String[] getDefaultSearchFields() {
			return new String[] {"fluxoCaixaPeriodo","nome","dataInicial","dataBase","numeroPeriodos","descricao"};
		}
		
		@Transactional
		public List<FluxoCaixaEntity> procuraNomeContendo(String query) {
			return getSession().createQuery("from FluxoCaixa where nome like :q")
					.setParameter("q", "%" + query + "%").list();
		}

}
