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
@Table(name = "patrim_tipo_movimentacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class TipoMovimentacaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_tipo_movimentacao_id_seq")
	@SequenceGenerator(name = "patrim_tipo_movimentacao_id_seq", sequenceName = "patrim_tipo_movimentacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "tipo")
	@Field
	@Caption("Tipo")
	@Size(max = 2, message = "Tamanho inválido.")
	private String tipo = "";

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	@Size(max = 50, message = "Tamanho inválido.")
	private String nome = "";

	@Column(name = "descricao")
	@Field
	@Caption("Descrição")
	private String descricao = "";

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

	@OneToMany(mappedBy = "tipoMovimentacao", fetch = FetchType.LAZY)
	private List<MovimentacaoBemEntity> movimentacaoBemList;

	/**
	 * CONSTRUTOR
	 */

	public TipoMovimentacaoEntity() {

	}

	public TipoMovimentacaoEntity(Integer id) {
		this.id = id;
	}

	// public TipoMovimentacaoEntity(Integer id, Empresa empresa) {
	// this.id = id;
	// this.empresa = empresa;
	// }

	public TipoMovimentacaoEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	// public Empresa getEmpresa() {
	// return empresa;
	// }
	//
	// public void setEmpresa(Empresa empresa) {
	// this.empresa = empresa;
	// }

	public List<MovimentacaoBemEntity> getMovimentacaoBemList() {
		return movimentacaoBemList;
	}

	public void setMovimentacaoBemList(
			List<MovimentacaoBemEntity> movimentacaoBemList) {
		this.movimentacaoBemList = movimentacaoBemList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}