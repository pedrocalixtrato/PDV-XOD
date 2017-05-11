package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.MunicipioEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class MunicipioListController extends
		CRUDListController<MunicipioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioFormController municipioFormController;

	/**
	 * CONSTRUTOR
	 */

	public MunicipioListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<MunicipioEntity> getFormController() {
		return municipioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome","uf", "codigoIbge", "codigoReceitaFederal","codigoEstadual" };
	}

	@Override
	public Class<? super MunicipioEntity> getEntityClass() {
		return MunicipioEntity.class;
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
	protected List<MunicipioEntity> pesquisa(String valor) {
		try {
			List<MunicipioEntity> auxLista = (List<MunicipioEntity>) this.municipioFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<MunicipioEntity> pesquisaDefault() {
		try {
			List<MunicipioEntity> auxLista = (List<MunicipioEntity>) this.municipioFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}