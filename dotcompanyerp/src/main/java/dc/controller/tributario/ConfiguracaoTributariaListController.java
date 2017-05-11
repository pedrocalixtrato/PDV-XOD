package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.ConfiguracaoTributariaEntity;
import dc.servicos.dao.tributario.IConfiguracaoTributariaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaListController extends CRUDListController<ConfiguracaoTributariaEntity> {

	@Autowired
	IConfiguracaoTributariaDAO dao;

	@Autowired
	ConfiguracaoTributariaFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "grupoTributario", "operacaoFiscal" };
	}

	@Override
	protected String getTitulo() {
		return "Configuração Tributária";
	}

	@Override
	protected List<ConfiguracaoTributariaEntity> pesquisa(String valor) {
		return null;
	}

	@Override
	public String getViewIdentifier() {
		return "listaConfiguracaoTributaria";
	}

	@Override
	protected CRUDFormController<ConfiguracaoTributariaEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ConfiguracaoTributariaEntity> getEntityClass() {
		return ConfiguracaoTributariaEntity.class;
	}

	@Override
	protected List<ConfiguracaoTributariaEntity> pesquisaDefault() {

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
