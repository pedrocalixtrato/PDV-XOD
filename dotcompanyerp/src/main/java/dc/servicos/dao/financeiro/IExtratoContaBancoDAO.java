package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.model.dao.AbstractDAO;

public interface IExtratoContaBancoDAO extends AbstractDAO<ExtratoContaBancoEntity>{

	public abstract List<ExtratoContaBancoEntity> findByExtratoContaBanco(ExtratoContaBancoEntity extrato);

}