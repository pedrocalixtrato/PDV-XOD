package dc.entidade.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
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
@Table(name = "folha_ferias_coletivas")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FeriasColetivasEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_ferias_coletivas_id_seq")
	@SequenceGenerator(name = "folha_ferias_coletivas_id_seq", sequenceName = "folha_ferias_coletivas_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_inicio")
	@Field
	@Caption("Data início")
	private Date dataInicio;

	@Column(name = "data_fim")
	@Field
	@Caption("Data término")
	private Date dataFim;

	@Column(name = "dias_gozo")
	@Field
	@Caption("Dias de gozo")
	private Integer diasGozo = new Integer(0);

	@Column(name = "abono_pecuniario_inicio")
	@Field
	@Caption("Abono pecuniário início")
	private Date abonoPecuniarioInicio;

	@Column(name = "abono_pecuniario_fim")
	@Field
	@Caption("Abono pecuniário término")
	private Date abonoPecuniarioFim;

	@Column(name = "dias_abono")
	@Field
	@Caption("Dias de abono")
	private Integer diasAbono = new Integer(0);

	@Column(name = "data_pagamento")
	@Field
	@Caption("Data do pagamento")
	private Date dataPagamento;

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
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public FeriasColetivasEntity() {
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDiasGozo() {
		return diasGozo;
	}

	public void setDiasGozo(Integer diasGozo) {
		this.diasGozo = diasGozo;
	}

	public Date getAbonoPecuniarioInicio() {
		return abonoPecuniarioInicio;
	}

	public void setAbonoPecuniarioInicio(Date abonoPecuniarioInicio) {
		this.abonoPecuniarioInicio = abonoPecuniarioInicio;
	}

	public Date getAbonoPecuniarioFim() {
		return abonoPecuniarioFim;
	}

	public void setAbonoPecuniarioFim(Date abonoPecuniarioFim) {
		this.abonoPecuniarioFim = abonoPecuniarioFim;
	}

	public Integer getDiasAbono() {
		return diasAbono;
	}

	public void setDiasAbono(Integer diasAbono) {
		this.diasAbono = diasAbono;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/*
	 * public Empresa getEmpresa() { return empresa; }
	 * 
	 * public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}