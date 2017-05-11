package dc.entidade.suprimentos.estoque;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

@Entity
@Table(name = "estoque_contagem_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContagemCabecalhoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_contagem_cabecalho_id_seq")
	@SequenceGenerator(name = "estoque_contagem_cabecalho_id_seq", sequenceName = "estoque_contagem_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data da contagem")
	@Column(name = "data_contagem")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataContagem;

	@Field
	@Caption("Estoque atualizado")
	@Column(name = "estoque_atualizado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String estoqueAtualizado = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "contagemCabecalho", orphanRemoval = true, cascade
	// = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "contagemCabecalho", fetch = FetchType.LAZY)
	private List<ContagemDetalheEntity> contagemDetalheList = new ArrayList<>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public ContagemCabecalhoEntity() {
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

	public Date getDataContagem() {
		return dataContagem;
	}

	public void setDataContagem(Date dataContagem) {
		this.dataContagem = dataContagem;
	}

	public String getEstoqueAtualizado() {
		return estoqueAtualizado;
	}

	public void setEstoqueAtualizado(String estoqueAtualizado) {
		this.estoqueAtualizado = (estoqueAtualizado == null ? "".trim()
				: estoqueAtualizado.toUpperCase().trim());
	}

	public List<ContagemDetalheEntity> getContagemDetalheList() {
		return contagemDetalheList;
	}

	public void setContagemDetalheList(
			List<ContagemDetalheEntity> contagemDetalheList) {
		this.contagemDetalheList = contagemDetalheList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}