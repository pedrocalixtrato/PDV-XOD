package dc.entidade.administrativo.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.CrtEn;
import dc.control.enums.TipoEmpresaEn;
import dc.control.enums.TipoRegimeEn;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.geral.diverso.PaisEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "empresa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EmpresaEntity extends AbstractModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_id_seq")
	@SequenceGenerator(name = "empresa_id_seq", sequenceName = "empresa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption()
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoEmpresaEn tipoEmpresa; // 1-Matriz 2-Filial 3-Depósito

	@Field
	@Caption("Razao Social")
	@Column(name = "RAZAO_SOCIAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String razaoSocial;

	@Field
	@Caption("Nome Fantasia")
	@Column(name = "NOME_FANTASIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeFantasia;

	@Field
	@Caption()
	@Column(name = "CNPJ", length = 14, unique = true)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_ESTADUAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadual;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_ESTADUAL_ST", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadualSt;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_MUNICIPAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoMunicipal;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_JUNTA_COMERCIAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoJuntaComercial;

	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption()
	@Column(name = "DATA_INSC_JUNTA_COMERCIAL")
	private Date dataInscJuntaComercial;

	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption()
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption()
	@Column(name = "DATA_INICIO_ATIVIDADES")
	private Date dataInicioAtividades;

	@Field
	@Caption()
	@Column(name = "SUFRAMA", length = 9)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String suframa;

	@Field
	@Caption()
	@Column(name = "EMAIL", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;

	@Lob
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Field
	@Caption()
	@Column(name = "IMAGEM_LOGOTIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String imagemLogotipo;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption()
	@Column(name = "CRT")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private CrtEn crt;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption()
	@Column(name = "TIPO_REGIME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoRegimeEn tipoRegime;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_PIS", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaPis;

	@Field
	@Caption()
	@Column(name = "CONTATO", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contato;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_COFINS", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaCofins;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_SAT", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaSat;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE_CIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbgeCidade;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE_UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbgeUf;

	@Field
	@Caption()
	@Column(name = "CODIGO_TERCEIROS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoTerceiros;

	@Field
	@Caption()
	@Column(name = "CODIGO_GPS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoGps;

	@Field
	@Caption()
	@Column(name = "CEI", length = 12)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cei;

	@Field
	@Caption()
	@Column(name = "codigo_cnae_principal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoCnaePrincipal;
	
	@Field
	@Caption()
	@Column(name = "tipo_controle_estoque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoControleEstoque;

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Caption()
	@Column(name = "id_matriz")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer matriz;

	@Field
	@Caption()
	@Column(name = "ID_EMPRESA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer empresa;

	@Field
	@Caption()
	@Column(name = "ID_SINDICATO_PATRONAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer sindicatoPatronal;

	@Field
	@Caption()
	@Column(name = "ID_FPAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer fpas;

	@Field
	@Caption()
	@Column(name = "ID_CONTADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer contador;

	@OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL, optional = false, fetch=FetchType.LAZY, orphanRemoval=true)
	private ContaEmpresa contaEmpresa;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<PaisEntity> paisList;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<PessoaEnderecoEntity> pessoaEnderecoList;

	//@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<EmpresaSeguimento> empresaSeguimentoList = new ArrayList<>();

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getNomeFantasia();
	}

	/**
	 * CONSTRUTOR
	 */

	public EmpresaEntity() {

	}

	public EmpresaEntity(Integer id) {
		this.id = id;
	}

	public EmpresaEntity(Integer id, int empresa) {
		this.id = id;
		this.empresa = empresa;
	}

	public EmpresaEntity(Integer id, String razaoSocial, String nomeFantasia) {
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
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

	public TipoEmpresaEn getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresaEn tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoEstadualSt() {
		return inscricaoEstadualSt;
	}

	public void setInscricaoEstadualSt(String inscricaoEstadualSt) {
		this.inscricaoEstadualSt = inscricaoEstadualSt;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoJuntaComercial() {
		return inscricaoJuntaComercial;
	}

	public void setInscricaoJuntaComercial(String inscricaoJuntaComercial) {
		this.inscricaoJuntaComercial = inscricaoJuntaComercial;
	}

	public Date getDataInscJuntaComercial() {
		return dataInscJuntaComercial;
	}

	public void setDataInscJuntaComercial(Date dataInscJuntaComercial) {
		this.dataInscJuntaComercial = dataInscJuntaComercial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicioAtividades() {
		return dataInicioAtividades;
	}

	public void setDataInicioAtividades(Date dataInicioAtividades) {
		this.dataInicioAtividades = dataInicioAtividades;
	}

	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagemLogotipo() {
		return imagemLogotipo;
	}

	public void setImagemLogotipo(String imagemLogotipo) {
		this.imagemLogotipo = imagemLogotipo;
	}

	public CrtEn getCrt() {
		return crt;
	}

	public void setCrt(CrtEn crt) {
		this.crt = crt;
	}

	public TipoRegimeEn getTipoRegime() {
		return tipoRegime;
	}

	public void setTipoRegime(TipoRegimeEn tipoRegime) {
		this.tipoRegime = tipoRegime;
	}

	public BigDecimal getAliquotaPis() {
		return aliquotaPis;
	}

	public void setAliquotaPis(BigDecimal aliquotaPis) {
		this.aliquotaPis = aliquotaPis;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public BigDecimal getAliquotaCofins() {
		return aliquotaCofins;
	}

	public void setAliquotaCofins(BigDecimal aliquotaCofins) {
		this.aliquotaCofins = aliquotaCofins;
	}

	public BigDecimal getAliquotaSat() {
		return aliquotaSat;
	}

	public void setAliquotaSat(BigDecimal aliquotaSat) {
		this.aliquotaSat = aliquotaSat;
	}

	public Integer getCodigoIbgeCidade() {
		return codigoIbgeCidade;
	}

	public void setCodigoIbgeCidade(Integer codigoIbgeCidade) {
		this.codigoIbgeCidade = codigoIbgeCidade;
	}

	public Integer getCodigoIbgeUf() {
		return codigoIbgeUf;
	}

	public void setCodigoIbgeUf(Integer codigoIbgeUf) {
		this.codigoIbgeUf = codigoIbgeUf;
	}

	public Integer getCodigoTerceiros() {
		return codigoTerceiros;
	}

	public void setCodigoTerceiros(Integer codigoTerceiros) {
		this.codigoTerceiros = codigoTerceiros;
	}

	public Integer getCodigoGps() {
		return codigoGps;
	}

	public void setCodigoGps(Integer codigoGps) {
		this.codigoGps = codigoGps;
	}

	public String getCei() {
		return cei;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}

	public String getCodigoCnaePrincipal() {
		return codigoCnaePrincipal;
	}

	public void setCodigoCnaePrincipal(String codigoCnaePrincipal) {
		this.codigoCnaePrincipal = codigoCnaePrincipal;
	}

	public Integer getMatriz() {
		return matriz;
	}

	public void setMatriz(Integer matriz) {
		this.matriz = matriz;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getSindicatoPatronal() {
		return sindicatoPatronal;
	}

	public void setSindicatoPatronal(Integer sindicatoPatronal) {
		this.sindicatoPatronal = sindicatoPatronal;
	}

	public Integer getFpas() {
		return fpas;
	}

	public void setFpas(Integer fpas) {
		this.fpas = fpas;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public ContaEmpresa getContaEmpresa() {
		return contaEmpresa;
	}

	public void setContaEmpresa(ContaEmpresa contaEmpresa) {
		this.contaEmpresa = contaEmpresa;
		contaEmpresa.setEmpresa(this);
	}

	public List<PaisEntity> getPaisList() {
		return paisList;
	}

	public void setPaisList(List<PaisEntity> paisList) {
		this.paisList = paisList;
	}

	public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
		return pessoaEnderecoList;
	}

	public void setPessoaEnderecoList(
			List<PessoaEnderecoEntity> pessoaEnderecoList) {
		this.pessoaEnderecoList = pessoaEnderecoList;
	}

	public List<EmpresaSeguimento> getEmpresaSeguimentoList() {
		return empresaSeguimentoList;
	}

	public void setEmpresaSeguimentoList(
			List<EmpresaSeguimento> empresaSeguimentoList) {
		this.empresaSeguimentoList = empresaSeguimentoList;
	}
	
	public String getTipoControleEstoque() {
		return tipoControleEstoque;
	}

	public void setTipoControleEstoque(String tipoControleEstoque) {
		this.tipoControleEstoque = tipoControleEstoque;
	}
	
	public void removeSeguimento(EmpresaSeguimento value) {
		this.empresaSeguimentoList.remove(value);
		value.setSeguimento(null);
	}
	
	public EmpresaSeguimento addSeguimento(EmpresaSeguimento e) {
		getEmpresaSeguimentoList().add(e);
		//e.setSeguimento(this);
		
		return e;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		return nomeFantasia;
	}

}