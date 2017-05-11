package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.CepEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class CepListController extends CRUDListController<CepEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CepFormController cepFormController;

	/**
	 * CONSTRUTOR
	 */

	public CepListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<CepEntity> getFormController() {
		return cepFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "cep", "logradouro" ,"complemento","bairro","municipio","uf","codigoIbgeMunicipio"};
	}

	@Override
	public Class<? super CepEntity> getEntityClass() {
		return CepEntity.class;
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
	protected List<CepEntity> pesquisa(String valor) {
		try {
			List<CepEntity> auxLista = (List<CepEntity>) this.cepFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<CepEntity> pesquisaDefault() {
		try {
			List<CepEntity> auxLista = (List<CepEntity>) this.cepFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}