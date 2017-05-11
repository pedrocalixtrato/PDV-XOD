package dc.controller.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.contabilidade.planoconta.PlanoContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.ordemservico.ColaboradorOsEntity;
import dc.entidade.ordemservico.TipoColaboradorOsEntity;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.ordemservico.IColaboradorOsDAO;
import dc.servicos.dao.ordemservico.ITipoColaboradorOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ColaboradorOsFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class ColaboradorOsFormController extends CRUDFormController<ColaboradorOsEntity> {

	private static final long serialVersionUID = 1L;

	private ColaboradorOsFormView subView;

	@Autowired
	private IColaboradorOsDAO colaboradorOsDAO;

	@Autowired
	private ITipoColaboradorOsDAO tipoColaboradorOsDAO;

	@Autowired
	private IPlanoContaDAO planoContaDAO;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;

	private ColaboradorOsEntity currentBean;

	@Override
	protected String getNome() {
		return "Colaborador O.S";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTfNome().getValue());
		currentBean.setFilial(Integer.valueOf(subView.getTfFilial().getValue()));
		if (subView.getPdfDataAdmissao() != null) {
			currentBean.setDataAdmissao(subView.getPdfDataAdmissao().getValue());
		}
		if (subView.getPdfDataDemissao() != null) {
			currentBean.setDataDemissao(subView.getPdfDataDemissao().getValue());
		}

		if (subView.getPdfDataNascimento() != null) {
			currentBean.setDataNascimento(subView.getPdfDataNascimento().getValue());
		}

		if (subView.getCbTipoColaborador() != null) {
			currentBean.setTipoColaboradorOs(subView.getCbTipoColaborador().getValue());
		}
		currentBean.setCpfCnpj(subView.getTfCpfCnpj().getValue());
		currentBean.setRgInscEstadual(subView.getTfRgInscEstadual().getValue());
		currentBean.setTelefone(subView.getTfTelefone().getValue());
		currentBean.setCelular(subView.getTfCelular().getValue());
		currentBean.setCep(subView.getTfCep().getValue());
		currentBean.setEndereco(subView.getTfEndereco().getValue());
		currentBean.setBairro(subView.getTfBairro().getValue());
		currentBean.setCidade(subView.getTfCidade().getValue());
		currentBean.setUf(subView.getTfUf().getValue());
		currentBean.setEmail(subView.getTfEmail().getValue());

		if (subView.getTfSalarioFixo() != null) {
			currentBean.setSalarioFixo((BigDecimal) subView.getTfSalarioFixo().getConvertedValue());
		}

		currentBean.setPriorizarComissao(Boolean.valueOf(subView.getCbPriorizarPgto().getValue().toString()));
		currentBean.setComissaoOver(Boolean.valueOf(subView.getCbComissaoOver().getValue().toString()));
		currentBean.setTipoComissaoServico((String) subView.getOptTipoComissaoServico().getValue());
		currentBean.setTipoComissaoProduto((String) subView.getOptTipoComissaoProduto().getValue());

		if (subView.getTfComissaoProduto() != null) {
			currentBean.setValorComissaoProduto((BigDecimal) subView.getTfComissaoProduto().getConvertedValue());
		}

		if (subView.getTfComissaoServico() != null) {
			currentBean.setValorComissaoServico((BigDecimal) subView.getTfComissaoServico().getConvertedValue());
		}

		if (subView.getCbPgtoComissao().getValue() != null) {
			currentBean.setPgtoComissaoSera(Integer.valueOf(subView.getCbPgtoComissao().getValue().toString()));
		}
		if (subView.getCbLctoComissao().getValue() != null) {
			currentBean.setLctoComissao(Integer.valueOf(subView.getCbLctoComissao().getValue().toString()));
		}
		if (subView.getCbContaCaixa() != null) {
			currentBean.setContaCaixa(subView.getCbContaCaixa().getValue());
		}
		if (subView.getCbPlanoConta() != null) {
			currentBean.setPlanoConta(subView.getCbPlanoConta().getValue());
		}
		try {
			colaboradorOsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = colaboradorOsDAO.find(id);

		if (currentBean != null) {
			subView.getTfNome().setValue(currentBean.getNome());
			subView.getTfFilial().setValue(currentBean.getFilial().toString());
			subView.getCbTipoColaborador().setValue(currentBean.getTipoColaboradorOs());
			subView.getPdfDataAdmissao().setValue(currentBean.getDataAdmissao());
			subView.getPdfDataDemissao().setValue(currentBean.getDataDemissao());
			subView.getPdfDataNascimento().setValue(currentBean.getDataNascimento());
			subView.getTfCpfCnpj().setValue(currentBean.getCpfCnpj());
			subView.getTfRgInscEstadual().setValue(currentBean.getRgInscEstadual());
			subView.getTfTelefone().setValue(currentBean.getTelefone());
			subView.getTfCelular().setValue(currentBean.getCelular());
			subView.getTfCep().setValue(currentBean.getCep());
			subView.getTfEndereco().setValue(currentBean.getEndereco());
			subView.getTfBairro().setValue(currentBean.getBairro());
			subView.getTfCidade().setValue(currentBean.getCidade());
			subView.getTfUf().setValue(currentBean.getUf());
			subView.getTfEmail().setValue(currentBean.getEmail());
			subView.getTfEmail().setValue(currentBean.getEmail());
			subView.getCbPriorizarPgto().setValue(currentBean.getPriorizarComissao());
			subView.getCbComissaoOver().setValue(currentBean.getComissaoOver());

			BigDecimal salarioFixo = currentBean.getSalarioFixo();
			if (salarioFixo != null) {
				subView.getTfSalarioFixo().setConvertedValue(salarioFixo);
				;
			}
			subView.getOptTipoComissaoServico().setValue(currentBean.getTipoComissaoServico());
			subView.getOptTipoComissaoProduto().setValue(currentBean.getTipoComissaoProduto());

			BigDecimal comissaoServico = currentBean.getValorComissaoServico();
			if (comissaoServico != null) {
				subView.getTfComissaoServico().setConvertedValue(comissaoServico);
				;
			}
			BigDecimal comissaoProduto = currentBean.getValorComissaoProduto();
			if (comissaoProduto != null) {
				subView.getTfComissaoProduto().setConvertedValue(comissaoProduto);
				;
			}
			subView.getCbPgtoComissao().setValue(currentBean.getPgtoComissaoSera());
			subView.getCbLctoComissao().setValue(currentBean.getLctoComissao());
			subView.getCbContaCaixa().setValue(currentBean.getContaCaixa());
			subView.getCbPlanoConta().setValue(currentBean.getPlanoConta());
		}

	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new ColaboradorOsFormView();

		preencheCombos();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new ColaboradorOsEntity();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<TipoColaboradorOsEntity> tipo = new DefaultManyToOneComboModel<TipoColaboradorOsEntity>(TipoColaboradorOsListController.class,
				this.tipoColaboradorOsDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCbTipoColaborador().setModel(tipo);

		DefaultManyToOneComboModel<PlanoContaEntity> planoConta = new DefaultManyToOneComboModel<PlanoContaEntity>(PlanoContaListController.class,
				this.planoContaDAO, super.getMainController());

		this.subView.getCbPlanoConta().setModel(planoConta);

		DefaultManyToOneComboModel<ContaCaixa> contaCaixa = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(contaCaixa);
	}

	@Override
	protected void remover(List<Serializable> ids) {
		colaboradorOsDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTfNome().getValue())) {
			adicionarErroDeValidacao(subView.getTfNome(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "colaboradorOsForm";
	}

	public String formataMoeda(String valor) {
		String format = "";
		System.out.println("valor.replace: " + valor.replace("R$", "").substring(0, valor.indexOf(",")));
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).replaceAll(",", ".").trim();
		return format;
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(",", ".");
		return format;
	}

	@Override
	public ColaboradorOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
