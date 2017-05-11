package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.financeiro.ITalonarioChequeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TalonarioChequeListController extends CRUDListController<TalonarioCheque> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITalonarioChequeDAO dao;

	@Autowired
	private TalonarioChequeFormController talonarioChequeFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "contaCaixa","talao", "statusTalao", "numero" };
	}

	@Override
	public Class<? super TalonarioCheque> getEntityClass() {
		return TalonarioCheque.class;
	}

	@Override
	protected String getTitulo() {
		return "Talonário Cheque";
	}

	@Override
	protected List<TalonarioCheque> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TalonarioCheque> getFormController() {
		return talonarioChequeFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected List<TalonarioCheque> pesquisaDefault() {
		return (List<TalonarioCheque>) dao.getAll(getEntityClass());
	}

}
