package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.IInformacaoGeralDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InformacaoGeralDAO extends AbstractCrudDAO<InformacaoGeralEntity> implements IInformacaoGeralDAO{

	@Override
	public Class<InformacaoGeralEntity> getEntityClass() {
		return InformacaoGeralEntity.class;
	}

	@Transactional
	public List<InformacaoGeralEntity> listarTodos() {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1)";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	@Transactional
	public List<InformacaoGeralEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}
	
	@Transactional  
	public InformacaoGeralEntity buscaInformacaoGeral(OrdemServicoEntity ordemServico){
		
		Criteria c = getSession().createCriteria(InformacaoGeralEntity.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (InformacaoGeralEntity)c.uniqueResult();
	}
	
	public InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {

			String sql = "SELECT ent FROM InformacaoGeralEntity ent INNER JOIN ent.ordemServico os INNER JOIN FETCH ent.carro ca "+
			"LEFT JOIN FETCH ent.statusOs st LEFT JOIN FETCH ent.situacaoServico ss LEFT JOIN FETCH ent.tipoServico tp " +
			"LEFT JOIN FETCH ent.atendente at LEFT JOIN FETCH ent.tipoPagamento tp "+
			"WHERE (1 = 1) AND ent.ordemServico.id =:id ";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			InformacaoGeralEntity ent = (InformacaoGeralEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	
	public List<InformacaoGeralEntity> listaTodos() {
		try {
			String sql = "SELECT ent FROM InformacaoGeralEntity ent INNER JOIN ent.ordemServico os INNER JOIN FETCH ent.carro ca "+
			"LEFT JOIN FETCH ent.statusOs st LEFT JOIN FETCH ent.situacaoServico ss LEFT JOIN FETCH ent.tipoServico tp " +
			"LEFT JOIN FETCH ent.atendente at LEFT JOIN FETCH ent.tipoPagamento tp "+
			"WHERE (1 = 1) ";

			Query query = super.getSession().createQuery(sql);

			List<InformacaoGeralEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}