package dc.entidade.folhapagamento.movimento;

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

/**
 * 
 * 
 */

@Entity(name = "folhapagamentoFechamentoEntity")
@Table(name = "folha_fechamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FechamentoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_fechamento_id_seq")
	@SequenceGenerator(name = "folha_fechamento_id_seq", sequenceName = "folha_fechamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "fechamento_atual")
	@Field
	@Caption("Fechamento atual")
	private String fechamentoAtual = "";

	@Column(name = "proximo_fechamento")
	@Field
	@Caption("Próximo fechamento")
	private String proximoFechamento = "";

	/**
	 * REFERENCIA - FK
	 */

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

	public FechamentoEntity() {
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

	public String getFechamentoAtual() {
		return fechamentoAtual;
	}

	public void setFechamentoAtual(String fechamentoAtual) {
		this.fechamentoAtual = (fechamentoAtual == null ? "" : fechamentoAtual
				.toUpperCase());
	}

	public String getProximoFechamento() {
		return proximoFechamento;
	}

	public void setProximoFechamento(String proximoFechamento) {
		this.proximoFechamento = (proximoFechamento == null ? ""
				: proximoFechamento.toUpperCase());
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