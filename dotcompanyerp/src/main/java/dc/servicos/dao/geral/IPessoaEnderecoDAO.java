package dc.servicos.dao.geral;

import java.io.Serializable;
import java.util.List;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaEnderecoDAO extends AbstractDAO<PessoaEnderecoEntity>{

	public abstract List<PessoaEnderecoEntity> listaTodos();

	public abstract List<PessoaEnderecoEntity> listaPorEmpresa(EmpresaEntity empresa);

	public abstract List<PessoaEnderecoEntity> listaPorPessoa(PessoaEntity pessoa);

	public abstract void deletePessoaEnderecoList(List<Serializable> auxLista);

	public abstract List<PessoaEnderecoEntity> getPessoaEnderecoList(EmpresaEntity entity);

	public abstract List<PessoaEnderecoEntity> getPessoaEnderecoList(PessoaEntity entity);

	public abstract void saveOrUpdatePessoaEnderecoList(List<PessoaEnderecoEntity> auxLista, EmpresaEntity empresa);

	public abstract List<PessoaEnderecoEntity> findByPessoaEndereco(PessoaEntity pessoa);

}