package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

//@Entity
//@Table(name = "")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AlteracaoSalarialEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
	@SequenceGenerator(name = "", sequenceName = "", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	/**
	 * CONSTRUTOR
	 */

	public AlteracaoSalarialEntity() {
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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}