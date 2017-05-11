package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.patrimonio.SeguradoraEntity;
import dc.servicos.dao.patrimonio.SeguradoraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.SeguradoraFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class SeguradoraFormController extends CRUDFormController<SeguradoraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SeguradoraFormView subView;

	/** DAO'S */

	@Autowired
	private SeguradoraDAO pDAO;

	/** ENTITIES */

	private SeguradoraEntity pEntity;

	/** CONSTRUTOR */

	public SeguradoraFormController() {
		if (this.pEntity == null) {
			this.pEntity = new SeguradoraEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Seguradora";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String nome = this.subView.getTfNome().getValue();
			String contato = this.subView.getTfContato().getValue();
			String telefone = this.subView.getTfTelefone().getValue();

			this.pEntity.setNome(nome);
			this.pEntity.setContato(contato);
			this.pEntity.setTelefone(telefone);

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
			this.pEntity = new SeguradoraEntity();

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContato().setValue(this.pEntity.getContato());
			this.subView.getTfTelefone().setValue(this.pEntity.getTelefone());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContato().setValue(this.pEntity.getContato());
			this.subView.getTfTelefone().setValue(this.pEntity.getTelefone());
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

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new SeguradoraFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new SeguradoraEntity();

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContato().setValue(this.pEntity.getContato());
			this.subView.getTfTelefone().setValue(this.pEntity.getTelefone());
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

		String contato = this.subView.getTfContato().getValue();

		if (!ObjectValidator.validateString(contato)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfContato(), msg);

			return false;
		}

		// String telefone = this.subView.getTfTelefone().getValue();

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_seguradora_fc";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public SeguradoraEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}