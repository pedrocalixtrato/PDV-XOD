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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_detalhe_imposto_issqn")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIssqnEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_issqn_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_issqn_id_seq", sequenceName = "nfe_detalhe_imposto_issqn_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "base_calculo_issqn")
	@Caption(value = "Base do cálculo do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal baseCalculoIssqn = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_issqn")
	@Caption(value = "Alíquota do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_issqn")
	@Caption(value = "Valor do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIssqn = new BigDecimal(0);

	@Field
	@Column(name = "municipio_issqn")
	@Caption(value = "Municipio do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIssqn = new Integer(0);

	@Field
	@Column(name = "item_lista_servicos")
	@Caption(value = "Item da lista de serviços")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer itemListaServicos = new Integer(0);

	@Field
	@Column(name = "valor_deducao")
	@Caption(value = "Valor da Dedução")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDeducao = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_outras_retencoes")
	@Caption(value = "Valor outras Retenções")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorOutrasRetencoes = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_desconto_incondicionado")
	@Caption(value = "Valor Desconto Incodicionado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDescontoIncondicionado = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_desconto_condicionado")
	@Caption(value = "Valor Desconto Codicionado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDescontoCondicionado = new BigDecimal(0);
	
	@Field
	@Column(name = "valor_retencao_iss")
	@Caption(value = "Valor Retenção ISS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorRetencaoIss = new BigDecimal(0);
	
	@Field
	@Column(name = "indicador_exigibilidade_iss")
	@Caption(value = "Indicador Exigibilidade ISS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer indicadorExigibilidadeIss = new Integer(0);
	
	@Field
	@Column(name = "codigo_servico")
	@Caption(value = "Código Serviço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoServico = "";
	
	@Field
	@Column(name = "municipio_incidencia")
	@Caption(value = "Município Incidência")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIncidencia = new Integer(0);
	
	@Field
	@Column(name = "pais_servico_prestado")
	@Caption(value = "País Serviço Prestado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer paisServicoPrestado = new Integer(0);
	
	@Field
	@Column(name = "numero_processo")
	@Caption(value = "Número Processo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroProcesso = "";
	
	@Field
	@Column(name = "indicador_incentivo_fiscal")
	@Caption(value = "Indicador Incentivo Fiscal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer indicadorIncentivoFiscal = new Integer(0);

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

	public NfeDetalheImpostoIssqnEntity() {
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

	public BigDecimal getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
		this.baseCalculoIssqn = (baseCalculoIssqn == null ? new BigDecimal(0)
				: baseCalculoIssqn);
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = (aliquotaIssqn == null ? new BigDecimal(0)
				: aliquotaIssqn);
	}

	public BigDecimal getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(BigDecimal valorIssqn) {
		this.valorIssqn = (valorIssqn == null ? new BigDecimal(0) : valorIssqn);
	}

	public Integer getMunicipioIssqn() {
		return municipioIssqn;
	}

	public void setMunicipioIssqn(Integer municipioIssqn) {
		this.municipioIssqn = (municipioIssqn == null ? new Integer(0)
				: municipioIssqn);
	}

	public Integer getItemListaServicos() {
		return itemListaServicos;
	}

	public void setItemListaServicos(Integer itemListaServicos) {
		this.itemListaServicos = (itemListaServicos == null ? new Integer(0)
				: itemListaServicos);
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}
	
	public BigDecimal getValorDeducao() {
		return valorDeducao;
	}

	public void setValorDeducao(BigDecimal valorDeducao) {
		this.valorDeducao= (valorDeducao == null ? new BigDecimal(0) : valorDeducao);
	}
	
	public BigDecimal getValorOutrasRetencoes() {
		return valorOutrasRetencoes;
	}

	public void setValorOutrasRetencoes(BigDecimal valorOutrasRetencoes) {
		this.valorOutrasRetencoes = (valorOutrasRetencoes == null ? new BigDecimal(0) : valorOutrasRetencoes);
	}
	
	public BigDecimal getValorDescontoIncondicionado() {
		return valorDescontoIncondicionado;
	}

	public void setValorDescontoIncondicionado(BigDecimal valorDescontoIncondicionado) {
		this.valorDescontoIncondicionado = (valorDescontoIncondicionado == null ? new BigDecimal(0) : valorDescontoIncondicionado);
	}
	
	public BigDecimal getValorDescontoCondicionado() {
		return valorDescontoCondicionado;
	}

	public void setValorDescontoCondicionado(BigDecimal valorDescontoCondicionado) {
		this.valorDescontoCondicionado = (valorDescontoCondicionado == null ? new BigDecimal(0) : valorDescontoCondicionado);
	}
	
	public BigDecimal getValorRetencaoIss() {
		return valorRetencaoIss;
	}

	public void setValorRetencaoIss(BigDecimal valorRetencaoIss) {
		this.valorRetencaoIss = (valorRetencaoIss == null ? new BigDecimal(0) : valorRetencaoIss);
	}


	public Integer getIndicadorExigibilidadeIss() {
		return indicadorExigibilidadeIss;
	}

	public void setIndicadorExigibilidadeIss(Integer indicadorExigibilidadeIss) {
		this.indicadorExigibilidadeIss = (indicadorExigibilidadeIss == null ? new Integer(0) : indicadorExigibilidadeIss);
	}
	
	public Integer getMunicipioIncidencia() {
		return municipioIncidencia;
	}

	public void setMunicipioIncidencia(Integer municipioIncidencia) {
		this.municipioIncidencia = (municipioIncidencia == null ? new Integer(0) : municipioIncidencia);
	}
	
	public Integer getPaisServicoPrestado() {
		return paisServicoPrestado;
	}

	public void setPaisServicoPrestado(Integer paisServicoPrestado) {
		this.paisServicoPrestado = (paisServicoPrestado == null ? new Integer(0) : paisServicoPrestado);
	}
	
	public Integer getIndicadorIncentivoFiscal() {
		return indicadorIncentivoFiscal;
	}

	public void setIndicadorIncentivoFiscal(Integer indicadorIncentivoFiscal) {
		this.indicadorIncentivoFiscal = (indicadorIncentivoFiscal == null ? new Integer(0) : indicadorIncentivoFiscal);
	}
	
	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}