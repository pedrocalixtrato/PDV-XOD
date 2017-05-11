package dc.servicos.dao.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.model.dao.AbstractDAO;

public interface ITipoRelacionamentoDAO extends AbstractDAO<TipoRelacionamentoEntity>{

	public abstract List<TipoRelacionamentoEntity> listaTodos();

}