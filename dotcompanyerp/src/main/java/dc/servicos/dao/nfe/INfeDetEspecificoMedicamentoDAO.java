package dc.servicos.dao.nfe;

import java.util.List;

import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.model.dao.AbstractDAO;

public interface INfeDetEspecificoMedicamentoDAO extends AbstractDAO<NfeDetEspecificoMedicamentoEntity> {

	List<NfeDetEspecificoMedicamentoEntity> getLista(NfeDetalheEntity nfeDetalhe);

}