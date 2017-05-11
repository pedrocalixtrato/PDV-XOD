package dc.controller.administrativo.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.servicos.dao.administrativo.empresa.IQuadroSocietarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class QuadroSocietarioListController extends
		CRUDListController<QuadroSocietarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private QuadroSocietarioFormController quadroSocietarioFormController;

	@Autowired
	private IQuadroSocietarioDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public QuadroSocietarioListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<QuadroSocietarioEntity> getFormController() {
		return quadroSocietarioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "dataRegistro", "capitalSocial", "valorQuota",
				"quantidadeCotas" };
	}

	@Override
	public Class<? super QuadroSocietarioEntity> getEntityClass() {
		return QuadroSocietarioEntity.class;
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
	protected List<QuadroSocietarioEntity> pesquisa(String valor) {
		try {
			List<QuadroSocietarioEntity> auxLista = (List<QuadroSocietarioEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<QuadroSocietarioEntity> pesquisaDefault() {
		try {
			List<QuadroSocietarioEntity> auxLista = (List<QuadroSocietarioEntity>) this.dao
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}