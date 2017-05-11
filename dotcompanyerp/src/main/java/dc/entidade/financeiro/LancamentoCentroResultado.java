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

import dc.anotacoes.Caption;
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
@Table(name = "lancamento_centro_resultado")

@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class LancamentoCentroResultado extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Ged Documento")
    @Column(name="GED_DOCUMENTO")
    private String gedDocumeto;
    
    @Column(name = "VALOR", precision = 18, scale = 6)
    private BigDecimal valor;
    
    @Column(name="HISTORICO")
    private String historico;
    
    @Column(name = "DATA_LANCAMENTO")
    @Temporal(TemporalType.DATE)
    @Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataLancamento;
    
    @Column(name = "DATA_INCLUSAO")
    @Temporal(TemporalType.DATE)
    @Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
    private Date dataInclusao;
    
    @Column(name="ORIGEM_DE_RATEIO")
    private Character origemRateio;
    
    @JoinColumn(name = "ID_CENTRO_RESULTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CentroResultado centroResultado;

    public LancamentoCentroResultado() {
    }

    public LancamentoCentroResultado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof LancamentoCentroResultado == false) return false;
    	if (this == object) return true;
    	final LancamentoCentroResultado other = (LancamentoCentroResultado) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

    public String getGedDocumeto() {
		return gedDocumeto;
	}

	public void setGedDocumeto(String gedDocumeto) {
		this.gedDocumeto = gedDocumeto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Character getOrigemRateio() {
		return origemRateio;
	}

	public void setOrigemRateio(Character origemRateio) {
		this.origemRateio = origemRateio;
	}

	/**
     * @return the centroResultado
     */
    public CentroResultado getCentroResultado() {
        return centroResultado;
    }

    /**
     * @param centroResultado the centroResultado to set
     */
    public void setCentroResultado(CentroResultado centroResultado) {
        this.centroResultado = centroResultado;
    }

}

