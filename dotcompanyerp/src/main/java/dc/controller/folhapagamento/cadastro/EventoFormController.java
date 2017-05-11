package dc.controller.folhapagamento.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.folhapagamento.cadastro.EventoEntity;
import dc.servicos.dao.folhapagamento.cadastro.EventoDAO;
import dc.visao.folhapagamento.cadastro.EventoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class EventoFormController extends CRUDFormController<EventoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EventoFormView subView;

	/** DAO'S */

	@Autowired
	private EventoDAO pDAO;

	/** ENTITIES */

	private EventoEntity pEntity;

	/** CONSTRUTOR */

	public EventoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EventoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Evento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String codigo = this.subView.getTfCodigo().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String unidade = this.subView.getTfUnidade().getValue();
			String baseCalculo = this.subView.getTfBaseCalculo().getValue();
			Double taxa = Double.parseDouble(this.subView.getTfTaxa()
					.getValue());

			this.pEntity.setCodigo(codigo);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setTipo(tipo);
			this.pEntity.setUnidade(unidade);
			this.pEntity.setBaseCalculo(baseCalculo);
			this.pEntity.setTaxa(taxa);

			/** Empresa vinda da conta do usuário logado */

			EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/** ************************************** */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new EventoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfUnidade().setValue(this.pEntity.getUnidade());
			this.subView.getTfBaseCalculo().setValue(
					this.pEntity.getBaseCalculo());
			this.subView.getTfTaxa().setValue(
					String.valueOf(this.pEntity.getTaxa()));
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfUnidade().setValue(this.pEntity.getUnidade());
			this.subView.getTfBaseCalculo().setValue(
					this.pEntity.getBaseCalculo());
			this.subView.getTfTaxa().setValue(
					String.valueOf(this.pEntity.getTaxa()));
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
			this.pEntity = new EventoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfUnidade().setValue(this.pEntity.getUnidade());
			this.subView.getTfBaseCalculo().setValue(
					this.pEntity.getBaseCalculo());
			this.subView.getTfTaxa().setValue(
					String.valueOf(this.pEntity.getTaxa()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new EventoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new EventoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfUnidade().setValue(this.pEntity.getUnidade());
			this.subView.getTfBaseCalculo().setValue(
					this.pEntity.getBaseCalculo());
			this.subView.getTfTaxa().setValue(
					String.valueOf(this.pEntity.getTaxa()));
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
		String taxa = this.subView.getTfTaxa().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(taxa)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfTaxa(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public EventoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

	/** COMBOS */

}