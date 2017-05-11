package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CofinsConfiguracaoTributariaDAO extends AbstractCrudDAO<CofinsConfiguracaoTributariaEntity> implements ICofinsConfiguracaoTributariaDAO {

	@Override
	public Class<CofinsConfiguracaoTributariaEntity> getEntityClass() {
		return CofinsConfiguracaoTributariaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.tributario.ICofinsConfiguracaoTributariaDAO#buscarPorConfiguracao(dc.entidade.tributario.ConfiguracaoTributariaEntity)
	 */
	@Override
	@Transactional
	public CofinsConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao){
		CofinsConfiguracaoTributariaEntity cofins = null;
		Criteria c = getSession().createCriteria(CofinsConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			cofins = (CofinsConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return cofins;
	}

}


