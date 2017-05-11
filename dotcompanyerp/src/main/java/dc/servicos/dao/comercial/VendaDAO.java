package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Venda;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class VendaDAO extends AbstractCrudDAO<Venda> implements IVendaDAO {

	@Override
	public Class<Venda> getEntityClass() {
		return Venda.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "numeroFatura","dataVenda" };
	}

	@Transactional
	public List<Venda> listaTodos() {
		return getSession().createQuery("from Venda").list();
	}

}