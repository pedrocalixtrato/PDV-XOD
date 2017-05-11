package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.model.dao.geral.pessoal.INotaFiscalTipoDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class NotaFiscalTipoDAO extends AbstractCrudDAO<NotaFiscalTipo> implements INotaFiscalTipoDAO{

	@Override
	public Class<NotaFiscalTipo> getEntityClass() {
		return NotaFiscalTipo.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Transactional
	public List<NotaFiscalTipo> listarTodos() {
		List<NotaFiscalTipo> lista = null;

		try {
			String sql = "FROM NotaFiscalTipo";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}