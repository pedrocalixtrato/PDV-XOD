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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.visao.spring.DCDateBridge;


/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, está diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/
@Entity
@Table(name = "cheque_emitido")

@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ChequeEmitido extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "DATA_EMISSAO")
    @Temporal(TemporalType.DATE)
    @Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataEmissao;
    
    @Column(name = "DATA_COMPENSACAO")
    @Temporal(TemporalType.DATE)
    @Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataCompensacao;
    
    @Column(name = "VALOR", precision = 14, scale = 0)
    private BigDecimal valor;
    
    @Column(name = "NOMINAL_A")
    private String nominalA;
    
    @JoinColumn(name = "ID_CHEQUE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cheque cheque;

    public ChequeEmitido() {
    }

    public ChequeEmitido(Integer id) {
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

    public Date getDataCompensacao() {
        return dataCompensacao;
    }

    public void setDataCompensacao(Date dataCompensacao) {
        this.dataCompensacao = dataCompensacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNominalA() {
        return nominalA;
    }

    public void setNominalA(String nominalA) {
        this.nominalA = nominalA;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof ChequeEmitido == false) return false;
    	if (this == object) return true;
    	final ChequeEmitido other = (ChequeEmitido) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the cheque
     */
    public Cheque getCheque() {
        return cheque;
    }

    /**
     * @param cheque the cheque to set
     */
    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

}
