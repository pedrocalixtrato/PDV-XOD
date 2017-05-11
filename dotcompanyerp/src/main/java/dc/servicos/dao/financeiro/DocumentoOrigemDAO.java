package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DocumentoOrigemDAO extends AbstractCrudDAO<DocumentoOrigem> implements IDocumentoOrigemDAO{

	@Override
	public Class<DocumentoOrigem> getEntityClass() {
		return DocumentoOrigem.class;
	}
	
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.IDocumentoOrigem#listLancamentos(dc.entidade.financeiro.DocumentoOrigem)
	 */
	@Override
	@Transactional
	public List<LancamentoPagarEntity> listLancamentos(DocumentoOrigem documento) {
		return getSession().createQuery("from LancamentoPagarEntity where documento.id = :bid").setParameter("bid", documento.getId()).list();
	}


	@Transactional
	public List<DocumentoOrigem> listaTodos() {
		return getSession().createQuery("from DocumentoOrigem").list();
	}

	@Transactional
	public List<DocumentoOrigem> procuraNomeContendo(String query) {
		return getSession().createQuery("from DocumentoOrigem where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"codigo","siglaDocumento","descricao"};
	}
	
}
