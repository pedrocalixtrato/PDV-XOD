package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.ParcelaCondicaoPagamento;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.servicos.dao.comercial.IParcelaCondicaoPagamentoDAO;
import dc.visao.comercial.CondicaoPagamentoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class CondicaoPagamentoFormController extends CRUDFormController<CondicaoPagamento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CondicaoPagamento currentBean;

	private CondicaoPagamentoFormView subView;

	@Autowired
	private ICondicaoPagamentoDAO dao;

	@Autowired
	private IParcelaCondicaoPagamentoDAO parcelaDAO;

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
			currentBean = new CondicaoPagamento();
			
			fieldGroup.setItemDataSource(this.currentBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		
		try {
			this.subView = new CondicaoPagamentoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(CondicaoPagamento.class);
            fieldGroup.bind(this.subView.getTxtNome(),"nome");

		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = dao.find(id);
			List<ParcelaCondicaoPagamento> parcelas = currentBean.getParcelas();
			if (parcelas != null) {
				subView.preencherSubForm(parcelas);
			}
            fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {

		try {

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Condição de Pagamento";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		try {
			for (Serializable id : ids) {
				CondicaoPagamento condicao = dao.find(id);
				for (ParcelaCondicaoPagamento parcela : condicao.getParcelas()) {
					parcelaDAO.delete(parcela);
				}
			}

			dao.deleteAllByIds(ids);
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro("Problema ao remover");
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			CondicaoPagamento cp = (CondicaoPagamento) id;

			try {
				dao.delete(cp);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	public ParcelaCondicaoPagamento adicionarParcela() {
		ParcelaCondicaoPagamento parcela = new ParcelaCondicaoPagamento();
		currentBean.adicionarParcela(parcela);
		return parcela;
	}

	@Override
	public CondicaoPagamento getModelBean() {
		return currentBean;
	}

}
