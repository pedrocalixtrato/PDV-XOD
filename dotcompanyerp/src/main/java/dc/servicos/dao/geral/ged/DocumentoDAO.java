package dc.servicos.dao.geral.ged;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DocumentoDAO extends AbstractCrudDAO<Documento> implements IDocumentoDAO{

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#getEntityClass()
	 */
	@Override
	public Class<Documento> getEntityClass() {
		return Documento.class;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#getDefaultSearchFields()
	 */
	@Override
	public String[] getDefaultSearchFields() {
		return new String[]{"nome", "descricao", "palavraChave"};
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#saveOrUpdate(dc.entidade.geral.ged.VersaoDocumento)
	 */
	@Override
	@Transactional
	public  void saveOrUpdate(VersaoDocumento versao) {
		sessionFactory.getCurrentSession().saveOrUpdate(versao);
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#nextVersionNumber(dc.entidade.geral.ged.Documento)
	 */
	@Override
	@Transactional
	public Integer nextVersionNumber(Documento documento)
	{
		Criteria criteria = sessionFactory.getCurrentSession()
			    .createCriteria(VersaoDocumento.class)
			    .add(Restrictions.eq("documento", documento))
			    .setProjection(Projections.max("versao"));
			Integer maxVersao = (Integer)criteria.uniqueResult();
			
		return ++maxVersao;	
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#deleteDocumentoArquivo(dc.entidade.geral.ged.DocumentoArquivo)
	 */
	@Override
	@Transactional
	public void deleteDocumentoArquivo(DocumentoArquivo documentoArquivo)
	{
		sessionFactory.getCurrentSession().delete(documentoArquivo);
		
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.geral.ged.IDocumentoDAO#getAll(java.lang.Class)
	 */
	@Override
	@SuppressWarnings({ "hiding", "unchecked" })
	@Transactional
	public <Documento> List<Documento> getAll(Class<Documento> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);
		crit.add(Restrictions.isNull("dataExclusao"));
		return crit.list();
	}
}
