package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class CargoListController extends CRUDListController<CargoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CargoFormController cargoFormController;

	/**
	 * CONSTRUTOR
	 */

	public CargoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<CargoEntity> getFormController() {
		return cargoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao","salario","cbo1994" ,"cbo2002" };
	}

	@Override
	public Class<? super CargoEntity> getEntityClass() {
		return CargoEntity.class;
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
	protected List<CargoEntity> pesquisa(String valor) {
		try {
			List<CargoEntity> auxLista = (List<CargoEntity>) this.cargoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<CargoEntity> pesquisaDefault() {
		try {
			List<CargoEntity> auxLista = (List<CargoEntity>) this.cargoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}