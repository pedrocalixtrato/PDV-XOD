package dc.entidade.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import dc.entidade.geral.pessoal.ColaboradorEntity;

/**
 * 
 * 
 */

@Entity
@Table(name = "folha_ppp")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PppEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "folha_ppp_id_seq")
	@SequenceGenerator(name = "folha_ppp_id_seq", sequenceName = "folha_ppp_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "observacao")
	@Field
	@Caption("Observação")
	@NotNull(message = "Observação é Obrigatório!")
	private String observacao = "";

	/**
	 * REFERENCIA - FK
	 */

	/* id_colaborador integer NOT NULL, */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
    @NotNull(message = "Colaborador é Obrigatório!")
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "ppp", fetch = FetchType.LAZY)
	private List<PppAtividadeEntity> pppAtividadeList = new ArrayList<>();

	@OneToMany(mappedBy = "ppp", fetch = FetchType.LAZY)
	private List<PppCatEntity> pppCatList;

	@OneToMany(mappedBy = "ppp", fetch = FetchType.LAZY)
	private List<PppExameMedicoEntity> pppExameMedicoList;

	@OneToMany(mappedBy = "ppp", fetch = FetchType.LAZY)
	private List<PppFatorRiscoEntity> pppFatorRiscoList;

	/**
	 * CONSTRUTOR
	 */

	public PppEntity() {
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "" : observacao.toUpperCase());
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public List<PppAtividadeEntity> getPppAtividadeList() {
		return pppAtividadeList;
	}

	public void setPppAtividadeList(List<PppAtividadeEntity> pppAtividadeList) {
		this.pppAtividadeList = pppAtividadeList;
	}

	public List<PppCatEntity> getPppCatList() {
		return pppCatList;
	}

	public void setPppCatList(List<PppCatEntity> pppCatList) {
		this.pppCatList = pppCatList;
	}

	public List<PppExameMedicoEntity> getPppExameMedicoList() {
		return pppExameMedicoList;
	}

	public void setPppExameMedicoList(
			List<PppExameMedicoEntity> pppExameMedicoList) {
		this.pppExameMedicoList = pppExameMedicoList;
	}

	public List<PppFatorRiscoEntity> getPppFatorRiscoList() {
		return pppFatorRiscoList;
	}

	public void setPppFatorRiscoList(List<PppFatorRiscoEntity> pppFatorRiscoList) {
		this.pppFatorRiscoList = pppFatorRiscoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}