package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SubGrupoListController extends CRUDListController<SubGrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubGrupoFormController subGrupoFormController;

	/**
	 * CONSTRUTOR
	 */

	public SubGrupoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<SubGrupoEntity> getFormController() {
		return subGrupoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"grupo", "nome", "descricao" };
	}

	@Override
	public Class<? super SubGrupoEntity> getEntityClass() {
		return SubGrupoEntity.class;
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
	protected List<SubGrupoEntity> pesquisa(String valor) {
		try {
			List<SubGrupoEntity> auxLista = (List<SubGrupoEntity>) this.subGrupoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<SubGrupoEntity> pesquisaDefault() {
		try {
			List<SubGrupoEntity> auxLista = (List<SubGrupoEntity>) this.subGrupoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}