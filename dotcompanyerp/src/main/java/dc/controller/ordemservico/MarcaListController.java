package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.MarcaOsEntity;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component("ordemservicoMarcaListController")
@Scope("prototype")
public class MarcaListController extends CRUDListController<MarcaOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMarcaOsDAO dao;

	@Autowired
	MarcaOsFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	protected String getTitulo() {
		return "Marca";
	}

	@Override
	protected List<MarcaOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaModelo";
	}

	@Override
	protected CRUDFormController<MarcaOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super MarcaOsEntity> getEntityClass() {
		return MarcaOsEntity.class;
	}

	@Override
	protected List<MarcaOsEntity> pesquisaDefault() {
		return dao.getAll(MarcaOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}