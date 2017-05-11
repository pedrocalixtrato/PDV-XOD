package dc.servicos.dao.suprimentos.contrato;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.contrato.ContratoEntity;
import dc.entidade.suprimentos.contrato.PrevFaturamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosContratoContratoDAO")
public class ContratoDAO extends AbstractCrudDAO<ContratoEntity> implements IContratoDAO {

	@Override
	public Class<ContratoEntity> getEntityClass() {
		return ContratoEntity.class;
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] {"pessoa", "numero", "nome", "descricao", "observacao" };
	}

	@Override
	@Transactional
	public ContratoEntity find(Serializable id) {
		ContratoEntity contrato = super.find(id);
		// workaround para lazy initialization exception
		initialize(contrato.getContratosHistoricosFaturamentos());
		initialize(contrato.getContratosHistoricosReajustes());
		initialize(contrato.getContratosPrevisoesFaturamentos());

		return contrato;
	}

	/* (non-Javadoc)
	 * @see dc.servicos.dao.suprimentos.contrato.IContratoDAO#delete(dc.entidade.suprimentos.contrato.PrevFaturamentoEntity)
	 */
	@Override
	@Transactional
	public void delete(PrevFaturamentoEntity contratoPrevFaturamento) {
		getSession().delete(contratoPrevFaturamento);
	}
}