package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.entidade.suprimentos.compra.CotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.servicos.dao.suprimentos.compra.CotacaoCompraBusiness;
import dc.servicos.dao.suprimentos.compra.IFornecedorCotacaoDAO;
import dc.servicos.dao.suprimentos.compra.IRequisicaoDetalheDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.ConfirmaCotacaoFormView;

@Controller
@Scope("prototype")
public class ConfirmaCotacaoFormController extends
		CRUDFormController<CotacaoCompraEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConfirmaCotacaoFormView subView;

	@Autowired
	private IFornecedorCotacaoDAO fornecedorCotacaoDao;

	@Autowired
	private IRequisicaoDetalheDAO requisicaoDetalheDao;

	private CotacaoCompraEntity currentBean;
	
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
		return "Confirma cotação";
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
			currentBean = business.find(id);
			
			List<FornecedorCotacaoEntity> fornecedorCotacaos = currentBean
					.getCompraFornecedorCotacaos();
			subView.fillCompraFornecedorCotacoesSubForm(fornecedorCotacaos);

			List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes = currentBean
					.getCompraReqCotacaoDetalhes();

			for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : compraReqCotacaoDetalhes) {
				RequisicaoCompraDetalheEntity requisicaoDetalhe = requisicaoCotacaoDetalhe
						.getRequisicaoDetalhe();

				COTACOES: for (FornecedorCotacaoEntity fornecedorCotacao : fornecedorCotacaos) {
					List<CotacaoDetalheEntity> cotacaoDetalhes = fornecedorCotacao
							.getCotacaoDetalheList();

					for (CotacaoDetalheEntity cotacaoDetalhe : cotacaoDetalhes) {
						if (cotacaoDetalhe.getProduto().equals(
								requisicaoDetalhe.getProduto())
								&& cotacaoDetalhe.getQuantidade().equals(
										requisicaoDetalhe.getQuantidade())) {
							continue COTACOES;
						}
					}

					CotacaoDetalheEntity cotacaoDetalhe = new CotacaoDetalheEntity();
					cotacaoDetalhe.setProduto(requisicaoDetalhe.getProduto());
					cotacaoDetalhe.setQuantidade(requisicaoDetalhe.getQuantidade());
					// fornecedorCotacao.addCotacaoDetalhe(cotacaoDetalhe);
				}
				
			}
			
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void initSubView() {
		try {
			subView = new ConfirmaCotacaoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(CotacaoCompraEntity.class);
		
     	    fieldGroup.bind(this.subView.getPdfDataCotacao(), "dataCotacao");
     	    fieldGroup.bind(this.subView.getTfDescricao(), "descricao");
     	    
     	   this.subView.getCbFornecedor().configuraCombo(
					"fornecedor.pessoa.nome", FornecedorCotacaoListController.class, this.fornecedorCotacaoDao, this.getMainController());
     	   
			
		}catch(Exception e) {
			e.printStackTrace();
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

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity fornecedorCotacao : values) {
			currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
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