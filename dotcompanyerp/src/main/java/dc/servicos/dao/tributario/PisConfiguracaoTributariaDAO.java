package dc.servicos.dao.tributario;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PisConfiguracaoTributariaDAO extends AbstractCrudDAO<PisConfiguracaoTributariaEntity> implements IPisConfiguracaoTributariaDAO {

	@Override
	public Class<PisConfiguracaoTributariaEntity> getEntityClass() {
		return PisConfiguracaoTributariaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.tributario.IPisConfiguracaoTributariaDAO#buscarPorConfiguracao(dc.entidade.tributario.ConfiguracaoTributariaEntity)
	 */
	@Override
	@Transactional
	public PisConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao){
		PisConfiguracaoTributariaEntity pis = null;
		Criteria c = getSession().createCriteria(PisConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			pis = (PisConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return pis;
	}

}

