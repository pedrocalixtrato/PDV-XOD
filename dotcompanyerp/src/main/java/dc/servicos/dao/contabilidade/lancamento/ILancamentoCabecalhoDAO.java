package dc.servicos.dao.contabilidade.lancamento;

import java.util.List;

import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.model.dao.AbstractDAO;

public interface ILancamentoCabecalhoDAO extends AbstractDAO<LancamentoCabecalhoEntity> {

	List<LancamentoCabecalhoEntity> procuraNomeContendo(String valor);

}