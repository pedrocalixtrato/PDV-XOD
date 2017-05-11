package dc.controller.geral.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PessoaEventosListController extends CRUDListController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PessoaEventosFormController pessoaEventosFormController;
	
	public PessoaEventosListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<PessoaEntity> getFormController() {
		// TODO Auto-generated method stub
		return pessoaEventosFormController;
	}

	@Override
	public String[] getColunas() {
		// TODO Auto-generated method stub
		return new String[] { "nome", "tipo", "email", "site" };
	}

	@Override
	public Class<? super PessoaEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return PessoaEntity.class;
	}

	@Override
	protected List<PessoaEntity> pesquisa(String valor) {
		// TODO Auto-generated method stub
		try {
			List<PessoaEntity> auxLista = (List<PessoaEntity>) this.pessoaEventosFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	protected List<PessoaEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		try {
			List<PessoaEntity> auxLista = (List<PessoaEntity>) this.pessoaEventosFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	protected String getTitulo() {
		// TODO Auto-generated method stub
		return super.getTitulo(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
