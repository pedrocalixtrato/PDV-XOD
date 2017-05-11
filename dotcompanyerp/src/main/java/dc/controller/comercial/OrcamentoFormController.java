package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.controller.folhapagamento.ausencia.VendedorListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.TransportadoraListController;
import dc.entidade.comercial.Frete;
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.servicos.dao.comercial.IFreteDAO;
import dc.servicos.dao.comercial.IItemOrcamentoDAO;
import dc.servicos.dao.comercial.IOrcamentoDAO;
import dc.servicos.dao.folhapagamento.IVendedorDAO;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.visao.comercial.OrcamentoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class OrcamentoFormController extends CRUDFormController<Orcamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Orcamento currentBean;

	private OrcamentoFormView subView;

	@Autowired
	private IOrcamentoDAO dao;

	@Autowired
	private ICondicaoPagamentoDAO condicaoPagamentoDAO;

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private ITransportadoraDAO transportadoraDAO;

	@Autowired
	private  IVendedorDAO vendedorDAO;

	@Autowired
	private IItemOrcamentoDAO itemOrcamentoDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IFreteDAO freteDAO;

	@Override
	public String getViewIdentifier() {
		return "orcamentoForm";
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
			currentBean = new Orcamento();
			
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void initSubView() {
		try {
			subView = new OrcamentoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(Orcamento.class);
		
			
			//fieldGroup.bind(this.subView.getTxtResponsavel(), "responsavel");
			fieldGroup.bind(this.subView.getCmbVendedor(), "vendedor");
			fieldGroup.bind(this.subView.getCmbCondicaoPagamento(), "condicaoPagamento");
			
			 this.subView.getCmbVendedor().configuraCombo(
						"colaborador", VendedorListController.class, this.vendedorDAO, this.getMainController());
			 this.subView.getCmbCliente().configuraCombo(
						"pessoa", ClienteListController.class, this.clienteDAO, this.getMainController());
			 this.subView.getCmbTransportadora().configuraCombo(
						"observacao", TransportadoraListController.class, this.transportadoraDAO, this.getMainController());
			 this.subView.getCmbCondicaoPagamento().configuraCombo(
						"nome", CondicaoPagamentoListController.class, this.condicaoPagamentoDAO, this.getMainController());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {

		
		try {
			this.currentBean = this.dao.find(id);
			
			List<ItemOrcamento> itens = itemOrcamentoDAO.findByOrcamento(currentBean);

			subView.preencheSubForm(itens);

			// Atribui a entidade carregada como origem de dados dos campos do formulario
            // no FieldGroup
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
		return "Orçamento de Venda";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			for (Serializable id : ids) {
				Orcamento orcamento = dao.find(id);
				List<ItemOrcamento> itens = itemOrcamentoDAO
						.findByOrcamento(orcamento);

				for (ItemOrcamento item : itens) {
					itemOrcamentoDAO.delete(item);
				}
			}

			dao.deleteAllByIds(ids);
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro("Problema ao remover!");
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		for (Serializable id : objetos) {
			Orcamento orc = (Orcamento) id;

			try {
				dao.delete(orc);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();

	}

	@Override
	public boolean isFullSized() {
		return true;
	}
	
public ItemOrcamento adicionarItem() {
		
	    ItemOrcamento item = new ItemOrcamento();
		currentBean.addItem(item);
		return item;
	}

	public void removerItem(List<ItemOrcamento> values) {
		for (ItemOrcamento value : values) {
			currentBean.removeItem(value);
		}
		
		mensagemRemovidoOK();

	}

	/*public ItemOrcamento adicionarItem() {
		ItemOrcamento item = new ItemOrcamento();
		List<ItemOrcamento> lista = itemOrcamentoDAO
				.findByOrcamento(currentBean);

		if (lista == null)
			lista = new ArrayList<ItemOrcamento>();

		currentBean.setItens(lista);
		currentBean.getItens().add(item);
		item.setOrcamento(currentBean);

		return item;
	}*/

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public BeanItemContainer<Frete> carregarFretes() {
		BeanItemContainer<Frete> container = new BeanItemContainer<>(
				Frete.class);

		for (Frete f : freteDAO.getAll()) {
			container.addBean(f);
		}

		return container;
	}

	public BigDecimal formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(","))
				.replaceAll(",", "").trim();

		return new BigDecimal(format);
	}

	public IProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	@Override
	public Orcamento getModelBean() {
		return currentBean;
	}

}