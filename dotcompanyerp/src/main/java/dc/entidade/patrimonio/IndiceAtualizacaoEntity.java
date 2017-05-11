package dc.entidade.patrimonio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.converter.RunField;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "patrim_indice_atualizacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceAtualizacaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_indice_atualizacao_id_seq")
	@SequenceGenerator(name = "patrim_indice_atualizacao_id_seq", sequenceName = "patrim_indice_atualizacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_indice")
	@Field
	@Caption("Data do Índice")
	@Temporal(value = TemporalType.TIMESTAMP)
	@RunField(mappedName = "dataIndice")
	private Date dataIndice;

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	@Size(max = 10, message = "Tamanho inválido.")
	@RunField(mappedName = "nome")
	private String nome = "";

	@Column(name = "valor")
	@Field
	@Caption("Valor")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@RunField(mappedName = "valor")
	private Double valor = new Double(0.0);

	@Column(name = "valor_alternativo")
	@Field
	@Caption("Valor alternativo")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	@RunField(mappedName = "valorAlternativo")
	private Double valorAlternativo = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public IndiceAtualizacaoEntity() {
		// TODO Auto-generated constructor stub
	}

	public IndiceAtualizacaoEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataIndice() {
		return dataIndice;
	}

	public void setDataIndice(Date dataIndice) {
		this.dataIndice = dataIndice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = (valor == null ? new Double(0.0) : valor);
	}

	public Double getValorAlternativo() {
		return valorAlternativo;
	}

	public void setValorAlternativo(Double valorAlternativo) {
		this.valorAlternativo = (valorAlternativo == null ? new Double(0.0)
				: valorAlternativo);
	}

	// /**
	// * HASH E EQUALS
	// */
	//
	// @Override
	// public int hashCode() {
	// return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	// }
	//
	// @Override
	// public boolean equals(Object object) {
	// if (object instanceof IndiceAtualizacaoEntity == false)
	// return false;
	//
	// if (this == object)
	// return true;
	//
	// final IndiceAtualizacaoEntity other = (IndiceAtualizacaoEntity) object;
	//
	// return EqualsBuilder.reflectionEquals(this, other);
	// }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}