package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.diverso.SetorEntity;

//@Entity
//@Table(name = "teste")

@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Teste extends AbstractModel<Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	private String nome;

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descricao")
	@Column(name = "DESCRICAO")
	private String descricao;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = EmpresaEntity.class)
	@JoinColumn(name = "id_empresa")
	private EmpresaEntity idEmpresa;

	public Teste() {
	}

	public Teste(Integer id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof SetorEntity == false)
			return false;
		if (this == object)
			return true;
		final SetorEntity other = (SetorEntity) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public EmpresaEntity getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(EmpresaEntity idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
