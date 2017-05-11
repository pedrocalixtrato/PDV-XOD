package dc.visao.framework.geral;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.sistema.IContaEmpresaDAO;

@Component
@Scope("prototype")
public class AlterarSenhaController  implements Serializable, ViewController {
	
	private static final long serialVersionUID = -3471150902570201915L;

	@Autowired
	private IContaEmpresaDAO dao;
	
	private AlterarSenhaView view;
	
	public void alteraSenha(String idConta, String value) {
		// TODO Auto-generated method stub
		try{
			ContaEmpresa conta = dao.find(new Integer(idConta));
			conta.getUsuarioCriador().setSenha(value);
			dao.saveOrUpdate(conta);	
			view.notifySaveOK();
		}catch (Exception e) {
			view.showErrorMessage("Problemas ao alterar a senha");
			e.printStackTrace();
		}
		
	}
	

	public void setView(AlterarSenhaView v){
		view = v;
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
