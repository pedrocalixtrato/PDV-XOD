package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.PontoHorarioAutorizado;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoHorarioAutorizadoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoHorarioAutorizadoFormView;

@Controller
@Scope("prototype")
public class PontoHorarioAutorizadoFormController extends CRUDFormController<PontoHorarioAutorizado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoHorarioAutorizadoFormView subView;

	@Autowired
	private PontoHorarioAutorizadoDAO pontoHorarioAutorizadoDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoHorarioAutorizado currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxCargaHoraria().getValue())) {
			adicionarErroDeValidacao(subView.getTxCargaHoraria(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxDataHorario().getValue())) {
			adicionarErroDeValidacao(subView.getTxDataHorario(), "Não pode ficar em branco");
			valido = false;
		}

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

		if (!Validator.validateString(subView.getTxHoraFechamentoDia().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraFechamentoDia(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbColaborador().getValue())) {
			adicionarErroDeValidacao(subView.getCmbColaborador(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoHorarioAutorizado();
		carregarCombosView();
	}

	private void carregarCombosView() {
		subView.carregarColaboradores(colaboradorDAO.getAll(ColaboradorEntity.class));
		subView.carregarTipo();
	}

	@Override
	protected void initSubView() {
		subView = new PontoHorarioAutorizadoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombosView();
		currentBean = pontoHorarioAutorizadoDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			pontoHorarioAutorizadoDAO.saveOrUpdate(currentBean);
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
		return "Ponto Horário Autorizado";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoHorarioAutorizadoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoHorarioAutorizadoFormController";
	}

	@Override
	public PontoHorarioAutorizado getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
