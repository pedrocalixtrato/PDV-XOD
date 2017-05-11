package dc.controller.geral.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.servicos.dao.geral.eventos.CerimonialEventosDAO;
import dc.servicos.dao.geral.eventos.ICerimonialEventosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class CerimonialEventosListController extends CRUDListController<CerimonialEventosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CerimonialEventosFormController cerimonialEventosFormController;
	
	@Autowired
	private ICerimonialEventosDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public CerimonialEventosListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<CerimonialEventosEntity> getFormController() {
		return cerimonialEventosFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "cnpj", "endereco", "email","telefone","celular","contato" };
	}

	@Override
	public Class<? super CerimonialEventosEntity> getEntityClass() {
		return CerimonialEventosEntity.class;
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
	protected List<CerimonialEventosEntity> pesquisa(String valor) {
		try {
			//List<CerimonialEventosEntity> auxLista = (List<CerimonialEventosEntity>) this.cerimonialEventosFormController
				//	.getBusiness().fullTextSearch(valor);
			
			List<CerimonialEventosEntity> auxLista = this.dao.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<CerimonialEventosEntity> pesquisaDefault() {
		try {
			//List<CerimonialEventosEntity> auxLista = (List<CerimonialEventosEntity>) this.cerimonialEventosFormController
					//.getBusiness().getAll(getEntityClass());
			
			List<CerimonialEventosEntity> auxLista = this.dao.getAll();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}