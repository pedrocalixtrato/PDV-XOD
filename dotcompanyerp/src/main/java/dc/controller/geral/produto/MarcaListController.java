package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.MarcaEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class MarcaListController extends CRUDListController<MarcaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MarcaFormController marcaFormController;

	/**
	 * CONSTRUTOR
	 */

	public MarcaListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<MarcaEntity> getFormController() {
		return marcaFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super MarcaEntity> getEntityClass() {
		return MarcaEntity.class;
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
	protected List<MarcaEntity> pesquisa(String valor) {
		try {
			List<MarcaEntity> auxLista = (List<MarcaEntity>) this.marcaFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<MarcaEntity> pesquisaDefault() {
		try {
			List<MarcaEntity> auxLista = (List<MarcaEntity>) this.marcaFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}