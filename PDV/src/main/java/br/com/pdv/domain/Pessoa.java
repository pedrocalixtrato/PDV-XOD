//package br.com.pdv.domain;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//
//@Entity
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
//public class Pessoa {
//
//	private Long codigo;
//
//	private String rua;
//	private String numero;
//	private String bairro;
//	private String cep;
//	private String complemento;
//	private String telefone;
//	private String celular;
//	private String email;
//	private String cidade;
//	private String estado;
//	private String ibge;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getCodigo() {
//		return codigo;
//	}
//
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//
//	@Column(length = 50)
//	public String getRua() {
//		return rua;
//	}
//
//	public void setRua(String rua) {
//		this.rua = rua;
//	}
//
//	@Column(length = 50)
//	public String getNumero() {
//		return numero;
//	}
//
//	public void setNumero(String numero) {
//		this.numero = numero;
//	}
//
//	@Column(length = 50)
//	public String getBairro() {
//		return bairro;
//	}
//
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//
//	@Column(length = 50)
//	public String getCep() {
//		return cep;
//	}
//
//	public void setCep(String cep) {
//		this.cep = cep;
//	}
//
//	@Column(length = 50)
//	public String getComplemento() {
//		return complemento;
//	}
//
//	public void setComplemento(String complemento) {
//		this.complemento = complemento;
//	}
//
//	@Column(length = 50)
//	public String getTelefone() {
//		return telefone;
//	}
//
//	public void setTelefone(String telefone) {
//		this.telefone = telefone;
//	}
//
//	@Column(length = 50)
//	public String getCelular() {
//		return celular;
//	}
//
//	public void setCelular(String celular) {
//		this.celular = celular;
//	}
//
//	@Column(length = 50)
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	@Column(length = 50)
//	public String getCidade() {
//		return cidade;
//	}
//
//	public void setCidade(String cidade) {
//		this.cidade = cidade;
//	}
//
//	@Column(length = 50)
//	public String getEstado() {
//		return estado;
//	}
//
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
//
//	public String getIbge() {
//		return ibge;
//	}
//
//	public void setIbge(String ibge) {
//		this.ibge = ibge;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pessoa other = (Pessoa) obj;
//		if (codigo == null) {
//			if (other.codigo != null)
//				return false;
//		} else if (!codigo.equals(other.codigo))
//			return false;
//		return true;
//	}
//	
//	
//
//}
