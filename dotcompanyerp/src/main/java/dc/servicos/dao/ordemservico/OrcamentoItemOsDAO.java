package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.entidade.ordemservico.OrcamentoOsItemEntity;
import dc.model.dao.ordemservico.IOrcamentoItemOsDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrcamentoItemOsDAO extends AbstractCrudDAO<OrcamentoOsItemEntity> implements IOrcamentoItemOsDAO {

	@Override
	public Class<OrcamentoOsItemEntity> getEntityClass() {
		return OrcamentoOsItemEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrcamentoOsItemEntity> listaTodos() {
		return getSession().createQuery("from OrcamentoOsItem").list();
	}
	
	@Transactional
	public List<OrcamentoOsItemEntity> findByOrcamentoOs(OrcamentoOsEntity orcamentoOs){

		List<OrcamentoOsItemEntity> lista = new ArrayList<>();

		try{
			if(orcamentoOs!=null){
				lista =  getSession()
						.createQuery("from OrcamentoOsItemEntity i where i.orcamentoOs = :orcamentoOs")
						.setParameter("orcamentoOs", orcamentoOs).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
