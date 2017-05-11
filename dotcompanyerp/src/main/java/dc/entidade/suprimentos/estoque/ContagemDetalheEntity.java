package dc.entidade.suprimentos.estoque;

import java.math.BigDecimal;

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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.produto.ProdutoEntity;

@Entity
@Table(name = "estoque_contagem_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContagemDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_contagem_detalhe_id_seq")
	@SequenceGenerator(name = "estoque_contagem_detalhe_id_seq", sequenceName = "estoque_contagem_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Acuracidade")
	@Column(name = "acuracidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal acuracidade = new BigDecimal(0);

	@Field
	@Caption("Divergência")
	@Column(name = "divergencia")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal divergencia = new BigDecimal(0);

	@Field
	@Caption("Quantidade contada")
	@Column(name = "quantidade_contada")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeContada = new BigDecimal(0);

	@Field
	@Caption("Quantidade no sistema")
	@Column(name = "quantidade_sistema")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeSistema = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_estoque_contagem_cabecalho")
	private ContagemCabecalhoEntity contagemCabecalho;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public ContagemDetalheEntity() {
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

	public BigDecimal getAcuracidade() {
		return acuracidade;
	}

	public void setAcuracidade(BigDecimal acuracidade) {
		this.acuracidade = (acuracidade == null ? new BigDecimal(0)
				: acuracidade);
	}

	public BigDecimal getDivergencia() {
		return divergencia;
	}

	public void setDivergencia(BigDecimal divergencia) {
		this.divergencia = (divergencia == null ? new BigDecimal(0)
				: divergencia);
	}

	public BigDecimal getQuantidadeContada() {
		return quantidadeContada;
	}

	public void setQuantidadeContada(BigDecimal quantidadeContada) {
		this.quantidadeContada = (quantidadeContada == null ? new BigDecimal(0)
				: quantidadeContada);
	}

	public BigDecimal getQuantidadeSistema() {
		return quantidadeSistema;
	}

	public void setQuantidadeSistema(BigDecimal quantidadeSistema) {
		this.quantidadeSistema = (quantidadeSistema == null ? new BigDecimal(0)
				: quantidadeSistema);
	}

	public ContagemCabecalhoEntity getContagemCabecalho() {
		return contagemCabecalho;
	}

	public void setContagemCabecalho(ContagemCabecalhoEntity contagemCabecalho) {
		this.contagemCabecalho = contagemCabecalho;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}