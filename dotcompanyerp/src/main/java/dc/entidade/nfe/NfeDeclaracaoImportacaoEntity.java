package dc.entidade.nfe;

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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_declaracao_importacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDeclaracaoImportacaoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_declaracao_importacao_id_seq")
	@SequenceGenerator(name = "nfe_declaracao_importacao_id_seq", sequenceName = "nfe_declaracao_importacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "numero_documento")
	@Caption(value = "Número do documento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroDocumento;

	@Field
	@Column(name = "data_registro")
	@Caption(value = "Data do registro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataRegistro;

	@Field
	@Column(name = "local_desembaraco")
	@Caption(value = "Local de desembaraço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String localDesembaraco;

	@Field
	@Column(name = "uf_desembaraco")
	@Caption(value = "UF de desembaraço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufDesembaraco;

	@Field
	@Column(name = "data_desembaraco")
	@Caption(value = "Data do desembaraço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataDesembaraco;

	@Field
	@Column(name = "codigo_exportador")
	@Caption(value = "Código do exportador")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoExportador;
	
	@Field
	@Column(name = "via_transporte")
	@Caption(value = "Via Transporte")
	private Integer viaTransporte;
	
	@Field
	@Column(name = "forma_intermediacao")
	@Caption(value = "Forma Intermediação")
	private Integer formaIntermediacao;
	
	@Field
	@Column(name = "cnpj")
	@Caption(value = "CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;

	
	@Field
	@Column(name = "uf_terceiro")
	@Caption(value = "UF Terceiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufTerceiro;

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Column(name = "id_nfe_detalhe")
	@Caption(value = "NFE detalhe")
	private Integer nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDeclaracaoImportacaoEntity() {
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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getLocalDesembaraco() {
		return localDesembaraco;
	}

	public void setLocalDesembaraco(String localDesembaraco) {
		this.localDesembaraco = localDesembaraco;
	}

	public String getUfDesembaraco() {
		return ufDesembaraco;
	}

	public void setUfDesembaraco(String ufDesembaraco) {
		this.ufDesembaraco = ufDesembaraco;
	}

	public Date getDataDesembaraco() {
		return dataDesembaraco;
	}

	public void setDataDesembaraco(Date dataDesembaraco) {
		this.dataDesembaraco = dataDesembaraco;
	}

	public String getCodigoExportador() {
		return codigoExportador;
	}

	public void setCodigoExportador(String codigoExportador) {
		this.codigoExportador = codigoExportador;
	}

	public Integer getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(Integer nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}
	
	public Integer getViaTransporte() {
		return viaTransporte;
	}

	public void setViaTransporte(Integer viaTransporte) {
		this.viaTransporte = viaTransporte;
	}

	public Integer getFormaIntermediacao() {
		return formaIntermediacao;
	}

	public void setFormaIntermediacao(Integer formaIntermediacao) {
		this.formaIntermediacao = formaIntermediacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getUfTerceiro() {
		return ufTerceiro;
	}

	public void setUfTerceiro(String ufTerceiro) {
		this.ufTerceiro = ufTerceiro;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}