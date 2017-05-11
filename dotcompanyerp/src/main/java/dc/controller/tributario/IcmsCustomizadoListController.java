package dc.controller.tributario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class IcmsCustomizadoListController extends
		CRUDListController<IcmsCustomizadoCabecalhoEntity> {
	
	private static final long serialVersionUID = 1L;


	@Autowired
	private IcmsCustomizadoFormController formController;
	
	public IcmsCustomizadoListController() {
	}

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "origemMercadoria" };
	}

	@Override
	protected String getTitulo() {
		return "ICMS Customizado";
	}

	@Override
	protected List<IcmsCustomizadoCabecalhoEntity> pesquisa(String valor) {
		try {
			List<IcmsCustomizadoCabecalhoEntity> auxLista = (List<IcmsCustomizadoCabecalhoEntity>) this.formController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<IcmsCustomizadoCabecalhoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super IcmsCustomizadoCabecalhoEntity> getEntityClass() {
		return IcmsCustomizadoCabecalhoEntity.class;
	}

	@Override
	protected List<IcmsCustomizadoCabecalhoEntity> pesquisaDefault() {
		try {
			@SuppressWarnings("unchecked")
			List<IcmsCustomizadoCabecalhoEntity> auxLista = (List<IcmsCustomizadoCabecalhoEntity>) this.formController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}