package dc.entidade.comercial;

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
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "venda_orcamento_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Orcamento extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tnf")
	@SequenceGenerator(name = "tnf", sequenceName = "venda_orcamento_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	@Caption(value = "Data de cadastro")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	@Caption(value = "Data de entrega")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name = "validade")
	@Caption(value = "Data de validade")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataValidade;

	@Field
	@Column(name = "valor_subtotal")
	@Caption(value = "Valor subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubTotal;

	@Field
	@Column(name = "valor_frete")
	@Caption(value = "Valor do frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorFrete;

	@Field
	@Column(name = "taxa_comissao")
	@Caption(value = "Taxa de comissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaComissao;

	@Field
	@Column(name = "valor_comissao")
	@Caption(value = "Valor da comissão")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorComissao;

	@Field
	@Column(name = "taxa_desconto")
	@Caption(value = "Taxa de desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto;

	@Field
	@Column(name = "valor_desconto")
	@Caption(value = "Valor do desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto;

	@Field
	@Column(name = "valor_total")
	@Caption(value = "Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal;

	@Field
	@Column(name = "observacao")
	@Caption(value = "Observação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	/**
	 * REFERENCIA - FK
	 */


    @JoinColumn(name = "id_venda_condicoes_pagamento", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Condição Pagamento é Obrigatório!")
    private CondicaoPagamento condicaoPagamento;
    
    @JoinColumn(name = "ID_TRANSPORTADORA", referencedColumnName = "ID")
    @ManyToOne
    private TransportadoraEntity transportadora;
    
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClienteEntity cliente;
    
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Vendedor é Obrigatório!")
    private VendedorEntity vendedor;


	/**
	 * REFERENCIA - LIST
	 */

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public Orcamento() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public void adicionarItem(ItemOrcamento item) {
		getItens().add(item);
		item.setOrcamento(this);
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
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

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TransportadoraEntity getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(TransportadoraEntity transportadora) {
		this.transportadora = transportadora;
	}
	
	public void removeItem(ItemOrcamento value) {
		this.itens.remove(value);
		value.setOrcamento(null);
	}
	
	public ItemOrcamento addItem(ItemOrcamento lctoPagarNtFinanceira) {
		getItens().add(lctoPagarNtFinanceira);
		lctoPagarNtFinanceira.setOrcamento(this);;

		return lctoPagarNtFinanceira;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Orcamento)) {
            return false;
        }

        Orcamento that = (Orcamento) obj;
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
	
	

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}