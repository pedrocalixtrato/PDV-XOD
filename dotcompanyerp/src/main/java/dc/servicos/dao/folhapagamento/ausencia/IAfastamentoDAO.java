package dc.servicos.dao.folhapagamento.ausencia;

import java.util.List;

import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.model.dao.AbstractDAO;

public interface IAfastamentoDAO extends AbstractDAO<AfastamentoEntity> {

	List<AfastamentoEntity> procuraNomeContendo(String valor);

}