package dc.entidade.suprimentos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.suprimentos.estoque.NotaFiscal;

@Entity
@Table(name = "nfe_transporte")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NFeTransporte extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rje")
	@SequenceGenerator(name = "rje", sequenceName = "nfe_transporte_id_seq", allocationSize = 1)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_nfe_cabecalho")
	NotaFiscal notaFiscal;

	@Column(name = "id_transportadora")
	Integer transportadora;

	@Column(name = "cpf_cnpj")
	String cpfCnpj;

	@Column(name = "nome")
	String razaoSocial;

	@Column(name = "inscricao_estadual")
	String inscricaoEstadual;

	@Column(name = "endereco")
	String logradouro;

	@Column(name = "nome_municipio")
	String cidade;

	String uf;

	@Column(name = "municipio")
	Integer codigoIBGE;

	Integer cfop;

	@Column(name = "valor_bc_retencao_icms")
	BigDecimal baseCalculo;

	@Column(name = "aliquota_retencao_icms")
	BigDecimal aliquota;

	@Column(name = "valor_servico")
	BigDecimal valorServico;

	@Column(name = "valor_icms_retido")
	BigDecimal valorIcmsRetido;

	@Column(name = "uf_veiculo")
	String ufVeiculo;

	@Column(name = "placa_veiculo")
	String placaVeiculo;

	@Column(name = "rntc_veiculo")
	String rntcVeiculo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(Integer codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}

	public void setBaseCalculo(BigDecimal baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public BigDecimal getValorIcmsRetido() {
		return valorIcmsRetido;
	}

	public void setValorIcmsRetido(BigDecimal valorIcmsRetido) {
		this.valorIcmsRetido = valorIcmsRetido;
	}

	public String getUfVeiculo() {
		return ufVeiculo;
	}

	public void setUfVeiculo(String ufVeiculo) {
		this.ufVeiculo = ufVeiculo;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getRntcVeiculo() {
		return rntcVeiculo;
	}

	public void setRntcVeiculo(String rntcVeiculo) {
		this.rntcVeiculo = rntcVeiculo;
	}

}