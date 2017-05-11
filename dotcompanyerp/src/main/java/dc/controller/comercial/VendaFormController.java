package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.folhapagamento.ausencia.VendedorListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.TransportadoraListController;
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.Venda;
import dc.entidade.comercial.VendaDetalhe;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.INotaFiscalTipoDAO;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.servicos.dao.comercial.IItemOrcamentoDAO;
import dc.servicos.dao.comercial.IOrcamentoDAO;
import dc.servicos.dao.comercial.IVendaDAO;
import dc.servicos.dao.comercial.IVendaDetalheDAO;
import dc.servicos.dao.folhapagamento.IVendedorDAO;
import dc.visao.comercial.VendaFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class VendaFormController extends CRUDFormController<Venda> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Venda currentBean;

	private VendaFormView subView;

	@Autowired
	private IVendaDAO dao;
	
	@Autowired
	private ITransportadoraDAO transportadoraDAO;

	@Autowired
	private IVendaDetalheDAO detalheDAO;

	@Autowired
	private IVendedorDAO vendedorDAO;

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private ICondicaoPagamentoDAO condicaoPagamentoDAO;

	@Autowired
	private INotaFiscalTipoDAO notaFiscalTipoDAO;
	

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IOrcamentoDAO orcamentoDAO;

	@Autowired
	private IItemOrcamentoDAO itemOrcamentoDAO;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

@Override
protected boolean validaSalvar() {
	 try {
        // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
        fieldGroup.commit();
        return true;
    } catch (FieldGroup.CommitException ce) {
        return false;
    }
}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new Venda();
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void initSubView() {
		try {
			subView = new VendaFormView(this);
			this.fieldGroup = new DCFieldGroup<>(Venda.class);
		
			
			//fieldGroup.bind(this.subView.getTxtResponsavel(), "responsavel");
			//fieldGroup.bind(this.subView.getTxtPlaca(), "placa");
			//fieldGroup.bind(this.subView.getCmbVenda(), "vendaCabecalho");
			
			fieldGroup.bind(this.subView.getCmbCliente(), "cliente");
			fieldGroup.bind(this.subView.getCmbCondicoesPagamento(), "condicoesPagamento");
			fieldGroup.bind(this.subView.getCmbTipoNotaFiscal(), "tipoNotaFiscal");
			
			 this.subView.getCmbOrcamento().configuraCombo(
					"observacao", OrcamentoListController.class, this.orcamentoDAO, this.getMainController());
			 this.subView.getCmbTipoNotaFiscal().configuraCombo(
						"nome", NotaFiscalTipoListController.class, this.notaFiscalTipoDAO, this.getMainController());
			 this.subView.getCmbVendedor().configuraCombo(
						"colaborador", VendedorListController.class, this.vendedorDAO, this.getMainController());
			 this.subView.getCmbCliente().configuraCombo(
						"pessoa", ClienteListController.class, this.clienteDAO, this.getMainController());
			 this.subView.getCmbTransportadora().configuraCombo(
						"observacao", TransportadoraListController.class, this.transportadoraDAO, this.getMainController());
			 this.subView.getCmbCondicoesPagamento().configuraCombo(
						"nome", CondicaoPagamentoListController.class, this.condicaoPagamentoDAO, this.getMainController());
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);
		fieldGroup.setItemDataSource(this.currentBean);
		List<VendaDetalhe> detalhes = detalheDAO.detalhesPorVenda(currentBean);
		if (detalhes != null) {
			subView.preencheSubForm(detalhes);
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
		return "Venda";
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

	public VendaDetalhe novoDetalhe() {
		VendaDetalhe detalhe = new VendaDetalhe();
		List<VendaDetalhe> detalhes = detalheDAO.detalhesPorVenda(currentBean);
		detalhe.setVenda(currentBean);
		detalhes.add(detalhe);
		currentBean.setDetalhes(detalhes);
		return detalhe;
	}

	/*public BeanItemContainer<VendedorEntity> carregarVendedores() {
		BeanItemContainer<VendedorEntity> container = new BeanItemContainer<>(
				VendedorEntity.class);
		for (VendedorEntity c : vendedorDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<ClienteEntity> carregarClientes() {
		BeanItemContainer<ClienteEntity> container = new BeanItemContainer<>(
				ClienteEntity.class);
		for (ClienteEntity c : clienteDAO.getAll()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<CondicaoPagamento> carregarCondicoesPagamento() {
		BeanItemContainer<CondicaoPagamento> container = new BeanItemContainer<>(
				CondicaoPagamento.class);
		for (CondicaoPagamento c : condicaoPagamentoDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<NotaFiscalTipo> carregarTipoNotaFiscal() {
		BeanItemContainer<NotaFiscalTipo> container = new BeanItemContainer<>(
				NotaFiscalTipo.class);
		for (NotaFiscalTipo c : notaFiscalTipoDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}*/

	/*public String formataMoeda(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(",", ".");
		return format;
	}

	public BeanItemContainer<Orcamento> carregarOrcamentos() {
		BeanItemContainer<Orcamento> container = new BeanItemContainer<>(
				Orcamento.class);
		for (Orcamento p : orcamentoDAO.getAll()) {
			container.addBean(p);
		}
		return container;
	}*/
	
	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public List<ItemOrcamento> carregarItensOrcamento(Orcamento orcamento) {
		return itemOrcamentoDAO.findByOrcamento(orcamento);
	}

	public void preencherDetalhes(List<VendaDetalhe> detalhes) {
		currentBean.setDetalhes(detalhes);
		for (VendaDetalhe detalhe : detalhes) {
			detalhe.setVenda(currentBean);
		}
	}

	@Override
	public Venda getModelBean() {
		return currentBean;
	}

}