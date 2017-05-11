package dc.control.util;

import dc.controller.suprimento.compra.CotacaoFormController;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.visao.framework.geral.ControllerTask;
import dc.visao.spring.SecuritySessionProvider;

public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

	public static synchronized String getUrl(ControllerTask c) {
		String s = c.getClass().getName();

		String[] s1 = s.split("[.]");

		StringBuilder sUrl = new StringBuilder("");

		for (int i = 2; i < s1.length; i++) {
			sUrl = sUrl.append(s1[i].toLowerCase());
			sUrl = sUrl.append("_");
		}

		int tamanho = sUrl.length();

		return sUrl.toString().substring(0, tamanho - 1);
	}

	public static synchronized UsuarioEntity getUsuario() {
		try {
			UsuarioEntity usuario = SecuritySessionProvider.getUsuario();

			return usuario;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * Para realizar testes
	 */

	public static void main(String[] args) {
		try {
			System.out.println(ClassUtils.getUrl(CotacaoFormController.class
					.newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}