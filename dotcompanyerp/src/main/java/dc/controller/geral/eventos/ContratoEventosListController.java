package dc.controller.geral.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.model.dao.eventos.IContratoEventosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContratoEventosListController extends CRUDListController<ContratoEventosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IContratoEventosDAO dao;

	@Autowired
	private ContratoEventosFormController contratoEventosFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "curso","unidade","nomeCerimonial","dataContrato","dataPrimeiroEvento","quantidadeFormandos","anoFormatura" };
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
	public Class<? super ContratoEventosEntity> getEntityClass() {
		return ContratoEventosEntity.class;
	}

	@Override
	protected CRUDFormController<ContratoEventosEntity> getFormController() {
		return contratoEventosFormController;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}
	
	@Override
	protected List<ContratoEventosEntity> pesquisa(String valor) {
		try {
			
			List<ContratoEventosEntity> auxLista = this.dao.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<ContratoEventosEntity> pesquisaDefault() {
		try {
			
			@SuppressWarnings("unchecked")
			List<ContratoEventosEntity> auxLista = (List<ContratoEventosEntity>) this.dao.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}
