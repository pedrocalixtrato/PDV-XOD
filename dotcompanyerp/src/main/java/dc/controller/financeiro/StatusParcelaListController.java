package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.StatusParcela;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class StatusParcelaListController extends CRUDListController<StatusParcela> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StatusParcelaFormController statusParcelaFormController;
	
	public StatusParcelaListController() {
	}

	@Override
	public String[] getColunas() {
		return new String[] { "situacao", "descricao", "procedimento" };
	}

	@Override
	public Class<? super StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<StatusParcela> pesquisa(String valor) {
		
		try {
			List<StatusParcela> auxLista = (List<StatusParcela>) this.statusParcelaFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected CRUDFormController<StatusParcela> getFormController() {
		return statusParcelaFormController;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<StatusParcela> pesquisaDefault() {
		try {
			List<StatusParcela> auxLista = (List<StatusParcela>) this.statusParcelaFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}
