package br.com.pdv.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CFOP implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String CFOP_DESCRICAO;
	
	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCFOP_DESCRICAO() {
		return CFOP_DESCRICAO;
	}
	public void setCFOP_DESCRICAO(String cFOP_DESCRICAO) {
		CFOP_DESCRICAO = cFOP_DESCRICAO;
	}
	
	

}
