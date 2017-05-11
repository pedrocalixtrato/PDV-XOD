package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.NcmEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NcmListController extends CRUDListController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private NcmFormController ncmFormController;

	/**
	 * CONSTRUTOR
	 */

	public NcmListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<NcmEntity> getFormController() {
		return ncmFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao","observacao" };
	}

	@Override
	public Class<? super NcmEntity> getEntityClass() {
		return NcmEntity.class;
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
	protected List<NcmEntity> pesquisa(String valor) {
		try {
			List<NcmEntity> auxLista = (List<NcmEntity>) this.ncmFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<NcmEntity> pesquisaDefault() {
		try {
			List<NcmEntity> auxLista = (List<NcmEntity>) this.ncmFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}