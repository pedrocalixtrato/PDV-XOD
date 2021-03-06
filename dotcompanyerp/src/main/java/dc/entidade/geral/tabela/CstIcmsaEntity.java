package dc.entidade.geral.tabela;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "cst_icms_a")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CstIcmsaEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cst_icms_a_id_seq")
	@SequenceGenerator(name = "cst_icms_a_id_seq", sequenceName = "cst_icms_a_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Codigo")
	@Column(name = "Codigo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código é obrigatório")
	@Length(max = 1, message = "O tamanho deve ser no máximo 1 caractere")
	private String codigo;

	@Field
	@Caption("Descrição")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "DESCRICAO", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é obrigatório")
	private String descricao;

	@Lob
	@Field
	@Caption("Observação")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "OBSERVACAO")
	@Type(type = "text")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Observação é obrigatório")
	private String observacao;

	public CstIcmsaEntity() {

	}

	public CstIcmsaEntity(Integer id) {
		this.id = id;
	}

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
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof CstIcmsaEntity)) {
	           return false;
	    }

	    CstIcmsaEntity that = (CstIcmsaEntity) obj;
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
		return descricao;
	}

}