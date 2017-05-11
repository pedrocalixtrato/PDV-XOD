package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.model.business.suprimento.compra.TipoPedidoBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.TipoPedidoFormView;

@Controller
@Scope("prototype")
public class TipoPedidoFormController extends
		CRUDFormController<TipoPedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoPedidoFormView subView;

	/**
	 * ENTITY
	 */

	private TipoPedidoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private TipoPedidoBusiness<TipoPedidoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public TipoPedidoFormController() {
		// TODO Auto-generated constructor stub
	}

	public TipoPedidoBusiness<TipoPedidoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Tipo de pedido";
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
	public TipoPedidoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new TipoPedidoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(TipoPedidoEntity.class);
		
     	    fieldGroup.bind(this.subView.getTfNome(), "nome");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			business.saveOrUpdate(entity);
			
			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			fieldGroup.setItemDataSource(this.entity);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			entity = new TipoPedidoEntity();
			fieldGroup.setItemDataSource(this.entity);
		}catch(Exception e) {
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
		for (Serializable id : ids) {
			TipoPedidoEntity pedido = (TipoPedidoEntity) id;

			try {
				business.delete(pedido);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

}