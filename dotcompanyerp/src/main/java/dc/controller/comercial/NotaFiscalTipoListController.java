package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class NotaFiscalTipoListController extends CRUDListController<NotaFiscalTipo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private NotaFiscalTipoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "serie", "nome", "descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Nota Fiscal Tipo";
	}

	@Override
	protected CRUDFormController<NotaFiscalTipo> getFormController() {
		return formController;
	}

	@Override
	public Class<? super NotaFiscalTipo> getEntityClass() {
		return NotaFiscalTipo.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Nota Fiscal Tipo";
	}

	@Override
	protected List<NotaFiscalTipo> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<NotaFiscalTipo> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
