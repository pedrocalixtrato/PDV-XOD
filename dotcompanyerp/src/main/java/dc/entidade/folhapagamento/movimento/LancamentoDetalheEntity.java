package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;

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
import dc.entidade.folhapagamento.cadastro.EventoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_lancamento_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "folha_lancamento_detalhe_id_seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_lancamento_detalhe_id_seq")
	@SequenceGenerator(name = "folha_lancamento_detalhe_id_seq", sequenceName = "folha_lancamento_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "origem")
	@Field
	@Caption("Origem")
	private Double origem = new Double(0.0);

	@Column(name = "provento")
	@Field
	@Caption("Provento")
	private Double provento = new Double(0.0);

	@Column(name = "desconto")
	@Field
	@Caption("Desconto")
	private Double desconto = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	/* id_folha_evento integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_folha_evento", nullable = false)
	@Caption("Evento")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private EventoEntity evento;

	/* id_folha_lancamento_cabecalho integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_folha_lancamento_cabecalho", nullable = false)
	@Caption("Lançamento cabeçalho")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LancamentoCabecalhoEntity lancamentoCabecalho;

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

	public LancamentoDetalheEntity() {
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

	public Double getOrigem() {
		return origem;
	}

	public void setOrigem(Double origem) {
		this.origem = (origem == null ? new Double(0.0) : origem);
	}

	public Double getProvento() {
		return provento;
	}

	public void setProvento(Double provento) {
		this.provento = (provento == null ? new Double(0.0) : provento);
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = (desconto == null ? new Double(0.0) : desconto);
	}

	public EventoEntity getEvento() {
		return evento;
	}

	public void setEvento(EventoEntity evento) {
		this.evento = evento;
	}

	public LancamentoCabecalhoEntity getLancamentoCabecalho() {
		return lancamentoCabecalho;
	}

	public void setLancamentoCabecalho(
			LancamentoCabecalhoEntity lancamentoCabecalho) {
		this.lancamentoCabecalho = lancamentoCabecalho;
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