package dc.servicos.dao.tributario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.entidade.tributario.IcmsConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IcmsConfiguracaoTributariaDAO extends AbstractCrudDAO<IcmsConfiguracaoTributariaEntity> implements IIcmsConfiguracaoTributariaDAO {

	@Override
	public Class<IcmsConfiguracaoTributariaEntity> getEntityClass() {
		return IcmsConfiguracaoTributariaEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	/* (non-Javadoc)
	 * @see dc.servicos.dao.tributario.IIcmsConfiguracaoTributariaDAO#buscarPorConfiguracao(dc.entidade.tributario.ConfiguracaoTributariaEntity)
	 */
	@Override
	@Transactional
	public List<IcmsConfiguracaoTributariaEntity> buscarPorConfiguracao(ConfiguracaoTributariaEntity configuracao){
		List<IcmsConfiguracaoTributariaEntity> lista = null;
		Criteria c = getSession().createCriteria(IcmsConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			
		}
		lista = c.list();
		return lista;
	}


}
 

