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
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_lancamento_comissao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoComissaoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_lancamento_comissao_id_seq")
	@SequenceGenerator(name = "folha_lancamento_comissao_id_seq", sequenceName = "folha_lancamento_comissao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "competencia")
	@Field
	@Caption("Competência")
	private String competencia = "";

	@Column(name = "vencimento")
	@Field
	@Caption("Vencimento")
	private Date vencimento;

	@Column(name = "base_calculo")
	@Field
	@Caption("Base de cálculo")
	private Double baseCalculo = new Double(0.0);

	@Column(name = "valor_comissao")
	@Field
	@Caption("Valor da comissão")
	private Double valorComissao = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	/* id_vendedor integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_vendedor", nullable = false)
	@Caption("Vendedor")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private VendedorEntity vendedor;

	/* id_empresa integer NOT NULL, */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_empresa", nullable = false)
	 * 
	 * @Caption("Empresa")
	 * 
	 * @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	 * private Empresa empresa;
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public LancamentoComissaoEntity() {
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

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = (competencia == null ? "" : competencia
				.toUpperCase());
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getBaseCalculo() {
		return baseCalculo;
	}

	public void setBaseCalculo(Double baseCalculo) {
		this.baseCalculo = (baseCalculo == null ? new Double(0.0) : baseCalculo);
	}

	public Double getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(Double valorComissao) {
		this.valorComissao = (valorComissao == null ? new Double(0.0)
				: valorComissao);
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	/*
	 * public Empresa getEmpresa() { return empresa; }
	 * 
	 * public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}