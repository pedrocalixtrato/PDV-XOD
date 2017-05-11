package dc.controller.administrativo.seguranca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.model.business.administrativo.seguranca.UsuarioBusiness;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.servicos.dao.sistema.IPapelDAO;
import dc.visao.administrativo.seguranca.UsuarioFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class UsuarioFormController extends CRUDFormController<UsuarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UsuarioFormView subView;

	/**
	 * ENTITY
	 */

	private UsuarioEntity entity = new UsuarioEntity();

	/**
	 * BUSINESS
	 */

	@Autowired
	private UsuarioBusiness<UsuarioEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private IColaboradorDAO colaboradorDAO;

	@Autowired
	private IPessoaDAO pessoaDAO;

	@Autowired
	private IPapelDAO papelDAO;

	/**
	 * CONSTRUTOR
	 */

	public UsuarioFormController() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioBusiness<UsuarioEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Usuário";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public UsuarioEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UsuarioFormView(this);

			ManyToOneComboModel<ColaboradorEntity> colaboradorModel = new DefaultManyToOneComboModel<ColaboradorEntity>(
					ColaboradorListController.class, this.colaboradorDAO,
					this.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "pessoa.nome";
				}

			};

			this.subView.getComboColaborador().setModel(colaboradorModel);

			ManyToOneComboModel<PapelEntity> papelModel = new DefaultManyToOneComboModel<PapelEntity>(
					PapelListController.class, this.papelDAO,
					this.getMainController());

			this.subView.getComboPapeis().setModel(papelModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		boolean retornoValidacao = true;

		try {
			subView.getBinder().commit();

			UsuarioEntity u = subView.getBinder().getItemDataSource().getBean();

			entity = u;

			u.setColaborador(subView.getComboColaborador().getValue());
			u.setPapel(subView.getComboPapeis().getValue());

			if (u.getColaborador() == null) {
				adicionarErroDeValidacao(subView.getComboColaborador(),
						"O Usuário deve estar associado a um colaborador");
				retornoValidacao = false;
			}

			if (u.getPapel() == null) {
				adicionarErroDeValidacao(subView.getComboPapeis(),
						"O Usuário deve estar associado a um papel");
				retornoValidacao = false;
			}

			if (u.getLogin() == null || u.getLogin().isEmpty()) {
				adicionarErroDeValidacao(subView.getLoginTxtField(),
						"Não pode ficar em branco");
				retornoValidacao = false;
			}

			if (u.getSenha() == null || u.getSenha().isEmpty()) {
				adicionarErroDeValidacao(subView.getSenhaPasswordField(),
						"Não pode ficar em branco");
				retornoValidacao = false;
			}
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornoValidacao;
	}

	@Override
	protected void actionSalvar() {
		try {
			ContaEmpresa conta = SecuritySessionProvider.getUsuario()
					.getConta();

			this.entity.setConta(conta);
			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro("Problemas ao salvar registro");
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView
					.carregaDataCadastro(getCurrentBean().getDataCadastro());
			this.subView.getBinder().setItemDataSource(getCurrentBean());
			this.subView.getComboColaborador().setValue(
					this.entity.getColaborador());
			this.subView.getComboPapeis().setValue(this.entity.getPapel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			getCurrentBean().setConfirmado(false);
			this.subView.getBinder().discard();
			this.subView.getBinder().setItemDataSource(getCurrentBean());
			this.subView
					.carregaDataCadastro(getCurrentBean().getDataCadastro());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new UsuarioEntity();
			this.entity.setDataCadastro(new Date());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	public UsuarioEntity getCurrentBean() {
		return entity;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				UsuarioEntity us = (UsuarioEntity) id;

				try {
					business.delete(us);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	public void alteraFormBaseadoEmColaborador(ColaboradorEntity colaborador) {
		if (isNovo()) {
			// dados padrão primeiro cadastro
			getCurrentBean().setDataCadastro(new Date());
			PessoaEntity p = colaborador.getPessoa();
			PessoaFisicaEntity pf = this.pessoaDAO.getPessoaFisica(p.getId());

			if (pf != null) {
				this.subView.getLoginTxtField().setValue(pf.getCpf());

				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

				if (pf.getDataNascimento() != null) {
					String sDate = sdf.format(pf.getDataNascimento());

					this.subView.getSenhaPasswordField().setValue(sDate);
				}
			}
		}
	}

}