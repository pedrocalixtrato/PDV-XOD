package dc.controller.geral.eventos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.TipoSemestre;
import dc.control.util.ClassUtils;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.model.business.eventos.ContratoEventosBusiness;
import dc.servicos.dao.geral.eventos.ICerimonialEventosDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.ContratoEventosFormView;

@Controller
@Scope("prototype")
public class ContratoEventosFormController extends CRUDFormController<ContratoEventosEntity> {
	
	private static final long serialVersionUID = 1L;

	private ContratoEventosFormView subView;
	
	@Autowired
	private ContratoEventosBusiness<ContratoEventosEntity> business;

	@Autowired
	private ICerimonialEventosDAO cerimonialEventosDAO;

	private ContratoEventosEntity entity;

	public ContratoEventosFormController() {
		// TODO Auto-generated constructor stub
	}
	
	public ContratoEventosBusiness<ContratoEventosEntity> getBusiness() { 
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
		} catch (FieldGroup.CommitException ce) {
			return false;
		}

	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ContratoEventosEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ContratoEventosFormView(this);

            this.fieldGroup = new DCFieldGroup<>(ContratoEventosEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTxtCurso(),"curso");
			fieldGroup.bind(this.subView.getTxtUnidade(),"unidade");
			fieldGroup.bind(this.subView.getTxtAnoFormatura(),"anoFormatura");
			fieldGroup.bind(this.subView.getTxtQuantidadeFormandos(),"quantidadeFormandos");
			fieldGroup.bind(this.subView.getPdfDataContrato(),"dataContrato");
			fieldGroup.bind(this.subView.getPdfDataPrimeiroEvento(),"dataPrimeiroEvento");
	
			
			this.subView.getMocNomeCerimonial().configuraCombo(
					"nome", CerimonialEventosListController.class, this.cerimonialEventosDAO, this.getMainController());

			fieldGroup.bind(this.subView.getMocNomeCerimonial(), "nomeCerimonial");
			comboTipoSemestre();
			
			fieldGroup.bind(this.subView.getCbTipoSemestre(), "tipoSemestre");


		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

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
			System.out.println(this.entity.getDataPrimeiroEvento().getTime());

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
		return "Contrato Eventos";
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
	public ContratoEventosEntity getModelBean() {
		return entity;
	}
	
	public void comboTipoSemestre() {
		for (TipoSemestre en : TipoSemestre.values()) {
			this.subView.getCbTipoSemestre().addItem(en);
		}
	}


}
