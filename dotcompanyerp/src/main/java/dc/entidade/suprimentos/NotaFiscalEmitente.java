package dc.entidade.suprimentos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.suprimentos.estoque.NotaFiscal;

@Entity
@Table(name = "nfe_emitente")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NotaFiscalEmitente extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Caption("Id")
	// private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emt")
	@SequenceGenerator(name = "emt", sequenceName = "nfe_emitente_id_seq", allocationSize = 1)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_nfe_cabecalho")
	NotaFiscal nota;

	@Column(name = "cpf_cnpj")
	String cpfCnpj;

	@Column(name = "razao_social")
	String razaoSocial;

	@Column(name = "fantasia")
	String nomeFantasia;

	String cep;

	String logradouro;

	String numero;

	String complemento;

	String bairro;

	@Column(name = "codigo_municipio")
	Integer codigoIBGE;

	@Column(name = "nome_municipio")
	String cidade;

	//String uf;
	
	@Caption("UF")
	@ManyToOne(optional = false)
	@JoinColumn(name = "uf")
	private UfEntity uf;

	@Column(name = "inscricao_estadual")
	String inscricaoEstadual;

	String telefone;

	String crt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NotaFiscal getNota() {
		return nota;
	}

	public void setNota(NotaFiscal nota) {
		this.nota = nota;
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(Integer codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCrt() {
		return crt;
	}

	public void setCrt(String crt) {
		this.crt = crt;
	}

}