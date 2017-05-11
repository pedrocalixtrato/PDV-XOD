package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.PaisEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PaisListController extends CRUDListController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PaisFormController paisFormController;

	/**
	 * CONSTRUTOR
	 */

	public PaisListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<PaisEntity> getFormController() {
		return paisFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nomeIngles", "nomePtbr", "sigla2",
				"sigla3" };
	}

	@Override
	public Class<? super PaisEntity> getEntityClass() {
		return PaisEntity.class;
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
	protected List<PaisEntity> pesquisa(String valor) {
		try {
			List<PaisEntity> auxLista = (List<PaisEntity>) this.paisFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<PaisEntity> pesquisaDefault() {
		try {
			List<PaisEntity> auxLista = (List<PaisEntity>) this.paisFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * **************************************
	 */

}