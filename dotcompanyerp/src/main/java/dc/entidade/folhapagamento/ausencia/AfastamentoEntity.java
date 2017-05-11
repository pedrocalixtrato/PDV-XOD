package dc.entidade.folhapagamento.ausencia;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.visao.spring.DCDateBridge;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_afastamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AfastamentoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_afastamento_id_seq")
	@SequenceGenerator(name = "folha_afastamento_id_seq", sequenceName = "folha_afastamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_inicio")
	@Caption("Data início")
	private Date dataInicio;

	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Column(name = "data_fim")
	@Caption("Data Fim")
	private Date dataFim;

	@Column(name = "dias_afastado")
	@Field
	@Caption("Dias afastado")
	private Integer diasAfastado = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	/*
	 * id_colaborador integer NOT NULL, id_folha_tipo_afastamento integer NOT
	 * NULL,
	 */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	@ManyToOne
	@JoinColumn(name = "id_folha_tipo_afastamento", nullable = false)
	@Caption("Tipo de afastamento")
	private TipoAfastamentoEntity tipoAfastamento;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public AfastamentoEntity() {
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

	public Integer getDiasAfastado() {
		return diasAfastado;
	}

	public void setDiasAfastado(Integer diasAfastado) {
		this.diasAfastado = diasAfastado;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public TipoAfastamentoEntity getTipoAfastamento() {
		return tipoAfastamento;
	}

	public void setTipoAfastamento(TipoAfastamentoEntity tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}