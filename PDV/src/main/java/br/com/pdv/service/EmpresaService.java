package br.com.pdv.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.EmpresaDAO;
import br.com.pdv.domain.Empresa;
import br.com.pdv.domain.Usuario;

public class EmpresaService implements Serializable {
	
	@Inject
	private EmpresaDAO empresaDAO;
	
	public void salvar(Empresa empresa, Usuario usuario) {

		try {
			
			
			empresaDAO.salvar(empresa);
			Messages.addGlobalInfo("Salvo com sucesso!");
		} catch (RuntimeException e) {

			Messages.addGlobalError("Não foi possivel salvar este cadastro");
			e.printStackTrace();

		}

}
}
