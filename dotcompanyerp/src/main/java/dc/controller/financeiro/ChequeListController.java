package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.Cheque;
import dc.servicos.dao.financeiro.IChequeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão da classe principal que é
 *         crudListController Temos alguns métodos que pegamos, temos a
 *         configuração do Título da Tela; O Método do Button pesquisar, pegando
 *         um valor. e também ele pega algumas informações da classe
 *         FormController
 * 
 */

@Controller
@Scope("prototype")
public class ChequeListController extends CRUDListController<Cheque> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IChequeDAO dao;

	@Autowired
	private ChequeFormController chequeFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "idTalonarioCheque","statusCheque", "numero", "dataStatus" };
	}

	@Override
	public Class<? super Cheque> getEntityClass() {
		return Cheque.class;
	}

	@Override
	protected String getTitulo() {
		return "Cheque";
	}

	@Override
	protected List<Cheque> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Cheque> getFormController() {
		return chequeFormController;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected List<Cheque> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Cheque>) dao.getAll(getEntityClass());
	}

}
