package dc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */
@Entity
@Table(name = "parcela_pagamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParcelaPagamento extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcela_pagamento_id_seq")
	@SequenceGenerator(name = "parcela_pagamento_id_seq", sequenceName = "parcela_pagamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption("Data de Pagamento")
	@Column(name = "DATA_PAGAMENTO")
	private Date dataPagamento;

	@Column(name = "TAXA_JURO")
	@Field
	@Caption("Taxa Juro")
	//@NotNull(message = "Taxa Juro é Obrigatório!")
	private BigDecimal taxaJuro;

	@Column(name = "TAXA_MULTA")
	@Field
	@Caption("Taxa Multa")
	private BigDecimal taxaMulta;

	@Column(name = "TAXA_DESCONTO")
	@Field
	@Caption("Taxa de Desconto")
	private BigDecimal taxaDesconto;

	@Column(name = "VALOR_JURO")
	@Field
	@Caption("Valor Juro")
	private BigDecimal valorJuro;

	@Column(name = "VALOR_MULTA")
	@Field
	@Caption("Valor de Multa")
	private BigDecimal valorMulta;

	@Column(name = "VALOR_DESCONTO")
	@Field
	@Caption("Valor de Desconto")
	private BigDecimal valorDesconto;

	@Column(name = "VALOR_PAGO")
	@Field
	@Caption("Valor Pago")
	private BigDecimal valorPago;

	@Column(name = "HISTORICO")
	@Field
	@Caption("Histórico")
	private String historico;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_FIN_PARCELA_PAGAR", referencedColumnName = "ID")
	//@ManyToOne(fetch = FetchType.EAGER)
	@Caption("Parcela à Pagar")
	private ParcelaPagar parcelaPagar;

	@Caption("Cheque Emitido")
	@JoinColumn(name = "ID_FIN_CHEQUE_EMITIDO", referencedColumnName = "ID")
	@ManyToOne
	private ChequeEmitido chequeEmitido;

	@Caption("Tipo Pagamento")
	@JoinColumn(name = "ID_FIN_TIPO_PAGAMENTO", referencedColumnName = "ID", nullable = false)
	//@ManyToOne(optional = false)
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	//@NotNull(message = "Tipo Pagamento é Obrigatório!")
	@IndexedEmbedded(includePaths={"descricao"})
	private TipoPagamento tipoPagamento;

	@Caption("Conta Caixa")
	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	//@NotNull(message = "Conta Caixa é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private ContaCaixa contaCaixa;

	public ParcelaPagamento() {
	}

	public ParcelaPagamento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ParcelaPagamento)) {
            return false;
        }

        ParcelaPagamento that = (ParcelaPagamento) obj;
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
		return getContaCaixa().getNome();
	}

	/**
	 * @return the chequeEmitido
	 */
	public ChequeEmitido getChequeEmitido() {
		return chequeEmitido;
	}

	/**
	 * @param chequeEmitido
	 *            the chequeEmitido to set
	 */
	public void setChequeEmitido(ChequeEmitido chequeEmitido) {
		this.chequeEmitido = chequeEmitido;
	}

	/**
	 * @return the tipoPagamento
	 */
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento
	 *            the tipoPagamento to set
	 */
	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * @return the parcelaPagar
	 */
	public ParcelaPagar getParcelaPagar() {
		return parcelaPagar;
	}

	/**
	 * @param parcelaPagar
	 *            the parcelaPagar to set
	 */
	public void setParcelaPagar(ParcelaPagar parcelaPagar) {
		this.parcelaPagar = parcelaPagar;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

}
