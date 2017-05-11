package dc.controller.geral.outro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SindicatoListController extends
		CRUDListController<SindicatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SindicatoFormController sindicatoFormController;

	/**
	 * CONSTRUTOR
	 */

	public SindicatoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<SindicatoEntity> getFormController() {
		return sindicatoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome","codigoBanco","codigoAgencia","contaBanco","codigoCedente",
				"logradouro","numero","bairro","fone1","fone2","email","tipoSindicato","dataBase","cnpj"};
	}

	@Override
	public Class<? super SindicatoEntity> getEntityClass() {
		return SindicatoEntity.class;
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
	protected List<SindicatoEntity> pesquisa(String valor) {
		try {
			List<SindicatoEntity> auxLista = (List<SindicatoEntity>) this.sindicatoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<SindicatoEntity> pesquisaDefault() {
		try {
			List<SindicatoEntity> auxLista = (List<SindicatoEntity>) this.sindicatoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}