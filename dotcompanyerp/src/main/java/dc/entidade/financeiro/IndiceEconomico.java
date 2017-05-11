
package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.PaisEntity;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "indice_economico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceEconomico extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 **/

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "indice_economico_id_seq")
	@SequenceGenerator(name = "indice_economico_id_seq", sequenceName = "indice_economico_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	/*
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "PAIS_ID", nullable = false) private int paisId;
	 */
	@Field
	@Column(name = "sigla")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Sigla")
	private String sigla;

	@Field
	@Column(name = "nome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Nome")
	private String nome;

	@Lob
	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao;

	/*
	 * Mapeamento Pais-Indice
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAIS_ID", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private PaisEntity paisId;

	/**
	 * 
	 * @module CONTABILIDADE
	 */

	@OneToMany(mappedBy = "indiceEconomico")
	private List<IndiceEntity> indiceList;

	/**
	 * CONSTRUTOR
	 */

	public IndiceEconomico() {

	}

	public IndiceEconomico(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PaisEntity getPaisId() {
		return paisId;
	}

	public void setPaisId(PaisEntity paisId) {
		this.paisId = paisId;
	}

	public List<IndiceEntity> getIndiceList() {
		return indiceList;
	}

	public void setIndiceList(List<IndiceEntity> indiceList) {
		this.indiceList = indiceList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return nome;
	}

}