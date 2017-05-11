package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.framework.mail.MailSender;
import dc.model.dao.administrativo.seguranca.IUsuarioDAO;
import dc.servicos.dao.administrativo.empresa.IEmpresaDAO;
import dc.servicos.dao.sistema.IContaEmpresaDAO;

@Component
@Scope("prototype")
public class CriaContaController implements Serializable, ViewController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CriaContaEmpresaView view;

	@Autowired
	transient IContaEmpresaDAO dao;

	@Autowired
	transient IUsuarioDAO usuarioDao;

	@Autowired
	transient IEmpresaDAO empresaDao;
	
	private ContaEmpresa currentBean;

	public static Logger logger = Logger.getLogger(CriaContaController.class);

	public void criarconta(ContaEmpresa c) {
		currentBean = c;

		boolean saved = false;

		logger.info("Conta empresa, tentativa de criação");
		logger.info(String.valueOf(c) + c.getNome() + c.getEmail());

		try {
			currentBean.getEmpresa().setRazaoSocial(
					currentBean.getEmpresa().getNomeFantasia());
			currentBean.getEmpresa().setContaEmpresa(currentBean);
			currentBean.getUsuarioCriador().setLogin(currentBean.getEmail());
			currentBean.getUsuarioCriador().setAdministrador(true);
			currentBean.getUsuarioCriador().setUsernome(currentBean.getNome());
			currentBean.getUsuarioCriador().setDataCadastro(new Date());
			currentBean.getUsuarioCriador().setConfirmado(true);
			currentBean.getUsuarioCriador().setConta(currentBean);

			if (validaConta()) {
				dao.save(currentBean);
				saved = true;
			}
		} catch (Exception e) {
			view.showErrorMessage("Problemas ao registrar conta. Por favor, tente novamente em instantes.");
			e.printStackTrace();
		}

		if (saved) {
			MailSender sender = new MailSender();

			try {
				sender.send(
						currentBean.getEmail(),
						"Bem vindo ao DotCompany ERP",
						"Bem vindo ao DotCompany ERP.</br> Clique <a href=\"http://www.dotcompanyerp.com.br/online\"> aqui</a> para acessar o sistema </br>",
						true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			view.notifySaveOK(currentBean);
		}
	}

	private boolean validaConta() {
		// TODO Auto-generated method stub
		UsuarioEntity u = usuarioDao.getUsuarioByLogin(currentBean.getEmail());
		ContaEmpresa c = dao.findByEmail(currentBean.getEmail());

		if (u != null || c != null) {
			view.showErrorMessage("E-mail já é utilizado por outro Usuário do sistema.");

			return false;
		} else {
			EmpresaEntity emp = empresaDao.findByCNPJ(currentBean.getEmpresa()
					.getCnpj());

			if (emp != null) {
				view.showErrorMessage("CNPJ já é utilizado por outro Usuário do sistema.");

				return false;
			}
		}

		return true;
	}

	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		currentBean = new ContaEmpresa();
		currentBean.setEmpresa(new EmpresaEntity());
		UsuarioEntity u = new UsuarioEntity();
		PapelEntity p = new PapelEntity();
		p.setId(PapelEntity.MASTER_ID);
		u.setPapel(p);
		u.setEmpresa(currentBean.getEmpresa());
		currentBean.setPrimeiroUsuario(u);
	}

	public ContaEmpresa getCurrentBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

	public void setView(CriaContaEmpresaView v) {
		view = v;
	}

}