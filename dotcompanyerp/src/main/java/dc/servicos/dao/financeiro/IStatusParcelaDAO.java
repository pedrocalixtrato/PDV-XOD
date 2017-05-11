package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.StatusParcela;
import dc.model.dao.AbstractDAO;

public interface IStatusParcelaDAO extends AbstractDAO<StatusParcela>{

	public abstract List<StatusParcela> procura(String query);

	public abstract List<StatusParcela> query(String q);

	public abstract StatusParcela findBySituacao(String descricao);

}