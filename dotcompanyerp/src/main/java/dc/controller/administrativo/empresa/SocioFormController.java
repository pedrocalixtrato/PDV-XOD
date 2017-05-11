package dc.controller.administrativo.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.DependenteEntity;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.empresa.ParticipacaoSocietariaEntity;
import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.servicos.dao.administrativo.empresa.IDependenteDAO;
import dc.servicos.dao.administrativo.empresa.IParticipacaoSocietariaDAO;
import dc.servicos.dao.administrativo.empresa.IQuadroSocietarioDAO;
import dc.servicos.dao.administrativo.empresa.ISocioDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.pessoal.ITipoRelacionamentoDAO;
import dc.visao.administrativo.empresa.SocioFormView;
import dc.visao.administrativo.empresa.SocioFormView.DIRIGENTE;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SocioFormController extends CRUDFormController<SocioEntity> {

	private SocioFormView subView;

	@Autowired
	private ISocioDAO dao;

	private SocioEntity currentBean;

	@Autowired
	private ITipoRelacionamentoDAO tipoRelacionamentoDAO;

	@Autowired
	private  IQuadroSocietarioDAO quadroSocietarioDAO;

	@Autowired
	private IParticipacaoSocietariaDAO participacaoSocietariaDAO;

	@Autowired
	private IUfDAO ufDAO;

	@Autowired
	private IDependenteDAO dependenteDAO;

	@Override
	public String getViewIdentifier() {
		return "socioForm";
	}

@Override
protected boolean validaSalvar() {
	try {
       // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
        fieldGroup.commit();
        return true;
    } catch (FieldGroup.CommitException ce) {
        return false;
    }
}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new SocioEntity();
			
			// Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
		      e.printStackTrace();
		       mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new SocioFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(SocioEntity.class);
			
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			fieldGroup.bind(this.subView.getTxtLogradouro(),"logradouro");
			fieldGroup.bind(this.subView.getTxtComplemento(),"complemento");
			fieldGroup.bind(this.subView.getTxtBairro(),"bairro");
			
			fieldGroup.bind(this.subView.getCmbQuadroSocietario(),"quadroSocietario");
			
			this.subView.getCmbQuadroSocietario().configuraCombo(
					"quantidadeCotas", QuadroSocietarioListController.class, this.quadroSocietarioDAO, this.getMainController());
			
			carregarUf();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void carregar(Serializable id) {

		try {
			
			currentBean = dao.find((Integer) id);
			List<DependenteEntity> dependentes = dependenteDAO.findBySocio(currentBean);
			List<ParticipacaoSocietariaEntity> participacoes = participacaoSocietariaDAO.findBySocio(currentBean);
			fieldGroup.setItemDataSource(this.currentBean);
			if (dependentes != null) {
				subView.preencherSubFormDependente(dependentes);
			}

			if (participacoes != null) {

				for (ParticipacaoSocietariaEntity p : participacoes) {

					if (p.getDirigente() != null && !(p.getDirigente().isEmpty()))
						p.setDirigenteEnum(DIRIGENTE.getDirigente(p.getDirigente()));
				}

				subView.preencherSubFormParticipacao(participacoes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {

		try {
			dao.saveOrUpdate(currentBean);

			/*List<ParticipacaoSocietariaEntity> participacoes = subView.getParticipacoesSubForm().getDados();
			for (ParticipacaoSocietariaEntity p : participacoes) {

				p.setDirigente(p.getDirigenteEnum().getCodigo());
				participacaoSocietariaDAO.saveOrUpdate(p);
			}*/

			notifiyFrameworkSaveOK(currentBean);

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
		return "Sócio";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			SocioEntity socio = (SocioEntity) id;

			try {
				dao.delete(socio);
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
	
	public List<TipoRelacionamentoEntity> buscarRelacionamentos() {
		return tipoRelacionamentoDAO.getAll(TipoRelacionamentoEntity.class);
	}


	public List<TipoRelacionamentoEntity> carregarTipoRelacionamento() {
		List<TipoRelacionamentoEntity> lista = new ArrayList<TipoRelacionamentoEntity>();
		for (TipoRelacionamentoEntity tipo : tipoRelacionamentoDAO.getAll()) {
			lista.add(tipo);
		}
		return lista;
	}

	public List<QuadroSocietarioEntity> listarQuadros() {
		return quadroSocietarioDAO.getAll();
	}

	public BeanItemContainer<QuadroSocietarioEntity> carregarQuadros() {
		BeanItemContainer<QuadroSocietarioEntity> container = new BeanItemContainer<>(QuadroSocietarioEntity.class);
		for (QuadroSocietarioEntity p : listarQuadros()) {
			container.addItem(p);
		}
		return container;

	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	@Override
	public SocioEntity getModelBean() {
		return currentBean;
	}
	
	public ParticipacaoSocietariaEntity adicionarParticipacao() {
		ParticipacaoSocietariaEntity participacao = new ParticipacaoSocietariaEntity();
		this.currentBean.addParticipacao(participacao);

		return participacao;
	}

	public void removerParticipacao(List<ParticipacaoSocietariaEntity> values) {
		for (ParticipacaoSocietariaEntity participacao : values) {
			this.currentBean.removeParticipacao(participacao);
		}

		mensagemRemovidoOK();

	}
	
	public DependenteEntity adicionarDependente() {
		DependenteEntity depe = new DependenteEntity();
		this.currentBean.addDependente(depe);

		return depe;
	}

	public void removerDependente(List<DependenteEntity> values) {
		for (DependenteEntity depe : values) {
			this.currentBean.removeDependente(depe);
		}

		mensagemRemovidoOK();

	}
	
	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.getAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getCmbUF().setContainerDataSource(bic);
			this.subView.getCmbUF().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}
