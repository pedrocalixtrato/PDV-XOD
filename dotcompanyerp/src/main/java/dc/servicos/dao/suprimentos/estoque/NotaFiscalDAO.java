package dc.servicos.dao.suprimentos.estoque;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@Transactional
public class NotaFiscalDAO extends AbstractCrudDAO<NotaFiscal> implements INotaFiscalDAO {

	@Override
	public Class<NotaFiscal> getEntityClass() {
		return NotaFiscal.class;
	}

	@Override
	public NotaFiscal find(Serializable id) {
		NotaFiscal nfe = super.find(id);
		// workaround para lazy initialization exception
		return nfe;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "id" };
	}

}