package dc.controller.geral.pessoal;

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
public class PessoaListController extends CRUDListController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PessoaFormController pessoaFormController;

	/**
	 * CONSTRUTOR
	 */

	public PessoaListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<PessoaEntity> getFormController() {
		return pessoaFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "tipo", "email", "site" };
	}

	@Override
	public Class<? super PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
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
	protected List<PessoaEntity> pesquisa(String valor) {
		try {
			List<PessoaEntity> auxLista = (List<PessoaEntity>) this.pessoaFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<PessoaEntity> pesquisaDefault() {
		try {
			List<PessoaEntity> auxLista = (List<PessoaEntity>) this.pessoaFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}