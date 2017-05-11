package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.ponto.PontoEscala;
import dc.entidade.ponto.PontoTurma;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoEscalaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoEscalaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoEscalaFormController extends CRUDFormController<PontoEscala> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoEscalaFormView subView;

	@Autowired
	private PontoEscalaDAO pontoEscalaDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoEscala currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateNumber(subView.getTxCodigoHorarioDomingo().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioDomingo(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioSegunda().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioSegunda(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioTerca().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioTerca(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioQuarta().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioQuarta(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioQuinta().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioQuinta(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioSexta().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioSexta(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxCodigoHorarioSabado().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigoHorarioSabado(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxDescontoDSR().getValue())) {
			adicionarErroDeValidacao(subView.getTxDescontoDSR(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxDescontoHoraDia().getValue())) {
			adicionarErroDeValidacao(subView.getTxDescontoHoraDia(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoEscala();

	}

	@Override
	protected void initSubView() {
		subView = new PontoEscalaFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pontoEscalaDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
		subView.preencheBean(currentBean);
		try {
			pontoEscalaDAO.saveOrUpdate(currentBean);
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
		return "Ponto Escala";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoEscalaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoEscalaFormController";
	}

	public void removerPontoTurma(List<PontoTurma> values) {

		for (PontoTurma ponto : values) {
			currentBean.removePontoTurma(ponto);
		}
	}

	public PontoTurma novoPontoTurma() {
		return currentBean.addPontoTurma();
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PontoEscala getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
