package dc.framework;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dc.entidade.framework.FmReport;


import dc.servicos.dao.framework.geral.FmReportDAO;
import dc.visao.framework.FmReportFormController;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

public class FmReportListController 
extends CRUDListController<FmReport> implements Serializable {

	private static final long serialVersionUID = -3824341653741522079L;

	@Autowired
	FmReportDAO dao;

	@Autowired
	FmReportFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "controllerClass" };
	}

	@Override
	public Class<? super FmReport> getEntityClass() {
		return FmReport.class;
	}

	@Override
	protected String getTitulo() {
		return "Relatório";
	}

	@Override
	protected List<FmReport> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<FmReport> getFormController() {
		return formController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaRelatorios";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected List<FmReport> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<FmReport>) dao.getAll(getEntityClass());
	}

}
