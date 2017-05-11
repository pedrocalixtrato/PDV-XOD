package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_detalhe_imposto_icms")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIcmsEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_icms_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_icms_id_seq", sequenceName = "nfe_detalhe_imposto_icms_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "origem_mercadoria")
	@Caption(value = "Origem da mercadoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String origemMercadoria = "";

	@Field
	@Column(name = "cst_icms")
	@Caption(value = "CST ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstIcms = "";

	@Field
	@Column(name = "csosn")
	@Caption(value = "CSOSN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String csosn = "";

	@Field
	@Column(name = "modalidade_bc_icms")
	@Caption(value = "Modalidade BC ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String modalidadeBcIcms = "";

	@Field
	@Column(name = "taxa_reducao_bc_icms")
	@Caption(value = "Taxa de redução BC ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaReducaoBcIcms = new BigDecimal(0);

	@Field
	@Column(name = "base_calculo_icms")
	@Caption(value = "Base de cálculo do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIcms = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_icms")
	@Caption(value = "Alíquota do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcms = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms")
	@Caption(value = "Valor do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcms = new BigDecimal(0);

	@Field
	@Column(name = "motivo_desoneracao_icms")
	@Caption(value = "Motivo da desoneração do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String motivoDesoneracaoIcms = "";

	@Field
	@Column(name = "modalidade_bc_icms_st")
	@Caption(value = "Modalidade BC ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String modalidadeBcIcmsSt = "";

	@Field
	@Column(name = "percentual_mva_icms_st")
	@Caption(value = "Percentual MVA ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualMvaIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "percentual_reducao_bc_icms_st")
	@Caption(value = "Percentual de redução BC ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualReducaoBcIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_base_calculo_icms_st")
	@Caption(value = "Valor da base de cálculo ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBaseCalculoIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_icms_st")
	@Caption(value = "Alíquota ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms_st")
	@Caption(value = "Valor ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsSt = new BigDecimal(0);

	@Field
	@Column(name = "valor_bc_icms_st_retido")
	@Caption(value = "Valor BC ICMS ST retido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBcIcmsStRetido = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms_st_retido")
	@Caption(value = "Valor ICMS ST retido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsStRetido = new BigDecimal(0);

	@Field
	@Column(name = "valor_bc_icms_st_destino")
	@Caption(value = "Valor BC ICMS ST destino")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBcIcmsStDestino = new BigDecimal(0);

	@Field
	@Column(name = "valor_icms_st_destino")
	@Caption(value = "Valor ICMS ST destino")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsStDestino = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_credito_icms_sn")
	@Caption(value = "Alíquota do crédito ICMS SN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaCreditoIcmsSn = new BigDecimal(0);

	@Field
	@Column(name = "valor_credito_icms_sn")
	@Caption(value = "Valor do crédito ICMS SN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCreditoIcmsSn = new BigDecimal(0);

	@Field
	@Column(name = "percentual_bc_operacao_propria")
	@Caption(value = "Percentual BC operação própria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualBcOperacaoPropria = new BigDecimal(0);

	@Field
	@Column(name = "uf_st")
	@Caption(value = "UF ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufSt = "";
	
	@Field
	@Column(name = "valor_icms_desonerado")
	@Caption(value = "Valor do ICMS Desonerado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsDesonerado = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_icms_operacao")
	@Caption(value = "Valor do ICMS Operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsOperacao = new BigDecimal(0);
	
	@Field
	@Column(name = "percentual_diferimento")
	@Caption(value = "Percentual Diferimento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal percentualDiferimento = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_icms_diferido")
	@Caption(value = "Valor do ICMS Diferido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIcmsDiferido = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne
	@JoinColumn(name = "id_nfe_detalhe")
	private NfeDetalheEntity nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDetalheImpostoIcmsEntity() {
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

	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = (origemMercadoria == null ? ""
				: origemMercadoria.toUpperCase().trim());
	}

	public String getCstIcms() {
		return cstIcms.trim();
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = (cstIcms == null ? "" : cstIcms.toUpperCase().trim());
	}

	public String getCsosn() {
		return csosn.trim();
	}

	public void setCsosn(String csosn) {
		this.csosn = (csosn == null ? "" : csosn.toUpperCase().trim());
	}

	public String getModalidadeBcIcms() {
		return modalidadeBcIcms;
	}

	public void setModalidadeBcIcms(String modalidadeBcIcms) {
		this.modalidadeBcIcms = (modalidadeBcIcms == null ? ""
				: modalidadeBcIcms.toUpperCase().trim());
	}

	public BigDecimal getTaxaReducaoBcIcms() {
		return taxaReducaoBcIcms;
	}

	public void setTaxaReducaoBcIcms(BigDecimal taxaReducaoBcIcms) {
		this.taxaReducaoBcIcms = (taxaReducaoBcIcms == null ? new BigDecimal(0)
				: taxaReducaoBcIcms);
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = (baseCalculoIcms == null ? new BigDecimal(0)
				: baseCalculoIcms);
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = (aliquotaIcms == null ? new BigDecimal(0)
				: aliquotaIcms);
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = (valorIcms == null ? new BigDecimal(0) : valorIcms);
	}

	public String getMotivoDesoneracaoIcms() {
		return motivoDesoneracaoIcms;
	}

	public void setMotivoDesoneracaoIcms(String motivoDesoneracaoIcms) {
		this.motivoDesoneracaoIcms = (motivoDesoneracaoIcms == null ? ""
				: motivoDesoneracaoIcms.toUpperCase().trim());
	}

	public String getModalidadeBcIcmsSt() {
		return modalidadeBcIcmsSt;
	}

	public void setModalidadeBcIcmsSt(String modalidadeBcIcmsSt) {
		this.modalidadeBcIcmsSt = (modalidadeBcIcmsSt == null ? ""
				: modalidadeBcIcmsSt.toUpperCase().trim());
	}

	public BigDecimal getPercentualMvaIcmsSt() {
		return percentualMvaIcmsSt;
	}

	public void setPercentualMvaIcmsSt(BigDecimal percentualMvaIcmsSt) {
		this.percentualMvaIcmsSt = (percentualMvaIcmsSt == null ? new BigDecimal(
				0) : percentualMvaIcmsSt);
	}

	public BigDecimal getPercentualReducaoBcIcmsSt() {
		return percentualReducaoBcIcmsSt;
	}

	public void setPercentualReducaoBcIcmsSt(
			BigDecimal percentualReducaoBcIcmsSt) {
		this.percentualReducaoBcIcmsSt = (percentualReducaoBcIcmsSt == null ? new BigDecimal(
				0) : percentualReducaoBcIcmsSt);
	}

	public BigDecimal getValorBaseCalculoIcmsSt() {
		return valorBaseCalculoIcmsSt;
	}

	public void setValorBaseCalculoIcmsSt(BigDecimal valorBaseCalculoIcmsSt) {
		this.valorBaseCalculoIcmsSt = (valorBaseCalculoIcmsSt == null ? new BigDecimal(
				0) : valorBaseCalculoIcmsSt);
	}

	public BigDecimal getAliquotaIcmsSt() {
		return aliquotaIcmsSt;
	}

	public void setAliquotaIcmsSt(BigDecimal aliquotaIcmsSt) {
		this.aliquotaIcmsSt = (aliquotaIcmsSt == null ? new BigDecimal(0)
				: aliquotaIcmsSt);
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = (valorIcmsSt == null ? new BigDecimal(0)
				: valorIcmsSt);
	}

	public BigDecimal getValorBcIcmsStRetido() {
		return valorBcIcmsStRetido;
	}

	public void setValorBcIcmsStRetido(BigDecimal valorBcIcmsStRetido) {
		this.valorBcIcmsStRetido = (valorBcIcmsStRetido == null ? new BigDecimal(
				0) : valorBcIcmsStRetido);
	}

	public BigDecimal getValorIcmsStRetido() {
		return valorIcmsStRetido;
	}

	public void setValorIcmsStRetido(BigDecimal valorIcmsStRetido) {
		this.valorIcmsStRetido = (valorIcmsStRetido == null ? new BigDecimal(0)
				: valorIcmsStRetido);
	}

	public BigDecimal getValorBcIcmsStDestino() {
		return valorBcIcmsStDestino;
	}

	public void setValorBcIcmsStDestino(BigDecimal valorBcIcmsStDestino) {
		this.valorBcIcmsStDestino = (valorBcIcmsStDestino == null ? new BigDecimal(
				0) : valorBcIcmsStDestino);
	}

	public BigDecimal getValorIcmsStDestino() {
		return valorIcmsStDestino;
	}

	public void setValorIcmsStDestino(BigDecimal valorIcmsStDestino) {
		this.valorIcmsStDestino = (valorIcmsStDestino == null ? new BigDecimal(
				0) : valorIcmsStDestino);
	}

	public BigDecimal getAliquotaCreditoIcmsSn() {
		return aliquotaCreditoIcmsSn;
	}

	public void setAliquotaCreditoIcmsSn(BigDecimal aliquotaCreditoIcmsSn) {
		this.aliquotaCreditoIcmsSn = (aliquotaCreditoIcmsSn == null ? new BigDecimal(
				0) : aliquotaCreditoIcmsSn);
	}

	public BigDecimal getValorCreditoIcmsSn() {
		return valorCreditoIcmsSn;
	}

	public void setValorCreditoIcmsSn(BigDecimal valorCreditoIcmsSn) {
		this.valorCreditoIcmsSn = (valorCreditoIcmsSn == null ? new BigDecimal(
				0) : valorCreditoIcmsSn);
	}

	public BigDecimal getPercentualBcOperacaoPropria() {
		return percentualBcOperacaoPropria;
	}

	public void setPercentualBcOperacaoPropria(
			BigDecimal percentualBcOperacaoPropria) {
		this.percentualBcOperacaoPropria = (percentualBcOperacaoPropria == null ? new BigDecimal(
				0) : percentualBcOperacaoPropria);
	}

	public String getUfSt() {
		return ufSt;
	}

	public void setUfSt(String ufSt) {
		this.ufSt = (ufSt == null ? "" : ufSt.toUpperCase().trim());
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}
	
	public BigDecimal getValorIcmsDesonerado() {
		return valorIcmsDesonerado;
	}

	public void setValorIcmsDesonerado(BigDecimal valorIcmsDesonerado) {
		this.valorIcmsDesonerado = (valorIcmsDesonerado == null ? new BigDecimal(0) : valorIcmsDesonerado);
	}
	
	public BigDecimal getValorIcmsOperacao() {
		return valorIcmsOperacao;
	}

	public void setValorIcmsOperacao(BigDecimal valorIcmsOperacao) {
		this.valorIcmsOperacao = (valorIcmsOperacao == null ? new BigDecimal(0) : valorIcmsOperacao);
	}
	
	public BigDecimal getPercentualDiferimento() {
		return percentualDiferimento;
	}

	public void setPercentualDiferimento(BigDecimal percentualDiferimento) {
		this.percentualDiferimento = (percentualDiferimento == null ? new BigDecimal(0) : percentualDiferimento);
	}
	
	public BigDecimal getValorIcmsDiferido() {
		return valorIcmsDiferido;
	}

	public void setValorIcmsDiferido(BigDecimal valorIcmsDiferido) {
		this.valorIcmsDiferido = (valorIcmsDiferido == null ? new BigDecimal(0) : valorIcmsDiferido);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * TRANSIENT
	 */

	@Transient
	public CsosnEn getCsosnEn() {
		if (this.csosn == null || this.csosn.trim().equals("")) {
			return null;
		} else {
			return CsosnEn.valueOf("_" + this.csosn.trim());
		}
	}

	@Transient
	public CstIcmsEn getCstIcmsEn() {
		if (this.cstIcms == null || this.cstIcms.trim().equals("")) {
			return null;
		} else {
			return CstIcmsEn.valueOf("_" + this.cstIcms.trim());
		}
	}

}