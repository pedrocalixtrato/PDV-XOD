package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.folhapagamento.movimento.FechamentoEntity;
import dc.servicos.dao.folhapagamento.movimento.FechamentoDAO;
import dc.visao.folhapagamento.movimento.FechamentoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class FechamentoFormController extends
		CRUDFormController<FechamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FechamentoFormView subView;

	/** DAO'S */

	@Autowired
	private FechamentoDAO pDAO;

	/** ENTITIES */

	private FechamentoEntity pEntity;

	/** CONSTRUTOR */

	public FechamentoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FechamentoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Fechamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String fechamentoAtual = this.subView.getTfFechamentoAtual()
					.getValue();
			String proximoFechamento = this.subView.getTfProximoFechamento()
					.getValue();

			this.pEntity.setFechamentoAtual(fechamentoAtual);
			this.pEntity.setProximoFechamento(proximoFechamento);

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
			this.pEntity = new FechamentoEntity();

			this.subView.getTfFechamentoAtual().setValue(
					this.pEntity.getFechamentoAtual());
			this.subView.getTfProximoFechamento().setValue(
					this.pEntity.getProximoFechamento());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfFechamentoAtual().setValue(
					this.pEntity.getFechamentoAtual());
			this.subView.getTfProximoFechamento().setValue(
					this.pEntity.getProximoFechamento());
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
			this.pEntity = new FechamentoEntity();

			this.subView.getTfFechamentoAtual().setValue(
					this.pEntity.getFechamentoAtual());
			this.subView.getTfProximoFechamento().setValue(
					this.pEntity.getProximoFechamento());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new FechamentoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new FechamentoEntity();

			this.subView.getTfFechamentoAtual().setValue(
					this.pEntity.getFechamentoAtual());
			this.subView.getTfProximoFechamento().setValue(
					this.pEntity.getProximoFechamento());
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
	public FechamentoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}