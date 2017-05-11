package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.EntradaServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EntradaServicoDAO extends AbstractCrudDAO<EntradaServicoEntity>{

	@Override
	public Class<EntradaServicoEntity> getEntityClass() {
		return EntradaServicoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<EntradaServicoEntity> listaTodos() {
		return getSession().createQuery("from EntradaServicoEntity").list();
	}
	
	@Transactional
	public List<EntradaServicoEntity> findByEntradaServico(OrdemServicoEntity ordemServico){

		List<EntradaServicoEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from EntradaServicoEntity i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
