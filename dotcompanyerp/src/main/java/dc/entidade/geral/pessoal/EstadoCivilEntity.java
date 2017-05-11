package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
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
@Table(name = "estado_civil")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EstadoCivilEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_civil_id_seq")
	@SequenceGenerator(name = "estado_civil_id_seq", sequenceName = "estado_civil_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "estadoCivil", fetch = FetchType.LAZY)
	private List<PessoaFisicaEntity> pessoaFisicaList =  new ArrayList<PessoaFisicaEntity>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public EstadoCivilEntity() {

	}

	public EstadoCivilEntity(Integer id) {
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
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public List<PessoaFisicaEntity> getPessoaFisicaList() {
		return pessoaFisicaList;
	}

	public void setPessoaFisicaList(List<PessoaFisicaEntity> pessoaFisicaList) {
		this.pessoaFisicaList = pessoaFisicaList;
	}

@Override
public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }
	
       if (!(obj instanceof EstadoCivilEntity)) {
           return false;
       }
	
       EstadoCivilEntity that = (EstadoCivilEntity) obj;
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