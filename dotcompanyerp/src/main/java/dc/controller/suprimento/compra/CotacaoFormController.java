package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.CotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.compra.CotacaoCompraBusiness;
import dc.servicos.dao.suprimentos.compra.IRequisicaoDetalheDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.CotacaoFormView;

@Controller
@Scope("prototype")
public class CotacaoFormController extends CRUDFormController<CotacaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CotacaoFormView subView;

	//@Autowired
	//private ICotacaoDAO cotacaoDao;

	@Autowired
	private IFornecedorDAO fornecedorDao;

	@Autowired
	private IRequisicaoDetalheDAO requisicaoDetalheDao;

	private CotacaoCompraEntity currentBean;
	
	private RequisicaoCompraDetalheEntity  compraRequisicaoDetalhe;
	
	private CotacaoDetalheEntity compraCotacaoDetalhe;
	
	/**
	 * BUSINESS
	 */
	@Autowired
	private CotacaoCompraBusiness<CotacaoCompraEntity> business;
	
	public CotacaoCompraBusiness<CotacaoCompraEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Cotação de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			business.saveOrUpdate(currentBean);
			
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			this.currentBean = this.business.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
			
			subView.fillCompraFornecedorCotacoesSubForm(currentBean.getCompraFornecedorCotacaos());
			subView.fillReqCotacaoDetalhesSubForm(currentBean.getCompraReqCotacaoDetalhes());
			
			//List<ReqCotacaoDetalheEntity> pedido = requisicaoDetalheDao.findByRequisicao(currentBean);
			//subView.fillReqCotacaoDetalhesSubForm(pedido);
			
			//List<FornecedorCotacaoEntity> fornecedor = fornecedorDao.findyByFor(currentBean);
			//subView.fillCompraFornecedorCotacoesSubForm(fornecedor);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new CotacaoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(CotacaoCompraEntity.class);
		
     	    fieldGroup.bind(this.subView.getCalDataCotacao(), "dataCotacao");
     	    fieldGroup.bind(this.subView.getTxtDescricao(), "descricao");
     	   
     	   //subView.getCmbSituacao().setValue("A");
     	   salvarItem();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvarItem() {
		try {
			if ((compraRequisicaoDetalhe.getQuantidade().subtract(compraRequisicaoDetalhe.getQuantidadeCotada())).compareTo(compraCotacaoDetalhe.getQuantidade()) == -1) {
				mensagemErro("A quantidade informada excede a quantidade disponível para cotação!");
			} else {
				compraCotacaoDetalhe.setProduto(compraRequisicaoDetalhe.getProduto());

				compraRequisicaoDetalhe.setQuantidadeCotada(compraRequisicaoDetalhe.getQuantidadeCotada().add(compraCotacaoDetalhe.getQuantidade()));
				if (compraRequisicaoDetalhe.getQuantidade().compareTo(compraRequisicaoDetalhe.getQuantidadeCotada()) == 0) {
					compraRequisicaoDetalhe.setItemCotado("S");
				}

				compraCotacaoDetalhe.setCompraRequisicaoDetalhe(compraRequisicaoDetalhe);
				
				mensagemErro("Item incluído com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//mensagemErro("Ocorreu um erro na inclusão do item");
		}
	}


	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new CotacaoCompraEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			CotacaoCompraEntity cotacao = (CotacaoCompraEntity) id;

			try {
				business.delete(cotacao);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	public List<RequisicaoCompraDetalheEntity> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoCompraDetalheEntity.class);
	}
	
	public ReqCotacaoDetalheEntity novoRequisicaoCotacaoDetalhe() {
		ReqCotacaoDetalheEntity cotacaoDetalhe = new ReqCotacaoDetalheEntity();
		currentBean.addReqCotacaoDetalhe(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(List<ReqCotacaoDetalheEntity> values) {
		for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : values) {
			//currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
			currentBean.removeReqCotacaoDetalhe(requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	public List<FornecedorEntity> buscarFornecedores() {
		return fornecedorDao.getAll(FornecedorEntity.class);
	}
	
	public List<CotacaoCompraEntity> buscarCotacao() throws Exception {
		return business.getAll(CotacaoCompraEntity.class);
	}

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		currentBean.addFornecedorCotacao(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity requisicaoCotacaoDetalhe : values) {
			currentBean.removeFornecedorCotacao(requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public CotacaoCompraEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}