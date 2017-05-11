package dc.controller.geral.eventos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.CrtEn;
import dc.control.enums.FormaDescontoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoFreteEn;
import dc.control.enums.TipoPessoaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.enums.TipoSanguineoEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.controller.geral.pessoal.AtividadeForCliListController;
import dc.controller.geral.pessoal.EstadoCivilListController;
import dc.controller.geral.pessoal.SituacaoForCliListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.pessoal.EstadoCivilBusiness;
import dc.model.business.geral.pessoal.PessoaBusiness;
import dc.model.business.geral.pessoal.PessoaContatoBusiness;
import dc.model.business.geral.pessoal.PessoaEnderecoBusiness;
import dc.model.dao.geral.diverso.ISetorDAO;
import dc.model.dao.geral.outro.ISindicatoDAO;
import dc.model.dao.geral.pessoal.ICargoDAO;
import dc.model.dao.geral.pessoal.IEstadoCivilDAO;
import dc.model.dao.geral.pessoal.IPessoaContatoDAO;
import dc.servicos.dao.contabilidade.IContabilContaDAO;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.servicos.dao.financeiro.IContaCaixaDAO;
import dc.servicos.dao.geral.INivelFormacaoDAO;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;
import dc.servicos.dao.geral.IUfDAO;
import dc.servicos.dao.geral.pessoal.IAtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoColaboradorDAO;
import dc.servicos.dao.geral.pessoal.ISituacaoForCliDAO;
import dc.servicos.dao.geral.pessoal.ITipoColaboradorDAO;
import dc.servicos.dao.tributario.IOperacaoFiscalDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.PessoaEventosFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PessoaEventosFormController extends CRUDFormController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PessoaEventosFormView subView;

	/**
	 * ENTITY
	 */

	private PessoaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PessoaBusiness<PessoaEntity> business;

	@Autowired
	private PessoaContatoBusiness<PessoaContatoEntity> pessoaContatoBusiness;

	@Autowired
	private PessoaEnderecoBusiness<PessoaEnderecoEntity> pessoaEnderecoBusiness;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private EstadoCivilBusiness<EstadoCivilEntity> estadoCivilBusiness;
	
	@Autowired
	private IEstadoCivilDAO estadoCivilDAO;

	@Autowired
	private ISituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private IAtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private IOperacaoFiscalDAO operacaoFiscalDAO;

	@Autowired
	private ITipoColaboradorDAO tipoColaboradorDAO;

	@Autowired
	private ISituacaoColaboradorDAO situacaoColaboradorDAO;

	@Autowired
	private ISindicatoDAO sindicatoDAO;

	@Autowired
	private INivelFormacaoDAO nivelFormacaoDAO;

	@Autowired
	private ICargoDAO cargoDAO;

	@Autowired
	private ISetorDAO setorDAO;

	@Autowired
	private IUfDAO ufDAO;

	@Autowired
	private IPlanoContaDAO planoContaDAO;

	@Autowired
	private IContaCaixaDAO contaCaixaDAO;
	
	@Autowired
	private IContabilContaDAO contabilContaDAO;
	
	@Autowired
	private IPessoaEnderecoDAO pessoaEnderecoDAO;
	
	@Autowired
	private IPessoaContatoDAO pessoaContatoDAO;

	/**
	 * CONSTRUTOR
	 */

	public PessoaEventosFormController() {
		// TODO Auto-generated constructor stub
	}

	public PessoaBusiness<PessoaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
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
	public PessoaEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaEventosFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(PessoaEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			//fieldGroup.bind(this.subView.getCbTipoPessoa(),"tipo");
			fieldGroup.bind(this.subView.getTfEmail(),"email");
			fieldGroup.bind(this.subView.getTfSite(),"site");
			
			this.subView.getMocEstadoCivil().configuraCombo(
					"nome", EstadoCivilListController.class, this.estadoCivilDAO, this.getMainController());
			
			System.out.println(":: load cliente");
			this.subView.getMocClienteSituacao().configuraCombo(
					"nome", SituacaoForCliListController.class, this.situacaoForCliDAO, this.getMainController());
			this.subView.getMocClienteAtividade().configuraCombo(
					"nome", AtividadeForCliListController.class, this.atividadeForCliDAO, this.getMainController());
			this.subView.getMocClienteOperacaoFiscal().configuraCombo(
					"descricao", OperacaoFiscalListController.class, this.operacaoFiscalDAO, this.getMainController());
			
			//

			carregarTipoPessoa();
			carregarTipoRegime();
			carregarCnh();
			carregarRaca();
			carregarCategoriaReservista();
			carregarCrt();
			carregarTipoSanguineo();
			carregarSexo();

			carregarGerarFinanceiro();
			carregarIndicadorPreco();
			carregarTipoFrete();
			carregarFormaDesconto();

			// Valores iniciais

			this.subView.getCbTipoPessoa().setValue(TipoPessoaEn.F);
			this.subView.getOgSexo().setValue(SexoEn.F);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();

			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) this.subView
					.getCbTipoPessoa().getValue();

			this.entity.setTipo(tipoPessoaEn);

			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.entity.getTipo().equals(TipoPessoaEn.F)) {
				savePessoaFisica();
			} else if (this.entity.getTipo().equals(TipoPessoaEn.J)) {
				savePessoaJuridica();
			}

			this.entity.setTipoCliente(this.subView.getCkCliente().getValue()
					.equals(Boolean.TRUE) ? "1" : "0");

			if (this.entity.getTipoCliente().equals("1")) {
				saveCliente();
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	private void savePessoaFisica() throws Exception {
		PessoaFisicaEntity ent = (this.entity.getPessoaFisica() == null ? new PessoaFisicaEntity()
				: this.entity.getPessoaFisica());
		ent.setPessoa(this.entity);

		ent.setCpf(this.subView.getMtfCpf().getValue());
		ent.setDataNascimento(this.subView.getPdfDataNascimento().getValue());
		ent.setNaturalidade(this.subView.getTfNaturalidade().getValue());
		ent.setNacionalidade(this.subView.getTfNacionalidade().getValue());
		ent.setNomeMae(this.subView.getTfNomeMae().getValue());
		ent.setNomePai(this.subView.getTfNomePai().getValue());
		ent.setRg(this.subView.getTfNumeroRg().getValue());
		ent.setOrgaoRg(this.subView.getTfOrgaoEmissor().getValue());
		ent.setDataEmissaoRg(this.subView.getPdfDataEmissaoRg().getValue());
		ent.setCnhNumero(this.subView.getTfCnh().getValue());

		CnhEn cnhEn = (CnhEn) this.subView.getCbCategoriaCnh().getValue();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			ent.setCnh(cnhEn);
		}

		ent.setCnhVencimento(this.subView.getPdfDataCnhEmissao().getValue());

		EstadoCivilEntity estadoCivil = this.subView.getMocEstadoCivil()
				.getValue();

		if (ObjectUtils.isNotBlank(estadoCivil)) {
			ent.setEstadoCivil(estadoCivil);
		}

		RacaEn racaEn = (RacaEn) this.subView.getCbRaca().getValue();

		if (ObjectUtils.isNotBlank(racaEn)) {
			ent.setRaca(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = (TipoSanguineoEn) this.subView
				.getCbTipoSanguineo().getValue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			ent.setTipoSangue(tipoSanguineoEn);
		}

		ent.setReservistaNumero(this.subView.getTfNumeroReservista().getValue());

		CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
				.getCbCategoriaReservista().getValue();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			ent.setReservistaCategoria(categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.subView.getOgSexo().getValue();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			ent.setSexo(sexoEn);
		}

		ent.setTituloEleitoralNumero(this.subView.getTfTituloEleitor()
				.getValue());
		ent.setTituloEleitoralSecao(NumberUtils.toInt(this.subView
				.getTfTituloSecao().getValue()));
		ent.setTituloEleitoralZona(NumberUtils.toInt(this.subView
				.getTfTituloZona().getValue()));

		this.entity.setPessoaFisica(ent);
	}

	private void savePessoaJuridica() throws Exception {
		PessoaJuridicaEntity ent = (this.entity.getPessoaJuridica() == null ? new PessoaJuridicaEntity()
				: this.entity.getPessoaJuridica());
		ent.setPessoa(this.entity);

		ent.setFantasia(this.subView.getTfFantasia().getValue());
		ent.setCnpj(this.subView.getMtfCnpj().getValue());
		ent.setInscricaoEstadual(this.subView.getTfInscricaoEstadual()
				.getValue());
		ent.setInscricaoMunicipal(this.subView.getTfInscricaoMunicipal()
				.getValue());
		ent.setDataConstituicao(this.subView.getPdfDataConstituicao()
				.getValue());
		ent.setSuframa(this.subView.getTfSuframa().getValue());

		TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
				.getCbTipoRegime().getValue();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			ent.setTipoRegime(tipoRegimeEn);
		}

		CrtEn crtEn = (CrtEn) this.subView.getCbCrt().getValue();

		if (ObjectUtils.isNotBlank(crtEn)) {
			ent.setCrt(crtEn);
		}

		this.entity.setPessoaJuridica(ent);
	}

	private void saveCliente() throws Exception {
		ClienteEntity ent = (this.entity.getCliente() == null ? new ClienteEntity()
				: this.entity.getCliente());
		ent.setPessoa(this.entity);

		ent.setObservacao(this.subView.getTaClienteObservacao().getValue());

		String limiteCredito = this.subView.getTfClienteLimiteCredito()
				.getValue();

		if (NumberUtils.isNumber(limiteCredito)) {
			ent.setLimiteCredito(NumberUtils.createBigDecimal(limiteCredito));
		} else {
			ent.setLimiteCredito(new BigDecimal(0));
		}

		String porcentoDesconto = this.subView.getTfClienteTaxaDesconto()
				.getValue();

		if (NumberUtils.isNumber(porcentoDesconto)) {
			ent.setPorcentoDesconto(NumberUtils
					.createBigDecimal(porcentoDesconto));
		} else {
			ent.setPorcentoDesconto(new BigDecimal(0));
		}

		FormaDescontoEn formaDesconto = (FormaDescontoEn) this.subView
				.getCbClienteFormaDesconto().getValue();

		ent.setFormaDesconto(formaDesconto);

		TipoFreteEn tipoFrete = (TipoFreteEn) this.subView
				.getCbClienteTipoFrete().getValue();

		ent.setTipoFrete(tipoFrete);

		IndicadorPrecoEn indicadorPreco = (IndicadorPrecoEn) this.subView
				.getCbClienteIndicadorPreco().getValue();

		ent.setIndicadorPreco(indicadorPreco);

		SimNaoEn gerarFinanceiro = (SimNaoEn) this.subView
				.getCbClienteGerarFinanceiro().getValue();

		ent.setGerarFinanceiro(gerarFinanceiro);

		ent.setContaTomador(this.subView.getTfClienteContaTomador().getValue());
		ent.setDesde(this.subView.getPdfClienteDesde().getValue());
		ent.setSituacaoForCli(this.subView.getMocClienteSituacao().getValue());
		ent.setAtividadeForCli(this.subView.getMocClienteAtividade().getValue());
		ent.setOperacaoFiscal(this.subView.getMocClienteOperacaoFiscal()
				.getValue());

		this.entity.setCliente(ent);
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);
			
			fieldGroup.setItemDataSource(this.entity);

			// PessoaContato
			//List<PessoaContatoEntity> auxLista1 = this.pessoaContatoBusiness.list(this.entity);
			//this.entity.setPessoaContatoList(auxLista1);
			List<PessoaContatoEntity> item = pessoaContatoDAO.findByPessoaContato(entity);
			subView.preencheSubFormContato(item);

			// PessoaEndereco
			//List<PessoaEnderecoEntity> auxLista2 = this.pessoaEnderecoBusiness.list(this.entity);
			//this.entity.setPessoaEnderecoList(auxLista2);			
			List<PessoaEnderecoEntity> itens = pessoaEnderecoDAO.findByPessoaEndereco(entity);
			subView.preencheSubFormEndereco(itens);

			this.subView.getTfNome().setValue(this.entity.getNome());

			this.subView.getCbTipoPessoa()
					.setValue(this.entity.getTipo());

			this.subView.getTfEmail().setValue(this.entity.getEmail());
			this.subView.getTfSite().setValue(this.entity.getSite());

			if (this.entity.getTipo().equals(TipoPessoaEn.F)) {
				this.entity.setPessoaFisica(loadPessoaFisica());
			} else if (this.entity.getTipo().equals(TipoPessoaEn.J)) {
				this.entity.setPessoaJuridica(loadPessoaJuridica());
			}

			this.subView.getCkCliente().setValue(
					this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
							: Boolean.TRUE);

			if (this.entity.getTipoCliente().equals("1")) {
				loadCliente();
			}

			// PessoaContato

			this.subView.getSfPessoaContato().fillWith(
					this.entity.getPessoaContatoList());

			// PessoaEndereco

			for (PessoaEnderecoEntity ent : this.entity.getPessoaEnderecoList()) {
				if (StringUtils.isNotBlank(ent.getSiglaUf())) {
					UfEntity uf = this.ufBusiness.find(ent.getIdUf());

					ent.setUf(uf);
				}
			}

			this.subView.getSfPessoaEndereco().fillWith(
					this.entity.getPessoaEnderecoList());

			visibleTabSheet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaFisicaEntity loadPessoaFisica() {
		PessoaFisicaEntity pf = this.entity.getPessoaFisica();

		this.subView.getMtfCpf().setValue(pf.getCpf());
		this.subView.getPdfDataNascimento().setValue(pf.getDataNascimento());
		this.subView.getTfNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTfNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTfNomeMae().setValue(pf.getNomeMae());
		this.subView.getTfNomePai().setValue(pf.getNomePai());
		this.subView.getTfNumeroRg().setValue(pf.getRg());
		this.subView.getTfOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getPdfDataEmissaoRg().setValue(pf.getDataEmissaoRg());
		this.subView.getTfCnh().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			this.subView.getCbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getPdfDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getMocEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTfNumeroReservista().setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.entity.getPessoaFisica().getSexo();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			this.subView.getOgSexo().setValue(sexoEn);
		}

		this.subView.getTfTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTfTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTfTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	private PessoaJuridicaEntity loadPessoaJuridica() {
		PessoaJuridicaEntity pj = this.entity.getPessoaJuridica();

		this.subView.getTfFantasia().setValue(pj.getFantasia());
		this.subView.getMtfCnpj().setValue(pj.getCnpj());
		this.subView.getTfInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTfInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getPdfDataConstituicao()
				.setValue(pj.getDataConstituicao());
		this.subView.getTfSuframa().setValue(pj.getSuframa());

		TipoRegimeEn tipoRegimeEn = pj.getTipoRegime();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			this.subView.getCbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (ObjectUtils.isNotBlank(crtEn)) {
			this.subView.getCbCrt().setValue(crtEn);
		}

		return pj;
	}

	private void loadCliente() throws Exception {
		ClienteEntity ent = this.entity.getCliente();

		if (ObjectUtils.isBlank(ent)) {
			return;
		}

		this.subView.getTaClienteObservacao().setValue(ent.getObservacao());
		this.subView.getTfClienteLimiteCredito().setValue(
				ent.getLimiteCredito().toString());
		this.subView.getTfClienteTaxaDesconto().setValue(
				ent.getPorcentoDesconto().toString());
		this.subView.getCbClienteFormaDesconto().setValue(
				ent.getFormaDesconto());
		this.subView.getCbClienteTipoFrete().setValue(ent.getTipoFrete());
		this.subView.getCbClienteIndicadorPreco().setValue(
				ent.getIndicadorPreco());
		this.subView.getCbClienteGerarFinanceiro().setValue(
				ent.getGerarFinanceiro());
		this.subView.getTfClienteContaTomador().setValue(ent.getContaTomador());
		this.subView.getPdfClienteDesde().setValue(ent.getDesde());

		SituacaoForCliEntity situacaoForCli = ent.getSituacaoForCli();

		if (ObjectUtils.isNotBlank(situacaoForCli)) {
			this.subView.getMocClienteSituacao().setValue(situacaoForCli);
		}

		AtividadeForCliEntity atividadeForCli = ent.getAtividadeForCli();

		if (ObjectUtils.isNotBlank(atividadeForCli)) {
			this.subView.getMocClienteAtividade().setValue(atividadeForCli);
		}

		OperacaoFiscalEntity operacaoFiscal = ent.getOperacaoFiscal();

		if (ObjectUtils.isNotBlank(operacaoFiscal)) {
			this.subView.getMocClienteOperacaoFiscal().setValue(operacaoFiscal);
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PessoaEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visibleTabSheet();
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

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

	/*public PessoaContatoEntity adicionarPessoaContato() {
		try {
			PessoaContatoEntity ent = new PessoaContatoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaContatoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaContato(List<PessoaContatoEntity> values) {
		try {
			for (PessoaContatoEntity ent : values) {
				this.pessoaContatoBusiness.delete(ent);
				this.entity.getPessoaContatoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}*/

	/*public PessoaEnderecoEntity adicionarPessoaEndereco() {
		try {
			PessoaEnderecoEntity ent = new PessoaEnderecoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaEnderecoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}*/
	
	public PessoaContatoEntity adicionarPessoaContato() {
		PessoaContatoEntity item = new PessoaContatoEntity();
		entity.addPessoaContato(item);
		return item;
	}
	
	public void removerPessoaContato(List<PessoaContatoEntity> pessoaContato) {
		for (PessoaContatoEntity pessoaCon : pessoaContato) {
			entity.removePessoaContato(pessoaCon);
		}
		mensagemRemovidoOK();
	}
	
	public PessoaEnderecoEntity adicionarPessoaEndereco() {
		PessoaEnderecoEntity item = new PessoaEnderecoEntity();
		entity.addPessoaEndereco(item);
		return item;
	}
	
	public void removerPessoaEndereco(List<PessoaEnderecoEntity> pessoaEndereco) {
		for (PessoaEnderecoEntity pessoaEn : pessoaEndereco) {
			entity.removePessoaEndereco(pessoaEn);
		}
		mensagemRemovidoOK();
	}

	/*public void removerPessoaEndereco(List<PessoaEnderecoEntity> values) {
		try {
			for (PessoaEnderecoEntity ent : values) {
				this.pessoaEnderecoBusiness.delete(ent);
				this.entity.getPessoaEnderecoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}*/

	/**
	 * COMBOS
	 */

	public void carregarTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCbTipoRegime().addItem(en);
		}
	}

	public void carregarCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCbCategoriaCnh().addItem(en);
		}
	}

	public void carregarRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCbRaca().addItem(en);
		}
	}

	public void carregarCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCbCategoriaReservista().addItem(en);
		}
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCbCrt().addItem(en);
		}
	}

	public void carregarTipoSanguineo() {
		for (TipoSanguineoEn en : TipoSanguineoEn.values()) {
			this.subView.getCbTipoSanguineo().addItem(en);
		}
	}

	public void carregarTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCbTipoPessoa().addItem(en);
		}
	}

	public void carregarSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getOgSexo().addItem(en);
		}
	}

	//

	public void carregarGerarFinanceiro() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbClienteGerarFinanceiro().addItem(en);
		}
	}

	public void carregarIndicadorPreco() {
		for (IndicadorPrecoEn en : IndicadorPrecoEn.values()) {
			this.subView.getCbClienteIndicadorPreco().addItem(en);
		}
	}

	public void carregarTipoFrete() {
		for (TipoFreteEn en : TipoFreteEn.values()) {
			this.subView.getCbClienteTipoFrete().addItem(en);
		}
	}

	public void carregarFormaDesconto() {
		for (FormaDescontoEn en : FormaDescontoEn.values()) {
			this.subView.getCbClienteFormaDesconto().addItem(en);
		}
	}

	//

	/**
	 * 
	 */

	public void visibleTabSheet() {
		this.subView
				.getTsGeral()
				.getTab(4)
				.setVisible(
						this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
								: Boolean.TRUE);

	}

	/**
	 * 
	 */

	public BeanItemContainer<UfEntity> getUfBic() {
		try {
			List<UfEntity> auxLista = this.ufBusiness.findAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			return bic;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 */

	public void vceVisibleTabSheet(ValueChangeEvent event, Integer indexTab) {
		Object obj = event.getProperty().getValue();

		if (obj instanceof TipoPessoaEn) {
			TipoPessoaEn en = (TipoPessoaEn) obj;

			this.subView
					.getTsGeral()
					.getTab(0)
					.setVisible(
							en.equals(TipoPessoaEn.F) ? Boolean.TRUE
									: Boolean.FALSE);
			this.subView
					.getTsGeral()
					.getTab(1)
					.setVisible(
							en.equals(TipoPessoaEn.J) ? Boolean.TRUE
									: Boolean.FALSE);

			this.subView.getTsGeral().setSelectedTab(
					en.equals(TipoPessoaEn.F) ? 0 : 1);
		} else if (obj instanceof Boolean) {
			Boolean b = (Boolean) obj;

			this.subView.getTsGeral().getTab(indexTab).setVisible(b);

			// this.subView.getCkColaborador().setEnabled(false);
			// this.subView.getTsGeral().getTab(5).setEnabled(false);
		} else {
			System.out.println(":: [instanceof] no type for " + obj.toString());
		}
	}
	
	public List<PessoaEnderecoEntity> getPessoaEndereco() {
		return pessoaEnderecoDAO.getAll();
	}

}