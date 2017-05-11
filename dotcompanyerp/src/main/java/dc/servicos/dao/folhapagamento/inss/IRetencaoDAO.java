package dc.servicos.dao.folhapagamento.inss;

import java.util.List;

import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.model.dao.AbstractDAO;

public interface IRetencaoDAO extends AbstractDAO<RetencaoEntity>{

	public abstract List<RetencaoEntity> procuraNomeContendo(String query);

}