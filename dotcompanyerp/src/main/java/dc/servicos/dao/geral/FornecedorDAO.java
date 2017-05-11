package dc.servicos.dao.geral;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class FornecedorDAO extends AbstractCrudDAO<FornecedorEntity> implements IFornecedorDAO {

	@Override
	public Class<FornecedorEntity> getEntityClass() {
		return FornecedorEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "pessoa","situacaoForCli","atividadeForCli","desde", "contaRemetente","geraFaturamento",
				"optanteSimplesNacional", "localizacao","sofreRetencao","numDiasPrimeiroVencimento", "numDiasIntervalo","chequeNominalA",
				"prazoMedioEntrega","quantidadeParcelas","observacao" };
	}
	
	@Transactional
	public List<FornecedorEntity> listaTodos() {
		
		List<FornecedorEntity> lista = getSession().createQuery("from Fornecedor")
				.list();

		return lista;
	}
	
	@Transactional
	public List<FornecedorEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Fornecedor where observacao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	@Transactional
	public List<FornecedorEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Fornecedor where lower(observacao) like :q").setParameter("q", q).list();
	}

	@Override
	public List<FornecedorCotacaoEntity> findyByFor(CotacaoCompraEntity currentBean) {
		List<FornecedorCotacaoEntity> lista = new ArrayList<>();

		try{
			if(currentBean!=null){
				lista =  getSession()
						.createQuery("from FornecedorCotacao i where i.cotacao = :cotacao")
						.setParameter("cotacao", currentBean).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
}