package dc.entidade.folhapagamento.cadastro;

import java.io.Serializable;

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
@Table(name = "folha_parametros")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParametroEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_parametros_id_seq")
	@SequenceGenerator(name = "folha_parametros_id_seq", sequenceName = "folha_parametros_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "competencia")
	@Field
	@Caption("Competência")
	private String competencia = "";

	@Column(name = "contribui_pis")
	@Field
	@Caption("Contribui PIS")
	private String contribuiPis = "";

	@Column(name = "aliquota_pis")
	@Field
	@Caption("Alíquota PIS")
	private String aliquotaPis = "";

	@Column(name = "discriminar_dsr")
	@Field
	@Caption("Discriminar DSR")
	private String discriminarDsr = "";

	@Column(name = "dia_pagamento")
	@Field
	@Caption("Dia do pagamento")
	private String diaPagamento = "";

	@Column(name = "calculo_proporcionalidade")
	@Field
	@Caption("Cálculo da proporcionalidade")
	private String calculoProporcionalidade = "";

	@Column(name = "descontar_faltas_13")
	@Field
	@Caption("Descontar faltas 13")
	private String descontarFaltas13 = "";

	@Column(name = "pagar_adicionais_13")
	@Field
	@Caption("Pagar adicionais 13")
	private String pagarAdicionais13 = "";

	@Column(name = "pagar_estagiarios_13")
	@Field
	@Caption("Pagar estagiários 13")
	private String pagarEstagiarios13 = "";

	@Column(name = "mes_adiantamento_13")
	@Field
	@Caption("Mês do adiantamento 13")
	private String mesAdiantamento13 = "";

	@Column(name = "percentual_adiantam_13")
	@Field
	@Caption("Percentual do adiantamento 13")
	private Double percentualAdiantam13 = new Double(0.0);

	@Column(name = "ferias_descontar_faltas")
	@Field
	@Caption("Férias descontar faltas")
	private String feriasDescontarFaltas = "";

	@Column(name = "ferias_pagar_adicionais")
	@Field
	@Caption("Férias pagar adicionais")
	private String feriasPagarAdicionais = "";

	@Column(name = "ferias_adiantar_13")
	@Field
	@Caption("Férias adiantar 13")
	private String feriasAdiantar13 = "";

	@Column(name = "ferias_pagar_estagiarios")
	@Field
	@Caption("Férias pagar estagiários")
	private String feriasPagarEstagiarios = "";

	@Column(name = "ferias_calc_justa_causa")
	@Field
	@Caption("Férias cálcular justa causa")
	private String feriasCalcJustaCausa = "";

	@Column(name = "ferias_movimento_mensal")
	@Field
	@Caption("Férias movimento mensal")
	private String feriasMovimentoMensal = "";

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

	public ParametroEntity() {
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

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = (competencia == null ? "" : competencia
				.toUpperCase());
	}

	public String getContribuiPis() {
		return contribuiPis;
	}

	public void setContribuiPis(String contribuiPis) {
		this.contribuiPis = (contribuiPis == null ? "" : contribuiPis
				.toUpperCase());
	}

	public String getAliquotaPis() {
		return aliquotaPis;
	}

	public void setAliquotaPis(String aliquotaPis) {
		this.aliquotaPis = (aliquotaPis == null ? "" : aliquotaPis
				.toUpperCase());
	}

	public String getDiscriminarDsr() {
		return discriminarDsr;
	}

	public void setDiscriminarDsr(String discriminarDsr) {
		this.discriminarDsr = (discriminarDsr == null ? "" : discriminarDsr
				.toUpperCase());
	}

	public String getDiaPagamento() {
		return diaPagamento;
	}

	public void setDiaPagamento(String diaPagamento) {
		this.diaPagamento = (diaPagamento == null ? "" : diaPagamento
				.toUpperCase());
	}

	public String getCalculoProporcionalidade() {
		return calculoProporcionalidade;
	}

	public void setCalculoProporcionalidade(String calculoProporcionalidade) {
		this.calculoProporcionalidade = (calculoProporcionalidade == null ? ""
				: calculoProporcionalidade.toUpperCase());
	}

	public String getDescontarFaltas13() {
		return descontarFaltas13;
	}

	public void setDescontarFaltas13(String descontarFaltas13) {
		this.descontarFaltas13 = (descontarFaltas13 == null ? ""
				: descontarFaltas13.toUpperCase());
	}

	public String getPagarAdicionais13() {
		return pagarAdicionais13;
	}

	public void setPagarAdicionais13(String pagarAdicionais13) {
		this.pagarAdicionais13 = (pagarAdicionais13 == null ? ""
				: pagarAdicionais13.toUpperCase());
	}

	public String getPagarEstagiarios13() {
		return pagarEstagiarios13;
	}

	public void setPagarEstagiarios13(String pagarEstagiarios13) {
		this.pagarEstagiarios13 = (pagarEstagiarios13 == null ? ""
				: pagarEstagiarios13.toUpperCase());
	}

	public String getMesAdiantamento13() {
		return mesAdiantamento13;
	}

	public void setMesAdiantamento13(String mesAdiantamento13) {
		this.mesAdiantamento13 = (mesAdiantamento13 == null ? ""
				: mesAdiantamento13.toUpperCase());
	}

	public Double getPercentualAdiantam13() {
		return percentualAdiantam13;
	}

	public void setPercentualAdiantam13(Double percentualAdiantam13) {
		this.percentualAdiantam13 = (percentualAdiantam13 == null ? new Double(
				0.0) : percentualAdiantam13);
	}

	public String getFeriasDescontarFaltas() {
		return feriasDescontarFaltas;
	}

	public void setFeriasDescontarFaltas(String feriasDescontarFaltas) {
		this.feriasDescontarFaltas = (feriasDescontarFaltas == null ? ""
				: feriasDescontarFaltas.toUpperCase());
	}

	public String getFeriasPagarAdicionais() {
		return feriasPagarAdicionais;
	}

	public void setFeriasPagarAdicionais(String feriasPagarAdicionais) {
		this.feriasPagarAdicionais = (feriasPagarAdicionais == null ? ""
				: feriasPagarAdicionais.toUpperCase());
	}

	public String getFeriasAdiantar13() {
		return feriasAdiantar13;
	}

	public void setFeriasAdiantar13(String feriasAdiantar13) {
		this.feriasAdiantar13 = (feriasAdiantar13 == null ? ""
				: feriasAdiantar13.toUpperCase());
	}

	public String getFeriasPagarEstagiarios() {
		return feriasPagarEstagiarios;
	}

	public void setFeriasPagarEstagiarios(String feriasPagarEstagiarios) {
		this.feriasPagarEstagiarios = (feriasPagarEstagiarios == null ? ""
				: feriasPagarEstagiarios.toUpperCase());
	}

	public String getFeriasCalcJustaCausa() {
		return feriasCalcJustaCausa;
	}

	public void setFeriasCalcJustaCausa(String feriasCalcJustaCausa) {
		this.feriasCalcJustaCausa = (feriasCalcJustaCausa == null ? ""
				: feriasCalcJustaCausa.toUpperCase());
	}

	public String getFeriasMovimentoMensal() {
		return feriasMovimentoMensal;
	}

	public void setFeriasMovimentoMensal(String feriasMovimentoMensal) {
		this.feriasMovimentoMensal = (feriasMovimentoMensal == null ? ""
				: feriasMovimentoMensal.toUpperCase());
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