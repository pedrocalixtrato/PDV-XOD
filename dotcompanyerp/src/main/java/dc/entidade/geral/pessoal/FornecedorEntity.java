package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.control.enums.LocalizacaoEn;
import dc.control.enums.SimNaoEn;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.patrimonio.BemEntity;

@Entity
@Table(name = "fornecedor")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FornecedorEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fornecedor_id_seq")
	@SequenceGenerator(name = "fornecedor_id_seq", sequenceName = "fornecedor_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption(value = "Desde")
	@Column(name = "desde")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Desde é Obrigatório!")
	private Date desde;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption(value = "Data de cadastro")
	@Column(name = "data_cadastro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption(value = "Cheque nominal à")
	@Column(name = "cheque_nominal_a")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Cheque Nominal a é Obrigatório!")
	private String chequeNominalA = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Observação")
	@Column(name = "observacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	//@NotNull(message = "Observação é Obrigatório!")
	private String observacao = "";

	@Field
	@Caption(value = "Conta do remetente")
	@Column(name = "conta_remetente")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaRemetente = "";

	@Field
	@Caption(value = "Prazo médio de entrega")
	@Column(name = "prazo_medio_entrega")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer prazoMedioEntrega;

	@Field
	@Caption(value = "Número de dias - Primeiro vencimento")
	@Column(name = "num_dias_primeiro_vencimento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numDiasPrimeiroVencimento;

	@Field
	@Caption(value = "Número de dias - Intervalo")
	@Column(name = "num_dias_intervalo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numDiasIntervalo;

	@Field
	@Caption(value = "Quantidade de parcelas")
	@Column(name = "quantidade_parcelas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelas;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Optante do simples nacional")
	@Column(name = "optante_simples_nacional")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Optante Simples Nacional é Obrigatório!")
	private SimNaoEn optanteSimplesNacional;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Localização")
	@Column(name = "localizacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Localização é Obrigatório!")
	private LocalizacaoEn localizacao;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Sofre retenção")
	@Column(name = "sofre_retencao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Sofre Retenção é Obrigatório!")
	private SimNaoEn sofreRetencao;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Gera faturamento")
	@Column(name = "gera_faturamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NotNull(message = "Gera Faturamento é Obrigatório!")
	private SimNaoEn geraFaturamento;

	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	// @IndexedEmbedded
	// @Analyzer(definition = "dc_combo_analyzer")
	// @Caption("Pessoa")
	// @ManyToOne(optional = false, fetch = FetchType.EAGER)
	// @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	// private PessoaEntity pessoa;

	//@Caption("Pessoa")
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "id_pessoa", insertable = true, updatable = true, nullable = true)
	//@JoinColumn(name = "id_pessoa", referencedColumnName = "id")
	//@NotNull(message = "Pessoa é Obrigatório!")
	//private PessoaEntity pessoa;
	
	@Caption("Pessoa")
	//@ManyToOne()
	//@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "ID")
	@OneToOne(optional = false)
	@NotNull(message = "Pessoa é Obrigatório!")
	@IndexedEmbedded(depth=2, includePaths={"nome"})
	private PessoaEntity pessoa;
	
	@Caption("Situação fornecedor / cliente")
	@ManyToOne
	@JoinColumn(name = "id_situacao_for_cli", referencedColumnName = "id")
	@NotNull(message = "Situação fornecedor é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private SituacaoForCliEntity situacaoForCli;

	@Caption("Atividade fornecedor / cliente")
	@ManyToOne()
	@JoinColumn(name = "id_atividade_for_cli", referencedColumnName = "id")
	@NotNull(message = "Atividade fornecedor é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private AtividadeForCliEntity atividadeForCli;

	/**
	 * REFERENCIA - LIST
	 */
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="fornecedor",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BemEntity> bemList = new ArrayList<BemEntity>();


	/**
	 * Módulo: NFE
	 */

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="fornecedor",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NfeCabecalhoEntity> nfeCabecalhoList =  new ArrayList<NfeCabecalhoEntity>();


	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="fornecedor",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<LancamentoPagarEntity> lancamentoPagarList =  new ArrayList<LancamentoPagarEntity>();
	
	/**
	 * TRANSIENT
	 */
	
	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return pessoa.getNome().toUpperCase().trim();
	}

	/*@Transient
	public String getCaption() {
		Object id = this.id;

		if (id == null) {
			id = "";
		}

		String nome = "";

		if (getPessoa() != null && getPessoa().getNome() != null) {
			nome = getPessoa().getNome();
		}

		return "[" + id + "] " + nome;
	}*/

	/**
	 * CONSTRUTOR
	 */

	public FornecedorEntity() {

	}

	public FornecedorEntity(Integer id) {
		this.id = id;
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

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getChequeNominalA() {
		return chequeNominalA;
	}

	public void setChequeNominalA(String chequeNominalA) {
		this.chequeNominalA = (chequeNominalA == null ? "".trim()
				: chequeNominalA.toUpperCase().trim());
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public String getContaRemetente() {
		return contaRemetente;
	}

	public void setContaRemetente(String contaRemetente) {
		this.contaRemetente = (contaRemetente == null ? "".trim()
				: contaRemetente.toUpperCase().trim());
	}

	public Integer getPrazoMedioEntrega() {
		return prazoMedioEntrega;
	}

	public void setPrazoMedioEntrega(Integer prazoMedioEntrega) {
		this.prazoMedioEntrega = (prazoMedioEntrega == null ? new Integer(0)
				: prazoMedioEntrega);
	}

	public Integer getNumDiasPrimeiroVencimento() {
		return numDiasPrimeiroVencimento;
	}

	public void setNumDiasPrimeiroVencimento(Integer numDiasPrimeiroVencimento) {
		this.numDiasPrimeiroVencimento = (numDiasPrimeiroVencimento == null ? new Integer(
				0) : numDiasPrimeiroVencimento);
	}

	public Integer getNumDiasIntervalo() {
		return numDiasIntervalo;
	}

	public void setNumDiasIntervalo(Integer numDiasIntervalo) {
		this.numDiasIntervalo = (numDiasIntervalo == null ? new Integer(0)
				: numDiasIntervalo);
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = (quantidadeParcelas == null ? new Integer(0)
				: quantidadeParcelas);
	}

	public SimNaoEn getOptanteSimplesNacional() {
		return optanteSimplesNacional;
	}

	public void setOptanteSimplesNacional(SimNaoEn optanteSimplesNacional) {
		this.optanteSimplesNacional = optanteSimplesNacional;
	}

	public LocalizacaoEn getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoEn localizacao) {
		this.localizacao = localizacao;
	}

	public SimNaoEn getSofreRetencao() {
		return sofreRetencao;
	}

	public void setSofreRetencao(SimNaoEn sofreRetencao) {
		this.sofreRetencao = sofreRetencao;
	}

	public SimNaoEn getGeraFaturamento() {
		return geraFaturamento;
	}

	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	public void setGeraFaturamento(SimNaoEn geraFaturamento) {
		this.geraFaturamento = geraFaturamento;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}
	
	/*public PessoaEventosEntity getPessoaEventos() {
		return pessoaEventos;
	}
	
	public void setPessoaEventos(PessoaEventosEntity pessoaEventos) {
		this.pessoaEventos = pessoaEventos;
	}*/

	public SituacaoForCliEntity getSituacaoForCli() {
		return situacaoForCli;
	}

	public void setSituacaoForCli(SituacaoForCliEntity situacaoForCli) {
		this.situacaoForCli = situacaoForCli;
	}

	public AtividadeForCliEntity getAtividadeForCli() {
		return atividadeForCli;
	}

	public void setAtividadeForCli(AtividadeForCliEntity atividadeForCli) {
		this.atividadeForCli = atividadeForCli;
	}

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
		return nfeCabecalhoList;
	}

	public void setNfeCabecalhoList(List<NfeCabecalhoEntity> nfeCabecalhoList) {
		this.nfeCabecalhoList = nfeCabecalhoList;
	}

	public List<LancamentoPagarEntity> getLancamentoPagarList() {
		return lancamentoPagarList;
	}

	public void setLancamentoPagarList(
			List<LancamentoPagarEntity> lancamentoPagarList) {
		this.lancamentoPagarList = lancamentoPagarList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return pessoa.getNome();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FornecedorEntity)) {
            return false;
        }

        FornecedorEntity that = (FornecedorEntity) obj;
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