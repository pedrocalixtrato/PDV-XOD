package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.model.business.suprimento.estoque.LoteProdutoBusiness;
import dc.servicos.dao.nfe.INfeDetalheDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.estoque.LoteProdutoFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class LoteProdutoFormController extends CRUDFormController<LoteProdutoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoteProdutoFormView subView;

	private LoteProdutoEntity currentBean;
	
	@Autowired
	private LoteProdutoBusiness<LoteProdutoEntity> business;
	
	@Autowired
	private INfeDetalheDAO nfeDetalheDAO;

	
	public LoteProdutoFormController() {
	}
	
	public LoteProdutoBusiness<LoteProdutoEntity> getBusiness() {
		return business;
	}

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
			currentBean = new LoteProdutoEntity();
			
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void initSubView() {
		
	       try {
				
				subView = new LoteProdutoFormView(this);
				
				this.fieldGroup = new DCFieldGroup<>(LoteProdutoEntity.class);
				
				// Mapeia os campos
				fieldGroup.bind(this.subView.getTxtNome(),"nome");
				fieldGroup.bind(this.subView.getPdDataCadastro(),"dataCadastro");

			}catch (Exception e) {
			   e.printStackTrace();
			}

		
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			this.currentBean = this.business.find(id);

			// Atribui a entidade carregada como origem de dados dos campos do formulario
            // no FieldGroup
			List<NfeDetalheEntity> itens = nfeDetalheDAO.findByNfeDetalhe(currentBean);
			subView.preencheSubForm(itens);
			
            fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void actionSalvar() {
		
		try {
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
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
		return "Lote Produto";
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
	protected void removerEmCascata(List<Serializable> objetos) {
		
		for (Serializable id : objetos) {
			LoteProdutoEntity loteProduto = (LoteProdutoEntity) id;

			try {
				business.delete(loteProduto);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
		
	}

	@Override
	public LoteProdutoEntity getModelBean() {
		return currentBean;
	}
	
    public NfeDetalheEntity novoNfe() {
		
	    NfeDetalheEntity nfe = new NfeDetalheEntity();
	    currentBean.addParcelaPagar(nfe);
		return nfe;
	}

	public void removerNfe(List<NfeDetalheEntity> values) {
		for (NfeDetalheEntity value : values) {
			currentBean.removeNfe(value);
		}
		
		mensagemRemovidoOK();

	}

	@Override
	public boolean isFullSized() {
		return true;
	}


}
