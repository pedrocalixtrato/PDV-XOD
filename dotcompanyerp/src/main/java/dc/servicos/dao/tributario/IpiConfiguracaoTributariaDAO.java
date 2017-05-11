package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.IpiConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IpiConfiguracaoTributariaDAO extends AbstractCrudDAO<IpiConfiguracaoTributariaEntity> implements IIpiConfiguracaoTributariaDAO {

	@Override
	public Class<IpiConfiguracaoTributariaEntity> getEntityClass() {
		return IpiConfiguracaoTributariaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.tributario.IIpiConfiguracaoTributariaDAO#buscarPorConfiguracao(dc.entidade.tributario.ConfiguracaoTributariaEntity)
	 */
	@Override
	@Transactional
	public IpiConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao){
		IpiConfiguracaoTributariaEntity ipi = null;
		Criteria c = getSession().createCriteria(IpiConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			ipi = (IpiConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return ipi;
	}

}


