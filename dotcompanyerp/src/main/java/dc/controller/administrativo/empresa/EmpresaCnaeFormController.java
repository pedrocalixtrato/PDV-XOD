
package dc.controller.administrativo.empresa;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.entidade.geral.CnaeEntity;
import dc.servicos.dao.administrativo.empresa.IEmpresaCnaeDAO;
import dc.servicos.dao.geral.ICnaeDAO;
import dc.servicos.util.Validator;
import dc.visao.administrativo.empresa.EmpresaCnaeFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class EmpresaCnaeFormController extends CRUDFormController<EmpresaCnaeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmpresaCnaeFormView subView;

	private EmpresaCnaeEntity currentBean;

	@Autowired
	private IEmpresaCnaeDAO dao;

	@Autowired
	private ICnaeDAO cnaeDAO;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getCmbCnae().getValue())) {
			adicionarErroDeValidacao(subView.getCmbCnae(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getCmbPrincipal().getValue())) {
			adicionarErroDeValidacao(subView.getCmbPrincipal(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateString(subView.getTxtObjetoSocial().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObjetoSocial(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtRamoAtividade().getValue())) {
			adicionarErroDeValidacao(subView.getTxtRamoAtividade(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new EmpresaCnaeEntity();
	}

	@Override
	protected void initSubView() {
		subView = new EmpresaCnaeFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getCmbCnae().setValue(currentBean.getCnae());

		SimNaoEn principalEn = (SimNaoEn) currentBean.getPrincipal();

		subView.getCmbPrincipal().setValue(principalEn);
		subView.getTxtRamoAtividade().setValue(currentBean.getRamoAtividade());
		subView.getTxtObjetoSocial().setValue(currentBean.getObjetoSocial());
	}

	@Override
	protected void actionSalvar() {
		try {
			CnaeEntity cnae = (CnaeEntity) subView.getCmbCnae().getValue();

			SimNaoEn principalEn = (SimNaoEn) subView.getCmbPrincipal().getValue();

			// String principal = (((PRINCIPAL)
			// subView.getCmbPrincipal().getValue()).getCodigo());
			String ramoAtividade = subView.getTxtRamoAtividade().getValue();
			String objetoSocial = subView.getTxtObjetoSocial().getValue();
/*
			if (cnae == null) {
				msgErro = "Informe o CNAE!";
				throw new ErroValidacaoException(msgErro);
			}

			if (principalEn == null) {
				msgErro = "Informe Principal!";
				throw new ErroValidacaoException(msgErro);
			}

			if (ramoAtividade == null) {
				msgErro = "Informe Ramo de Atividade!";
				throw new ErroValidacaoException(msgErro);
			}

			if (objetoSocial == null) {
				msgErro = "Informe Objeto Social!";
				throw new ErroValidacaoException(msgErro);
			}
*/
			currentBean.setEmpresa(empresaAtual());
			currentBean.setCnae(cnae);

			currentBean.setPrincipal(principalEn);
			currentBean.setRamoAtividade(ramoAtividade);
			currentBean.setObjetoSocial(objetoSocial);

			dao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();

		return format;
	}

	@Override
	protected void quandoNovo() {
		try {
			// subView.filEstoqueDetalhesSubForm(currentBean.getDetalhes());
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
		return "Empresa Cnae";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<CnaeEntity> trazerListaCnae() {
		List<CnaeEntity> lista = cnaeDAO.getAll();

		return lista;
	}

	@Override
	public EmpresaCnaeEntity getModelBean() {
		return currentBean;
	}

}