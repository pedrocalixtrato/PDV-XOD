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
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "ferias_periodo_aquisitivo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FeriasPeriodoAquisitivoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ferias_periodo_aquisitivo_id_seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ferias_periodo_aquisitivo_id_seq")
	@SequenceGenerator(name = "ferias_periodo_aquisitivo_id_seq", sequenceName = "ferias_periodo_aquisitivo_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_inicio")
	@Field
	@Caption("Data início")
	private Date dataInicio;

	@Column(name = "situacao")
	@Field
	@Caption("Situação")
	private String situacao = "";

	@Column(name = "limite_para_gozo")
	@Field
	@Caption("Limite para gozo")
	private Date limiteParaGozo;

	@Column(name = "descontar_faltas")
	@Field
	@Caption("Descontar faltas")
	private String descontarFaltas = "";

	@Column(name = "desconsiderar_afastamento")
	@Field
	@Caption("Desconsiderar afastamento")
	private String desconsiderarAfastamento = "";

	@Column(name = "afastamento_previdencia")
	@Field
	@Caption("Afastamento previdência")
	private Integer afastamentoPrevidencia = new Integer(0);

	@Column(name = "afastamento_sem_remun")
	@Field
	@Caption("Afastamento sem remuneração")
	private Integer afastamentoSemRemun = new Integer(0);

	@Column(name = "afastamento_com_remun")
	@Field
	@Caption("Afastamento com remuneração")
	private Integer afastamentoComRemun = new Integer(0);

	@Column(name = "dias_direito")
	@Field
	@Caption("Dias de direito")
	private Integer diasDireito = new Integer(0);

	@Column(name = "dias_gozados")
	@Field
	@Caption("Dias gozados")
	private Integer diasGozados = new Integer(0);

	@Column(name = "dias_faltas")
	@Field
	@Caption("Dias de faltas")
	private Integer diasFaltas = new Integer(0);

	@Column(name = "dias_restantes")
	@Field
	@Caption("Dias restantes")
	private Integer diasRestantes = new Integer(0);

	@Column(name = "data_fim")
	@Field
	@Caption("Data término")
	private Date dataFim;

	/**
	 * REFERENCIA - FK
	 */

	// id_colaborador integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

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

	public FeriasPeriodoAquisitivoEntity() {
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = (situacao == null ? "" : situacao.toUpperCase());
	}

	public Date getLimiteParaGozo() {
		return limiteParaGozo;
	}

	public void setLimiteParaGozo(Date limiteParaGozo) {
		this.limiteParaGozo = limiteParaGozo;
	}

	public String getDescontarFaltas() {
		return descontarFaltas;
	}

	public void setDescontarFaltas(String descontarFaltas) {
		this.descontarFaltas = (descontarFaltas == null ? "" : descontarFaltas
				.toUpperCase());
	}

	public String getDesconsiderarAfastamento() {
		return desconsiderarAfastamento;
	}

	public void setDesconsiderarAfastamento(String desconsiderarAfastamento) {
		this.desconsiderarAfastamento = (desconsiderarAfastamento == null ? ""
				: desconsiderarAfastamento.toUpperCase());
	}

	public Integer getAfastamentoPrevidencia() {
		return afastamentoPrevidencia;
	}

	public void setAfastamentoPrevidencia(Integer afastamentoPrevidencia) {
		this.afastamentoPrevidencia = (afastamentoPrevidencia == null ? new Integer(
				0) : afastamentoPrevidencia);
	}

	public Integer getAfastamentoSemRemun() {
		return afastamentoSemRemun;
	}

	public void setAfastamentoSemRemun(Integer afastamentoSemRemun) {
		this.afastamentoSemRemun = (afastamentoSemRemun == null ? new Integer(0)
				: afastamentoSemRemun);
	}

	public Integer getAfastamentoComRemun() {
		return afastamentoComRemun;
	}

	public void setAfastamentoComRemun(Integer afastamentoComRemun) {
		this.afastamentoComRemun = (afastamentoComRemun == null ? new Integer(0)
				: afastamentoComRemun);
	}

	public Integer getDiasDireito() {
		return diasDireito;
	}

	public void setDiasDireito(Integer diasDireito) {
		this.diasDireito = (diasDireito == null ? new Integer(0) : diasDireito);
	}

	public Integer getDiasGozados() {
		return diasGozados;
	}

	public void setDiasGozados(Integer diasGozados) {
		this.diasGozados = (diasGozados == null ? new Integer(0) : diasGozados);
	}

	public Integer getDiasFaltas() {
		return diasFaltas;
	}

	public void setDiasFaltas(Integer diasFaltas) {
		this.diasFaltas = (diasFaltas == null ? new Integer(0) : diasFaltas);
	}

	public Integer getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(Integer diasRestantes) {
		this.diasRestantes = (diasRestantes == null ? new Integer(0)
				: diasRestantes);
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
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