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
import dc.control.util.NumberUtils;
import dc.control.util.classes.ContagemCabecalhoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.model.business.suprimento.estoque.ContagemCabecalhoBusiness;
import dc.model.business.suprimento.estoque.ContagemDetalheBusiness;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.estoque.ContagemFormView;

@Controller
@Scope("prototype")
public class ContagemFormController extends
		CRUDFormController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContagemFormView subView;

	/**
	 * ENTITY
	 */

	private ContagemCabecalhoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private ContagemCabecalhoBusiness<ContagemCabecalhoEntity> business;

	@Autowired
	private ContagemDetalheBusiness<ContagemDetalheEntity> contagemDetalheBusiness;

	@Autowired
	private ProdutoBusiness<ProdutoEntity> produtoBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private IProdutoDAO produtoDAO;

	/**
	 * CONSTRUTOR
	 */

	public ContagemFormController() {
		// TODO Auto-generated constructor stub
	}

	public ContagemCabecalhoBusiness<ContagemCabecalhoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Contagem Estoque";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}
	
	public ContagemDetalheEntity calculaAcuracidade(ContagemDetalheEntity contagem) {
		
		contagem.setAcuracidade(contagem.getQuantidadeSistema().divide(contagem.getQuantidadeContada(), RoundingMode.UP).multiply(BigDecimal.valueOf(100)));
		
		return contagem;
	}

	@Override
	public ContagemCabecalhoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ContagemFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			ContagemCabecalhoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}
	
	@Override
	protected void actionSalvar() {
		try {
			this.entity.setDataContagem(this.subView.getPdfDataContagem()
					.getValue());

			//

			List<ContagemDetalheEntity> auxLista1 = this.subView
					.getSfContagemDetalhe().getDados();

			this.entity.setContagemDetalheList(auxLista1);

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			// ContagemDetalhe

			List<ContagemDetalheEntity> auxLista1 = this.contagemDetalheBusiness
					.list(this.entity);

			this.entity.setContagemDetalheList(auxLista1);

			//

			this.subView.getPdfDataContagem().setValue(
					this.entity.getDataContagem());

			this.subView.getSfContagemDetalhe().fillWith(
					this.entity.getContagemDetalheList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ContagemCabecalhoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new ContagemCabecalhoEntity();
		} catch (Exception e) {
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
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

	public ContagemDetalheEntity adicionarContagemDetalhe() {
		try {
			ContagemDetalheEntity ent = new ContagemDetalheEntity();
			ent.setContagemCabecalho(this.entity);

			this.entity.getContagemDetalheList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerContagemDetalhe(List<ContagemDetalheEntity> values) {
		try {
			for (ContagemDetalheEntity ent : values) {
				if (NumberUtils.isNotBlank(ent.getId())) {
					this.contagemDetalheBusiness.delete(ent);
				}

				this.entity.getContagemDetalheList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

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