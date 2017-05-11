package dc.servicos.dao.geral;

import java.util.List;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.model.dao.AbstractDAO;

public interface IFornecedorDAO extends AbstractDAO<FornecedorEntity> {

	List<FornecedorCotacaoEntity> findyByFor(CotacaoCompraEntity currentBean);


}