package dc.entidade.suprimentos.compra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
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

@Entity
@Table(name = "compra_cotacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoCompraEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
    private Integer id;
    
    @Temporal(TemporalType.DATE)
	@Field
	@Caption("Data da cotação")
	@Column(name = "data_cotacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
    @NotNull(message = "Data Cotação é Obrigatório!")
    private Date dataCotacao;
    
	@Field
	@Caption("Descrição")
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao = "";

	@Field
	@Caption("Situação")
	@Column(name = "situacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String situacao = "";

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<FornecedorCotacaoEntity> compraFornecedorCotacaos = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes = new ArrayList<>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public CotacaoCompraEntity() {
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

	public Date getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = (situacao == null ? "".trim() : situacao.toUpperCase()
				.trim());
	}

	public List<FornecedorCotacaoEntity> getCompraFornecedorCotacaos() {
		return compraFornecedorCotacaos;
	}

	public void setCompraFornecedorCotacaos(
			List<FornecedorCotacaoEntity> compraFornecedorCotacaos) {
		this.compraFornecedorCotacaos = compraFornecedorCotacaos;
	}

	public List<ReqCotacaoDetalheEntity> getCompraReqCotacaoDetalhes() {
		return compraReqCotacaoDetalhes;
	}

	public void setCompraReqCotacaoDetalhes(
			List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes) {
		this.compraReqCotacaoDetalhes = compraReqCotacaoDetalhes;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public FornecedorCotacaoEntity addFornecedorCotacao(FornecedorCotacaoEntity fornecedorCotacao) {
		getCompraFornecedorCotacaos().add(fornecedorCotacao);
		fornecedorCotacao.setCotacao(this);

		return fornecedorCotacao;
		
	}
	
	public FornecedorCotacaoEntity removeFornecedorCotacao(FornecedorCotacaoEntity fornecedorCotacao) {
		getCompraFornecedorCotacaos().remove(fornecedorCotacao);
		fornecedorCotacao.setCotacao(null);

		return fornecedorCotacao;
	}

	public ReqCotacaoDetalheEntity addReqCotacaoDetalhe(ReqCotacaoDetalheEntity cotacaoDetalhe) {
		getCompraReqCotacaoDetalhes().add(cotacaoDetalhe);
		cotacaoDetalhe.setCotacao(this);

		return cotacaoDetalhe;
		
	}
	
	public ReqCotacaoDetalheEntity removeReqCotacaoDetalhe(ReqCotacaoDetalheEntity cotacaoDetalhe) {
		getCompraReqCotacaoDetalhes().remove(cotacaoDetalhe);
		cotacaoDetalhe.setCotacao(null);

		return cotacaoDetalhe;
	}

}