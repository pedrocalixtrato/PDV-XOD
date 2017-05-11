package dc.entidade.geral.eventos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import dc.anotacoes.Caption;
import dc.control.enums.TipoSemestre;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "contrato_eventos")
@XmlRootElement
@Indexed
public class ContratoEventosEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contrato_eventos_id_seq")
	@SequenceGenerator(name = "contrato_eventos_id_seq", sequenceName = "contrato_eventos_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	private Integer id;

	@Field
	@Caption("Curso")
	@Column(name = "CURSO")
	@ComboValue
	@NotNull(message = "Curso é Obrigatório!")
	private String curso = "";

	@Field
	@Caption("Unidade")
	@Column(name = "UNIDADE")
	@ComboValue
	@NotNull(message = "Unidade é Obrigatório!")
	private String unidade = "";

	@Field(index = Index.YES, analyze=Analyze.NO, store = Store.YES)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption("Data Contrato")
	@Column(name = "DATA_CONTRATO")
	private Date dataContrato;

	@Field(analyze = Analyze.NO, index = Index.YES, store=Store.YES)
	@Caption("Data Primeiro Evento")
	@Column(name = "DATA_PRIMEIRO_EVENTO")
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataPrimeiroEvento;

	@Field
	@Caption("Quantidade Formandos")
	@Column(name = "QUANTIDADE_FORMANDOS")
	@NotNull(message = "Quantidade de Formandos é Obrigatório!")
	private Integer quantidadeFormandos;

	@Field
	@Caption("Ano Formatura")
	@Column(name = "ANO_FORMATURA")
	@ComboValue
	@NotNull(message = "Ano de Formatura é Obrigatório!")
	private String anoFormatura = "";

	@Caption("Nome Cerimonial")
	@JoinColumn(name = "id_nome_cerimonial", referencedColumnName = "id")
	// @ManyToOne(optional = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@IndexedEmbedded(includePaths = { "nome" })
	private CerimonialEventosEntity nomeCerimonial;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo Semestre")
	@Column(name = "TIPO_SEMESTRE")
	@ComboValue
	private TipoSemestre tipoSemestre;

	public ContratoEventosEntity() {
	}

	public ContratoEventosEntity(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = (curso == null ? "".trim() : curso.toUpperCase().trim());
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = (unidade == null ? "".trim() : unidade.toUpperCase().trim());
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public Date getDataPrimeiroEvento() {
		return dataPrimeiroEvento;
	}

	public void setDataPrimeiroEvento(Date dataPrimeiroEvento) {
		this.dataPrimeiroEvento = dataPrimeiroEvento;
	}

	public Integer getQuantidadeFormandos() {
		return quantidadeFormandos;
	}

	public void setQuantidadeFormandos(Integer quantidadeFormandos) {
		this.quantidadeFormandos = quantidadeFormandos;
	}

	public String getAnoFormatura() {
		return anoFormatura;
	}

	public void setAnoFormatura(String anoFormatura) {
		this.anoFormatura = anoFormatura;
	}

	public TipoSemestre getTipoSemestre() {
		return tipoSemestre;
	}

	public void setTipoSemestre(TipoSemestre tipoSemestre) {
		this.tipoSemestre = tipoSemestre;
	}

	public CerimonialEventosEntity getNomeCerimonial() {
		return nomeCerimonial;
	}

	public void setNomeCerimonial(CerimonialEventosEntity nomeCerimonial) {
		this.nomeCerimonial = nomeCerimonial;
	}

	@Override
	public String toString() {
		return curso;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContratoEventosEntity)) {
			return false;
		}

		ContratoEventosEntity that = (ContratoEventosEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(getId(), that.getId());
		return eb.isEquals();
	}

	@Override
	public int hashCode() {
		if (getId() == null) {
			return super.hashCode();
		} else {
			return new HashCodeBuilder().append(id).toHashCode();
		}
	}

}
