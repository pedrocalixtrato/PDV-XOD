package dc.servicos.dao.comercial;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ItemOrcamentoDAO extends AbstractCrudDAO<ItemOrcamento> implements IItemOrcamentoDAO {

	@Override
	public Class<ItemOrcamento> getEntityClass() {
		return ItemOrcamento.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}

	@Override
	@Transactional
	public List<ItemOrcamento> findByOrcamento(Orcamento orcamento){

		List<ItemOrcamento> lista = new ArrayList<>();

		try{
			if(orcamento!=null){
				lista =  getSession()
						.createQuery("from ItemOrcamento i where i.orcamento = :orcamento")
						.setParameter("orcamento", orcamento).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;


	}


}


