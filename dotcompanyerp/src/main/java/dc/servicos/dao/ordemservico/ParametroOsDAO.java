package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ordemservico.ParametroOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParametroOsDAO extends AbstractCrudDAO<ParametroOsEntity> implements IParametroOsDAO{

	@Override
	public Class<ParametroOsEntity> getEntityClass() {
		return ParametroOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"limparBdAut","vendedorAtendente","vendedorProduto","vendedorServico","valorPagoPeca","descontoGeral",
				"qtdDiasRevisao","qtdDiasPadrao","obsDefeitoPadrao","obsPadrao","obsPadraoOsSimples"};
	}
	
	@Transactional
	public List<ParametroOsEntity> listaTodos() {
		return getSession().createQuery("from ParametroOsEntity").list();
	}
	
	@Transactional  
	public ParametroOsEntity buscaParametroOs(EmpresaEntity empresa){
		
		Criteria c = getSession().createCriteria(ParametroOsEntity.class);
		c.add(Restrictions.eq("empresa",empresa));
		return (ParametroOsEntity)c.uniqueResult(); 
	}
}
