package dc.servicos.dao.patrimonio;

import java.util.List;

import dc.entidade.patrimonio.SeguradoraEntity;
import dc.model.dao.AbstractDAO;

public interface ISeguradoraDAO extends AbstractDAO<SeguradoraEntity> {

	public abstract List<SeguradoraEntity> seguradoraLista();

}