package dc.controller.administrativo.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.servicos.dao.administrativo.empresa.ISocioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SocioListController extends CRUDListController<SocioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SocioFormController socioFormController;

	@Autowired
	private ISocioDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public SocioListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<SocioEntity> getFormController() {
		return socioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "quadroSocietario", "nome", "cpf", "logradouro", "complemento" };
	}

	@Override
	public Class<? super SocioEntity> getEntityClass() {
		return SocioEntity.class;
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
	protected List<SocioEntity> pesquisa(String valor) {
		try {
			List<SocioEntity> auxLista = (List<SocioEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<SocioEntity> pesquisaDefault() {
		try {
			List<SocioEntity> auxLista = (List<SocioEntity>) this.dao.getAll(getEntityClass());
			
			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}