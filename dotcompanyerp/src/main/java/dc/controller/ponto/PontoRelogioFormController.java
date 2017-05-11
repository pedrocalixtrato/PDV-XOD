package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.ponto.PontoRelogio;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoRelogioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoRelogioFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoRelogioFormController extends CRUDFormController<PontoRelogio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoRelogioFormView subView;

	@Autowired
	private PontoRelogioDAO pontoRelogioDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoRelogio currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxFabricante().getValue())) {
			adicionarErroDeValidacao(subView.getTxFabricante(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxLocalizacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxLocalizacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxMarca().getValue())) {
			adicionarErroDeValidacao(subView.getTxMarca(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxNumSerie().getValue())) {
			adicionarErroDeValidacao(subView.getTxNumSerie(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbUtilizacao().getValue())) {
			adicionarErroDeValidacao(subView.getCmbUtilizacao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoRelogio();
		carregarCombosView();
	}

	private void carregarCombosView() {
		subView.carrregarUtilizacao();
	}

	@Override
	protected void initSubView() {
		subView = new PontoRelogioFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombosView();
		currentBean = pontoRelogioDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
			EmpresaEntity empresa = usuario.getConta().getEmpresa();
			currentBean.setEmpresa(empresa);
			pontoRelogioDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Relógio";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoRelogioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoRelogioFormController";
	}

	@Override
	public PontoRelogio getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
