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
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "parcela_receber")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaReceber extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "parcela_receber_id_seq")
	@SequenceGenerator(name = "parcela_receber_id_seq", sequenceName = "parcela_receber_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "NUMERO_PARCELA")
	@Caption(value = "Número Parcelas")
	private Integer numeroParcela;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EMISSAO")
	@Caption(value = "Data Emissão")
	private Date dataEmissao;
	
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO")
	@Caption(value = "Data Vencimento")
	private Date dataVencimento;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Temporal(TemporalType.DATE)
	@Column(name = "DESCONTO_ATE")
	@Caption(value = "Desconto até")
	private Date descontoAte;

	@Field
	@Column(name = "VALOR")
	@Caption(value = "Valor")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valor;

	@Column(name = "TAXA_JURO")
	@Field
	@Caption(value = "Taxa Juros")
	private BigDecimal taxaJuro;

	@Field
	@Column(name = "TAXA_MULTA")
	@Caption(value = "Taxa Multa")
	private BigDecimal taxaMulta;

	@Field
	@Column(name = "TAXA_DESCONTO")
	@Caption(value = "Taxa Desconto")
	private BigDecimal taxaDesconto;

	@Field
	@Column(name = "VALOR_JURO")
	@Caption(value = "Valor Juro")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorJuro;

	@Field
	@Column(name = "VALOR_MULTA")
	@Caption(value = "Valor Multa")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorMulta;

	@Field
	@Column(name = "VALOR_DESCONTO")
	@Caption(value = "Valor Desconto")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorDesconto;

	@Column(name = "EMITIU_BOLETO")
	@Field
	@Caption(value = "Emitiu Boleto")
	private String emitiuBoleto;

	@Column(name = "BOLETO_NOSSO_NUMERO")
	@Field
	@Caption(value = "Boleto Nosso Número")
	private String boletoNossoNumero;

	@JoinColumn(name = "ID_LANCAMENTO_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Lançamento à Receber")
	private LancamentoReceber lancamentoReceber;

	@JoinColumn(name = "ID_STATUS_PARCELA", referencedColumnName = "ID")
	//@Transient
	@ManyToOne(optional = false)
	private StatusParcela finStatusParcela;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Conta Caixa")
	private ContaCaixa contaCaixa;

	@OneToMany(mappedBy = "parcelaReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaRecebimento> parcelasRecebidas = new ArrayList<>();

	@Transient
	private boolean selecionado;

	@Transient
	@Caption(value = "Valor Faltante")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorFaltante;

	public ParcelaReceber() {
	}

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNomeCliente() {
		return lancamentoReceber.getCliente().getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
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

	public String getEmitiuBoleto() {
		return emitiuBoleto;
	}

	public void setEmitiuBoleto(String emitiuBoleto) {
		this.emitiuBoleto = emitiuBoleto;
	}

	public String getBoletoNossoNumero() {
		return boletoNossoNumero;
	}

	public void setBoletoNossoNumero(String boletoNossoNumero) {
		this.boletoNossoNumero = boletoNossoNumero;
	}

	public LancamentoReceber getLancamentoReceber() {
		return lancamentoReceber;
	}

	public void setLancamentoReceber(LancamentoReceber finLancamentoReceber) {
		this.lancamentoReceber = finLancamentoReceber;
	}

	public StatusParcela getStatusParcela() {
		return finStatusParcela;
	}

	public void setStatusParcela(StatusParcela finStatusParcela) {
		this.finStatusParcela = finStatusParcela;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	@Override
	public String toString() {
		return finStatusParcela.getDescricao();
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public BigDecimal getValorFaltante() {
		valorFaltante = BigDecimal.ZERO.add(valor);
		for (ParcelaRecebimento p : parcelasRecebidas) {
			if (p.getValorRecebido() != null) {
				valorFaltante = valorFaltante.subtract(p.getValorRecebido());
			}
		}

		return valorFaltante;
	}

	public void setValorFaltante(BigDecimal valorFaltante) {
		this.valorFaltante = valorFaltante;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ParcelaReceber)) {
            return false;
        }

        ParcelaReceber that = (ParcelaReceber) obj;
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
