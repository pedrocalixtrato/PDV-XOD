package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.type.StatusChequeType;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
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
@Table(name = "cheque")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Cheque extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
    private Integer id;
    
    @Field
    @Caption("Numero")
    @Column(name = "NUMERO")
    @ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "Número é Obrigatório!")
    private Integer numero;
    
    @Enumerated(EnumType.STRING)
	@Field
	@Caption("Status Cheque")
	@Column(name = "STATUS_CHEQUE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Status Cheque é Obrigatório!")
	private StatusChequeType statusCheque;
    
    @Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    @Caption("Data Status")
    @Column(name = "DATA_STATUS")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data Status Cheque é Obrigatório!")
    private Date dataStatus;
    
    @Caption("Talonário Cheque")
	//@ManyToOne(cascade = { CascadeType.PERSIST })
	@ManyToOne
	@JoinColumn(name = "ID_TALONARIO_CHEQUE", nullable = true)
	@NotNull(message = "Talonário Cheque é obrigatório") 
    @IndexedEmbedded(includePaths={"talao"})
	private TalonarioCheque idTalonarioCheque;

    public Cheque() {
    }

    public Cheque(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public StatusChequeType getStatusCheque() {
        return statusCheque;
    }

    public void setStatusCheque(StatusChequeType statusCheque) {
        this.statusCheque = statusCheque;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public TalonarioCheque getIdTalonarioCheque() {
		return idTalonarioCheque;
	}

	public void setIdTalonarioCheque(TalonarioCheque idTalonarioCheque) {
		this.idTalonarioCheque = idTalonarioCheque;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof Cheque)) {
	           return false;
	    }

	    Cheque that = (Cheque) obj;
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
