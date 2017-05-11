package dc.servicos.dao.geral.eventos;

import java.util.List;

import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.model.dao.AbstractDAO;

public interface ICerimonialEventosDAO extends AbstractDAO<CerimonialEventosEntity> {

	List<CerimonialEventosEntity> procuraNomeContendo(String valor);

}