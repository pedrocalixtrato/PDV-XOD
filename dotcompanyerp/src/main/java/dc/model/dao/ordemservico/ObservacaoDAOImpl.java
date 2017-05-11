package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.ordemservico.AcessorioOsEntity;
import dc.entidade.ordemservico.ObservacaoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class ObservacaoDAOImpl extends AbstractCrudDAO<ObservacaoEntity> implements
ObservacaoDAO<ObservacaoEntity> {

	private static Logger logger = Logger.getLogger(ObservacaoDAOImpl.class);

	@Override
	public Class<ObservacaoEntity> getEntityClass() {
		return ObservacaoEntity.class;
	}

	public List<ObservacaoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ObservacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ObservacaoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<ObservacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ObservacaoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<ObservacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Override
	public ObservacaoEntity buscaObservacao(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			ObservacaoEntity ent = (ObservacaoEntity) query.uniqueResult();
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}