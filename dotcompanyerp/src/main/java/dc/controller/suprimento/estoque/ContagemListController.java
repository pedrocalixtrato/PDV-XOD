package dc.controller.suprimento.estoque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContagemListController extends
		CRUDListController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContagemFormController contagemCabecalhoFormController;

	/**
	 * CONSTRUTOR
	 */

	public ContagemListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<ContagemCabecalhoEntity> getFormController() {
		return contagemCabecalhoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "dataContagem" };
	}

	@Override
	public Class<? super ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Contagem Estoque";
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
	protected List<ContagemCabecalhoEntity> pesquisa(String valor) {
		try {
			List<ContagemCabecalhoEntity> auxLista = (List<ContagemCabecalhoEntity>) this.contagemCabecalhoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContagemCabecalhoEntity>();
		}
	}

	@Override
	protected List<ContagemCabecalhoEntity> pesquisaDefault() {
		try {
			List<ContagemCabecalhoEntity> auxLista = (List<ContagemCabecalhoEntity>) this.contagemCabecalhoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContagemCabecalhoEntity>();
		}
	}

}