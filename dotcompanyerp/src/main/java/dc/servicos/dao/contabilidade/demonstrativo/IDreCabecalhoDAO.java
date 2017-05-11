package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.List;

import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.model.dao.AbstractDAO;

public interface IDreCabecalhoDAO extends AbstractDAO<DreCabecalhoEntity>{

	List<DreCabecalhoEntity> procuraNomeContendo(String valor);

}