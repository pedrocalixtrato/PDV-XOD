package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.TipoRequisicaoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.suprimentos.compra.TipoRequisicaoEntity;
import dc.model.business.suprimento.compra.TipoRequisicaoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.TipoRequisicaoFormView;

@Controller
@Scope("prototype")
public class TipoRequisicaoFormController extends
		CRUDFormController<TipoRequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoRequisicaoFormView subView;

	/**
	 * ENTITY
	 */

	private TipoRequisicaoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private TipoRequisicaoBusiness<TipoRequisicaoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public TipoRequisicaoFormController() {
		// TODO Auto-generated constructor stub
	}

	public TipoRequisicaoBusiness<TipoRequisicaoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Tipo de requisição";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public TipoRequisicaoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new TipoRequisicaoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			TipoRequisicaoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setCodigo(this.subView.getTfCodigo().getValue());
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTaDescricao().getValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfCodigo().setValue(this.entity.getCodigo());
			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTaDescricao().setValue(this.entity.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new TipoRequisicaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new TipoRequisicaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

}