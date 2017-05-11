package dc.controller.administrativo.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.servicos.dao.administrativo.empresa.IEmpresaCnaeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class EmpresaCnaeListController extends
		CRUDListController<EmpresaCnaeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaCnaeFormController empresaCnaeFormController;

	@Autowired
	private IEmpresaCnaeDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public EmpresaCnaeListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<EmpresaCnaeEntity> getFormController() {
		return empresaCnaeFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "cnae", "principal", "ramoAtividade",
				"objetoSocial" };
	}

	@Override
	public Class<? super EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
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
	protected List<EmpresaCnaeEntity> pesquisa(String valor) {
		try {
			List<EmpresaCnaeEntity> auxLista = (List<EmpresaCnaeEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<EmpresaCnaeEntity> pesquisaDefault() {
		try {
			List<EmpresaCnaeEntity> auxLista = (List<EmpresaCnaeEntity>) this.dao
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}