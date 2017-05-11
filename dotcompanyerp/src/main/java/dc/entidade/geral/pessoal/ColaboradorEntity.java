package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.control.enums.FormaPagamentoEn;
import dc.control.enums.SimNaoEn;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.entidade.folhapagamento.movimento.RescisaoEntity;
import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.suprimentos.contrato.SolicitacaoServicoEntity;

@Entity
@Table(name = "colaborador")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ColaboradorEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer MASTER_ID = 1;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colaborador_id_seq")
	@SequenceGenerator(name = "colaborador_id_seq", sequenceName = "colaborador_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Matrícula")
	@Column(name = "MATRICULA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Matrícula é Obrigatório!")
	private String matricula = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Foto 3x4")
	@Column(name = "FOTO_34")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String foto34 = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de cadastro")
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Data Cadastro é Obrigatório!")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de admissão")
	@Column(name = "DATA_ADMISSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAdmissao;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Vencimento de férias")
	@Column(name = "VENCIMENTO_FERIAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date vencimentoFerias;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de transferência")
	@Column(name = "DATA_TRANSFERENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Data de Transferência é Obrigatório!")
	private Date dataTransferencia;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("FGTS - Data da opção")
	@Column(name = "FGTS_DATA_OPCAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date fgtsDataOpcao;

	@Field
	@Caption("FGTS - Conta")
	@Column(name = "FGTS_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer fgtsConta;

	@Field
	@Caption("Pagamento - Banco")
	@Column(name = "PAGAMENTO_BANCO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoBanco = "";

	@Field
	@Caption("Pagamento - Agência")
	@Column(name = "PAGAMENTO_AGENCIA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoAgencia = "";

	@Field
	@Caption("Pagamento - Dígito da agência")
	@Column(name = "PAGAMENTO_AGENCIA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoAgenciaDigito = "";

	@Field
	@Caption("Pagamento - Conta")
	@Column(name = "PAGAMENTO_CONTA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoConta = "";

	@Field
	@Caption("Pagamento - Dígito da conta")
	@Column(name = "PAGAMENTO_CONTA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoContaDigito = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Último exame médico")
	@Column(name = "EXAME_MEDICO_ULTIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoUltimo;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Exame médico - Vencimento")
	@Column(name = "EXAME_MEDICO_VENCIMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoVencimento;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("PIS - Data de cadastro")
	@Column(name = "PIS_DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date pisDataCadastro;

	@Field
	@Caption("PIS - Número")
	@Column(name = "PIS_NUMERO", length = 12)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisNumero = "";

	@Field
	@Caption("PIS - Banco")
	@Column(name = "PIS_BANCO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisBanco = "";

	@Field
	@Caption("PIS - Agência")
	@Column(name = "PIS_AGENCIA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisAgencia = "";

	@Field
	@Caption("PIS - Dígito da agência")
	@Column(name = "PIS_AGENCIA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisAgenciaDigito = "";

	@Field
	@Caption("CTPS - Número")
	@Column(name = "CTPS_NUMERO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsNumero = "";

	@Field
	@Caption("CTPS - Série")
	@Column(name = "CTPS_SERIE", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsSerie = "";

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("CTPS - Data da expedição")
	@Column(name = "CTPS_DATA_EXPEDICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date ctpsDataExpedicao;

	@Field
	@Caption("CTPS - UF")
	@Column(name = "CTPS_UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsUf = "";

	@Field
	@Caption("SEFIP - Categoria")
	@Column(name = "CATEGORIA_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String categoriaSefip = "";

	@Field
	@Caption("SEFIP - Ocorrência")
	@Column(name = "OCORRENCIA_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ocorrenciaSefip = new Integer(0);

	@Field
	@Caption("CAGED - Código da admissão")
	@Column(name = "CODIGO_ADMISSAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAdmissaoCaged = new Integer(0);

	@Field
	@Caption("CAGED - Código da demissão")
	@Column(name = "CODIGO_DEMISSAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoCaged = new Integer(0);

	@Field
	@Caption("SEFIP - Código da demissão")
	@Column(name = "CODIGO_DEMISSAO_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoSefip = new Integer(0);

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de demissão")
	@Column(name = "DATA_DEMISSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataDemissao;

	@Field
	@Caption("Código da turma do ponto")
	@Column(name = "CODIGO_TURMA_PONTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoTurmaPonto = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Observação")
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Field
	@Caption("Salário fixo")
	@Column(name = "salario_fixo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal salarioFixo = new BigDecimal(0);

	@Field
	@Caption("Tipo de comissão do serviço")
	@Column(name = "tipo_comissao_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoComissaoServico = "";

	@Field
	@Caption("Valor da comissão do serviço")
	@Column(name = "valor_comissao_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorComissaoServico = new BigDecimal(0);

	@Field
	@Caption("Tipo de comissão do produto")
	@Column(name = "tipo_comissao_produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoComissaoProduto = "";

	@Field
	@Caption("Valor da comissão do produto")
	@Column(name = "valor_comissao_produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorComissaoProduto = new BigDecimal(0);

	@Field
	@Caption("Priorizar comissão")
	@Column(name = "priorizar_comissao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean priorizarComissao = Boolean.FALSE;

	@Field
	@Caption("Comissão OVER")
	@Column(name = "comissao_over")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean comissaoOver = Boolean.FALSE;

	@Field
	@Caption("Pagamento da comissão será")
	@Column(name = "pgto_comissao_sera")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer pgtoComissaoSera = new Integer(0);

	@Field
	@Caption("Lançamento da comissão")
	@Column(name = "lcto_comissao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer lctoComissao = new Integer(0);

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("FGTS - Optante")
	@Column(name = "FGTS_OPTANTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn fgtsOptante;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Forma de pagamento")
	@Column(name = "PAGAMENTO_FORMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private FormaPagamentoEn pagamentoForma;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Desconto do plano de saúde")
	@Column(name = "DESCONTO_PLANO_SAUDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn descontoPlanoSaude;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Sai na RAIS")
	@Column(name = "SAI_NA_RAIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn saiNaRais;
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Setor")
	@ManyToOne()
	@JoinColumn(name = "id_setor", nullable = false)
	@NotNull(message = "Setor é Obrigatório!")
	@IndexedEmbedded( includePaths={"nome"})
	private SetorEntity setor;

	@Caption("Cargo")
	@ManyToOne()
	@JoinColumn(name = "id_cargo")
	@NotNull(message = "Cargo é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private CargoEntity cargo;

	@Caption("Tipo do colaborador")
	@ManyToOne()
	@JoinColumn(name = "id_tipo_colaborador", nullable = false)
	@NotNull(message = "Tipo Colaborador é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private TipoColaboradorEntity tipoColaborador;

	@Caption("Situação do colaborador")
	@ManyToOne()
	@JoinColumn(name = "id_situacao_colaborador", nullable = false)
	@NotNull(message = "Situação do Colaborador é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private SituacaoColaboradorEntity situacaoColaborador;

	@Caption("Nível de formação")
	@ManyToOne()
	@JoinColumn(name = "id_nivel_formacao", nullable = false)
	@NotNull(message = "Nível de Formação é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private NivelFormacaoEntity nivelFormacao;

	@Caption("Sindicato")
	@ManyToOne()
	@JoinColumn(name = "id_sindicato")
	private SindicatoEntity sindicato;

	@Caption("Plano de conta")
	@ManyToOne()
	@JoinColumn(name = "id_plano_conta")
	private PlanoContaEntity planoConta;

	@Caption("Conta da caixa")
	@ManyToOne()
	@JoinColumn(name = "id_conta_caixa")
	//@NotNull(message = "Conta Caixa é Obrigatório!")
	private ContaCaixa contaCaixa;

	@Caption("Tipo de admissão")
	@ManyToOne()
	@JoinColumn(name = "id_tipo_admissao")
	private TipoAdmissaoEntity tipoAdmissao;

	@Caption("Pessoa")
	@OneToOne()
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	@NotNull(message = "Pessoa é Obrigatório!")
	@IndexedEmbedded(depth=2, includePaths={"nome"})
	private PessoaEntity pessoa;

	/**
	 * REFERENCIA - LIST
	 */
	
	@OneToMany(mappedBy = "colaborador", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<SolicitacaoServicoEntity> solicitacaoList = new ArrayList<SolicitacaoServicoEntity>();
	
	@OneToMany(mappedBy = "colaborador", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<UsuarioEntity> usuarioList = new ArrayList<UsuarioEntity>();
	
	@OneToMany(mappedBy = "colaborador", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<VersaoDocumento> versaoList = new ArrayList<VersaoDocumento>();

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module PATRIMONIO
	 */

	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<BemEntity> bemList = new ArrayList<BemEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<VendedorEntity> vendedorList = new ArrayList<VendedorEntity>();

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<AfastamentoEntity> afastamentoList = new ArrayList<AfastamentoEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PlanoSaudeEntity> planoSaudeList = new ArrayList<PlanoSaudeEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PppEntity> pppList = new ArrayList<PppEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<RescisaoEntity> rescisaoList = new ArrayList<RescisaoEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ValeTransporteEntity> valeTransporteList = new ArrayList<ValeTransporteEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<HistoricoSalarialEntity> historicoSalarialList = new ArrayList<HistoricoSalarialEntity>();
	
	//@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//@Fetch(FetchMode.SUBSELECT)
	//private List<LancamentoCabecalhoEntity> lancamentoCabecalhoList = new ArrayList<LancamentoCabecalhoEntity>();
	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoEntityList = new ArrayList<FeriasPeriodoAquisitivoEntity>();

	/**
	 * ********************************************************
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public ColaboradorEntity() {

	}

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	public ColaboradorEntity(Integer id, String matricula) {
		this.id = id;
		this.matricula = matricula;
	}

	/**
	 * ********************************************************
	 */

	public ColaboradorEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = (matricula == null ? "".trim() : matricula
				.toUpperCase().trim());
	}

	public String getFoto34() {
		return foto34;
	}

	public void setFoto34(String foto34) {
		this.foto34 = (foto34 == null ? "".trim() : foto34.toUpperCase().trim());
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getVencimentoFerias() {
		return vencimentoFerias;
	}

	public void setVencimentoFerias(Date vencimentoFerias) {
		this.vencimentoFerias = vencimentoFerias;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Date getFgtsDataOpcao() {
		return fgtsDataOpcao;
	}

	public void setFgtsDataOpcao(Date fgtsDataOpcao) {
		this.fgtsDataOpcao = fgtsDataOpcao;
	}

	public Integer getFgtsConta() {
		return fgtsConta;
	}

	public void setFgtsConta(Integer fgtsConta) {
		this.fgtsConta = (fgtsConta == null ? new Integer(0) : fgtsConta);
	}

	public String getPagamentoBanco() {
		return pagamentoBanco;
	}

	public void setPagamentoBanco(String pagamentoBanco) {
		this.pagamentoBanco = (pagamentoBanco == null ? "".trim()
				: pagamentoBanco.toUpperCase().trim());
	}

	public String getPagamentoAgencia() {
		return pagamentoAgencia;
	}

	public void setPagamentoAgencia(String pagamentoAgencia) {
		this.pagamentoAgencia = (pagamentoAgencia == null ? "".trim()
				: pagamentoAgencia.toUpperCase().trim());
	}

	public String getPagamentoAgenciaDigito() {
		return pagamentoAgenciaDigito;
	}

	public void setPagamentoAgenciaDigito(String pagamentoAgenciaDigito) {
		this.pagamentoAgenciaDigito = (pagamentoAgenciaDigito == null ? ""
				.trim() : pagamentoAgenciaDigito.toUpperCase().trim());
	}

	public String getPagamentoConta() {
		return pagamentoConta;
	}

	public void setPagamentoConta(String pagamentoConta) {
		this.pagamentoConta = (pagamentoConta == null ? "".trim()
				: pagamentoConta.toUpperCase().trim());
	}

	public String getPagamentoContaDigito() {
		return pagamentoContaDigito;
	}

	public void setPagamentoContaDigito(String pagamentoContaDigito) {
		this.pagamentoContaDigito = (pagamentoContaDigito == null ? "".trim()
				: pagamentoContaDigito.toUpperCase().trim());
	}

	public Date getExameMedicoUltimo() {
		return exameMedicoUltimo;
	}

	public void setExameMedicoUltimo(Date exameMedicoUltimo) {
		this.exameMedicoUltimo = exameMedicoUltimo;
	}

	public Date getExameMedicoVencimento() {
		return exameMedicoVencimento;
	}

	public void setExameMedicoVencimento(Date exameMedicoVencimento) {
		this.exameMedicoVencimento = exameMedicoVencimento;
	}

	public Date getPisDataCadastro() {
		return pisDataCadastro;
	}

	public void setPisDataCadastro(Date pisDataCadastro) {
		this.pisDataCadastro = pisDataCadastro;
	}

	public String getPisNumero() {
		return pisNumero;
	}

	public void setPisNumero(String pisNumero) {
		this.pisNumero = (pisNumero == null ? "".trim() : pisNumero
				.toUpperCase().trim());
	}

	public String getPisBanco() {
		return pisBanco;
	}

	public void setPisBanco(String pisBanco) {
		this.pisBanco = (pisBanco == null ? "".trim() : pisBanco.toUpperCase()
				.trim());
	}

	public String getPisAgencia() {
		return pisAgencia;
	}

	public void setPisAgencia(String pisAgencia) {
		this.pisAgencia = (pisAgencia == null ? "".trim() : pisAgencia
				.toUpperCase().trim());
	}

	public String getPisAgenciaDigito() {
		return pisAgenciaDigito;
	}

	public void setPisAgenciaDigito(String pisAgenciaDigito) {
		this.pisAgenciaDigito = (pisAgenciaDigito == null ? "".trim()
				: pisAgenciaDigito.toUpperCase().trim());
	}

	public String getCtpsNumero() {
		return ctpsNumero;
	}

	public void setCtpsNumero(String ctpsNumero) {
		this.ctpsNumero = (ctpsNumero == null ? "".trim() : ctpsNumero
				.toUpperCase().trim());
	}

	public String getCtpsSerie() {
		return ctpsSerie;
	}

	public void setCtpsSerie(String ctpsSerie) {
		this.ctpsSerie = (ctpsSerie == null ? "".trim() : ctpsSerie
				.toUpperCase().trim());
	}

	public Date getCtpsDataExpedicao() {
		return ctpsDataExpedicao;
	}

	public void setCtpsDataExpedicao(Date ctpsDataExpedicao) {
		this.ctpsDataExpedicao = ctpsDataExpedicao;
	}

	public String getCtpsUf() {
		return ctpsUf;
	}

	public void setCtpsUf(String ctpsUf) {
		this.ctpsUf = (ctpsUf == null ? "".trim() : ctpsUf.toUpperCase().trim());
	}

	public String getCategoriaSefip() {
		return categoriaSefip;
	}

	public void setCategoriaSefip(String categoriaSefip) {
		this.categoriaSefip = (categoriaSefip == null ? "".trim()
				: categoriaSefip.toUpperCase().trim());
	}

	public Integer getOcorrenciaSefip() {
		return ocorrenciaSefip;
	}

	public void setOcorrenciaSefip(Integer ocorrenciaSefip) {
		this.ocorrenciaSefip = (ocorrenciaSefip == null ? new Integer(0)
				: ocorrenciaSefip);
	}

	public Integer getCodigoAdmissaoCaged() {
		return codigoAdmissaoCaged;
	}

	public void setCodigoAdmissaoCaged(Integer codigoAdmissaoCaged) {
		this.codigoAdmissaoCaged = (codigoAdmissaoCaged == null ? new Integer(0)
				: codigoAdmissaoCaged);
	}

	public Integer getCodigoDemissaoCaged() {
		return codigoDemissaoCaged;
	}

	public void setCodigoDemissaoCaged(Integer codigoDemissaoCaged) {
		this.codigoDemissaoCaged = (codigoDemissaoCaged == null ? new Integer(0)
				: codigoDemissaoCaged);
	}

	public Integer getCodigoDemissaoSefip() {
		return codigoDemissaoSefip;
	}

	public void setCodigoDemissaoSefip(Integer codigoDemissaoSefip) {
		this.codigoDemissaoSefip = (codigoDemissaoSefip == null ? new Integer(0)
				: codigoDemissaoSefip);
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getCodigoTurmaPonto() {
		return codigoTurmaPonto;
	}

	public void setCodigoTurmaPonto(String codigoTurmaPonto) {
		this.codigoTurmaPonto = (codigoTurmaPonto == null ? "".trim()
				: codigoTurmaPonto.toUpperCase().trim());
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public BigDecimal getSalarioFixo() {
		return salarioFixo;
	}

	public void setSalarioFixo(BigDecimal salarioFixo) {
		this.salarioFixo = (salarioFixo == null ? new BigDecimal(0)
				: salarioFixo);
	}

	public String getTipoComissaoServico() {
		return tipoComissaoServico;
	}

	public void setTipoComissaoServico(String tipoComissaoServico) {
		this.tipoComissaoServico = (tipoComissaoServico == null ? "".trim()
				: tipoComissaoServico.toUpperCase().trim());
	}

	public BigDecimal getValorComissaoServico() {
		return valorComissaoServico;
	}

	public void setValorComissaoServico(BigDecimal valorComissaoServico) {
		this.valorComissaoServico = (valorComissaoServico == null ? new BigDecimal(
				0) : valorComissaoServico);
	}

	public String getTipoComissaoProduto() {
		return tipoComissaoProduto;
	}

	public void setTipoComissaoProduto(String tipoComissaoProduto) {
		this.tipoComissaoProduto = (tipoComissaoProduto == null ? "".trim()
				: tipoComissaoProduto.toUpperCase().trim());
	}

	public BigDecimal getValorComissaoProduto() {
		return valorComissaoProduto;
	}

	public void setValorComissaoProduto(BigDecimal valorComissaoProduto) {
		this.valorComissaoProduto = (valorComissaoProduto == null ? new BigDecimal(
				0) : valorComissaoProduto);
	}

	public Boolean getPriorizarComissao() {
		return priorizarComissao;
	}

	public void setPriorizarComissao(Boolean priorizarComissao) {
		this.priorizarComissao = (priorizarComissao == null ? Boolean.FALSE
				: priorizarComissao);
	}

	public Boolean getComissaoOver() {
		return comissaoOver;
	}

	public void setComissaoOver(Boolean comissaoOver) {
		this.comissaoOver = (comissaoOver == null ? Boolean.FALSE
				: comissaoOver);
	}

	public Integer getPgtoComissaoSera() {
		return pgtoComissaoSera;
	}

	public void setPgtoComissaoSera(Integer pgtoComissaoSera) {
		this.pgtoComissaoSera = (pgtoComissaoSera == null ? new Integer(0)
				: pgtoComissaoSera);
	}

	public Integer getLctoComissao() {
		return lctoComissao;
	}

	public void setLctoComissao(Integer lctoComissao) {
		this.lctoComissao = (lctoComissao == null ? new Integer(0)
				: lctoComissao);
	}

	public SimNaoEn getFgtsOptante() {
		return fgtsOptante;
	}

	public void setFgtsOptante(SimNaoEn fgtsOptante) {
		this.fgtsOptante = fgtsOptante;
	}

	public FormaPagamentoEn getPagamentoForma() {
		return pagamentoForma;
	}

	public void setPagamentoForma(FormaPagamentoEn pagamentoForma) {
		this.pagamentoForma = pagamentoForma;
	}

	public SimNaoEn getDescontoPlanoSaude() {
		return descontoPlanoSaude;
	}

	public void setDescontoPlanoSaude(SimNaoEn descontoPlanoSaude) {
		this.descontoPlanoSaude = descontoPlanoSaude;
	}

	public SimNaoEn getSaiNaRais() {
		return saiNaRais;
	}

	public void setSaiNaRais(SimNaoEn saiNaRais) {
		this.saiNaRais = saiNaRais;
	}
	
	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	public SetorEntity getSetor() {
		return setor;
	}

	public void setSetor(SetorEntity setor) {
		this.setor = setor;
	}

	public CargoEntity getCargo() {
		return cargo;
	}

	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}

	public TipoColaboradorEntity getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(TipoColaboradorEntity tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	public SituacaoColaboradorEntity getSituacaoColaborador() {
		return situacaoColaborador;
	}

	public void setSituacaoColaborador(
			SituacaoColaboradorEntity situacaoColaborador) {
		this.situacaoColaborador = situacaoColaborador;
	}

	public NivelFormacaoEntity getNivelFormacao() {
		return nivelFormacao;
	}

	public void setNivelFormacao(NivelFormacaoEntity nivelFormacao) {
		this.nivelFormacao = nivelFormacao;
	}

	public SindicatoEntity getSindicato() {
		return sindicato;
	}

	public void setSindicato(SindicatoEntity sindicato) {
		this.sindicato = sindicato;
	}

	public PlanoContaEntity getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public TipoAdmissaoEntity getTipoAdmissao() {
		return tipoAdmissao;
	}

	public void setTipoAdmissao(TipoAdmissaoEntity tipoAdmissao) {
		this.tipoAdmissao = tipoAdmissao;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	public List<AfastamentoEntity> getAfastamentoList() {
		return afastamentoList;
	}

	public void setAfastamentoList(List<AfastamentoEntity> afastamentoList) {
		this.afastamentoList = afastamentoList;
	}

	public List<PlanoSaudeEntity> getPlanoSaudeList() {
		return planoSaudeList;
	}

	public void setPlanoSaudeList(List<PlanoSaudeEntity> planoSaudeList) {
		this.planoSaudeList = planoSaudeList;
	}

	public List<PppEntity> getPppList() {
		return pppList;
	}

	public void setPppList(List<PppEntity> pppList) {
		this.pppList = pppList;
	}

	public List<RescisaoEntity> getRescisaoList() {
		return rescisaoList;
	}

	public void setRescisaoList(List<RescisaoEntity> rescisaoList) {
		this.rescisaoList = rescisaoList;
	}

	public List<ValeTransporteEntity> getValeTransporteList() {
		return valeTransporteList;
	}

	public void setValeTransporteList(
			List<ValeTransporteEntity> valeTransporteList) {
		this.valeTransporteList = valeTransporteList;
	}

	public List<HistoricoSalarialEntity> getHistoricoSalarialList() {
		return historicoSalarialList;
	}

	public void setHistoricoSalarialList(
			List<HistoricoSalarialEntity> historicoSalarialList) {
		this.historicoSalarialList = historicoSalarialList;
	}

	/*public List<LancamentoCabecalhoEntity> getLancamentoCabecalhoList() {
		return lancamentoCabecalhoList;
	}

	public void setLancamentoCabecalhoList(
			List<LancamentoCabecalhoEntity> lancamentoCabecalhoList) {
		this.lancamentoCabecalhoList = lancamentoCabecalhoList;
	}*/

	public List<FeriasPeriodoAquisitivoEntity> getFeriasPeriodoAquisitivoEntityList() {
		return feriasPeriodoAquisitivoEntityList;
	}

	public void setFeriasPeriodoAquisitivoEntityList(
			List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoEntityList) {
		this.feriasPeriodoAquisitivoEntityList = feriasPeriodoAquisitivoEntityList;
	}
	
	public List<SolicitacaoServicoEntity> getSolicitacaoList() {
		return solicitacaoList;
	}

	public void setSolicitacaoList(List<SolicitacaoServicoEntity> solicitacaoList) {
		this.solicitacaoList = solicitacaoList;
	}

	public List<UsuarioEntity> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<UsuarioEntity> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<VersaoDocumento> getVersaoList() {
		return versaoList;
	}

	public void setVersaoList(List<VersaoDocumento> versaoList) {
		this.versaoList = versaoList;
	}
	
	public List<VendedorEntity> getVendedorList() {
		return vendedorList;
	}

	public void setVendedorList(List<VendedorEntity> vendedorList) {
		this.vendedorList = vendedorList;
	}
	
	

	@Override
	public String toString() {
		return this.pessoa.getNome();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ColaboradorEntity)) {
            return false;
        }

        ColaboradorEntity that = (ColaboradorEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(getId(), that.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
        }
    }

}
