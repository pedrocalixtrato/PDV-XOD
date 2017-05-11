package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.servicos.dao.patrimonio.EstadoConservacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.EstadoConservacaoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class EstadoConservacaoFormController extends CRUDFormController<EstadoConservacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstadoConservacaoFormView subView;

	/** DAO'S */

	@Autowired
	private EstadoConservacaoDAO pDAO;

	/** ENTITIES */

	private EstadoConservacaoEntity pEntity;

	/** CONSTRUTOR */

	public EstadoConservacaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EstadoConservacaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Estado de conservação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String codigo = this.subView.getTfCodigo().getValue();
			String nome = this.subView.getTfNome().getValue();
			String descricao = this.subView.getTfDescricao().getValue();

			this.pEntity.setCodigo(codigo);
			this.pEntity.setNome(nome);
			this.pEntity.setDescricao(descricao);

			/** Empresa vinda da conta do usuário logado */

			EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/** ************************************** */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new EstadoConservacaoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
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
			this.pEntity = new EstadoConservacaoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new EstadoConservacaoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new EstadoConservacaoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
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
		String nome = (String) this.subView.getTfNome().getValue();

		if (!ObjectValidator.validateString(nome)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNome(), msg);

			return false;
		}

		String codigo = (String) this.subView.getTfCodigo().getValue();

		if (!ObjectValidator.validateString(codigo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfCodigo(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_estado_conservacao_fc";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public EstadoConservacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}