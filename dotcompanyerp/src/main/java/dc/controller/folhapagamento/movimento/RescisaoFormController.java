package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.folhapagamento.movimento.RescisaoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.folhapagamento.movimento.RescisaoDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.visao.folhapagamento.movimento.RescisaoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class RescisaoFormController extends CRUDFormController<RescisaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RescisaoFormView subView;

	/** DAO'S */

	@Autowired
	private RescisaoDAO pDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	/** ENTITIES */

	private RescisaoEntity pEntity;

	/** CONSTRUTOR */

	public RescisaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new RescisaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Rescisão";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataDemissao = this.subView.getPdfDataDemissao().getValue();
			Date dataPagamento = this.subView.getPdfDataPagamento().getValue();
			String motivo = this.subView.getTfMotivo().getValue();
			Date dataAvisoPrevio = this.subView.getPdfDataAvisoPrevio()
					.getValue();
			Integer diasAvisoPrevio = Integer.parseInt(this.subView
					.getTfDiasAvisoPrevio().getValue());
			String comprovouNovoEmprego = this.subView
					.getTfComprovouNovoEmprego().getValue();
			String dispensouEmpregado = this.subView.getTfDispensouEmpregado()
					.getValue();
			Double pensaoAlimenticia = Double.parseDouble(this.subView
					.getTfPensaoAlimenticia().getValue());
			Double pensaoAlimenticiaFgts = Double.parseDouble(this.subView
					.getTfPensaoAlimenticiaFgts().getValue());
			Double fgtsValorRescisao = Double.parseDouble(this.subView
					.getTfFgtsValorRescisao().getValue());
			Double fgtsSaldoBanco = Double.parseDouble(this.subView
					.getTfFgtsSaldoBanco().getValue());
			Double fgtsComplementoSaldo = Double.parseDouble(this.subView
					.getTfFgtsComplementoSaldo().getValue());
			String fgtsCodigoAfastamento = this.subView
					.getTfFgtsCodigoAfastamento().getValue();
			String fgtsCodigoSaque = this.subView.getTfFgtsCodigoSaque()
					.getValue();

			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
					.getCbColaborador().getValue();

			this.pEntity.setDataDemissao(dataDemissao);
			this.pEntity.setDataPagamento(dataPagamento);
			this.pEntity.setMotivo(motivo);
			this.pEntity.setDataAvisoPrevio(dataAvisoPrevio);
			this.pEntity.setDiasAvisoPrevio(diasAvisoPrevio);
			this.pEntity.setComprovouNovoEmprego(comprovouNovoEmprego);
			this.pEntity.setDispensouEmpregado(dispensouEmpregado);
			this.pEntity.setPensaoAlimenticia(pensaoAlimenticia);
			this.pEntity.setPensaoAlimenticiaFgts(pensaoAlimenticiaFgts);
			this.pEntity.setFgtsValorRescisao(fgtsValorRescisao);
			this.pEntity.setFgtsSaldoBanco(fgtsSaldoBanco);
			this.pEntity.setFgtsComplementoSaldo(fgtsComplementoSaldo);
			this.pEntity.setFgtsCodigoAfastamento(fgtsCodigoAfastamento);
			this.pEntity.setFgtsCodigoSaque(fgtsCodigoSaque);

			this.pEntity.setColaborador(colaborador);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new RescisaoEntity();

			this.subView.getPdfDataDemissao().setValue(
					this.pEntity.getDataDemissao());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());
			this.subView.getPdfDataAvisoPrevio().setValue(
					this.pEntity.getDataAvisoPrevio());
			this.subView.getTfDiasAvisoPrevio().setValue(
					String.valueOf(this.pEntity.getDiasAvisoPrevio()));
			this.subView.getTfComprovouNovoEmprego().setValue(
					this.pEntity.getComprovouNovoEmprego());
			this.subView.getTfDispensouEmpregado().setValue(
					this.pEntity.getDispensouEmpregado());
			this.subView.getTfPensaoAlimenticia().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticia()));
			this.subView.getTfPensaoAlimenticiaFgts().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticiaFgts()));
			this.subView.getTfFgtsValorRescisao().setValue(
					String.valueOf(this.pEntity.getFgtsValorRescisao()));
			this.subView.getTfFgtsSaldoBanco().setValue(
					String.valueOf(this.pEntity.getFgtsSaldoBanco()));
			this.subView.getTfFgtsComplementoSaldo().setValue(
					String.valueOf(this.pEntity.getFgtsComplementoSaldo()));
			this.subView.getTfFgtsCodigoAfastamento().setValue(
					this.pEntity.getFgtsCodigoAfastamento());
			this.subView.getTfFgtsCodigoSaque().setValue(
					this.pEntity.getFgtsCodigoSaque());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataDemissao().setValue(
					this.pEntity.getDataDemissao());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());
			this.subView.getPdfDataAvisoPrevio().setValue(
					this.pEntity.getDataAvisoPrevio());
			this.subView.getTfDiasAvisoPrevio().setValue(
					String.valueOf(this.pEntity.getDiasAvisoPrevio()));
			this.subView.getTfComprovouNovoEmprego().setValue(
					this.pEntity.getComprovouNovoEmprego());
			this.subView.getTfDispensouEmpregado().setValue(
					this.pEntity.getDispensouEmpregado());
			this.subView.getTfPensaoAlimenticia().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticia()));
			this.subView.getTfPensaoAlimenticiaFgts().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticiaFgts()));
			this.subView.getTfFgtsValorRescisao().setValue(
					String.valueOf(this.pEntity.getFgtsValorRescisao()));
			this.subView.getTfFgtsSaldoBanco().setValue(
					String.valueOf(this.pEntity.getFgtsSaldoBanco()));
			this.subView.getTfFgtsComplementoSaldo().setValue(
					String.valueOf(this.pEntity.getFgtsComplementoSaldo()));
			this.subView.getTfFgtsCodigoAfastamento().setValue(
					this.pEntity.getFgtsCodigoAfastamento());
			this.subView.getTfFgtsCodigoSaque().setValue(
					this.pEntity.getFgtsCodigoSaque());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
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
			this.pEntity = new RescisaoEntity();

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getPdfDataDemissao().setValue(
					this.pEntity.getDataDemissao());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());
			this.subView.getPdfDataAvisoPrevio().setValue(
					this.pEntity.getDataAvisoPrevio());
			this.subView.getTfDiasAvisoPrevio().setValue(
					String.valueOf(this.pEntity.getDiasAvisoPrevio()));
			this.subView.getTfComprovouNovoEmprego().setValue(
					this.pEntity.getComprovouNovoEmprego());
			this.subView.getTfDispensouEmpregado().setValue(
					this.pEntity.getDispensouEmpregado());
			this.subView.getTfPensaoAlimenticia().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticia()));
			this.subView.getTfPensaoAlimenticiaFgts().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticiaFgts()));

			this.subView.getTfFgtsValorRescisao().setValue(
					String.valueOf(this.pEntity.getFgtsValorRescisao()));
			this.subView.getTfFgtsSaldoBanco().setValue(
					String.valueOf(this.pEntity.getFgtsSaldoBanco()));
			this.subView.getTfFgtsComplementoSaldo().setValue(
					String.valueOf(this.pEntity.getFgtsComplementoSaldo()));
			this.subView.getTfFgtsCodigoAfastamento().setValue(
					this.pEntity.getFgtsCodigoAfastamento());
			this.subView.getTfFgtsCodigoSaque().setValue(
					this.pEntity.getFgtsCodigoSaque());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new RescisaoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new RescisaoEntity();

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getPdfDataDemissao().setValue(
					this.pEntity.getDataDemissao());
			this.subView.getPdfDataPagamento().setValue(
					this.pEntity.getDataPagamento());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());
			this.subView.getPdfDataAvisoPrevio().setValue(
					this.pEntity.getDataAvisoPrevio());
			this.subView.getTfDiasAvisoPrevio().setValue(
					String.valueOf(this.pEntity.getDiasAvisoPrevio()));
			this.subView.getTfComprovouNovoEmprego().setValue(
					this.pEntity.getComprovouNovoEmprego());
			this.subView.getTfDispensouEmpregado().setValue(
					this.pEntity.getDispensouEmpregado());
			this.subView.getTfPensaoAlimenticia().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticia()));
			this.subView.getTfPensaoAlimenticiaFgts().setValue(
					String.valueOf(this.pEntity.getPensaoAlimenticiaFgts()));

			this.subView.getTfFgtsValorRescisao().setValue(
					String.valueOf(this.pEntity.getFgtsValorRescisao()));
			this.subView.getTfFgtsSaldoBanco().setValue(
					String.valueOf(this.pEntity.getFgtsSaldoBanco()));
			this.subView.getTfFgtsComplementoSaldo().setValue(
					String.valueOf(this.pEntity.getFgtsComplementoSaldo()));
			this.subView.getTfFgtsCodigoAfastamento().setValue(
					this.pEntity.getFgtsCodigoAfastamento());
			this.subView.getTfFgtsCodigoSaque().setValue(
					this.pEntity.getFgtsCodigoSaque());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
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
		Object dataDemissao = this.subView.getPdfDataDemissao().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataDemissao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataDemissao(), msg);

			return false;
		}

		Object dataPagamento = this.subView.getPdfDataPagamento().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataPagamento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataPagamento(), msg);

			return false;
		}

		Object dataAvisoPrevio = this.subView.getPdfDataAvisoPrevio()
				.getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataAvisoPrevio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataAvisoPrevio(), msg);

			return false;
		}

		String diasAvisoPrevio = this.subView.getTfDiasAvisoPrevio().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(diasAvisoPrevio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfDiasAvisoPrevio(), msg);

			return false;
		}

		String pensaoAlimenticia = this.subView.getTfPensaoAlimenticia()
				.getValue();

		if (!ObjectValidator.validateNotRequiredNumber(pensaoAlimenticia)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPensaoAlimenticia(), msg);

			return false;
		}

		String pensaoAlimenticiaFgts = this.subView
				.getTfPensaoAlimenticiaFgts().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(pensaoAlimenticiaFgts)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPensaoAlimenticiaFgts(),
					msg);

			return false;
		}

		String fgtsValorRescisao = this.subView.getTfFgtsValorRescisao()
				.getValue();

		if (!ObjectValidator.validateNotRequiredNumber(fgtsValorRescisao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfFgtsValorRescisao(), msg);

			return false;
		}

		String fgtsSaldoBanco = this.subView.getTfFgtsSaldoBanco().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(fgtsSaldoBanco)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfFgtsSaldoBanco(), msg);

			return false;
		}

		String fgtsComplementoSaldo = this.subView.getTfFgtsComplementoSaldo()
				.getValue();

		if (!ObjectValidator.validateNotRequiredNumber(fgtsComplementoSaldo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfFgtsComplementoSaldo(),
					msg);

			return false;
		}

		ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
				.getCbColaborador().getValue();

		if (!ObjectValidator.validateObject(colaborador)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbColaborador(), msg);

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

	public List<ColaboradorEntity> colaboradorListarTodos() {
		List<ColaboradorEntity> auxLista = new ArrayList<ColaboradorEntity>();

		auxLista = this.cDAO.colaboradorLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public RescisaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}