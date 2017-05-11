package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;

public interface IEmpresaSeguimentoDAO {

	public abstract List<EmpresaSeguimento> listaPorEmpresa(EmpresaEntity empresa);

	public abstract List<EmpresaSeguimento> getEmpresaSeguimentoList(EmpresaEntity empresa);

	public abstract void saveOrUpdateEmpresaSeguimentoList(List<EmpresaSeguimento> auxLista);

}