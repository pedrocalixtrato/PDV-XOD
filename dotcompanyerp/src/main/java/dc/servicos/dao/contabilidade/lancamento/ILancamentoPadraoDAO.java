package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoPadraoEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoPadraoDAO extends AbstractDAO<LancamentoPadraoEntity> {

	List<LancamentoPadraoEntity> procuraNomeContendo(String valor);

}