package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaFisicaDAO extends AbstractDAO<PessoaFisicaEntity> {
	public PessoaFisicaEntity getPessoaFisica(int idPessoa);
}