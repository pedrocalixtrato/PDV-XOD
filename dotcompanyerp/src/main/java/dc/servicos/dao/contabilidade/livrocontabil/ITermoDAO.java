package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.List;

import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.model.dao.AbstractDAO;

public interface ITermoDAO extends AbstractDAO<TermoEntity> {

	public abstract List<TermoEntity> procuraNomeContendo(String query);

}