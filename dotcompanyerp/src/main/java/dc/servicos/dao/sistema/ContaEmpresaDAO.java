package dc.servicos.dao.sistema;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContaEmpresaDAO extends AbstractCrudDAO<ContaEmpresa> implements IContaEmpresaDAO {

	@Override
	public Class<ContaEmpresa> getEntityClass() {
		// TODO Auto-generated method stub
		return ContaEmpresa.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.sistema.IContaEmpresaDAO#findConfiguracaoByIdConta(java.lang.Integer)
	 */
	@Override
	@Transactional
	public ConfiguracaoContaEmpresa findConfiguracaoByIdConta(Integer contaId) {
		Criteria criteria = getSession().createCriteria(
				ConfiguracaoContaEmpresa.class);
		criteria.add(Restrictions.eq("conta.id", contaId));

		return (ConfiguracaoContaEmpresa) criteria.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.sistema.IContaEmpresaDAO#findByEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public ContaEmpresa findByEmail(String email) {
		return (ContaEmpresa) getSession().createCriteria(ContaEmpresa.class)
				.add(Restrictions.eq("email", email)).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.sistema.IContaEmpresaDAO#findConfiguracaoByIdContaWithModules(java.lang.Integer)
	 */
	@Override
	@Transactional
	public ConfiguracaoContaEmpresa findConfiguracaoByIdContaWithModules(
			Integer contaId) {

		Criteria criteria = getSession().createCriteria(
				ConfiguracaoContaEmpresa.class);
		criteria.add(Restrictions.eq("conta.id", contaId));

		criteria.createCriteria("modulos")
				.setFetchMode("menus", FetchMode.JOIN);

		// criteria.createCriteria("modulos.menus").setFetchMode("menusFilho",
		// FetchMode.JOIN);
		return (ConfiguracaoContaEmpresa) criteria.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.sistema.IContaEmpresaDAO#save(dc.entidade.sistema.ContaEmpresa)
	 */
	@Override
	@Transactional
	public void save(ContaEmpresa currentBean) {
		try {
			PapelEntity p = (PapelEntity) getSession().get(PapelEntity.class, PapelEntity.MASTER_ID);
			currentBean.getUsuarioCriador().setPapel(p);

			super.saveOrUpdate(currentBean.getEmpresa());
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}