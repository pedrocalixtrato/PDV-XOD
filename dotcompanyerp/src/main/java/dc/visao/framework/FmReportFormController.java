package dc.visao.framework;

import java.io.Serializable;
import java.util.List;

import com.vaadin.ui.Component;

import dc.entidade.framework.FmReport;
import dc.visao.framework.geral.CRUDFormController;

public class FmReportFormController extends CRUDFormController<FmReport> {

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void criarNovoBean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initSubView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void actionSalvar() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNome() {
		// TODO Auto-generated method stub
		return "Relatório";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public FmReport getModelBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
