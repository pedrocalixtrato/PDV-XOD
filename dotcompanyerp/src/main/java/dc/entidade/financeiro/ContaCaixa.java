package dc.entidade.financeiro;

import java.util.ArrayList;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
import dc.control.enums.ContaCaixaTipoEnum;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;

@Entity
@Table(name = "conta_caixa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContaCaixa extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "conta_caixa_id_seq")
	@SequenceGenerator(name = "conta_caixa_id_seq", sequenceName = "conta_caixa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "CODIGO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Código é Obrigatório!")
	private String codigo = "";

	@Field
	@Caption("Dígito")
	@Column(name = "DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Dígito é Obrigatório!")
	private String digito = "";

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome = "";

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao = "";
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo Conta")
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Tipo Conta é Obrigatório!")
	private ContaCaixaTipoEnum tipoConta;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "ID_AGENCIA_BANCO", nullable = true)
	@NotNull(message = "Agência Banco é Obrigatório!")
	@Caption("Agência Banco")
	@IndexedEmbedded(includePaths={"nome"})
	private AgenciaBancoEntity agenciaBanco;

	/**
	 * REFERENCIA - LIST
	 */
	
	@OneToMany(mappedBy = "contaCaixa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ColaboradorEntity> colaboradorList = new ArrayList<ColaboradorEntity>();
	
	@OneToMany(mappedBy = "contaCaixa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<TalonarioCheque> talonarioList = new ArrayList<TalonarioCheque>();
	
	@OneToMany(mappedBy = "contaCaixa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<OperadoraCartaoEntity> operadoraCartaoList = new ArrayList<OperadoraCartaoEntity>();
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="contaCaixa",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConfiguracaoBoleto> configuraBoletoList = new ArrayList<ConfiguracaoBoleto>();
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="contaCaixa",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ParcelaPagar> parcelaPagarList = new ArrayList<ParcelaPagar>();
	
	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public ContaCaixa() {

	}

	public ContaCaixa(Integer id) {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = (codigo == null ? "".trim() : codigo.toUpperCase().trim());
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = (digito == null ? "".trim() : digito.toUpperCase().trim());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}
	
	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}
	
	public ContaCaixaTipoEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(ContaCaixaTipoEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public AgenciaBancoEntity getAgenciaBanco() {
		return agenciaBanco;
	}

	public void setAgenciaBanco(AgenciaBancoEntity agenciaBanco) {
		this.agenciaBanco = agenciaBanco;
	}
	
	public List<ColaboradorEntity> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<ColaboradorEntity> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}
	
	public List<TalonarioCheque> getTalonarioList() {
		return talonarioList;
	}

	public void setTalonarioList(List<TalonarioCheque> talonarioList) {
		this.talonarioList = talonarioList;
	}
	
	
	public List<OperadoraCartaoEntity> getOperadoraCartaoList() {
		return operadoraCartaoList;
	}

	public void setOperadoraCartaoList(
			List<OperadoraCartaoEntity> operadoraCartaoList) {
		this.operadoraCartaoList = operadoraCartaoList;
	}
	
	public List<ConfiguracaoBoleto> getConfiguraBoletoList() {
		return configuraBoletoList;
	}

	public void setConfiguraBoletoList(List<ConfiguracaoBoleto> configuraBoletoList) {
		this.configuraBoletoList = configuraBoletoList;
	}
	
	public List<ParcelaPagar> getParcelaPagarList() {
		return parcelaPagarList;
	}

	public void setParcelaPagarList(List<ParcelaPagar> parcelaPagarList) {
		this.parcelaPagarList = parcelaPagarList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContaCaixa)) {
            return false;
        }

        ContaCaixa that = (ContaCaixa) obj;
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