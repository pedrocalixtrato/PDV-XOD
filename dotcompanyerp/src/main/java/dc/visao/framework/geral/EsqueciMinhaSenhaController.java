package dc.visao.framework.geral;

import java.io.Serializable;
import java.net.URI;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;
import com.vaadin.server.Page;

import dc.entidade.sistema.ContaEmpresa;
import dc.framework.mail.MailSender;
import dc.servicos.dao.sistema.IContaEmpresaDAO;

@Component
@Scope("prototype")
public class EsqueciMinhaSenhaController implements Serializable, ViewController {
	
	private static final long serialVersionUID = 3908353011945861868L;

	EsqueciMinhaSenhaView view;
	
	@Autowired
	private transient IContaEmpresaDAO dao;
	
	public static Logger logger = Logger.getLogger(EsqueciMinhaSenhaController.class);
	
	public void enviaEmail(String email){
			MailSender sender = new MailSender();
			ContaEmpresa c = dao.findByEmail(email);
			boolean showOkMessage = true;
			if(c!= null){
				String url = generateTokenizedURL(c);
				try {
					sender.send(email, "Instruções para obter nova senha - DotCompanyERP", "Clique <a href=\"" +  url + "\"> aqui</a> para cadastrar uma nova senha para sua conta no DotCompanyERP.</br>",true);
				} catch (Exception e) {
					showOkMessage=false;
					view.showErrorMessage("Problemas ao enviar e-mail");
					e.printStackTrace();
				}
			}
			
			if(showOkMessage){
				view.notifyMailOK();	
			}
	}


	private String generateTokenizedURL(ContaEmpresa c) {
		DateTime date = new DateTime().toDateMidnight().toDateTime();
		DateTime futureDay = date.plusDays(2);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-YYYY");
		String dateToken = fmt.print(futureDay);
		
		String token  = "";
		Base64 encoder = new Base64();
		token  = encoder.encodeAsString((c.getId() + "#"+ dateToken + "#" + this.getClass().getName()).getBytes());
		
		URI uri = Page.getCurrent().getLocation();
		String path = uri.getPath();
		String[] paths =path.split("/");
		String url =  uri.getScheme() +"://"+ uri.getAuthority() + "/"+ paths[1] + "/alterarSenha?token=" +token;
		return url;
	}
		
		
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		
	}

	

	public void setView(EsqueciMinhaSenhaView v){
		view = v;
	}


	
}
