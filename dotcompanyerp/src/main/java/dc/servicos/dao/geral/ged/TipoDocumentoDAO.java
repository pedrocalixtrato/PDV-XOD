package dc.servicos.dao.geral.ged;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.ged.TipoDocumento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoDocumentoDAO extends AbstractCrudDAO<TipoDocumento> implements ITipoDocumentoDAO{

	@Override
	public Class<TipoDocumento> getEntityClass() {
		return TipoDocumento.class;
	}
	
	@Transactional
	public List<TipoDocumento> listaTodos() {
		return getSession().createQuery("from TipoDocumento").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[]{"nome", "tamanhoMaximo"};
	}
	
	@Transactional
	public List<TipoDocumento> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from TipoDocumento where lower(nome) like :q").setParameter("q", q).list();
	}

}
