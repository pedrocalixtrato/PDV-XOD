package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ParametroOsEntity;
import dc.servicos.dao.ordemservico.IParametroOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ParametroOsListController extends CRUDListController<ParametroOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IParametroOsDAO dao; 
	
	@Autowired
	private ParametroOsFormController formController;
	

	@Override 
	public String[] getColunas() {
		return new String[] {"limparBdAut","vendedorAtendente","vendedorProduto","vendedorServico","valorPagoPeca","descontoGeral",
				"qtdDiasRevisao","qtdDiasPadrao","obsDefeitoPadrao","obsPadrao","obsPadraoOsSimples"};
	}

	@Override
	protected String getTitulo() {
		return "Parametro de O.S";
	}

	@Override
	protected List<ParametroOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaParametros";
	}

	@Override
	protected CRUDFormController<ParametroOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ParametroOsEntity> getEntityClass() {
		return ParametroOsEntity.class;
	}

	@Override
	protected List<ParametroOsEntity> pesquisaDefault() {
		return dao.getAll(ParametroOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
