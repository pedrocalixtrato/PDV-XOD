package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.model.dao.ordemservico.IOrcamentoOsDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrcamentoOsDAO extends AbstractCrudDAO<OrcamentoOsEntity> implements IOrcamentoOsDAO {

	@Override
	public Class<OrcamentoOsEntity> getEntityClass() {
		return OrcamentoOsEntity.class;
	}


	public String[] getDefaultSearchFields() {
		return new String[] {"nomeVendedor","formaPagamento","nome","endereco","fone", "placa","marca","modelo","cor",
				"ano","motor","motorizacao"};
	}
	
	@Transactional
	public List<OrcamentoOsEntity> listaTodos() {
		return getSession().createQuery("from OrcamentoOs").list();
	}
	
	@Transactional
	public List<OrcamentoOsEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from OrcamentoOs where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}
}
