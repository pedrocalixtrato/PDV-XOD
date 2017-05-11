package dc.entidade.financeiro;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/** @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados. */
@Entity
@Table(name = "parcela_pagar")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaPagar extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "parcela_pagar_id_seq")
	@SequenceGenerator(name = "parcela_pagar_id_seq", sequenceName = "parcela_pagar_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	@Caption("Data Emissão")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataEmissao;

	@Caption("Data Vencimento")
	@Column(name = "DATA_VENCIMENTO")
	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )	
	private Date dataVencimento;

	@Caption("Desconto Até")
	@Column(name = "DESCONTO_ATE")
	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date descontoAte;

	@Caption("Sofre Retencao")
	@Column(name = "SOFRE_RETENCAO")
	@Field
	private String sofreRetencao;

	@Field
	@Caption("Valor")
	@Column(name = "VALOR")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;

	@Caption("Taxa Juro")
	@Column(name = "TAXA_JURO", precision = 14, scale = 0)
	@Field
	private BigDecimal taxaJuro;

	@Caption("Taxa Multa")
	@Column(name = "TAXA_MULTA", precision = 14, scale = 0)
	@Field
	private BigDecimal taxaMulta;

	@Caption("Taxa Desconto")
	@Column(name = "TAXA_DESCONTO", precision = 14, scale = 0)
	@Field
	private BigDecimal taxaDesconto;

	@Caption("Valor Juro")
	@NumberFormat(style=Style.CURRENCY)
	@Column(name = "VALOR_JURO", precision = 14, scale = 0)
	@Field
	private BigDecimal valorJuro;

	@Caption("Valor Multa")
	@NumberFormat(style=Style.CURRENCY)
	@Column(name = "VALOR_MULTA", precision = 14, scale = 0)
	@Field
	private BigDecimal valorMulta;

	@Caption("Valor Desconto")
	@NumberFormat(style=Style.CURRENCY)
	@Column(name = "VALOR_DESCONTO", precision = 14, scale = 0)
	@Field
	private BigDecimal valorDesconto;

	@Caption("Status Parcela")
	@JoinColumn(name = "id_status_parcela", referencedColumnName = "id")
	@ManyToOne(optional = false)
	//@ManyToOne(optional = false, fetch = FetchType.EAGER)
	//@Transient
	@IndexedEmbedded(includePaths={"descricao"})
	private StatusParcela statusParcela;

	@JoinColumn(name = "id_lancamento_pagar", referencedColumnName = "id")
	//@ManyToOne(optional = false)
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@Caption("Lançamento Pagar")
	@IndexedEmbedded(depth=3, includePaths={"fornecedor.pessoa.nome"})
	private LancamentoPagarEntity lancamentoPagar;

	/*
	 * @Caption(value = "Contrato")
	 * 
	 * @JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID")
	 * 
	 * @ManyToOne(optional = false) private Contrato contrato;
	 */

	@Caption("Número Parcela")
	@Column(name = "NUMERO_PARCELA")
	@Field
	private Integer numeroParcela;

	@Caption("Conta Caixa")
	@JoinColumn(name = "id_conta_caixa", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	//@NotNull(message = "Conta Caixa é Obrigatório!")
	private ContaCaixa contaCaixa;

	@OneToMany(mappedBy = "parcelaPagar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaPagamento> parcelapagamentos = new ArrayList<>();

	@Transient
	@Caption("Valor Faltante")
	@Field
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorFaltante;
	
	@Transient
	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de Pagamento")
	private Date dataPagamento;
	
	@Transient
	@Caption("Data de Pagamento")
	private ParcelaPagamento parcelaPagamentos;

	public ParcelaPagar() {
	}

	public ParcelaPagar(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDescontoAte() {
		return descontoAte;
	}

	public void setDescontoAte(Date descontoAte) {
		this.descontoAte = descontoAte;
	}

	public String getSofreRetencao() {
		return sofreRetencao;
	}

	public void setSofreRetencao(String sofreRetencao) {
		this.sofreRetencao = sofreRetencao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxaJuro() {
		return taxaJuro;
	}

	public void setTaxaJuro(BigDecimal taxaJuro) {
		this.taxaJuro = taxaJuro;
	}

	public BigDecimal getTaxaMulta() {
		return taxaMulta;
	}

	public void setTaxaMulta(BigDecimal taxaMulta) {
		this.taxaMulta = taxaMulta;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorJuro() {
		return valorJuro;
	}

	public void setValorJuro(BigDecimal valorJuro) {
		this.valorJuro = valorJuro;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof ParcelaPagar)) {
	           return false;
	    }

	    ParcelaPagar that = (ParcelaPagar) obj;
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

	@Override
	public String toString() {
		return getLancamentoPagar().getFornecedor().getNome();
	}

	/** @return the statusParcela */
	public StatusParcela getStatusParcela() {
		return statusParcela;
	}

	/** @param statusParcela
	 *            the statusParcela to set */
	public void setStatusParcela(StatusParcela statusParcela) {
		this.statusParcela = statusParcela;
	}

	/** @return the lancamentoPagar */
	public LancamentoPagarEntity getLancamentoPagar() {
		return lancamentoPagar;
	}

	/** @param lancamentoPagar
	 *            the lancamentoPagar to set */
	public void setLancamentoPagar(LancamentoPagarEntity lancamentoPagar) {
		this.lancamentoPagar = lancamentoPagar;
	}

	/*
	 * public Contrato getContrato() { return contrato; }
	 * 
	 * public void setContrato(Contrato contrato) { this.contrato = contrato; }
	 */

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public List<ParcelaPagamento> getParcelapagamentos() {
		return parcelapagamentos;
	}

	public void setParcelapagamentos(List<ParcelaPagamento> parcelapagamentos) {
		this.parcelapagamentos = parcelapagamentos;
	}

	public BigDecimal getValorFaltante() {
		valorFaltante = BigDecimal.ZERO.add(valor);
		for (ParcelaPagamento p : parcelapagamentos) {
			if (p.getValorPago() != null) {
				valorFaltante = valorFaltante.subtract(p.getValorPago());
			}
		}

		return valorFaltante;
	}
	
	public Date getDataPagamento() {
		for (ParcelaPagamento p : parcelapagamentos) {
			if (p.getDataPagamento() != null) {
				dataPagamento = p.getDataPagamento();
			}
		}
		return dataPagamento;
	}

	public ParcelaPagamento getParcelaPagamentos() {
		return parcelaPagamentos;
	}

	public void setParcelaPagamentos(ParcelaPagamento parcelaPagamentos) {
		this.parcelaPagamentos = parcelaPagamentos;
	}
	
}
