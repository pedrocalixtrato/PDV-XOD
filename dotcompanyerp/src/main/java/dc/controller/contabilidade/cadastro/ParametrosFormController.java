package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.servicos.dao.contabilidade.cadastro.IParametrosDAO;
import dc.visao.contabilidade.cadastro.ParametrosFormView;
import dc.visao.framework.geral.CRUDFormController;


@Controller()
@Scope("prototype")
public class ParametrosFormController extends
		CRUDFormController<ParametrosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParametrosFormView subView;

	/** DAO'S */

	@Autowired
	private IParametrosDAO pDAO;

	/** ENTITIES */

	private ParametrosEntity pEntity;

	/** CONSTRUTOR */

	public ParametrosFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ParametrosEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Parâmetro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String mascara = this.subView.getTfMascara().getValue();
			Integer niveis = Integer.parseInt(this.subView.getTfNiveis()
					.getValue());
			
			SimNaoEn informarContaPor = (SimNaoEn) this.subView
					.getTfInformarContaPor().getValue();
			
			SimNaoEn compartilhaPlanoConta = (SimNaoEn) this.subView
					.getTfCompartilhaPlanoConta().getValue();
			
			SimNaoEn compartilhaHistoricos = (SimNaoEn) this.subView
					.getTfCompartilhaHistoricos().getValue();
			
			SimNaoEn alteraLancamentoOutro = (SimNaoEn) this.subView
					.getTfAlteraLancamentoOutro().getValue();
			
			SimNaoEn historicoObrigatorio = (SimNaoEn) this.subView
					.getTfHistoricoObrigatorio().getValue();
			
			SimNaoEn permiteLancamentoZerado = (SimNaoEn) this.subView
					.getTfPermiteLancamentoZerado().getValue();
			
			SimNaoEn geraInformativoSped = (SimNaoEn) this.subView
					.getTfGeraInformativoSped().getValue();
			
			String spedFormaEscritDiario = this.subView
					.getTfSpedFormaEscritDiario().getValue();
			String spedNomeLivroDiario = this.subView
					.getTfSpedNomeLivroDiario().getValue();
			String assinaturaDireita = this.subView.getTfAssinaturaDireita()
					.getValue();
			String assinaturaEsquerda = this.subView.getTfAssinaturaEsquerda()
					.getValue();
			String contaAtivo = this.subView.getTfContaAtivo().getValue();
			String contaPassivo = this.subView.getTfContaPassivo().getValue();
			String contaPatrimonioLiquido = this.subView
					.getTfContaPatrimonioLiquido().getValue();
			String contaDepreciacaoAcumulada = this.subView
					.getTfContaDepreciacaoAcumulada().getValue();
			String contaCapitalSocial = this.subView.getTfContaCapitalSocial()
					.getValue();
			String contaResultadoExercicio = this.subView
					.getTfContaResultadoExercicio().getValue();
			String contaPrejuizoAcumulado = this.subView
					.getTfContaPrejuizoAcumulado().getValue();
			String contaLucroAcumulado = this.subView
					.getTfContaLucroAcumulado().getValue();
			String contaTituloPagar = this.subView.getTfContaTituloPagar()
					.getValue();
			String contaTituloReceber = this.subView.getTfContaTituloReceber()
					.getValue();
			String contaJurosPassivo = this.subView.getTfContaJurosPassivo()
					.getValue();
			String contaJurosAtivo = this.subView.getTfContaJurosAtivo()
					.getValue();
			String contaDescontoObtido = this.subView
					.getTfContaDescontoObtido().getValue();
			String contaDescontoConcedido = this.subView
					.getTfContaDescontoConcedido().getValue();
			String contaCmv = this.subView.getTfContaCmv().getValue();
			String contaVenda = this.subView.getTfContaVenda().getValue();
			String contaVendaServico = this.subView.getTfContaVendaServico()
					.getValue();
			String contaEstoque = this.subView.getTfContaEstoque().getValue();
			String contaApuraResultado = this.subView
					.getTfContaApuraResultado().getValue();
			String contaJurosApropriar = this.subView
					.getTfContaJurosApropriar().getValue();

			this.pEntity.setMascara(mascara);
			this.pEntity.setNiveis(niveis);
			this.pEntity.setInformarContaPor(informarContaPor);
			this.pEntity.setCompartilhaPlanoConta(compartilhaPlanoConta);
			this.pEntity.setCompartilhaHistoricos(compartilhaHistoricos);
			this.pEntity.setAlteraLancamentoOutro(alteraLancamentoOutro);
			this.pEntity.setHistoricoObrigatorio(historicoObrigatorio);
			this.pEntity.setPermiteLancamentoZerado(permiteLancamentoZerado);
			this.pEntity.setGeraInformativoSped(geraInformativoSped);
			this.pEntity.setSpedFormaEscritDiario(spedFormaEscritDiario);
			this.pEntity.setSpedNomeLivroDiario(spedNomeLivroDiario);
			this.pEntity.setAssinaturaDireita(assinaturaDireita);
			this.pEntity.setAssinaturaEsquerda(assinaturaEsquerda);
			this.pEntity.setContaAtivo(contaAtivo);
			this.pEntity.setContaPassivo(contaPassivo);
			this.pEntity.setContaPatrimonioLiquido(contaPatrimonioLiquido);
			this.pEntity
					.setContaDepreciacaoAcumulada(contaDepreciacaoAcumulada);
			this.pEntity.setContaCapitalSocial(contaCapitalSocial);
			this.pEntity.setContaResultadoExercicio(contaResultadoExercicio);
			this.pEntity.setContaPrejuizoAcumulado(contaPrejuizoAcumulado);
			this.pEntity.setContaLucroAcumulado(contaLucroAcumulado);
			this.pEntity.setContaTituloPagar(contaTituloPagar);
			this.pEntity.setContaTituloReceber(contaTituloReceber);
			this.pEntity.setContaJurosPassivo(contaJurosPassivo);
			this.pEntity.setContaJurosAtivo(contaJurosAtivo);
			this.pEntity.setContaDescontoObtido(contaDescontoObtido);
			this.pEntity.setContaDescontoConcedido(contaDescontoConcedido);
			this.pEntity.setContaCmv(contaCmv);
			this.pEntity.setContaVenda(contaVenda);
			this.pEntity.setContaVendaServico(contaVendaServico);
			this.pEntity.setContaEstoque(contaEstoque);
			this.pEntity.setContaApuraResultado(contaApuraResultado);
			this.pEntity.setContaJurosApropriar(contaJurosApropriar);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ParametrosFormView(this);

		popularCombo();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		pEntity = new ParametrosEntity();

		/*
		 * try { novoObjeto(0); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		String niveis = this.subView.getTfNiveis().getValue();

		if (!ObjectValidator.validateNotRequiredInteger(niveis)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNiveis(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	private void popularCombo() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new ParametrosEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfMascara().setValue(this.pEntity.getMascara());
			this.subView.getTfNiveis().setValue(
					this.pEntity.getNiveis().toString());
			this.subView.getTfInformarContaPor().setValue(
					this.pEntity.getInformarContaPor());
			this.subView.getTfCompartilhaPlanoConta().setValue(
					this.pEntity.getCompartilhaPlanoConta());
			this.subView.getTfCompartilhaHistoricos().setValue(
					this.pEntity.getCompartilhaHistoricos());
			this.subView.getTfAlteraLancamentoOutro().setValue(
					this.pEntity.getAlteraLancamentoOutro());
			this.subView.getTfHistoricoObrigatorio().setValue(
					this.pEntity.getHistoricoObrigatorio());
			this.subView.getTfPermiteLancamentoZerado().setValue(
					this.pEntity.getPermiteLancamentoZerado());
			this.subView.getTfGeraInformativoSped().setValue(
					this.pEntity.getGeraInformativoSped());
			this.subView.getTfSpedFormaEscritDiario().setValue(
					this.pEntity.getSpedFormaEscritDiario());
			this.subView.getTfSpedNomeLivroDiario().setValue(
					this.pEntity.getSpedNomeLivroDiario());
			this.subView.getTfAssinaturaDireita().setValue(
					this.pEntity.getAssinaturaDireita());
			this.subView.getTfAssinaturaEsquerda().setValue(
					this.pEntity.getAssinaturaEsquerda());
			this.subView.getTfContaAtivo().setValue(
					this.pEntity.getContaAtivo());
			this.subView.getTfContaPassivo().setValue(
					this.pEntity.getContaPassivo());
			this.subView.getTfContaPatrimonioLiquido().setValue(
					this.pEntity.getContaPatrimonioLiquido());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(
					this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaCapitalSocial().setValue(
					this.pEntity.getContaCapitalSocial());
			this.subView.getTfContaResultadoExercicio().setValue(
					this.pEntity.getContaResultadoExercicio());
			this.subView.getTfContaPrejuizoAcumulado().setValue(
					this.pEntity.getContaPrejuizoAcumulado());
			this.subView.getTfContaLucroAcumulado().setValue(
					this.pEntity.getContaLucroAcumulado());
			this.subView.getTfContaTituloPagar().setValue(
					this.pEntity.getContaTituloPagar());
			this.subView.getTfContaTituloReceber().setValue(
					this.pEntity.getContaTituloReceber());
			this.subView.getTfContaJurosPassivo().setValue(
					this.pEntity.getContaJurosPassivo());
			this.subView.getTfContaJurosAtivo().setValue(
					this.pEntity.getContaJurosAtivo());
			this.subView.getTfContaDescontoObtido().setValue(
					this.pEntity.getContaDescontoObtido());
			this.subView.getTfContaDescontoConcedido().setValue(
					this.pEntity.getContaDescontoConcedido());
			this.subView.getTfContaCmv().setValue(this.pEntity.getContaCmv());
			this.subView.getTfContaVenda().setValue(
					this.pEntity.getContaVenda());
			this.subView.getTfContaVendaServico().setValue(
					this.pEntity.getContaVendaServico());
			this.subView.getTfContaEstoque().setValue(
					this.pEntity.getContaEstoque());
			this.subView.getTfContaApuraResultado().setValue(
					this.pEntity.getContaApuraResultado());
			this.subView.getTfContaJurosApropriar().setValue(
					this.pEntity.getContaJurosApropriar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ParametrosEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}