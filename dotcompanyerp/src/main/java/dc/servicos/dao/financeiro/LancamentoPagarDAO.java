package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LancamentoPagarDAO extends AbstractCrudDAO<LancamentoPagarEntity> implements ILancamentoPagarDAO {

	@Override
	public Class<LancamentoPagarEntity> getEntityClass() {
		return LancamentoPagarEntity.class;
	}

	@Transactional
	public List<LancamentoPagarEntity> listaTodos() {
		return getSession().createQuery("from LancamentoPagar").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"fornecedor", "documentoOrigem","dataLancamento","pagamentoCompartilhado","valorTotal","valorAPagar" ,
				"quantidadeParcela","numeroDocumento","primeiroVencimento"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILancamentoPagarDAO#buscar(dc.entidade.geral.pessoal.FornecedorEntity)
	 */
	@Override
	@Transactional
	public List<LancamentoPagarEntity> buscar(FornecedorEntity fornecedor){

		List<LancamentoPagarEntity> lista = new ArrayList<>();

		try{
			if(fornecedor!=null){
				lista =  getSession()
						.createQuery("from LancamentoPagar i where i.fornecedor = :fornecedor")
						.setParameter("fornecedor", fornecedor).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.financeiro.ILancamentoPagarDAO#procuraNomeContendo(java.lang.String)
	 */
	@Override
	@Transactional
	public List<LancamentoPagarEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from LancamentoPagar where valorTotal like :q")
				.setParameter("q", "%" + query + "%").list();
	}

}
