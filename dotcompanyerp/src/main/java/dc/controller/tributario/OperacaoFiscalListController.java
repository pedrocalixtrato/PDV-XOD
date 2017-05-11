package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class OperacaoFiscalListController extends CRUDListController<OperacaoFiscalEntity> {

	@Autowired
	private IOperacaoFiscalDAO dao;

	@Autowired
	OperacaoFiscalFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "cfop", "descricao","descricaoNaNf","observacao" };
	}

	@Override
	protected String getTitulo() {
		return "Operação Fiscal";
	}

	@Override
	protected List<OperacaoFiscalEntity> pesquisa(String valor) {
		return new ArrayList<>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaOperacaoFiscal";
	}

	@Override
	protected CRUDFormController<OperacaoFiscalEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super OperacaoFiscalEntity> getEntityClass() {
		return OperacaoFiscalEntity.class;
	}

	@Override
	protected List<OperacaoFiscalEntity> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}