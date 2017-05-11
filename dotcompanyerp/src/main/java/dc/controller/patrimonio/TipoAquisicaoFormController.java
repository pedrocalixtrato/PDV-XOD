package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.servicos.dao.patrimonio.TipoAquisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.TipoAquisicaoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TipoAquisicaoFormController extends CRUDFormController<TipoAquisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoAquisicaoFormView subView;

	/** DAO'S */

	@Autowired
	private TipoAquisicaoDAO pDAO;

	/** ENTITIES */

	private TipoAquisicaoEntity pEntity;

	/** CONSTRUTOR */

	public TipoAquisicaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new TipoAquisicaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Tipo de aquisição";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String tipo = this.subView.getTfTipo().getValue();
			String nome = this.subView.getTfNome().getValue();
			String descricao = this.subView.getTfDescricao().getValue();

			this.pEntity.setTipo(tipo);
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
			this.pEntity = new TipoAquisicaoEntity();

			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
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
			this.pEntity = new TipoAquisicaoEntity();

			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new TipoAquisicaoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new TipoAquisicaoEntity();

			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
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
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	public void autocomplete(EmpresaEntity ent) {
		adicionarErroDeValidacao(this.subView.getTfNome(), "Não pode ficar em branco" + ent);
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_tipo_aquisicao_fc";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public TipoAquisicaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}