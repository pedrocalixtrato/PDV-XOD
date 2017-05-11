package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaDAO extends AbstractDAO<PessoaEntity> {

	public PessoaFisicaEntity getPessoaFisica(Integer id);
	public PessoaJuridicaEntity getPessoaJuridica(Integer idPessoa);

}