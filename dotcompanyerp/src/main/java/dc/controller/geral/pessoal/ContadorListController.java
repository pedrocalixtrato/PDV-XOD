package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.model.dao.geral.pessoal.IContadorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContadorListController extends CRUDListController<ContadorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IContadorDAO dao;

	@Autowired
	ContadorFormController contadorFormController;

	@Override
	protected CRUDFormController<ContadorEntity> getFormController() {
		return contadorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"nome", "logradouro", "bairro","complemento","numero","uf","email","fone","fax",
				"cpf","cnpj","cep"};
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super ContadorEntity> getEntityClass() {
		return ContadorEntity.class;
	}

	@Override
	protected List<ContadorEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<ContadorEntity> pesquisaDefault() {
		return (List<ContadorEntity>) dao.getAll(getEntityClass());
	}

}