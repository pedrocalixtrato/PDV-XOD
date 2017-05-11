package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.controller.tributario.IcmsCustomizadoListController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.model.dao.geral.diverso.IAlmoxarifadoDAO;
import dc.model.dao.geral.produto.IGrupoDAO;
import dc.model.dao.geral.produto.IMarcaDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.model.dao.geral.produto.IUnidadeProdutoDAO;
import dc.model.dao.tributario.IGrupoTributarioDAO;
import dc.model.dao.tributario.IIcmsCustomizadoDAO;
import dc.servicos.dao.geral.produto.INcmDAO;
import dc.servicos.dao.geral.produto.ISubGrupoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.ProdutoFormView;

@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<ProdutoEntity> {

    private static final long serialVersionUID = 1L;

    private ProdutoFormView subView;

    private ProdutoEntity entity;

    @Autowired
    private ProdutoBusiness<ProdutoEntity> business;

    /**
     * DAO
     */
    
    @Autowired
    private IProdutoDAO dao;

    @Autowired
    private ISubGrupoDAO subGrupoDAO;

    @Autowired
    private IAlmoxarifadoDAO almoxarifadoDAO;

    @Autowired
    private IMarcaDAO marcaDAO;

    @Autowired
    private IIcmsCustomizadoDAO icmsCustomizadoDAO;

    @Autowired
    private IGrupoTributarioDAO grupoTributarioDAO;

    @Autowired
    private IGrupoDAO grupoDAO;

    @Autowired
    private INcmDAO ncmDAO;

    @Autowired
    private IProdutoDAO produtoDAO;

    @Autowired
    private IUnidadeProdutoDAO unidadeProdutoDAO;

    public ProdutoFormController() {
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
            this.entity = new ProdutoEntity();

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }

    }

    @Override
    protected void initSubView() {
        try {
            this.subView = new ProdutoFormView(this);

            configuraBinding();

            this.subView.getCbTemIcmsCustomizado().setValue(SimNaoEn.N);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void configuraBinding() {
        this.fieldGroup = new DCFieldGroup<>(ProdutoEntity.class);

        
        fieldGroup.bind(this.subView.getMocSubGrupo(), "subGrupo");
        fieldGroup.bind(this.subView.getMocUnidadeProduto(), "unidadeProduto");
        fieldGroup.bind(this.subView.getMocMarca(), "marca");
        fieldGroup.bind(this.subView.getMocAlmoxarifado(), "almoxarifado");
        fieldGroup.bind(this.subView.getMocGrupo(), "grupo");
        //fieldGroup.bind(this.subView.getMocGrupoTributario(), "grupoTributario");
        
        /**************** Informação Geral *********************/        
        fieldGroup.bind(this.subView.getTfNome(), "nome");
        fieldGroup.bind(this.subView.getMocNcm(), "ncm");
        fieldGroup.bind(this.subView.getCbInativo(), "inativo");
        fieldGroup.bind(this.subView.getCbClasse(), "classe");
        
        /**************** Informação de Valores *********************/  
        /*fieldGroup.bind(this.subView.getCfValorCompra(), "valorCompra");
        fieldGroup.bind(this.subView.getCfValorVenda(), "valorVenda");
        fieldGroup.bind(this.subView.getCfValorVendaMinimo(), "precoVendaMinimo");
        fieldGroup.bind(this.subView.getCfValorSugerido(), "precoSugerido");
        fieldGroup.bind(this.subView.getCfPrecoLucroZero(), "precoLucroZero");
        fieldGroup.bind(this.subView.getCfPrecoLucroMinimo(), "precoLucroMinimo");
        fieldGroup.bind(this.subView.getCfPrecoLucroMaximo(), "precoLucroMaximo");*/
        
        /**************** Informação Complementar *********************/  
        fieldGroup.bind(this.subView.getTfLst(), "codigoLst");
        fieldGroup.bind(this.subView.getTfExtipi(), "exTipi");
        fieldGroup.bind(this.subView.getCbTipoVenda(), "tipoVenda");
        fieldGroup.bind(this.subView.getCbIat(), "iat");
        fieldGroup.bind(this.subView.getCbIppt(), "ippt");
        fieldGroup.bind(this.subView.getCbTipoItemSped(), "tipoSped");
        fieldGroup.bind(this.subView.getTfCodigoBalanca(), "codigoBalanca");

        // Configura os ManyToOneComboFields
        this.subView.getMocSubGrupo().configuraCombo(
                "nome", SubGrupoListController.class, this.subGrupoDAO, this.getMainController());
        
        this.subView.getMocUnidadeProduto().configuraCombo(
                "descricao", UnidadeProdutoListController.class, this.unidadeProdutoDAO, this.getMainController());

        this.subView.getMocMarca().configuraCombo(
                "nome", MarcaListController.class, this.marcaDAO, this.getMainController());
        
        this.subView.getMocAlmoxarifado().configuraCombo(
                "nome", AlmoxarifadoListController.class, this.almoxarifadoDAO, this.getMainController());
        
        this.subView.getMocGrupo().configuraCombo(
                "nome", GrupoListController.class, this.grupoDAO, this.getMainController());
        
        this.subView.getMocGrupoTributario().configuraCombo(
                "descricao", GrupoTributarioListController.class, this.grupoTributarioDAO, this.getMainController());
        
        this.subView.getMocNcm().configuraCombo(
                "descricao", NcmListController.class, this.ncmDAO, this.getMainController());
        
        this.subView.getMocIcmsCustomizado().configuraCombo(
                "descricao", IcmsCustomizadoListController.class, this.icmsCustomizadoDAO, this.getMainController());


    }

    /*private void preencheCombos() {

        DefaultManyToOneComboModel<GrupoEntity> modelGrupo = new DefaultManyToOneComboModel<GrupoEntity>(
                GrupoListController.class, this.grupoDAO,
                super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "nome";
            }
        };
        this.subView.getMocGrupo().setModel(modelGrupo);

        DefaultManyToOneComboModel<NcmEntity> modelNcm = new DefaultManyToOneComboModel<NcmEntity>(
                NcmListController.class, this.ncmDAO, super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "descricao";
            }
        };
        this.subView.getMocNcm().setModel(modelNcm);

        DefaultManyToOneComboModel<GrupoTributarioEntity> modelGrupoTributario = new DefaultManyToOneComboModel<GrupoTributarioEntity>(
                GrupoTributarioListController.class, this.grupoTributarioDAO,
                super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "descricao";
            }
        };
        this.subView.getMocGrupoTributario().setModel(modelGrupoTributario);

    }*/

    @Override
    protected void carregar(Serializable id) {

        try {
            this.entity = this.business.find(id);

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void actionSalvar() {

        try {
            this.business.saveOrUpdate(this.entity);
            
            /*this.entity.setValorVenda(NumberUtils.createBigDecimal(this.subView.getCfValorVenda().getConvertedValue()));
            this.entity.setValorCompra(NumberUtils.createBigDecimal(this.subView.getCfValorCompra().getConvertedValue()));
            this.entity.setPrecoVendaMinimo(NumberUtils.createBigDecimal(this.subView.getCfValorVendaMinimo().getConvertedValue()));
            this.entity.setPrecoSugerido(NumberUtils.createBigDecimal(this.subView.getCfValorSugerido().getConvertedValue()));
            this.entity.setCustoMedioLiquido(NumberUtils.createBigDecimal(this.subView.getCfCustoMedioLiquido().getConvertedValue()));
            this.entity.setPrecoLucroZero(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroZero().getConvertedValue()));
            this.entity.setPrecoLucroMaximo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMaximo().getConvertedValue()));
            this.entity.setPrecoLucroMinimo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMinimo().getConvertedValue()));
            this.entity.setMarkup(NumberUtils.createBigDecimal(this.subView.getCfMarkup().getConvertedValue()));*/

            notifiyFrameworkSaveOK(this.entity);
        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
    }

    public String formataBigDecimal(String valor) {
        String format = "";
        format = valor.replace(".", "").replace(",", ".");
        return format;
    }

    @Override
    protected Component getSubView() {
        return subView;
    }

    @Override
    protected String getNome() {
        return "Produto";
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
protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			ProdutoEntity produto = (ProdutoEntity) id;

			try {
				business.delete(produto);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
}

    @Override
    public ProdutoEntity getModelBean() {
        // TODO Auto-generated method stub
        return entity;
    }

    /**
     * COMBO
     */

    public List<String> getIcmsCustomizado() {
        try {
            List<String> siLista = new ArrayList<String>();

            for (SimNaoEn en : SimNaoEn.values()) {
                siLista.add(en.ordinal(), en.toString());

            }

            return siLista;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public ProdutoBusiness<ProdutoEntity> getBusiness() {
        return business;
    }

    public List<ProdutoEntity> buscarProdutos() {
        return produtoDAO.getAll(ProdutoEntity.class);
    }

}
