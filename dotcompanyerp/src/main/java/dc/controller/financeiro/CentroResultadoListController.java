package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.CentroResultado;
import dc.servicos.dao.financeiro.ICentroResultadoDAO;
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
public class CentroResultadoListController extends CRUDListController<CentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICentroResultadoDAO dao;

	@Autowired
	private CentroResultadoFormController centroresultadoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "planoCentroResultado", "classificacao", "descricao", "sofreRateio" };
	}

	@Override
	public Class<? super CentroResultado> getEntityClass() {
		return CentroResultado.class;
	}

	@Override
	protected String getTitulo() {
		return "Centro Resultado";
	}

	@Override
	protected List<CentroResultado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CentroResultado> getFormController() {
		return centroresultadoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<CentroResultado> pesquisaDefault() {
		return (List<CentroResultado>) dao.getAll(getEntityClass());
	}

}