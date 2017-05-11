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
@Table(name = "folha_ppp_fator_risco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PppFatorRiscoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_ppp_fator_risco_id_seq")
	@SequenceGenerator(name = "folha_ppp_fator_risco_id_seq", sequenceName = "folha_ppp_fator_risco_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_inicio")
	@Field
	@Caption("Data do início")
	private Date dataInicio;

	@Column(name = "data_fim")
	@Field
	@Caption("Data do término")
	private Date dataTermino;

	@Column(name = "tipo")
	@Field
	@Caption("Tipo")
	private String tipo = "";

	@Column(name = "fator_risco")
	@Field
	@Caption("Fator de risco")
	private String fatorRisco = "";

	@Column(name = "intensidade")
	@Field
	@Caption("Intensidade")
	private String intensidade = "";

	@Column(name = "tecnica_utilizada")
	@Field
	@Caption("Técnica utilizada")
	private String tecnicaUtilizada = "";

	@Column(name = "epc_eficaz")
	@Field
	@Caption("EPC eficaz")
	private String epcEficaz = "";

	@Column(name = "epi_eficaz")
	@Field
	@Caption("EPI eficaz")
	private String epiEficaz = "";

	@Column(name = "ca_epi")
	@Field
	@Caption("CA EPI")
	private Integer caEpi = new Integer(0);

	@Column(name = "atendimento_nr06_1")
	@Field
	@Caption("Atendimento Nr 06 1")
	private String atendimentoNr061 = "";

	@Column(name = "atendimento_nr06_2")
	@Field
	@Caption("Atendimento Nr 06 2")
	private String atendimentoNr062 = "";

	@Column(name = "atendimento_nr06_3")
	@Field
	@Caption("Atendimento Nr 06 3")
	private String atendimentoNr063 = "";

	@Column(name = "atendimento_nr06_4")
	@Field
	@Caption("Atendimento Nr 06 4")
	private String atendimentoNr064 = "";

	@Column(name = "atendimento_nr06_5")
	@Field
	@Caption("Atendimento Nr 06 5")
	private String atendimentoNr065 = "";

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

	public PppFatorRiscoEntity() {
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

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public String getFatorRisco() {
		return fatorRisco;
	}

	public void setFatorRisco(String fatorRisco) {
		this.fatorRisco = (fatorRisco == null ? "" : fatorRisco.toUpperCase());
	}

	public String getIntensidade() {
		return intensidade;
	}

	public void setIntensidade(String intensidade) {
		this.intensidade = (intensidade == null ? "" : intensidade
				.toUpperCase());
	}

	public String getTecnicaUtilizada() {
		return tecnicaUtilizada;
	}

	public void setTecnicaUtilizada(String tecnicaUtilizada) {
		this.tecnicaUtilizada = (tecnicaUtilizada == null ? ""
				: tecnicaUtilizada.toUpperCase());
	}

	public String getEpcEficaz() {
		return epcEficaz;
	}

	public void setEpcEficaz(String epcEficaz) {
		this.epcEficaz = (epcEficaz == null ? "" : epcEficaz.toUpperCase());
	}

	public String getEpiEficaz() {
		return epiEficaz;
	}

	public void setEpiEficaz(String epiEficaz) {
		this.epiEficaz = (epiEficaz == null ? "" : epiEficaz.toUpperCase());
	}

	public Integer getCaEpi() {
		return caEpi;
	}

	public void setCaEpi(Integer caEpi) {
		this.caEpi = (caEpi == null ? new Integer(0) : caEpi);
	}

	public String getAtendimentoNr061() {
		return atendimentoNr061;
	}

	public void setAtendimentoNr061(String atendimentoNr061) {
		this.atendimentoNr061 = (atendimentoNr061 == null ? ""
				: atendimentoNr061.toUpperCase());
	}

	public String getAtendimentoNr062() {
		return atendimentoNr062;
	}

	public void setAtendimentoNr062(String atendimentoNr062) {
		this.atendimentoNr062 = (atendimentoNr062 == null ? ""
				: atendimentoNr062.toUpperCase());
	}

	public String getAtendimentoNr063() {
		return atendimentoNr063;
	}

	public void setAtendimentoNr063(String atendimentoNr063) {
		this.atendimentoNr063 = (atendimentoNr063 == null ? ""
				: atendimentoNr063.toUpperCase());
	}

	public String getAtendimentoNr064() {
		return atendimentoNr064;
	}

	public void setAtendimentoNr064(String atendimentoNr064) {
		this.atendimentoNr064 = (atendimentoNr064 == null ? ""
				: atendimentoNr064.toUpperCase());
	}

	public String getAtendimentoNr065() {
		return atendimentoNr065;
	}

	public void setAtendimentoNr065(String atendimentoNr065) {
		this.atendimentoNr065 = (atendimentoNr065 == null ? ""
				: atendimentoNr065.toUpperCase());
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