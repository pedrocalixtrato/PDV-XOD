package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.entidade.ordemservico.VendaPecaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class VendaPecaDAO extends AbstractCrudDAO<VendaPecaEntity>{

	@Override
	public Class<VendaPecaEntity> getEntityClass() {
		return VendaPecaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<VendaPecaEntity> listaTodos() {
		return getSession().createQuery("from VendaPecaEntity").list();
	}
	
	@Transactional
	public List<VendaPecaEntity> findByVendaPeca(OrdemServicoEntity ordemServico){

		List<VendaPecaEntity> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from VendaPecaEntity i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
