package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.TransportadoraListController;
import dc.entidade.comercial.Frete;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.servicos.dao.comercial.IFreteDAO;
import dc.servicos.dao.comercial.IVendaDAO;
import dc.visao.comercial.FreteFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FreteFormController extends CRUDFormController<Frete> {

	private static final long serialVersionUID = 1L;
	
	private Frete currentBean;

	private FreteFormView subView;

	@Autowired
	private IFreteDAO dao;

	@Autowired
	private ITransportadoraDAO transportadoraDAO;

	@Autowired
	private IVendaDAO vendaDAO;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		try {
			
			fieldGroup.commit();
			return true;
		}catch (FieldGroup.CommitException ce) {
	        return false;
	    }
	}

	@Override
	protected void criarNovoBean() {
		
		try {
			currentBean = new Frete();
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void initSubView() {
		
		try {
			subView = new FreteFormView(this);
			this.fieldGroup = new DCFieldGroup<>(Frete.class);
		
			
			fieldGroup.bind(this.subView.getTxtResponsavel(), "responsavel");
			fieldGroup.bind(this.subView.getTxtPlaca(), "placa");
			fieldGroup.bind(this.subView.getCmbVenda(), "vendaCabecalho");
			
			 this.subView.getCmbTransportadora().configuraCombo(
					"observacao", TransportadoraListController.class, this.transportadoraDAO, this.getMainController());
			 this.subView.getCmbVenda().configuraCombo(
						"numeroFatura", VendaListController.class, this.vendaDAO, this.getMainController());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = dao.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {

		try {

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());

		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Frete";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public BigDecimal formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return new BigDecimal(format);
	}

	@Override
	public Frete getModelBean() {
		return currentBean;
	}

}
