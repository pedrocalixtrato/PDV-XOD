package dc.servicos.dao.folhapagamento.ausencia;

import java.util.List;

import dc.entidade.folhapagamento.ausencia.FeriasColetivasEntity;
import dc.model.dao.AbstractDAO;

public interface IFeriasColetivasDAO extends AbstractDAO<FeriasColetivasEntity> {

	List<FeriasColetivasEntity> procuraNomeContendo(String valor);

}