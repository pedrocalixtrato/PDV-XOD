package dc.servicos.dao.comercial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Venda;
import dc.entidade.comercial.VendaDetalhe;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class VendaDetalheDAO extends AbstractCrudDAO<VendaDetalhe> implements IVendaDetalheDAO {

	@Override
	public Class<VendaDetalhe> getEntityClass() {
		return VendaDetalhe.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.comercial.IVendaDetalheDAO#detalhesPorVenda(dc.entidade.comercial.Venda)
	 */
	@Override
	@Transactional
	public List<VendaDetalhe> detalhesPorVenda(Venda venda) {
		List<VendaDetalhe> lista = new ArrayList<>();

		if (venda.getId() != null) {
			lista = getSession()
					.createQuery("from VendaDetalhe v where v.venda= :venda")
					.setParameter("venda", venda).list();
		}

		return lista;
	}

}