package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.ParticipacaoSocietariaEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ParticipacaoSocietariaDAO extends AbstractCrudDAO<ParticipacaoSocietariaEntity> implements IParticipacaoSocietariaDAO {


	@Override
	public Class<ParticipacaoSocietariaEntity> getEntityClass() {
		return ParticipacaoSocietariaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"cnpj"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.administrativo.empresa.IParticipacaoSocietariaDAO#findBySocio(dc.entidade.administrativo.empresa.SocioEntity)
	 */
	@Override
	@Transactional
	public List<ParticipacaoSocietariaEntity> findBySocio(SocioEntity socio){

		List<ParticipacaoSocietariaEntity> lista = null;

		try{
			if(socio!=null){
				lista = getSession().createQuery("from ParticipacaoSocietaria p where p.socio = :socio")
						.setParameter("socio", socio).list();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;

	}

	
}



