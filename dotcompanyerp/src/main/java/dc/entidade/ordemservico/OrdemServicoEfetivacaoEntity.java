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
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "os_ordem_servico_efetivacao")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class OrdemServicoEfetivacaoEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServicoEntity ordemServico;

	@ManyToOne
	@JoinColumn(name = "id_tipo_efetivacao", referencedColumnName = "id")
	private TipoEfetivacaoOsEntity tipoEfetivacao;
	
	@Field
	@Caption("Banco")
	@Column(name = "banco")
	private Integer banco;

	@Field
	@Caption("Agencia")
	@Column(name = "agencia")
	private Integer agencia;

	@Field
	@Caption("Nr. Conta")
	@Column(name = "nr_conta")
	private Integer contaCorrente;

	@Field
	@Caption("Nr. Cheque")
	@Column(name = "nr_cheque")
	private Integer numeroCheque;

	@Field
	@Caption("Correntista")
	@Column(name = "correntista")
	private String correntista;

	@Field
	@Caption("Nr. Documento")
	@Column(name = "numero_doc")
	private Integer numeroDocumento;

	@Field
	@Caption("Nr. Original")
	@Column(name = "nr_original")
	private Integer numeroOriginal;

	@Field
	@Caption("Nr. Nota fiscal")
	@Column(name = "nf_numero")
	private Integer numeroNotaFiscal;
	
	@Field
	@Caption("Bandeira")
	@Column(name = "bandeira")
	private String bandeira;

	@Field
	@Caption("Titular")
	@Column(name = "titular")
	private String titular;

	@Field
	@Caption("Nr. Cartão")
	@Column(name = "nr_cartao")
	private String numeroCartao;

	@Field
	@Caption("Cód. Segurança")
	@Column(name = "nr_seguranca")
	private String codigoSeguranca;
	
	@Field
	@Caption("Validade")
	@Column(name = "data_validade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
	
	@Field
	@Caption("Dias")
	@Column(name = "dias")
	private Integer dias;

	@Field
	@Caption("Valor")
	@Column(name = "valor")
	private BigDecimal valorTotal;

	@Field
	@Caption("Vencimento")
	@Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    
	@Field
	@Caption("Data efetivacao")
	@Column(name = "data_efetivacao")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEfetivacao;

	@Field
	@Caption("Ordem Parcela")
	@Column(name = "ordem_parcela")
	private Integer ordemParcela;
	
	@Field
	@Caption("Comprovante")
	@Column(name = "comprovante_venda")
	private String comprovanteVenda;

	@Caption("Tipo")
	@Transient
	private String tipo;

	@Caption("Quantidade")
	@Transient
	private Integer quantidade;

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

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(Integer contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Integer getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getCorrentista() {
		return correntista;
	}

	public void setCorrentista(String correntista) {
		this.correntista = correntista;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataEfetivacao() {
		return dataEfetivacao;
	}

	public void setDataEfetivacao(Date dataEfetivacao) {
		this.dataEfetivacao = dataEfetivacao;
	}

	public Integer getOrdemParcela() {
		return ordemParcela;
	}

	public void setOrdemParcela(Integer ordemParcela) {
		this.ordemParcela = ordemParcela;
	}

	public TipoEfetivacaoOsEntity getTipoEfetivacao() {
		return tipoEfetivacao;
	}

	public void setTipoEfetivacao(TipoEfetivacaoOsEntity tipoEfetivacao) {
		this.tipoEfetivacao = tipoEfetivacao;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getNumeroOriginal() {
		return numeroOriginal;
	}

	public void setNumeroOriginal(Integer numeroOriginal) {
		this.numeroOriginal = numeroOriginal;
	}

	public Integer getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(Integer numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
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

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getComprovanteVenda() {
		return comprovanteVenda;
	}

	public void setComprovanteVenda(String comprovanteVenda) {
		this.comprovanteVenda = comprovanteVenda;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}