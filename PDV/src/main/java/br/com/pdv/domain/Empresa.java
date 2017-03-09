package br.com.pdv.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Empresa implements Serializable {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Long id;
	    @Column(name = "RAZAO_SOCIAL")
	    private String razaoSocial;
	    @Column(name = "NOME_FANTASIA")
	    private String nomeFantasia;
	    @Column(name = "CNPJ")
	    private String cnpj;
	    @Column(name = "INSCRICAO_ESTADUAL")
	    private String inscricaoEstadual;
	    @Column(name = "INSCRICAO_MUNICIPAL")
	    private String inscricaoMunicipal;	    
	    @Column(name = "TIPO")
	    private String tipo;	    
	    @Column(name = "EMAIL")
	    private String email;	    
	    @Column(name = "CRT")
	    private String crt;
	    @Column(name = "TIPO_REGIME")
	    private String tipoRegime;	    
	    @Column(name = "CONTATO")
	    private String contato;	    
	    @Column(name = "CODIGO_IBGE_CIDADE")
	    private Integer codigoIbgeCidade;
	    @Column(name = "CODIGO_IBGE_UF")
	    private Integer codigoIbgeUf;  
	    
	    @Column(name = "LOGRADOURO")
	    private String logradouro;
	    @Column(name = "NUMERO")
	    private String numero;
	    @Column(name = "COMPLEMENTO")
	    private String complemento;
	    @Column(name = "BAIRRO")
	    private String bairro;
	    @Column(name = "CIDADE")
	    private String cidade;
	    @Column(name = "CEP")
	    private String cep;
	    @Column(name = "FONE")
	    private String fone;
	    @Column(name = "MUNICIPIO_IBGE")
	    private Integer municipioIbge;
	    @Column(name = "UF")
	    private String uf;	       
	  
	    
	    
	    
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getInscricaoEstadual() {
			return inscricaoEstadual;
		}
		public void setInscricaoEstadual(String inscricaoEstadual) {
			this.inscricaoEstadual = inscricaoEstadual;
		}
		public String getInscricaoMunicipal() {
			return inscricaoMunicipal;
		}
		public void setInscricaoMunicipal(String inscricaoMunicipal) {
			this.inscricaoMunicipal = inscricaoMunicipal;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCrt() {
			return crt;
		}
		public void setCrt(String crt) {
			this.crt = crt;
		}
		public String getTipoRegime() {
			return tipoRegime;
		}
		public void setTipoRegime(String tipoRegime) {
			this.tipoRegime = tipoRegime;
		}
		public String getContato() {
			return contato;
		}
		public void setContato(String contato) {
			this.contato = contato;
		}
		public Integer getCodigoIbgeCidade() {
			return codigoIbgeCidade;
		}
		public void setCodigoIbgeCidade(Integer codigoIbgeCidade) {
			this.codigoIbgeCidade = codigoIbgeCidade;
		}
		public Integer getCodigoIbgeUf() {
			return codigoIbgeUf;
		}
		public void setCodigoIbgeUf(Integer codigoIbgeUf) {
			this.codigoIbgeUf = codigoIbgeUf;
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
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getFone() {
			return fone;
		}
		public void setFone(String fone) {
			this.fone = fone;
		}
		public Integer getMunicipioIbge() {
			return municipioIbge;
		}
		public void setMunicipioIbge(Integer municipioIbge) {
			this.municipioIbge = municipioIbge;
		}
		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
		}		
			
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Empresa other = (Empresa) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
	    
	    
	    
}
