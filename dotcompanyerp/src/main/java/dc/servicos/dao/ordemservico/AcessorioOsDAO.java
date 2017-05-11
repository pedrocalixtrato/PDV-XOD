package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class AcessorioOsDAO extends AbstractCrudDAO<AcessorioOsEntity>{

	@Override
	public Class<AcessorioOsEntity> getEntityClass() {
		return AcessorioOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"acessorio.nome"};
	}
	
	@Transactional
	public List<AcessorioOsEntity> listaTodos() {
		return getSession().createQuery("from AcessorioOsEntity").list();
	}
	
	@Transactional
	public List<AcessorioOsEntity> findByAcessorioOs(OrdemServicoEntity ordemServico){

		List<AcessorioOsEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from AcessorioOs i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
