package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SubGrupoOsListController extends
		CRUDListController<SubGrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SubGrupoOsFormController formController;

	/**
	 * CONSTRUTOR
	 */
	public SubGrupoOsListController() {
	}

	@Override
	protected CRUDFormController<SubGrupoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "grupo" };
	}

	@Override
	protected String getTitulo() {
		return "SubGrupo";
	}

	@Override
	public Class<? super SubGrupoOsEntity> getEntityClass() {
		return SubGrupoOsEntity.class;
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
	protected List<SubGrupoOsEntity> pesquisa(String valor) {
		try {
			List<SubGrupoOsEntity> auxLista = (List<SubGrupoOsEntity>) this.formController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<SubGrupoOsEntity> pesquisaDefault() {
		try {
			List<SubGrupoOsEntity> auxLista = (List<SubGrupoOsEntity>) this.formController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}