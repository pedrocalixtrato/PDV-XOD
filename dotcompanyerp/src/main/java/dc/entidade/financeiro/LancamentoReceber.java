package dc.entidade.financeiro;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "lancamento_receber")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoReceber extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_receber_id_seq")
	@SequenceGenerator(name = "lancamento_receber_id_seq", sequenceName = "lancamento_receber_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "QUANTIDADE_PARCELA")
	@Caption(value = "Quantidade Parcela")
	@NotNull(message = "Quantidade Parcelas é Obrigatório!")
	private Integer quantidadeParcela;

	@Field
	@Column(name = "VALOR_TOTAL", precision = 18, scale = 6)
	@Caption(value = "Valor Total")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorTotal;

	@Field
	@Column(name = "VALOR_A_RECEBER", precision = 18, scale = 6)
	@Caption(value = "Valor Receber")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorAReceber;

	@Field(index = Index.YES, analyze=Analyze.NO, store = Store.YES)
	@FieldBridge(impl = DCDateBridge.class )
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_LANCAMENTO")
	@Caption(value = "Data Lançamento")
	private Date dataLancamento;

	@Field
	@Column(name = "NUMERO_DOCUMENTO")
	@Caption(value = "Número Documento")
	private String numeroDocumento;

	@Field(index = Index.YES, analyze=Analyze.NO, store = Store.YES)
	@FieldBridge(impl = DCDateBridge.class )
	@Temporal(TemporalType.DATE)
	@Column(name = "PRIMEIRO_VENCIMENTO")
	@Caption(value = "Primeiro Vencimento")
	@NotNull(message = "Primeiro Vencimento é Obrigatório!")
	private Date primeiroVencimento;

	@Field
	@Column(name = "TAXA_COMISSAO")
	@Caption(value = "Taxa Comissão")
	private BigDecimal taxaComissao;

	@Field
	@Column(name = "VALOR_COMISSAO",precision = 18, scale = 6)
	@Caption(value = "Valor Comissão")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissao;

	@Column(name = "INTERVALO_ENTRE_PARCELAS")
	@Field
	@Caption(value = "Inrvalo Parcelas")
	private Integer intervaloEntreParcelas;

	@Field
	@Column(name = "CODIGO_MODULO_LCTO")
	@Caption(value = "Código Módulo Lcto")
	private String codigoModuloLcto;

	@JoinColumn(name = "ID_DOCUMENTO_ORIGEM", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Documento Origem")
	@NotNull(message = "Documento Origem é Obrigatório!")
	@IndexedEmbedded(includePaths={"descricao"})
	private DocumentoOrigem documentoOrigem;

	@Caption(value = "Cliente")
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@NotNull(message = "Cliente é Obrigatório!")
	@IndexedEmbedded(depth=3, includePaths={"pessoa.nome"})
	private ClienteEntity cliente;

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaReceber> parcelasReceber = new ArrayList<>();

	@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<LctoReceberNtFinanceiraEntity> LctoReceberNtFinanceira = new ArrayList<>();
	
	//@OneToMany(mappedBy = "lancamentoReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	//@Fetch(FetchMode.SUBSELECT)
	//private List<CentroResultado> CentroResultado = new ArrayList<>();

	public LancamentoReceber() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorAReceber() {
		return valorAReceber;
	}

	public void setValorAReceber(BigDecimal valorAReceber) {
		this.valorAReceber = valorAReceber;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	public Integer getIntervaloEntreParcelas() {
		return intervaloEntreParcelas;
	}

	public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
		this.intervaloEntreParcelas = intervaloEntreParcelas;
	}

	public String getCodigoModuloLcto() {
		return codigoModuloLcto;
	}

	public void setCodigoModuloLcto(String codigoModuloLcto) {
		this.codigoModuloLcto = codigoModuloLcto;
	}

	public DocumentoOrigem getDocumentoOrigem() {
		return documentoOrigem;
	}

	public void setDocumentoOrigem(DocumentoOrigem documentoOrigem) {
		this.documentoOrigem = documentoOrigem;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/*
	 * public List<ParcelaReceber> getParcelaReceber() { return parcelaReceber;
	 * }
	 * 
	 * public void setParcelaReceber(List<ParcelaReceber> parcelaReceber) {
	 * this.parcelaReceber = parcelaReceber; }
	 */

	public List<LctoReceberNtFinanceiraEntity> getLctoReceberNtFinanceira() {
		return LctoReceberNtFinanceira;
	}

	public void setLctoReceberNtFinanceira(List<LctoReceberNtFinanceiraEntity> lctoReceberNtFinanceira) {
		LctoReceberNtFinanceira = lctoReceberNtFinanceira;
	}

	public void removeLctoReceberNtFinanceira(LctoReceberNtFinanceiraEntity value) {
		this.LctoReceberNtFinanceira.remove(value);
		value.setLancamentoReceber(null);
	}

	public LctoReceberNtFinanceiraEntity addLctoReceberNtFinanceira() {
		LctoReceberNtFinanceiraEntity lctoReceberNtFinanceira = new LctoReceberNtFinanceiraEntity();
		lctoReceberNtFinanceira.setLancamentoReceber(this);
		this.LctoReceberNtFinanceira.add(lctoReceberNtFinanceira);

		return lctoReceberNtFinanceira;
	}
	
	/*public void removeCentroResultado(CentroResultado value) {
		this.CentroResultado.remove(value);
		value.setPlanoCentroResultado(null);
	}

	public CentroResultado addCentroResultado() {
		CentroResultado centroResultado = new CentroResultado();
		//centroResultado.setPlanoCentroResultado(this);
		this.CentroResultado.add(centroResultado);

		return centroResultado;
	}*/

	public List<ParcelaReceber> getParcelasReceber() {
		return parcelasReceber;
	}

	public void setParcelasReceber(List<ParcelaReceber> parcelaReceber) {
		this.parcelasReceber = parcelaReceber;
	}

	public void removeParcelaReceber(ParcelaReceber value) {
		value.setLancamentoReceber(null);
		parcelasReceber.remove(value);

	}

	public void addParcelaReceber(ParcelaReceber parcela) {
		parcela.setLancamentoReceber(this);
		this.parcelasReceber.add(parcela);

	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LancamentoReceber)) {
            return false;
        }

        LancamentoReceber that = (LancamentoReceber) obj;
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
