package dc.entidade.ordemservico;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.TipoPagamento;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "os_financeiro")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class FinanceiroOsEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServicoEntity ordemServico;

	@Caption(value = "Cliente")
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "id_tipo_pagamento", referencedColumnName = "id")
	private TipoPagamento tipoPagamento;

	@Column(name = "bandeira")
	private Integer bandeira;

	@Column(name = "titular")
	private String titular;

	@Column(name = "nr_cartao")
	private String numeroCartao;

	@Column(name = "nr_seguranca")
	private Integer codigoSeguranca;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_validade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataValidade;

	@Column(name = "dias")
	private Integer dias;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataVencimento;

	@Column(name = "valor", precision = 14, scale = 0)
	@Caption(value = "Valor")
	private BigDecimal valor;

	@Column(name = "nr_parcela")
	private Integer numeroParcela;

	@Column(name = "nr_carne")
	private Integer numeroCarne;

	@Column(name = "nr_original")
	private Integer numeroOriginal;

	@Column(name = "nf_numero")
	private Integer numeroNotaFiscal;

	@Column(name = "banco")
	private Integer banco;

	@Column(name = "agencia")
	private String agencia;

	@Column(name = "nr_conta")
	private String contaCorrente;

	@Column(name = "nr_cheque")
	private String numeroCheque;

	@Column(name = "nr_comprovante")
	private String comprovante;

	@Column(name = "fechamento_cartao")
	private String fechamentoCartao;

	@Field
	@Caption("Data Lançamento")
	@Column(name = "data_lancamento")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataLancamento;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServicoEntity getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServicoEntity ordemServico) {
		this.ordemServico = ordemServico;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public Integer getBandeira() {
		return bandeira;
	}

	public void setBandeira(Integer bandeira) {
		this.bandeira = bandeira;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Integer getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(Integer codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Integer getNumeroCarne() {
		return numeroCarne;
	}

	public void setNumeroCarne(Integer numeroCarne) {
		this.numeroCarne = numeroCarne;
	}

	public Integer getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(Integer numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getComprovante() {
		return comprovante;
	}

	public void setComprovante(String comprovante) {
		this.comprovante = comprovante;
	}

	public String getFechamentoCartao() {
		return fechamentoCartao;
	}

	public void setFechamentoCartao(String fechamentoCartao) {
		this.fechamentoCartao = fechamentoCartao;
	}

	public Integer getNumeroOriginal() {
		return numeroOriginal;
	}

	public void setNumeroOriginal(Integer numeroOriginal) {
		this.numeroOriginal = numeroOriginal;
	}
	
}
