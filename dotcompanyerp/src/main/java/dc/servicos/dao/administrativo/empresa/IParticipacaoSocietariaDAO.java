package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import dc.entidade.administrativo.empresa.ParticipacaoSocietariaEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.model.dao.AbstractDAO;

public interface IParticipacaoSocietariaDAO extends AbstractDAO<ParticipacaoSocietariaEntity>{

	public abstract List<ParticipacaoSocietariaEntity> findBySocio(SocioEntity socio);

}