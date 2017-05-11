package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SefipCodigoMovimentacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SefipCodigoMovimentacaoDAO extends
		AbstractCrudDAO<SefipCodigoMovimentacaoEntity> implements ISefipCodigoMovimentacaoDAO {

	@Override
	public Class<SefipCodigoMovimentacaoEntity> getEntityClass() {
		return SefipCodigoMovimentacaoEntity.class;
	}

	@Transactional
	public List<SefipCodigoMovimentacaoEntity> listaTodos() {
		return getSession().createQuery("from SefipCodigoMovimentacao").list();
	}

	@Transactional
	public List<SefipCodigoMovimentacaoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery(
						"from SefipCodigoMovimentacao where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "aplicacao" };
	}

}