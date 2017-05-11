package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.TipoReceitaDipiEntity;
import dc.model.dao.AbstractDAO;

public interface ITipoReceitaDipiDAO extends AbstractDAO<TipoReceitaDipiEntity> {

	TipoReceitaDipiEntity procuraPorCodigo(String codigo);

}