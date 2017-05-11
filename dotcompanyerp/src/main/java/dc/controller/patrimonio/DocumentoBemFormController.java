package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.patrimonio.DocumentoBemEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.servicos.dao.patrimonio.DocumentoBemDAO;
import dc.servicos.dao.patrimonio.IBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.DocumentoBemFormView;

@Controller
@Scope("prototype")
public class DocumentoBemFormController extends CRUDFormController<DocumentoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoBemFormView subView;

	/** DAO'S */

	@Autowired
	private DocumentoBemDAO pDAO;

	@Autowired
	private IBemDAO bDAO;

	/** ENTITIES */

	private DocumentoBemEntity pEntity;

	public DocumentoBemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DocumentoBemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Documento do bem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String nome = this.subView.getTfNome().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			String imagem = this.subView.getTfImagem().getValue();

			BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();

			this.pEntity.setNome(nome);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setImagem(imagem);
			this.pEntity.setBem(bem);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new DocumentoBemEntity();

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfImagem().setValue(this.pEntity.getImagem());

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfImagem().setValue(this.pEntity.getImagem());
			this.subView.getCbBem().setValue(this.pEntity.getBem());
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
			this.pEntity = new DocumentoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfImagem().setValue(this.pEntity.getImagem());

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new DocumentoBemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new DocumentoBemEntity();

			if (this.bDAO == null) {
				this.bDAO = new BemDAO();
			}

			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfImagem().setValue(this.pEntity.getImagem());

			this.subView.carregarCmbBem(this.bemListarTodos());

			this.subView.getCbBem().setValue(this.pEntity.getBem());
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
		BemEntity bem = (BemEntity) this.subView.getCbBem().getValue();

		if (!ObjectValidator.validateObject(bem)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbBem(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_documento_bem_fc";
	}

	/** COMBOS */

	public List<BemEntity> bemListarTodos() {
		List<BemEntity> auxLista = new ArrayList<BemEntity>();

		auxLista = this.bDAO.bemLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public DocumentoBemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}