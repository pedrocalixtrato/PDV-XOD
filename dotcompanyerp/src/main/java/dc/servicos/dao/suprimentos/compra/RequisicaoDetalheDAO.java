package dc.servicos.dao.suprimentos.compra;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDetalheDAO")
public class RequisicaoDetalheDAO extends
		AbstractCrudDAO<RequisicaoCompraDetalheEntity> implements IRequisicaoDetalheDAO {

	@Override
	public Class<RequisicaoCompraDetalheEntity> getEntityClass() {
		return RequisicaoCompraDetalheEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "produto.descricao" };
	}

	
	@Override
	@Transactional
	public List<ReqCotacaoDetalheEntity> findByRequisicao(CotacaoCompraEntity currentBean) {

			List<ReqCotacaoDetalheEntity> lista = new ArrayList<>();

			try{
				if(currentBean!=null){
					lista =  getSession()
							.createQuery("from CompraReqCotacaoDetalhe i where i.cotacao = :cotacao")
							.setParameter("cotacao", currentBean).list();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return lista;
	}	
	

}