package dc.controller.administrativo.seguranca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PapelListController extends CRUDListController<PapelEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PapelFormController papelFormController;

	/**
	 * CONSTRUTOR
	 */

	public PapelListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<PapelEntity> getFormController() {
		return papelFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome","descricao" };
	}

	@Override
	public Class<? super PapelEntity> getEntityClass() {
		return PapelEntity.class;
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
	protected List<PapelEntity> pesquisa(String valor) {
		try {
			List<PapelEntity> auxLista = (List<PapelEntity>) this.papelFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<PapelEntity> pesquisaDefault() {
		try {
			List<PapelEntity> auxLista = (List<PapelEntity>) this.papelFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}