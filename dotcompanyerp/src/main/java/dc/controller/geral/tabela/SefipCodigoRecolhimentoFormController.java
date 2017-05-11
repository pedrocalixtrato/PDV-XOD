package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.SefipCodigoRecolhimentoEntity;
import dc.servicos.dao.geral.tabela.ISefipCodigoRecolhimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.SefipCodigoRecolhimentoFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class SefipCodigoRecolhimentoFormController extends
		CRUDFormController<SefipCodigoRecolhimentoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SefipCodigoRecolhimentoFormView subView;

	@Autowired
	private ISefipCodigoRecolhimentoDAO sefipCodigoRecolhimentoDAO;

	private SefipCodigoRecolhimentoEntity currentBean;

	@Override
	protected String getNome() {
		return "Código Recolhimento - SEFIP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setAplicacao(subView.getTxtAplicacao().getValue());
		try {
			sefipCodigoRecolhimentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = sefipCodigoRecolhimentoDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtAplicacao().setValue(currentBean.getAplicacao());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new SefipCodigoRecolhimentoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new SefipCodigoRecolhimentoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		sefipCodigoRecolhimentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtDescricao().getValue() == null
				|| subView.getTxtDescricao().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "sefipCodigoRecolhimentoForm";
	}

	@Override
	public SefipCodigoRecolhimentoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}