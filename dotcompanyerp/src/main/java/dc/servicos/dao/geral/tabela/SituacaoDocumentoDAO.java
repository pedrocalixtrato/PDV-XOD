package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SituacaoDocumentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SituacaoDocumentoDAO extends
		AbstractCrudDAO<SituacaoDocumentoEntity> implements ISituacaoDocumentoDAO {

	@Override
	public Class<SituacaoDocumentoEntity> getEntityClass() {
		return SituacaoDocumentoEntity.class;
	}

	@Transactional
	public List<SituacaoDocumentoEntity> listaTodos() {
		return getSession().createQuery("from SituacaoDocumento").list();
	}

	@Transactional
	public List<SituacaoDocumentoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SituacaoDocumento where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}