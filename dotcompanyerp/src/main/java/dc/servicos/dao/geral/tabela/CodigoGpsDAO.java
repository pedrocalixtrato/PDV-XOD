package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CodigoGpsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CodigoGpsDAO extends AbstractCrudDAO<CodigoGpsEntity> implements ICodigoGpsDAO {

	@Override
	public Class<CodigoGpsEntity> getEntityClass() {
		return CodigoGpsEntity.class;
	}

	@Transactional
	public List<CodigoGpsEntity> listaTodos() {
		return getSession().createQuery("from CodigoGps").list();
	}

	@Transactional
	public CodigoGpsEntity procuraPorCodigo(String codigo) {
		CodigoGpsEntity cod = null;
		Criteria c = getSession().createCriteria(CodigoGpsEntity.class);

		if (codigo != null && !(codigo.isEmpty())) {
			c.add(Restrictions.eq("codigo", codigo));
		}

		cod = (CodigoGpsEntity) c.uniqueResult();

		return cod;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}