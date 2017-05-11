package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.FormacaoPrecoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository()
public class FormacaoPrecoDAO extends AbstractCrudDAO<FormacaoPrecoEntity>  {
	
	
		@Override
		public Class<FormacaoPrecoEntity> getEntityClass() {
			return FormacaoPrecoEntity.class;
		}

		@Override
		public String[] getDefaultSearchFields() {
			return new String[] { "" };
		}

}
