package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "folha_ppp_cat")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PppCatEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_ppp_cat_id_seq")
	@SequenceGenerator(name = "folha_ppp_cat_id_seq", sequenceName = "folha_ppp_cat_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "numero_cat")
	@Field
	@Caption("Número do cat")
	private Integer numeroCat = new Integer(0);

	@Column(name = "data_afastamento")
	@Field
	@Caption("Data do afastamento")
	private Date dataAfastamento;

	@Column(name = "data_registro")
	@Field
	@Caption("Data do registro")
	private Date dataRegistro;

	/**
	 * REFERENCIA - FK
	 */

	// id_folha_ppp integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_folha_ppp", nullable = false)
	@Caption("Ppp")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private PppEntity ppp;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public PppCatEntity() {
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

	public Integer getNumeroCat() {
		return numeroCat;
	}

	public void setNumeroCat(Integer numeroCat) {
		this.numeroCat = (numeroCat == null ? new Integer(0) : numeroCat);
	}

	public Date getDataAfastamento() {
		return dataAfastamento;
	}

	public void setDataAfastamento(Date dataAfastamento) {
		this.dataAfastamento = dataAfastamento;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public PppEntity getPpp() {
		return ppp;
	}

	public void setPpp(PppEntity ppp) {
		this.ppp = ppp;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}