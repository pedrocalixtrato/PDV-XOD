package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.comercial.Venda;
import dc.servicos.dao.comercial.IVendaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class VendaListController extends CRUDListController<Venda> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IVendaDAO dao;

	@Autowired
	private VendaFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "cliente", "vendedor" };
	}

	@Override
	protected String getTitulo() {
		return "Venda";
	}

	@Override
	protected CRUDFormController<Venda> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Venda> getEntityClass() {
		return Venda.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Venda";
	}

	@Override
	protected List<Venda> pesquisa(String valor) {
		return null;
	}

	@Override
	protected List<Venda> pesquisaDefault() {
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
