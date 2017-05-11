package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AlmoxarifadoListController extends
		CRUDListController<AlmoxarifadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AlmoxarifadoFormController almoxarifadoFormController;

	/**
	 * CONSTRUTOR
	 */

	public AlmoxarifadoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<AlmoxarifadoEntity> getFormController() {
		return almoxarifadoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	public Class<? super AlmoxarifadoEntity> getEntityClass() {
		return AlmoxarifadoEntity.class;
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
		return true;
	}

	@Override
	protected List<AlmoxarifadoEntity> pesquisa(String valor) {
		try {
			List<AlmoxarifadoEntity> auxLista = (List<AlmoxarifadoEntity>) this.almoxarifadoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<AlmoxarifadoEntity> pesquisaDefault() {
		try {
			List<AlmoxarifadoEntity> auxLista = (List<AlmoxarifadoEntity>) this.almoxarifadoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}