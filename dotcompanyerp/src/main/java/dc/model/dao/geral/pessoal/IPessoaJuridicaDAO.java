package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaJuridicaDAO extends AbstractDAO<PessoaJuridicaEntity> {

	public PessoaJuridicaEntity getPessoaJuridica(int idPessoa);
}