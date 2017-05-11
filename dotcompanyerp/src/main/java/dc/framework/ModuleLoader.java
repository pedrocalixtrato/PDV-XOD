package dc.framework;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmModulo;
import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.servicos.dao.framework.geral.IFmModuloDAO;
import dc.servicos.dao.sistema.IContaEmpresaDAO;

@Component
@Scope("prototype")
public class ModuleLoader {

	@Autowired
	public IFmModuloDAO dao;

	@Autowired
	public IContaEmpresaDAO daoConf;

	public List<FmModulo> loadModules(UsuarioEntity usuario) {
		List<FmModulo> modules = new ArrayList<FmModulo>();

		List<FmModulo> databaseModules = new ArrayList<FmModulo>();

		if (usuario.getAdministrador()) {
			ConfiguracaoContaEmpresa conta = daoConf.findConfiguracaoByIdContaWithModules(usuario.getConta().getId());
			if (conta != null) {
				databaseModules = conta.getModulos();
			} else {
				databaseModules = new ArrayList<>();
			}
		} else {
			databaseModules = dao.getAllByUserIdOrderedByCaption(usuario.getId());
		}

		modules.addAll(databaseModules);

		FmModulo sistema = FmModulo.loadSystemInstance();
		if (usuario.getLogin().equals(DcConstants.ADMIN_USERNAME)) {
			modules.add(sistema);
		}

		return modules;
	}

	public FmModulo loadModule(Integer id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	/*
	 * public String getModuleViewIdentifier(String moduleID){
	 * 
	 * }
	 */

}
