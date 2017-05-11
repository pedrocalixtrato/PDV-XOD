package dc.entidade.geral.diverso;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "operadora_cartao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperadoraCartaoEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operadora_cartao_id_seq")
	@SequenceGenerator(name = "operadora_cartao_id_seq", sequenceName = "operadora_cartao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Bandeira")
	@Column(name = "BANDEIRA", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Bandeira é Obrigatório!")
	private String bandeira;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Field
	@Caption("Taxa administrativa")
	@Column(name = "TAXA_ADM", precision = 14, scale = 0)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaAdm;

	@Field
	@Caption("Taxa administrativa - Débito")
	@Column(name = "TAXA_ADM_DEBITO", precision = 14, scale = 0)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaAdmDebito;

	@Field
	@Caption("Valor do aluguel pós PIN")
	@Column(name = "VALOR_ALUGUEL_POS_PIN", precision = 14, scale = 0)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	//@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorAluguelPosPin;

	@Field
	@Caption("Vencimento do aluguel")
	@Column(name = "VENCIMENTO_ALUGUEL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer vencimentoAluguel;

	@Field
	@Caption("Telefone 1")
	@Column(name = "FONE1")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone1;

	@Field
	@Caption("Telefone 2")
	@Column(name = "FONE2")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone2;
	
	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Conta da caixa")
	@ManyToOne()
	@JoinColumn(name = "ID_CONTA_CAIXA", nullable = false)
	@NotNull(message = "Conta Caixa é Obrigatório!")
	private ContaCaixa contaCaixa;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public OperadoraCartaoEntity() {

	}

	public OperadoraCartaoEntity(Integer id) {
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

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = (bandeira == null ? "".trim() : bandeira.toUpperCase()
				.trim());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public BigDecimal getTaxaAdm() {
		return taxaAdm;
	}

	public void setTaxaAdm(BigDecimal taxaAdm) {
		this.taxaAdm = taxaAdm;
	}

	public BigDecimal getTaxaAdmDebito() {
		return taxaAdmDebito;
	}

	public void setTaxaAdmDebito(BigDecimal taxaAdmDebito) {
		this.taxaAdmDebito = taxaAdmDebito;
	}

	public BigDecimal getValorAluguelPosPin() {
		return valorAluguelPosPin;
	}

	public void setValorAluguelPosPin(BigDecimal valorAluguelPosPin) {
		this.valorAluguelPosPin = valorAluguelPosPin;
	}

	public Integer getVencimentoAluguel() {
		return vencimentoAluguel;
	}

	public void setVencimentoAluguel(Integer vencimentoAluguel) {
		this.vencimentoAluguel = vencimentoAluguel;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = (fone1 == null ? "".trim() : fone1.toUpperCase().trim());
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = (fone2 == null ? "".trim() : fone2.toUpperCase().trim());
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}
	
	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OperadoraCartaoEntity)) {
            return false;
        }

        OperadoraCartaoEntity that = (OperadoraCartaoEntity) obj;
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