package dc.entidade.suprimentos.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.pessoal.ColaboradorEntity;

@Entity
@Table(name = "estoque_reajuste_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ReajusteCabecalhoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_reajuste_cabecalho_id_seq")
	@SequenceGenerator(name = "estoque_reajuste_cabecalho_id_seq", sequenceName = "estoque_reajuste_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_reajuste")
	@Caption("Data do Reajuste")
	private Date dataReajuste;

	@Caption("Porcentagem")
	private BigDecimal porcentagem;

	@Caption("Tipo de Reajuste")
	@Column(name = "tipo_reajuste")
	private String tipo;

	@Caption("Tipo de Reajuste")
	@Transient
	private String tipoString;

	@Caption("Colaborador")
	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private ColaboradorEntity colaborador;

	@OneToMany(mappedBy = "reajuste", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<ReajusteDetalheEntity> detalhes = new ArrayList<ReajusteDetalheEntity>();

	@Transient
	static Integer AUMENTAR = 1;

	@Transient
	static Integer DIMINUIR = 2;

	public ReajusteCabecalhoEntity() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataReajuste() {
		return dataReajuste;
	}

	public void setDataReajuste(Date dataReajuste) {
		this.dataReajuste = dataReajuste;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	public List<ReajusteDetalheEntity> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<ReajusteDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public ReajusteDetalheEntity addDetalhe(ReajusteDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setReajuste(this);
		return detalhe;
	}

	public static BigDecimal valorAumentado(BigDecimal valorOriginal,
			BigDecimal porcentagem, Integer tipo) {
		BigDecimal valorFinal = new BigDecimal(0);
		BigDecimal valorPorcentagem = porcentagem.multiply(valorOriginal);
		if (tipo.equals(AUMENTAR)) {
			valorFinal = valorOriginal.add(valorPorcentagem);
		}

		if (tipo.equals(DIMINUIR)) {
			valorFinal = valorOriginal.subtract(valorPorcentagem);
		}

		return valorFinal;
	}

	public String getTipoString() {
		return tipo.equals("1") ? "Aumentar" : "Diminuir";
	}

	public void setTipoString(String tipoString) {
		this.tipoString = tipoString;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}