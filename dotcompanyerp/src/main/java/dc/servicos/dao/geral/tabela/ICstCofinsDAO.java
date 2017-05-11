package dc.servicos.dao.geral.tabela;

import dc.entidade.geral.tabela.CstCofinsEntity;
import dc.model.dao.AbstractDAO;

public interface ICstCofinsDAO extends AbstractDAO<CstCofinsEntity>{

	public abstract CstCofinsEntity procuraPorCodigo(String codigo);

}