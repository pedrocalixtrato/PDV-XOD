package dc.entidade.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "venda_condicoes_pagamento")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
@XmlRootElement
public class CondicaoPagamento extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_condicoes_pagamento_id_seq1")
	@SequenceGenerator(name = "venda_condicoes_pagamento_id_seq1", sequenceName = "venda_condicoes_pagamento_id_seq1", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name="nome")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Field
	@Column(name="descricao")
	@Caption("Descrição")
	private String descricao;
	
	@Field
	@Caption("Faturamento Mínimo")
	@Column(name="faturamento_minimo")
	private BigDecimal faturamentoMinimo;
	
	@Field
	@Caption("Faturamento Máximo")
	@Column(name="faturamento_maximo")
	private BigDecimal faturamentoMaximo;
	
	@Field
	@Caption("Índice Correção")
	@Column(name="indice_correcao")
	private BigDecimal indiceCorrecao;
	
	@Field
	@Column(name="dias_tolerancia")
	private Integer diasTolerancia;
	
	@Field
	@Column(name="valor_tolerancia")
	private BigDecimal valorTolerancia;
	
	@Field
	@Caption("Prazo Médio")
	@Column(name="prazo_medio")
	private Integer prazoMedio;
	
	@OneToMany(mappedBy = "condicaoPagamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaCondicaoPagamento> parcelas = new ArrayList<ParcelaCondicaoPagamento>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getFaturamentoMinimo() {
		return faturamentoMinimo;
	}

	public void setFaturamentoMinimo(BigDecimal faturamentoMinimo) {
		this.faturamentoMinimo = faturamentoMinimo;
	}

	public BigDecimal getFaturamentoMaximo() {
		return faturamentoMaximo;
	}

	public void setFaturamentoMaximo(BigDecimal faturamentoMaximo) {
		this.faturamentoMaximo = faturamentoMaximo;
	}

	public BigDecimal getIndiceCorrecao() {
		return indiceCorrecao;
	}

	public void setIndiceCorrecao(BigDecimal indiceCorrecao) {
		this.indiceCorrecao = indiceCorrecao;
	}

	public Integer getDiasTolerancia() {
		return diasTolerancia;
	}

	public void setDiasTolerancia(Integer diasTolerancia) {
		this.diasTolerancia = diasTolerancia;
	}

	public BigDecimal getValorTolerancia() {
		return valorTolerancia;
	}

	public void setValorTolerancia(BigDecimal valorTolerancia) {
		this.valorTolerancia = valorTolerancia;
	}

	public Integer getPrazoMedio() {
		return prazoMedio;
	}

	public void setPrazoMedio(Integer prazoMedio) {
		this.prazoMedio = prazoMedio;
	}

	public List<ParcelaCondicaoPagamento> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaCondicaoPagamento> parcelas) {
		this.parcelas = parcelas;
	}
	
	public void adicionarParcela(ParcelaCondicaoPagamento parcela){
	    if(parcelas == null) parcelas = new ArrayList<ParcelaCondicaoPagamento>();
		getParcelas().add(parcela);
		parcela.setCondicaoPagamento(this);
	}

	@Override
	public String toString() {
		return  nome ;
	}
	
   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }

       if (!(obj instanceof CondicaoPagamento)) {
           return false;
       }

       CondicaoPagamento that = (CondicaoPagamento) obj;
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
