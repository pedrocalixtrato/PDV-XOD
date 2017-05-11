package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalClienteDAO")
public class ClienteDAO extends AbstractCrudDAO<ClienteEntity>  implements IClienteDAO{

	@Override
	public Class<ClienteEntity> getEntityClass() {
		return ClienteEntity.class;
	}

	@Transactional
	public List<ClienteEntity> listaTodos() {
		List<ClienteEntity> lista = getSession().createQuery("from Cliente")
				.list();

		return lista;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "pessoa","situacaoForCli","atividadeForCli","operacaoFiscal", "desde", "contaTomador",
				"observacao", "gerarFinanceiro", "indicadorPreco", "tipoFrete", "gerarFinanceiro",
				"formaDesconto", "porcentoDesconto", "limiteCredito" };
	}

	@Transactional
	public List<ClienteEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from Cliente where lower(observacao) like :q")
				.setParameter("q", q).list();
	}

	@Override
	public ClienteEntity findById(ClienteEntity cliente) {
		try {

			String sql = "SELECT distinct ent FROM # AS ent INNER JOIN FETCH ent.pessoa p LEFT JOIN FETCH p.pessoaEnderecoList AS pe WHERE (1 = 1) AND ent.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", cliente.getId());

			ClienteEntity cli = new ClienteEntity();
			
			cli = (ClienteEntity) query.uniqueResult();

			return cli;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}