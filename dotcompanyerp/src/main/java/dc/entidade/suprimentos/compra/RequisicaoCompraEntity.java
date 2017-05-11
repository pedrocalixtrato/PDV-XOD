package dc.entidade.suprimentos.compra;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * The persistent class for the compra_requisicao database table.
 * 
 */
@Entity
@Table(name = "compra_requisicao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RequisicaoCompraEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_requisicao_id_seq")
	@SequenceGenerator(name = "compra_requisicao_id_seq", sequenceName = "compra_requisicao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_requisicao")
	@Caption("Data de Requisição")
	@NotNull(message = "Data Rrequisição é Obrigatório!")
	private Date dataRequisicao;

	@Lob
	@Field
	@Caption("Observacao")
	@Column(name = "OBSERVACAO")
	@Type(type = "text")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_colaborador")
	@Caption("Requisitante")
	@NotNull(message = "Colaborador é Obrigatório!")
	private ColaboradorEntity colaborador;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_compra_tipo_requisicao")
	@Caption("Tipo Requisição")
	@NotNull(message = "Tipo Requisição é Obrigatório!")
	private TipoRequisicaoEntity tipoRequisicao;

	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<RequisicaoCompraDetalheEntity> requisicaoDetalhes = new ArrayList<>();

	public RequisicaoCompraEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRequisicao() {
		return this.dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public TipoRequisicaoEntity getTipoRequisicao() {
		return this.tipoRequisicao;
	}

	public void setTipoRequisicao(TipoRequisicaoEntity tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}

	public List<RequisicaoCompraDetalheEntity> getRequisicaoDetalhes() {
		return this.requisicaoDetalhes;
	}

	public RequisicaoCompraDetalheEntity addRequisicaoDetalhe(
			RequisicaoCompraDetalheEntity compraRequisicaoDetalhe) {
		getRequisicaoDetalhes().add(compraRequisicaoDetalhe);
		compraRequisicaoDetalhe.setRequisicao(this);

		return compraRequisicaoDetalhe;
	}

	public RequisicaoCompraDetalheEntity removeRequisicaoDetalhe(
			RequisicaoCompraDetalheEntity compraRequisicaoDetalhe) {
		getRequisicaoDetalhes().remove(compraRequisicaoDetalhe);
		compraRequisicaoDetalhe.setRequisicao(null);

		return compraRequisicaoDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return getColaborador().getPessoa().getNome();
	}
	
	

}