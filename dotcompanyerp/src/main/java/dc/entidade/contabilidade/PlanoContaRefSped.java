package dc.entidade.contabilidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "PLANO_CONTA_REF_SPED")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PlanoContaRefSped extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Analyzer(definition = "dc_combo_analyzer")
	@ComboCode
	private Integer id;

	@Caption(value = "Código Conta")
	@Column(name = "COD_CTA_REF")
	@Analyzer(definition = "dc_combo_analyzer")
	@Field
	private String codCtaRef;

	@Caption(value = "Descrição")
	@Column(name = "DESCRICAO")
	@Analyzer(definition = "dc_combo_analyzer")
	@ComboValue
	@Field
	private String descricao;

	@Caption(value = "Orientações")
	@Column(name = "ORIENTACOES")
	@Analyzer(definition = "dc_combo_analyzer")
	@Field
	private String orientacoes;

	@Caption(value = "Início Validade")
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_VALIDADE")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date inicioValidade;

	@Caption(value = "Fim Validade")
	@Temporal(TemporalType.DATE)
	@Column(name = "FIM_VALIDADE")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date fimValidade;

	@Caption(value = "Tipo")
	@Column(name = "TIPO")
	@Analyzer(definition = "dc_combo_analyzer")
	@Field
	private String tipo;
	

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "planoContaRefSped", fetch = FetchType.LAZY)
	private List<ContaEntity> contaList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getDescricao();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }


	public PlanoContaRefSped() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodCtaRef() {
		return codCtaRef;
	}

	public void setCodCtaRef(String codCtaRef) {
		this.codCtaRef = codCtaRef;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public Date getInicioValidade() {
		return inicioValidade;
	}

	public void setInicioValidade(Date inicioValidade) {
		this.inicioValidade = inicioValidade;
	}

	public Date getFimValidade() {
		return fimValidade;
	}

	public void setFimValidade(Date fimValidade) {
		this.fimValidade = fimValidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "PlanoContaRefSpedVO[id=" + id + "]";
	}

}
