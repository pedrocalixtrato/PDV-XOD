package dc.servicos.dao.suprimentos.compra;

import java.util.List;

import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.model.dao.AbstractDAO;

public interface IRequisicaoDetalheDAO extends AbstractDAO<RequisicaoCompraDetalheEntity>{

	List<ReqCotacaoDetalheEntity> findByRequisicao(CotacaoCompraEntity currentBean);

}