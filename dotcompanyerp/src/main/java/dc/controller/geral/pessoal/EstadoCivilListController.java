package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class EstadoCivilListController extends
		CRUDListController<EstadoCivilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EstadoCivilFormController estadoCivilFormController;

	/**
	 * CONSTRUTOR
	 */

	public EstadoCivilListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<EstadoCivilEntity> getFormController() {
		return estadoCivilFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super EstadoCivilEntity> getEntityClass() {
		return EstadoCivilEntity.class;
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
	protected List<EstadoCivilEntity> pesquisa(String valor) {
		try {
			List<EstadoCivilEntity> auxLista = (List<EstadoCivilEntity>) this.estadoCivilFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<EstadoCivilEntity> pesquisaDefault() {
		try {
			List<EstadoCivilEntity> auxLista = (List<EstadoCivilEntity>) this.estadoCivilFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}