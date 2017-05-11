package dc.servicos.dao.nfe;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.model.dao.AbstractDAO;

public interface INfeDestinatarioDAO extends AbstractDAO<NfeDestinatarioEntity> {

	public abstract NfeDestinatarioEntity getEntidade(NfeCabecalhoEntity ent);

}