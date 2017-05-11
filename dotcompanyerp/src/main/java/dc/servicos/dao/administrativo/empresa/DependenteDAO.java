package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.DependenteEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DependenteDAO extends AbstractCrudDAO<DependenteEntity> implements IDependenteDAO {


	@Override
	public Class<DependenteEntity> getEntityClass() {
		return DependenteEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}

	@Transactional
	public List<DependenteEntity> findBySocio(SocioEntity socio){

		List<DependenteEntity> lista = null;

		try{
			if(socio!=null){
				lista = getSession().createQuery("from Dependente d where d.socio = :socio")
						.setParameter("socio", socio).list();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;

	}

}


