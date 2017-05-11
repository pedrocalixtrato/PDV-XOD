package dc.entidade.patrimonio;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
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
@Table(name = "patrim_movimentacao_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class MovimentacaoBemEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_movimentacao_bem_id_seq")
	@SequenceGenerator(name = "patrim_movimentacao_bem_id_seq", sequenceName = "patrim_movimentacao_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_movimentacao")
	@Field
	@Caption("Data da movimentação")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataMovimentacao;

	@Column(name = "responsavel")
	@Field
	@Caption("Responsável")
	@Size(max = 50, message = "Tamanho inválido.")
	private String responsavel = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_patrim_bem", nullable = false)
	@Caption("Bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private BemEntity bem;

	/**
	 * REFERENCIA - LIST
	 */

	@ManyToOne
	@JoinColumn(name = "id_patrim_tipo_movimentacao", nullable = false)
	@Caption("Tipo de movimentação")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private TipoMovimentacaoEntity tipoMovimentacao;

	/**
	 * CONSTRUTOR
	 */

	public MovimentacaoBemEntity() {

	}

	public MovimentacaoBemEntity(Integer id) {
		this.id = id;
	}

	public MovimentacaoBemEntity(Integer id, String responsavel) {
		this.id = id;
		this.responsavel = responsavel;
	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = (responsavel == null ? "" : responsavel
				.toUpperCase());
	}

	public BemEntity getBem() {
		return bem;
	}

	public void setBem(BemEntity bem) {
		this.bem = bem;
	}

	public TipoMovimentacaoEntity getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacaoEntity tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}