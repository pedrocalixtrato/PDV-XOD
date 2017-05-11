package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.PontoAbono;
import dc.entidade.ponto.PontoAbonoUtilizacao;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.ponto.IPontoAbonoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoAbonoFormView;

@Controller
@Scope("prototype")
public class PontoAbonoFormController extends CRUDFormController<PontoAbono> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoAbonoFormView subView;

	@Autowired
	private IPontoAbonoDAO pontoAbonoDAO;
	@Autowired
	private IColaboradorDAO colaboradorDAO;

	private PontoAbono currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateNumber(subView.getTxQuantidade().getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidade(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxSaldo().getValue())) {
			adicionarErroDeValidacao(subView.getTxSaldo(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxUtilizado().getValue())) {
			adicionarErroDeValidacao(subView.getTxUtilizado(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaObservacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbColaborador().getValue())) {
			adicionarErroDeValidacao(subView.getCbColaborador(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtCadastro().getValue())) {
			adicionarErroDeValidacao(subView.getDtCadastro(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtUtilizacao().getValue())) {
			adicionarErroDeValidacao(subView.getDtUtilizacao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoAbono();
	}

	@Override
	protected void initSubView() {
		subView = new PontoAbonoFormView(this);

		DefaultManyToOneComboModel<ColaboradorEntity> colaboradorModel = new DefaultManyToOneComboModel<ColaboradorEntity>(ColaboradorListController.class,
				this.colaboradorDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};

		subView.getCbColaborador().setModel(colaboradorModel);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pontoAbonoDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			pontoAbonoDAO.saveOrUpdate(currentBean);
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
		return "Ponto Abono";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoAbonoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoAbonoFormController";
	}

	public void removerPontoAbonoUtilizacao(List<PontoAbonoUtilizacao> values) {

		for (PontoAbonoUtilizacao ponto : values) {
			currentBean.removePontoAbonoUtilizacao(ponto);
		}
	}

	public PontoAbonoUtilizacao novoPontoAbonoUtilizacao() {
		return currentBean.addPontoAbonoUtilizacao();
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PontoAbono getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
