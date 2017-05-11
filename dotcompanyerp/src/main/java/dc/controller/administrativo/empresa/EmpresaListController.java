package dc.controller.administrativo.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.servicos.dao.administrativo.empresa.IEmpresaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class EmpresaListController extends CRUDListController<EmpresaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEmpresaDAO dao;

	@Autowired
	private EmpresaFormController empresaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Override
	public Class<? super EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Empresa";
	}

	@Override
	protected List<EmpresaEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EmpresaEntity> getFormController() {
		return empresaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<EmpresaEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		List<EmpresaEntity> auxLista = (List<EmpresaEntity>) this.dao
				.getAll(getEntityClass());

		return (List<EmpresaEntity>) this.dao.getAll(getEntityClass());
	}
}