package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.ParcelaRecebimento;
import dc.model.dao.AbstractDAO;

public interface IParcelaRecebimentoDAO extends AbstractDAO<ParcelaRecebimento>{

	public abstract List<ParcelaRecebimento> buscaPorParcelaReceber(ParcelaReceber parcelaReceber);

}