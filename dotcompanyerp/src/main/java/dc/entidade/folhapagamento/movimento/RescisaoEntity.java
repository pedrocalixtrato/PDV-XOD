package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_rescisao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RescisaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_rescisao_id_seq")
	@SequenceGenerator(name = "folha_rescisao_id_seq", sequenceName = "folha_rescisao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_demissao")
	@Field
	@Caption("Data da demissão")
	private Date dataDemissao;

	@Column(name = "data_pagamento")
	@Field
	@Caption("Data do pagamento")
	private Date dataPagamento;

	@Column(name = "motivo")
	@Field
	@Caption("Motivo")
	private String motivo = "";

	@Column(name = "data_aviso_previo")
	@Field
	@Caption("Data do aviso prévio")
	private Date dataAvisoPrevio;

	@Column(name = "dias_aviso_previo")
	@Field
	@Caption("Dias de aviso prévio")
	private Integer diasAvisoPrevio = new Integer(0);

	@Column(name = "comprovou_novo_emprego")
	@Field
	@Caption("Comprovou novo emprego")
	private String comprovouNovoEmprego = "";

	@Column(name = "dispensou_empregado")
	@Field
	@Caption("Dispensou empregado")
	private String dispensouEmpregado = "";

	@Column(name = "pensao_alimenticia")
	@Field
	@Caption("Pensão alimentícia")
	private Double pensaoAlimenticia = new Double(0.0);

	@Column(name = "pensao_alimenticia_fgts")
	@Field
	@Caption("Pensão alimentícia FGTS")
	private Double pensaoAlimenticiaFgts = new Double(0.0);

	@Column(name = "fgts_valor_rescisao")
	@Field
	@Caption("FGTS valor da rescisão")
	private Double fgtsValorRescisao = new Double(0.0);

	@Column(name = "fgts_saldo_banco")
	@Field
	@Caption("FGTS saldo do banco")
	private Double fgtsSaldoBanco = new Double(0.0);

	@Column(name = "fgts_complemento_saldo")
	@Field
	@Caption("FGTS complemento do saldo")
	private Double fgtsComplementoSaldo = new Double(0.0);

	@Column(name = "fgts_codigo_afastamento")
	@Field
	@Caption("FGTS código do afastamento")
	private String fgtsCodigoAfastamento = "";

	@Column(name = "fgts_codigo_saque")
	@Field
	@Caption("FGTS código do saque")
	private String fgtsCodigoSaque = "";

	/**
	 * REFERENCIA - FK
	 */

	/* id_colaborador integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public RescisaoEntity() {
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

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = (motivo == null ? "" : motivo.toUpperCase());
	}

	public Date getDataAvisoPrevio() {
		return dataAvisoPrevio;
	}

	public void setDataAvisoPrevio(Date dataAvisoPrevio) {
		this.dataAvisoPrevio = dataAvisoPrevio;
	}

	public Integer getDiasAvisoPrevio() {
		return diasAvisoPrevio;
	}

	public void setDiasAvisoPrevio(Integer diasAvisoPrevio) {
		this.diasAvisoPrevio = diasAvisoPrevio;
	}

	public String getComprovouNovoEmprego() {
		return comprovouNovoEmprego;
	}

	public void setComprovouNovoEmprego(String comprovouNovoEmprego) {
		this.comprovouNovoEmprego = (comprovouNovoEmprego == null ? ""
				: comprovouNovoEmprego.toUpperCase());
	}

	public String getDispensouEmpregado() {
		return dispensouEmpregado;
	}

	public void setDispensouEmpregado(String dispensouEmpregado) {
		this.dispensouEmpregado = (dispensouEmpregado == null ? ""
				: dispensouEmpregado.toUpperCase());
	}

	public Double getPensaoAlimenticia() {
		return pensaoAlimenticia;
	}

	public void setPensaoAlimenticia(Double pensaoAlimenticia) {
		this.pensaoAlimenticia = pensaoAlimenticia;
	}

	public Double getPensaoAlimenticiaFgts() {
		return pensaoAlimenticiaFgts;
	}

	public void setPensaoAlimenticiaFgts(Double pensaoAlimenticiaFgts) {
		this.pensaoAlimenticiaFgts = pensaoAlimenticiaFgts;
	}

	public Double getFgtsValorRescisao() {
		return fgtsValorRescisao;
	}

	public void setFgtsValorRescisao(Double fgtsValorRescisao) {
		this.fgtsValorRescisao = fgtsValorRescisao;
	}

	public Double getFgtsSaldoBanco() {
		return fgtsSaldoBanco;
	}

	public void setFgtsSaldoBanco(Double fgtsSaldoBanco) {
		this.fgtsSaldoBanco = fgtsSaldoBanco;
	}

	public Double getFgtsComplementoSaldo() {
		return fgtsComplementoSaldo;
	}

	public void setFgtsComplementoSaldo(Double fgtsComplementoSaldo) {
		this.fgtsComplementoSaldo = fgtsComplementoSaldo;
	}

	public String getFgtsCodigoAfastamento() {
		return fgtsCodigoAfastamento;
	}

	public void setFgtsCodigoAfastamento(String fgtsCodigoAfastamento) {
		this.fgtsCodigoAfastamento = (fgtsCodigoAfastamento == null ? ""
				: fgtsCodigoAfastamento.toUpperCase());
	}

	public String getFgtsCodigoSaque() {
		return fgtsCodigoSaque;
	}

	public void setFgtsCodigoSaque(String fgtsCodigoSaque) {
		this.fgtsCodigoSaque = (fgtsCodigoSaque == null ? "" : fgtsCodigoSaque
				.toUpperCase());
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}