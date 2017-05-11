package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class MaterialServicoDAO extends AbstractCrudDAO<MaterialServicoEntity>{

	@Override
	public Class<MaterialServicoEntity> getEntityClass() {
		return MaterialServicoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<MaterialServicoEntity> listaTodos() {
		return getSession().createQuery("from MaterialServicoEntity").list();
	}
	
	@Transactional
	public List<MaterialServicoEntity> findByMaterialServico(OrdemServicoEntity ordemServico){

		List<MaterialServicoEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from MaterialServicoEntity i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
