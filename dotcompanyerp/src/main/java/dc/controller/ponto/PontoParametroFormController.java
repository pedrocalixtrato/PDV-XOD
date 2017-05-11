package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.ponto.PontoParametro;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoParametroDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoParametroFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoParametroFormController extends CRUDFormController<PontoParametro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoParametroFormView subView;

	@Autowired
	private PontoParametroDAO pontoParametroDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoParametro currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxDiaInicialApuracao().getValue())) {
			adicionarErroDeValidacao(subView.getTxDiaInicialApuracao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxDuracaoHora().getValue())) {
			adicionarErroDeValidacao(subView.getTxDuracaoHora(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxHoraNoturnaFim().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraNoturnaFim(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxHoraNoturnaInicio().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraNoturnaInicio(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxHoraNoturnaFim().getValue())) {
			adicionarErroDeValidacao(subView.getTxHoraNoturnaFim(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxPercentual().getValue())) {
			adicionarErroDeValidacao(subView.getTxPercentual(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxPercentualHeDiurna().getValue())) {
			adicionarErroDeValidacao(subView.getTxPercentualHeDiurna(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxPercentualHeNoturna().getValue())) {
			adicionarErroDeValidacao(subView.getTxPercentualHeNoturna(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxPeriodoMinimo().getValue())) {
			adicionarErroDeValidacao(subView.getTxPeriodoMinimo(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTratamentoHoraMais().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTratamentoHoraMais(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTratamentoHoraMenos().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTratamentoHoraMenos(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoParametro();
	}

	@Override
	protected void initSubView() {
		subView = new PontoParametroFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pontoParametroDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
			EmpresaEntity empresa = usuario.getConta().getEmpresa();
			currentBean.setEmpresa(empresa);
			pontoParametroDAO.saveOrUpdate(currentBean);
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
		return "Ponto Parâmetro";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoParametroDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "PontoParametroFormController";
	}

	@Override
	public PontoParametro getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
