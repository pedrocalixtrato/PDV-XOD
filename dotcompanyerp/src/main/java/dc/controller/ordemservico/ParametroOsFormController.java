package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.ParametroOsEntity;
import dc.servicos.dao.ordemservico.IParametroOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ParametroOsFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ParametroOsFormController extends CRUDFormController<ParametroOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IParametroOsDAO dao;

	private ParametroOsEntity currentBean;

	ParametroOsFormView subView;

	@Override
	protected String getNome() {
		return "Parâmetro de ordem de serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {

			boolean valido = validaSalvar();

			if (valido) {
				currentBean.setLimparBdAut(subView.getCbLimparBD().getValue());
				currentBean.setVendedorProduto(subView.getCbVendedorProduto().getValue());
				currentBean.setDescontoGeral(subView.getCbDesconto().getValue());
				currentBean.setTecnicoProduto(subView.getCbTecnicoProduto().getValue());
				currentBean.setVendedorAtendente(subView.getCbVendedorAtendente().getValue());
				currentBean.setQtdDiasRevisao(subView.getCbProximaRevisao().getValue());
				currentBean.setValorPagoPeca(Boolean.valueOf(subView.getCbValorPagoPeca().getValue().toString()));
				currentBean.setVendedorServico(Boolean.valueOf(subView.getCbVendedorServico().getValue().toString()));
				currentBean.setQtdDiasPadrao(Integer.valueOf(subView.getTfDiasPadrao().getValue()));
				currentBean.setObsDefeitoPadrao(subView.getTaDefeitoApresentado().getValue());
				currentBean.setObsPadraoOsSimples(subView.getTaObsPadraoSimpes().getValue());
				currentBean.setObsPadrao(subView.getTaObsPadrao().getValue());
				currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());

				dao.saveOrUpdate(currentBean);
				notifiyFrameworkSaveOK(this.currentBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);

		subView.getCbLimparBD().setValue(currentBean.getLimparBdAut());
		subView.getCbVendedorProduto().setValue(currentBean.getVendedorProduto());
		subView.getCbValorPagoPeca().setValue(currentBean.getValorPagoPeca());
		subView.getCbDesconto().setValue(currentBean.getDescontoGeral());
		subView.getCbTecnicoProduto().setValue(currentBean.getTecnicoProduto());
		subView.getCbVendedorAtendente().setValue(currentBean.getVendedorAtendente());
		subView.getCbVendedorServico().setValue(currentBean.getVendedorServico());
		subView.getCbProximaRevisao().setValue(currentBean.getQtdDiasRevisao());
		subView.getTfDiasPadrao().setValue(currentBean.getQtdDiasPadrao().toString());
		subView.getTaDefeitoApresentado().setValue(currentBean.getObsDefeitoPadrao());
		subView.getTaObsPadraoSimpes().setValue(currentBean.getObsPadraoOsSimples());
		subView.getTaObsPadrao().setValue(currentBean.getObsPadrao());
	}

	@Override
	protected void initSubView() {
		subView = new ParametroOsFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParametroOsEntity();

	}

	@Override
	public String getViewIdentifier() {
		return "parametroOsForm";
	}

	@Override
	protected boolean validaSalvar() {
		System.out.println("Chegou no valida salvar");

		boolean valido = true;

		if (!Validator.validateObject(subView.getCbLimparBD().getValue())) {
			valido = false;
			mensagemErro("O campo limpar banco de dados automaticamente não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorAtendente().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor\\atendente não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorProduto().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor no lançamento de produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbValorPagoPeca().getValue())) {
			valido = false;
			mensagemErro("O campo usar campo valor pago na peça\\produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbDesconto().getValue())) {
			valido = false;
			mensagemErro("O campo usar desconto geral não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbTecnicoProduto().getValue())) {
			valido = false;
			mensagemErro("O campo usar técnico no lançamento de produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorServico().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor no lançamento de serviço não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbProximaRevisao().getValue())) {
			valido = false;
			mensagemErro("O campo perguntar quantidade dias próxima revisão não pode ficar em branco.");
		}

		return valido;
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			ParametroOsEntity parametroos = (ParametroOsEntity) id;

			try {
				dao.delete(parametroos);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public ParametroOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
