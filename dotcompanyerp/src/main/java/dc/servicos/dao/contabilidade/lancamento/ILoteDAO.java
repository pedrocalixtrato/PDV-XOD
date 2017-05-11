package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.model.dao.AbstractDAO;

public interface ILoteDAO extends AbstractDAO<LoteEntity> {

	List<LoteEntity> procuraNomeContendo(String valor);

}