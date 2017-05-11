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
import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "operadora_plano_saude")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperadoraPlanoSaudeEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "operadora_plano_saude_id_seq")
	@SequenceGenerator(name = "operadora_plano_saude_id_seq", sequenceName = "operadora_plano_saude_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome = "";

	@Field
	@Caption("Registro ANS")
	@Column(name = "REGISTRO_ANS", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Registro Ans é Obrigatório!")
	private String registroAns = "";
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * ********************************************************
	 */

	/**
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@OneToMany(mappedBy = "operadoraPlanoSaude", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<PlanoSaudeEntity> planoSaudeList = new ArrayList<PlanoSaudeEntity>();

	/**
	 * ********************************************************
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public OperadoraPlanoSaudeEntity() {

	}

	public OperadoraPlanoSaudeEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public OperadoraPlanoSaudeEntity(Integer id) {
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

	public String getRegistroAns() {
		return registroAns;
	}

	public void setRegistroAns(String registroAns) {
		this.registroAns = (registroAns == null ? "".trim() : registroAns
				.toUpperCase().trim());
	}
	
	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	public List<PlanoSaudeEntity> getPlanoSaudeList() {
		return planoSaudeList;
	}

	public void setPlanoSaudeList(List<PlanoSaudeEntity> planoSaudeList) {
		this.planoSaudeList = planoSaudeList;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OperadoraPlanoSaudeEntity)) {
            return false;
        }

        OperadoraPlanoSaudeEntity that = (OperadoraPlanoSaudeEntity) obj;
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