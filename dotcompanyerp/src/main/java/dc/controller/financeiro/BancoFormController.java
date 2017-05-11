package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.IBancoDAO;
import dc.visao.financeiro.BancoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class BancoFormController extends CRUDFormController<BancoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BancoFormView subView;

	@Autowired
	private IBancoDAO bancoDAO;

	private BancoEntity currentBean;

	@Override
	protected String getNome() {
		return "Banco";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			bancoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
        try {
			
        	currentBean = bancoDAO.find(id);

			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		subView = new BancoFormView();
		
		this.fieldGroup = new DCFieldGroup<>(BancoEntity.class);

        fieldGroup.bind(this.subView.getTxtNome(), "nome");
        fieldGroup.bind(this.subView.getTxtCodigo(), "codigo");
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new BancoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			this.bancoDAO.deleteAll(ids);

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
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public BancoEntity getModelBean() {
		return currentBean;
	}
}
