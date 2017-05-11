package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.ponto.PontoHorario;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoHorarioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoHorarioFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoHorarioFormController extends CRUDFormController<PontoHorario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoHorarioFormView subView;

	@Autowired
	private PontoHorarioDAO pontoHorarioDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoHorario currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxEntrada01().getValue())) {
			adicionarErroDeValidacao(subView.getTxEntrada01(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxEntrada02().getValue())) {
			adicionarErroDeValidacao(subView.getTxEntrada02(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxEntrada03().getValue())) {
			adicionarErroDeValidacao(subView.getTxEntrada03(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxEntrada04().getValue())) {
			adicionarErroDeValidacao(subView.getTxEntrada04(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxEntrada05().getValue())) {
			adicionarErroDeValidacao(subView.getTxEntrada05(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxSaida01().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaida01(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateString(subView.getTxSaida02().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaida02(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxSaida03().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaida03(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxSaida04().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaida04(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxSaida05().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaida05(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxCargaHoraria().getValue())) {
			adicionarErroDeValidacao(subView.getTxCargaHoraria(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigo(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxHoraFimJornada().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraFimJornada(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxHoraInicioJornada().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraInicioJornada(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateString(subView.getTxNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTipo().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipo(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateObject(subView.getCmbTipoTrabalho().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipoTrabalho(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoHorario();
		carregarCombosView();
	}

	private void carregarCombosView() {
		subView.carregarTipo();
		subView.carregarTipoTrabalho();
	}

	@Override
	protected void initSubView() {
		subView = new PontoHorarioFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombosView();
		currentBean = pontoHorarioDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
			EmpresaEntity empresa = usuario.getConta().getEmpresa();
			currentBean.setEmpresa(empresa);
			pontoHorarioDAO.saveOrUpdate(currentBean);
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
		return "Ponto Horário";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoHorarioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoHorarioFormController";
	}

	@Override
	public PontoHorario getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
