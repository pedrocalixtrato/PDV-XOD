package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.LocalizacaoEn;
import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.model.business.geral.pessoal.FornecedorBusiness;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoForCliDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.FornecedorFormView;

@Controller
@Scope("prototype")
public class FornecedorFormController extends
		CRUDFormController<FornecedorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FornecedorFormView subView;

	/**
	 * ENTITY
	 */

	private FornecedorEntity entity;

	/**
	 * BUSINESS
	 */
	
	@Autowired
	private FornecedorBusiness<FornecedorEntity> business;

	/**
	 * DAO
	 */

	//@Autowired
	//private FornecedorDAO dao;

	@Autowired
	private IAtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private ISituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private IPessoaDAO pessoaDAO;

	/**
	 * CONSTRUTOR
	 */

	public FornecedorFormController() {
		// TODO Auto-generated constructor stub
	}
	
	public FornecedorBusiness<FornecedorEntity> getBusiness() {
		 return business;
	}

	@Override
	protected String getNome() {
		return "Fornecedor";
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

	@Override
	public FornecedorEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new FornecedorFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(FornecedorEntity.class);
			
			// Mapeia os campos
			//fieldGroup.bind(this.subView.getPdfDesde(),"desde");
			fieldGroup.bind(this.subView.getCbGerarFaturamento(),"geraFaturamento");
			//fieldGroup.bind(this.subView.getCbOptanteSimples(),"optanteSimplesNacional");
			//fieldGroup.bind(this.subView.getCbLocalizacao(),"localizacao");
			//fieldGroup.bind(this.subView.getCbSofreRentencao(),"sofreRetencao");
			//fieldGroup.bind(this.subView.getTfChequeNominalA(),"chequeNominalA");
			//fieldGroup.bind(this.subView.getTaObservacao(),"observacao");
			
			fieldGroup.bind(this.subView.getMocPessoa(),"pessoa");
			fieldGroup.bind(this.subView.getMocSituacaoForCli(),"situacaoForCli");
			fieldGroup.bind(this.subView.getMocAtividadeForCli(),"atividadeForCli");
			
			this.subView.getMocPessoa().configuraCombo(
					"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());
			this.subView.getMocSituacaoForCli().configuraCombo(
					"nome", SituacaoForCliListController.class, this.situacaoForCliDAO, this.getMainController());
			this.subView.getMocAtividadeForCli().configuraCombo(
					"nome", AtividadeForCliListController.class, this.atividadeForCliDAO, this.getMainController());
            
            comboGerarFaturamento();
            comboLocalizacao();
            comboSofreRentencao();
            comboOptanteSimples();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
		    return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
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
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.entity);

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new FornecedorEntity();
			
			fieldGroup.setItemDataSource(this.entity);
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
			for (Serializable id : ids) {
				FornecedorEntity forn = (FornecedorEntity) id;

				try {
					business.delete(forn);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}
	
	/**
	 * COMBOS
	 */

	public void comboGerarFaturamento() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbGerarFaturamento().addItem(en);
		}
	}
	public void comboSofreRentencao() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbSofreRentencao().addItem(en);
		}
	}
	public void comboOptanteSimples() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbOptanteSimples().addItem(en);
		}
	}
	public void comboLocalizacao() {
		for (LocalizacaoEn en : LocalizacaoEn.values()) {
			this.subView.getCbLocalizacao().addItem(en);
		}
	}

}