package dc.entidade.tributario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "tribut_operacao_fiscal")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperacaoFiscalEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_operacao_fiscal_id_seq")
	@SequenceGenerator(name = "tribut_operacao_fiscal_id_seq", sequenceName = "tribut_operacao_fiscal_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("CFOP")
	@Column(name = "cfop")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer cfop;

	@Field
	@Caption("Descrição")
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao = "";

	@Field
	@Caption("Descrição na NF")
	@Column(name = "descricao_na_nf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição da NF é Obrigatório!")
	private String descricaoNaNf = "";

	@Field
	@Caption("Observação")
	@Column(name = "observacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	/**
	 * REFERENCIA - LIST
	 */

	@Fetch(FetchMode.SUBSELECT)
	//@OneToMany(mappedBy="operacaoFiscal",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "operacaoFiscal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ClienteEntity> clienteList = new ArrayList<ClienteEntity>();

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getDescricao();
	}

	/**
	 * CONSTRUTOR
	 */

	public OperacaoFiscalEntity() {
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

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = (cfop == null ? new Integer(0) : cfop);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public String getDescricaoNaNf() {
		return descricaoNaNf;
	}

	public void setDescricaoNaNf(String descricaoNaNf) {
		this.descricaoNaNf = descricaoNaNf;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public List<ClienteEntity> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<ClienteEntity> clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
	       if (this == obj) {
	           return true;
	       }
		
	       if (!(obj instanceof OperacaoFiscalEntity)) {
	           return false;
	       }
		
	       OperacaoFiscalEntity that = (OperacaoFiscalEntity) obj;
	       EqualsBuilder eb = new EqualsBuilder();
	       eb.append(getId(), that.getId());
	       return eb.isEquals();
	}
		
	@Override
	public int hashCode() {
	      if (getId() == null) {
	          return super.hashCode();
	      } else {
	          return new HashCodeBuilder()
	                   .append(id)
	                   .toHashCode();
	      }
	}

}