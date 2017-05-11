package dc.controller.administrativo.seguranca;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.model.business.administrativo.seguranca.PapelBusiness;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.sistema.IPapelDAO;
import dc.visao.administrativo.seguranca.PapelFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PapelFormController extends CRUDFormController<PapelEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PapelFormView subView;

	/**
	 * ENTITY
	 */

	private PapelEntity entity = new PapelEntity();

	/**
	 * BUSINESS
	 */

	@Autowired
	private PapelBusiness<PapelEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IPapelDAO papelDAO;

	@Autowired
	private IFmMenuDAO menuDAO;

	/**
	 * CONSTRUTOR
	 */

	public PapelFormController() {
		// TODO Auto-generated constructor stub
	}

	public PapelBusiness<PapelEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Papel";
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
	public PapelEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		
		try {
			this.subView = new PapelFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PapelEntity.class);
            fieldGroup.bind(this.subView.getTxtNome(),"nome");
            fieldGroup.bind(this.subView.getDescricao(),"descricao");
            
            subView.populaModulos(papelDAO.getAll(FmModulo.class));

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		//boolean retornoValidacao = true;

		try {
			//subView.getBinder().commit();
			 fieldGroup.commit();
	            

			/*PapelEntity papel = subView.getBinder().getItemDataSource().getBean();

			List<PapelMenu> fs = subView.getPapelMenus();

			papel.setPapelMenuList(fs);

			if (papel.getNome() == null || papel.getNome().isEmpty()) {
				adicionarErroDeValidacao(subView.getTxtNome(),
						"Não pode ficar em Branco!");

				retornoValidacao = false;
			}

			if (papel.getPapelMenuList() == null
					|| papel.getPapelMenuList().isEmpty()) {
				adicionarErroDeValidacao(subView.getTreeTable(),
						"Ao menos um menu deve ser associado ao papel");

				retornoValidacao = false;
			}

			this.entity = papel;*/
			
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}

		//return retornoValidacao;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			this.business.saveOrUpdate(entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro("Problemas ao salvar registro");
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			//this.subView.getBinder().setItemDataSource(this.entity);
			
			//List<PapelMenu> itens = papelDAO.getPapelMenusOrdered(entity);
			//subView.populaPapelMenu(itens);
			
			this.subView.populaPapelMenu(this.papelDAO.getPapelMenusOrdered(this.entity));
			//subView.populaPapelMenu(entity.getPapelMenuList());
			
			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PapelEntity();
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
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
			this.papelDAO.deleteAll(objetos);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */

	public PapelEntity getCurrentBean() {
		return entity;
	}

	public void loadModules(FmModulo modulo) {
		List<FmMenu> m = menuDAO.getAllMenusByModuleIdGrouped(modulo.getId());
		subView.buildTree(m, modulo);
	}

	public List<FmMenu> getFmMenus(FmModulo fm) {
		return menuDAO.getAllMenusByModuleId(fm.getId());
	}

}