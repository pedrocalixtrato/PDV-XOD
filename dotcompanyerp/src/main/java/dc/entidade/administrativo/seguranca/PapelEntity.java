package dc.entidade.administrativo.seguranca;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
import dc.entidade.framework.PapelMenu;

@Entity
@Table(name = "papel")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PapelEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public static final Integer MASTER_ID = 1;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String nome = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	//@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "papel", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PapelMenu> papelMenuList = new ArrayList<PapelMenu>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PapelEntity() {

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

	/*
	 * @Override public int hashCode() { return
	 * HashCodeBuilder.reflectionHashCode(this, new String[] {"id"}); }
	 */

	/*
	 * @Override public boolean equals(Object object) { if (object instanceof
	 * Papel== false) return false; if (this == object) return true; final Papel
	 * other = (Papel) object; boolean igual =
	 * EqualsBuilder.reflectionEquals(this, other, new String[] {"papelMenus"});
	 * return igual; }
	 */

	/*
	 * @Override public String toString() { return
	 * ToStringBuilder.reflectionToString(this); }
	 */

	public List<PapelMenu> getPapelMenuList() {
		return papelMenuList;
	}

	public void setPapelMenuList(List<PapelMenu> papelMenuList) {
		this.papelMenuList = papelMenuList;
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

        if (!(obj instanceof PapelEntity)) {
            return false;
        }

        PapelEntity that = (PapelEntity) obj;
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