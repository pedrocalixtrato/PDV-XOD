package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "CONFIGURACAO_BOLETO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ConfiguracaoBoleto extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "configuracao_boleto_id_seq")
	@SequenceGenerator(name = "configuracao_boleto_id_seq", sequenceName = "configuracao_boleto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "INSTRUCAO01")
	@Caption(value = "Instrução01")
	@NotNull(message = "Instrução 01 é Obrigatório!")
	private String instrucao01;

	@Field
	@Column(name = "INSTRUCAO02")
	@Caption(value = "Instrução02")
	private String instrucao02;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_REMESSA")
	@Caption(value = "Caminho Arquivo Remessa")
	private String caminhoArquivoRemessa;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_RETORNO")
	@Caption(value = "Caminho Arquivo Retorno")
	private String caminhoArquivoRetorno;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_LOGOTIPO")
	@Caption(value = "Caminho Arquivo Logotipo")
	private String caminhoArquivoLogotipo;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_PDF")
	@Caption(value = "Caminho Arquivo PDF")
	private String caminhoArquivoPdf;

	@Field
	@Column(name = "MENSAGEM")
	@Caption(value = "Mensagem")
	@NotNull(message = "Mensagem é Obrigatório!")
	private String mensagem;

	@Field
	@Column(name = "LOCAL_PAGAMENTO")
	@Caption(value = "Local Pagamento")
	private String localPagamento;

	@Field
	@Column(name = "LAYOUT_REMESSA")
	@Caption(value = "Layout Remessa")
	private Character layoutRemessa;

	@Field
	@Column(name = "ACEITE")
	@Caption(value = "Aceite")
	private Character aceite;

	@Field
	@Column(name = "ESPECIE")
	@Caption(value = "Espécie")
	private Character especie;

	@Field
	@Column(name = "CARTEIRA")
	@Caption(value = "Carteira")
	private Character carteira;

	@Field
	@Column(name = "CODIGO_CONVENIO")
	@Caption(value = "Código Convénio")
	private String codigoConvenio;

	@Field
	@Column(name = "CODIGO_CEDENTE")
	@Caption(value = "Código Cedente")
	private String codigoCedente;

	@Field
	@Column(name = "TAXA_MULTA")
	@Caption(value = "Taxa Multa")
	private BigDecimal taxaMulta;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Caption(value = "Conta Caixa")
	@NotNull(message = "Conta Caixa é Obrigatório!")
	private ContaCaixa contaCaixa;

	public ConfiguracaoBoleto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstrucao01() {
		return instrucao01;
	}

	public void setInstrucao01(String instrucao01) {
		this.instrucao01 = instrucao01;
	}

	public String getInstrucao02() {
		return instrucao02;
	}

	public void setInstrucao02(String instrucao02) {
		this.instrucao02 = instrucao02;
	}

	public String getCaminhoArquivoRemessa() {
		return caminhoArquivoRemessa;
	}

	public void setCaminhoArquivoRemessa(String caminhoArquivoRemessa) {
		this.caminhoArquivoRemessa = caminhoArquivoRemessa;
	}

	public String getCaminhoArquivoRetorno() {
		return caminhoArquivoRetorno;
	}

	public void setCaminhoArquivoRetorno(String caminhoArquivoRetorno) {
		this.caminhoArquivoRetorno = caminhoArquivoRetorno;
	}

	public String getCaminhoArquivoLogotipo() {
		return caminhoArquivoLogotipo;
	}

	public void setCaminhoArquivoLogotipo(String caminhoArquivoLogotipo) {
		this.caminhoArquivoLogotipo = caminhoArquivoLogotipo;
	}

	public String getCaminhoArquivoPdf() {
		return caminhoArquivoPdf;
	}

	public void setCaminhoArquivoPdf(String caminhoArquivoPdf) {
		this.caminhoArquivoPdf = caminhoArquivoPdf;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}

	public Character getLayoutRemessa() {
		return layoutRemessa;
	}

	public void setLayoutRemessa(Character layoutRemessa) {
		this.layoutRemessa = layoutRemessa;
	}

	public Character getAceite() {
		return aceite;
	}

	public void setAceite(Character aceite) {
		this.aceite = aceite;
	}

	public Character getEspecie() {
		return especie;
	}

	public void setEspecie(Character especie) {
		this.especie = especie;
	}

	public Character getCarteira() {
		return carteira;
	}

	public void setCarteira(Character carteira) {
		this.carteira = carteira;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getCodigoCedente() {
		return codigoCedente;
	}

	public void setCodigoCedente(String codigoCedente) {
		this.codigoCedente = codigoCedente;
	}

	public BigDecimal getTaxaMulta() {
		return taxaMulta;
	}

	public void setTaxaMulta(BigDecimal taxaMulta) {
		this.taxaMulta = taxaMulta;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	@Override
	public String toString() {
		return instrucao01;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ConfiguracaoBoleto)) {
            return false;
        }

        ConfiguracaoBoleto that = (ConfiguracaoBoleto) obj;
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
