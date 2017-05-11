package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEfetivacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrdemServicoEfetivacaoDAO extends AbstractCrudDAO<OrdemServicoEfetivacaoEntity>{

	@Override
	public Class<OrdemServicoEfetivacaoEntity> getEntityClass() {
		return OrdemServicoEfetivacaoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrdemServicoEfetivacaoEntity> listaTodos() {
		return getSession().createQuery("from OrdemServicoEfetivacaoEntity").list();
	}
	
	@Transactional
	public List<OrdemServicoEfetivacaoEntity> buscarOsPorOrdemServico(OrdemServicoEntity ordemServico){

		List<OrdemServicoEfetivacaoEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from OrdemServicoEfetivacaoEntity i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
