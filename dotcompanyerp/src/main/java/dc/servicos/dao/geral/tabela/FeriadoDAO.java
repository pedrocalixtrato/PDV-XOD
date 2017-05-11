package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.FeriadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FeriadoDAO extends AbstractCrudDAO<FeriadoEntity> implements IFeriadoDAO {

	@Override
	public Class<FeriadoEntity> getEntityClass() {
		return FeriadoEntity.class;
	}

	@Transactional
	public List<FeriadoEntity> listaTodos() {
		return getSession().createQuery("from Feriado").list();
	}

	@Transactional
	public List<FeriadoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Feriado where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "ano", "nome" };
	}

}