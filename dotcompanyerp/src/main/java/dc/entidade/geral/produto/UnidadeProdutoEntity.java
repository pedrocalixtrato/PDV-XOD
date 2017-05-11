package dc.entidade.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
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
import dc.control.enums.SimNaoEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "unidade_produto")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UnidadeProdutoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_produto_id_seq")
	@SequenceGenerator(name = "unidade_produto_id_seq", sequenceName = "unidade_produto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA")
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Sigla é obrigatório")
	private String sigla;

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Basic(fetch = javax.persistence.FetchType.LAZY) 
	@NotNull(message = "Descrição é obrigatório")
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pode fracionar")
	@Column(name = "PODE_FRACIONAR")
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn podeFracionar;

	/**
	 * REFERENCIA - LIST
	 */

	@Fetch(FetchMode.SUBSELECT)
	//@OneToMany(mappedBy = "unidadeProduto", fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "unidadeProduto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProdutoEntity> produtoList = new ArrayList<ProdutoEntity>();

	/**
	 * CONSTRUTOR
	 */

	public UnidadeProdutoEntity() {

	}

	public UnidadeProdutoEntity(Integer id) {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SimNaoEn getPodeFracionar() {
		return podeFracionar;
	}

	public void setPodeFracionar(SimNaoEn podeFracionar) {
		this.podeFracionar = podeFracionar;
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
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof UnidadeProdutoEntity)) {
	           return false;
	    }

	    UnidadeProdutoEntity that = (UnidadeProdutoEntity) obj;
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

//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}

}