package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.PontoBancoHoras;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoBancoHorasDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoBancoHorasFormView;

@Controller
@Scope("prototype")
public class PontoBancoHorasFormController extends CRUDFormController<PontoBancoHoras> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoBancoHorasFormView subView;

	@Autowired
	private PontoBancoHorasDAO pontoBancoHorasDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private PontoBancoHoras currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxQuantidade().getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidade(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDataTrabalho().getValue())) {
			adicionarErroDeValidacao(subView.getDtDataTrabalho(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbColaborador().getValue())) {
			adicionarErroDeValidacao(subView.getCmbColaborador(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbSituacao().getValue())) {
			adicionarErroDeValidacao(subView.getCmbColaborador(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoBancoHoras();
		carregarCombosView();
	}

	private void carregarCombosView() {
		subView.carregarColaboradores(colaboradorDAO.getAll(ColaboradorEntity.class));
		subView.carregarSituacao();
	}

	@Override
	protected void initSubView() {
		subView = new PontoBancoHorasFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombosView();
		currentBean = pontoBancoHorasDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			pontoBancoHorasDAO.saveOrUpdate(currentBean);
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
		return "Ponto Banco Horas";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoBancoHorasDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoBancoHorasFormController";
	}

	@Override
	public PontoBancoHoras getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
