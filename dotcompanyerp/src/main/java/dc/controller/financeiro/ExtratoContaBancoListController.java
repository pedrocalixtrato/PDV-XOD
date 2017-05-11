package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.servicos.dao.financeiro.IExtratoContaBancoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ExtratoContaBancoListController extends CRUDListController<ExtratoContaBancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IExtratoContaBancoDAO dao;
	
	@Autowired
	private ExtratoContaBancoFormController controller;
	
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<ExtratoContaBancoEntity> getFormController() {
		return controller;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "mes","ano","dataMovimento","valor","observacoes" };
	}

	@Override
	public Class<? super ExtratoContaBancoEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return ExtratoContaBancoEntity.class;
	}

	@Override
	protected List<ExtratoContaBancoEntity> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return dao.fullTextSearch(valor);
	}

	@Override
	protected List<ExtratoContaBancoEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		return dao.getAll(ExtratoContaBancoEntity.class);
	}

	@Override
	protected String getTitulo() {
		// TODO Auto-generated method stub
		return "Extrato Conta Banco";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
