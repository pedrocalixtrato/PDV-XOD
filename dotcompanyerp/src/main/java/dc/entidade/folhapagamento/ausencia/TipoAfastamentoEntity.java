package dc.entidade.folhapagamento.ausencia;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_tipo_afastamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class TipoAfastamentoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_tipo_afastamento_id_seq")
	@SequenceGenerator(name = "folha_tipo_afastamento_id_seq", sequenceName = "folha_tipo_afastamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "codigo")
	@Field
	@Caption("Código")
	private String codigo = "";

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	private String nome = "";

	@Column(name = "descricao")
	@Field
	@Caption("Descrição")
	private String descricao = "";

	/**
	 * REFERENCIA - FK
	 */

	/* id_empresa integer NOT NULL, */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_empresa", nullable = false)
	 * 
	 * @Caption("Empresa")
	 * 
	 * @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	 * private Empresa empresa;
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "tipoAfastamento", fetch = FetchType.LAZY)
	private List<AfastamentoEntity> afastamentoList;

	/**
	 * CONSTRUTOR
	 */

	public TipoAfastamentoEntity() {

	}

	public TipoAfastamentoEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = (codigo == null ? "" : codigo.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	/*
	 * public Empresa getEmpresa() { return empresa; }
	 * 
	 * public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	 */

	public List<AfastamentoEntity> getAfastamentoList() {
		return afastamentoList;
	}

	public void setAfastamentoList(List<AfastamentoEntity> afastamentoList) {
		this.afastamentoList = afastamentoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}