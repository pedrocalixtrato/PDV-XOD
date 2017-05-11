package dc.controller.sistema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.framework.SeguimentoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SeguimentoListController extends
		CRUDListController<SeguimentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SeguimentoFormController seguimentoFormController;

	/**
	 * CONSTRUTOR
	 */

	public SeguimentoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<SeguimentoEntity> getFormController() {
		return seguimentoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super SeguimentoEntity> getEntityClass() {
		return SeguimentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Seguimento";
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
	protected List<SeguimentoEntity> pesquisa(String valor) {
		try {
			List<SeguimentoEntity> auxLista = (List<SeguimentoEntity>) this.seguimentoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<SeguimentoEntity> pesquisaDefault() {
		try {
			List<SeguimentoEntity> auxLista = (List<SeguimentoEntity>) this.seguimentoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}