package dc.controller.patrimonio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.patrimonio.GrupoBemEntity;
import dc.servicos.dao.patrimonio.GrupoBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.patrimonio.GrupoBemFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class GrupoBemFormController extends CRUDFormController<GrupoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrupoBemFormView subView;

	/** DAO'S */

	@Autowired
	private GrupoBemDAO pDAO;

	/** ENTITIES */

	private GrupoBemEntity pEntity;

	/** CONSTRUTOR */

	public GrupoBemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new GrupoBemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Grupo do bem";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String codigo = this.subView.getTfCodigo().getValue();
			String nome = this.subView.getTfNome().getValue();
			String contaAtivoImobilizado = this.subView.getTfContaAtivoImobilizado().getValue();
			String contaDepreciacaoAcumulada = this.subView.getTfContaDepreciacaoAcumulada().getValue();
			String contaDespesaDepreciacao = this.subView.getTfContaDespesaDepreciacao().getValue();
			Integer codigoHistorico = Integer.parseInt(this.subView.getTfCodigoHistorico().getValue());

			this.pEntity.setCodigo(codigo);
			this.pEntity.setNome(nome);
			this.pEntity.setContaAtivoImobilizado(contaAtivoImobilizado);
			this.pEntity.setContaDepreciacaoAcumulada(contaDepreciacaoAcumulada);
			this.pEntity.setContaDespesaDepreciacao(contaDespesaDepreciacao);
			this.pEntity.setCodigoHistorico(codigoHistorico);

			/** Empresa vinda da conta do usuário logado */

			EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/** ************************************** */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new GrupoBemEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContaAtivoImobilizado().setValue(this.pEntity.getContaAtivoImobilizado());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaDespesaDepreciacao().setValue(this.pEntity.getContaDespesaDepreciacao());
			this.subView.getTfCodigoHistorico().setValue(String.valueOf(this.pEntity.getCodigoHistorico()));
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContaAtivoImobilizado().setValue(this.pEntity.getContaAtivoImobilizado());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaDespesaDepreciacao().setValue(this.pEntity.getContaDespesaDepreciacao());
			this.subView.getTfCodigoHistorico().setValue(String.valueOf(this.pEntity.getCodigoHistorico()));
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
			this.pEntity = new GrupoBemEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContaAtivoImobilizado().setValue(this.pEntity.getContaAtivoImobilizado());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaDespesaDepreciacao().setValue(this.pEntity.getContaDespesaDepreciacao());
			this.subView.getTfCodigoHistorico().setValue(String.valueOf(this.pEntity.getCodigoHistorico()));
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new GrupoBemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new GrupoBemEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
			this.subView.getTfContaAtivoImobilizado().setValue(this.pEntity.getContaAtivoImobilizado());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaDespesaDepreciacao().setValue(this.pEntity.getContaDespesaDepreciacao());
			this.subView.getTfCodigoHistorico().setValue(String.valueOf(this.pEntity.getCodigoHistorico()));
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
		String codigo = this.subView.getTfCodigo().getValue();

		if (!ObjectValidator.validateString(codigo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfCodigo(), msg);

			return false;
		}

		String nome = this.subView.getTfNome().getValue();

		if (!ObjectValidator.validateString(nome)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNome(), msg);

			return false;
		}

		String contaAtivoImobilizado = this.subView.getTfContaAtivoImobilizado().getValue();

		if (!ObjectValidator.validateString(contaAtivoImobilizado)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfContaAtivoImobilizado(), msg);

			return false;
		}

		String contaDepreciacaoAcumulada = this.subView.getTfContaDepreciacaoAcumulada().getValue();

		if (!ObjectValidator.validateString(contaDepreciacaoAcumulada)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfContaDepreciacaoAcumulada(), msg);

			return false;
		}

		String contaDespesaDepreciacao = this.subView.getTfContaDespesaDepreciacao().getValue();

		if (!ObjectValidator.validateString(contaDespesaDepreciacao)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfContaDespesaDepreciacao(), msg);

			return false;
		}

		String codigoHistorico = this.subView.getTfCodigoHistorico().getValue();

		if (!ObjectValidator.validateInteger(codigoHistorico)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfCodigoHistorico(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getViewIdentifier() {
		return "patrimonio_grupo_bem_fc";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public GrupoBemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}