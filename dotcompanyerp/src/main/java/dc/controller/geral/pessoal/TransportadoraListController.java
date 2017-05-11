package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TransportadoraListController extends
		CRUDListController<TransportadoraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ITransportadoraDAO dao;

	@Autowired
	TransportadoraFormController transportadoraFormController;

	@Override
	protected CRUDFormController<TransportadoraEntity> getFormController() {
		return transportadoraFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {"pessoa", "observacao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super TransportadoraEntity> getEntityClass() {
		return TransportadoraEntity.class;
	}

	@Override
	protected List<TransportadoraEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<TransportadoraEntity> pesquisaDefault() {
		return (List<TransportadoraEntity>) dao.getAll(getEntityClass());
	}

}