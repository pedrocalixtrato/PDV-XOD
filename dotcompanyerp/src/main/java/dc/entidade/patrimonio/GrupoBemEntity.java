package dc.entidade.patrimonio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
@Table(name = "patrim_grupo_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class GrupoBemEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_grupo_bem_id_seq")
	@SequenceGenerator(name = "patrim_grupo_bem_id_seq", sequenceName = "patrim_grupo_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "codigo")
	@Field
	@Caption("Código")
	@Size(max = 3, message = "Tamanho inválido.")
	private String codigo = "";

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	@Size(max = 50, message = "Tamanho inválido.")
	private String nome = "";

	@Column(name = "conta_ativo_imobilizado")
	@Field
	@Caption("Conta ativo imobilizado")
	@Size(max = 30, message = "Tamanho inválido.")
	private String contaAtivoImobilizado = "";

	@Column(name = "conta_depreciacao_acumulada")
	@Field
	@Caption("Conta da depreciação acumulada")
	@Size(max = 30, message = "Tamanho inválido.")
	private String contaDepreciacaoAcumulada = "";

	@Column(name = "conta_despesa_depreciacao")
	@Field
	@Caption("Conta da despesa depreciação")
	@Size(max = 30, message = "Tamanho inválido.")
	private String contaDespesaDepreciacao = "";

	@Column(name = "codigo_historico")
	@Field
	@Caption("Código do historico")
	private Integer codigoHistorico = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	// @ManyToOne
	// @JoinColumn(name = "ID_EMPRESA", nullable = false)
	// @Caption("Empresa")
	// @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	// private Empresa empresa;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "grupoBem", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	/**
	 * CONSTRUTOR
	 */

	public GrupoBemEntity() {

	}

	public GrupoBemEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = (codigo == null ? "" : codigo.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getContaAtivoImobilizado() {
		return contaAtivoImobilizado;
	}

	public void setContaAtivoImobilizado(String contaAtivoImobilizado) {
		this.contaAtivoImobilizado = (contaAtivoImobilizado == null ? ""
				: contaAtivoImobilizado.toUpperCase());
	}

	public String getContaDepreciacaoAcumulada() {
		return contaDepreciacaoAcumulada;
	}

	public void setContaDepreciacaoAcumulada(String contaDepreciacaoAcumulada) {
		this.contaDepreciacaoAcumulada = (contaDepreciacaoAcumulada == null ? ""
				: contaDepreciacaoAcumulada.toUpperCase());
	}

	public String getContaDespesaDepreciacao() {
		return contaDespesaDepreciacao;
	}

	public void setContaDespesaDepreciacao(String contaDespesaDepreciacao) {
		this.contaDespesaDepreciacao = (contaDespesaDepreciacao == null ? ""
				: contaDespesaDepreciacao.toUpperCase());
	}

	public Integer getCodigoHistorico() {
		return codigoHistorico;
	}

	public void setCodigoHistorico(Integer codigoHistorico) {
		this.codigoHistorico = codigoHistorico;
	}

	// public Empresa getEmpresa() {
	// return empresa;
	// }
	//
	// public void setEmpresa(Empresa empresa) {
	// this.empresa = empresa;
	// }

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}