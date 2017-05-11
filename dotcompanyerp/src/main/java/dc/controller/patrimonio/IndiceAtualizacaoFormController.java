package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.converter.Teste;
import dc.control.validator.ObjectValidator;
import dc.entidade.patrimonio.IndiceAtualizacaoEntity;
import dc.servicos.dao.patrimonio.IndiceAtualizacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.IndiceAtualizacaoFormView;

@Controller
@Scope("prototype")
public class IndiceAtualizacaoFormController extends CRUDFormController<IndiceAtualizacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceAtualizacaoFormView subView;

	/** DAO'S */

	@Autowired
	private IndiceAtualizacaoDAO pDAO;

	/** ENTITIES */

	private IndiceAtualizacaoEntity pEntity;

	/** CONSTRUTOR */

	public IndiceAtualizacaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new IndiceAtualizacaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Índice de Atualização";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			IndiceAtualizacaoEntity pEntity111 = (IndiceAtualizacaoEntity) Teste.entityToSubview(this.subView, this.pEntity);

			Date dataIndice = this.subView.getPdfDataIndice().getValue();
			String nome = this.subView.getTfNome().getValue();
			Double valor = Double.parseDouble(this.subView.getTfValor().getValue());
			Double valorAlternativo = Double.parseDouble(this.subView.getTfValorAlternativo().getValue());

			this.pEntity.setDataIndice(dataIndice);
			this.pEntity.setNome(nome);
			this.pEntity.setValor(valor);
			this.pEntity.setValorAlternativo(valorAlternativo);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new IndiceAtualizacaoEntity();

			this.subView.getPdfDataIndice().setValue(this.pEntity.getDataIndice());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));
			this.subView.getTfValorAlternativo().setValue(String.valueOf(this.pEntity.getValorAlternativo()));
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = pDAO.find(id);

			this.subView.getPdfDataIndice().setValue(this.pEntity.getDataIndice());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));
			this.subView.getTfValorAlternativo().setValue(String.valueOf(this.pEntity.getValorAlternativo()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new IndiceAtualizacaoEntity();

			this.subView.getPdfDataIndice().setValue(this.pEntity.getDataIndice());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));
			this.subView.getTfValorAlternativo().setValue(String.valueOf(this.pEntity.getValorAlternativo()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new IndiceAtualizacaoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new IndiceAtualizacaoEntity();

			this.subView.getPdfDataIndice().setValue(this.pEntity.getDataIndice());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfValor().setValue(String.valueOf(this.pEntity.getValor()));
			this.subView.getTfValorAlternativo().setValue(String.valueOf(this.pEntity.getValorAlternativo()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		try {
			String nome = (String) this.subView.getTfNome().getValue();

			if (!ObjectValidator.validateString(nome)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfNome(), msg);

				return false;
			}

			String valor = this.subView.getTfValor().getValue();

			if (!ObjectValidator.validateNumber(valor)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfValor(), msg);

				return false;
			}

			String valorAlternativo = this.subView.getTfValorAlternativo().getValue();

			if (!ObjectValidator.validateNotRequiredNumber(valorAlternativo)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getTfValorAlternativo(), msg);

				return false;
			}

			Object dataIndice = this.subView.getPdfDataIndice().getValue();

			if (!ObjectValidator.validateDate(dataIndice)) {
				String msg = "Não pode ficar em branco.";

				adicionarErroDeValidacao(this.subView.getPdfDataIndice(), msg);

				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_indice_atualizacao_fc";
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public IndiceAtualizacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}