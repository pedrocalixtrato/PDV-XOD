package dc.controller.folhapagamento.ausencia;

import java.util.List;

import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.model.dao.AbstractDAO;

public interface IFeriasPeriodoAquisitivoDAO extends AbstractDAO<FeriasPeriodoAquisitivoEntity> {

	public abstract List<FeriasPeriodoAquisitivoEntity> procuraNomeContendo(String valor);

}