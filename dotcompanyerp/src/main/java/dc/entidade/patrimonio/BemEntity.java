package dc.entidade.patrimonio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "patrim_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class BemEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_bem_id_seq")
	@SequenceGenerator(name = "patrim_bem_id_seq", sequenceName = "patrim_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "numero_nb")
	@Caption("Número NB")
	@Size(max = 20, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroNb = "";

	@Field
	@Column(name = "nome")
	@Caption("Nome")
	@Size(max = 100, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Field
	@Column(name = "descricao")
	@Caption("Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao = "";

	@Field
	@Column(name = "numero_serie")
	@Caption("Número de serie")
	@Size(max = 50, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroSerie = "";

	@Field
	@Column(name = "data_aquisicao")
	@Caption("Data da aquisição")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAquisicao;

	@Field
	@Column(name = "data_aceite")
	@Caption("Data do aceite")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAceite;

	@Field
	@Column(name = "data_cadastro")
	@Caption("Data do cadastro")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Column(name = "data_contabilizado")
	@Caption("Data contabilizado")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataContabilizado;

	@Field
	@Column(name = "data_vistoria")
	@Caption("Data da vistoria")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataVistoria;

	@Field
	@Column(name = "data_marcacao")
	@Caption("Data da marcação")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataMarcacao;

	@Field
	@Column(name = "data_baixa")
	@Caption("Data da baixa")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataBaixa;

	@Field
	@Column(name = "vencimento_garantia")
	@Caption("Vencimento da garantia")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date vencimentoGarantia;

	@Field
	@Column(name = "numero_nota_fiscal")
	@Caption("Número da nota fiscal")
	@Size(max = 50, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroNotaFiscal = "";

	@Field
	@Column(name = "chave_nfe")
	@Caption("Chave NFE")
	@Size(max = 44, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String chaveNfe = "";

	@Field
	@Column(name = "valor_original")
	@Caption("Valor original")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorOriginal = new Double(0.0);

	@Field
	@Column(name = "valor_compra")
	@Caption("Valor da compra")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorCompra = new Double(0.0);

	@Field
	@Column(name = "valor_atualizado")
	@Caption("Valor atualizado")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorAtualizado = new Double(0.0);

	@Field
	@Column(name = "valor_baixa")
	@Caption("Valor da baixa")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBaixa = new Double(0.0);

	@Field
	@Column(name = "deprecia")
	@Caption("Deprecia")
	@Size(max = 1, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String deprecia = "";

	@Field
	@Column(name = "metodo_depreciacao")
	@Caption("Método de depreciação")
	@Size(max = 1, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String metodoDepreciacao = "";

	@Field
	@Column(name = "inicio_depreciacao")
	@Caption("Início da depreciação")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date inicioDepreciacao;

	@Field
	@Column(name = "ultima_depreciacao")
	@Caption("Última depreciação")
	@Temporal(value = TemporalType.TIMESTAMP)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date ultimaDepreciacao;

	@Field
	@Column(name = "tipo_depreciacao")
	@Caption("Tipo de depreciação")
	@Size(max = 1, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoDepreciacao = "";

	@Field
	@Column(name = "taxa_anual_depreciacao")
	@Caption("Taxa anual de depreciação")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double taxaAnualDepreciacao = new Double(0.0);

	@Field
	@Column(name = "taxa_mensal_depreciacao")
	@Caption("Taxa mensal de depreciação")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double taxaMensalDepreciacao = new Double(0.0);

	@Field
	@Column(name = "taxa_depreciacao_acelerada")
	@Caption("Taxa de depreciação acelerada")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double taxaDepreciacaoAcelerada = new Double(0.0);

	@Field
	@Column(name = "taxa_depreciacao_incentivada")
	@Caption("Taxa de depreciação incentivada")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double taxaDepreciacaoIncentivada = new Double(0.0);

	@Field
	@Column(name = "funcao")
	@Caption("Função")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String funcao = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_patrim_tipo_aquisicao_bem", nullable = false)
	@Caption("Tipo de aquisição")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private TipoAquisicaoEntity tipoAquisicao;

	@ManyToOne
	@JoinColumn(name = "id_patrim_estado_conservacao", nullable = false)
	@Caption("Estado de conservação")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private EstadoConservacaoEntity estadoConservacao;

	@ManyToOne
	@JoinColumn(name = "id_patrim_grupo_bem", nullable = false)
	@Caption("Grupo do bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private GrupoBemEntity grupoBem;

	@ManyToOne
	@JoinColumn(name = "id_setor", nullable = false)
	@Caption("Setor")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private SetorEntity setor;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor", nullable = false)
	@Caption("Fornecedor")
	private FornecedorEntity fornecedor;

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<ApoliceSeguroEntity> apoliceSeguroList;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<MovimentacaoBemEntity> movimentacaoBemList;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<DocumentoBemEntity> documentoBemList;

	/**
	 * CONSTRUTOR
	 */

	public BemEntity() {

	}

	public BemEntity(Integer id) {
		this.id = id;
	}

	public BemEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroNb() {
		return numeroNb;
	}

	public void setNumeroNb(String numeroNb) {
		this.numeroNb = (numeroNb == null ? "" : numeroNb.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = (numeroSerie == null ? "" : numeroSerie
				.toUpperCase());
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataContabilizado() {
		return dataContabilizado;
	}

	public void setDataContabilizado(Date dataContabilizado) {
		this.dataContabilizado = dataContabilizado;
	}

	public Date getDataVistoria() {
		return dataVistoria;
	}

	public void setDataVistoria(Date dataVistoria) {
		this.dataVistoria = dataVistoria;
	}

	public Date getDataMarcacao() {
		return dataMarcacao;
	}

	public void setDataMarcacao(Date dataMarcacao) {
		this.dataMarcacao = dataMarcacao;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public Date getVencimentoGarantia() {
		return vencimentoGarantia;
	}

	public void setVencimentoGarantia(Date vencimentoGarantia) {
		this.vencimentoGarantia = vencimentoGarantia;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = (numeroNotaFiscal == null ? ""
				: numeroNotaFiscal.toUpperCase());
	}

	public String getChaveNfe() {
		return chaveNfe;
	}

	public void setChaveNfe(String chaveNfe) {
		this.chaveNfe = (chaveNfe == null ? "" : chaveNfe.toUpperCase());
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = (valorOriginal == null ? new Double(0.0)
				: valorOriginal);
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = (valorCompra == null ? new Double(0.0) : valorCompra);
	}

	public Double getValorAtualizado() {
		return valorAtualizado;
	}

	public void setValorAtualizado(Double valorAtualizado) {
		this.valorAtualizado = (valorAtualizado == null ? new Double(0.0)
				: valorAtualizado);
	}

	public Double getValorBaixa() {
		return valorBaixa;
	}

	public void setValorBaixa(Double valorBaixa) {
		this.valorBaixa = (valorBaixa == null ? new Double(0.0) : valorBaixa);
	}

	public String getDeprecia() {
		return deprecia;
	}

	public void setDeprecia(String deprecia) {
		this.deprecia = (deprecia == null ? "" : deprecia.toUpperCase());
	}

	public String getMetodoDepreciacao() {
		return metodoDepreciacao;
	}

	public void setMetodoDepreciacao(String metodoDepreciacao) {
		this.metodoDepreciacao = (metodoDepreciacao == null ? ""
				: metodoDepreciacao.toUpperCase());
	}

	public Date getInicioDepreciacao() {
		return inicioDepreciacao;
	}

	public void setInicioDepreciacao(Date inicioDepreciacao) {
		this.inicioDepreciacao = inicioDepreciacao;
	}

	public Date getUltimaDepreciacao() {
		return ultimaDepreciacao;
	}

	public void setUltimaDepreciacao(Date ultimaDepreciacao) {
		this.ultimaDepreciacao = ultimaDepreciacao;
	}

	public String getTipoDepreciacao() {
		return tipoDepreciacao;
	}

	public void setTipoDepreciacao(String tipoDepreciacao) {
		this.tipoDepreciacao = (tipoDepreciacao == null ? "" : tipoDepreciacao
				.toUpperCase());
	}

	public Double getTaxaAnualDepreciacao() {
		return taxaAnualDepreciacao;
	}

	public void setTaxaAnualDepreciacao(Double taxaAnualDepreciacao) {
		this.taxaAnualDepreciacao = (taxaAnualDepreciacao == null ? new Double(
				0.0) : taxaAnualDepreciacao);
	}

	public Double getTaxaMensalDepreciacao() {
		return taxaMensalDepreciacao;
	}

	public void setTaxaMensalDepreciacao(Double taxaMensalDepreciacao) {
		this.taxaMensalDepreciacao = (taxaMensalDepreciacao == null ? new Double(
				0.0) : taxaMensalDepreciacao);
	}

	public Double getTaxaDepreciacaoAcelerada() {
		return taxaDepreciacaoAcelerada;
	}

	public void setTaxaDepreciacaoAcelerada(Double taxaDepreciacaoAcelerada) {
		this.taxaDepreciacaoAcelerada = (taxaDepreciacaoAcelerada == null ? new Double(
				0.0) : taxaDepreciacaoAcelerada);
	}

	public Double getTaxaDepreciacaoIncentivada() {
		return taxaDepreciacaoIncentivada;
	}

	public void setTaxaDepreciacaoIncentivada(Double taxaDepreciacaoIncentivada) {
		this.taxaDepreciacaoIncentivada = (taxaDepreciacaoIncentivada == null ? new Double(
				0.0) : taxaDepreciacaoIncentivada);
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = (funcao == null ? "" : funcao.toUpperCase());
	}

	public TipoAquisicaoEntity getTipoAquisicao() {
		return tipoAquisicao;
	}

	public void setTipoAquisicao(TipoAquisicaoEntity tipoAquisicao) {
		this.tipoAquisicao = tipoAquisicao;
	}

	public EstadoConservacaoEntity getEstadoConservacao() {
		return estadoConservacao;
	}

	public void setEstadoConservacao(EstadoConservacaoEntity estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}

	public GrupoBemEntity getGrupoBem() {
		return grupoBem;
	}

	public void setGrupoBem(GrupoBemEntity grupoBem) {
		this.grupoBem = grupoBem;
	}

	public SetorEntity getSetor() {
		return setor;
	}

	public void setSetor(SetorEntity setor) {
		this.setor = setor;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public List<ApoliceSeguroEntity> getApoliceSeguroList() {
		return apoliceSeguroList;
	}

	public void setApoliceSeguroList(List<ApoliceSeguroEntity> apoliceSeguroList) {
		this.apoliceSeguroList = apoliceSeguroList;
	}

	public List<MovimentacaoBemEntity> getMovimentacaoBemList() {
		return movimentacaoBemList;
	}

	public void setMovimentacaoBemList(
			List<MovimentacaoBemEntity> movimentacaoBemList) {
		this.movimentacaoBemList = movimentacaoBemList;
	}

	public List<DocumentoBemEntity> getDocumentoBemList() {
		return documentoBemList;
	}

	public void setDocumentoBemList(List<DocumentoBemEntity> documentoBemList) {
		this.documentoBemList = documentoBemList;
	}

	/**
	 * HASH E EQUALS
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}