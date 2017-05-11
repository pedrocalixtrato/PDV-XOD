package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.TransporteItinerarioEntity;
import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.administrativo.empresa.TransporteItinerarioDAO;
import dc.servicos.dao.folhapagamento.movimento.ValeTransporteDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.visao.folhapagamento.movimento.ValeTransporteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ValeTransporteFormController extends
		CRUDFormController<ValeTransporteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ValeTransporteFormView subView;

	/** DAO'S */

	@Autowired
	private ValeTransporteDAO pDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	@Autowired
	private TransporteItinerarioDAO tiDAO;

	/** ENTITIES */

	private ValeTransporteEntity pEntity;

	/** CONSTRUTOR */

	public ValeTransporteFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ValeTransporteEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Vale transporte";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Integer quantidade = Integer.parseInt(this.subView
					.getTfQuantidade().getValue());

			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView
					.getCbColaborador().getValue();
			TransporteItinerarioEntity transporteItinerario = (TransporteItinerarioEntity) this.subView
					.getCbTransporteItinerario().getValue();

			this.pEntity.setQuantidade(quantidade);

			this.pEntity.setColaborador(colaborador);
			this.pEntity.setTransporteItinerario(transporteItinerario);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new ValeTransporteEntity();

			this.subView.getTfQuantidade().setValue(
					String.valueOf(this.pEntity.getQuantidade()));

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTransporteItinerario(this
					.transporteItinerarioListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTransporteItinerario().setValue(
					this.pEntity.getColaborador());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfQuantidade().setValue(
					String.valueOf(this.pEntity.getQuantidade()));

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTransporteItinerario(this
					.transporteItinerarioListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTransporteItinerario().setValue(
					this.pEntity.getColaborador());
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
			this.pEntity = new ValeTransporteEntity();

			this.subView.getTfQuantidade().setValue(
					String.valueOf(this.pEntity.getQuantidade()));

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTransporteItinerario(this
					.transporteItinerarioListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTransporteItinerario().setValue(
					this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ValeTransporteFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new ValeTransporteEntity();

			this.subView.getTfQuantidade().setValue(
					String.valueOf(this.pEntity.getQuantidade()));

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
			this.subView.carregarCmbTransporteItinerario(this
					.transporteItinerarioListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
			this.subView.getCbTransporteItinerario().setValue(
					this.pEntity.getColaborador());
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

	public List<TransporteItinerarioEntity> transporteItinerarioListarTodos() {
		List<TransporteItinerarioEntity> auxLista = new ArrayList<TransporteItinerarioEntity>();

		auxLista = this.tiDAO.listarTodos();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public ValeTransporteEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}