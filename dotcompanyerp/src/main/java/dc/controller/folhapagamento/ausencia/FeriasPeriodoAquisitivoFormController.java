package dc.controller.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.visao.folhapagamento.ausencia.FeriasPeriodoAquisitivoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class FeriasPeriodoAquisitivoFormController extends
		CRUDFormController<FeriasPeriodoAquisitivoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FeriasPeriodoAquisitivoFormView subView;

	/** DAO'S */

	@Autowired
	private IFeriasPeriodoAquisitivoDAO pDAO;

	@Autowired
	private IColaboradorDAO cDAO;

	/** ENTITIES */

	private FeriasPeriodoAquisitivoEntity pEntity;

	/** CONSTRUTOR */

	public FeriasPeriodoAquisitivoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FeriasPeriodoAquisitivoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Férias - Período aquisitivo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			String situacao = this.subView.getTfSituacao().getValue();
			Date limiteParaGozo = this.subView.getPdfLimiteParaGozo()
					.getValue();
			String descontarFaltas = this.subView.getTfDescontarFaltas()
					.getValue();
			String desconsiderarAfastamento = this.subView
					.getTfDesconsiderarAfastamento().getValue();
			Integer afastamentoPrevidencia = Integer.parseInt(this.subView
					.getTfAfastamentoPrevidencia().getValue());
			Integer afastamentoSemRemun = Integer.parseInt(this.subView
					.getTfAfastamentoSemRemun().getValue());
			Integer afastamentoComRemun = Integer.parseInt(this.subView
					.getTfAfastamentoComRemun().getValue());
			Integer diasDireito = Integer.parseInt(this.subView
					.getTfDiasDireito().getValue());
			Integer diasGozados = Integer.parseInt(this.subView
					.getTfDiasGozados().getValue());
			Integer diasFaltas = Integer.parseInt(this.subView
					.getTfDiasFaltas().getValue());
			Integer diasRestantes = Integer.parseInt(this.subView
					.getTfDiasRestantes().getValue());
			Date dataFim = this.subView.getPdfDataFim().getValue();

			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
					.getCbColaborador().getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setSituacao(situacao);
			this.pEntity.setLimiteParaGozo(limiteParaGozo);
			this.pEntity.setDescontarFaltas(descontarFaltas);
			this.pEntity.setDesconsiderarAfastamento(desconsiderarAfastamento);
			this.pEntity.setAfastamentoPrevidencia(afastamentoPrevidencia);
			this.pEntity.setAfastamentoSemRemun(afastamentoSemRemun);
			this.pEntity.setAfastamentoComRemun(afastamentoComRemun);
			this.pEntity.setDiasDireito(diasDireito);
			this.pEntity.setDiasGozados(diasGozados);
			this.pEntity.setDiasFaltas(diasFaltas);
			this.pEntity.setDiasRestantes(diasRestantes);
			this.pEntity.setDataFim(dataFim);
			this.pEntity.setColaborador(colaborador);

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
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			novoObjeto(id);

			/*
			 * this.subView.getPdfDataInicio().setValue(
			 * this.pEntity.getDataInicio());
			 * this.subView.getTfSituacao().setValue
			 * (this.pEntity.getSituacao());
			 * this.subView.getPdfLimiteParaGozo().setValue(
			 * this.pEntity.getLimiteParaGozo());
			 * this.subView.getTfDescontarFaltas().setValue(
			 * this.pEntity.getDescontarFaltas());
			 * this.subView.getTfDesconsiderarAfastamento().setValue(
			 * this.pEntity.getDesconsiderarAfastamento());
			 * this.subView.getTfAfastamentoPrevidencia().setValue(
			 * this.pEntity.getAfastamentoPrevidencia().toString());
			 * this.subView.getTfAfastamentoSemRemun().setValue(
			 * this.pEntity.getAfastamentoSemRemun().toString());
			 * this.subView.getTfAfastamentoComRemun().setValue(
			 * this.pEntity.getAfastamentoComRemun().toString());
			 * this.subView.getTfDiasDireito().setValue(
			 * this.pEntity.getDiasDireito().toString());
			 * this.subView.getTfDiasGozados().setValue(
			 * this.pEntity.getDiasGozados().toString());
			 * this.subView.getTfDiasFaltas().setValue(
			 * this.pEntity.getDiasFaltas().toString());
			 * this.subView.getTfDiasRestantes().setValue(
			 * this.pEntity.getDiasRestantes().toString());
			 * this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			 * 
			 * this.subView.carregarCmbColaborador(this.colaboradorListarTodos())
			 * ;
			 * 
			 * this.subView.getCbColaborador().setValue(
			 * this.pEntity.getColaborador());
			 */
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
			novoObjeto(0);

			/*
			 * this.pEntity = new FeriasPeriodoAquisitivoEntity();
			 * 
			 * this.subView.getPdfDataInicio().setValue(
			 * this.pEntity.getDataInicio());
			 * this.subView.getTfSituacao().setValue
			 * (this.pEntity.getSituacao());
			 * this.subView.getPdfLimiteParaGozo().setValue(
			 * this.pEntity.getLimiteParaGozo());
			 * this.subView.getTfDescontarFaltas().setValue(
			 * this.pEntity.getDescontarFaltas());
			 * this.subView.getTfDesconsiderarAfastamento().setValue(
			 * this.pEntity.getDesconsiderarAfastamento());
			 * this.subView.getTfAfastamentoPrevidencia().setValue(
			 * this.pEntity.getAfastamentoPrevidencia().toString());
			 * this.subView.getTfAfastamentoSemRemun().setValue(
			 * this.pEntity.getAfastamentoSemRemun().toString());
			 * this.subView.getTfAfastamentoComRemun().setValue(
			 * this.pEntity.getAfastamentoComRemun().toString());
			 * this.subView.getTfDiasDireito().setValue(
			 * this.pEntity.getDiasDireito().toString());
			 * this.subView.getTfDiasGozados().setValue(
			 * this.pEntity.getDiasGozados().toString());
			 * this.subView.getTfDiasFaltas().setValue(
			 * this.pEntity.getDiasFaltas().toString());
			 * this.subView.getTfDiasRestantes().setValue(
			 * this.pEntity.getDiasRestantes().toString());
			 * this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			 * 
			 * this.subView.carregarCmbColaborador(this.colaboradorListarTodos())
			 * ;
			 * 
			 * this.subView.getCbColaborador().setValue(
			 * this.pEntity.getColaborador());
			 */
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new FeriasPeriodoAquisitivoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {


			novoObjeto(0);

			/*
			 * this.pEntity = new FeriasPeriodoAquisitivoEntity();
			 * 
			 * this.subView.getPdfDataInicio().setValue(
			 * this.pEntity.getDataInicio());
			 * this.subView.getTfSituacao().setValue
			 * (this.pEntity.getSituacao());
			 * this.subView.getPdfLimiteParaGozo().setValue(
			 * this.pEntity.getLimiteParaGozo());
			 * this.subView.getTfDescontarFaltas().setValue(
			 * this.pEntity.getDescontarFaltas());
			 * this.subView.getTfDesconsiderarAfastamento().setValue(
			 * this.pEntity.getDesconsiderarAfastamento());
			 * this.subView.getTfAfastamentoPrevidencia().setValue(
			 * this.pEntity.getAfastamentoPrevidencia().toString());
			 * this.subView.getTfAfastamentoSemRemun().setValue(
			 * this.pEntity.getAfastamentoSemRemun().toString());
			 * this.subView.getTfAfastamentoComRemun().setValue(
			 * this.pEntity.getAfastamentoComRemun().toString());
			 * this.subView.getTfDiasDireito().setValue(
			 * this.pEntity.getDiasDireito().toString());
			 * this.subView.getTfDiasGozados().setValue(
			 * this.pEntity.getDiasGozados().toString());
			 * this.subView.getTfDiasFaltas().setValue(
			 * this.pEntity.getDiasFaltas().toString());
			 * this.subView.getTfDiasRestantes().setValue(
			 * this.pEntity.getDiasRestantes().toString());
			 * this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());
			 * 
			 * this.subView.carregarCmbColaborador(this.colaboradorListarTodos())
			 * ;
			 * 
			 * this.subView.getCbColaborador().setValue(
			 * this.pEntity.getColaborador());
			 */
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

	/** COMBOS */

	public List<ColaboradorEntity> colaboradorListarTodos() {
		List<ColaboradorEntity> auxLista = new ArrayList<ColaboradorEntity>();

		auxLista = this.cDAO.colaboradorLista();

		return auxLista;
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
				this.pEntity = new FeriasPeriodoAquisitivoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getTfSituacao().setValue(this.pEntity.getSituacao());
			this.subView.getPdfLimiteParaGozo().setValue(
					this.pEntity.getLimiteParaGozo());
			this.subView.getTfDescontarFaltas().setValue(
					this.pEntity.getDescontarFaltas());
			this.subView.getTfDesconsiderarAfastamento().setValue(
					this.pEntity.getDesconsiderarAfastamento());
			this.subView.getTfAfastamentoPrevidencia().setValue(
					this.pEntity.getAfastamentoPrevidencia().toString());
			this.subView.getTfAfastamentoSemRemun().setValue(
					this.pEntity.getAfastamentoSemRemun().toString());
			this.subView.getTfAfastamentoComRemun().setValue(
					this.pEntity.getAfastamentoComRemun().toString());
			this.subView.getTfDiasDireito().setValue(
					this.pEntity.getDiasDireito().toString());
			this.subView.getTfDiasGozados().setValue(
					this.pEntity.getDiasGozados().toString());
			this.subView.getTfDiasFaltas().setValue(
					this.pEntity.getDiasFaltas().toString());
			this.subView.getTfDiasRestantes().setValue(
					this.pEntity.getDiasRestantes().toString());
			this.subView.getPdfDataFim().setValue(this.pEntity.getDataFim());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FeriasPeriodoAquisitivoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}