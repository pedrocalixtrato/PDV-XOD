package dc.controller.suprimento.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.contrato.TipoContratoEntity;
import dc.servicos.dao.suprimentos.contrato.ITipoContratoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoContratoListController extends
		CRUDListController<TipoContratoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoContratoDAO dao;

	@Autowired
	TipoContratoFormController tipoContratoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super TipoContratoEntity> getEntityClass() {
		return TipoContratoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Contrato";
	}

	@Override
	protected List<TipoContratoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TipoContratoEntity> getFormController() {
		return tipoContratoFormController;
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
	protected List<TipoContratoEntity> pesquisaDefault() {
		return (List<TipoContratoEntity>) dao.getAll(getEntityClass());
	}

}