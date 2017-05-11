package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SalarioMinimoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SalarioMinimoDAO extends AbstractCrudDAO<SalarioMinimoEntity> implements ISalarioMinimoDAO {

	@Override
	public Class<SalarioMinimoEntity> getEntityClass() {
		return SalarioMinimoEntity.class;
	}

	@Transactional
	public List<SalarioMinimoEntity> listaTodos() {
		return getSession().createQuery("from SalarioMinimo").list();
	}

	@Transactional
	public List<SalarioMinimoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SalarioMinimo where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "vigencia", "valorMensal", "valorDiario",
				"valorHora" };
	}

}