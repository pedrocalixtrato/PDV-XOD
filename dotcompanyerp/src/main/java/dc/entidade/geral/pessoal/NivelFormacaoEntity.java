package dc.entidade.geral.pessoal;

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
@Table(name = "nivel_formacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NivelFormacaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nivel_formacao_id_seq")
	@SequenceGenerator(name = "nivel_formacao_id_seq", sequenceName = "nivel_formacao_id_seq", allocationSize = 1, initialValue = 0)
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
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao;

	@Field
	@Caption("Grau de instrução CAGED")
	@Column(name = "GRAU_INSTRUCAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoCaged;

	@Field
	@Caption("Grau de instrução SEFIP")
	@Column(name = "GRAU_INSTRUCAO_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoSefip;

	@Field
	@Caption("Grau de instrução RAIS")
	@Column(name = "GRAU_INSTRUCAO_RAIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoRais;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */
	
	@OneToMany(mappedBy = "nivelFormacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ColaboradorEntity> colaboradorList = new ArrayList<ColaboradorEntity>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public NivelFormacaoEntity() {

	}

	public NivelFormacaoEntity(Integer id) {
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

	public Integer getGrauInstrucaoCaged() {
		return grauInstrucaoCaged;
	}

	public void setGrauInstrucaoCaged(Integer grauInstrucaoCaged) {
		this.grauInstrucaoCaged = (grauInstrucaoCaged == null ? new Integer(0)
				: grauInstrucaoCaged);
	}

	public Integer getGrauInstrucaoSefip() {
		return grauInstrucaoSefip;
	}

	public void setGrauInstrucaoSefip(Integer grauInstrucaoSefip) {
		this.grauInstrucaoSefip = (grauInstrucaoSefip == null ? new Integer(0)
				: grauInstrucaoSefip);
	}

	public Integer getGrauInstrucaoRais() {
		return grauInstrucaoRais;
	}

	public void setGrauInstrucaoRais(Integer grauInstrucaoRais) {
		this.grauInstrucaoRais = (grauInstrucaoRais == null ? new Integer(0)
				: grauInstrucaoRais);
	}
	
	public List<ColaboradorEntity> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<ColaboradorEntity> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NivelFormacaoEntity)) {
            return false;
        }

        NivelFormacaoEntity that = (NivelFormacaoEntity) obj;
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
    
    @Override
    public String toString() {
    	return nome;
    }

}