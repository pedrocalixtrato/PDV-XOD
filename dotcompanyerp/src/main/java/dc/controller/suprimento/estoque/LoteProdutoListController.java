package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class LoteProdutoListController extends
		CRUDListController<LoteProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoteProdutoFormController loteProdutoFormController;

	/**
	 * CONSTRUTOR
	 */

	public LoteProdutoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<LoteProdutoEntity> getFormController() {
		return loteProdutoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"codigo", "nome" };
	}

	@Override
	public Class<? super LoteProdutoEntity> getEntityClass() {
		return LoteProdutoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected List<LoteProdutoEntity> pesquisa(String valor) {
		try {
			List<LoteProdutoEntity> auxLista = (List<LoteProdutoEntity>) this.loteProdutoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<LoteProdutoEntity> pesquisaDefault() {
		try {
			List<LoteProdutoEntity> auxLista = (List<LoteProdutoEntity>) this.loteProdutoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		
		
		
	}

}