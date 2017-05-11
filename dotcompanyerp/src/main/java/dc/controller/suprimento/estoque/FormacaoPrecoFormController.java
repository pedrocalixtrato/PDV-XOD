package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.FormacaoPrecoEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.estoque.FormacaoPrecoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.estoque.FormacaoPrecoFormView;

@Controller
@Scope("prototype")
public class FormacaoPrecoFormController extends CRUDFormController<FormacaoPrecoEntity> {
	
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Autowired
		FormacaoPrecoDAO dao;

		@Autowired
		ProdutoDAO produtoDAO;

		private FormacaoPrecoEntity currentBean;

		FormacaoPrecoFormView subView;
		
		@Autowired
		private ProdutoBusiness<ProdutoEntity> produtoBusiness;

		@Override
		protected String getNome() {
			return "Formação Preço";
		}

		@Override
		protected Component getSubView() {
			return subView;
		}

		@Override
		protected void actionSalvar() {
			try {
				dao.saveOrUpdate(currentBean);

				notifiyFrameworkSaveOK(this.currentBean);
			} catch (Exception e) {
				mensagemErro(e.getMessage());
				e.printStackTrace();
			}
		}

		@Override
		protected void carregar(Serializable id) {
			currentBean = dao.find((Integer) id);
			
			subView.preencherDetalhesSubForm(currentBean.getDetalhes());
		}

		@Override
		protected void initSubView() {
		
			
			try {
				subView = new FormacaoPrecoFormView(this);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void criarNovoBean() {
			currentBean = new FormacaoPrecoEntity();
		}

		@Override
		public String getViewIdentifier() {
			return ClassUtils.getUrl(this);
		}

		@Override
		protected boolean validaSalvar() {
			boolean valido = true;

			return valido;
		}

		@Override
		protected void remover(List<Serializable> ids) {
			try {
				dao.deleteAllByIds(ids);

				mensagemRemovidoOK();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void removerEmCascata(List<Serializable> objetos) {
			// TODO Auto-generated method stub
			remover(objetos);
		}

		public FormacaoPrecoEntity novoDetalhe() {
			FormacaoPrecoEntity detalhe = new FormacaoPrecoEntity();
			currentBean.addDetalhe(detalhe);

			return detalhe;
		}

		public List<ProdutoEntity> buscarProdutos() {
			return produtoDAO.getAll(ProdutoEntity.class);
		}

		@Override
		public boolean isFullSized() {
			return true;
		}

		@Override
		public FormacaoPrecoEntity getModelBean() {
			// TODO Auto-generated method stub
			return currentBean;
		}

		public BeanItemContainer<ProdutoEntity> getProdutoBic() {
			try {
				List<ProdutoEntity> auxLista = this.produtoBusiness.findAll();

				BeanItemContainer<ProdutoEntity> bic = new BeanItemContainer<ProdutoEntity>(
						ProdutoEntity.class, auxLista);

				return bic;
			} catch (Exception e) {
				e.printStackTrace();

				return null;
			}
		}
		
		 public void calculaPrecoVenda() {
		        /*
		        C = Valor Compra
		        E = % de encargos sobre vendas
		        M = % markup sobre o custo
		        P = preÃ§o de venda

		        P = C(1 + M) Ã· (1-E)
		         */

		        List<FormacaoPrecoEntity> listaFormacaoPreco = subView.getSubForm().getDados();
		        FormacaoPrecoEntity formacaoPreco;
		        BigDecimal valorVenda;
		        BigDecimal valorCompra;
		        BigDecimal encargo;
		        BigDecimal markup;
		        for (int i = 0; i < listaFormacaoPreco.size(); i++) {
		            formacaoPreco = listaFormacaoPreco.get(i);

		            valorCompra = formacaoPreco.getProduto().getValorCompra();
		            markup = formacaoPreco.getMarkup().divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
		            encargo = formacaoPreco.getEncargoVenda().divide(BigDecimal.valueOf(100), RoundingMode.DOWN);

		            valorVenda = valorCompra.multiply(BigDecimal.ONE.add(markup))
		                    .divide(BigDecimal.ONE.subtract(encargo), RoundingMode.DOWN);

		            formacaoPreco.setValorVenda(valorVenda);
		            subView.getSubForm().fillWith(listaFormacaoPreco);
		        }
		    }


}
