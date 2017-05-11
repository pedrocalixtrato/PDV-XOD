package dc.entidade.suprimentos.contrato;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.pessoal.PessoaEntity;

@Entity
@Table(name = "contrato")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContratoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contrato_id_seq")
	@SequenceGenerator(name = "contrato_id_seq", sequenceName = "contrato_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption(value = "Número")
	@Column(name = "NUMERO")
	private String numero;

	@Field
	@Caption(value = "Nome")
	@Column(name = "NOME")
	private String nome;

	@Field
	@Caption(value = "Descrição")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Field
	@Caption(value = "Data Cadastro")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption(value = "Data Início Vigência")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INICIO_VIGENCIA")
	private Date dataInicioVigencia;

	@Field
	@Caption(value = "Data Fim Vigência")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FIM_VIGENCIA")
	private Date dataFimVigencia;

	@Field
	@Caption(value = "Dia Faturamento")
	@Column(name = "DIA_FATURAMENTO", columnDefinition = "bpchar")
	private Integer diaFaturamento;
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/*
	 * @Field
	 * 
	 * @Temporal(TemporalType.DATE)
	 * 
	 * @Column(name = "PRIMEIRO_VENCIMENTO")
	 * 
	 * @Caption(value = "Primeiro Vencimento") private Date primeiroVencimento;
	 */

	@Field
	@Caption(value = "Valor")
	@Column(name = "VALOR")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;

	@Field
	@Caption(value = "Quantidade de Parcelas")
	@Column(name = "QUANTIDADE_PARCELAS")
	private Integer quantidadeParcelas;

	@Field
	@Caption(value = "Intervalo entre Parcelas")
	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	private Integer intervaloEntreParcelas;

	@Field
	@Caption(value = "Observação")
	private String observacao;

	/*
	 * @Column(name = "ARQUIVO_CONTRATO")
	 * 
	 * @Caption(value = "Arquivo Contrato") private String arquivoContrato;
	 */
	@Caption(value = "Tipo Contrato")
	@JoinColumn(name = "ID_TIPO_CONTRATO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@IndexedEmbedded(includePaths={"nome"})
	private TipoContratoEntity tipoContrato;

	@Caption(value = "Solicitação de Serviço")
	@JoinColumn(name = "ID_SOLICITACAO_SERVICO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@IndexedEmbedded(depth=2, includePaths={"contratoTipoServico.nome"})
	private SolicitacaoServicoEntity contratoSolicitacaoServico;

	@Caption(value = "Modelo Documento")
	@JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "ID")
	@ManyToOne(optional = true)
	@IndexedEmbedded(includePaths={"nome"})
	private Documento documento;

	/**
	 * 
	 * Mapeamento de Pessoa @ Wesley Jr
	 * 
	 */
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Caption(value = "Pessoa")
	@IndexedEmbedded(depth=2, includePaths={"nome"})
	private PessoaEntity pessoa;

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<HistFaturamentoEntity> contratosHistoricosFaturamentos = new ArrayList<>();

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ContratoProduto> contratoProduto = new ArrayList<>();

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<HistoricoReajusteEntity> contratosHistoricosReajustes = new ArrayList<>();

	@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PrevFaturamentoEntity> contratosPrevisoesFaturamentos = new ArrayList<>();

	/*
	 * @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL,
	 * orphanRemoval = true, fetch = FetchType.EAGER)
	 * 
	 * @Fetch(FetchMode.SUBSELECT) private List<ParcelaPagar> parcelasPagar =
	 * new ArrayList<>();
	 */

	@Transient
	private ContratoTemplate contratoTemplate;

	public ContratoEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public Date getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public Integer getDiaFaturamento() {
		return diaFaturamento;
	}

	public void setDiaFaturamento(Integer diaFaturamento) {
		this.diaFaturamento = diaFaturamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Integer getIntervaloEntreParcelas() {
		return intervaloEntreParcelas;
	}

	public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
		this.intervaloEntreParcelas = intervaloEntreParcelas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoContratoEntity getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContratoEntity tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	public SolicitacaoServicoEntity getContratoSolicitacaoServico() {
		return contratoSolicitacaoServico;
	}

	public void setContratoSolicitacaoServico(
			SolicitacaoServicoEntity contratoSolicitacaoServico) {
		this.contratoSolicitacaoServico = contratoSolicitacaoServico;
	}

	public void addParcela(PrevFaturamentoEntity contratoPrevFaturamento) {
		contratoPrevFaturamento.setContrato(this);
		this.contratosPrevisoesFaturamentos.add(contratoPrevFaturamento);
	}

	public void removeParcela(PrevFaturamentoEntity contratoPrevFaturamento) {
		contratoPrevFaturamento.setContrato(null);
		contratosPrevisoesFaturamentos.remove(contratoPrevFaturamento);

	}

	/*
	 * public void addParcelaPagar(ParcelaPagar parcela) {
	 * parcela.setContrato(this); this.parcelasPagar.add(parcela); }
	 * 
	 * public void removeParcelaPagar(ParcelaPagar parcela) {
	 * parcela.setContrato(null); parcelasPagar.remove(parcela);
	 * 
	 * }
	 */

	/**
	 * @return the contratoTemplate
	 */
	public ContratoTemplate getContratoTemplate() {
		return contratoTemplate;
	}

	/**
	 * @param contratoTemplate
	 *            the contratoTemplate to set
	 */
	public void setContratoTemplate(ContratoTemplate contratoTemplate) {
		this.contratoTemplate = contratoTemplate;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public HistFaturamentoEntity addContratoHistFaturamento(
			HistFaturamentoEntity contratoHistFaturamento) {
		getContratosHistoricosFaturamentos().add(contratoHistFaturamento);
		contratoHistFaturamento.setContrato(this);

		return contratoHistFaturamento;
	}

	public ContratoProduto addContratoHistFaturamento(
			ContratoProduto contratoProduto) {
		getContratosProduto().add(contratoProduto);
		contratoProduto.setContrato(this);
		return contratoProduto;
	}

	public HistFaturamentoEntity removeContratoHistFaturamento(
			HistFaturamentoEntity contratoHistFaturamento) {
		getContratosHistoricosFaturamentos().remove(contratoHistFaturamento);
		contratoHistFaturamento.setContrato(null);

		return contratoHistFaturamento;
	}

	public ContratoProduto removeContratoProduto(ContratoProduto contratoProduto) {
		getContratosProduto().remove(contratoProduto);
		contratoProduto.setContrato(null);

		return contratoProduto;
	}

	public HistoricoReajusteEntity addContratoHistoricoReajuste(
			HistoricoReajusteEntity contratoHistReajustes) {
		getContratosHistoricosReajustes().add(contratoHistReajustes);
		contratoHistReajustes.setContrato(this);

		return contratoHistReajustes;
	}

	public HistoricoReajusteEntity removeContratoHistoricoReajuste(
			HistoricoReajusteEntity contratoHistReajustes) {
		getContratosHistoricosReajustes().remove(contratoHistReajustes);
		contratoHistReajustes.setContrato(null);

		return contratoHistReajustes;
	}

	public PrevFaturamentoEntity addContratoPrevFaturamento(
			PrevFaturamentoEntity contratoPrevFaturamentos) {
		getContratosPrevisoesFaturamentos().add(contratoPrevFaturamentos);
		contratoPrevFaturamentos.setContrato(this);

		return contratoPrevFaturamentos;
	}

	public PrevFaturamentoEntity removeContratoPrevFaturamento(
			PrevFaturamentoEntity contratoPrevFaturamento) {
		getContratosPrevisoesFaturamentos().remove(contratoPrevFaturamento);
		contratoPrevFaturamento.setContrato(null);

		return contratoPrevFaturamento;
	}

	public List<HistFaturamentoEntity> getContratosHistoricosFaturamentos() {
		return contratosHistoricosFaturamentos;
	}

	public List<ContratoProduto> getContratosProduto() {
		return contratoProduto;
	}

	public void setContratosHistoricosFaturamentos(
			List<HistFaturamentoEntity> contratosHistoricosFaturamentos) {
		this.contratosHistoricosFaturamentos = contratosHistoricosFaturamentos;
	}

	public List<HistoricoReajusteEntity> getContratosHistoricosReajustes() {
		return contratosHistoricosReajustes;
	}

	public void setContratosHistoricosReajustes(
			List<HistoricoReajusteEntity> contratosHistoricosReajustes) {
		this.contratosHistoricosReajustes = contratosHistoricosReajustes;
	}

	public List<PrevFaturamentoEntity> getContratosPrevisoesFaturamentos() {
		return contratosPrevisoesFaturamentos;
	}

	public void setContratosPrevisoesFaturamentos(
			List<PrevFaturamentoEntity> contratosPrevisoesFaturamentos) {
		this.contratosPrevisoesFaturamentos = contratosPrevisoesFaturamentos;
	}

	/*
	 * public Date getPrimeiroVencimento() { return primeiroVencimento; }
	 * 
	 * public void setPrimeiroVencimento(Date primeiroVencimento) {
	 * this.primeiroVencimento = primeiroVencimento; }
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}