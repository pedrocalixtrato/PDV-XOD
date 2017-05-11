package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.model.business.tributario.IcmsCustomizadoBusiness;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.tabela.ICfopDAO;
import dc.servicos.dao.geral.tabela.ICsosnbDAO;
import dc.servicos.dao.geral.tabela.ICstIcmsbDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tributario.ICMSCustomizadoFormView;

@Controller
@Scope("prototype")
public class IcmsCustomizadoFormController extends CRUDFormController<IcmsCustomizadoCabecalhoEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private IcmsCustomizadoCabecalhoEntity entity;
	private ICMSCustomizadoFormView subView;
	
	@Autowired
    private IcmsCustomizadoBusiness<IcmsCustomizadoCabecalhoEntity> business;
	
	
	/**
	 * DAO'S
	 */
	
	@Autowired
	private IUfDAO ufDAO;
	@Autowired
	private ICfopDAO cfopDAO;

	@Autowired
	private ICsosnbDAO csosnbDAO;

	@Autowired
	private ICstIcmsbDAO cstbDAO;


	public IcmsCustomizadoFormController() {
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
        } catch (FieldGroup.CommitException ce) {

            return false;
        }
	}

	@Override
	protected void criarNovoBean() {
		try {
            this.entity = new IcmsCustomizadoCabecalhoEntity();

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
		
	}

	@Override
	protected void initSubView() {
		try {
            this.subView = new ICMSCustomizadoFormView(this);
            
            this.fieldGroup = new DCFieldGroup<>(IcmsCustomizadoCabecalhoEntity.class);
            
            fieldGroup.bind(this.subView.getCmbOrigemMercadoria(), "origemMercadoria");
            fieldGroup.bind(this.subView.getTxaDescricao(), "descricao");


        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
            this.entity = this.business.find(id);
            fieldGroup.setItemDataSource(this.entity);
            
            List<IcmsCustomizadoDetalheEntity> detalhes = entity.getDetalhes();

    		for (IcmsCustomizadoDetalheEntity d : detalhes) {
    			Integer idCsosn = new Integer(d.getCsosnB().trim());
    			d.setCsosn(csosnbDAO.find(idCsosn));

    			Integer idCst = new Integer(d.getCstB().trim());
    			d.setCst(cstbDAO.find(idCst));

    		}

    		subView.preencheSubForm(detalhes);


        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	protected void actionSalvar() {
		try {
            this.business.saveOrUpdate(this.entity);
            
            notifiyFrameworkSaveOK(this.entity);
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
		return "Icms Customizado";
	}

	 @Override
	    public boolean isFullSized() {
	        return true;
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

	        try {

	        } catch (Exception e) {
	            e.printStackTrace();

	            mensagemErro(e.getMessage());
	        }
	    }

	@Override
	public IcmsCustomizadoCabecalhoEntity getModelBean() {
		return entity;
	}
	
	public IcmsCustomizadoBusiness<IcmsCustomizadoCabecalhoEntity> getBusiness() {
        return business;
    }
	
	public IcmsCustomizadoDetalheEntity novoDetalhe() {
		IcmsCustomizadoDetalheEntity detalhe = new IcmsCustomizadoDetalheEntity();
		entity.adicionarDetalhe(detalhe);
		return detalhe;
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.getAll();
	}

	public List<CfopEntity> carregarCfop() {
		return cfopDAO.getAll();
	}

	public List<CsosnbEntity> carregarCsosnb() {
		return csosnbDAO.getAll();
	}

	public List<CstIcmsbEntity> carregarCstb() {
		return  cstbDAO.getAll();
	}
		
	public void removerDetalhe(List<IcmsCustomizadoDetalheEntity> values) {
		for (IcmsCustomizadoDetalheEntity value : values) {
			entity.removeDetalhe(value);
		}
	}
	
	public List<UfEntity> buscarUf() {
		return ufDAO.getAll(UfEntity.class);
	}
	
	public List<CfopEntity> buscarCfop() {
		return cfopDAO.getAll(CfopEntity.class);
	}
	
	public List<CsosnbEntity> buscarCsosn() {
		return csosnbDAO.getAll(CsosnbEntity.class);
	}
	
	public List<CstIcmsbEntity> buscarCst() {
		return cstbDAO.getAll(CstIcmsbEntity.class);
	}


}
