package dc.model.dao.geral.tabela;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CboDAOImpl extends AbstractCrudDAO<CboEntity> implements CboDAO<CboEntity> {

private static Logger logger = Logger.getLogger(CboDAOImpl.class);

@Override
public Class<CboEntity> getEntityClass() {
return CboEntity.class;
}

public List<CboEntity> listaTodos() {
try {
	String sql = "FROM # ent WHERE (1 = 1)";
	sql = sql.replace("#", this.getEntityClass().getName());

	Query query = super.getSession().createQuery(sql);

	List<CboEntity> auxLista = query.list();

	return auxLista;
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

public List<CboEntity> procuraNomeContendo(String value) {
try {
	String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
	sql = sql.replace("#", this.getEntityClass().getName());

	Query query = super.getSession().createQuery(sql);
	query.setParameter("nome", value);

	List<CboEntity> auxLista = query.list();

	return auxLista;
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

public List<CboEntity> query(String value) {
try {
	String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
	sql = sql.replace("#", getEntityClass().getName());

	value = "%" + value.toLowerCase() + "%";

	Query query = super.getSession().createQuery(sql);
	query.setParameter("q", value);

	List<CboEntity> auxLista = query.list();

	return auxLista;
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

public String[] getDefaultSearchFields() {
return new String[] { "nome" };
}

}
