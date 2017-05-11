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
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_ppp_atividade")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PppAtividadeEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_ppp_atividade_id_seq")
	@SequenceGenerator(name = "folha_ppp_atividade_id_seq", sequenceName = "folha_ppp_atividade_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_inicio")
	@Field
	@Caption("Data Início")
	private Date dataInicio;

	@Column(name = "data_fim")
	@Field
	@Caption("Data Fim")
	private Date dataFim;

	@Column(name = "descricao")
	@Field
	@Caption("Descrição")
	private String descricao = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_folha_ppp integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_folha_ppp", nullable = false)
	@Caption("Ppp")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private PppEntity ppp;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public PppAtividadeEntity() {
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public PppEntity getPpp() {
		return ppp;
	}

	public void setPpp(PppEntity ppp) {
		this.ppp = ppp;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}