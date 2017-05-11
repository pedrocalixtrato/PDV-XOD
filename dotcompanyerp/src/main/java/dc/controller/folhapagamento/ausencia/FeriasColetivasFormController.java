package dc.controller.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.folhapagamento.ausencia.FeriasColetivasEntity;
import dc.servicos.dao.folhapagamento.ausencia.IFeriasColetivasDAO;
import dc.visao.folhapagamento.ausencia.FeriasColetivasFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class FeriasColetivasFormController extends
		CRUDFormController<FeriasColetivasEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FeriasColetivasFormView subView;

	/** DAO'S */

	@Autowired
	private IFeriasColetivasDAO pDAO;

	/** ENTITIES */

	private FeriasColetivasEntity pEntity;

	/** CONSTRUTOR */

	public FeriasColetivasFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FeriasColetivasEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Férias coletivas";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			Date dataFim = this.subView.getPdfDataFim().getValue();
			Date dataPagamento = this.subView.getPdfDataPagamento().getValue();
			Integer diasGozo = Integer.parseInt(this.subView.getTfDiasGozo()
					.getValue());
			Integer diasAbono = Integer.parseInt(this.subView.getTfDiasAbono()
					.getValue());
			Date abonoPecuniarioInicio = this.subView
					.getPdfAbonoPecuniarioInicio().getValue();
			Date abonoPecuniarioFim = this.subView.getPdfAbonoPecuniarioFim()
					.getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setDataPagamento(dataPagamento);
			this.pEntity.setDiasGozo(diasGozo);
			this.pEntity.setDiasAbono(diasAbono);
			this.pEntity.setAbonoPecuniarioInicio(abonoPecuniarioInicio);
			this.pEntity.setAbonoPecuniarioFim(abonoPecuniarioFim);

			/** Empresa vinda da conta do usuário logado */

			EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/** ************************************** */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new FeriasColetivasEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfDiasGozo().setValue(
					String.valueOf(this.pEntity.getDiasGozo()));
			this.subView.getTfDiasAbono().setValue(
					String.valueOf(this.pEntity.getDiasAbono()));
			this.subView.getPdfAbonoPecuniarioInicio().setValue(
					this.pEntity.getAbonoPecuniarioInicio());
			this.subView.getPdfAbonoPecuniarioFim().setValue(
					this.pEntity.getAbonoPecuniarioFim());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfDiasGozo().setValue(
					String.valueOf(this.pEntity.getDiasGozo()));
			this.subView.getTfDiasAbono().setValue(
					String.valueOf(this.pEntity.getDiasAbono()));
			this.subView.getPdfAbonoPecuniarioInicio().setValue(
					this.pEntity.getAbonoPecuniarioInicio());
			this.subView.getPdfAbonoPecuniarioFim().setValue(
					this.pEntity.getAbonoPecuniarioFim());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new FeriasColetivasEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfDiasGozo().setValue(
					String.valueOf(this.pEntity.getDiasGozo()));
			this.subView.getTfDiasAbono().setValue(
					String.valueOf(this.pEntity.getDiasAbono()));
			this.subView.getPdfAbonoPecuniarioInicio().setValue(
					this.pEntity.getAbonoPecuniarioInicio());
			this.subView.getPdfAbonoPecuniarioFim().setValue(
					this.pEntity.getAbonoPecuniarioFim());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new FeriasColetivasFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new FeriasColetivasEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfDiasGozo().setValue(
					String.valueOf(this.pEntity.getDiasGozo()));
			this.subView.getTfDiasAbono().setValue(
					String.valueOf(this.pEntity.getDiasAbono()));
			this.subView.getPdfAbonoPecuniarioInicio().setValue(
					this.pEntity.getAbonoPecuniarioInicio());
			this.subView.getPdfAbonoPecuniarioFim().setValue(
					this.pEntity.getAbonoPecuniarioFim());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		Date dataInicio = this.subView.getPdfDataInicio().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInicio(), msg);

			return false;
		}

		Date dataFim = this.subView.getPdfDataFim().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataFim)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataFim(), msg);

			return false;
		}

		String diasGozo = this.subView.getTfDiasGozo().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(diasGozo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfDiasGozo(), msg);

			return false;
		}

		Date abonoPecuniarioInicio = this.subView.getPdfAbonoPecuniarioInicio()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(abonoPecuniarioInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(
					this.subView.getPdfAbonoPecuniarioInicio(), msg);

			return false;
		}

		Date abonoPecuniarioFim = this.subView.getPdfAbonoPecuniarioFim()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(abonoPecuniarioFim)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfAbonoPecuniarioFim(),
					msg);

			return false;
		}

		String diasAbono = this.subView.getTfDiasAbono().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(diasAbono)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfDiasAbono(), msg);

			return false;
		}

		Date dataPagamento = this.subView.getPdfDataPagamento().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataPagamento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataPagamento(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public FeriasColetivasEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}