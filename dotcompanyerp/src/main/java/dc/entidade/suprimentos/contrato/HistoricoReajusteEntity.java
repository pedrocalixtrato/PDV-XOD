package dc.entidade.suprimentos.contrato;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "contrato_historico_reajuste")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class HistoricoReajusteEntity extends
		AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrato_historico_reajuste_id_seq")
	@SequenceGenerator(name = "contrato_historico_reajuste_id_seq", sequenceName = "contrato_historico_reajuste_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "INDICE")
	@Field
	private BigDecimal indice;

	@Column(name = "VALOR_ANTERIOR")
	@Field
	private BigDecimal valorAnterior;

	@Column(name = "VALOR_ATUAL")
	@Field
	private BigDecimal valorAtual;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_REAJUSTE")
	@Field
	private Date dataReajuste;

	@Column(name = "OBSERVACAO")
	@Field
	private String observacao;

	@JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContratoEntity contrato;

	public HistoricoReajusteEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getIndice() {
		return indice;
	}

	public void setIndice(BigDecimal indice) {
		this.indice = indice;
	}

	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Date getDataReajuste() {
		return dataReajuste;
	}

	public void setDataReajuste(Date dataReajuste) {
		this.dataReajuste = dataReajuste;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ContratoEntity getContrato() {
		return contrato;
	}

	public void setContrato(ContratoEntity contrato) {
		this.contrato = contrato;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}