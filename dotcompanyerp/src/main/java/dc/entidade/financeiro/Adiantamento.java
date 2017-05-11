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
import org.hibernate.annotations.Type;
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
@Table(name = "adiantamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Adiantamento extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adiantamento_id_seq")
	@SequenceGenerator(name = "adiantamento_id_seq", sequenceName = "adiantamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption("Data Adiantamento")
	@Column(name = "DATA_ADIANTAMENTO")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Data Adiantamento é Obrigatório!")
	private Date dataAdiantamento;

	@Field
	@Caption("Valor")
	@Column(name = "VALOR", precision = 14, scale = 0)
	@NumberFormat(style=Style.CURRENCY)
	@NotNull(message = "Valor é Obrigatório!")
	private BigDecimal valor;

	@Lob
	@Field
	@Caption("Observações")
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "OBSERVACOES")
	private String observacoes;

	@Caption("Lançamento Pagar")
	@JoinColumn(name = "ID_LANCAMENTO_PAGAR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@NotNull(message = "Lançamento Pagar é Obrigatório!")
	private LancamentoPagarEntity idLancamentoPagar;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * 
	 */

	/**
	 * CONSTRUTOR
	 */

	public Adiantamento() {

	}

	public Adiantamento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAdiantamento() {
		return dataAdiantamento;
	}

	public void setDataAdiantamento(Date dataAdiantamento) {
		this.dataAdiantamento = dataAdiantamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Adiantamento)) {
            return false;
        }

        Adiantamento that = (Adiantamento) obj;
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
		return ToStringBuilder.reflectionToString(this);
	}

	public LancamentoPagarEntity getIdLancamentoPagar() {
		return idLancamentoPagar;
	}

	public void setIdLancamentoPagar(LancamentoPagarEntity idLancamentoPagar) {
		this.idLancamentoPagar = idLancamentoPagar;
	}

}