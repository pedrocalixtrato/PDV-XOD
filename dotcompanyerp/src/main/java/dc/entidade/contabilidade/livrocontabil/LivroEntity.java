package dc.entidade.contabilidade.livrocontabil;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "CONTABIL_LIVRO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LivroEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_livro_id_seq")
	@SequenceGenerator(name = "contabil_livro_id_seq", sequenceName = "contabil_livro_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao = "";

	@Field
	@Column(name = "competencia")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Competência")
	private String competencia = "";

	@Field
	@Column(name = "forma_escrituracao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Forma de escrituração")
	private String formaEscrituracao = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
	private List<TermoEntity> termoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getDescricao();
	}

	// public void setNome(String nome) {
	// setDescricao(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public LivroEntity() {
		// TODO Auto-generated constructor stub
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = (competencia == null ? "" : competencia
				.toUpperCase());
	}

	public String getFormaEscrituracao() {
		return formaEscrituracao;
	}

	public void setFormaEscrituracao(String formaEscrituracao) {
		this.formaEscrituracao = (formaEscrituracao == null ? ""
				: formaEscrituracao.toUpperCase());
	}

	public List<TermoEntity> getTermoList() {
		return termoList;
	}

	public void setTermoList(List<TermoEntity> termoList) {
		this.termoList = termoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}