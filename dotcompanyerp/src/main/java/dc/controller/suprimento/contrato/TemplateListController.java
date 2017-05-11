package dc.controller.suprimento.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.contrato.TemplateEntity;
import dc.servicos.dao.suprimentos.contrato.ITemplateDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TemplateListController extends CRUDListController<TemplateEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITemplateDAO dao;

	@Autowired
	TemplateFormController templateFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super TemplateEntity> getEntityClass() {
		return TemplateEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Template";
	}

	@Override
	protected List<TemplateEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TemplateEntity> getFormController() {
		return templateFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TemplateEntity> pesquisaDefault() {
		return (List<TemplateEntity>) dao.getAll(getEntityClass());
	}

}