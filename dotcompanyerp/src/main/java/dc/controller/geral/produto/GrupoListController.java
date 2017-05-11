package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class GrupoListController extends CRUDListController<GrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private GrupoFormController grupoFormController;

	/**
	 * CONSTRUTOR
	 */

	public GrupoListController() {
	}

	@Override
	protected CRUDFormController<GrupoEntity> getFormController() {
		return grupoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super GrupoEntity> getEntityClass() {
		return GrupoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<GrupoEntity> pesquisa(String valor) {
		try {
			List<GrupoEntity> auxLista = (List<GrupoEntity>) this.grupoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<GrupoEntity> pesquisaDefault() {
		try {
			List<GrupoEntity> auxLista = (List<GrupoEntity>) this.grupoFormController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}