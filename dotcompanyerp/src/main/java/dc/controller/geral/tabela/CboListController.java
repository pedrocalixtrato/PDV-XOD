package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.tabela.CboEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CboListController extends CRUDListController<CboEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CboFormController pController;
	
	//@Autowired
	//private CboDAO cboDAO;
	
	public CboListController() {
		
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo","nome", "observacao" };
	}

	@Override
	public Class<? super CboEntity> getEntityClass() {
		return CboEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "CBO";
	}

	@Override
	protected List<CboEntity> pesquisa(String valor) {
		try {
			List<CboEntity> auxLista = (List<CboEntity>) this.pController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected CRUDFormController<CboEntity> getFormController() {
		return pController;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CboEntity> pesquisaDefault() {
		try {
			List<CboEntity> auxLista = (List<CboEntity>) this.pController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}