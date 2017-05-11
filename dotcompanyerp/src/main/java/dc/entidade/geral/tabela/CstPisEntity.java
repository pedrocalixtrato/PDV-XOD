package dc.entidade.geral.tabela;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

/**
 * 
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "cst_pis")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CstPisEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cst_pis_id_seq")
	@SequenceGenerator(name = "cst_pis_id_seq", sequenceName = "cst_pis_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Codigo")
	@Column(name = "Codigo")
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código é obrigatório")
	@Length(max = 2, message = "O tamanho deve ser no máximo 2 caracteres")
	private String codigo = "";

	@Field
	@Caption("Descricao")
	@Column(name = "DESCRICAO", length = 250)
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é obrigatório")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	private String descricao = "";

	public CstPisEntity() {

	}

	public CstPisEntity(Integer id) {
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

	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof CstPisEntity)) {
	           return false;
	    }

	    CstPisEntity that = (CstPisEntity) obj;
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