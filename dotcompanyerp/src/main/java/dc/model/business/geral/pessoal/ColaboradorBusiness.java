package dc.model.business.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface ColaboradorBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {
	public List<ColaboradorEntity> findVendedores() throws Exception;
	public List<ColaboradorEntity> findTecnicos() throws Exception;
}