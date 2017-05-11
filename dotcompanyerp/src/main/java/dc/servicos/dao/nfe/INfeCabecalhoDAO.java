package dc.servicos.dao.nfe;

import java.util.List;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.model.dao.AbstractDAO;

public interface INfeCabecalhoDAO extends AbstractDAO<NfeCabecalhoEntity>{

	public abstract List<NfeCabecalhoEntity> find(String s);

	public abstract void saveOrUpdateNfeCabecalho(NfeCabecalhoEntity nfeCabecalho) throws Exception;

}