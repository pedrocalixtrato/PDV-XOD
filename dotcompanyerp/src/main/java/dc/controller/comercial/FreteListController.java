package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.comercial.Frete;
import dc.servicos.dao.comercial.IFreteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FreteListController extends CRUDListController<Frete> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFreteDAO dao;

	@Autowired
	FreteFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "responsavel", "vendaCabecalho", "transportadora" };
	}

	@Override
	protected String getTitulo() {
		return "Frete";
	}

	@Override
	protected CRUDFormController<Frete> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Frete> getEntityClass() {
		return Frete.class;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected List<Frete> pesquisa(String valor) {
		try {
			List<Frete> auxLista = this.dao.procuraNomeContendo(valor);
			

			return auxLista;
			
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<Frete> pesquisaDefault() {
		try {
			List<Frete> auxLista = this.dao.getAll();
			

			return auxLista;
			
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
