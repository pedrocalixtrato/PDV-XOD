package dc.servicos.dao.geral;

import dc.entidade.geral.diverso.UfEntity;
import dc.model.dao.AbstractDAO;

public interface IUfDAO extends AbstractDAO<UfEntity>{

	UfEntity getObject(String sigla);

}