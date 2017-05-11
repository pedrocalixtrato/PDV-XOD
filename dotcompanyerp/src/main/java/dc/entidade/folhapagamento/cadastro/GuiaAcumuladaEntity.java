package dc.entidade.folhapagamento.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "guias_acumuladas")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class GuiaAcumuladaEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "guias_acumuladas_id_seq")
	@SequenceGenerator(name = "guias_acumuladas_id_seq", sequenceName = "guias_acumuladas_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Caption("Gps tipo")
	@Column(name = "gps_tipo")
	@NotNull(message = "GPS Tipo é Obrigatório!")
	private String gpsTipo = "";

	@Field
	@Caption("Gps competência")
	@Column(name = "gps_competencia", length = 1)
	@NotNull(message = "Gps competência é Obrigatório!")
	private String gpsCompetencia = "";

	@Field
	@Caption("Gps valor INSS")
	@Column(name = "gps_valor_inss", precision = 14, scale = 0)
	@NotNull(message = "Gps valor INSS é Obrigatório!")
	private BigDecimal gpsValorInss;

	@Field
	@Caption("Gps valor outras ent")
	@Column(name = "gps_valor_outras_ent", precision = 14, scale = 0)
	@NotNull(message = "Gps valor outras ent é Obrigatório!")
	private BigDecimal gpsValorOutrasEnt ;

	@Field
	@Caption("Gps data pagamento")
	@Column(name = "gps_data_pagamento")
	@NotNull(message = "Gps data pagamento é Obrigatório!")
	private Date gpsDataPagamento;

	@Field
	@Caption("IRRF competência")
	@Column(name = "irrf_competencia")
	@NotNull(message = "IRRF competência é Obrigatório!")
	private String irrfCompetencia = "";

	@Field
	@Caption("IRRF código recolhimento")
	@Column(name = "irrf_codigo_recolhimento")
	@NotNull(message = "IRRF código recolhimento é Obrigatório!")
	private Integer irrfCodigoRecolhimento ;

	@Field
	@Caption("IRRF valor acumulado")
	@Column(name = "irrf_valor_acumulado", precision = 14, scale = 0)
	@NotNull(message = "IRRF valor acumulado é Obrigatório!")
	private BigDecimal irrfValorAcumulado ;

	@Field
	@Caption("IRRF data pagamento")
	@Column(name = "irrf_data_pagamento")
	@NotNull(message = "IRRF data pagamento é Obrigatório!")
	private Date irrfDataPagamento;

	@Field
	@Caption("PIS competência")
	@Column(name = "pis_competencia")
	@NotNull(message = "PIS competência é Obrigatório!")
	private String pisCompetencia = "";

	@Field
	@Caption("PIS valor acumulado")
	@Column(name = "pis_valor_acumulado", precision = 14, scale = 0)
	@NotNull(message = "PIS valor acumulado é Obrigatório!")
	private BigDecimal pisValorAcumulado;

	@Field
	@Caption("PIS Data Pagamento")
	@Column(name = "pis_data_pagamento")
	@NotNull(message = "PIS Data Pagamento é Obrigatório!")
	private Date pisDataPagamento;

	/**
	 * REFERENCIA - FK
	 */

	/* id_empresa integer NOT NULL, */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_empresa", nullable = false)
	 * 
	 * @Caption("Empresa")
	 * 
	 * @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	 * private Empresa empresa;
	 */

	/**
	 * LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public GuiaAcumuladaEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGpsTipo() {
		return gpsTipo;
	}

	public void setGpsTipo(String gpsTipo) {
		this.gpsTipo = (gpsTipo == null ? "" : gpsTipo.toUpperCase());
	}

	public String getGpsCompetencia() {
		return gpsCompetencia;
	}

	public void setGpsCompetencia(String gpsCompetencia) {
		this.gpsCompetencia = (gpsCompetencia == null ? "" : gpsCompetencia
				.toUpperCase());
	}

	public Date getGpsDataPagamento() {
		return gpsDataPagamento;
	}

	public void setGpsDataPagamento(Date gpsDataPagamento) {
		this.gpsDataPagamento = gpsDataPagamento;
	}

	public String getIrrfCompetencia() {
		return irrfCompetencia;
	}

	public void setIrrfCompetencia(String irrfCompetencia) {
		this.irrfCompetencia = (irrfCompetencia == null ? "" : irrfCompetencia
				.toUpperCase());
	}

	public Integer getIrrfCodigoRecolhimento() {
		return irrfCodigoRecolhimento;
	}

	public void setIrrfCodigoRecolhimento(Integer irrfCodigoRecolhimento) {
		this.irrfCodigoRecolhimento = (irrfCodigoRecolhimento == null ? new Integer(
				0) : irrfCodigoRecolhimento);
	}

	public Date getIrrfDataPagamento() {
		return irrfDataPagamento;
	}

	public void setIrrfDataPagamento(Date irrfDataPagamento) {
		this.irrfDataPagamento = irrfDataPagamento;
	}

	public String getPisCompetencia() {
		return pisCompetencia;
	}

	public void setPisCompetencia(String pisCompetencia) {
		this.pisCompetencia = (pisCompetencia == null ? "" : pisCompetencia
				.toUpperCase());
	}

	public Date getPisDataPagamento() {
		return pisDataPagamento;
	}

	public void setPisDataPagamento(Date pisDataPagamento) {
		this.pisDataPagamento = pisDataPagamento;
	}
	
	public BigDecimal getGpsValorInss() {
		return gpsValorInss;
	}

	public void setGpsValorInss(BigDecimal gpsValorInss) {
		this.gpsValorInss = gpsValorInss;
	}

	public BigDecimal getGpsValorOutrasEnt() {
		return gpsValorOutrasEnt;
	}

	public void setGpsValorOutrasEnt(BigDecimal gpsValorOutrasEnt) {
		this.gpsValorOutrasEnt = gpsValorOutrasEnt;
	}

	public BigDecimal getIrrfValorAcumulado() {
		return irrfValorAcumulado;
	}

	public void setIrrfValorAcumulado(BigDecimal irrfValorAcumulado) {
		this.irrfValorAcumulado = irrfValorAcumulado;
	}

	public BigDecimal getPisValorAcumulado() {
		return pisValorAcumulado;
	}

	public void setPisValorAcumulado(BigDecimal pisValorAcumulado) {
		this.pisValorAcumulado = pisValorAcumulado;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return gpsTipo;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof GuiaAcumuladaEntity)) {
	           return false;
	    }

	    GuiaAcumuladaEntity that = (GuiaAcumuladaEntity) obj;
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