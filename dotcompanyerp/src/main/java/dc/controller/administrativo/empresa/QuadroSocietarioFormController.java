package dc.controller.administrativo.empresa;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.servicos.dao.administrativo.empresa.IQuadroSocietarioDAO;
import dc.visao.administrativo.empresa.QuadroSocietarioFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class QuadroSocietarioFormController extends CRUDFormController<QuadroSocietarioEntity> {

	private QuadroSocietarioFormView subView;

	@Autowired
	private IQuadroSocietarioDAO dao;

	private QuadroSocietarioEntity currentBean;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected void criarNovoBean() {
		try {
			currentBean = new QuadroSocietarioEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
	    }
	}

	@Override
	protected void initSubView() {
		try {
			subView = new QuadroSocietarioFormView(this);
			
			
			this.fieldGroup = new DCFieldGroup<>(QuadroSocietarioEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getDataRegistro(),"dataRegistro");
			fieldGroup.bind(this.subView.getTxtQuantidadeCotas(),"quantidadeCotas");
			
			
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			
			currentBean = this.dao.find((Integer) id);
			
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Quadro Societário";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public QuadroSocietarioEntity getModelBean() {
		return currentBean;
	}

}
