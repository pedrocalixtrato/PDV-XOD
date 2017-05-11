package dc.entidade.financeiro;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.PaisEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "indice_economico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceEconomicoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 **/

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "indice_economico_id_seq")
	@SequenceGenerator(name = "indice_economico_id_seq", sequenceName = "indice_economico_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "sigla")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Sigla")
	@NotNull(message = "Sigla é Obrigatório!")
	private String sigla;

	@Field
	@Column(name = "nome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Nome")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Lob
	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao;

	/**
	 * REFERENCIA - FK
	 */

	//@ManyToOne
	//@JoinColumn(name = "id_pais", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	@JoinColumn(name = "id_pais", referencedColumnName = "id")
	@Caption("País")
	@NotNull(message = "País é Obrigatório")
	private PaisEntity pais;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public IndiceEconomicoEntity() {

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

	public PaisEntity getPais() {
		return pais;
	}

	public void setPais(PaisEntity pais) {
		this.pais = pais;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof IndiceEconomicoEntity)) {
	           return false;
	    }

	    IndiceEconomicoEntity that = (IndiceEconomicoEntity) obj;
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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return nome;
	}

}