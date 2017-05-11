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
import dc.entidade.administrativo.empresa.TransporteItinerarioEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_vale_transporte")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ValeTransporteEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_vale_transporte_id_seq")
	@SequenceGenerator(name = "folha_vale_transporte_id_seq", sequenceName = "folha_vale_transporte_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "quantidade")
	@Field
	@Caption("Quantidade")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Integer quantidade = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	/*
	 * id_empresa_transp_itin integer NOT NULL, id_colaborador integer NOT NULL,
	 */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	@ManyToOne
	@JoinColumn(name = "id_empresa_transp_itin", nullable = false)
	@Caption("Transporte itinerário")
	private TransporteItinerarioEntity transporteItinerario;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public ValeTransporteEntity() {
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public TransporteItinerarioEntity getTransporteItinerario() {
		return transporteItinerario;
	}

	public void setTransporteItinerario(
			TransporteItinerarioEntity transporteItinerario) {
		this.transporteItinerario = transporteItinerario;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}