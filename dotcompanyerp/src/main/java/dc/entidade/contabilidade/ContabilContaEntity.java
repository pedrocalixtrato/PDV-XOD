package dc.entidade.contabilidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "CONTABIL_CONTA")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContabilContaEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_conta_id_seq")
	@SequenceGenerator(name = "contabil_conta_id_seq", sequenceName = "contabil_conta_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption(value = "Classificação")
	@Column(name = "CLASSIFICACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacao;

	@Field
	@Caption(value = "Tipo")
	@Column(name = "TIPO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipo;

	@Field
	@Caption(value = "Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Caption(value = "Data Inclusão")
	@Column(name = "DATA_INCLUSAO")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataInclusao;

	@Field
	@Caption(value = "Situação")
	@Column(name = "SITUACAO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String situacao;

	@Field
	@Caption(value = "Natureza")
	@Column(name = "NATUREZA", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String natureza;

	@Field
	@Caption(value = "Patrimonio Resultado")
	@Column(name = "PATRIMONIO_RESULTADO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String patrimonioResultado;

	@Field
	@Caption(value = "Livro Caixa")
	@Column(name = "LIVRO_CAIXA", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String livroCaixa;

	@Field
	@Caption(value = "Dfc")
	@Column(name = "DFC", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String dfc;

	@Field
	@Caption(value = "Ordem")
	@Column(name = "ORDEM")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ordem;

	@Field
	@Caption(value = "Código Reduzido")
	@Column(name = "CODIGO_REDUZIDO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoReduzido;

	@Field
	@Caption(value = "Código EFD")
	@Column(name = "CODIGO_EFD", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoEfd;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PLANO_CONTA_REF_SPED", nullable = false)
	private PlanoContaRefSped planoContaRefSped;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PLANO_CONTA", nullable = false)
	private PlanoContaEntity planoConta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CONTABIL_CONTA", nullable = true)
	private ContabilContaEntity contabilConta;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "contabilConta", cascade = CascadeType.ALL)
	// private List<OperadoraPlanoSaudeEntity> operadoraPlanoSaudeList;

	// @OneToMany(mappedBy = "contabilConta", cascade = CascadeType.ALL)
	// private List<OperadoraCartaoEntity> operadoraCartaoList;

	/**
	 * 
	 * @module FINANCEIRO
	 */

	// @OneToMany(mappedBy = "contabilConta", fetch = FetchType.LAZY)
	// private List<ContaCaixa> contaCaixaList;

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

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getCaption() {
		return getDescricao();
	}

	/**
	 * CONSTRUTOR
	 */

	public ContabilContaEntity() {

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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getPatrimonioResultado() {
		return patrimonioResultado;
	}

	public void setPatrimonioResultado(String patrimonioResultado) {
		this.patrimonioResultado = patrimonioResultado;
	}

	public String getLivroCaixa() {
		return livroCaixa;
	}

	public void setLivroCaixa(String livroCaixa) {
		this.livroCaixa = livroCaixa;
	}

	public String getDfc() {
		return dfc;
	}

	public void setDfc(String dfc) {
		this.dfc = dfc;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getCodigoReduzido() {
		return codigoReduzido;
	}

	public void setCodigoReduzido(String codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}

	public String getCodigoEfd() {
		return codigoEfd;
	}

	public void setCodigoEfd(String codigoEfd) {
		this.codigoEfd = codigoEfd;
	}

	public PlanoContaRefSped getPlanoContaRefSped() {
		return planoContaRefSped;
	}

	public void setPlanoContaRefSped(PlanoContaRefSped planoContaRefSped) {
		this.planoContaRefSped = planoContaRefSped;
	}

	public PlanoContaEntity getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

	public ContabilContaEntity getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilContaEntity contabilConta) {
		this.contabilConta = contabilConta;
	}

	// public List<OperadoraPlanoSaudeEntity> getOperadoraPlanoSaudeList() {
	// return operadoraPlanoSaudeList;
	// }

	// public void setOperadoraPlanoSaudeList(
	// List<OperadoraPlanoSaudeEntity> operadoraPlanoSaudeList) {
	// this.operadoraPlanoSaudeList = operadoraPlanoSaudeList;
	// }

	// public List<OperadoraCartaoEntity> getOperadoraCartaoList() {
	// return operadoraCartaoList;
	// }

	// public void setOperadoraCartaoList(
	// List<OperadoraCartaoEntity> operadoraCartaoList) {
	// this.operadoraCartaoList = operadoraCartaoList;
	// }

	// public List<ContaCaixa> getContaCaixaList() {
	// return contaCaixaList;
	// }

	// public void setContaCaixaList(List<ContaCaixa> contaCaixaList) {
	// this.contaCaixaList = contaCaixaList;
	// }

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return classificacao;
	}

}