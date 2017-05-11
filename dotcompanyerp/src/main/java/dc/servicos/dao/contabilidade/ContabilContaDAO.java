package dc.servicos.dao.contabilidade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.ContabilContaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContabilContaDAO extends AbstractCrudDAO<ContabilContaEntity> implements IContabilContaDAO {

	@Override
	public Class<ContabilContaEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return ContabilContaEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String []{"classificacao"};
	}
	@Transactional
	public List<ContabilContaEntity> listaTodos() {
		return getSession().createQuery("from ContabilConta").list();
	}
	
	@Transactional
	public List<ContabilContaEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from ContabilConta where lower(classificacao) like :q").setParameter("q", q).list();
	}
}
