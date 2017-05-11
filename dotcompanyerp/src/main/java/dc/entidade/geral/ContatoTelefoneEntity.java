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
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "contato_telefone")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContatoTelefoneEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_telefone_id_seq")
	@SequenceGenerator(name = "contato_telefone_id_seq", sequenceName = "contato_telefone_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "TIPO_TELEFONE_ID", nullable = false)
	private int tipoTelefoneId;

	@Basic(optional = false)
	@Column(name = "CONTATO_ID", nullable = false)
	private int contatoId;

	@Column(name = "TELEFONE", length = 10)
	private String telefone;

	public ContatoTelefoneEntity() {

	}

	public ContatoTelefoneEntity(Integer id) {
		this.id = id;
	}

	public ContatoTelefoneEntity(Integer id, int tipoTelefoneId, int contatoId) {
		this.id = id;
		this.tipoTelefoneId = tipoTelefoneId;
		this.contatoId = contatoId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTipoTelefoneId() {
		return tipoTelefoneId;
	}

	public void setTipoTelefoneId(int tipoTelefoneId) {
		this.tipoTelefoneId = tipoTelefoneId;
	}

	public int getContatoId() {
		return contatoId;
	}

	public void setContatoId(int contatoId) {
		this.contatoId = contatoId;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}