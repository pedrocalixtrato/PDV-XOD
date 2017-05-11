package dc.servicos.dao.administrativo.empresa;

import java.io.Serializable;
import java.util.List;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.model.dao.AbstractDAO;

public interface IEmpresaDAO extends AbstractDAO<EmpresaEntity>{

	public abstract List<EmpresaEntity> listaTodos();

	public abstract List<EmpresaEntity> procuraNomeContendo(String query);

	/**
	 * *******************************************************
	 */

	public abstract List<EmpresaEntity> getListEmpresa();

	public abstract EmpresaEntity findEmpresa(Integer id);

	public abstract EmpresaEntity findByCNPJ(String cnpj);

	public abstract List<EmpresaEntity> getEmpresaMatrizList();

	public abstract EmpresaEntity findEmpresaByContaEmpresa(Integer contaEmpresaId);

	public abstract void deleteEmpresaList(List<Serializable> auxLista);
	
	public void saveOrUpdateEmpresa(EmpresaEntity empresa) throws Exception;

}