package dc.entidade.folhapagamento.inss;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
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

@Entity
@Table(name = "folha_inss_retencao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RetencaoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_inss_retencao_id_seq")
	@SequenceGenerator(name = "folha_inss_retencao_id_seq", sequenceName = "folha_inss_retencao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Caption("Valor mensal")
	@Column(name = "valor_mensal")
	@NotNull(message = "Valor Mensal é Obrigatório!")
	private BigDecimal valorMensal;

	@Field
	@Caption("Valor 13")
	@Column(name = "valor_13")
	@NotNull(message = "Valor 13 é Obrigatório!")
	private BigDecimal valor13;

	/**
	 * REFERENCIA - FK
	 */

	/*
	 * id_folha_inss integer NOT NULL, id_folha_inss_servico integer NOT NULL,
	 */

	@ManyToOne
	@JoinColumn(name = "id_folha_inss", nullable = false)
	@Caption("INSS")
	private InssEntity inss;

	@ManyToOne
	@JoinColumn(name = "id_folha_inss_servico", nullable = false)
	@Caption("Serviço")
	private ServicoEntity servico;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public RetencaoEntity() {
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
	
	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(BigDecimal valorMensal) {
		this.valorMensal = valorMensal;
	}

	public BigDecimal getValor13() {
		return valor13;
	}

	public void setValor13(BigDecimal valor13) {
		this.valor13 = valor13;
	}

	public InssEntity getInss() {
		return inss;
	}

	public void setInss(InssEntity inss) {
		this.inss = inss;
	}

	public ServicoEntity getServico() {
		return servico;
	}

	public void setServico(ServicoEntity servico) {
		this.servico = servico;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}