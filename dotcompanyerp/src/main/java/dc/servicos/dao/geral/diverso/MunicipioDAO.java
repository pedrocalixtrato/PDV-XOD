package dc.servicos.dao.geral.diverso;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.MunicipioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class MunicipioDAO extends AbstractCrudDAO<MunicipioEntity>{

	@Override
	public Class<MunicipioEntity> getEntityClass() {
		return MunicipioEntity.class;
	}

	@Transactional
	public List<MunicipioEntity> listaTodos() {
		return getSession().createQuery("from Municipio").list();
	}

	@Transactional
	public List<MunicipioEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Municipio where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome","codigoIbge","codigoReceitaFederal"};
	}
	
	@Transactional
	public List<MunicipioEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Municipio where lower(nome) like :q").setParameter("q", q).list();
	}

}
