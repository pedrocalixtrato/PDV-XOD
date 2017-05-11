package dc.entidade.patrimonio;

import java.io.Serializable;

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
@Table(name = "patrim_documento_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class DocumentoBemEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_documento_bem_id_seq")
	@SequenceGenerator(name = "patrim_documento_bem_id_seq", sequenceName = "patrim_documento_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	@Size(max = 50, message = "Tamanho inválido.")
	private String nome = "";

	@Column(name = "descricao")
	@Field
	@Caption("Descrição")
	private String descricao = "";

	@Column(name = "imagem")
	@Field
	@Caption("Imagem")
	private String imagem = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_patrim_bem", nullable = false)
	@Caption("Bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private BemEntity bem;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public DocumentoBemEntity() {

	}

	public DocumentoBemEntity(Integer id) {
		this.id = id;
	}

	public DocumentoBemEntity(Integer id, String nome) {
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = (imagem == null ? "" : imagem.toUpperCase());
	}

	public BemEntity getBem() {
		return bem;
	}

	public void setBem(BemEntity bem) {
		this.bem = bem;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}