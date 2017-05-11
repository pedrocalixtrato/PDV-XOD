package dc.entidade.geral.diverso;

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
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "pais")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PaisEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
	@SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "codigo")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código é Obrigatório!")
	private Integer codigo = new Integer(0);

	@Field
	@Caption("Nome EN")
	@Column(name = "nome_en", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome Inglês é Obrigatório!")
	private String nomeIngles = "";

	@Field
	@Caption("Nome PTBR")
	@Column(name = "nome_ptbr", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome Ptbr é Obrigatório!")
	private String nomePtbr = "";

	@Field
	@Caption("Sigla 2")
	@Column(name = "sigla2", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Sigla 2 é Obrigatório!")
	private String sigla2 = "";

	@Field
	@Caption("Sigla 3")
	@Column(name = "sigla3", length = 3)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Sigla 3 é Obrigatório!")
	private String sigla3 = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="pais",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UfEntity> ufList = new ArrayList<UfEntity>();
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="pais", fetch = FetchType.LAZY)
	private List<IndiceEconomicoEntity> indiceList = new ArrayList<>();

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public PaisEntity() {

	}

	public PaisEntity(Integer id) {
		this.id = id;
	}

	public PaisEntity(Integer id, String nomePtbr) {
		this.id = id;
		this.nomePtbr = nomePtbr;
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = (codigo == null ? new Integer(0) : codigo);
	}

	public String getNomeIngles() {
		return nomeIngles;
	}

	public void setNomeIngles(String nomeIngles) {
		this.nomeIngles = (nomeIngles == null ? "".trim() : nomeIngles
				.toUpperCase().trim());
	}

	public String getNomePtbr() {
		return nomePtbr;
	}

	public void setNomePtbr(String nomePtbr) {
		this.nomePtbr = (nomePtbr == null ? "".trim() : nomePtbr.toUpperCase()
				.trim());
	}

	public String getSigla2() {
		return sigla2;
	}

	public void setSigla2(String sigla2) {
		this.sigla2 = (sigla2 == null ? "".trim() : sigla2.toUpperCase().trim());
	}

	public String getSigla3() {
		return sigla3;
	}

	public void setSigla3(String sigla3) {
		this.sigla3 = (sigla3 == null ? "".trim() : sigla3.toUpperCase().trim());
	}

	public List<UfEntity> getUfList() {
		return ufList;
	}

	public void setUfList(List<UfEntity> ufList) {
		this.ufList = ufList;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PaisEntity)) {
            return false;
        }

        PaisEntity that = (PaisEntity) obj;
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
    
    
    public List<IndiceEconomicoEntity> getIndiceList() {
		return indiceList;
	}

	public void setIndiceList(List<IndiceEconomicoEntity> indiceList) {
		this.indiceList = indiceList;
	}

	@Override
    public String toString() {
    	return nomePtbr;
    }

}