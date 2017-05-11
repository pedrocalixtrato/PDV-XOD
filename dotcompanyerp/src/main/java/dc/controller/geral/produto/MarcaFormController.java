package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.MarcaEntity;
import dc.model.business.geral.produto.MarcaBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.MarcaFormView;

@Controller
@Scope("prototype")
public class MarcaFormController extends CRUDFormController<MarcaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarcaFormView subView;

	/**
	 * ENTITY
	 */
	private MarcaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private MarcaBusiness<MarcaEntity> business;

    /**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public MarcaFormController() {
		// TODO Auto-generated constructor stub
	}

	public MarcaBusiness<MarcaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Marca";
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
	public MarcaEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
            this.subView = new MarcaFormView(this);

            // Configura o mapeamento Model-View usando o mecanismo de DataBinding do Vaadin 7 e uma classe
            // especial DCFieldGroup que extende BeanFieldGroup.
            // Aqui associamos o campo a uma propriedade da entidade. As regras de validação
            // (requerido, etc.) são buscadas direto das anotações da entitdade.
            this.fieldGroup = new DCFieldGroup<>(MarcaEntity.class);
            fieldGroup.bind(this.subView.getTfNome(),"nome");
            fieldGroup.bind(this.subView.getTfDescricao(),"descricao");

        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
            // Commit tenta transferir os dados do View para o Model, levando em conta os critérios de validação
            fieldGroup.commit();

			return true;
		} catch (FieldGroup.CommitException ce) {
			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
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

			// Atribui a entidade carregada como origem de dados dos campos do formulario
            // no FieldGroup
            fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {

			this.entity = new MarcaEntity();

            // Atribui a entidade nova como origem de dados dos campos do formulario
            // no FieldGroup
            fieldGroup.setItemDataSource(this.entity);

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