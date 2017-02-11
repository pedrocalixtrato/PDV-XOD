package br.com.pdv.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Primary;

@SuppressWarnings("serial")
@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable {
	
	@Id  
	@PrimaryKeyJoinColumn
    @Column(name = "CLI_CODIGO")
	@SequenceGenerator(name="id_sequence",sequenceName="HIB_SEQ")
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long codigo;
	@Column(name="CLI_RAZAO")
	@NotNull
	private String nome;
	@Column(name="CLI_FANTASIA")
	private String nomeFantasia;
	@Column(name="CLI_CPFCNPJ")
	private String cpfCnpj;
	@Column(name="CLI_RFINSC")
	private String rgInsc;
	@Column(name="CLI_TIPO")
	private String cliTipo;
	
	@Column(name="CLI_END_ENDERECO")
	private String endEndereco;
	@Column(name="CLI_END_BAIRRO")
	private String endBairro;
	@Column(name="CLI_END_CID_NOME")
	private String endCidNom;
	@Column(name="CLI_END_CID_UF")
	private String endCidUf;
	@Column(name="CLI_END_CEP")
	private String endCep;
	@Column(name="CLI_FONE")
	private String fone;
	@Column(name="CLI_END_COMPLETO")
	private String endComp;
	@Column(name="CLI_END_COMPLEMENTO")
	private String endComplemento;
	@Column(name="CLI_END_NUMERO")
	private String endNum;
	@Column(name="CLI_END_LT")
	private String endLt;
	@Column(name="CLI_END_QD")
	private String endQd;
	@Column(name="CLI_END_COD_IBGEUF")
	private String endIBGEUF;
	@Column(name="CLI_END_COD_IBGEMUNIC")
	private String endIBGEMunc;
	
	@JoinColumn(name = "FIL_CODIGO", referencedColumnName = "FIL_CODIGO")
	@ManyToOne(fetch=FetchType.EAGER)
	private Filiais filiais;
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getRgInsc() {
		return rgInsc;
	}
	public void setRgInsc(String rgInsc) {
		this.rgInsc = rgInsc;
	}
	public String getCliTipo() {
		return cliTipo;
	}
	public void setCliTipo(String cliTipo) {
		this.cliTipo = cliTipo;
	}
	public String getEndEndereco() {
		return endEndereco;
	}
	public void setEndEndereco(String endEndereco) {
		this.endEndereco = endEndereco;
	}
	public String getEndBairro() {
		return endBairro;
	}
	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}
	public String getEndCidNom() {
		return endCidNom;
	}
	public void setEndCidNom(String endCidNom) {
		this.endCidNom = endCidNom;
	}
	public String getEndCidUf() {
		return endCidUf;
	}
	public void setEndCidUf(String endCidUf) {
		this.endCidUf = endCidUf;
	}
	public String getEndCep() {
		return endCep;
	}
	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEndComp() {
		return endComp;
	}
	public void setEndComp(String endComp) {
		this.endComp = endComp;
	}
	public String getEndComplemento() {
		return endComplemento;
	}
	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	public String getEndLt() {
		return endLt;
	}
	public void setEndLt(String endLt) {
		this.endLt = endLt;
	}
	public String getEndQd() {
		return endQd;
	}
	public void setEndQd(String endQd) {
		this.endQd = endQd;
	}
	public String getEndIBGEUF() {
		return endIBGEUF;
	}
	public void setEndIBGEUF(String endIBGEUF) {
		this.endIBGEUF = endIBGEUF;
	}
	public String getEndIBGEMunc() {
		return endIBGEMunc;
	}
	public void setEndIBGEMunc(String endIBGEMunc) {
		this.endIBGEMunc = endIBGEMunc;
	}
	public Filiais getFiliais() {
		return filiais;
	}
	public void setFiliais(Filiais filiais) {
		this.filiais = filiais;
	}
	
	
	
	

}