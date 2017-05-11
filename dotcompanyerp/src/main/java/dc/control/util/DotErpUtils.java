package dc.control.util;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.visao.spring.SecuritySessionProvider;

public class DotErpUtils {

	public static EmpresaEntity getEmpresaUsuarioLogado() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

}