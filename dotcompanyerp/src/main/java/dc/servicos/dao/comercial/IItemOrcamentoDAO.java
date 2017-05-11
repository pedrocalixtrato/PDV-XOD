package dc.servicos.dao.comercial;

import java.util.List;

import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.model.dao.AbstractDAO;

public interface IItemOrcamentoDAO extends AbstractDAO<ItemOrcamento> {

	List<ItemOrcamento> findByOrcamento(Orcamento currentBean);

}