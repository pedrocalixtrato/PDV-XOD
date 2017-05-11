package dc.model.dao.geral.outro;

import java.util.List;

import dc.entidade.geral.outro.SindicatoEntity;
import dc.model.dao.AbstractDAO;

public interface ISindicatoDAO extends AbstractDAO<SindicatoEntity> {

	List<SindicatoEntity> getSindicatoList();

}