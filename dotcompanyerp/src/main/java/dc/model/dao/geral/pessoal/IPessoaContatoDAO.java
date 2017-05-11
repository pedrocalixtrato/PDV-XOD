package dc.model.dao.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaContatoDAO extends AbstractDAO<PessoaContatoEntity> {

	public List<PessoaContatoEntity> list(PessoaEntity entity);
	//public List<T> list(PessoaEventosEntity pessoa);

	public List<PessoaContatoEntity> findByPessoaContato(PessoaEntity entity);

}