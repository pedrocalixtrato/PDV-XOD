package dc.servicos.dao.nfe;

import java.util.List;

import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.model.dao.AbstractDAO;

public interface INfeDetEspecificoArmamentoDAO extends AbstractDAO<NfeDetEspecificoArmamentoEntity> {

	List<NfeDetEspecificoArmamentoEntity> getLista(NfeDetalheEntity nfeDetalhe);

}