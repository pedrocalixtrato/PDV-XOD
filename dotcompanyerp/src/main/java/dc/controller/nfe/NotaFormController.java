package dc.controller.nfe;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.servicos.dao.contabilidade.livrocontabil.LivroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.NotaFormView;

@Controller
@Scope("prototype")
public class NotaFormController extends CRUDFormController<LivroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NotaFormView subView;

	/** DAO'S */

	@Autowired
	private LivroDAO pDAO;

	/** ENTITIES */

	private LivroEntity pEntity;

	/** CONSTRUTOR */

	public NotaFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LivroEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Livro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			// String descricao = this.subView.getTfDescricao().getValue();
			// String competencia = this.subView.getTfCompetencia().getValue();
			// String formaEscrituracao = this.subView.getTfFormaEscrituracao()
			// .getValue();

			// this.pEntity.setDescricao(descricao);
			// this.pEntity.setCompetencia(competencia);
			// this.pEntity.setFormaEscrituracao(formaEscrituracao);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new NotaFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new LivroEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			// this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			// this.subView.getTfCompetencia().setValue(
			// this.pEntity.getCompetencia());
			// this.subView.getTfFormaEscrituracao().setValue(
			// this.pEntity.getFormaEscrituracao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LivroEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}