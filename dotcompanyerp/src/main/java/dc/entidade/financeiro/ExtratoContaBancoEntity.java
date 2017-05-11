package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

/**
*
* @author Wesley Junior
*/
@Entity
@Table(name = "fin_extrato_conta_banco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ExtratoContaBancoEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
    private Integer id;
	
	@Field
	@Caption("Mês")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "MES")
    private String mes;
	
	@Field
	@Caption("Ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "ANO")
    private String ano;
    
	@Caption("Data Movimento")
    @Column(name = "DATA_MOVIMENTO")
    @Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataMovimento;
    
	@Caption("Data Balancete")
    @Column(name = "DATA_BALANCETE")
    @Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataBalancete;
    
	@Field
	@Caption("Histórico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "HISTORICO")
    private String historico;
    
	@Field
	@Caption("Documento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "DOCUMENTO")
    private String documento;
    
	@Field
	@Caption("Valor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "VALOR")
    private BigDecimal valor;
    
	@Field
	@Caption("Conciliado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "CONCILIADO")
    private String conciliado;
    
    @Lob
    @Field
	@Caption("Observação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @Column(name = "observacao")
    private String observacoes;
    
    @Caption("Conta Caixa")
    @JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
    //@JoinColumn(name = "ID_CONTA_CAIXA", nullable = true)
	//@ManyToOne()
    @ManyToOne(optional = false)
    private ContaCaixa contaCaixa;
    
    
    /*@OneToMany(mappedBy = "extratoConta", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<ExtratoContaBancoEntity> itens = new ArrayList<>();*/

   public ExtratoContaBancoEntity() {
   }

   public ExtratoContaBancoEntity(Integer id) {
       this.id = id;
   }

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public String getMes() {
       return mes;
   }

   public void setMes(String mes) {
       this.mes = mes;
   }

   public String getAno() {
       return ano;
   }

   public void setAno(String ano) {
       this.ano = ano;
   }

   public Date getDataMovimento() {
       return dataMovimento;
   }

   public void setDataMovimento(Date dataMovimento) {
       this.dataMovimento = dataMovimento;
   }

   public Date getDataBalancete() {
       return dataBalancete;
   }

   public void setDataBalancete(Date dataBalancete) {
       this.dataBalancete = dataBalancete;
   }

   public String getHistorico() {
       return historico;
   }

   public void setHistorico(String historico) {
       this.historico = historico;
   }

   public String getDocumento() {
       return documento;
   }

   public void setDocumento(String documento) {
       this.documento = documento;
   }

   public BigDecimal getValor() {
       return valor;
   }

   public void setValor(BigDecimal valor) {
       this.valor = valor;
   }

   public String getConciliado() {
       return conciliado;
   }

   public void setConciliado(String conciliado) {
       this.conciliado = conciliado;
   }

   public String getObservacoes() {
       return observacoes;
   }

   public void setObservacoes(String observacoes) {
       this.observacoes = observacoes;
   }

   /**
    * @return the contaCaixa
    */
   public ContaCaixa getContaCaixa() {
       return contaCaixa;
   }

   /**
    * @param contaCaixa the contaCaixa to set
    */
   public void setContaCaixa(ContaCaixa contaCaixa) {
       this.contaCaixa = contaCaixa;
   }
   
   /**
	 * TO STRING
	 */
	
	@Override
	public String toString() {
		return ano;
	}

//@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}

   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }

       if (!(obj instanceof ExtratoContaBancoEntity)) {
           return false;
       }

       ExtratoContaBancoEntity that = (ExtratoContaBancoEntity) obj;
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
   
   /*public List<ExtratoContaBancoEntity> getExtrato() {
	return itens;
}

public void setExtrato(List<ExtratoContaBancoEntity> itens) {
	this.itens = itens;
}

public ExtratoContaBancoEntity addExtratoContaBancoItem(ExtratoContaBancoEntity extratoItem) {
		getExtrato().add(extratoItem);
		extratoItem.setContaCaixa(null);
		return extratoItem;
	}

	public ExtratoContaBancoEntity removeExtratoContaBancoItem(ExtratoContaBancoEntity extratoItem) {
		getExtrato().remove(extratoItem);
		extratoItem.setContaCaixa(null);
		return extratoItem;
	}*/

}

