package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.UfEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UfListController extends CRUDListController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UfFormController ufFormController;

	/**
	 * CONSTRUTOR
	 */

	public UfListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<UfEntity> getFormController() {
		return ufFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"pais", "nome", "sigla" ,"codigoIbge"};
	}

	@Override
	public Class<? super UfEntity> getEntityClass() {
		return UfEntity.class;
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
	protected List<UfEntity> pesquisa(String valor) {
		try {
			List<UfEntity> auxLista = (List<UfEntity>) this.ufFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<UfEntity> pesquisaDefault() {
		try {
			List<UfEntity> auxLista = (List<UfEntity>) this.ufFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}