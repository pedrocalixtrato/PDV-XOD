package dc.entidade.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "produto_marca")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class MarcaEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_marca_id_seq")
	@SequenceGenerator(name = "produto_marca_id_seq", sequenceName = "produto_marca_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "Nome é obrigatório")
	private String nome;

	@Lob
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "Descrição é obrigatório")
	private String descricao;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ProdutoEntity> produtoList = new ArrayList<ProdutoEntity>();

	/**
	 * CONSTRUTOR
	 */

	public MarcaEntity() {

	}

	public MarcaEntity(Integer id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProdutoEntity> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<ProdutoEntity> produtoList) {
		this.produtoList = produtoList;
	}

	/**
	 * TO STRING
	 */
	
	@Override
	public String toString() {
		return nome;
	}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
          return true;
    }

    if (!(obj instanceof MarcaEntity)) {
           return false;
    }

    MarcaEntity that = (MarcaEntity) obj;
    EqualsBuilder eb = new EqualsBuilder();
    eb.append(getId(), that.getId());
    return eb.isEquals();
}

@Override
public int hashCode() {
    if (getId() == null) {
          return super.hashCode();
    } else {
          return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
    }
}

}