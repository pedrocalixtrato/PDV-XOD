package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.CarroEntity;
import dc.model.dao.ordemservico.ICarroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CarroListController extends CRUDListController<CarroEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICarroDAO dao;
	
	@Autowired
	private CarroFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"cliente","placa","marca","modelo","cor","combustivel","ano","motorizacao","chassi","observacao"};
	}

	@Override
	protected String getTitulo() {
		return "Carro";
	}

	@Override
	protected List<CarroEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<CarroEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super CarroEntity> getEntityClass() {
		return CarroEntity.class;
	}

	@Override
	protected List<CarroEntity> pesquisaDefault() {
		return dao.getAll(CarroEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
