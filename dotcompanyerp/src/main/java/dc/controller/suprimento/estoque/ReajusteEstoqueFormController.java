package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ReajusteCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ReajusteDetalheEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.suprimentos.estoque.IReajusteCabecalhoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimento.estoque.ReajusteEstoqueFormView;
import dc.visao.suprimento.estoque.ReajusteEstoqueFormView.TipoReajuste;

@Controller
@Scope("prototype")
public class ReajusteEstoqueFormController extends
		CRUDFormController<ReajusteCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IReajusteCabecalhoDAO dao;

	@Autowired
	private IProdutoDAO produtoDAO;

	private ReajusteCabecalhoEntity currentBean;

	ReajusteEstoqueFormView subView;
	
	@Autowired
	private ProdutoBusiness<ProdutoEntity> produtoBusiness;

	@Override
	protected String getNome() {
		return "Reajuste de Preço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public ColaboradorEntity buscaColaborador() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity col = usuario.getColaborador();

		return col;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDataReajuste(subView.getDataReajuste().getValue());
			currentBean.setPorcentagem(new BigDecimal(subView.getPorcentagem().getValue()));
			currentBean.setTipo(((TipoReajuste) subView.getCmbTipoReajuste()
					.getValue()).getCodigo());
			currentBean.setColaborador(buscaColaborador());
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
		
		subView.getDataReajuste().setValue(currentBean.getDataReajuste());
		subView.getPorcentagem().setValue(
				currentBean.getPorcentagem().toString());
		subView.preencherDetalhesSubForm(currentBean.getDetalhes());
		subView.carregarView(currentBean);

		System.out.println("");
	}

	@Override
	protected void initSubView() {
	
		
		try {
			subView = new ReajusteEstoqueFormView(this);
			
			subView.carregarTipoReajuste();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ReajusteCabecalhoEntity();
		subView.carregarTipoReajuste();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getPorcentagem().getValue())) {
			adicionarErroDeValidacao(subView.getPorcentagem(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTipoReajuste().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipoReajuste(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getPorcentagem().getValue())) {
			adicionarErroDeValidacao(subView.getPorcentagem(), "Valor Inválido");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void quandoNovo() {
		subView.preencherDetalhesSubForm(currentBean.getDetalhes());
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

	public ReajusteDetalheEntity novoDetalhe() {
		ReajusteDetalheEntity detalhe = new ReajusteDetalheEntity();
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
	public ReajusteCabecalhoEntity getModelBean() {
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

}