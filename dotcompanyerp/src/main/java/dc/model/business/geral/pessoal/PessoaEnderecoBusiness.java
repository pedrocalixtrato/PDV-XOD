package dc.model.business.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface PessoaEnderecoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {

	public List<T> list(PessoaEntity entity);
	//public List<T> list(PessoaEventosEntity pessoa);

}