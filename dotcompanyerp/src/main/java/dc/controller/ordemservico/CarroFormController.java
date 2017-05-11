package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.ClienteListController;
import dc.entidade.ordemservico.CarroEntity;
import dc.entidade.ordemservico.ModeloOsEntity;
import dc.model.business.ordemservico.CarroBusiness;
import dc.model.business.ordemservico.ModeloOsBusiness;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.ordemservico.ICombustivelDAO;
import dc.model.dao.ordemservico.ICorDAO;
import dc.model.dao.ordemservico.IModeloOsDAO;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.CarroFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class CarroFormController extends CRUDFormController<CarroEntity> {

	private static final long serialVersionUID = 1L;

	private CarroFormView subView;

	private CarroEntity currentBean;

	@Autowired
	private CarroBusiness<CarroEntity> business;

	/*@Autowired
	private CorBusiness<CorEntity> businessCor;

	@Autowired
	private CombustivelBusiness<CombustivelEntity> businessCombustivel;

	@Autowired
	private MarcaOsBusiness<MarcaOsEntity> businessMarcaOs;

	@Autowired
	private ModeloOsBusiness<ModeloOsEntity> businessModeloOs;

	@Autowired
	private ClienteBusiness<ClienteEntity> businessCliente;*/
	
	@Autowired
	private ModeloOsBusiness<ModeloOsEntity> businessModeloOs;
	
	@Autowired
	private ICorDAO corDAO;
	
	@Autowired
	private ICombustivelDAO combustivelDAO;
	
	@Autowired
	private IMarcaOsDAO marcaDAO;
	
	@Autowired
	private IModeloOsDAO modeloDAO;
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	

	/**
	 * CONSTRUTOR
	 */
	public CarroFormController() {
	}

	public CarroBusiness<CarroEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Carro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected void actionSalvar() {
		try {
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
	
		//preencheCombos();
		
		try {
			subView = new CarroFormView(this);
			this.fieldGroup = new DCFieldGroup<>(CarroEntity.class);
		
     	    fieldGroup.bind(this.subView.getTfPlaca(), "placa");
			
	        fieldGroup.bind(this.subView.getCbCliente(), "cliente");
	        fieldGroup.bind(this.subView.getCbMarca(), "marca");
			fieldGroup.bind(this.subView.getCbModelo(), "modelo");
			fieldGroup.bind(this.subView.getCbCor(), "cor");
			fieldGroup.bind(this.subView.getTfAno(), "ano");
			fieldGroup.bind(this.subView.getCbCombustivel(), "combustivel");
			
			        // Configura os ManyToOneComboFields
			        this.subView.getCbCliente().configuraCombo(
			                "pessoa.nome", ClienteListController.class, this.clienteDAO, this.getMainController());
			        
			        this.subView.getCbMarca().configuraCombo(
			                "nome", MarcaListController.class, this.marcaDAO, this.getMainController());
			        
			        this.subView.getCbCor().configuraCombo(
			                "nome", CorListController.class, this.corDAO, this.getMainController());
			        
			        this.subView.getCbCombustivel().configuraCombo(
			                "nome", CombustivelListController.class, this.combustivelDAO, this.getMainController());
			        
			        this.subView.getCbModelo().configuraCombo(
			                "nome", ModeloListController.class, this.modeloDAO, this.getMainController());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		
		
		try {
			currentBean = new CarroEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	/*private void preencheCombos() {
		DefaultManyToOneComboModel<ClienteEntity> cliente = new DefaultManyToOneComboModel<ClienteEntity>(ClienteListController.class,super.getMainController(),false,this.businessCliente);

		this.subView.getCbCliente().setModel(cliente);

		DefaultManyToOneComboModel<MarcaOsEntity> marca = new DefaultManyToOneComboModel<MarcaOsEntity>(MarcaListController.class,super.getMainController(),false,this.businessMarcaOs);

		this.subView.getCbMarca().setModel(marca);

		DefaultManyToOneComboModel<CorEntity> cor = new DefaultManyToOneComboModel<CorEntity>(CorListController.class, super.getMainController(), false, this.businessCor);

		this.subView.getCbCor().setModel(cor);

		DefaultManyToOneComboModel<CombustivelEntity> combustivel = new DefaultManyToOneComboModel<CombustivelEntity>(CombustivelListController.class,
				super.getMainController(),false,this.businessCombustivel);

		this.subView.getCbCombustivel().setModel(combustivel);

	}*/

	
	/*public void getModelo(String classePesquisa, Integer idSelecionado) {
		DefaultManyToOneComboModelSelect<ModeloOsEntity> modelo = new DefaultManyToOneComboModelSelect<ModeloOsEntity>(ModeloListController.class, this.businessModeloOs,
				super.getMainController(), classePesquisa, idSelecionado, false);

		this.subView.getCbModelo().setModel(modelo);
	}*/

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
	
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public CarroEntity getModelBean() {
		return currentBean;
	}
}
