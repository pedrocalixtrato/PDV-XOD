package dc.controller.ponto;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.ponto.PontoMarcacao;
import dc.model.dao.administrativo.seguranca.IUsuarioDAO;
import dc.servicos.dao.ponto.PontoMarcacaoDAO;
import dc.visao.framework.geral.BlankFormController;
import dc.visao.ponto.PontoMarcacaoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoMarcacaoFormController extends BlankFormController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoMarcacaoFormView subView;

	@Autowired
	PontoMarcacaoDAO pontoMarcacaoDAO;
	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Marcação";
	}

	@Override
	public String getViewIdentifier() {
		return "pontoMarcacaoFormController";
	}

	@Override
	protected void initSubView() {
		subView = new PontoMarcacaoFormView();

		Thread atulizaRelogia = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					subView.getLblDataHora().setValue(new Date().toString());
					subView.getLblDataHora().markAsDirty();
					try {
						Thread.sleep(50000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		subView.getBtnMarcar().addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
				Calendar dataAtual = Calendar.getInstance();

				// validar

				String user = subView.getTxUsuario().getValue();
				String senha = subView.getPwSenha().getValue();
				if (usuario.getSenha().equals(senha) && usuario.getLogin().equals(user)) {
					usuario = usuarioDAO.find(usuario.getId());
					PontoMarcacao pontoMarcacao = pontoMarcacaoDAO.getPontoMarcacao(usuario, dataAtual);
					pontoMarcacaoDAO.save(pontoMarcacao);

					mensagemSalvoOK();
				} else {
					mensagemErro("Usuário e/ou senha inválido(s)");
				}

			}
		});
		// atulizaRelogia.run();
	};

}
