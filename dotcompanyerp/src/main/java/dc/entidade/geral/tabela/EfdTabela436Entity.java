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
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "efd_tabela_436")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EfdTabela436Entity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "efd_tabela_436_id_seq")
	@SequenceGenerator(name = "efd_tabela_436_id_seq", sequenceName = "efd_tabela_436_pis_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Codigo")
	@Column(name = "Codigo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigo;

	@Field
	@Caption("Descricao")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "DESCRICAO", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	public EfdTabela436Entity() {

	}

	public EfdTabela436Entity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}