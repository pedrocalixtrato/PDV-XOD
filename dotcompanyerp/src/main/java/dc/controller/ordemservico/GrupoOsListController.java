package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class GrupoOsListController extends CRUDListController<GrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GrupoOsFormController formController;

	/**
	 * CONSTRUTOR
	 */
	public GrupoOsListController() {
	}

	@Override
	protected CRUDFormController<GrupoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	protected String getTitulo() {
		return "Grupo";
	}

	@Override
	public Class<? super GrupoOsEntity> getEntityClass() {
		return GrupoOsEntity.class;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<GrupoOsEntity> pesquisa(String valor) {
		try {
			List<GrupoOsEntity> auxLista = (List<GrupoOsEntity>) this.formController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<GrupoOsEntity> pesquisaDefault() {
		try {
			List<GrupoOsEntity> auxLista = (List<GrupoOsEntity>) this.formController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}