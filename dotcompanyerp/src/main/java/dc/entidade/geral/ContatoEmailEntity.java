package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "contato_email")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContatoEmailEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_email_id_seq")
	@SequenceGenerator(name = "contato_email_id_seq", sequenceName = "contato_email_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "TIPO_EMAIL_ID", nullable = false)
	private int tipoEmailId;

	@Basic(optional = false)
	@Column(name = "CONTATO_ID", nullable = false)
	private int contatoId;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL", length = 100)
	private String email;

	public ContatoEmailEntity() {

	}

	public ContatoEmailEntity(Integer id) {
		this.id = id;
	}

	public ContatoEmailEntity(Integer id, int tipoEmailId, int contatoId) {
		this.id = id;
		this.tipoEmailId = tipoEmailId;
		this.contatoId = contatoId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTipoEmailId() {
		return tipoEmailId;
	}

	public void setTipoEmailId(int tipoEmailId) {
		this.tipoEmailId = tipoEmailId;
	}

	public int getContatoId() {
		return contatoId;
	}

	public void setContatoId(int contatoId) {
		this.contatoId = contatoId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}