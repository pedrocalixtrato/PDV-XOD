package dc.controller.geral.outro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.outro.ConvenioEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ConvenioListController extends CRUDListController<ConvenioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ConvenioFormController convenioFormController;

	/**
	 * CONSTRUTOR
	 */

	public ConvenioListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<ConvenioEntity> getFormController() {
		return convenioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "pessoa","nome", "logradouro","numero", "bairro","dataVencimento","dataCadastro","email","telefone","contato",
				"cnpj","cep","site","descricao" };
	}

	@Override
	public Class<? super ConvenioEntity> getEntityClass() {
		return ConvenioEntity.class;
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
	protected List<ConvenioEntity> pesquisa(String valor) {
		try {
			List<ConvenioEntity> auxLista = (List<ConvenioEntity>) this.convenioFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<ConvenioEntity> pesquisaDefault() {
		try {
			List<ConvenioEntity> auxLista = (List<ConvenioEntity>) this.convenioFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}