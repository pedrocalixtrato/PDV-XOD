package dc.controller.administrativo.seguranca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UsuarioListController extends CRUDListController<UsuarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioFormController usuarioFormController;

	/**
	 * CONSTRUTOR
	 */

	public UsuarioListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<UsuarioEntity> getFormController() {
		return usuarioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"colaborador","login","dataCadastro" };
	}

	@Override
	public Class<? super UsuarioEntity> getEntityClass() {
		return UsuarioEntity.class;
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
	protected List<UsuarioEntity> pesquisa(String valor) {
		try {
			List<UsuarioEntity> auxLista = (List<UsuarioEntity>) this.usuarioFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<UsuarioEntity> pesquisaDefault() {
		try {
			List<UsuarioEntity> auxLista = (List<UsuarioEntity>) this.usuarioFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}