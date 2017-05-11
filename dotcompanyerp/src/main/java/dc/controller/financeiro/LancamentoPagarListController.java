package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.servicos.dao.financeiro.ILancamentoPagarDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class LancamentoPagarListController extends CRUDListController<LancamentoPagarEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ILancamentoPagarDAO dao;

	@Autowired
	private LancamentoPagarFormController lancamentoPagarFormController;

	@Override
	public String[] getColunas() {
		
		String atributos[] = new String[] { "fornecedor", "documentoOrigem","dataLancamento","pagamentoCompartilhado","valorTotal","valorAPagar" ,
				"quantidadeParcela","numeroDocumento","primeiroVencimento" };
		
						
		return atributos;
		
		
	}

	@Override
	public Class<? super LancamentoPagarEntity> getEntityClass() {
		return LancamentoPagarEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento à Pagar";
	}

	@Override
	protected List<LancamentoPagarEntity> pesquisa(String valor) {
		try {
			List<LancamentoPagarEntity> auxLista = (List<LancamentoPagarEntity>) this.lancamentoPagarFormController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		
		//return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<LancamentoPagarEntity> getFormController() {
		return lancamentoPagarFormController;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected List<LancamentoPagarEntity> pesquisaDefault() {
		try {
			List<LancamentoPagarEntity> auxLista = (List<LancamentoPagarEntity>) this.lancamentoPagarFormController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		
		//return (List<LancamentoPagarEntity>) dao.getAll(getEntityClass());
		
	}
	

}