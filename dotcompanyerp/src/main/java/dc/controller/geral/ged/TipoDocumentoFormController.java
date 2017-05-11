package dc.controller.geral.ged;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.ged.TipoDocumento;
import dc.servicos.dao.geral.ged.ITipoDocumentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.ged.TipoDocumentoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TipoDocumentoFormController extends
		CRUDFormController<TipoDocumento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoDocumentoFormView subView;

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;

	private TipoDocumento currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtTamanho().getValue())) {
			adicionarErroDeValidacao(subView.getTxtTamanho(), "Número inválido");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoDocumento();
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
	}

	@Override
	protected void initSubView() {
		subView = new TipoDocumentoFormView();

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoDocumentoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtTamanho().setValue(
				currentBean.getTamanhoMaximo());

	}

	@Override
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		currentBean.setNome(nome);
		currentBean.setTamanhoMaximo(subView.getTxtTamanho().getBigDecimalValue());
		try {
			tipoDocumentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return subView;
	}

	@Override
	protected String getNome() {
		// TODO Auto-generated method stub
		return "Tipo Documento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoDocumentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "tipoDocumentoForm";
	}

	@Override
	public TipoDocumento getModelBean() {
		return currentBean;
	}

}