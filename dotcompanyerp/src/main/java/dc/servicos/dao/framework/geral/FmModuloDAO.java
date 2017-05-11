package dc.servicos.dao.framework.geral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmModulo;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão a classe principal
 *         abstractCrudDao e dela herdamos alguns métodos, fazemos uma Conexão
 *         com o Banco, uma listagem E aqui herdamos também o Método do
 *         pesquisar, onde nela colocamos os campos que colocamos as anotações
 *         lá no TO (ENTIDADE), que vai ser pesquisado na Tela quando rodar o
 *         projeto.
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FmModuloDAO extends AbstractCrudDAO<FmModulo> implements IFmModuloDAO {

	@Value("${modules.id.adm}")
	public Integer idModuloAdmObrigatorio;

	@Value("${modules.id.sistema}")
	public Integer idModuloSistemaObrigatorio;

	public static Logger logger = Logger.getLogger(FmModuloDAO.class.getName());

	@Override
	public Class<FmModulo> getEntityClass() {
		return FmModulo.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "caption", "urlID" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getAllOrderedByCaption()
	 */
	@Override
	@Transactional
	public List<FmModulo> getAllOrderedByCaption() {
		return getSession().createCriteria(FmModulo.class)
				.setFetchMode("menus", FetchMode.SELECT)
				.addOrder(Order.asc("caption")).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getAllByUserIdOrderedByCaption(int)
	 */
	@Override
	@Transactional
	public List<FmModulo> getAllByUserIdOrderedByCaption(int user_id) {
		String q = "select distinct modulo.* from fm_modulo modulo "
				+ " inner join fm_menu menu on modulo.id = menu.fmmodulo_id "
				+ " inner join papel_menu pm on menu.id = pm.id_menu "
				+ " inner join papel p on pm.id_papel = p.id  "
				+ " inner join usuario u on u.id_papel = p.id and u.id = :user_id "
				+ " order by modulo.caption ";
		return getSession().createSQLQuery(q).addEntity(FmModulo.class)
				.setInteger("user_id", user_id).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getModulosSelecionaveis()
	 */
	@Override
	@Transactional
	public List<FmModulo> getModulosSelecionaveis() {
		logger.info("Modulo adm: " + this.idModuloAdmObrigatorio);
		logger.info("Modulo sistema: " + this.idModuloSistemaObrigatorio);
		Criteria c = getSession().createCriteria(FmModulo.class);
		c.add(Restrictions.not(Restrictions.in("id", new Integer[] {
				this.idModuloAdmObrigatorio,
				this.idModuloSistemaObrigatorio })));
		return c.addOrder(Order.asc("caption")).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getModulosObrigatorios()
	 */
	@Override
	@Transactional
	public List<FmModulo> getModulosObrigatorios() {
		Criteria c = getSession().createCriteria(FmModulo.class);
		c.add(Restrictions.in("id", new Integer[] {
				this.idModuloAdmObrigatorio,
				this.idModuloSistemaObrigatorio }));
		return c.addOrder(Order.asc("caption")).list();
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getModuloLista(dc.entidade.administrativo.seguranca.UsuarioEntity)
	 */
	@Override
	@Transactional
	public List<FmModulo> getModuloLista(UsuarioEntity usuario) {
		try {
			String sql = "FROM FmModulo ent WHERE (1 = 1)";

			if (usuario.getAdministrador()) {
				sql += " AND ent.id = " + 7;
			}

			List<FmModulo> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FmModulo>();
		}
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getIdModuloAdmObrigatorio()
	 */
	@Override
	public Integer getIdModuloAdmObrigatorio() {
		return idModuloAdmObrigatorio;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#setIdModuloAdmObrigatorio(java.lang.Integer)
	 */
	@Override
	public void setIdModuloAdmObrigatorio(Integer idModuloAdmObrigatorio) {
		this.idModuloAdmObrigatorio = idModuloAdmObrigatorio;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#getIdModuloSistemaObrigatorio()
	 */
	@Override
	public Integer getIdModuloSistemaObrigatorio() {
		return idModuloSistemaObrigatorio;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.framework.geral.IFmModuloDAO#setIdModuloSistemaObrigatorio(java.lang.Integer)
	 */
	@Override
	public void setIdModuloSistemaObrigatorio(Integer idModuloSistemaObrigatorio) {
		this.idModuloSistemaObrigatorio = idModuloSistemaObrigatorio;
	}

}