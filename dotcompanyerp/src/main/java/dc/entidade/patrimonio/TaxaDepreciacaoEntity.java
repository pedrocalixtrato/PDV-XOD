package dc.entidade.patrimonio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
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
@Table(name = "patrim_taxa_depreciacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class TaxaDepreciacaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_taxa_depreciacao_id_seq")
	@SequenceGenerator(name = "patrim_taxa_depreciacao_id_seq", sequenceName = "patrim_taxa_depreciacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "ncm")
	@Field
	@Caption("Ncm")
	@Size(max = 8, message = "Tamanho inválido.")
	private String ncm = "";

	@Column(name = "bem")
	@Field
	@Caption("Bem")
	@Size(max = 250, message = "Tamanho inválido.")
	private String bem = "";

	@Column(name = "vida")
	@Field
	@Caption("Vida")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double vida = new Double(0.0);

	@Column(name = "taxa")
	@Field
	@Caption("Taxa")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxa = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public TaxaDepreciacaoEntity() {

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

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = (ncm == null ? "" : ncm.toUpperCase());
	}

	public String getBem() {
		return bem;
	}

	public void setBem(String bem) {
		this.bem = (bem == null ? "" : bem.toUpperCase());
	}

	public Double getVida() {
		return vida;
	}

	public void setVida(Double vida) {
		this.vida = vida;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}