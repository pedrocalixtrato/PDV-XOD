package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.GarantiaEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GarantiaDAO extends AbstractCrudDAO<GarantiaEntity>{

	@Override
	public Class<GarantiaEntity> getEntityClass() {
		return GarantiaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<GarantiaEntity> listaTodos() {
		return getSession().createQuery("from MaterialServicoEntity").list();
	}
	
	@Transactional
	public List<GarantiaEntity> findByEntradaServico(OrdemServicoEntity ordemServico){

		List<GarantiaEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from GarantiaEntity i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
