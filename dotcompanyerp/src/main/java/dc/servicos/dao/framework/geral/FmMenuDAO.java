package dc.servicos.dao.framework.geral;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.NullPrecedence;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.BancoEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;

@Repository
public class FmMenuDAO extends AbstractCrudDAO<FmMenu> implements IFmMenuDAO {

	@Override
	public Class<FmMenu> getEntityClass() {
		return FmMenu.class;
	}

	@Transactional
	public List<BancoEntity> listaTodos() {
		return getSession().createQuery("from FmMenu").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "caption", "urlId" };
	}

	@Transactional
	public List<FmModulo> getAllModulos() {
		return getSession().createCriteria(FmModulo.class).list();
	}

	@Transactional
	public List<FmMenu> getAllMenusByModuleIdGrouped(Integer moduleID,
			Integer userID) {
		String query = "select distinct menu.* from fm_menu menu "
				+ " inner join fm_modulo modulo on modulo.id = menu.fmmodulo_id "
				+ " inner join papel_menu pm on menu.id = pm.id_menu "
				+ " inner join papel p on pm.id_papel = p.id "
				+ " inner join usuario u on u.id_papel = p.id and u.id = :user_id "
				+ " where ( modulo.id = :module_id and pm.habilitado = 'S' ) "
				+ " group by "
				+ "	menu.parent_id, menu.id, menu.caption, menu.controller "
				+ " order by " + "	menu.parent_id, menu.caption ";

		return getSession().createSQLQuery(query).addEntity(FmMenu.class)
				.setInteger("user_id", userID)
				.setInteger("module_id", moduleID).list();
	}

	@Transactional
	public List<FmMenu> getAllMenusByModuleIdGrouped(Integer moduleID) {
		return getSession()
				.createCriteria(FmMenu.class)
				.add(Restrictions.eq("fmModulo.id", moduleID))
				.addOrder(Order.asc("parent.id").nulls(NullPrecedence.FIRST))
				.addOrder(Order.asc("caption"))
				.setProjection(
						Projections
								.projectionList()
								.add(Property.forName("controllerClass").as(
										"controllerClass"))
								.add(Property.forName("caption").as("caption"))
								.add(Property.forName("id").as("id"))
								.add(Property.forName("urlId").as("urlId"))
								.add(Property.forName("parent.id").group()
										.as("parentId"))
								.add(Property.forName("caption").group()
										.as("caption"))
								.add(Property.forName("id").group().as("id"))
								.add(Property.forName("controllerClass")
										.group().as("controllerClass")))
				.setResultTransformer(Transformers.aliasToBean(FmMenu.class))
				.list();
	}

	@Transactional
	public List<FmMenu> getAllMenusByModuleId(Integer moduleID) {
		return getSession().createCriteria(FmMenu.class)
				.add(Restrictions.eq("fmModulo.id", moduleID)).list();
	}

	@Transactional
	public FmMenu getEntity(String controllerClass) {
		try {
			String sql = "SELECT ent FROM FmMenu ent WHERE (1 = 1)"
					+ " AND ent.controllerClass = '" + controllerClass + "'";

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("controllerClass", controllerClass);

			FmMenu menu = (FmMenu) query.uniqueResult();

			return menu;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<FmMenu> getMenuLista(List lista, String nomeClasse) {
		try {
			String sql = "SELECT ent.parent.menusFilho FROM FmMenu ent"
					+ " WHERE (1 = 1) AND (ent.parent.fmModulo IN (:lista))";

			Query query = super.getSession().createQuery(sql);
			query.setParameterList("lista", lista);

			List<FmMenu> auxLista1 = query.list();

			sql = "FROM FmMenu ent WHERE (1 = 1) AND ent.controllerClass = :controller";

			query = super.getSession().createQuery(sql);
			query.setParameter("controller", nomeClasse);

			List<FmMenu> auxLista2 = query.list();

			boolean b = auxLista1.contains(auxLista2.get(0));

			List<FmMenu> auxLista = new ArrayList<FmMenu>();

			if (b) {
				auxLista.add(auxLista2.get(0));
			}

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FmMenu>();
		}
	}

}